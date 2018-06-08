package com.quick.portal.search.infomng;

/**
 * 
 * 修改备注：
 * 
 * @version
 *
 */
public class SolrInfoConstants {

	public static final String INDEX_PORTAL_DOC = "PORTAL_DOC";
	public static final String INDEX_USER_MSG = "USER_MSG";

	public static final String INDEX_PORTAL_MSG = "PORTAL_MSG";

	public static final String INDEX_PORTAL_METRIC = "PORTAL_METRIC";

	public static final String PORTAL_DOC_URL = "http://10.1.3.193:8983/solr/portal_doc";

	public static final String PORTAL_METRIC_URL = "http://10.1.3.193:8983/solr/portal_metric";

	public static final String PORTAL_MSG_URL = "http://10.1.3.193:8983/solr/portal_msg";

	public static final String DATA_MSG_URL = "http://10.1.3.193:8983/solr/portal_data";
	
	public static final String USER_MSG_URL ="http://10.1.3.193:8983/solr/user_msg";
	
	public static final String INDEX_KEYWORD = "keyword";
	
//	public static final String INDEX_ID = "id";
	
	public static final int CONNECTION_TIMEOUT = 10000;
	
	public static final int SOCKET_TIMEOUT = 60000;
	
	
	// 9：全部;4：未阅;5：已阅;1：指标;2：消息;3：应用;6：资料
	public static final String ALL_OBJ_TYPE = "9";
	public static final String UNREAD_OBJ_TYPE = "4";
	public static final String READ_OBJ_TYPE = "5";
	public static final String INDEX_OBJ_TYPE = "1";
	public static final String MSG_OBJ_TYPE = "2";
	public static final String APP_OBJ_TYPE = "3";
	public static final String DATA_OBJ_TYPE = "6";
	
	// status:1表示已阅;0表示未阅
	public static final String READ_STATUS = "1";
	public static final String UNREAD_STATUS = "0";
	
	public static final int PAGE_START = 0;
	public static final int PAGE_ROWS = 2000;



}
