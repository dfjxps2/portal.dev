package com.seaboxdata.portal.mobile;
import com.quick.core.base.ISysBaseService;
import com.quick.core.base.SysApiController;
import com.quick.core.base.model.PageBounds;
import com.quick.core.util.common.QRequest;
import com.quick.portal.secMetricConfig.ISecMetricConfigService;
import com.quick.portal.web.model.DataResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by lizc on 2018/7/16.
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/mobile/VersionConfig")
public class VersionConfigController extends SysApiController {
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
    //app启用指标配置
    @RequestMapping(value = "/useMetricVersion")    //启用指标配置
    @ResponseBody
    public Object useMetricVersion() throws Exception {
        String userId = rstr("u", loginer.getUser_id().toString());//获取当前用户id

        int term_type_id = 0;//获取终端设备类型id  0 电脑 1手机 2 pad 9 全部
        Map<String, Object> queryMap = getQueryMap(request);
        queryMap.put("term_type_id",term_type_id);//参数列表增加设备终端id
        queryMap.put("user_id", userId);//user_id 存入参数
        queryMap.put("is_active",'1');//启用

        int result;

        result=secMetricConfigService.updateUAC_Active(queryMap);

        if(result!=0)
            return new DataResult(1,"版本激活成功");
        return new DataResult(0,"版本激活失败");
    }
    //app修改版本号接口
    @RequestMapping(value = "/updateVerNaApp")    //修改版本号
    @ResponseBody
    public Object updateVerNaApp() throws Exception {
        Map<String, Object> queryMap = getQueryMap(request);
        String userId = rstr("u", loginer.getUser_id().toString());//获取当前用户id
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
            return new DataResult(1,"版本号设置成功");
        return new DataResult(0,"版本号设置失败");
    }
}
