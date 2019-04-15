package com.seaboxdata.portal.auth.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AuthorizeInfoMapper {

    @Select("select r.role_id from sys_user u, user_role_rela ur, user_role r " +
            "where u.user_id = ur.user_id " +
            "and ur.role_id = r.role_id " +
            "and u.user_name = #{userName}")
    List<String> getRoles(@Param("userName") String userName);

    @Select("select sys_priv_name from user_role r, role_privilege rp, sys_privilege p " +
            "where r.role_id = rp.role_id " +
            "and rp.sys_priv_id = p.sys_priv_id " +
            "and r.role_id = #{roleId}")
    List<String> getPermission(@Param("roleId") String roleId);

    @Select("select user_global_code from sys_user where user_name = #{userName}")
    String getUniqueId(@Param("userName") String userName);
}
