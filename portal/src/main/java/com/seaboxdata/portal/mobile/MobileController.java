package com.seaboxdata.portal.mobile;

import com.quick.portal.sysUser.SysUserDO;
import com.quick.portal.web.model.DataResult;

import org.pac4j.core.context.J2EContext;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.ProfileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mobile")
public class MobileController {
    final private Logger logger = LoggerFactory.getLogger(getClass());
    
    @RequestMapping(value = "/")
    public String index(HttpServletRequest request, HttpServletResponse response) {
    	 return "page/home/login";
    }

    @RequestMapping(value = "/login", produces = {"text/json;charset=UTF-8"})
    public DataResult login(HttpServletRequest request, HttpServletResponse response) {
        final WebContext context = new J2EContext(request, response);
        List<CommonProfile> profiles = new ProfileManager<>(context).getAll(true);

        if (profiles.size() == 0) {
            logger.debug("No user profile found.");
            return new DataResult(0, "User id is not available.");
        }

        CommonProfile profile = profiles.get(0);

        logger.debug("Received login request from user {}", profile.getId());

        Map<String, Object> userAttrs = profile.getAttributes();
        SysUserDO sysUserDO = new SysUserDO();
        sysUserDO.setUser_id(Integer.valueOf((String)userAttrs.get("user_id")));
        sysUserDO.setUser_name(profile.getId());
        sysUserDO.setUser_real_name((String)userAttrs.get("user_real_name"));
        sysUserDO.setUser_global_id((String)userAttrs.getOrDefault("user_global_id", ""));
        DataResult result = new DataResult().setOk("OK");
        result.setData(sysUserDO);

        return result;
    }
}
