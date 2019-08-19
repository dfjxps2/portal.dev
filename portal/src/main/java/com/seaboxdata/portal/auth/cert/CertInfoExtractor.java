package com.seaboxdata.portal.auth.cert;

import org.pac4j.core.context.WebContext;
import org.pac4j.core.credentials.extractor.CredentialsExtractor;

public class CertInfoExtractor implements CredentialsExtractor<CertUserCredentials> {
    @Override
    public CertUserCredentials extract(WebContext context) {
        String clientCert = context.getRequestParameter("UserCert");
        String userSignedData = context.getRequestParameter("UserSignedData");
        String containerName = context.getRequestParameter("ContainerName");
        String random = context.getRequestParameter("Random");

        return new CertUserCredentials(clientCert, userSignedData, containerName, random);
    }
}
