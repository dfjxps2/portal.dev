package com.quick.portal.web.login;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }
    
    
    private String role_ids;
    
    private String role_type_ids;

    /**
     * 保存系统登录信息至Cookies中
     */
    public WebLoginUser loadSession(HttpServletRequest request, HttpServletResponse response) {
        try {
            String rid = QCookie.getValue(request, "sbd.role");
            String ids = QCookie.getValue(request, "ids");
            String uname = QCookie.getValue(request, "sbd.user");
            String gid = QCookie.getValue(request, "sbd.gid");
            String uid = QCookie.getValue(request, "sbd.uid");
			String ustate = QCookie.getValue(request, "sbd.ustate");
			String rtype = QCookie.getValue(request, "sbd.rtype");
			

            if (!QCommon.isNullOrEmpty(uname))
                uname = URLDecoder.decode(uname, "utf-8");
            if (QCommon.isNullOrEmpty(rid))
                rid = "0";
            if (QCommon.isNullOrEmpty(ids))
                ids = "0";
			if (QCommon.isNullOrEmpty(ustate))
            	ustate = "0";
			if (QCommon.isNullOrEmpty(rtype))
				rtype = "0";
			 
            this.setRole_ids(rid);
            this.setUser_real_name(uname);
            this.setUser_id(Integer.valueOf(ids));
            this.setUser_global_id(gid);
            this.setUser_name(uid);
            String requestSerial = QCookie.getValue(request, "request.serial");
            this.setRequestSerial(requestSerial == null ? 0 : Integer.valueOf(requestSerial));
			this.setUser_state(Integer.valueOf(ustate));
			this.setRole_type_ids(rtype);
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
        try {
          
            String userNm = "";
            if(null ==this.getUser_real_name() || this.getUser_real_name().equals("")){
            	userNm = userNm;
            }else{
            	userNm = URLEncoder.encode(this.getUser_real_name(), "utf-8");
            }

            int cookieTTL = Integer.valueOf(PropertiesUtil.getPropery("portal.session.timeout"));
            logger.debug("Set portal session  timeout to {} seconds.", cookieTTL);
            QCookie.set(response, "ids", this.getUser_id().toString(),cookieTTL); //浏览器关闭就过期
            QCookie.set(response, "sbd.user", userNm, cookieTTL);
            QCookie.set(response, "sbd.uid", this.getUser_name(), cookieTTL);
            QCookie.set(response, "sbd.role", this.getRole_ids(), cookieTTL);
            QCookie.set(response, "sbd.tk", this.createToken(request), cookieTTL); //验证参数是否被修改
            QCookie.set(response, "sbd.gid", this.getUser_global_id(), cookieTTL);
            QCookie.set(response, "request.serial", String.valueOf(this.requestSerial), cookieTTL);
			QCookie.set(response, "sbd.ustate", String.valueOf(this.getUser_state()),cookieTTL);
			QCookie.set(response, "sbd.rtype", this.getRole_type_ids(),cookieTTL);

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

	public String getRole_ids() {
		return role_ids;
	}

	public void setRole_ids(String role_ids) {
		this.role_ids = role_ids;
	}

	public String getRole_type_ids() {
		return role_type_ids;
	}

	public void setRole_type_ids(String role_type_ids) {
		this.role_type_ids = role_type_ids;
	}
}
