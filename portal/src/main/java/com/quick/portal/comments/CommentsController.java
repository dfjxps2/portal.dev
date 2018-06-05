/**
 * <h3>标题 : potal统一门户-comments </h3>
 * <h3>描述 : comments请求类</h3>
 * <h3>日期 : 2018-05-03</h3>
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
package com.quick.portal.comments;

import com.quick.core.base.ISysBaseService;
import com.quick.core.base.SysBaseController;
import javax.annotation.Resource;

import com.quick.core.base.model.DataStore;
import com.quick.core.util.common.QCommon;
import com.quick.core.util.common.QUpload;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
/**
 * comments请求类
 * @author 你自己的姓名
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/comments")
public class CommentsController extends SysBaseController<CommentsDO> {
    
    @Resource(name = "commentsService")
    private ICommentsService commentsService;
    
    @Override
    public ISysBaseService getBaseService(){
        return commentsService;
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

    @Override
    public DataStore save(CommentsDO model) {
        Integer val = model.getComment_id();
        if(val == null || val == 0){
            String imgdata = rstr("ppimg");
            String txt = rstr("ppcomm");
            if(txt.length() == 0)
                return ActionMsg.setError("请填写相关批示内容");
            if(imgdata.length() == 0)
               return ActionMsg.setError("请上传批示截图");
            //保存图片至upload/comment

            String fname = QCommon.getUUID() + ".png";
            ActionMsg = QUpload.saveImage(request, "/upload/comment/", fname, imgdata);

            if(ActionMsg.isError())
                return ActionMsg.setError("无法保存批示截图,"+ActionMsg.getMsg());

            model.setCmt_state(0);
            model.setCmt_time(new Date());
            model.setConent(txt);
            model.setSnapshot_url("upload/comment/" + fname);
            model.setUser_id(loginer.getUser_id());
        }
        ActionMsg = commentsService.save(model);

        return ActionMsg;
    }

    @RequestMapping
    @ResponseBody
    public DataStore update(Integer comment_id){
        if(comment_id == null || comment_id == 0)
            return  ActionMsg.setError("批示不存在");
        CommentsDO model = commentsService.selectObj(comment_id.toString());
        model.setCmt_state(1);
        ActionMsg = commentsService.save(model);
        return ActionMsg;
    }
}