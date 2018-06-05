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
		@RequestMapping(value = "/getInfo", method=RequestMethod.POST)
		@ResponseBody
		public Object getInfo(String json) {
			String str = "[]";
			// 默认O条数据
			int recordCount = 0;
			List dt = new ArrayList<>();
			Map<String, Object> queryMap = getQueryMap(request, null,
					null, null, null);
			Map<String, Object> mp = new HashMap();
			String keyword = queryMap.get(SolrInfoConstants.INDEX_KEYWORD).toString();
	        if(null == keyword || "".equals(keyword)){
	        	mp = new HashMap();
	 			mp.put("rows", 0);
	        }else{
	        	String type = QRequest.getString(request, "obj_type");
				int pageSize = QRequest.getInteger(request, "pageSize", 10); // 获取datagrid传来的行数
				int pageNo = QRequest.getInteger(request, "page", 1); // 获取datagrid传来的页码
				PageBounds pager = new PageBounds(pageNo, pageSize);
				//当前用户编号
				String ids = QCookie.getValue(request, "ids");
				List retList = infoMngService.getSolrInfo(queryMap, pager,ids,type);
				if(null !=retList && !retList.isEmpty()){
					dt = (List)retList.get(1);
					recordCount = (int) retList.get(0);
					//记录搜索信息
					saveSearchTermsInfo(queryMap,ids);
				}else{
					mp = new HashMap();
					mp.put("rows", 0);
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
	     * @throws IOException
	     */
	    @RequestMapping(value = "/download")
		public ModelAndView download(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
		String type = request.getParameter("type");
		String filePath = request.getParameter("id");
		try {
			filePath = URLDecoder.decode(filePath,
					MetricPrivilegeConstants.LANGUAGE_CODE_UTF);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new Exception("指标转义异常：" + e.getLocalizedMessage());
		}

		if (filePath == null || "".equals(filePath)) {
			System.out.println("文件路径名称:" + filePath + ",filePath不能为空");
			throw new Exception("查询文件路径异常: " + "文件路径:" + filePath
					+ ",filePath不能为空");
		}
		String ids = QCookie.getValue(request, "ids");
		infoMngService.saveVisitInfo(filePath, Integer.parseInt(type),
				Integer.parseInt(ids));
		FileOperateUtils.download(request, response, filePath);
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
}