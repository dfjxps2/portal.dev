package com.quick.portal.web.mainframe;

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
import com.quick.core.base.exception.ExceptionEnumServiceImpl;
import com.quick.core.util.common.JsonUtil;
import com.quick.core.util.common.QCookie;
import com.quick.portal.sysMenu.ISysMenuService;
import com.quick.portal.sysUser.ISysUserService;
import com.quick.portal.userAccessLog.IUserAccessLogService;
import com.quick.portal.userAccessLog.UserAccessLogConstants;
import com.quick.portal.userRoleRela.IUserRoleRelaService;
import com.quick.portal.web.login.WebLoginConstants;
import com.quick.portal.web.login.WebLoginUitls;
import com.quick.portal.web.login.WebLoginUser;

/**
 * 查询菜单权限
 *
 * @author Administrator
 */
@Controller
@Scope("prototype")

public class MainFrameController extends SysBaseController<MainFrameBean> {


    @Resource(name = "mainFrameService")
    private MainFrameService mainFrameService;

    @Override
    public ISysBaseService getBaseService() {
        return mainFrameService;
    }

    @Resource(name = "userAccessLogService")
    private IUserAccessLogService userAccessLogService;

    @Resource(name = "sysMenuService")
    private ISysMenuService sysMenuService;

    /*
     * 查询菜单权限
     *
     */
    @RequestMapping(value = "/mainframe")
    public String goMainFrame(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        String jsonStr = "false";
        //根据cookie拿到当前用户的id
        String userId = QCookie.getValue(request, "sbd.user_id");
        String rid = QCookie.getValue(request, "sbd.user_role");

        try {
            //权限菜单
            List<MainFrameBean> menuList = mainFrameService.searchMainFrame(userId);
            if (null != menuList && menuList.size() > 0) {
                MainFrameBean menuTree = this.convertListToTree(menuList);
                jsonStr = JsonUtil.toJson(menuTree.getChildren());
            } else {
                request.getSession().setAttribute("code", ExceptionEnumServiceImpl.USER_RESOURCE_NULL.getCode());
                request.getSession().setAttribute("message", ExceptionEnumServiceImpl.USER_RESOURCE_NULL.getMessage());
                return WebLoginConstants.REDIRECT_KEY.concat(WebLoginConstants.COMMON_ERROR_CONTROLLER);
            }
            model.addAttribute("data", jsonStr);
        } catch (Exception e) {
            request.setAttribute("code", ExceptionEnumServiceImpl.NO_PERMITION.getCode());
            request.setAttribute("message", ExceptionEnumServiceImpl.NO_PERMITION.getMessage() + "ERROR:=" + e.getMessage());
            return WebLoginConstants.REDIRECT_KEY.concat(WebLoginConstants.COMMON_ERROR_CONTROLLER);
        }
        return "page/index/mainframe";
    }


    /*
     * 菜单数据至拼装
     *
     */
    private MainFrameBean convertListToTree(List<MainFrameBean> menuList) {
        if (menuList == null || menuList.size() <= 0) {
            return new MainFrameBean();
        }
        MainFrameBean frameCol = null;
        int depth = 0;
        for (MainFrameBean mfrBean : menuList) {
            //二级及以下菜单
            if (mfrBean.getSuperMenuId() == 0) {
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
    @RequestMapping(value = "/goIframe")
    public String goIframe(HttpServletRequest request, Model model) throws Exception {
        return "page/index/i2";
    }


    //添加用户
    @RequestMapping(value = "/sendLog")
    @ResponseBody
    public void sendLog(HttpServletRequest request, int menuId, String menuNm) throws Exception {
        //记录日志
        try {
            userAccessLogService.saveLog(request,
                    UserAccessLogConstants.SYS_LOG_TYPE_ID,
                    UserAccessLogConstants.OPER_MENU_USER_OP_TYPE,
                    menuId,
                    UserAccessLogConstants.OPER_MENU_USER_OP_DESC.concat(menuNm),
                    null,
                    "");
        } catch (Exception e) {
            throw new Exception("记录日志异常：" + e.getMessage());
        }
    }

    //APP:1;MENU:0
    @RequestMapping(value = "/getIsAppMenuByID")
    @ResponseBody
    public void getIsAppMenuByID(HttpServletRequest req, HttpServletResponse res, int menuId) throws Exception {
        String flag = sysMenuService.getIsAppMenuByID(menuId);
        res.getWriter().write(flag);
    }


//    public WebLoginUser loadPortalUserInfo(HttpServletRequest request, HttpServletResponse response){
//    	 String account = null;
//    	 List<CommonProfile> profiles = WebLoginUitls.getProfiles(request, response);
//    	 for(CommonProfile profile : profiles){
//    		 account =  profile.getId();
//    	 }
//		if (null !=account && !"".equals(account)) {
//			Map<String, Object> parm = new HashMap<>();
//			parm.put("user_name", account);
//			Map<String, Object> u = sysUserService.selectMap(parm);
//			if(null == u || u.isEmpty()){
//				return null;
//			}
//			parm.put("user_id", u.get("user_id"));
//            List<Map<String, Object>> roles = userRoleRelaService.select(parm);
//            WebLoginUser user = WebLoginUitls.getLoginUser(u, roles);
//			user.saveSession(request, response);//保存至本地
//			return user;
//		}
//
//		return null;
//	}

}
