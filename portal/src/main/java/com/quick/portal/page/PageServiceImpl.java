/**
 * <h3>标题 : potal统一门户-page </h3>
 * <h3>描述 : page服务实现类</h3>
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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quick.core.base.ISysBaseDao;
import com.quick.core.base.SysBaseService;
import com.quick.core.base.model.DataStore;
import com.quick.core.util.common.DateTime;
import com.quick.core.util.common.JsonUtil;
import com.quick.core.util.common.QCommon;
import com.quick.portal.appPage.AppPageDO;
import com.quick.portal.appPage.IAppPageDao;
import com.quick.portal.metric.IMetricDao;
import com.quick.portal.secMetricConfig.ISecMetricConfigDao;
import com.quick.portal.secMetricConfig.SecMetricConfigDo;
import com.quick.portal.secMetricConfig.UserActiveConfigDo;
import com.quick.portal.section.ISectionDao;
import com.quick.portal.section.SectionDO;
import com.quick.portal.sectionMetric.ISectionMetricDao;
import com.quick.portal.sectionMetric.SectionMetricDO;

/**
 * page服务实现类
 */
 @Transactional
 @Service("pageService")
public class PageServiceImpl extends SysBaseService<PageDO> implements IPageService {
    
    /**
     * 构造函数
     */
    public PageServiceImpl() {
        BaseTable = "page";
        BaseComment = "page";
        PrimaryKey = "page_id";
        NameKey = "page_name";
    }
    
    @Autowired
    private IPageDao dao;

    @Autowired
    private IAppPageDao appPageDao;

    @Autowired
    private ISectionDao sectionDao;
    
    @Autowired
    private ISectionMetricDao sectionMetricDao;
    
    @Autowired
    private ISecMetricConfigDao metricConfigDao;
    
    @Autowired
    private IMetricDao metricDao;
    
    @Override
    public ISysBaseDao getDao(){
        return dao;
    }
    
