package com.seaboxdata.portal.auth.cert;

import org.pac4j.core.profile.CommonProfile;

public class CertUserProfile extends CommonProfile {
    private String uniqueId;
    private String uniqueIdCode;

    public CertUserProfile (String userName, String uniqueId, String uniqueIdCode) {
        super();
        setId(userName);
        this.uniqueId = uniqueId;
        this.uniqueIdCode = uniqueIdCode;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public String getUniqueIdCode() {
        return uniqueIdCode;
    }
}
