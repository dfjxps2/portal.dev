/**
 * <h3>标题 : Quick通用系统框架 </h3>
 * <h3>描述 : 应用中的相关配置信息都放在此</h3>
 * <h3>日期 : 2014-09-22</h3>
 * <h3>版权 : Copyright (C) 海口鑫网计算机网络有限公司</h3>
 *
 * <p>
 * @author admin admin@xinwing.com.cn
 * @version <b>v1.0.0</b>
 *
 * <b>修改历史:</b>
 * ------------------------------------------- 修改人 修改日期 修改描述
 * -------------------------------------------
 *
 *
 * </p>
 */
package com.quick.core.base;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pac4j.core.profile.CommonProfile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import com.quick.core.base.model.DataStore;
import com.quick.core.util.common.QCommon;
import com.quick.core.util.common.QRequest;
import com.quick.portal.sysUser.ISysUserService;
import com.quick.portal.web.login.WebLoginUitls;
import com.quick.portal.web.login.WebLoginUser;

/**
 *
 * @author Administrator
 */
public abstract class SysApiController extends SysBaseController<DataStore> {
    public final static int ROLEID_ADMIN = 1;
    
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	
	public Map<String, Object> urlMap;

	public WebLoginUser loginer;

	public DataStore ActionMsg;

	protected UrlPathHelper urlPathHelper; // 路径助手

    @Resource(name = "sysUserService")
	private ISysUserService loginerService;
    
    
    
    public SysApiController(){
		urlMap=new HashMap<String,Object>();
	}
    
    /**
     * 当Action方法执行后被调用
     */
    @Override
    public void actionAfter(HttpServletRequest request,
                            HttpServletResponse response, Object handler,
                            ModelAndView modelAndView) {
        if (modelAndView == null)
            return;
        String viewName = modelAndView.getViewName();

        if (QCommon.isNullOrEmpty(viewName)) {
            modelAndView.clear();
        } else if (viewName.startsWith("redirect:")) {
            // modelAndView.addAllObjects(_ASSIGN_);
        } else {

            String url = request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort() + request.getContextPath();
            Map<String, Object> G = new HashMap<String, Object>();
            G.put("host", url);

            modelAndView.addObject("G", G);

        }
    }
    

    public void config(){}

    @Override
    public DataStore deleteAction(){
        return null;
    }

    @Override
    public Object getData(String json) {
        return null;
    }
    
	/**
	 * 初始化函数，设置相关参数
	 */
	public void init(HttpServletRequest request, HttpServletResponse response,
			Object handler) {
		this.urlPathHelper = new UrlPathHelper();
		this.request = request;
		this.response = response;
		setMobileCurrentLoginUser(request,response);
	}
    
    public WebLoginUser setMobileCurrentLoginUser(HttpServletRequest request,HttpServletResponse response){
		if(loginer == null){
			String account = request.getParameter("u");
	    	 if (null !=account && !"".equals(account)) {
				Map<String, Object> parm = new HashMap<>();
				parm.put("user_id", account);
				Map<String, Object> u = loginerService.selectMap(parm);
				loginer = new WebLoginUser();
//				loginer.setRole_id(Integer.valueOf(val(u, "role_id")) );
				loginer.setUser_real_name(val(u, "user_real_name"));
				loginer.setUser_id(Integer.valueOf(val(u, "user_id")));
				loginer.setUser_global_id(val(u, "user_global_id"));
				loginer.setUser_name(WebLoginUitls.getVal(u, "user_name"));
				loginer.setUser_state(Integer.valueOf(WebLoginUitls.getVal(u, "user_state")));
//				loginer.setRole_type_id(Integer.valueOf(WebLoginUitls.getVal(u, "role_type_id")));
				loginer.saveSession(request, response);//保存至本地
				return loginer;
			}
		}	
		return loginer;
	}
    
    
	public String val(Object obj){
		if(obj == null)
			return "";
		return obj.toString();
	}
	public String val(Map<String, Object> m, String key){
		Object obj = m.get(key);
		if(obj == null)
			return "";
		return obj.toString();
	}
	public String rstr(String name){
		return QRequest.getString(request, name);
	}
	public String rstr(String name, String defValue){
		return QRequest.getString(request, name, defValue);
	}
	public Integer rint(String name){
		return QRequest.getInteger(request, name);
	}
	public Integer rint(String name, Integer defValue){
		return QRequest.getInteger(request, name, defValue);
	}
	public String getUrl(){
		String url = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath();
		return url;
	}

}