    /**
     * 保存业务
     * @return 
     */
    @Override
    public DataStore save(PageDO entity, Integer app_id, String section_json, String metric_json,String user_id) {
        //section_json: [{x: 0, y: 0, width: 12, height: 6}]
        if(QCommon.isNullOrEmpty(section_json))
            return ActionMsg.setError("栏目不能为空,请添加栏目");
        if(QCommon.isNullOrEmpty(metric_json))
            return ActionMsg.setError("栏目中指标不能为空,请添加指标");
        //如果编号为空,新增实体对象,否则更新实体对象
        Integer val = entity.getPage_id();
        Date now = DateTime.Now().getTime();
        int c = 0;
        //1保存页面
        if(val == null || val == 0) {
            entity.setCre_time( now );  //新增时间
			entity.setUpd_time( now );  //修改时间

            c = dao.insert(entity);

            //添加关联信息
            AppPageDO ap = new AppPageDO();
            ap.setApp_id(app_id);
            ap.setPage_id(entity.getPage_id());
            appPageDao.insert(ap);

        }else {
            entity.setUpd_time( now );  //修改时间

            c = dao.update(entity);
        }
        if(c == 0)
            return ActionMsg.setError("操作失败");
        //2保存页面栏目，栏目关联信息源，栏目关联指标
        List<Map<String, Object>> osectionLst = sectionDao.selectPageSection(entity.getPage_id());
        //List<Map<String, Object>> metricLst = sectionDao.selectPageMetric(entity.getPage_id());
        //List<Map<String, Object>> msgLst = sectionDao.selectPageSectionMsg(entity.getPage_id());
        List<Map<String, Object>> secmap = JsonUtil.fromJson(section_json, List.class, Map.class);
       
        List<Integer> ids = new ArrayList<>();
        Date dd = new Date();

        for(int i = 0; i < secmap.size(); i++){
            Integer sid = intval(secmap, i, "id", "0");
            Integer mid = intval(secmap, i, "mid", "-1");
            ids.add(sid);
            SectionDO sdo = new SectionDO();
            sdo.setSection_name("栏目"+(i+1));
            sdo.setSection_type(intval(secmap, i, "type", "1"));
            sdo.setUpd_time(dd);
            //移除配置里的id，
            Map<String, Object> sec = secmap.get(i);
            sec.remove("id");
            sdo.setSection_url(JsonUtil.toJson(sec));
            //2.1栏目保存
            saveSection(sdo, sid, entity.getPage_id(), intval(secmap, i, "no", "1"));
            //2.2栏目指标保存[多指标保存]
            //2.3指标配置保存
            String section_na = intval(secmap, i, "no", "1").toString();
            int section_id = sdo.getSection_id();
            List<Map<String, Object>> metric = JsonUtil.fromJson(metric_json, List.class, Map.class);
            //删除原来的配置信息
            List<Map<String, Object>> sec_id = sectionMetricDao.getId(section_id);
            if (sec_id.size()>0) {
                for (int k = 0; k < sec_id.size(); k++) {
    				int q= metricConfigDao.deleteBySectionId(sec_id.get(k).get("sec_metric_id").toString());
    			}
		}
            //sectionMetricDao.delete(String.valueOf(section_id));
            SecMetricConfigDo con = null;
            if (sec_id.size()>metric.size()&&sec_id.size()>0) {
            	//如果表中的数据多于新的数据则将多于的数据删除
            	deleteSecId(metric,sec_id);
			}
            for (int j = 0; j < metric.size(); j++) {
            	SectionMetricDO sectionMetricDO = new SectionMetricDO();
            	List<Map<String, Object>> das = new ArrayList<Map<String,Object>>();
				if (section_na.equals(metric.get(j).get("section_id"))) {
					List<Map<String, Object>> id =metricDao.getMetricId(metric.get(j).get("metric_id").toString());
					if (id.size()>0) {
						sectionMetricDO.setMetric_id(Integer.parseInt(id.get(0).get("metric_id").toString()));
						sectionMetricDO.setSection_id(section_id);
						//如果新数据多于原始数据  则多余的部分新增  其他的修改
						int a = addSectionInfo(sectionMetricDO,sec_id,j);
						//int a=sectionMetricDao.insert(sectionMetricDO);
	                    String[] paramKeys = new String[]{"", "metric_id","category_id","dimension","charts","numb","measure_name","time_dim","unit","display"};
	                   if (a>0) {
	                        for(int x = 1; x < paramKeys.length; x++){
	                            con =  new SecMetricConfigDo();
	                            con.setUser_id(0);
	                            con.setSec_metric_id(sectionMetricDO.getSec_metric_id());
	                            con.setParam_id(x);
	                            con.setParam_value(metric.get(j).get(paramKeys[x]).toString());
	                            con.setCre_time(now);
	                            metricConfigDao.insert(con);
	                        }
						}
					}
				}
        	}
        }
        //2.3删除不存在的栏目
        delOldSection(osectionLst, ids);

        ActionMsg.setValue(entity);
        return ActionMsg.setOk("操作成功");
    }
    
