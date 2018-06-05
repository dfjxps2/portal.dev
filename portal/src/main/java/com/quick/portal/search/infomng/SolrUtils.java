package com.quick.portal.search.infomng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import com.quick.core.base.model.PageBounds;
import com.quick.core.util.common.DateTime;

/**
 * Solr7 操作 solr版本是7.3.0 
 * solr-solrj 使用的是5.0.0
 */
public class SolrUtils {
	
	private HttpSolrClient server = null;

	

	/**
	 * 添加文档 用solrJ创建索引
	 * @throws Exception 
	 */
	public static void addSolrInfo(String id, String content, String type,String title) {
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", id);
		doc.addField("content",content);
		doc.addField("_text_", content);
		doc.addField("create_time", DateTime.Now().getTime());
		doc.addField("portal_doc_class", type);
		doc.addField("portal_doc_title", title);
		HttpSolrClient server = null;
		try {
		    server = InitSolrServer.initServer(SolrInfoConstants.PORTAL_DOC_URL);
			UpdateResponse response = server.add(doc);
			// 提交
			server.commit();
			System.out.println("########## Query Time :" + response.getQTime());
			System.out.println("########## Elapsed Time :"
					+ response.getElapsedTime());
			System.out.println("########## Status :" + response.getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	

	/**
	 * 单个id 的删除索引
	 */
	public static void deleteSolrInfo(String id) throws Exception {
		// [1]获取连接
		HttpSolrClient server = InitSolrServer.initServer(SolrInfoConstants.PORTAL_DOC_URL);
		// [2]通过id删除
		server.deleteById(id);
		// [3]提交
		server.commit();
	}
	


	/**
	 * 多个id 的list集合 删除索引
	 */
	public static void deleteBatchSolrList(ArrayList<String> ids) throws Exception {
		// [1]获取连接
		HttpSolrClient server = InitSolrServer.initServer(SolrInfoConstants.PORTAL_DOC_URL);
		// [2]通过id删除
		for (String id : ids) {
			server.deleteById(id);
		}
		// [3]提交
		server.commit();
	}
	
	/*
	 * 查询SOLR数据
	 */
	public static List searchInfoDataByCondition(Map<String, Object> m,
			PageBounds page, List<Map<String, Object>> retList, String type) {
		List restList = new ArrayList();
		if (SolrInfoConstants.UNREAD_OBJ_TYPE.equals(type)) {
			int fromIndex = page.getStartRow() == 1 ? 0 : page.getStartRow() - 1;
			int toIndex = page.getEndRow();
			SolrDocumentList docInfoList = (SolrDocumentList) getUnReadData(m,retList);
			List dataList = getUnReadInfoData(docInfoList);
			restList = getDataList(dataList, fromIndex,toIndex);
		}else if (SolrInfoConstants.READ_OBJ_TYPE.equals(type)) {
			 int fromIndex = page.getStartRow() == 1 ? 0 : page.getStartRow() - 1;
			 int toIndex = page.getEndRow();
			 restList = getReadInfoData(m,retList,fromIndex,toIndex);
		}else{
			SolrQuery query = getAllSolrQuery(m, page,type);
			SolrDocumentList docList = getSolrInfoDataByTitle(query);
			restList = getAllInfoData(docList,retList);
		}
	
		return restList;
	}
	


	/*
	 * 未查阅数据
	 *  去掉已阅数据
	 */
	public static List getUnReadData(Map<String, Object> m,List<Map<String, Object>> retList) {
		SolrQuery query= getSolrQueryByCond(m);
		SolrDocumentList docList = getSolrInfoDataByTitle(query);
		String id = null;
		String cid = null;
		System.out.println("####### 未查阅数据总共 ： " + docList.getNumFound() + "条记录");
		for (Map<String, Object> data : retList) {
			cid = data.get("MSG_CONTENT").toString();
			for (SolrDocument doc : docList) {
				System.out.println("#######未查阅数据 id : " + doc.get("id")
						+ "  title : " + doc.get("portal_doc_title")
						+ "  portal_doc_class : " + doc.get("portal_doc_class"));
				id = doc.get("id") == null ? "" : doc.get("id").toString();
				if (null != cid && cid.equals(id)) {
					docList.remove(doc);
					break;
				}
			}
		}
		return docList;
	}
	
	public static List getUnReadInfoData(SolrDocumentList docList){
		List restList = new ArrayList();
		List<Map<String, Object>> dataList = new ArrayList<>();
		Map<String, Object> dataMap = null;
		String id = null;
		System.out.println("####### 总共 ： " + docList.size() + "条记录");
		for (SolrDocument doc : docList) {
			System.out.println("####### id : " + doc.get("id") + "  title : "
					+ doc.get("portal_doc_title") + "  portal_doc_class : "
					+ doc.get("portal_doc_class") );
			id = doc.get("id") == null ? "" : doc.get("id").toString();
			dataMap = new HashMap();
			dataMap.put("status", SolrInfoConstants.UNREAD_STATUS);
			dataMap.put("id", id);
			dataMap.put("title",
					doc.get("portal_doc_title") == null ? "" : doc.get("portal_doc_title"));
			dataMap.put("portal_doc_class",
					doc.get("portal_doc_class") == null ? "" : doc.get("portal_doc_class"));
			dataList.add(dataMap);
		}
		Integer count = dataList.size();
		restList.add(count);
		restList.add(dataList);
		return restList;
	}
	
	/*
	 * 全部
	 * 
	 */
	public static SolrQuery getAllSolrQuery(Map<String, Object> m,PageBounds page,String type){
		String queryStr = m.get(SolrInfoConstants.INDEX_KEYWORD).toString();
		SolrQuery query = new SolrQuery(queryStr);
		query.setStart(page.getStartRow() == 1 ? 0 : page.getStartRow() - 1);
		query.setRows(page.getPageSize());
		if (SolrInfoConstants.INDEX_OBJ_TYPE.equals(type)) {
			query.addFilterQuery("portal_doc_class:"+SolrInfoConstants.INDEX_OBJ_TYPE+"");
		} else if (SolrInfoConstants.MSG_OBJ_TYPE.equals(type)) {
			query.addFilterQuery("portal_doc_class:"+SolrInfoConstants.MSG_OBJ_TYPE+"");
		} else if (SolrInfoConstants.APP_OBJ_TYPE.equals(type)) {
			query.addFilterQuery("portal_doc_class:"+SolrInfoConstants.APP_OBJ_TYPE+"");
		}else if (SolrInfoConstants.DATA_OBJ_TYPE.equals(type)) {
			query.addFilterQuery("portal_doc_class:"+SolrInfoConstants.DATA_OBJ_TYPE+"");
			query.setStart(SolrInfoConstants.PAGE_START);
			query.setRows(SolrInfoConstants.PAGE_ROWS);
		}  
		// query.addFilterQuery("description:演员");
		// 排序 如果按照blogId 排序，那么将blogId desc(or asc) 改成 id desc(or asc)
		query.addSort("create_time", ORDER.asc);
		return query;
	}
	
	/*
	 * 未阅、已阅
	 * 
	 */
	public static SolrQuery getSolrQueryByCond(Map<String, Object> m){
		String queryStr = m.get(SolrInfoConstants.INDEX_KEYWORD).toString();
		SolrQuery query = new SolrQuery(queryStr);
		query.setStart(SolrInfoConstants.PAGE_START);
		query.setRows(SolrInfoConstants.PAGE_ROWS);
		return query;
	}
	
	/*
	 * 连接SOLR服务
	 * 
	 */
	public static SolrDocumentList getSolrInfoDataByTitle(SolrQuery query){
		QueryResponse response = null;
		try {
			HttpSolrClient server = InitSolrServer.initServer(SolrInfoConstants.PORTAL_DOC_URL);
			response = server.query(query);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SolrDocumentList docList = response.getResults();
		return docList;
	}
	
	

	/*
	 * 已查阅数据
	 * 与查阅数据比对
	 */
	public static List getReadInfoData(Map<String, Object> m,
			List<Map<String, Object>> retList,int fromIndex, int toIndex) {
		List<Map<String, Object>> dataList = new ArrayList<>();
		SolrQuery query= getSolrQueryByCond(m);	
		List<SolrDocument> docList = getSolrInfoDataByTitle(query);
		List restList = getInfoData(docList, retList);
		List dtList = getDataList(restList, fromIndex,toIndex);
		return dtList;		
	}
	
	
	public static List getInfoData(List<SolrDocument> docList,
			List<Map<String, Object>> retList) {
		List restList = new ArrayList();
		Map<String, Object> dataMap = null;
		List<Map<String, Object>> dataList = new ArrayList<>();
		System.out.println("####### 已查阅表关系数据总共 ： " + retList.size() + "条记录");
		String cid = null;
		String id = null;
		for (Map<String, Object> data : retList) {
			cid = data.get("MSG_CONTENT").toString();
			dataMap = new HashMap();
			for (SolrDocument doc : docList) {
				System.out.println("####### 查询数据id : " + doc.get("id")
						+ "  title : " + doc.get("portal_doc_title")
						+ "  portal_doc_class : " + doc.get("portal_doc_class"));
				id = doc.get("id") == null ? "" : doc.get("id").toString();
				if (null != cid && cid.equals(id)) {
					// status:1表示已阅
					dataMap.put("status", SolrInfoConstants.READ_STATUS);
					dataMap.put("id", id);
					dataMap.put("title",
							doc.get("portal_doc_title") == null ? "" : doc.get("portal_doc_title"));
					dataMap.put(
							"portal_doc_class",
							doc.get("portal_doc_class") == null ? "" : doc
									.get("portal_doc_class"));
					
					dataList.add(dataMap);
					break;
				}
			}
		}
		Integer count = dataList.size();
		restList.add(count);
		restList.add(dataList);
		return restList;
	}
	
	
	public static List getDataList(List dataList, int fromIndex,int toIndex) {
		List retList = new ArrayList();
		Integer count = (int)dataList.get(0);
		retList.add(count);
		List dtList  = (List)dataList.get(1);
		if(dtList.size() > toIndex){
			retList.add(dtList.subList(fromIndex, toIndex));
		}else{
			retList.add(dtList.subList(fromIndex, dtList.size()));
		}
		return retList;
	}
	
	
	
	public static List getAllInfoData(SolrDocumentList docList,
			List<Map<String, Object>> retList) {
		List restList = new ArrayList();
		Map<String, Object> dataMap = null;
		List<Map<String, Object>> dataList = new ArrayList<>();
		System.out.println("####### 总共 ： " + docList.getNumFound() + "条记录");
		int count = (int) docList.getNumFound();
		String cid = null;
		String id = null;
		for (SolrDocument doc : docList) {
			System.out.println("####### id : " + doc.get("id")
					+ "  title : " + doc.get("portal_doc_title")
					+ "  portal_doc_class : " + doc.get("portal_doc_class"));
			id = doc.get("id") == null ? "" : doc.get("id").toString();
			dataMap = new HashMap();
			dataMap.put("status",  SolrInfoConstants.UNREAD_STATUS);
			for (Map<String, Object> data : retList) {
				cid = data.get("MSG_CONTENT").toString();
				if (null != cid && cid.equals(id)) {
					// status:1表示已阅
					dataMap.put("status", SolrInfoConstants.READ_STATUS);
					break;
				}
			}
			dataMap.put("id", id);
			dataMap.put("title",
					doc.get("portal_doc_title") == null ? "" : doc.get("portal_doc_title"));
			dataMap.put("portal_doc_class", doc.get("portal_doc_class") == null ? ""
					: doc.get("portal_doc_class"));
			
			dataList.add(dataMap);
		}
		restList.add(count);
		restList.add(dataList);
		return restList;
	}
	
	/*
	 * 通过关键字查询资料数据
	 */
	public static List<String> getDataByKeyword(String keyword){
		Map<String, Object> m = new HashMap();
		m.put(SolrInfoConstants.INDEX_KEYWORD, keyword);
		PageBounds page = new PageBounds();
		List<String> retList = searchDataByCondition(m,page,SolrInfoConstants.DATA_OBJ_TYPE);
		return retList;
	}
	
	public static List<String>  searchDataByCondition(Map<String, Object> m,
			PageBounds page, String type) {
		SolrQuery query = getAllSolrQuery(m, page,type);
		SolrDocumentList docList = getSolrInfoDataByTitle(query);
		List<String> list = new ArrayList<String>();
		String id = null;
		for (SolrDocument doc : docList) {
			id = doc.get("id") == null ? "" : doc.get("id").toString();
			list.add(id);
		}
		return list;
	}

	public static void main(String[] args) throws Exception {
		SolrUtils st = new SolrUtils();
		// st.testQueryCondition();
/*		Map<String, Object> m = new HashMap();
		m.put("title", "认证");
		PageBounds page = null;
		// List<Map<String, Object>> retList = searchInfoDataByCondition(m,
		// page);
		// SolrUtils.addDoc("qqq","q","4");
		String id ="C:/Users/cxh/Desktop/test/测试文档.docx";
//		st.deleteSolrInfo(id);
		String content = "测试文档";
		String type="1";
//		int i =0;
		String path ="C:/Users/cxh/Desktop/test/";
		for(int i=30;i<200;i++){
			id = path.concat(content).concat(i+"").concat("docx");
			st.addSolrInfo( id,  content, type,content);
		}
		
		ArrayList<String> ids = new ArrayList();
		SolrQuery sq = getSolrQueryNoCond();
		SolrDocumentList docList = getSolrInfoDataByTitle(sq);
		if(docList.size()>0){
			for(SolrDocument sot:docList){
				ids.add(sot.get("id").toString());
				
			}
		}
		System.out.println("---="+ids.size());
		
		deleteBatchSolrList(ids);*/
		String keyword = "测试";
		
		List<String> retList = getDataByKeyword(keyword);
		 for (String key : retList) {  
			   System.out.println("key= "+ key );  
			  } 
		
	/*	 Iterator<Map.Entry<String, Object>> it = (Iterator<Entry<String, Object>>) getDataByKeyword(keyword);;  
		  while (it.hasNext()) {  
		   Map.Entry<String, Object> entry = it.next();  
		   System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());  
		  }  */
/*		List<Integer> itList = new ArrayList();
		int j =10;
		for(int i =0;i<10;i++){
			itList.add(i);
		}
		List ret = itList.subList(0, 8);
		List ret1 = itList.subList(1, 10);
		*/
	}
	
	
	public static SolrQuery getSolrQueryNoCond(){
		SolrQuery query = new SolrQuery();  
	    query.setQuery("*:*"); 
	    query.setStart(SolrInfoConstants.PAGE_START);
		query.setRows(SolrInfoConstants.PAGE_ROWS);
		return query;
	}
}
