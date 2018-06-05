package com.quick.portal.search.infomng;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.impl.HttpSolrClient;

public class InitSolrServer {

	
	private static InitSolrServer instance ;
	
	
  
	private static void instance() {
	}

	public static InitSolrServer getInstance() {
		if (instance == null) {
			synchronized (InitSolrServer.class) {
				InitSolrServer temp = instance;
				if (temp == null) {
					synchronized (InitSolrServer.class) {
						temp = new InitSolrServer();
					}
					instance = temp;
				}
			}
		}
		return instance;
	}
    private static HttpSolrClient docServer = null;
    private static HttpSolrClient metricServer = null;
    private static HttpSolrClient pMsgServer = null;
    private static HttpSolrClient uMsgServer = null;
    
    public static List<HttpSolrClient> getSolrServer() {
    	String [] data = new String[]{SolrInfoConstants.INDEX_PORTAL_DOC,SolrInfoConstants.INDEX_PORTAL_METRIC,SolrInfoConstants.INDEX_PORTAL_MSG,SolrInfoConstants.INDEX_USER_MSG};
    	List<HttpSolrClient> serList = new ArrayList<HttpSolrClient>();
    	HttpSolrClient service = null;
    	for(String dt:data){
			service = InitSolrServer(dt);
			serList.add(service);
    	}
    	return serList;
    }
    
    public static HttpSolrClient InitSolrServer(String data) {
    	HttpSolrClient  server = null;
    	switch (data) {
    		case SolrInfoConstants.INDEX_PORTAL_DOC: // 全表格[{}]
    			server = InitSolrServer.getServer(docServer,SolrInfoConstants.PORTAL_DOC_URL);
    			break;
    		case SolrInfoConstants.INDEX_PORTAL_METRIC:
    			server = InitSolrServer.getServer(metricServer,SolrInfoConstants.PORTAL_METRIC_URL);
    			break;
    		case SolrInfoConstants.INDEX_PORTAL_MSG: 
    			server = InitSolrServer.getServer(pMsgServer,SolrInfoConstants.PORTAL_MSG_URL);
    			break;
    		case SolrInfoConstants.INDEX_USER_MSG: 
    			server = InitSolrServer.getServer(uMsgServer,SolrInfoConstants.USER_MSG_URL);
    			break;
    		}
    	return server;
    }
    
    public static synchronized HttpSolrClient getServer(HttpSolrClient server, String url){
    	 // 创建 server
	   	 if(server == null){
	   		 server = new HttpSolrClient.Builder(url)
	           .withConnectionTimeout(SolrInfoConstants.CONNECTION_TIMEOUT)
	           .withSocketTimeout(SolrInfoConstants.SOCKET_TIMEOUT)
	           .build();
	   	 }
	     return server;
    }
    
    
    /*
     * 创建 server
     */
    public static synchronized HttpSolrClient initServer(String url) {
    	 if(docServer == null){
    		 docServer = new HttpSolrClient.Builder(url)
             .withConnectionTimeout(SolrInfoConstants.CONNECTION_TIMEOUT)
             .withSocketTimeout(SolrInfoConstants.SOCKET_TIMEOUT)
             .build();
    	 }
    	 return docServer;
    }

}
