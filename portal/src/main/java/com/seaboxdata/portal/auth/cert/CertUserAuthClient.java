package com.seaboxdata.portal.auth.cert;

import org.pac4j.core.client.DirectClient;
import org.pac4j.core.util.CommonHelper;

public class CertUserAuthClient extends DirectClient<CertUserCredentials, CertUserProfile> {
    @Override
    protected void clientInit() {
        defaultCredentialsExtractor(new CertInfoExtractor());
        defaultAuthenticator(new CertUserAuthenticator());
    }

    @Override
    public String toString() {
        return CommonHelper.toNiceString(this.getClass(), "name", getName(),
                "credentialsExtractor", getCredentialsExtractor(),
                "authenticator", getAuthenticator(), "profileCreator", getProfileCreator(),
                "authorizationGenerators", getAuthorizationGenerators());
    }
}
