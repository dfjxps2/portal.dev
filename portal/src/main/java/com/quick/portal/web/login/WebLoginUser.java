package com.quick.portal.web.login;

import com.quick.core.util.common.QCommon;
import com.quick.core.util.common.QCookie;
import com.quick.portal.sysUser.SysUserDO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by cc on 2018/4/14.
 */
public class WebLoginUser extends SysUserDO {

    private static final String KEY = "seabox.portal";

    private Integer role_id;

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    /**
     * 保存系统登录信息至Cookies中
     */
    public WebLoginUser loadSession(HttpServletRequest request, HttpServletResponse response) {
        //过期时间为4小时
        try {
            String rid = QCookie.getValue(request, "sbd.role");
            String ids = QCookie.getValue(request, "ids");
            String uname = QCookie.getValue(request, "sbd.user");
            if(!QCommon.isNullOrEmpty(uname))
                uname= URLDecoder.decode(uname, "utf-8");
            if(QCommon.isNullOrEmpty(rid))
                rid = "0";
            if(QCommon.isNullOrEmpty(ids))
                ids = "0";

            this.setRole_id( Integer.valueOf(rid) );
            this.setUser_real_name(uname);
            this.setUser_id(Integer.valueOf(ids));

        } catch (Exception e) {
            System.out.print("无法缓存用户会话信息");
            e.printStackTrace();
        }
        return this;
    }

    /**
     * 保存系统登录信息至Cookies中
     */
    public WebLoginUser saveSession(HttpServletRequest request, HttpServletResponse response) {
        //过期时间为4小时
        try {
            QCookie.set(response, "ids", this.getUser_id().toString()); //浏览器关闭就过期
            QCookie.set(response, "sbd.user",  URLEncoder.encode(this.getUser_real_name(), "utf-8"), 4*3600);
            QCookie.set(response, "sbd.role", this.getRole_id().toString(), 4*3600);
            QCookie.set(response, "sbd.tk", this.createToken(request), 4*3600); //验证参数是否被修改

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
                + this.getUser_id() + "," + this.getRole_id();
        return QCommon.toMD5(seek);
    }
}
