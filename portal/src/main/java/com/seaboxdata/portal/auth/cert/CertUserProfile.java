package com.seaboxdata.portal.auth.cert;

import org.pac4j.core.profile.CommonProfile;

public class CertUserProfile extends CommonProfile {
    private String uniqueId;

    public CertUserProfile (String userName, String uniqueId) {
        super();
        setId(userName);
        this.uniqueId = uniqueId;
    }

    public String getUniqueId() {
        return uniqueId;
    }
}
