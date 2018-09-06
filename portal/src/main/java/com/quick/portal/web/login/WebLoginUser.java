package com.quick.portal.web.login;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quick.portal.userRole.UserRoleDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quick.core.util.common.QCommon;
import com.quick.core.util.common.QCookie;
import com.quick.portal.security.authority.metric.PropertiesUtil;
import com.quick.portal.sysUser.SysUserDO;

/**
 * Created by cc on 2018/4/14.
 */
public class WebLoginUser extends SysUserDO {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String KEY = "seabox.portal";

    private Integer role_id;

    private int requestSerial = 0;

    public int getRequestSerial() {
        return requestSerial;
    }

    public void setRequestSerial(int requestSerial) {
        this.requestSerial = requestSerial;
    }

    public WebLoginUser() {
    }

    public WebLoginUser(SysUserDO u) {
        super(u);
    }

    /**
     * 保存系统登录信息至Cookies中
     */
    public WebLoginUser loadSession(HttpServletRequest request, HttpServletResponse response) {
        try {
            String userRole = QCookie.getValue(request, "sbd.user_role");
            String userId = QCookie.getValue(request, "sbd.user_id");
            String userRealName = QCookie.getValue(request, "sbd.user_real_name");
            String gid = QCookie.getValue(request, "sbd.gid");
            String uid = QCookie.getValue(request, "sbd.user_name");
            String userState = QCookie.getValue(request, "sbd.ustate");


            if (!QCommon.isNullOrEmpty(userRealName))
                userRealName = URLDecoder.decode(userRealName, "utf-8");
            if (QCommon.isNullOrEmpty(userRole))
                userRole = "";
            if (QCommon.isNullOrEmpty(userId))
                userId = "0";
            if (QCommon.isNullOrEmpty(userState))
                userState = "0";

            this.setRoleList(this.extractRoleListStr(userRole));
            this.setUser_real_name(userRealName);
            this.setUser_id(Integer.valueOf(userId));
            this.setUser_global_id(gid);
            this.setUser_name(uid);
            String requestSerial = QCookie.getValue(request, "request.serial");
            this.setRequestSerial(requestSerial == null ? 0 : Integer.valueOf(requestSerial));
            this.setUser_state(Integer.valueOf(userState));
        } catch (Exception e) {
            System.out.print("无法从会话中读取用户信息");
            e.printStackTrace();
        }

        return this;
    }

    /**
     * 保存系统登录信息至Cookies中
     */
    public WebLoginUser saveSession(HttpServletRequest request, HttpServletResponse response) {
        try {
            String userNm = "";
            if (null != this.getUser_real_name() && !this.getUser_real_name().equals("")) {
                userNm = URLEncoder.encode(this.getUser_real_name(), "utf-8");
            }

            int cookieTTL = Integer.valueOf(PropertiesUtil.getPropery("portal.session.timeout"));
            logger.debug("Set portal session  timeout to {} seconds.", cookieTTL);

            QCookie.set(response, "sbd.user_id", this.getUser_id().toString()); //浏览器关闭就过期
            QCookie.set(response, "sbd.user_real_name", userNm, cookieTTL);
            QCookie.set(response, "sbd.user_name", this.getUser_name(), cookieTTL);
            QCookie.set(response, "sbd.user_role", this.constructRoleListStr(), cookieTTL);
            QCookie.set(response, "sbd.tk", this.createToken(request), cookieTTL); //验证参数是否被修改
            QCookie.set(response, "sbd.gid", this.getUser_global_id(), cookieTTL);
            QCookie.set(response, "request.serial", String.valueOf(this.requestSerial), cookieTTL);
            QCookie.set(response, "sbd.ustate", String.valueOf(this.getUser_state()), cookieTTL);

        } catch (Exception e) {
            System.out.print("无法缓存用户会话信息");
            e.printStackTrace();
        }
        return this;
    }

    public String createToken(HttpServletRequest request) throws Exception {
        String seek = request.getHeader("User-Agent")
                + request.getRemoteAddr()
                + KEY
                + this.getUser_id();
        return QCommon.toMD5(seek);
    }

    public String constructRoleListStr() {
        return getRoleList().stream()
                .map((r) -> r.getRole_id().toString() + ":" + r.getRole_type_id().toString())
                .reduce("", (r1, r2) -> r1 + "#" + r2);
    }

    public List<UserRoleDO> extractRoleListStr(String roleListStr) {
        return Arrays.stream(roleListStr.split("#"))
                .filter((s) -> s.length() > 0)
                .map((s) -> {
                    String[] a = s.split(":");
                    UserRoleDO roleDO = new UserRoleDO();
                    roleDO.setRole_id(Integer.valueOf(a[0]));
                    roleDO.setRole_type_id(Integer.valueOf(a[1]));
                    return roleDO;
                }).collect(Collectors.toList());
    }
}
