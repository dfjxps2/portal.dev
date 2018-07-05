package com.quick.portal.authorizer.util;

import org.apache.shiro.session.Session;

public class PortalShiroProvidedSessionStore extends PortalShiroSessionStore{

	
	private Session session;
	
	
	public PortalShiroProvidedSessionStore(Session session) {
		this.session = session;
	}
	

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
	protected Session getSession(final boolean createSession) {
        return getSession();
    }
	
	
}
