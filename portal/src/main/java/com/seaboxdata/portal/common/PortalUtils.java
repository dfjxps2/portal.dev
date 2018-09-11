package com.seaboxdata.portal.common;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.quick.portal.web.login.WebLoginUitls.val;

public class PortalUtils {
    public static void fixUrl(HttpServletRequest request, List<Map<String, Object>> list) {

        String contextUrl = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + request.getContextPath();

        for (Map<String, Object> m : list) {
            String app_url = val(m, "app_url");
            String app_preview_url = val(m, "app_preview_url");
            String menu_icon_url = val(m, "menu_icon_url");
            if (app_url.length() > 0 && !app_url.startsWith("http:"))
                m.put("app_url", contextUrl + "/" + app_url);
            if (app_preview_url.length() > 0 && !app_preview_url.startsWith("http:"))
                m.put("app_preview_url", contextUrl + "/" + app_preview_url);
            if (menu_icon_url.length() > 0 && !menu_icon_url.startsWith("http:"))
                m.put("menu_icon_url", contextUrl + "/" + menu_icon_url);
        }
    }

}
