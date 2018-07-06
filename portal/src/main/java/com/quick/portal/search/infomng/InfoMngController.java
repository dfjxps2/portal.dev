/**
 * <h3>标题 : potal统一门户-InfoMngController </h3>
 * <h3>描述 : InfoMngController请求类</h3>
 * <h3>日期 : 2018-04-13</h3>
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
package com.quick.portal.search.infomng;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.quick.core.base.ISysBaseService;
import com.quick.core.base.SysBaseController;
import com.quick.core.base.model.JsonDataGrid;
import com.quick.core.base.model.PageBounds;
import com.quick.core.util.common.QCookie;
import com.quick.core.util.common.QRequest;
import com.quick.portal.security.authority.metric.MetricPrivilegeConstants;
import com.quick.portal.web.model.DataResult;

/**
 * sys_user请求类
 * @author 你自己的姓名
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/info")
public class InfoMngController extends SysBaseController<InfoMngDO> {

	 	@Resource(name = "infoMngService")
	    private IInfoMngService infoMngService;

	    
	    @Override
	    public ISysBaseService getBaseService(){
	        return infoMngService;
	    }
	    
	    //页面请求
	    @RequestMapping
	    public String list(ModelMap model) {
	    	String ids = QCookie.getValue(request, "ids");
	    	String habitInfo = infoMngService.getPersonalHabitsInfo(ids);
	    	model.addAttribute("data", habitInfo);
	        return view();
	    }
	    @RequestMapping
	    public String edit(ModelMap model) {
	        return view();
	    }
	    @RequestMapping
	    public String chose(ModelMap model) {
	        return view();
	    }


		/**
		 * 获取数据集合
		 * 
		 * @return
		 */
		@RequestMapping(value = "/getInfo")
		@ResponseBody
		public Object getInfo() {
			String str = "[]";
			// 默认O条数据
			int recordCount = 0;
			List dt = new ArrayList<>();
			Map<String, Object> queryMap = getQueryMap(request, null,
					null, null, null);
			String keyword = queryMap.get(SolrInfoConstants.INDEX_KEYWORD).toString();
	        if(null != keyword && !"".equals(keyword)){
	        	String type = QRequest.getString(request, "obj_type");
				int pageSize = QRequest.getInteger(request, "pageSize", 10); // 获取datagrid传来的行数
				int pageNo = QRequest.getInteger(request, "page", 1); // 获取datagrid传来的页码
				PageBounds pager = new PageBounds(pageNo, pageSize);
				//当前用户编号
				String ids = QCookie.getValue(request, "ids");
				List retList = infoMngService.getSolrInfo(queryMap, pager,ids,type);
				if(null !=retList && retList.size()>0){
					dt = (List)retList.get(1);
					recordCount = (int) retList.get(0);
					//记录搜索信息
					saveSearchTermsInfo(queryMap,ids);
				}
	        }
	        // 日期格式
			String dateTimeFormat = QRequest.getString(request, "dateTimeFormat",
					"yyyy-MM-dd HH:mm"); // 例：dateTimeFormat=yyyy-MM-dd HH:mm
			response.setContentType(QRequest.getResponseType("json")); // 输出JS文件
			str = new JsonDataGrid(recordCount, dt).toJson(dateTimeFormat);
			try {
				response.getWriter().print(str);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		/*
		 * 记录搜索信息
		 */
		public void saveSearchTermsInfo (Map<String, Object> queryMap,String userID){
			infoMngService.saveSearchTermsInfo(queryMap,userID);
		}
		
		 /**
	     * 下载文件
	     *
	     * @param page
	     *            页面请求对象
	     * @return 跳转页面
		 * @throws Exception 
	     * @throws IOException
	     * filePath：内容
	     * attachPath:附件
	     */
	    @RequestMapping(value = "/download")
		public ModelAndView download(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			String type = request.getParameter("type");
			String filePath = request.getParameter("id");
			String attachPath = request.getParameter("aid");
			try {
				if (filePath == null || "".equals(filePath)) {
					System.out.println("文件路径名称:" + filePath + ",filePath不能为空");
					throw new Exception("查询文件路径异常: " + "文件路径:" + filePath
							+ ",filePath不能为空");
				}
				filePath = URLDecoder.decode(filePath,
						MetricPrivilegeConstants.LANGUAGE_CODE_UTF);
				String ids = QCookie.getValue(request, "ids");
				infoMngService.saveVisitInfo(filePath, Integer.parseInt(type),
					Integer.parseInt(ids));
				//消息：2，有内容附件和上传附件
				if(null != type && SolrInfoConstants.MSG_OBJ_TYPE.equals(type)){
					if (attachPath == null || "".equals(attachPath)) {
						FileOperateUtils.download(request, response, filePath);
					}else{
						attachPath = URLDecoder.decode(attachPath,
								MetricPrivilegeConstants.LANGUAGE_CODE_UTF);
						FileOperateUtils.download(request, response, filePath, attachPath);
					}
				}else{
					FileOperateUtils.download(request, response, filePath);
				}
			} catch (Exception e) {
				String msg = e.getLocalizedMessage();
//				String resultStr = "<script>message('"+msg+"');</script>";
//				response.getWriter().write(resultStr);
				response.getWriter().write("<script>alert('"+msg+"');window.history.back();</script>");
			}
			return null;
		}
	    
	    
	    
	    /**
	     * 按个人习惯搜索信息查询
	     *
	     * @throws IOException
	     */
	    @RequestMapping(value = "/getPersonalHabitsInfo")
		public void getPersonalHabitsInfo(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
	    	 String userID = QCookie.getValue(request, "ids");
	    	 String json = infoMngService.getPersonalHabitsInfo(userID);
	         try {
	        	 response.getWriter().write(json);
	         } catch (IOException e) {
	             e.printStackTrace();
	         }
		}
	    
	    
	    
	    @RequestMapping(value = "/getMsgIDByID")
	    @ResponseBody
	    public void getMsgIDByID(HttpServletResponse res,String uid) throws Exception {
	    	String	msgID = "0";
			if (uid == null || "".equals(uid)) {
				msgID = "0";
				System.out.println("文件路径名称:" + uid + ",filePath不能为空");
				throw new Exception("查询文件路径异常: " + "文件路径:" + uid
						+ ",filePath不能为空");
			}
			uid = URLDecoder.decode(uid,
					MetricPrivilegeConstants.LANGUAGE_CODE_UTF);
			msgID = infoMngService.getMsgIDByID(uid);
	        res.getWriter().write(msgID);
	    }
	    
	    
	    
	    /**
	     * 查询用户所有应用
	     * @return
	     * http://localhost:8080/portal/info/getInfoApp?obj=1&keyword=北京&page=1&pageSize=10
	     * 
	     * portal_doc_class：信息类型
			id:信息文档URL
			title:信息标题
			status:已阅(1)、未阅（0）
	     * @throws UnsupportedEncodingException 

	     */
	   
	    @RequestMapping(value = "/getInfoApp")
	    @ResponseBody
	    public DataResult getInfoApp(String obj,String keyword,Integer page,Integer pageSize) throws UnsupportedEncodingException{
	    	Map<String, Object> queryMap  = new HashMap<String, Object>();
	    	if(null == keyword || "".equals(keyword)){
	    		DataResult dt = new DataResult();
	    		dt.setError("关键字不允许为空,请输入关键字查询条件!");
	    		return dt;
	    	}
	    	keyword = URLDecoder.decode(keyword, "UTF-8");
	    	queryMap.put(SolrInfoConstants.INDEX_KEYWORD, keyword);
	    	String type = QRequest.getString(request, "obj");
			int pgSize = QRequest.getInteger(request, "pageSize", 10); // 获取datagrid传来的行数
			int pageNo = QRequest.getInteger(request, "page", 1); // 获取datagrid传来的页码
			PageBounds pager = new PageBounds(pageNo, pgSize);
			//当前用户编号
			String ids = QCookie.getValue(request, "ids");
			List<Map<String, Object>> retList = infoMngService.getSolrInfo(queryMap, pager,ids,type);
	        return new DataResult(retList);
	    }
	    
	    
}