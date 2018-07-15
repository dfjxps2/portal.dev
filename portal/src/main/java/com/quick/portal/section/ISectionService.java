/**
 * <h3>标题 : potal统一门户-section </h3>
 * <h3>描述 : section服务接口</h3>
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
package com.quick.portal.section;

import com.quick.core.base.ISysBaseService;

import java.util.List;
import java.util.Map;

/**
 * section服务接口
 */
public interface ISectionService extends ISysBaseService<SectionDO> {

    List<Map<String, Object>> selectPageSection(Integer page_id);

    String selectSectionJson(Integer page_id);

    String selectMetricJson(Integer page_id, Integer user_id,String time);

    String selectLayoutJson(Integer page_id,Integer user_id,String time);
    
    String getEditionMetricJson(Integer app_id,Integer user_id);
}