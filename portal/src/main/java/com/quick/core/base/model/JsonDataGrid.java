/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quick.core.base.model;

import com.quick.core.util.common.JsonUtil;
import com.quick.core.util.common.QCommon;
import com.quick.core.util.common.ReflectUtil;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.TreeMap;

import javax.swing.JApplet;

/**
 * Easyui数据模型
 * @author Administrator
 */
public class JsonDataGrid {

    private Integer total;
    private List<Map<String, Object>> rows;
    
    public JsonDataGrid(Integer total, List<Map<String, Object>> rows){
        this.total = total;
        this.rows = rows;
    }

    public Map<String, Object> toObj(){
        Map<String, Object> ret = new HashMap<>();
        ret.put("total", total);
        ret.put("rows", rows);
        return ret;
    }
    
    public String toJson(){
        return "{\"total\":" + total.toString() + ",\"rows\":" + JsonUtil.toJson(rows) + "}";
    }
    public String toDataJson(){
        return JsonUtil.toJson(rows);
    }
    public String toObjJson(){
        if(rows == null || rows.size() == 0)
            return "{}";
        return JsonUtil.toJson(rows.get(0));
    }
    public String toDataJson(String dataFormatter){
    	DateFormat format1 = new SimpleDateFormat(dataFormatter);
    	for(int i=0;i<rows.size();i++){
    		Map<String, Object> map=rows.get(i);
    			for (Map.Entry<String, Object> entry : map.entrySet()) {  
    				
    				  if(entry.getValue().getClass().getName().equals("java.sql.Timestamp")){
    					  entry.setValue(format1.format((Date)entry.getValue()));
    				  }
			}
    	}
        return JsonUtil.toJson(rows);
    }
    public String toJson(String dataFormatter){
    	DateFormat format1 = new SimpleDateFormat(dataFormatter);
    	for(int i=0;i<rows.size();i++){
    		Map<String, Object> map=rows.get(i);
    			for (Map.Entry<String, Object> entry : map.entrySet()) {  
    				
    				  if(entry.getValue().getClass().getName().equals("java.sql.Timestamp")){
    					  entry.setValue(format1.format((Date)entry.getValue()));
    				  }
			}
    	}
        return "{\"total\":" + total.toString() + ",\"rows\":" + JsonUtil.toJson(rows) + "}";
    }
    public String toComboJson(String show){
        StringBuilder sb = new StringBuilder();
        if(rows.size() == 0){
            return "[]";
        }
        String[] cols = QCommon.split(show, ",");
        Iterator<String> iterator = rows.get(0).keySet().iterator();
        String col1 = cols.length < 2 ? iterator.next() : cols[0];
        String col2 = cols.length < 2 ?  col1 : cols[1];
        int i = 0;
        sb.append("[");
        for (Map<String, Object> row : rows) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append("{\"value\":\"").append(row.get(col1)).append("\",\"text\":\"").append(row.get(col2)).append("\"}");
            i++;
        }
        sb.append("]");
        return sb.toString();
    }
    public String toTreeJson(String fieldTree){
        String[] fieldArray = QCommon.split(fieldTree, ","); //树型节点
        String rootVal = "";
        if (fieldArray == null || fieldArray.length < 4) //未设置树的id,text,parentid则返回空
        {
        	if(fieldArray.length < 3){
        		return "[{\"id\":0,\"text\":\"未设置节点\",\"children\":[]}]";
        	}else {
        		rootVal = "";
        	}
        }else{
        	rootVal = fieldArray[3];
        }
        for(Map<String, Object> map : rows){
        	map.put("id", map.get(fieldArray[0]));
            map.put("text", map.get(fieldArray[1]));
            map.put("parentId", map.get(fieldArray[2]));
            if(fieldArray.length>4)
            	 map.put("iconCls", fieldArray[4]);
            	
        }
        List<Map<String, Object>> tree = getTreeList(fieldArray[0],fieldArray[1],fieldArray[2],rootVal, rows);
        return JsonUtil.serialize(tree);
    }
    public String toTreeJsonByRoot(String fieldTree){
        String[] fieldArray = QCommon.split(fieldTree, ","); //树型节点
        String rootVal = "";
        if (fieldArray == null || fieldArray.length < 4) //未设置树的id,text,parentid则返回空
        {
        	if(fieldArray.length < 3){
        		return "[{\"id\":0,\"text\":\"未设置节点\",\"children\":[]}]";
        	}else {
        		rootVal = "";
        	}
        }else{
        	rootVal = fieldArray[3];
        }
        for(Map<String, Object> map : rows){
        	map.put("id", map.get(fieldArray[0]));
            map.put("text", map.get(fieldArray[1]));
            map.put("parentId", map.get(fieldArray[2]));
            if(fieldArray.length>4)
            	 map.put("iconCls", fieldArray[4]);
            	
        }
        List<Map<String, Object>> tree = getTreeListByRoot(fieldArray[0],fieldArray[1],fieldArray[2],rootVal, rows);
        return JsonUtil.serialize(tree);
    }
    /**
     * 输出_dataStore JS文件
     * @param show
     * @param storeName
     * @return 
     */
    public String writeDataStoreJs(String show, String storeName){
        StringBuilder sb = new StringBuilder(total * 50);
        sb.append("_dataStore[\"").append(storeName).append("\"]=[");
        if (rows == null || rows.size() == 0) {
            sb.append("];");
            return sb.toString();
        }
        String[] cols = QCommon.split(show, ",");
        Iterator<String> iterator = rows.get(0).keySet().iterator();
        String col0 = iterator.next();
        String col1 = cols.length < 1 ? col0 : cols[0];
        String col2 = cols.length < 2 ?  col1 : cols[1];
        int i = 0;
        for (Map<String, Object> row : rows) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append("{\"text\":\"").append(row.get(col1)).append("\",\"value\":\"").append(row.get(col2));
            if(cols.length>2)
                for(int j=2;j<cols.length;j++)
                {
                	 sb.append("\",\""+cols[j]+"\":\"").append(row.get(cols[j]));
                }
            sb.append("\"}");
            i++;
        }
        sb.append("];");
        return sb.toString();
    }
    
    public static <T> List<Map<String, Object>> getTreeList(String idField, String textField, String parentField, String rootId, List<Map<String, Object>> newsources) {
        String childField = "children";
        //使用Map来消除递归函数
        List<Map<String, Object>> rootList = new ArrayList<>();
        List<String> idList = new ArrayList();
        for(Map<String,Object> node:newsources){
        	if(node.get(idField)!=null)
        		idList.add(node.get(idField).toString());
        	else
        		System.out.print("************"+node.get("DICID")+"*********************");
        }
        //生成Map树(无分支和有分支的)
        Map<String, Map<String, Object>> stack = new TreeMap<>();
        
        for(Map<String, Object> obj : newsources) {
            //----获取子节点
            String pid = obj.get(idField).toString();
            List<Map<String, Object>> sublist = new ArrayList<>();
            for(Map<String, Object> subObj : newsources) {
                Object parentVal = subObj.get(parentField);
                if(parentVal != null && parentVal.equals(pid)) {
                    sublist.add(subObj);
                }
            }
            obj.put(childField, sublist);
            //----获取子节点End
            stack.put(obj.get(idField).toString(), obj);
        }
        for(String id:idList) {
        	Map<String, Object> self = stack.get(id);
        	Object parentObj = self.get(parentField);
     	    String parentId = parentObj == null ? "" : parentObj.toString();
            Map<String, Object> parent = stack.get(parentId);
            if(parent != null) {
            	Object subsObj = parent.get(childField);
                List<Map<String, Object>> subs = subsObj == null ? new ArrayList<Map<String, Object>>() : (List<Map<String, Object>>)subsObj;
                Boolean b = false;
                for(Map<String, Object> chr : subs) {
                    if(chr.get(idField).equals(id)) {
                        b = true;
                        break;
                    }
                }
                //刷新
                if(!b) {
                    subs.add(self);
                    //parent.put(childField, subs);
                }
            }
        }
        
        //查询指定节点,否则表示查询根节点
        if(!QCommon.isNullOrEmpty(rootId)) {
            if(stack.containsKey(rootId)) {
                rootList.add(stack.get(rootId));
            }else {
                for(String id: idList) {
                 Map<String, Object> node = stack.get(id);
                    if(rootId.equals(node.get(parentField))) {
                        rootList.add(stack.get(node.get(idField)));
                    }
                }
            }
            if(rootList == null)
                rootList = new ArrayList<>();
            return rootList;
        } else {
            for(Map<String, Object> node : stack.values()) {
            	Object parentObj = node.get(parentField);
                String parentVal = parentObj == null ? "" : parentObj.toString();
                if(QCommon.isNullOrEmpty(parentVal)) {
                    rootList.add(stack.get(node.get(idField)));
                }
            }
            return rootList;
        }
    }
    public static <T> List<Map<String, Object>> getTreeListByRoot(String idField, String textField, String parentField, String rootId, List<Map<String, Object>> newsources) {
        String childField = "children";
        //使用Map来消除递归函数
        List<Map<String, Object>> rootList = new ArrayList<>();
        List<String> idList = new ArrayList();
        for(Map<String,Object> node:newsources){
        	if(node.get(idField)!=null)
        		idList.add(node.get(idField).toString());
        	else
        		System.out.print("************"+node.get("DICID")+"*********************");
        }
        //生成Map树(无分支和有分支的)
        Map<String, Map<String, Object>> stack = new TreeMap<>();
        
        for(Map<String, Object> obj : newsources) {
            //----获取子节点
            String pid = obj.get(idField).toString();
            List<Map<String, Object>> sublist = new ArrayList<>();
            for(Map<String, Object> subObj : newsources) {
                Object parentVal = subObj.get(parentField);
                if(parentVal != null && parentVal.equals(pid)) {
                    sublist.add(subObj);
                }
            }
            obj.put(childField, sublist);
            //----获取子节点End
            stack.put(obj.get(idField).toString(), obj);
        }
        for(String id:idList) {
        	Map<String, Object> self = stack.get(id);
        	Object parentObj = self.get(parentField);
     	    String parentId = parentObj == null ? "" : parentObj.toString();
            Map<String, Object> parent = stack.get(parentId);
            if(parent != null) {
            	Object subsObj = parent.get(childField);
                List<Map<String, Object>> subs = subsObj == null ? new ArrayList<Map<String, Object>>() : (List<Map<String, Object>>)subsObj;
                Boolean b = false;
                for(Map<String, Object> chr : subs) {
                    if(chr.get(idField).equals(id)) {
                        b = true;
                        break;
                    }
                }
                //刷新
                if(!b) {
                    subs.add(self);
                    //parent.put(childField, subs);
                }
            }
        }
        
        //查询指定节点,否则表示查询根节点
        if(!QCommon.isNullOrEmpty(rootId)) {
            if(stack.containsKey(rootId)) {
                rootList.add(stack.get(rootId));
            }else {
                for(String id: idList) {
                 Map<String, Object> node = stack.get(id);
                    if(rootId.equals(node.get(parentField))) {
                        rootList.add(stack.get(node.get(idField)));
                    }
                }
            }
            if(rootList == null)
                rootList = new ArrayList<>();
            return rootList;
        } else {
            for(Map<String, Object> node : stack.values()) {
            	Object parentObj = node.get(parentField);
                String parentVal = parentObj == null ? "" : parentObj.toString();
                if(QCommon.isNullOrEmpty(parentVal)) {
                    rootList.add(stack.get(node.get(idField)));
                }
            }
            return rootList;
        }
    }
    
    public static <T> List<Map<String, Object>> convertToTree(String idField, String textField, String parentField, String rootId, List<T> sources) {
        List<Map<String, Object>> newsources = new ArrayList<>();
        for(T node : sources) {
            Map<String, Object> obj = toMap(node, idField, textField, parentField);
            newsources.add(obj);
        }
        return getTreeList(idField, textField, parentField, rootId, newsources);
    }
   
    
    private static Map<String, Object> toMap(Object obj, String idField, String textField, String parentField) {
        Map<String, Object> map = ReflectUtil.toMap(obj);
        map.put("id", map.get(idField));
        map.put("text", map.get(textField));
        map.put("parentId", map.get(parentField));
        return map;
    }
}