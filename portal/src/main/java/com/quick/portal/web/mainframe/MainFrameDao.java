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
 * -------------------------------------------
 * 修改人 修改日期 修改描述
 * -------------------------------------------
 *          
 *          
 * </p>
 */
package com.quick.portal.web.mainframe;

import java.util.List;

import com.quick.core.base.ISysBaseDao;

/**
 * 查询菜单权限
 * @author Administrator
 */
public interface MainFrameDao extends ISysBaseDao {
    
    /*根据userId查询菜单权限 */
    public List<MainFrameBean> searchMainFrame(int userId);
}
