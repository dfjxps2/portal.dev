/**
 * <h3>标题 : Quick通用系统框架 </h3>
 * <h3>描述 : 应用中的相关配置信息都放在此</h3>
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
import com.quick.core.base.model.JsonDataGrid;
import com.quick.core.base.model.PageBounds;
import com.quick.core.base.spring.UtilDatePropertyEditor;
import com.quick.core.util.common.JsonUtil;
import com.quick.core.util.common.QCommon;
import com.quick.core.util.common.QRequest;
import com.quick.portal.sysUser.ISysUserService;
import com.quick.portal.web.login.WebLoginUitls;
import com.quick.portal.web.login.WebLoginUser;

import org.pac4j.core.profile.CommonProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 通用控制层抽象类
 * 
 * @author Administrator
 */
public abstract class SysBaseController<T> {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	//private final static String LOGIN_URL = "/";

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	public Map<String, Object> urlMap;

	public WebLoginUser loginer;

	public DataStore ActionMsg;

	protected UrlPathHelper urlPathHelper; // 路径助手

	@Resource(name = "sysUserService")
	private ISysUserService loginerService;

	// <editor-fold defaultstate="collapsed" desc="自定义方法">

	public SysBaseController(){
		ActionMsg = new DataStore();
		urlMap=new HashMap<String,Object>();
	}

	/**
	 * 判断用户是否已登录(集成CAS)
	 * @param request
	 * @param response
     * @return
     */
	public Boolean isLogin(HttpServletRequest request,
			HttpServletResponse response) {
		Boolean b = true;
		getCurrentLoginUser(request,response);
/*		if (loginer == null) {
			b = false;
			try {
				if(isAjax(request)){
					writeJs(JsonUtil.toJson(ActionMsg.setError("您的登录已过期 , 请重新登录!!")));
				}else{
					writeFatal("您的登录已过期 , 请重新登录!!");
				}
			} catch (Exception ex) {
				logger.error("无法拦截登录退出:" + ex.getMessage());
				ex.printStackTrace();
			}
		}*/
		return b;
	}


	protected String view() {
		return "page" + urlPathHelper.getLookupPathForRequest(request);
	}



	// <editor-fold desc="消息处理方法">

	protected void printError(String title, Exception e) {
		System.out.println(title);
		e.printStackTrace();
	}

	/**
	 * 输出DataGrid 返回对象
	 * 
	 * @param count
	 * @param list
	 * @return
	 */
	protected Map<String, Object> showDataStoreMap(int count, List list) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put("total", list.size());
		result.put("rows", list);

