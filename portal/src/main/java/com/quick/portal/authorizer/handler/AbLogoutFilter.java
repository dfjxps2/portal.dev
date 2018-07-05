package com.quick.portal.authorizer.handler;

import io.buji.pac4j.context.ShiroSessionStore;
import io.buji.pac4j.filter.LogoutFilter;
import io.buji.pac4j.profile.ShiroProfileManager;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pac4j.core.config.Config;
import org.pac4j.core.context.J2EContext;
import org.pac4j.core.context.session.SessionStore;
import org.pac4j.core.engine.DefaultLogoutLogic;
import org.pac4j.core.engine.LogoutLogic;
import org.pac4j.core.http.J2ENopHttpActionAdapter;

public class AbLogoutFilter extends LogoutFilter{
	
	  private LogoutLogic<Object, J2EContext> logoutLogic;

	    private Config config;

	    private String defaultUrl;

	    private String logoutUrlPattern;

	    private Boolean localLogout;

	    private Boolean centralLogout;

	    public AbLogoutFilter() {
	        logoutLogic = new DefaultLogoutLogic<>();
	        ((DefaultLogoutLogic<Object, J2EContext>) logoutLogic).setProfileManagerFactory(ShiroProfileManager::new);
	    }

	    @Override
	    public void init(final FilterConfig filterConfig) throws ServletException {}

	    @Override
	    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
	        final HttpServletRequest request = (HttpServletRequest) servletRequest;
	      
	        final HttpServletResponse response = (HttpServletResponse) servletResponse;
	        final SessionStore<J2EContext> sessionStore = config.getSessionStore();
	        final J2EContext context = new J2EContext(request, response, sessionStore != null ? sessionStore : ShiroSessionStore.INSTANCE);
	        // remove profiles
/*	    	final ShiroProfileManager manager = new ShiroProfileManager(context);
	        manager.logout();*/
	        final boolean invalidated = sessionStore.destroySession(context);
            if (!invalidated) {
            	System.out.println("The session has not been invalidated for {} channel logout");
            }
            request.getSession().invalidate();
	        logoutLogic.perform(context, config, J2ENopHttpActionAdapter.INSTANCE, this.defaultUrl, this.logoutUrlPattern, this.localLogout, false, this.centralLogout);
	    }

	    @Override
	    public void destroy() {}
	    
	    
	 


	    public Config getConfig() {
	        return config;
	    }

	    public void setConfig(final Config config) {
	        this.config = config;
	    }

	    public String getDefaultUrl() {
	        return this.defaultUrl;
	    }

	    public void setDefaultUrl(final String defaultUrl) {
	        this.defaultUrl = defaultUrl;
	    }

	    public String getLogoutUrlPattern() {
	        return logoutUrlPattern;
	    }

	    public void setLogoutUrlPattern(String logoutUrlPattern) {
	        this.logoutUrlPattern = logoutUrlPattern;
	    }

	    public LogoutLogic<Object, J2EContext> getLogoutLogic() {
	        return logoutLogic;
	    }

	    public void setLogoutLogic(final LogoutLogic<Object, J2EContext> logoutLogic) {
	        this.logoutLogic = logoutLogic;
	    }

	    public Boolean getLocalLogout() {
	        return localLogout;
	    }

	    public void setLocalLogout(final Boolean localLogout) {
	        this.localLogout = localLogout;
	    }

	    public Boolean getCentralLogout() {
	        return centralLogout;
	    }

	    public void setCentralLogout(final Boolean centralLogout) {
	        this.centralLogout = centralLogout;
	    }
}
