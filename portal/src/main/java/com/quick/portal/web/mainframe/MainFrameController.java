/**
 * <h3>标题 : Quick通用系统框架 </h3>
 * <h3>描述 : 应用中的相关配置信息都放在此</h3>
 * <h3>日期 : 2014-03-23</h3>
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
package com.quick.portal.web.mainframe;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pac4j.core.profile.CommonProfile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quick.core.base.ISysBaseService;
import com.quick.core.base.SysBaseController;
import com.quick.core.util.common.JsonUtil;
import com.quick.core.util.common.QCommon;
import com.quick.core.util.common.QCookie;
import com.quick.core.util.web.WebUtil;
import com.quick.portal.security.authority.metric.PropertiesUtil;
import com.quick.portal.sysMenu.ISysMenuService;
import com.quick.portal.sysUser.ISysUserService;
import com.quick.portal.userAccessLog.IUserAccessLogService;
import com.quick.portal.userAccessLog.UserAccessLogConstants;
import com.quick.portal.web.login.WebLoginUitls;
import com.quick.portal.web.login.WebLoginUser;

/**
 * 查询菜单权限
 *
 * @author Administrator
 */
@Controller
@Scope("prototype")

public class MainFrameController extends SysBaseController<MainFrameBean>{

    
    @Resource(name = "mainFrameService")
    private MainFrameService mainFrameService;
    
    @Override
    public ISysBaseService getBaseService(){
        return mainFrameService;
    }
    
    @Resource(name = "userAccessLogService")
    private IUserAccessLogService userAccessLogService;
    
    @Resource(name = "sysMenuService")
    private ISysMenuService sysMenuService;
    
    @Resource(name = "sysUserService")
    private ISysUserService sysUserService;
    
    
    /*
     * 查询菜单权限
     * 
     */
    @RequestMapping(value = "/mainframe" )
    public String goMainFrame(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
    	String jsonStr = "false";
        //根据cookie拿到当前用户的id
        String userId = QCookie.getValue(request, "ids");
        String rid = QCookie.getValue(request, "sbd.role");
        if(PORTAL_ZORE_VAL.equals(userId) || null == userId || PORTAL_ZORE_VAL.equals(rid) || null == rid){
        	WebLoginUser loginer = loadCASUserInfo(request,response);
        	userId = loginer.getUser_id().toString();
        }
        try{
        	 //权限菜单
            List<MainFrameBean> menuList = mainFrameService.searchMainFrame(userId);
            if(null != menuList && menuList.size()> 0){
            	  MainFrameBean menuTree = this.convertListToTree(menuList);
                  jsonStr = JsonUtil.toJson(menuTree.getChildren());
            }  
            model.addAttribute("data", jsonStr);
        } catch (Exception e){
        	throw new Exception("查询权限菜单异常,权限菜单数据:jsonStr="+jsonStr +"ERROR:="+e.getMessage());
        }
        return "page/index/mainframe";
    }
    

    
    /*
     * 菜单数据至拼装
     * 
     */
    private MainFrameBean convertListToTree(List<MainFrameBean> menuList) {
		if (menuList == null || menuList.size() <= 0){
			return new MainFrameBean();
		}
		MainFrameBean frameCol = null;
		int depth = 0;
		for (MainFrameBean mfrBean : menuList) {
			//二级及以下菜单
			if (mfrBean.getSuperMenuId()==0) {
				frameCol = mfrBean;
				frameCol.setParent(true);
				frameCol.setDepth(depth);
				break;
			}
		}
		frameCol.buildChildren(menuList);
		return frameCol;
	}
    
    
    /*
     * 查询菜单权限
     * 
     */
    @RequestMapping(value = "/goIframe" )
    public String goIframe(HttpServletRequest request, Model model) throws Exception {
        return "page/index/i2";
    }
    
    
    //添加用户
    @RequestMapping(value = "/sendLog")
    @ResponseBody
    public void sendLog(HttpServletRequest request, int menuId,String menuNm) throws Exception {
    	//记录日志
    	try{
    		 userAccessLogService.saveLog(request, UserAccessLogConstants.SYS_LOG_TYPE_ID, UserAccessLogConstants.OPER_MENU_USER_OP_TYPE,menuId,UserAccessLogConstants.OPER_MENU_USER_OP_DESC.concat(menuNm),null,"");
    	}catch(Exception e){
    		throw new Exception("记录日志异常："+e.getMessage());
    	}
    }
    
