/**
 * <h3>标题 : potal统一门户-app_class_rela </h3>
 * <h3>描述 : app_class_rela数据访问接口</h3>
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
package com.quick.portal.appClassRela;

import com.quick.core.base.ISysBaseDao;

import java.util.List;
import java.util.Map;

/**
 * app_class_rela数据访问接口
 */
public interface IAppClassRelaDao<AppClassRelaDO> extends ISysBaseDao<AppClassRelaDO> {
    /**
     * 查询关联分类
     * @param m
     * @return
     */
    List<Map<String, Object>> selectRela(Map<String, Object> m);

    int deleteByAppId(String appId);
}