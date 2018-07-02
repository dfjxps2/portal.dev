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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quick.core.base.ISysBaseService;
import com.quick.core.base.SysBaseController;
import com.quick.core.util.common.JsonUtil;
import com.quick.core.util.web.WebUtil;
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
        String userId = WebUtil.getCookieUsrid(request);
        if("".equals(userId) || null == userId){
        	WebLoginUser loginer = loadCASUserInfo(request,response);
        	userId = loginer.getUser_id().toString();
//        	throw new Exception("当前用户为空，查询权限菜单异常");
        }
        try{
        	 //权限菜单
            List<MainFrameBean> menuList = mainFrameService.searchMainFrame(userId);
            if(null != menuList && menuList.size()> 0){
            	  MainFrameBean menuTree = this.convertListToTree(menuList);
                  jsonStr = JsonUtil.toJson(menuTree.getChildren());
            }  
            model.addAttribute("data", jsonStr);
            System.out.println("jsonStr="+jsonStr);
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
    public void getIsAppMenuByID(HttpServletResponse res,int menuId) throws Exception {
    	String	flag = sysMenuService.getIsAppMenuByID(menuId);
        res.getWriter().write(flag);
    }
    
    
    public WebLoginUser loadCASUserInfo(HttpServletRequest request,HttpServletResponse response){
		if (request.getRemoteUser() != null) {
			Map<String, Object> parm = new HashMap<>();
			parm.put("user_name", request.getRemoteUser());
			Map<String, Object> u = sysUserService.selectMap(parm);
			WebLoginUser user = new WebLoginUser();
			user.setRole_id( Integer.valueOf(WebLoginUitls.getVal(u, "role_id")) );
			user.setUser_real_name(WebLoginUitls.getVal(u, "user_real_name"));
			user.setUser_id(Integer.valueOf(WebLoginUitls.getVal(u, "user_id")));
			user.setUser_global_id(WebLoginUitls.getVal(u, "user_global_id"));
			user.saveSession(request, response);//保存至本地
			return user;
		}
		return null;
	}
}
