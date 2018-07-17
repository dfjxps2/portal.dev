package com.seaboxdata.portal.auth;

import org.pac4j.cas.client.rest.CasRestBasicAuthClient;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.core.credentials.extractor.BasicAuthExtractor;

public class MyCasRestBasicAuthClient extends CasRestBasicAuthClient {

    public MyCasRestBasicAuthClient() {

    }

    public MyCasRestBasicAuthClient(CasConfiguration configuration, String headerName, String prefixHeader) {
        super(configuration, headerName, prefixHeader);
    }

    protected void clientInit() {
        this.defaultCredentialsExtractor(new BasicAuthExtractor(getHeaderName(), getPrefixHeader()));
        this.defaultAuthenticator(new MyCasRestAuthenticator(this.configuration));
    }
}
