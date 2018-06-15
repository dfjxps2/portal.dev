/**
 * <h3>标题 : potal统一门户-section </h3>
 * <h3>描述 : section数据访问接口</h3>
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

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.quick.core.base.ISysBaseDao;

/**
 * section数据访问接口
 */
public interface ISectionDao<SectionDO> extends ISysBaseDao<SectionDO> {

    int insertPageSection(Integer section_id, Integer page_id, Integer section_idx);

    int insertSectionMetric(Integer section_id, Integer metric_id);

    int updateSectionMetric(Integer sec_metric_id, Integer section_id, Integer metric_id);

    int insertSectionMsg(Integer section_id, Integer msg_src_id, Integer msg_type_id);

    int updateSectionMsg(Integer sec_msg_src_id, Integer section_id, Integer msg_src_id, Integer msg_type_id);

    List<Map<String, Object>> selectPageSection(Integer page_id);

    List<Map<String, Object>> selectPageMetric(Integer page_id);

    List<Map<String, Object>> selectPageMetricConfig(Map<String, Object> map);
    
    List<Map<String, Object>> selectPageMetricUserConfig(Map<String, Object> map);

    List<Map<String, Object>> getEditionMetric(Integer app_id);
    
    List<Map<String, Object>> getMetricRoleByUserId(Integer user_id);
    
    int getIdByMetricId(String src_metric_id);
    
    
}