    //添加用户指标配置信息
    @Override
	public DataStore addUserConfig(String metric_json, String user_id) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> metric = JsonUtil.fromJson(metric_json, List.class, Map.class);
   	 String[] paramKeys = new String[]{"", "metric_id","category_id","dimension","charts","numb","measure_name","time_dim","unit","display"};
   	 SecMetricConfigDo con = null;
   	 Date now = DateTime.Now().getTime();
   	 int s = 0;
   	 if (metric.size()>0) {
   		for (int j = 0; j < metric.size(); j++) {
   		 	String sec_id = getSecMetricId(metric.get(j).get("section_id").toString(),metric.get(j).get("metric_id").toString()).get(0).get("sec_metric_id").toString();
   		 	int a =0; 
   		 	for(int x = 1; x < paramKeys.length; x++){
                 con =  new SecMetricConfigDo();
                 con.setUser_id(Integer.parseInt(user_id));
                 con.setSec_metric_id(Integer.parseInt(sec_id));
                 con.setParam_id(x);
                 con.setParam_value(metric.get(j).get(paramKeys[x]).toString());
                 con.setCre_time(now);
                a = metricConfigDao.insert(con);
             }
             if (a>0) {
					s++;
				}
    	 }
	}
   	int b =0;
   	 if (s == metric.size()) {
   		Map<String, Object> map = new HashMap<String, Object>();
   		map.put("user_id", user_id);
    	map.put("term_type_id", 0);
    	int t = metricConfigDao.updateUserActive(map);
       	UserActiveConfigDo usDO = new UserActiveConfigDo();
       	usDO.setUser_id(Integer.parseInt(user_id));
       	usDO.setIs_active(1);
       	usDO.setCre_time(now);
       	b = metricConfigDao.insertUAC_Version(usDO);
	}
   	if (b>0) {
		return ActionMsg.setOk("操作成功！");
	}else{
		 return ActionMsg.setOk("操作失败！");
	}
	}
    
    private List<Map<String, Object>>  getSecMetricId(String section_id,String metric_id){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("section_id", section_id);
    	map.put("src_metric_id", metric_id);
    	List<Map<String, Object>> list = sectionMetricDao.getSecId( map);
    	return list;
    }
    
    //删除多余数据
    private void  deleteSecId(List<Map<String, Object>> metric,List<Map<String, Object>> sec_id){
    	for (int i = metric.size(); i < sec_id.size(); i++) {
    		sectionMetricDao.deleteById(sec_id.get(i).get("sec_metric_id").toString());
    	}
    }
    //新增或修改数据
    private int  addSectionInfo(SectionMetricDO sectionMetricDO,List<Map<String, Object>> sec_id,int s){
    	int a = 0;
    	if (s<sec_id.size()) {
    		sectionMetricDO.setSec_metric_id(Integer.parseInt(sec_id.get(s).get("sec_metric_id").toString()));
    		a = sectionMetricDao.update(sectionMetricDO);
		}else{
			a = sectionMetricDao.insert(sectionMetricDO);
		}
    	return a;
    }

    private String val(List<Map<String, Object>> lst, int i ,String name, String defValue){
        Map<String, Object> m = lst.get(i);
        if(m == null)
            return "";
        Object o = m.get(name);
        return o == null ? defValue : o.toString();
    }
    private Integer intval(List<Map<String, Object>> lst, int i ,String name, String defValue){
        return Integer.valueOf(val(lst, i, name, defValue));
    }
    private Map<String, Object> findMetric(List<Map<String, Object>> lst, Integer section_id, Integer metric_id){
        for(int i = 0; i < lst.size(); i++){
            String sid = val(lst, i, "section_id", "");
            String mid = val(lst, i, "section_id", "");
            if(sid.equals(section_id.toString()) && mid.equals(metric_id.toString()))
                return lst.get(i);
        }
        return null;
    }

    private void saveSection(SectionDO sdo, Integer sid, Integer page_id, Integer no){
        if(sid > 0){
            sdo.setSection_id(sid);
            sectionDao.update(sdo);
        }else{
            sectionDao.insert(sdo);
            sectionDao.insertPageSection(sdo.getSection_id(), page_id, no);
        }
    }
    private void delOldSection(List<Map<String, Object>> olst, List<Integer> ids){
        for(int i = 0; i < olst.size(); i++){
            Boolean b = true;
            Integer osid = intval(olst, i, "section_id", "-1");
            for(int j = 0; j < ids.size(); j++){
                Integer nsid =ids.get(j);
                if(osid.equals(nsid)){
                    b = false;
                    break;
                }
            }
            if(b){
                sectionDao.delete(osid.toString());
            }

        }
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