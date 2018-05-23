/**
 * <h3>标题 : potal统一门户-comments </h3>
 * <h3>描述 : comments服务实现类</h3>
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

import com.quick.core.base.SysBaseService;
import com.quick.core.base.ISysBaseDao;
import com.quick.core.util.common.QCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.quick.core.base.model.DataStore;
import com.quick.core.util.common.DateTime;

/**
 * comments服务实现类
 */
 @Transactional
 @Service("commentsService")
public class CommentsServiceImpl extends SysBaseService<CommentsDO> implements ICommentsService {
    
    /**
     * 构造函数
     */
    public CommentsServiceImpl() {
        BaseTable = "comments";
        BaseComment = "comments";
        PrimaryKey = "comment_id";
    }
    
    @Autowired
    private ICommentsDao dao;
    
    @Override
    public ISysBaseDao getDao(){
        return dao;
    }
    
    /**
     * 保存业务
     * @return 
     */
    @Override
    public DataStore save(CommentsDO entity) {
        //如果编号为空,新增实体对象,否则更新实体对象
        Integer val = entity.getComment_id();
        int c;
        if(val == null || val == 0) {
            c = dao.insert(entity);
        }else {
            c = dao.update(entity);
        }
        if(c == 0)
            return ActionMsg.setError("操作失败");
        ActionMsg.setValue(entity);
        return ActionMsg.setOk("操作成功");
    }
    
    /**
     * 删除业务
     * @param sysid
     * @return 
     */
    @Override
    public DataStore delete(String sysid) {
        dao.delete(sysid);
        return ActionMsg.setOk("操作成功");
    }
}