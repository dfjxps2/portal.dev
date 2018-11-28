/**
 * <h3>标题 : potal统一门户-user_access_log </h3>
 * <h3>描述 : user_access_log请求类</h3>
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
package com.quick.portal.userAccessLog;

import com.quick.core.base.ISysBaseService;
import com.quick.core.base.SysBaseController;
import com.quick.core.base.model.JsonDataGrid;
import com.quick.core.base.model.PageBounds;
import com.quick.core.util.common.QRequest;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * user_access_log请求类
 * lizc
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/userAccessLog")
public class UserAccessLogController extends SysBaseController<UserAccessLogDO> {


    @Resource(name = "userAccessLogService")
    private IUserAccessLogService userAccessLogService;

    @Override
    public ISysBaseService getBaseService(){
        return userAccessLogService;
    }

    @RequestMapping
    //页面请求
    public String list(ModelMap model) {
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

    @RequestMapping(value = "/logTable")
    public Object  logTable( ) {
        return view();
    }

    @RequestMapping(value = "/logTableItem")
    @ResponseBody
    public Map<String, Object> logTableItem( ) {
        Map<String, Object> queryMap = getQueryMap(request);
        // 日期格式
        String dateTimeFormat = QRequest.getString(request, "dateTimeFormat",
                "yyyy-MM-dd ");
        // response.setContentType(QRequest.getResponseType("json")); // 输出JS文件
        int count = getBaseService().count( queryMap );
        Map<String, Object> item=new HashedMap();
        item.put("count",count);
        return item;

    }
    @RequestMapping(value = "/logTableUser")
    @ResponseBody
    public List<Map<String, Object>> logTableUser( ) {
        Map<String, Object> queryMap = getQueryMap(request);
        // 日期格式
        String dateTimeFormat = QRequest.getString(request, "dateTimeFormat",
                "yyyy-MM-dd ");
        // response.setContentType(QRequest.getResponseType("json")); // 输出JS文件
        // int count = getBaseService().count( queryMap );
        //时间范围内登录最多用户前10
        List<Map<String, Object>> topUser= userAccessLogService.selectTopUser(queryMap);
        //时间范围访问最多标签数前10
        //List<Map<String, Object>> topTag=userAccessLogService.selectTopTag(queryMap);
        return topUser;

    }
    @RequestMapping(value = "/logTableTag")
    @ResponseBody
    public List<Map<String, Object>> logTableTag( ) {
        Map<String, Object> queryMap = getQueryMap(request);
        // 日期格式
        String dateTimeFormat = QRequest.getString(request, "dateTimeFormat",
                "yyyy-MM-dd ");
        // response.setContentType(QRequest.getResponseType("json")); // 输出JS文件
        int count = getBaseService().count( queryMap );
        //时间范围内登录最多用户前10
        // List<Map<String, Object>> topUser= userAccessLogService.selectTopUser(queryMap);
        //时间范围访问最多标签数前10
        List<Map<String, Object>> topTag=userAccessLogService.selectTopTag(queryMap);
        return topTag;

    }
    @RequestMapping(value = "/getList")
    @ResponseBody
    public Object getList() throws Exception {
        return getData("page");
    }
    @RequestMapping(value = "/getData")
    @ResponseBody
    public Object getData(String json) {
        String str = "[]";
        if (json == null)
            json = "data";
        int pageSize = QRequest.getInteger(request, "pageSize", 99999); // 获取datagrid传来的行数
        // //每页显示条数
        int pageNo = QRequest.getInteger(request, "page", 1); // 获取datagrid传来的页码
        // //当前页
        // json=obj时只查一行
        if (json .equals( "obj") ){
            pageSize = 1;
        }
        // 表名
        String tableName = getTableName();
        // 主键
        String primaryKey = getPrimaryKey();
        // 排序处理
        String fieldOrder = getFieldOrder();
        String whereStr = getCondition();
        whereStr += getFilterCondition();   // 数据必须受限
        String fieldShow = getFieldShow();

        // 日期格式
        String dateTimeFormat = QRequest.getString(request, "dateTimeFormat",
                "yyyy-MM-dd HH:mm:ss"); // 例：dateTimeFormat=yyyy-MM-dd HH:mm

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

}