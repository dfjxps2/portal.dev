package com.seaboxdata.portal.auth;

import static org.pac4j.core.util.CommonHelper.assertNotNull;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pac4j.core.config.Config;
import org.pac4j.core.context.J2EContext;
import org.pac4j.core.engine.CallbackLogic;
import org.pac4j.core.engine.DefaultCallbackLogic;
import org.pac4j.core.http.adapter.J2ENopHttpActionAdapter;
import org.pac4j.springframework.web.CallbackController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quick.portal.security.authority.metric.PropertiesUtil;


@Controller
@Scope("prototype")
@RequestMapping(value = "/callback")
public class AbCallbackController extends CallbackController {

	 private CallbackLogic<Object, J2EContext> callbackLogic = new DefaultCallbackLogic<>();

	    @Value("${pac4j.callback.defaultUrl:#{null}}")
	    private String defaultUrl;

	    @Value("${pac4j.callback.multiProfile:#{null}}")
	    private Boolean multiProfile;

	    @Value("${pac4j.callback.saveInSession:#{null}}")
	    private Boolean saveInSession;

	    @Value("${pac4j.callback.renewSession:#{null}}")
	    private Boolean renewSession;

	    @Value("${pac4j.callback.defaultClient:#{null}}")
	    private String defaultClient;

	    @Autowired
	    private Config config;
	    
	    
	    /*
	     * MDY: 
	     * context.getSessionStore().Pac4jConstants.REQUESTED_URL RETURN NULL
	     * 
	     * redirectUrl SET defaultUrl
	     * 
	     * (non-Javadoc)
	     * @see org.pac4j.springframework.web.CallbackController#callback(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	     */
	    @RequestMapping(value = "/callbackSetDefaultUrl")
		@Override
	    public void callback(final HttpServletRequest request, final HttpServletResponse response) {
	        assertNotNull("callbackLogic", callbackLogic);
	        assertNotNull("config", config);
	        final J2EContext context = new J2EContext(request, response, config.getSessionStore());
	        if(null == this.defaultUrl || "".equals(this.defaultUrl)){
	        	String serviceURL = PropertiesUtil.getPropery("portal.homepage.url");
	   	        this.defaultUrl = serviceURL;
	        }
	        callbackLogic.perform(context, config, J2ENopHttpActionAdapter.INSTANCE, this.defaultUrl,
	            this.saveInSession, this.multiProfile, this.renewSession,
	            this.defaultClient);
	    }


	    public String getDefaultUrl() {
	        return defaultUrl;
	    }

	    public void setDefaultUrl(final String defaultUrl) {
	        this.defaultUrl = defaultUrl;
	    }

	    public CallbackLogic<Object, J2EContext> getCallbackLogic() {
	        return callbackLogic;
	    }

	    public void setCallbackLogic(final CallbackLogic<Object, J2EContext> callbackLogic) {
	        this.callbackLogic = callbackLogic;
	    }

	    public Boolean getMultiProfile() {
	        return multiProfile;
	    }

	    public void setMultiProfile(final Boolean multiProfile) {
	        this.multiProfile = multiProfile;
	    }

	    public Boolean getRenewSession() {
	        return renewSession;
	    }

	    public void setRenewSession(final Boolean renewSession) {
	        this.renewSession = renewSession;
	    }

	    public String getDefaultClient() {
	        return defaultClient;
	    }

	    public void setDefaultClient(final String client) {
	        this.defaultClient = client;
	    }

	    public Config getConfig() {
	        return config;
	    }

	    public void setConfig(final Config config) {
	        this.config = config;
	    }
}
