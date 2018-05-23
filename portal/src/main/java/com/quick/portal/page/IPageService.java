/**
 * <h3>标题 : potal统一门户-page </h3>
 * <h3>描述 : page服务接口</h3>
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
package com.quick.portal.page;

import com.quick.core.base.ISysBaseService;
import com.quick.core.base.model.DataStore;

/**
 * page服务接口
 */
public interface IPageService extends ISysBaseService<PageDO> {
    /**
     * 保存所有业务信息
     * @param entity
     * @param app_id
     * @return
     */
    DataStore save(PageDO entity, Integer app_id, String section_json, String metric_json);
}