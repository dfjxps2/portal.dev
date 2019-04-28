package com.seaboxdata.portal.auth;

import com.quick.core.util.common.CommonUtils;
import org.pac4j.core.authorization.checker.AuthorizationChecker;
import org.pac4j.core.authorization.checker.DefaultAuthorizationChecker;
import org.pac4j.core.client.Client;
import org.pac4j.core.client.Clients;
import org.pac4j.core.client.DirectClient;
import org.pac4j.core.client.finder.ClientFinder;
import org.pac4j.core.client.finder.DefaultSecurityClientFinder;
import org.pac4j.core.config.Config;
import org.pac4j.core.context.J2EContext;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.credentials.Credentials;
import org.pac4j.core.engine.DefaultSecurityLogic;
import org.pac4j.core.engine.SecurityGrantedAccessAdapter;
import org.pac4j.core.engine.decision.ProfileStorageDecision;
import org.pac4j.core.exception.HttpAction;
import org.pac4j.core.http.adapter.HttpActionAdapter;
import org.pac4j.core.http.ajax.AjaxRequestResolver;
import org.pac4j.core.http.ajax.DefaultAjaxRequestResolver;
import org.pac4j.core.matching.MatchingChecker;
import org.pac4j.core.matching.RequireAllMatchersChecker;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.ProfileManager;
import org.pac4j.core.util.CommonHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MySecurityLogic<R, C extends WebContext> extends DefaultSecurityLogic<R, C> {
    private ClientFinder clientFinder = new DefaultSecurityClientFinder();
    private AuthorizationChecker authorizationChecker = new DefaultAuthorizationChecker();
    private MatchingChecker matchingChecker = new RequireAllMatchersChecker();
    private ProfileStorageDecision profileStorageDecision = new MyProfileStorageDecision();
    private AjaxRequestResolver ajaxRequestResolver = new DefaultAjaxRequestResolver();

    public MySecurityLogic() {
    }

    public R perform(C context, Config config, SecurityGrantedAccessAdapter<R, C> securityGrantedAccessAdapter, HttpActionAdapter<R, C> httpActionAdapter, String clients, String authorizers, String matchers, Boolean inputMultiProfile, Object... parameters) {
        this.logger.debug("=== SECURITY ===");

        HttpAction action;
        try {
            boolean multiProfile;
            if (inputMultiProfile == null) {
                multiProfile = false;
            } else {
                multiProfile = inputMultiProfile;
            }

            CommonHelper.assertNotNull("context", context);
            CommonHelper.assertNotNull("config", config);
            CommonHelper.assertNotNull("httpActionAdapter", httpActionAdapter);
            CommonHelper.assertNotNull("clientFinder", this.clientFinder);
            CommonHelper.assertNotNull("authorizationChecker", this.authorizationChecker);
            CommonHelper.assertNotNull("matchingChecker", this.matchingChecker);
            CommonHelper.assertNotNull("profileStorageDecision", this.profileStorageDecision);
            Clients configClients = config.getClients();
            CommonHelper.assertNotNull("configClients", configClients);
            this.logger.debug("url: {}", context.getFullRequestURL());
            this.logger.debug("matchers: {}", matchers);
            if (!this.matchingChecker.matches(context, matchers, config.getMatchers())) {
                this.logger.debug("no matching for this request -> grant access");
                return securityGrantedAccessAdapter.adapt(context, Arrays.asList(), parameters);
            }

            this.logger.debug("clients: {}", clients);
            List<Client> currentClients = this.clientFinder.find(configClients, context, clients);
            this.logger.debug("currentClients: {}", currentClients);
            boolean loadProfilesFromSession = this.profileStorageDecision.mustLoadProfilesFromSession(context, currentClients);
            this.logger.debug("loadProfilesFromSession: {}", loadProfilesFromSession);
            ProfileManager manager = this.getProfileManager(context, config);
            List<CommonProfile> profiles = manager.getAll(loadProfilesFromSession);
            this.logger.debug("profiles: {}", profiles);
            if (CommonHelper.isEmpty(profiles) && CommonHelper.isNotEmpty(currentClients)) {
                boolean updated = false;
                Iterator var18 = currentClients.iterator();

                while (var18.hasNext()) {
                    Client currentClient = (Client) var18.next();
                    if (currentClient instanceof DirectClient) {
                        this.logger.debug("Performing authentication for direct client: {}", currentClient);
                        Credentials credentials = currentClient.getCredentials(context);
                        this.logger.debug("credentials: {}", credentials);
                        CommonProfile profile = currentClient.getUserProfile(credentials, context);
                        this.logger.debug("profile: {}", profile);
                        if (profile != null) {
                            boolean saveProfileInSession = this.profileStorageDecision.mustSaveProfileInSession(context, currentClients, (DirectClient) currentClient, profile);
                            this.logger.debug("saveProfileInSession: {} / multiProfile: {}", saveProfileInSession, multiProfile);
                            manager.save(saveProfileInSession, profile, multiProfile);
                            updated = true;
                            if (!multiProfile) {
                                break;
                            }
                        }
                    }
                }

                if (updated) {
                    profiles = manager.getAll(loadProfilesFromSession);
                    this.logger.debug("new profiles: {}", profiles);
                }
            }

            if (CommonHelper.isNotEmpty(profiles)) {
                this.logger.debug("authorizers: {}", authorizers);
                if (this.authorizationChecker.isAuthorized(context, profiles, authorizers, config.getAuthorizers())) {
                    this.logger.debug("authenticated and authorized -> grant access");
                    return securityGrantedAccessAdapter.adapt(context, profiles, parameters);
                }

                this.logger.debug("forbidden");
                action = this.forbidden(context, currentClients, profiles, authorizers);
            } else if (this.startAuthentication(context, currentClients)) {
                this.logger.debug("Starting authentication");
                this.saveRequestedUrl(context, currentClients);
                action = this.redirectToIdentityProvider(context, currentClients);
            } else {
                this.logger.debug("unauthorized");
                action = this.unauthorized(context, currentClients);
            }
        } catch (Exception var23) {
            return this.handleException(var23, httpActionAdapter, context);
        }

        return httpActionAdapter.adapt(action.getCode(), context);
    }
}
