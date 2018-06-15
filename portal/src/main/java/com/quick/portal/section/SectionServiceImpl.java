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
import com.quick.portal.secMetricConfig.ISecMetricConfigDao;

import java.util.ArrayList;
import java.util.HashMap;
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
    
    @Autowired
    private ISecMetricConfigDao configDao;
    
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
    public String selectLayoutJson(Integer page_id,Integer user_id){
        List<Map<String, Object>> ls = dao.selectPageSection(page_id);
        List<Map<String,Object>> metricls = dao.selectPageMetric(page_id);
        
        List<Map<String,Object>> li = configDao.selectTime(user_id);
        Map<String, Object> map1 = new HashMap<String, Object>();
    	map1.put("user_id", user_id);
    	map1.put("page_id", page_id);
        String cre_time = "";
        List<Map<String,Object>> mconfigls = dao.selectPageMetricConfig(map1);
        if (li.size()>0) {
        	cre_time = li.get(0).get("cre_time").toString();
        	map1.put("cre_time", cre_time);
        	mconfigls = mergeData(map1,"show");
		}
        //mergeData(mconfigls,page_id,user_id);
        if(ls == null || ls.size() == 0)
            return "[]";
        String json = "";
        int i, l = ls.size();
        for(i = 0; i < l; i++){
            String j = getSectionConfig(ls.get(i));
            String m = getMetricConfig(ls.get(i).get("section_id"),metricls, mconfigls,user_id);
            json += "," + j.substring(0, j.length() - 1) + ",metric:" + m + "}";
        }
        json = "[" + json.substring(1) + "]";
        return json;
    }
    
    public List<Map<String,Object>> mergeData(Map<String,Object> map,String type){
    	 List<Map<String,Object>> mconfigls = dao.selectPageMetricConfig(map);
         List<Map<String,Object>> userMconfigls = dao.selectPageMetricUserConfig(map);
         if (type.equals("show")&&userMconfigls.size()>0) {
	         for (int i = 0; i < userMconfigls.size(); i++) {
	        		 if (userMconfigls.get(i).get("param_id").toString().equals("9")&&userMconfigls.get(i).get("param_value").toString().equals("1")) {
	     				String secId = userMconfigls.get(i).get("sec_metric_id").toString();
	     				for (int j = 0; j < userMconfigls.size(); j++) {
	     					if (userMconfigls.get(j).get("sec_metric_id").toString().equals(secId)) {
	     						mconfigls.add(userMconfigls.get(j));
	     					}
	     				}
	     			}
				}
         }else if (type.equals("set")&&userMconfigls.size()>0) {
			for (int k = 0; k < userMconfigls.size(); k++) {
 				mconfigls.add(userMconfigls.get(k));
 			}
		}
    	return mconfigls;
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
    public String selectMetricJson(Integer page_id,Integer user_id) {
        List<Map<String,Object>> metriclst = dao.selectPageMetric(page_id);
        List<Map<String,Object>> li = configDao.selectTime(user_id);
    	 Map<String, Object> map1 = new HashMap<String, Object>();
     	map1.put("user_id", user_id);
     	map1.put("page_id", page_id);
         String cre_time = "";
         List<Map<String,Object>> mconfiglst = dao.selectPageMetricConfig(map1);
         if (li.size()>0) {
         	cre_time = li.get(0).get("cre_time").toString();
         	map1.put("cre_time", cre_time);
         	mconfiglst = mergeData(map1,"set");
 		}
        if(metriclst == null || metriclst.size() == 0 || mconfiglst == null || mconfiglst.size() == 0)
            return "[]";
        String json = "";
        int i, l = metriclst.size() - 1;
        for(i = 0; i < l; i++){
        	 String json1 = "";
        	if (getMetricConfig(metriclst.get(i), mconfiglst,user_id).length()>0) {
                json1 = getMetricConfig(metriclst.get(i), mconfiglst,user_id);
                if (json1.equals("")==false) {
            		json +=","+json1;
    			}
			}
        }
        json += ','+getMetricConfig(metriclst.get(l), mconfiglst,user_id) + "]";
        if (json.length()>1) {
            json = json.substring(1);
		}
        json =  "["+json;
        return json;
    }

    private String getSectionConfig(Map<String, Object> m){
        String json  = m.get("section_url").toString();
        if(QCommon.isNullOrEmpty(json))
            return "{}";
        return "{\"id\":" + m.get("section_id") + "," + json.substring(1);
    }
    private String getMetricConfig(Object section_value, List<Map<String,Object>> metrics, List<Map<String,Object>> config,Integer user_id){
        String section_id = section_value.toString();
        String json = "";
        if (user_id.equals(1)==false) {
        	List<Map<String,Object>> metric_role = dao.getMetricRoleByUserId(user_id);
            config = setConfig("1",config, metric_role);
		}
        for(Map<String,Object> m : metrics){
            String sid = m.get("section_id").toString();
            String smid = m.get("sec_metric_id").toString();
            if(section_id.equals(sid)){
                String json2 = "";
                		if (findSubMetricConfig(smid, "1", "metric_id", config).toString().equals("")==false) {
                		json2 = json2+"{"+ findSubMetricConfig(smid, "1", "metric_id", config)
                        + "," + findSubMetricConfig(smid, "2", "category_id", config)
                        + "," + findSubMetricConfig(smid, "3", "dimension", config)
                        + "," + findSubMetricConfig(smid, "4", "charts", config)
                        + "," + findSubMetricConfig(smid, "5", "numb", config)
                        + "," + findSubMetricConfig(smid, "6", "measure_name", config)
                        + "," + findSubMetricConfig(smid, "7", "time_dim", config)
                        + "," + findSubMetricConfig(smid, "8", "unit", config)
                        + "," + findSubMetricConfig(sid, "9", "display", config)
                        + "}";
                		}
                		if (json2.length()>0) {
                            json += "," + json2;	
						}
            }
        }
        if(json.length()>1)
            json = json.substring(1);
        return "[" + json + "]";
    }
    private String getMetricConfig(Map<String, Object> m, List<Map<String,Object>> config,Integer user_id){
    	 if (user_id.equals(1)==false) {
         	List<Map<String,Object>> metric_role = dao.getMetricRoleByUserId(user_id);
             config = setConfig("1",config, metric_role);
 		}
        String sid = m.get("sec_metric_id").toString();
        String json = "";
        if (findSubMetricConfig(sid, "1", "metric_id", config).toString().equals("")==false) {
        	json = json+"{\"section_id\":"+m.get("section_id")
        	+ "," + findSubMetricConfig(sid, "1", "metric_id", config)
            + "," + findSubMetricConfig(sid, "2", "category_id", config)
            + "," + findSubMetricConfig(sid, "3", "dimension", config)
            + "," + findSubMetricConfig(sid, "4", "charts", config)
            + "," + findSubMetricConfig(sid, "5", "numb", config)
            + "," + findSubMetricConfig(sid, "6", "measure_name", config)
            + "," + findSubMetricConfig(sid, "7", "time_dim", config)
            + "," + findSubMetricConfig(sid, "8", "unit", config)
            + "," + findSubMetricConfig(sid, "9", "display", config)
            + "}";
		}
        return json;
    }
    private String findSubMetricConfig(String id, String no, String name, List<Map<String,Object>> config){
        for(Map<String,Object> m : config){
            String sid = m.get("sec_metric_id").toString();
            String sno = m.get("param_id").toString();
            if(id.equals(sid) && no.equals(sno))
                return "\""+name+"\":\"" + m.get("param_value").toString() + "\"";
        }
        return "";
        //return "\""+name+"\":\"\"";
    }
    
    private List<Map<String,Object>> setConfig(String name,List<Map<String,Object>> config,List<Map<String,Object>> metric_role){
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>(); 
    	for (int i = 0; i < config.size(); i++) {
    		if ((config.get(i).get("param_id").toString()).equals(name)) {
        		Integer id = dao.getIdByMetricId((String) config.get(i).get("param_value"));
        		String conId = "";
        		for (int j = 0; j < metric_role.size(); j++) {
    				if (id.equals(metric_role.get(j).get("metric_id"))) {
    					conId=config.get(i).get("sec_metric_id").toString();
    				}
    			}
        		if (conId.equals("")==false) {
            		for (int k = 0; k < config.size(); k++) {
    					if (config.get(k).get("sec_metric_id").toString().equals(conId)) {
    						list.add(config.get(k));
    					}
    				}
				}
			}
		}
        return list;
    }

	@Override
	public String getEditionMetricJson(Integer app_id, Integer user_id) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> metric_list = dao.getEditionMetric(app_id);
		return null;
	}
}