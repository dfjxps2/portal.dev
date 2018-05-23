package com.quick.portal.indSetting;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quick.core.base.ISysBaseService;
import com.quick.core.base.SysBaseController;
import com.quick.core.base.model.DataStore;
import com.quick.portal.sysMenu.ISysMenuService;
import com.quick.portal.sysMenu.SysMenuDO;


/**
 * sys_menu请求类
 * @author 你自己的姓名
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/indSetting")
public class IndSettingController  extends SysBaseController<SysMenuDO> {

	 @Resource(name = "IndSettingService")
	    private IndSettingService IndSettingService;
	    
	    @Override
	    public ISysBaseService getBaseService(){
	        return IndSettingService;
	    }
	    
	    public DataStore ActionMsg;
	    
	    //页面请求
	    @RequestMapping
	    public String list(ModelMap model) {
	    	System.out.println("---------------------------------------------");
			return view();
	    }
	    @RequestMapping
	    public String edit(ModelMap model) {
	        return view();
	    }
	    @RequestMapping
	    public String chose(ModelMap model) {
	        return view();
	    }
	    
	    @RequestMapping
	       public String info(ModelMap model) {
	    	return view();
	    }
}
