package com.seaboxdata.portal.auth;

import com.quick.portal.security.authority.metric.PropertiesUtil;
import org.pac4j.cas.client.rest.CasRestFormClient;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.cas.credentials.authenticator.CasRestAuthenticator;
import org.pac4j.cas.profile.CasProfile;
import org.pac4j.cas.profile.CasRestProfile;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.credentials.TokenCredentials;
import org.pac4j.core.credentials.UsernamePasswordCredentials;
import org.pac4j.core.exception.TechnicalException;
import org.pac4j.core.util.CommonHelper;
import org.pac4j.core.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MyCasRestAuthenticator extends CasRestAuthenticator {
    private static final Logger logger = LoggerFactory.getLogger(MyCasRestAuthenticator.class);


    public MyCasRestAuthenticator(CasConfiguration configuration) {
        super(configuration);
    }

    public void validate(UsernamePasswordCredentials credentials, WebContext context) {
        if (credentials != null && credentials.getPassword() != null && credentials.getUsername() != null) {
            String ticketGrantingTicketId = this.requestTicketGrantingTicket(credentials.getUsername(), credentials.getPassword(), context);
            if (CommonHelper.isNotBlank(ticketGrantingTicketId)) {
                CasRestProfile casRestProfile = new CasRestProfile(ticketGrantingTicketId, credentials.getUsername());
                CasRestFormClient casRestFormClient = new CasRestFormClient(configuration, credentials.getUsername(), credentials.getPassword());
                String serviceURL = PropertiesUtil.getPropery("portal.homepage.url");
                TokenCredentials tokenCredentials = casRestFormClient.requestServiceTicket(serviceURL, casRestProfile, context);
                CasProfile casProfile = casRestFormClient.validateServiceTicket(serviceURL, tokenCredentials, context);
                casRestFormClient.destroyTicketGrantingTicket(casRestProfile, context);
                credentials.setUserProfile(casProfile);
            }

        } else {
            logger.error("Credentials are required");
        }
    }

    private String requestTicketGrantingTicket(String username, String password, WebContext context) {
        HttpURLConnection connection = null;

        String var9;
        try {
            connection = HttpUtils.openPostConnection(new URL(this.configuration.computeFinalRestUrl(context)));
            String payload = HttpUtils.encodeQueryParam("username", username) + "&" + HttpUtils.encodeQueryParam("password", password);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8));
            out.write(payload);
            out.close();
            String locationHeader = connection.getHeaderField("location");
            int responseCode = connection.getResponseCode();
            if (locationHeader != null && responseCode == 201) {
                var9 = locationHeader.substring(locationHeader.lastIndexOf("/") + 1);
                return var9;
            }

            logger.debug("Ticket granting ticket request failed: " + locationHeader + " " + responseCode + HttpUtils.buildHttpErrorMessage(connection));
            var9 = null;
        } catch (IOException var13) {
            throw new TechnicalException(var13);
        } finally {
            HttpUtils.closeConnection(connection);
        }

        return var9;
    }
}
