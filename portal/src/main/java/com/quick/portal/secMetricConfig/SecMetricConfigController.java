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
package com.quick.portal.secMetricConfig;

import com.quick.core.base.ISysBaseService;
import com.quick.core.base.SysBaseController;
import com.quick.core.base.model.JsonDataGrid;
import com.quick.core.base.model.PageBounds;
import com.quick.core.util.common.QCookie;
import com.quick.core.util.common.QRequest;
import com.quick.portal.web.model.DataResult;
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
 * sec_metric_config请求类
 * lizc
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/secMetricConfig")
public class SecMetricConfigController extends SysBaseController<SecMetricConfigDo> {
    
    @Resource(name = "secMetricConfigService")
    private ISecMetricConfigService secMetricConfigService;
    
    @Override
    public ISysBaseService getBaseService(){
        return secMetricConfigService;
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
    @RequestMapping(value = "/getList")
    @ResponseBody
    public Object getList() throws Exception {
        return getData("page");
    }

    @RequestMapping(value = "/getData")
    @ResponseBody
    public Object getData(String json) {
        //用户ID
        String userId = QCookie.getValue(request, "ids");//获取当前用户id
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
        queryMap.put("user_id", userId);//user_id 存入参数
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
    @RequestMapping(value = "/use")    //启用指标配置
    @ResponseBody
    public Object use() throws Exception {
        String userId = QCookie.getValue(request, "ids");//获取当前用户id

        int term_type_id = 0;//获取终端设备类型id  0 电脑 1手机 2 pad 9 全部
       String is_active=request.getParameter("is_active");
        Map<String, Object> queryMap = getQueryMap(request);
        queryMap.put("term_type_id",term_type_id);//参数列表增加设备终端id
        queryMap.put("user_id", userId);//user_id 存入参数
        if(is_active.equals("undefined") ||is_active.equals("")||is_active.equals("0")){
            queryMap.put("is_active",'1');//启用
        }else{
            queryMap.put("is_active",'0');//禁用
        }
        int result;
        /*int count=secMetricConfigService.selectUAC(queryMap);
        int result;
        if(count==0){
            //数据库没有相应记录 新增
            result=secMetricConfigService.insertUAC(queryMap);
        }else{
            //数据库有相应记录 更新
            result=secMetricConfigService.updateUAC_Active(queryMap);
        }*/
        result=secMetricConfigService.updateUAC_Active(queryMap);

        return result;
    }
    @RequestMapping(value = "/updateVerNa")    //修改版本号
    @ResponseBody
    public Object updateVerNa() throws Exception {
        Map<String, Object> queryMap = getQueryMap(request);
        String userId = QCookie.getValue(request, "ids");//获取当前用户id
        int user_id=Integer.parseInt(userId);
        int term_type_id = 0;//获取终端设备类型id  0 电脑 1手机 2 pad 9 全部
        queryMap.put("user_id",user_id);
        queryMap.put("term_type_id",term_type_id);//参数列表增加设备终端id
        int count=secMetricConfigService.selectUAC(queryMap);
        int result;
        if(count==0){
            //数据库没有相应记录 新增
            result=secMetricConfigService.insertUAC(queryMap);
        }else{
            //数据库有相应记录 更新
            result=secMetricConfigService.updateUAC_Version(queryMap);
        }
        if(result!=0)
            return true;
        return false;
    }

    //app获取配置版本接口
    @RequestMapping(value = "/getMetricData")
    @ResponseBody
    public DataResult getMetricData() {
        //用户ID
        String userId = rstr("u", loginer.getUser_id().toString()); //用户id
        int pageSize = QRequest.getInteger(request, "pageSize", 99999); // 获取datagrid传来的行数
        // //每页显示条数
        int pageNo = QRequest.getInteger(request, "page", 1); // 获取datagrid传来的页

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

        response.setContentType(QRequest.getResponseType("json")); // 输出JS文件
        int recordCount = 0;

        Map<String, Object> queryMap = getQueryMap(request, fieldShow,
                tableName, whereStr, fieldOrder);
        queryMap.put("user_id", userId);//user_id 存入参数
        PageBounds pager = new PageBounds(pageNo, pageSize);

        List<Map<String, Object>> dt = getBaseService().select(queryMap, pager);

        recordCount = pager.getTotal();
        try {
           // return new DataResult(new JsonDataGrid(recordCount, dt).toJson(dateTimeFormat));
            return new DataResult( dt);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}