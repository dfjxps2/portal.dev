/**
 * <h3>标题 : potal统一门户-section </h3>
 * <h3>描述 : section服务实现类</h3>
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

import com.quick.core.base.SysBaseService;
import com.quick.core.base.ISysBaseDao;
import com.quick.core.util.common.QCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.quick.core.base.model.DataStore;
import com.quick.core.util.common.DateTime;

import java.util.List;
import java.util.Map;

/**
 * section服务实现类
 */
 @Transactional
 @Service("sectionService")
public class SectionServiceImpl extends SysBaseService<SectionDO> implements ISectionService {
    
    /**
     * 构造函数
     */
    public SectionServiceImpl() {
        BaseTable = "section";
        BaseComment = "section";
        PrimaryKey = "section_id";
        NameKey = "section_name";
    }
    
    @Autowired
    private ISectionDao dao;
    
    @Override
    public ISysBaseDao getDao(){
        return dao;
    }
    
    /**
     * 保存业务
     * @return 
     */
    @Override
    public DataStore save(SectionDO entity) {
        //如果编号为空,新增实体对象,否则更新实体对象
        Integer val = entity.getSection_id();
        int c = 0;
        if(val == null || val == 0) {
            entity.setUpd_time( DateTime.Now().getTime() );  //修改时间
            c = dao.insert(entity);
        }else {
            entity.setUpd_time( DateTime.Now().getTime() );  //修改时间
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

    @Override
    public List<Map<String, Object>> selectPageSection(Integer page_id){
        return dao.selectPageSection(page_id);
    }
    @Override
    public String selectLayoutJson(Integer page_id){
        List<Map<String, Object>> ls = dao.selectPageSection(page_id);
        List<Map<String,Object>> metricls = dao.selectPageMetric(page_id);
        List<Map<String,Object>> mconfigls = dao.selectPageMetricConfig(page_id);
        if(ls == null || ls.size() == 0)
            return "[]";
        String json = "";
        int i, l = ls.size();
        for(i = 0; i < l; i++){
            String j = getSectionConfig(ls.get(i));
            String m = getMetricConfig(ls.get(i).get("section_id"),metricls, mconfigls);
            json += "," + j.substring(0, j.length() - 1) + ",metric:" + m + "}";
        }
        json = "[" + json.substring(1) + "]";
        return json;
    }
    @Override
    public String selectSectionJson(Integer page_id){
        List<Map<String, Object>> lst = dao.selectPageSection(page_id);
        if(lst == null || lst.size() == 0)
            return "";
        String json = "[";
        int i, l = lst.size() - 1;
        for(i = 0; i < l; i++){
            json += getSectionConfig(lst.get(i)) + ",";
        }
        json += getSectionConfig(lst.get(l)) + "]";
        return json;
    }

    @Override
    public String selectMetricJson(Integer page_id) {
        List<Map<String,Object>> metriclst = dao.selectPageMetric(page_id);
        List<Map<String,Object>> mconfiglst = dao.selectPageMetricConfig(page_id);
        String json = "[";
        int i, l = metriclst.size() - 1;
        for(i = 0; i < l; i++){
            json += getMetricConfig(metriclst.get(i), mconfiglst) + ",";
        }
        json += getMetricConfig(metriclst.get(l), mconfiglst) + "]";
        return json;
    }

    private String getSectionConfig(Map<String, Object> m){
        String json  = m.get("section_url").toString();
        if(QCommon.isNullOrEmpty(json))
            return "{}";
        return "{\"id\":" + m.get("section_id") + "," + json.substring(1);
    }
    private String getMetricConfig(Object section_value, List<Map<String,Object>> metrics, List<Map<String,Object>> config){
        String section_id = section_value.toString();
        String json = "";
        for(Map<String,Object> m : metrics){
            String sid = m.get("section_id").toString();
            String smid = m.get("sec_metric_id").toString();
            if(section_id.equals(sid)){
                String json2 = "{"
                        + findSubMetricConfig(smid, "1", "metric_id", config)
                        + "," + findSubMetricConfig(smid, "2", "category_id", config)
                        + "," + findSubMetricConfig(smid, "3", "dimension", config)
                        + "," + findSubMetricConfig(smid, "4", "charts", config)
                        + "," + findSubMetricConfig(smid, "5", "numb", config)
                        + "," + findSubMetricConfig(smid, "6", "measure_name", config)
                        + "}";
                json += "," + json2;
            }
        }
        if(json.length()>1)
            json = json.substring(1);
        return "[" + json + "]";
    }
    private String getMetricConfig(Map<String, Object> m, List<Map<String,Object>> config){
        String sid = m.get("sec_metric_id").toString();
        String json = "{\"section_id\":"+m.get("section_id")
                + "," + findSubMetricConfig(sid, "1", "metric_id", config)
                + "," + findSubMetricConfig(sid, "2", "category_id", config)
                + "," + findSubMetricConfig(sid, "3", "dimension", config)
                + "," + findSubMetricConfig(sid, "4", "charts", config)
                + "," + findSubMetricConfig(sid, "5", "numb", config)
                + "," + findSubMetricConfig(sid, "6", "measure_name", config)
                + "}";
        return json;
    }
    private String findSubMetricConfig(String id, String no, String name, List<Map<String,Object>> config){
        for(Map<String,Object> m : config){
            String sid = m.get("sec_metric_id").toString();
            String sno = m.get("param_id").toString();
            if(id.equals(sid) && no.equals(sno))
                return "\""+name+"\":\"" + m.get("param_value").toString() + "\"";
        }
        return "\""+name+"\":\"\"";
    }
}