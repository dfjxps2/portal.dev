/**
 * <h3>标题 : potal统一门户-application </h3>
 * <h3>描述 : application请求类</h3>
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
package com.quick.portal.application;

import com.quick.core.base.ISysBaseService;
import com.quick.core.base.SysBaseController;
import javax.annotation.Resource;
import javax.faces.application.Application;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quick.core.base.model.DataStore;
import com.quick.core.base.model.JsonDataGrid;
import com.quick.core.base.model.PageBounds;
import com.quick.core.util.common.QCommon;
import com.quick.core.util.type.TypeUtil;
import com.quick.portal.appClassRela.AppClassRelaDO;
import com.quick.portal.appClassRela.IAppClassRelaService;
import com.quick.portal.security.authority.metric.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * application请求类
 * @author 你自己的姓名
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/application")
public class ApplicationController extends SysBaseController<ApplicationDO> {
    
    @Resource(name = "applicationService")
    private IApplicationService applicationService;

    @Resource(name = "appClassRelaService")
    private IAppClassRelaService appClassRelaService;
    
    @Override
    public ISysBaseService getBaseService(){
        return applicationService;
    }
    
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
    public String bizlist(ModelMap model) {
        return view();
    }
    @RequestMapping
    public String bizedit(ModelMap model) {
        return view();
    }

    @Override
    public DataStore save(ApplicationDO model) {
        //保存上传图片
        String up_url = saveUpload();
        if(up_url.length() > 0)
            model.setApp_preview_url(up_url);
        //保存应用
        ActionMsg = getBaseService().save(model);
        if (ActionMsg.isError())
            return ActionMsg;

        //保存应用分类
        String acids = rstr("app_class_id");
        String rids = rstr("rel_id");
        if(!QCommon.isNullOrEmpty(acids)){
            appClassRelaService.deleteByAppId(model.getApp_id().toString());
            String[] rel_ids = rids.split(",");
            String[] app_class_ids = acids.split(",");
            for(int i = 0; i < app_class_ids.length; i++){
                AppClassRelaDO m = new AppClassRelaDO();
                if(i < rel_ids.length)
                    m.setRel_id( (Integer)TypeUtil.parse(Integer.class, rel_ids[i]) );
                m.setApp_class_id( (Integer)TypeUtil.parse(Integer.class, app_class_ids[i]) );
                m.setApp_id(model.getApp_id());

                appClassRelaService.save(m);
            }
        }
        return ActionMsg;
    }

    @RequestMapping(value = "/getBizList", method = RequestMethod.POST)
    @ResponseBody
    public Object getBizList() throws Exception {
        return getQuery("is_app_page=1");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public DataStore update(Integer app_id){
        if(app_id == null || app_id == 0)
            return  ActionMsg.setError("应用不存在");
        ApplicationDO model = applicationService.selectObj(app_id.toString());
        model.setPub_date(new Date());
        ActionMsg = applicationService.save(model);
        return ActionMsg;
    }

    public String saveUpload(){
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
        //将request变成多部分request
        MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
        //创建文件夹
        String baseDir = PropertiesUtil.getPropery("file.dir");
        File dirPath = new File(baseDir + SRC_UPLOAD_PATH);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
        //获取multiRequest 中所有的文件名
        Iterator iter=multiRequest.getFileNames();

        try {
            while (iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {
                    //上传
                    // 获取文件名后缀
                    String oldname = file.getOriginalFilename();
                    String suffix = oldname.indexOf(".") != -1 ? oldname.substring(oldname.lastIndexOf(".")) : "";
                    String fname = QCommon.getUUID() + suffix;
                    File uploadFile = new File(dirPath+File.separator+ fname);
                    FileCopyUtils.copy(file.getBytes(), uploadFile);
                    String url = TARGE_UPLOAD_PATH + fname;
                    return url;
                }
            }
        }catch (Exception e){
            System.out.print("[application]无法保存上传文件:" + e.getMessage());
            e.printStackTrace();
        }
        return "";
    }
    
    private final static String TARGE_UPLOAD_PATH = "upload/files/pc/";
    
    private final static String SRC_UPLOAD_PATH = "/pc";
}