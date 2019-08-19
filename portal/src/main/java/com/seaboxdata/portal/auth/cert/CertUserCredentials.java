package com.seaboxdata.portal.auth.cert;

import org.pac4j.core.credentials.Credentials;
import org.pac4j.core.util.CommonHelper;

public class CertUserCredentials extends Credentials {
    private String clientCert;
    private String userSignedData;
    private String containerName;
    private String random;

    public CertUserCredentials(String clientCert, String userSignedData, String containerName, String random) {
        this.clientCert = clientCert;
        this.userSignedData = userSignedData;
        this.containerName = containerName;
        this.random = random;
    }

    public String getClientCert() {
        return clientCert;
    }

    public String getUserSignedData() {
        return userSignedData;
    }

    public String getContainerName() {
        return containerName;
    }

    public String getRandom() {
        return random;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final CertUserCredentials that = (CertUserCredentials) o;

        if (clientCert != null ? !clientCert.equals(that.clientCert) : that.clientCert != null) return false;
        if (userSignedData != null ? !userSignedData.equals(that.userSignedData) : that.userSignedData != null)
            return false;
        return !(containerName != null ? !containerName.equals(that.containerName) : that.containerName != null);
    }

    @Override
    public int hashCode() {
        int result = clientCert != null ? clientCert.hashCode() : 0;
        result = 31 * result + (userSignedData != null ? userSignedData.hashCode() : 0);
        result = 31 * result + (containerName != null ? containerName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return CommonHelper.toNiceString(this.getClass(), "ClientCert", this.clientCert,
                "UserSignedData", this.userSignedData, "ContainerName", this.containerName);
    }
}
