package com.quick.portal.security.synchrodata.bjcadata.util;

import java.text.*;
import java.util.*;

public class Config {
    public Config() {
    }

    public String getProperValue(String keyName){
        String configValue = null;
        if(configValue == null)
            try {
                ResourceBundle resBundle = ResourceBundle.getBundle("com.quick.portal.security.synchrodata.bjcadata.util.Config");
                configValue = resBundle.getString(keyName);
                if(configValue != null) {
                    configValue = new String(configValue.getBytes("ISO-8859-1"),
                                             "GB2312");
                }
            }
            catch(Exception e) {
                configValue = null;
            }
        return configValue;
    }

}
