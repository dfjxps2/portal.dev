/**
 * <h3>标题 : potal统一门户-sys_menu </h3>
 * <h3>描述 : sys_menu请求类</h3>
 * <h3>日期 : 2018-04-13</h3>
 * <h3>版权 : Copyright (C) 北京东方金信科技有限公司</h3>
 * 
 * <p>
 * @author 你自己的姓名 mazong@seaboxdata.com
 * @version <b>v1.0.0</b>
 *          
 * <b>修改历史:</b>
 * -------------------------------------------
 * 修改人 修改日期 修改描述
 * -------------------------------------------
 *          
 *          
 * </p>
 */
package com.quick.portal.sysMenu;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.quick.core.base.ISysBaseService;
import com.quick.core.base.SysBaseController;
import com.quick.core.base.model.DataStore;

/**
 * sys_menu请求类
 * @author 你自己的姓名
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/sysMenu")
public class SysMenuController extends SysBaseController<SysMenuDO> {
    
    @Resource(name = "sysMenuService")
    private ISysMenuService sysMenuService;
    
    @Override
    public ISysBaseService getBaseService(){
        return sysMenuService;
    }
    
    public DataStore ActionMsg;
    
    //页面请求
    @RequestMapping
    public String list(ModelMap model) {
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
    
    @RequestMapping(value = "/deletes")
	@ResponseBody
    public DataStore deletes(String menu_id, int state) {
    	String[] arr = menu_id.split(",");
		List<String> id = Arrays.asList(arr);
		return sysMenuService.deletes(id, state);
	}
    
    @RequestMapping(value ="/listAllMenu")
    @ResponseBody
    public List<Map<String,Object>> listAllMenu(){
        return sysMenuService.listAllMenu();
    }
    
    
    //保存图片
    /**
     * 保存集成系统及图片
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/savepic",method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    //BO({code:1,msg:'ok'}) DTO(Map<String,Object>)
    public DataStore saveActions(DefaultMultipartHttpServletRequest request, HttpServletResponse response, SysMenuDO model) {
        try{
            Map<String, MultipartFile> fileMap = request.getFileMap();
            // 文件存储路径
            StringBuilder uploadFilePath = new StringBuilder();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = new Date(); //获取当前时间
            String strDateTime = sdf.format(date);
           uploadFilePath.append("menuImg"); //年月
            //suploadFilePath.append(File.separator);
            //uploadFilePath.append(strDateTime.substring(6, 8)); //日

            // 文件上传时间
            SimpleDateFormat sdfFile = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strFileTime = sdfFile.format(date);

            //创建文件夹
            File dirPath = new File(request.getSession().getServletContext().getRealPath("")+"/upload/" + uploadFilePath.toString());
            if (!dirPath.exists()) {
                dirPath.mkdirs();
            }
            uploadFilePath.append(File.separator);

            // 时分秒
            String strHms = strDateTime.substring(8);

            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
                // 获取上传文件
                MultipartFile mf = entity.getValue();
                // 获取文件名
                String fileName = mf.getOriginalFilename();

                long Size =mf.getSize();
                //                strEnc = des.encrypt(fileName);
                // 返回一个随机UUID
                String uuid = UUID.randomUUID().toString().replaceAll("\\-", "");
                // 获取文件名后缀
                String suffix = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".")) : "";
                // 构成新文件名
                String newFileName = strHms + "_" + uuid + suffix;
                // 上传文件
                File uploadFile = new File(dirPath+File.separator+ newFileName);
                FileCopyUtils.copy(mf.getBytes(), uploadFile);
                String url = "upload/"+uploadFilePath.toString() +newFileName;
                url = url.replace("\\", "/");
                model.setMenu_icon_url(url);
            }

        }catch(Exception e)
        {
            return ActionMsg.setError("操作失败");
        }
        ActionMsg = saveBefore(model);//saveBefore(model);
        if(ActionMsg.isError())
            return ActionMsg;
        ActionMsg = save(model);
        if(ActionMsg.isError())
            return ActionMsg;

        return saveAfter(model);
    }

    @RequestMapping(value ="/getApp")
    @ResponseBody
    public List<Map<String,Object>> getApp(){
        return sysMenuService.getApp();
    }
}