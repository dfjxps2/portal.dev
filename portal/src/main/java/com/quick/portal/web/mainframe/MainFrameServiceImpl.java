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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quick.core.base.SysBaseService;

/**
 * 查询菜单权限
 * @author Administrator
 */
@Service("mainFrameService")
public class MainFrameServiceImpl extends SysBaseService<MainFrameBean> implements MainFrameService {

    @Autowired
    private MainFrameDao dao;

	@Override
	public List<MainFrameBean> searchMainFrame(String userId) {
		// TODO Auto-generated method stub
		Integer uid = Integer.valueOf(userId);
		List<MainFrameBean> mainFrameList= dao.searchMainFrame(uid);
		return mainFrameList;
	}

}
