package com.seaboxdata.portal.mobile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quick.core.base.SysApiController;
import com.quick.core.base.model.PageBounds;
import com.quick.core.util.common.QCookie;
import com.quick.core.util.common.QRequest;
import com.quick.portal.search.infomng.IInfoMngService;
import com.quick.portal.search.infomng.SolrInfoConstants;
import com.quick.portal.sysUser.ISysUserService;
import com.quick.portal.sysUser.SysUserDO;
import com.quick.portal.web.model.DataResult;


/**
 * 门户API接口类
 *
 * @author Administrator
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/mobile/info")
public class InfoApiController extends SysApiController {
	
 	@Resource(name = "infoMngService")
    private IInfoMngService infoMngService;
 	
    @Resource(name = "sysUserService")
    private ISysUserService sysUserService;
 	
 	
    /**
     * 查询用户所有应用
     * @return
     * http://localhost:8080/portal/api/info/getInfoApp?obj=1&keyword=北京&page=1&pageSize=10
     * 
     * portal_doc_class：信息类型
		id:信息文档URL
		title:信息标题
		status:已阅(1)、未阅（0）
     * @throws UnsupportedEncodingException 

     */
    @RequestMapping(value = "/getInfoApp")
    @ResponseBody
    public DataResult getInfoApp(String obj,String keyword,Integer page,Integer pageSize,String userName) throws UnsupportedEncodingException{
    	Map<String, Object> queryMap  = new HashMap<String, Object>();
    	if(null == keyword || "".equals(keyword)){
    		DataResult dt = new DataResult();
    		dt.setError("关键字不允许为空,请输入关键字查询条件!");
    		return dt;
    	}
    	if(null == userName || "".equals(userName)){
    		DataResult dt = new DataResult();
    		dt.setError("用户账号不能为空!");
    		return dt;
    	}
    	keyword = URLDecoder.decode(keyword, "UTF-8");
    	queryMap.put(SolrInfoConstants.INDEX_KEYWORD, keyword);
    	String type = QRequest.getString(request, "obj");
		int pgSize = QRequest.getInteger(request, "pageSize", 10); // 获取datagrid传来的行数
		int pageNo = QRequest.getInteger(request, "page", 1); // 获取datagrid传来的页码
		PageBounds pager = new PageBounds(pageNo, pgSize);
		//当前用户编号
		String userID =  getUserIdByuserNm(userName);
		List<Map<String, Object>> retList = infoMngService.getSolrInfo(queryMap, pager,userID,type);
        return new DataResult(retList);
    }
    
    
    

	 
	 
	   /**
	     * 按热点搜索信息查询
	     *
	     * @throws IOException
	     */
    	@RequestMapping(value = "/getHotSearchInfo")
    	@ResponseBody
		public DataResult getHotSearchInfo() throws Exception {
	    	 List<Map<String, Object>> data = infoMngService.getHotSearchInfo();
	    	 return new DataResult(data);
		}
    	
    	
    	/**
	     * 按个人习惯搜索信息查询
	     *
	     * @throws IOException
	     */
    	@RequestMapping(value = "/getPerHabitsInfo")
    	@ResponseBody
		public DataResult getPerHabitsInfo(String userName) throws Exception {
	    	if(null == userName || "".equals(userName)){
	    		DataResult dt = new DataResult();
	    		dt.setError("用户账号不能为空!");
	    		return dt;
	    	}
	    	 String userID =  getUserIdByuserNm(userName);
	    	 List<Map<String, Object>> data = infoMngService.getPerHabitsInfo(userID);
	    	 return new DataResult(data);
		}
	    
	    /*
	     * 通过用户帐号查询用户编号
	     */
	    public String getUserIdByuserNm(String userName){
		      String userId = null;
	    	  Map<String, Object> parm = new HashMap<>();
	          parm.put("user_name", userName);
	  	      SysUserDO loginUser = sysUserService.selectObj(parm);
	  	      if(null != loginUser){
	  	    	userId = loginUser.getUser_id().toString();
	  	      }
	  	      return userId;
	    }
	    
	  

}