		return result;
	}

	/**
	 * 输出请求成功处理对象
	 * 
	 * @param msg
	 * @return
	 */
	protected Map<String, Object> showSuccess(String msg) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put("code", 1);
		result.put("msg", msg);

		return result;
	}

	/**
	 * 输出请求失败处理对象
	 * 
	 * @param msg
	 * @return
	 */
	protected Map<String, Object> showError(String msg) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put("code", -1);
		result.put("msg", msg);

		return result;
	}

	/**
	 * 输出请求成功处理对象
	 * 
	 * @param code 1成功
	 * @param msg
	 * @return
	 */
	public Map<String, Object> showDataStoreMap(int code, String msg) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put("code", code);
		result.put("msg", msg);

		return result;
	}

	public Map<String, Object> getQueryMap(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Enumeration<String> names = request.getParameterNames();
		String key;
		while (names.hasMoreElements()) {
			key = names.nextElement();
			if(key.startsWith("IN_")){
			String[] val = request.getParameterValues(key);
			for(int i=0;i<val.length;i++)
			{
				val[i] = QCommon.urlDecode(val[i]);
			}
			map.put(key, val);
			}else {
				String val = request.getParameter(key);
				val = QCommon.urlDecode(val);
				map.put(key, val);
			}
			
		}
		map.putAll(urlMap);
		return map;
	}

	public Map<String, Object> getQueryMap(HttpServletRequest request,
			String columnNames, String tableName, String whereStr,
			String orderStr) {
		Map<String, Object> map = getQueryMap(request);
		map.put("_sql_select", columnNames);
		map.put("_sql_table", tableName);
		map.put("_sql_where", whereStr);
		map.put("_sql_order", orderStr);
		map.putAll(urlMap);
		return map;
	}
	public void addQueryMap(String url){
		String[] params = url.split("&");
		for (int i = 0; i < params.length; i++) {
			String[] p = params[i].split("=");
			if (p.length == 2) {
				urlMap.put(p[0], p[1]);
			}
		}
	}
	public PageBounds getQueryPager(){
		int pageSize = QRequest.getInteger(request, "pageSize", 10); // 获取datagrid传来的行数
		//每页显示条数
		int pageNo = QRequest.getInteger(request, "page", 1); // 获取datagrid传来的页码
		//当前页
		PageBounds pager = new PageBounds(pageNo, pageSize);
		return pager;
	}
	public Object getQuery(String url){
		addQueryMap(url);
		return getData("page");
	}

	public void write(String data) {
		try {
			response.setHeader("content-type", "text/html;charset=UTF-8");
			OutputStream out = response.getOutputStream();
			out.write(data.getBytes("UTF-8"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// </editor-fold>

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="通用事件">
	/**
	 * 初始化函数，设置相关参数
	 */
	public void init(HttpServletRequest request, HttpServletResponse response,
			Object handler) {
		this.urlPathHelper = new UrlPathHelper();
		this.request = request;
		this.response = response;

        getCurrentLoginUser(request,response);
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(true);
		
		binder.registerCustomEditor(Date.class, new UtilDatePropertyEditor(dateFormat,true));
	}

	/**
	 * 当Action方法执行后被调用
	 */
	public void actionAfter(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
		if (modelAndView == null)
			return;
		String viewName = modelAndView.getViewName();

		if (QCommon.isNullOrEmpty(viewName)) {
			modelAndView.clear();
		} else if (viewName.startsWith("redirect:")) {
			// modelAndView.addAllObjects(_ASSIGN_);
		} else {

			String url = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort() + request.getContextPath();
			String actionUrl = urlPathHelper.getLookupPathForRequest(request);
			int idx = actionUrl.lastIndexOf("/");
			actionUrl = actionUrl.substring(0, idx);
			Map<String, Object> G = new HashMap<String, Object>();
			G.put("host", url);
			G.put("year", new Date().getYear()+1900);
			G.put("serverUrl",url+actionUrl);
			G.put("editUrl", url+actionUrl + "/edit");
			G.put("objName", getBaseService().getBaseComment());
			G.put("idField", getBaseService().getPrimaryKey());

			modelAndView.addObject("G", G);
			modelAndView.addObject("L", loginer);

		}

	}

	@ExceptionHandler
	@ResponseBody
	public Object exceptionHandler(Exception ex, ModelAndView modelAndView) {
		printError("【未处理异常】", ex);

    	/*如果是Ajax请求将错误信息返回到ajax*/
		if(isAjax(request)){
			return showError(ex.getMessage());
		}else{
			writeFatal("系统已重启，请重新登录!");
			return null;
		}
	}

	protected WebLoginUser getCurrentLoginUser(HttpServletRequest req,HttpServletResponse res) {
		//获取本机用户信息，如无法获取，从cas反查
		if(loginer == null){
			loginer = new WebLoginUser().loadSession(req, res);
			if(loginer.getUser_id() == null || loginer.getUser_id() == 0 
					|| loginer.getRole_id() == null || loginer.getRole_id() == 0){
				loginer = loadCASUserInfo(req,res);
			}
		}

		loginer.setRequestSerial(loginer.getRequestSerial()+1);
		loginer.saveSession(req, res);

		return loginer;
	}

	public WebLoginUser loadCASUserInfo(HttpServletRequest request,HttpServletResponse response){
		String account = null;
		 List<CommonProfile> profiles = WebLoginUitls.getProfiles(request, response);
    	 for(CommonProfile profile : profiles){
    		 account =  profile.getId();
    	 }
    	 if (null !=account && !"".equals(account)) {
			Map<String, Object> parm = new HashMap<>();
			parm.put("user_name", account);
			Map<String, Object> u = loginerService.selectMap(parm);
			WebLoginUser user = new WebLoginUser();
			user.setRole_id(Integer.valueOf(val(u, "role_id")) );
			user.setUser_real_name(val(u, "user_real_name"));
			user.setUser_id(Integer.valueOf(val(u, "user_id")));
			user.setUser_global_id(val(u, "user_global_id"));
			user.setUser_name(WebLoginUitls.getVal(u, "user_name"));
			user.setUser_state(Integer.valueOf(WebLoginUitls.getVal(u, "user_state")));
			user.saveSession(request, response);//保存至本地
			return user;
		}
		return null;
	}
	public Boolean isWhiteList(){
		return false;
	}

	private boolean isAjax(HttpServletRequest request){
		//如果是ajax请求响应头会有x-requested-with
		if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
			return true;
		}
		return false;
	}
	public void writeJs(String data) {
		try {
			response.setContentType("application/x-javascript; charset=UTF-8"); // 输出JS文件
			OutputStream out = response.getOutputStream();
			out.write(data.getBytes("UTF-8"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public void writeFatal(String msg){
		String url = getUrl();
		response.setHeader("content-type", "text/html;charset=UTF-8");
		String outString =" <script src=\""+url+"/res/plugin/jQuery/jquery-1.11.3.min.js\" type=\"text/javascript\"></script>";
		outString+="<link href=\""+url+"/res/layer/skin/default/layer.css\" rel=\"stylesheet\">";
		outString+="<link href=\""+url+"/res/layer/skin/moon/style.css\" rel=\"stylesheet\">";
		outString+="<script src=\""+url+"/res/layer/layer.js\"></script>";
		outString+= "<script language=javascript>layer.msg('"+msg+"',{icon: 1, time: 2000, skin: 'layer-ext-moon'},function(){(window.parent||window).location='"
				+ url + "/';});</script>";
		try {
			response.getWriter().print(outString);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="自定义接口">
	public ISysBaseService getBaseService() {
		return null;
	}

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="自定义事件">
	/**
	 * 检查是否具有访问页面权限
	 * 
	 * @return
	 */
	public boolean checkPermission() {
		return true;
	}

	// </editor-fold>

	// <editor-fold desc="保存Action">
	@RequestMapping(value = "/save",method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public DataStore saveAction(T model) {
		return save(model);
		/*ActionMsg = saveBefore(model);
		if(ActionMsg.isError())
			return ActionMsg;
		ActionMsg = save(model);
		if(ActionMsg.isError())
			return ActionMsg;

		return saveAfter(model);*/
	}

	public DataStore saveBefore(T model){
		return ActionMsg.setSuceess("操作成功");
	}

	public DataStore saveAfter(T model){
		return ActionMsg.setSuceess("操作成功");
	}


	public DataStore save(T model) {
		return getBaseService().save(model);
	}

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="通用删除Action">
	@RequestMapping(value = "/delete")
	@ResponseBody
	public DataStore deleteAction() {
		String id = rstr(getBaseService().getPrimaryKey() );
		return delete(id);
		/*
		ActionMsg= delBefore(id);
		if(ActionMsg.isError())
			return ActionMsg;
		ActionMsg=delete(id);
		if(ActionMsg.isError())
			return ActionMsg;
		return delAfter(id);
		*/
	}
	public DataStore delBefore(String id){
		return ActionMsg.setSuceess("操作成功");
	}
	
	public DataStore delAfter(String id){
		return ActionMsg.setSuceess("操作成功");
	}

	public DataStore delete(String sysid) {
		
		return getBaseService().delete(sysid);
	}

	// </editor-fold>

	// <editor-fold desc="通用查询Action">

	public String getTableName() {
		String tableName = rstr("table");
		// 如果表名为空则取内置表名（视图）
		if (tableName.length() == 0) {
			tableName = getBaseService().getBaseView();
		}
		return tableName;
	}

	public String getPrimaryKey() {
		String primaryKey = rstr("pkey");
		// 如果主键为空则取内置主键
		if (primaryKey.length() == 0) {
			primaryKey = getBaseService().getPrimaryKey();
		}
		return primaryKey;
	}


	/**
	 * 获取选择字段
	 * 
	 * @return
	 */
	public String getFieldShow() {
		String show = rstr("fieldShow");
		if (show.length() == 0)
			show = "*";
		return show;
	}

	/**
	 * 获取排序字段
	 */
	public String getFieldOrder() {
		String sort = rstr("sort"); // 例sort=?? desc
		String order = rstr("order");
		if (sort.length() == 0){
			//sort = getBaseService().getPrimaryKey();
		}
		else{
			sort += " " + order;
		}
		
		return sort;
	}

	/**
	 * 返回过滤条件
	 * 
	 * @return
	 */
	public String getFilterCondition() {
		return "";
	}

	@RequestMapping(value = "/getObj", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getObj() throws Exception {

		String sysid = QRequest.getString(request, getBaseService()
				.getPrimaryKey());
		Map<String,Object> obj = getBaseService().selectMap(sysid);
		return obj;
	}

	@RequestMapping(value = "/getList", method=RequestMethod.POST)
	@ResponseBody
	public Object getList() throws Exception {
		return getData("page");
	}

	public String getCondition(){
		return "";
	}
	// </editor-fold>

	// <editor-fold desc="上传文件Action">

	/**
	 * 获取数据集合
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getData", method=RequestMethod.POST)
	@ResponseBody
	public Object getData(String json) {
		String str = "[]";
		if (json == null)
			json = "data";
		int pageSize = QRequest.getInteger(request, "pageSize", 10); // 获取datagrid传来的行数
																	// //每页显示条数
		int pageNo = QRequest.getInteger(request, "page", 1); // 获取datagrid传来的页码
																// //当前页
		// json=obj时只查一行
		if (json == "obj") {
			pageSize = 1;
		}
		// 表名
		String tableName = getTableName();
		// 主键
		String primaryKey = getPrimaryKey();
		// 排序处理
		String fieldOrder = getFieldOrder();

		String whereStr = getFilterCondition(); // 数据必须受限
		String fieldShow = getFieldShow();

		// 日期格式
		String dateTimeFormat = QRequest.getString(request, "dateTimeFormat",
				"yyyy-MM-dd HH:mm"); // 例：dateTimeFormat=yyyy-MM-dd HH:mm

		// 默认输出html(?)
		// String contentType = QRequest.getString(request, "responseType",
		// "json"); //输出json 为： responseType=json
		response.setContentType(QRequest.getResponseType("json")); // 输出JS文件
		// 默认O条数据
		int recordCount = 0;
		Map<String, Object> queryMap = getQueryMap(request, fieldShow,
				tableName, whereStr, fieldOrder);
		PageBounds pager = new PageBounds(pageNo, pageSize);

		List<Map<String, Object>> dt = getBaseService().select(queryMap, pager);
		recordCount = pager.getTotal();

		switch (json) {
		case "data": // 全表格[{}]
			str = new JsonDataGrid(recordCount, dt).toDataJson(dateTimeFormat);
			break;
		case "dataJs":
			String jsName = QRequest.getString(request, "name");
			if (jsName.length() == 0)
				jsName = getBaseService().getBaseTable();
			response.setContentType("application/x-javascript; charset=UTF-8"); // 输出JS文件
			str = MessageFormat.format("var _dataStore[\"{0}\"] = {1} ;",
					jsName, new JsonDataGrid(recordCount, dt).toDataJson(dateTimeFormat));
			break;
		case "page": // 分页表格{total:0,rows:[]}
			str = new JsonDataGrid(recordCount, dt).toJson(dateTimeFormat);
			break;
		case "tree": // 树下拉[{id:0,text:"",children:[]}]
						// fieldTree:id列名,text列名,parentid列名,开始节点id
			String fieldTree = QRequest.getString(request, "fieldTree");
			str = new JsonDataGrid(recordCount, dt).toTreeJson(fieldTree);
			break;

		case "obj":
			str = new JsonDataGrid(recordCount, dt).toObjJson();
			break;
		case "combo":
			str = new JsonDataGrid(recordCount, dt).toComboJson(fieldShow);
			break;
		case "store": // 数据仓库
			response.setContentType("application/x-javascript; charset=UTF-8"); // 输出JS文件
			String storeName = QRequest.getString(request, "name");
			if (storeName.length() == 0)
				storeName = getBaseService().getBaseTable();
			str = new JsonDataGrid(recordCount, dt).writeDataStoreJs(fieldShow,
					storeName);
			break;
		}
		try {
			response.getWriter().print(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	// </editor-fold>

	public Object getMapper(String mapper){
		//int pageSize = QRequest.getInteger(request, "rows", 99999); // 获取datagrid传来的行数
		//int pageNo = QRequest.getInteger(request, "page", 1); // 获取datagrid传来的页码
		
		// 表名
		String tableName = getTableName();
		// 主键
		String primaryKey = getPrimaryKey();
		// 排序处理
		String fieldOrder = getFieldOrder();
		String whereStr = getFilterCondition(); // 数据必须受限
		String fieldShow = getFieldShow();

		response.setContentType(QRequest.getResponseType("json")); // 输出JS文件
		Map<String, Object> queryMap = getQueryMap(request, fieldShow,
				tableName, whereStr, fieldOrder);
		//PageBounds pager = new PageBounds(pageNo, pageSize);

		List<Map<String, Object>> dt = getBaseService().selectMapper(mapper, queryMap);
		int recordCount = dt != null ? dt.size() : 0;
		String str = new JsonDataGrid(recordCount, dt).toDataJson();

		try {
			response.getWriter().print(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/// <summary>
    /// 验证存在
    /// </summary>
    /// <param name="fieldName">字段名</param>
    /// <param name="fieldValue">字段值</param>
    /// <param name="filters">过滤条件</param>
    /// <returns>返回[1]表示存在，返回[0]表示不存在。</returns>
	@RequestMapping(value = "/VerifiedExists")  
	@ResponseBody
    public  Object VerifiedExists(String fieldName, String fieldValue, String filters )
    {
        // 验证结果
        int nCount = 0;
        Map<String, Object> map=new HashMap<String, Object>();
        map.put(fieldName, fieldValue);
        if(filters!=null){
	        Map<String,Object> filterMap=JsonUtil.fromJson(filters,Map.class,String.class,Object.class);
	        map.putAll(filterMap);
        }
        List<Map<String, Object>> list=this.getBaseService().select(map);
        nCount=list.size();
        try {
        	response.setContentType("text/html");
			response.getWriter().print(nCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
    }

	// <editor-fold desc="获取参数">

	public String val(Object obj){
		if(obj == null)
			return "";
		return obj.toString();
	}
	public String val(Map<String, Object> m, String key){
		Object obj = m.get(key);
		if(obj == null)
			return "";
		return obj.toString();
	}
	public String rstr(String name){
		return QRequest.getString(request, name);
	}
	public String rstr(String name, String defValue){
		return QRequest.getString(request, name, defValue);
	}
	public Integer rint(String name){
		return QRequest.getInteger(request, name);
	}
	public Integer rint(String name, Integer defValue){
		return QRequest.getInteger(request, name, defValue);
	}
	public String getUrl(){
		String url = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath();
		return url;
	}
	// </editor-fold>
}


