/**
 * <h3>标题 : Quick通用系统框架 </h3>
 * <h3>描述 : 通用服务类</h3>
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
package com.quick.core.base;

import com.quick.core.base.model.DataStore;
import com.quick.core.base.model.PageBounds;
import com.quick.core.util.common.DateTime;
import com.quick.core.util.common.QCommon;
import com.quick.core.util.common.ReflectUtil;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import javax.annotation.Resource;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
public abstract class SysBaseService<T> implements ISysBaseService<T> {
    
    public String BaseTable = "";
    public String BaseView = "";
    public String BaseConnect = "";
    public String BaseComment = "";
    public String PrimaryKey = "";
    public String NameKey = "";
    public DataStore ActionMsg;
    
    private ISysBaseDao<T> baseDao;
    private Class<T> entityClass;
    
    public SysBaseService(){
        entityClass =(Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        ActionMsg = new DataStore();
    }
    
    @Resource(name = "sqlDao")
    private SysSqlDao sqlDao;

    public void setDao(ISysBaseDao<T> dao){
        this.baseDao = dao;
    }
    
    @Override
    public ISysBaseDao<T> getDao(){
        return baseDao;
    }
    
    public Map<String, Object> getQueryMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("_sql_select", "*");
        map.put("_sql_table", getBaseTable());
        map.put("_sql_where", "");
        map.put("_sql_order", "");
        
        return map;
    }

    public String getNextNo() {
        return QCommon.getUUID();
    }
    
    @Transactional
    @Override
    public DataStore save(T entity) {
        //如果编号为空,新增实体对象,否则更新实体对象
        String key = getPrimaryKey();
        String keyVal = ReflectUtil.getValue(entity, key, entityClass).toString();
        int c = 0;
        if(QCommon.isNullOrEmpty(keyVal)) {
            //如果有新增时间，维护对应字段
            ReflectUtil.trySetValue(entity, "cre_time", DateTime.Now().getTime(), entityClass);
            //如果有修改时间，维护对应字段
            ReflectUtil.trySetValue(entity, "upd_time", DateTime.Now().getTime(), entityClass);
            c = getDao().insert(entity);
        }else {
            //如果有修改时间，维护对应字段
            ReflectUtil.trySetValue(entity, "upd_time", DateTime.Now().getTime(), entityClass);
            c = getDao().update(entity);
        }

        if(c == 0) {
            int error = Integer.valueOf(
                    ReflectUtil.getValue(entity, "error_no", entityClass).toString());
            if (error == 0)
                return ActionMsg.setError("操作失败");
        }

        ActionMsg.setValue(entity);
        return ActionMsg.setOk("操作成功");
    }
    
    /**
     * 删除实体类
     * @param sysid
     * @return 
     */
    @Transactional
    @Override
    public DataStore delete(String sysid) {
        getDao().delete(sysid);
        return ActionMsg.setOk("操作成功");
    }
    
    //@Override
    public T selectObj(String sysid){
        Map<String, Object> dic = selectMap(sysid);
        if(dic==null)
        	return null;
        return (T)ReflectUtil.toObjectByClass(dic, entityClass);
    }
    
    //@Override
    public T selectObj(Map<String,Object> map){
        Map<String, Object> dic = selectMap(map);
        if(dic==null)
        	return null;
        return (T)ReflectUtil.toObjectByClass(dic, entityClass);
    }
    
    public Map<String, Object> selectMap(String sysid){
        Map<String, Object> m = getQueryMap();
        if(QCommon.isNullOrEmpty(sysid))
            sysid = "0";
        m.put(getPrimaryKey(), sysid);
        List<Map<String, Object>> list = getDao().select(m);
        if(list == null || list.size() == 0)
            return null;
        Map<String, Object> dic = list.get(0);
        return dic;
    }
    
    public Map<String, Object> selectMap(Map<String, Object> map){
    	List<Map<String, Object>> list = getDao().select(map);
        if(list == null || list.size() == 0)
            return null;
        Map<String, Object> dic = list.get(0);
    	return dic;
    }
    
    @Override
    public List<Map<String, Object>> select(Map<String, Object> m, PageBounds page) {
        return getDao().select(m, page);
    }
    
    @Override
    public List<Map<String, Object>> select(Map<String, Object> m) {
        return getDao().select(m);
    }
    
    @Override
    public List<Map<String, Object>> selectAll() {
        return getDao().selectAll();
    }
    
    /**
     * 直接查询DaoMapper.xml内指定的sql
     * @param mapper
     * @param m
     * @return
     */
    public List<Map<String, Object>> selectMapper(String mapper, Map<String, Object> m){
    	String nameSpace = this.getClass().getPackage().getName();
    	return sqlDao.selectMapper(nameSpace, mapper, m);
    }
    
    @Override
    public String getBaseTable(){
        return BaseTable;
    }
    @Override
    public String getBaseView(){
        if(QCommon.isNullOrEmpty(BaseView))
            return BaseTable;
        return BaseView;
    }
    @Override
    public String getBaseComment(){
        return BaseComment;
    }
    @Override
    public String getPrimaryKey(){
        return PrimaryKey;
    }
    
    public int count(Map<String, Object> m){
    	return getDao().count(m);
    }

    public boolean exist(String key, Object value, Object id){
        Map<String, Object> parm = new HashMap<>();
        parm.put(key, value);
        Map<String, Object> m = selectMap(parm);
        if(m == null)
            return false;
        if(id != null){
            Object mid = m.get(getPrimaryKey());
            if(mid.equals(id))
                return false;
        }
        return true;
    }
}