    //APP:1;MENU:0
    @RequestMapping(value = "/getIsAppMenuByID")
    @ResponseBody
    public void getIsAppMenuByID(HttpServletRequest req,HttpServletResponse res,int menuId) throws Exception {
    	String	flag = sysMenuService.getIsAppMenuByID(menuId);
        res.getWriter().write(flag);
    }
    
    
    public WebLoginUser loadCASUserInfo(HttpServletRequest request,HttpServletResponse response){
    	 String account = null;
    	 List<CommonProfile> profiles = WebLoginUitls.getProfiles(request, response);
    	 for(CommonProfile profile : profiles){
    		 account =  profile.getId();
    	 }
        if (null !=account && !"".equals(account)) {
			Map<String, Object> parm = new HashMap<>();
			parm.put("user_name", account);
			Map<String, Object> u = sysUserService.selectMap(parm);
			WebLoginUser user = new WebLoginUser();
			user.setRole_id(Integer.valueOf(WebLoginUitls.getVal(u, "role_id")) );
			user.setUser_real_name(WebLoginUitls.getVal(u, "user_real_name"));
			user.setUser_id(Integer.valueOf(WebLoginUitls.getVal(u, "user_id")));
			user.setUser_global_id(WebLoginUitls.getVal(u, "user_global_id"));
			user.setUser_name(WebLoginUitls.getVal(u, "user_name"));
			user.saveSession(request, response);//保存至本地
			return user;
		}
		return null;
	}
    
    private String getCasLogoutUrl(HttpServletRequest req){
   	 req.getSession().invalidate();
        String casUrl = PropertiesUtil.getPropery("cas.serverUrl");
        String cUrl = req.getScheme() + "://" + req.getServerName()
                + ":" + req.getServerPort() + req.getContextPath()
                + "/"; 
        String casLogoutUrl =casUrl.concat("/logout?service=").concat(QCommon.urlEncode(cUrl));
        return casLogoutUrl;
   }
    
  //前台弹出alert框
  	public void toAlert(HttpServletResponse response,HttpServletRequest request){
  		String casLogoutUrl = getCasLogoutUrl(request); 
  	    try {
  	         response.setContentType("text/html;charset=UTF-8");
  	         response.setCharacterEncoding("UTF-8");
  	            
  	         OutputStreamWriter out = new OutputStreamWriter(response.getOutputStream());   
  	         
  	         String msg="由于您长时间没有操作，session已过期，请重新登录！";
  	         msg=new String(msg.getBytes("UTF-8"));
  	         
  	         out.write("<meta http-equiv='Content-Type' content='text/html';charset='UTF-8'>");
  	         out.write("<script>");
  	         out.write("alert('"+msg+"');");
  	         out.write("top.location.href = '"+casLogoutUrl+"'; ");
  	         out.write("</script>");
  	         out.flush();
  	         out.close();

  	    } catch (IOException e) {
  	        e.printStackTrace();
  	    }
  	}
  	
  	/*
  	 * 智能道路管理
  	 */
    @RequestMapping(value = "/goRoadIframe" )
    public String goRoadIframe(HttpServletRequest request, Model model) throws Exception {
        return "page/index/roadframe";
    }
    /*
     * 智能视频监控
     */
    @RequestMapping(value = "/goVideoIframe" )
    public String goVideoIframe(HttpServletRequest request, Model model) throws Exception {
        return "page/index/videoframe";
    }
    /*
     * 智慧环境监测
     */
    @RequestMapping(value = "/goEnvIframe" )
    public String goEnvIframe(HttpServletRequest request, Model model) throws Exception {
        return "page/index/envframe";
    }
    
    /*
     * 	智能一卡通管理
     */
    @RequestMapping(value = "/goBusIframe" )
    public String goBusIframe(HttpServletRequest request, Model model) throws Exception {
        return "page/index/busframe";
    }
    /*
     * 智慧能源管理
     */
    @RequestMapping(value = "/goEnergyIframe" )
    public String goEnergyIframe(HttpServletRequest request, Model model) throws Exception {
        return "page/index/energyframe";
    }
    
    /*
     * 智慧园区管理
     */
    @RequestMapping(value = "/goParkframe" )
    public String goParkframe(HttpServletRequest request, Model model) throws Exception {
        return "page/index/parkframe";
    }

  	
	public static final String PORTAL_ZORE_VAL = "0";
}
