package com.seaboxdata.portal.auth;

import com.seaboxdata.portal.auth.mapper.AuthorizeInfoMapper;
import org.pac4j.core.authorization.generator.AuthorizationGenerator;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.profile.CommonProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBAuthGenerator implements AuthorizationGenerator<CommonProfile> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private AuthorizeInfoMapper authorizeInfoMapper;

    public AuthorizeInfoMapper getAuthorizeInfoMapper() {
        return authorizeInfoMapper;
    }

    public void setAuthorizeInfoMapper(AuthorizeInfoMapper authorizeInfoMapper) {
        this.authorizeInfoMapper = authorizeInfoMapper;
    }

    @Override
    public CommonProfile generate(WebContext webContext, CommonProfile commonProfile) {

        String userName = commonProfile.getUsername();

        if (userName == null){
            userName = commonProfile.getId();
        }

        logger.debug("Getting authorization info for user {}", userName);

        for (String role : authorizeInfoMapper.getRoles(userName)) {
            commonProfile.addRole(role);
            commonProfile.addPermissions(authorizeInfoMapper.getPermission(role));
        }

        commonProfile.addAttribute("uniqueId", authorizeInfoMapper.getUniqueId(userName));

        return commonProfile;
    }
}
