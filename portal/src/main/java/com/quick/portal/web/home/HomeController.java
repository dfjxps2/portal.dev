/**
 * <h3>标题 : portal统一门户-管理驾驶仓 </h3>
 * <h3>描述 : 应用中的相关配置信息都放在此</h3>
 * <h3>日期 : 2018-04-13</h3>
 * <h3>版权 : Copyright (C) 北京东方金信科技有限公司</h3>
 *
 * <p>
 *
 * @author admin mazong@seaboxdata.com
 * @version <b>v1.0.0</b>
 *
 * <b>修改历史:</b>
 * -------------------------------------------
 * 修改人  修改日期   修改描述
 * -------------------------------------------
 *
 *
 * </p>
 */
package com.quick.portal.web.home;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.seaboxdata.portal.common.PortalUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quick.core.base.SysWebController;
import com.quick.core.base.model.DataStore;
import com.quick.core.util.common.JsonUtil;
import com.quick.core.util.common.QCommon;
import com.quick.portal.sysPrivilege.ISysPrivilegeService;
import com.quick.portal.search.infomng.IInfoMngService;
import com.quick.portal.web.login.WebLoginConstants;
import com.quick.portal.web.login.WebLoginUitls;
import com.quick.portal.web.model.DataResult;

/**
 * 门户请求类
 *
 * @author Administrator
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/home")
public class HomeController extends SysWebController {

    @Resource(name = "homeService")
    private IHomeService homeService;
/*

    @Resource(name = "menuPrivilegeService")
    private ISysPrivilegeService menuPrivilegeService;
*/

    @Resource(name = "infoMngService")
    private IInfoMngService infoMngService;


    /**
     * 管理驾驶仓(仪表盘)
     * @param model
     * @return
     */
    @RequestMapping(value = "/main")
    public String main(Model model) {
        //查找管理员按钮权限
        Map<String, Object> parm = new HashMap<>();
        parm.put("user_id", loginer.getUser_id());
        //获取用户应用列表,如果没有，从menu_privilege表读取对应角色默认应用列表
        List<Map<String, Object>> apps = queryUserApp();
        if (apps == null || apps.size() == 0) {
            //如果没有用户桌面，就添加一个
            homeService.addDashboard(parm);
            apps = queryUserApp();
        }

        String habitInfo = infoMngService.getPersonalHabitsInfo(loginer.getUser_id().toString());
        model.addAttribute("txtdata", habitInfo);  //信息搜索
        model.addAttribute("apps", JsonUtil.toJson(apps));

        boolean bool = WebLoginUitls.isAdminRoleType(loginer);
        String flag = WebLoginConstants.ADMINISTRATOR_ROLE_TYPE;
        if (bool) {
            flag = WebLoginConstants.ADMINISTRATOR_ROLE_TYPE;
        } else {
            flag = WebLoginConstants.BUSINESS_ROLE_TYPE;
        }
        model.addAttribute("roleTypeId", flag); //管理员ROLEID
        return view();
    }

    /**
     * 添加应用
     * @param model
     * @return
     */
    @RequestMapping
    public String addapp(Model model) {
        Object apps = getApp();//查询所有应用
        model.addAttribute("apps", JsonUtil.toJson(apps));
        return view();
    }

    /**
     * 删除应用
     * @return
     */
    @PostMapping
    @ResponseBody
    public Object dodel(String id) {
        if (id != null && !id.equals("")) {
            String[] ids = id.split(",");
            for (String str : ids) {
                homeService.deleteApp(str);
            }
        }
        return getUserApp();
    }

    @PostMapping
    @ResponseBody
    public Object doadd(String id) {
        if (id == null || id.equals(""))
            return ActionMsg.setError("请选择要添加的应用");
        String[] ids = id.split(",");
        Map<String, Object> p = new HashMap<>();

        String uid = rstr("u", loginer.getUser_id().toString());
        p.put("user_id", uid);

        Map<String, Object> u = homeService.queryAppConfig(p);
        if (u == null)
            return ActionMsg.setError("读取用户桌面出错，请刷新页面");
        String dashboard_id = val(u, "dashboard_id");
        String sno = val(u, "param_value");
        Integer param_value = sno.length() == 0 ? 1 : Integer.valueOf(sno);
        boolean bool = false;
        Map<String, Object> m = null;
        for (String str : ids) {
            m = new HashMap<String, Object>();
            m.put("dashboard_id", dashboard_id);
            m.put("param_value", param_value);
            m.put("app_id", str);
            m.put("param_id", 1);
            bool = homeService.isExitsAppInfo(m);
            if (!bool) {
                homeService.addApp(m);
            }
            param_value++;
        }
        return getUserApp();
    }

    /**
     * 查询用户所有应用
     * @return
     */
    @PostMapping
    @ResponseBody
    public DataResult getUserApp() {
        return new DataResult(queryUserApp());
    }

    private List<Map<String, Object>> queryUserApp() {
        String app_name = rstr("t");
        if (app_name.length() > 0)
            urlMap.put("app_name", app_name);

        String uid = rstr("u", loginer.getUser_id().toString());
        urlMap.put("user_id", uid);

        List<Map<String, Object>> list = homeService.queryUserApp(urlMap);
        PortalUtils.fixUrl(request, list);
        return list;
    }

    @PostMapping
    @ResponseBody
    public DataStore saveSort(String s) {
        if (QCommon.isNullOrEmpty(s) || !s.startsWith("["))
            return ActionMsg.setError("无效的格式");
        try {
            List<Map<String, Object>> lst = JsonUtil.fromJson(s, List.class, Map.class);
            homeService.updateAppSort(lst);
            return ActionMsg.setOk("OK");
        } catch (Exception e) {
            return ActionMsg.setError("无效的格式," + e.getMessage());
        }
    }


    /**
     * 查询所有应用
     * @return
     */
    @PostMapping
    @ResponseBody
    public Object getApp() {
        String uid = rstr("u", loginer.getUser_id().toString());
        urlMap.put("user_id", uid);

        List<Map<String, Object>> list = homeService.queryApp(urlMap);

        PortalUtils.fixUrl(request, list);
        return list;
    }

    /**
     * 查询所有应用2
     * @return
     */
    @PostMapping
    @ResponseBody
    public Object getAllApp() {
        String uid = rstr("u", loginer.getUser_id().toString());

        List<Map<String, Object>> list = homeService.queryUserAllApp(urlMap);

        PortalUtils.fixUrl(request, list);
        return list;
    }


    @RequestMapping
    public String listinfo(Model model) {
        String ids = loginer.getUser_id().toString();
        String habitInfo = infoMngService.getPersonalHabitsInfo(ids);
        model.addAttribute("data", habitInfo);
        return view();
    }
}