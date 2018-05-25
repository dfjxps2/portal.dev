package com.quick.portal.mesManage;

import com.quick.core.base.ISysBaseService;
import com.quick.core.base.SysBaseController;
import com.quick.core.base.model.DataStore;
import com.quick.core.base.model.JsonDataGrid;
import com.quick.core.base.model.PageBounds;
import com.quick.core.util.common.QRequest;
import com.quick.portal.search.infomng.SolrInfoConstants;
import com.quick.portal.search.infomng.SolrUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.SolrDocumentList;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.xml.sax.SAXException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by GaoPh on 2018/5/3.
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/mesManage")
public class MesManageController extends SysBaseController<MesManageDO> {


    @Resource(name="mesManageService")
    private MesManageService mesManageService;

    @Autowired
    private MesManageDao mesManageDao;

    @Override
    public ISysBaseService getBaseService(){
        return mesManageService;
    }

    @RequestMapping
    public String  list(Model view){return  view();}

    @RequestMapping
    public String  tagedit(Model view){return  view();}

    @RequestMapping
    public String  tagchose(Model view){return  view();}

    @RequestMapping
    public String  addtag(Model view){return  view();}

    @RequestMapping
    public String  addtagtype(Model view){return  view();}

    @RequestMapping
    public String  tagtypeedit(Model view){return  view();}

    @RequestMapping
    public String  tagtypelist(Model view){return  view();}

    @RequestMapping
    public String  taglist(Model view){return  view();}

    @RequestMapping
    public String  contentrule(Model view){return  view();}

    @RequestMapping
    public String  addRules(Model view){return  view();}

    @RequestMapping
    public String  mespublish(Model view){return  view();}

    @RequestMapping
    public String  mespubtag(Model view){return  view();}

    @RequestMapping
    public String  uploadRule(Model view){return  view();}

    @RequestMapping
    public String  mesedit(Model view){return  view();}

    @RequestMapping
    public String  mesappr(Model view){return  view();}

    @RequestMapping
    public String  mesdetail(Model view){return  view();}


    //内容管理--查询
    @RequestMapping(value = "getMesData")
    @ResponseBody
   public String getMesData(HttpServletResponse res ){
       String str="[]";
       //分页参数
       int pageSize= QRequest.getInteger(request,"pageSize",10);
       int pageNo=QRequest.getInteger(request,"pageNo",1);
       //获取表名
       String tableName = getTableName();

       // 排序处理
       String fieldOrder = getFieldOrder();
       String whereStr = getCondition();
       whereStr += getFilterCondition(); // 数据必须受限
       String fieldShow = getFieldShow();

       // 日期格式
       String dateTimeFormat = QRequest.getString(request, "dateTimeFormat",
               "yyyy-MM-dd HH:mm:ss");
       response.setCharacterEncoding("utf-8");
       response.setContentType(QRequest.getResponseType("json")); // 输出JS文件
       // 默认O条数据
       int recordCount = 0;
       String tags = QRequest.getString(request,"tag_text");
       int maxtagtypeid = mesManageDao.selectMaxTypeTagId();
       Map<String, Object> queryMap = getQueryMap(request, fieldShow,
               tableName, whereStr, fieldOrder);
       PageBounds pager = new PageBounds(pageNo, pageSize);
        List<Map<String, Object>> dt =new ArrayList<>();
        if(tags!=null && !tags.equals("")&&!tags.equals("undefined")){
            String[] result = tags.split(",");
            for(String data:result){
                int tagid = Integer.parseInt(data);
                if(tagid > maxtagtypeid){
                    List<Map<String,Object>> map = mesManageDao.mesByTag(tagid - 1);
                   for(Map<String,Object> param : map){
                    queryMap.put("msg_id",param.get("msg_id"));
                       List<Map<String,Object>> list = mesManageDao.selectMes(queryMap, pager);
                       dt.add(list.get(0));
                   }
                }
            }
        }else {
            dt= mesManageDao.selectMes(queryMap, pager);
        }
        int a = 1;
       for (Map<String,Object> data : dt){
           String  serialNum= String.format("%03d",a);
           String msgId  =  data.get("msg_id").toString();
       List<String> msgTag = mesManageDao.selectMesTag(msgId);
           data.put("msgtext", msgTag);
           data.put("serialNum",serialNum);
            a+=1;
       }
       recordCount = mesManageDao.count(queryMap);
       str = new JsonDataGrid(recordCount, dt).toJson(dateTimeFormat);
       try {
           response.getWriter().print(str);
           response.getWriter().flush();
       } catch (IOException e) {
           e.printStackTrace();
       }
       return null;
   }

   //发布内容
   @RequestMapping(value="addMes",method={RequestMethod.GET,RequestMethod.POST})
   @ResponseBody
   public void publishMes(MesManageDO mesManageDO,String[] tagId,HttpServletRequest request,HttpServletResponse response,boolean MERGE) throws IOException {
       String path = createPath();
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
       MultipartFile file = multipartRequest.getFile("file");
       String title = mesManageDO.getMsg_title();
       String attName = file.getOriginalFilename(); //附件文件名
       String contentName = title+".txt"; //发布信息内容文件名
       File fc = createFile(path,contentName);
       File fa = createFile(path,attName);
       FileOutputStream fos = null;
       file.transferTo(fa);
       contentFile(fos,fc,mesManageDO);
       try{
           if(fos != null){
                         fos.close();
           }
           mesManageDO.setMsg_attachment(path+attName);//上传附件存放路径
           mesManageDO.setMsg_content(path+contentName); //上传信息内容存放路径
           mesManageDO.setMsg_src_id(MesInfoConstants.PLATFORM_SOURCE_ID);
           mesManageDO.setMsg_type_id(MesInfoConstants.PLATFORM_MES_TYPE);
           Calendar date = Calendar.getInstance();
           Date date1 =date.getTime();
           mesManageDO.setPub_time(date1);
           mesManageDO.setAppr_time(date1);
            String id = path+contentName;
           String content = combinFile(fa,mesManageDO);
           String type = SolrInfoConstants.MSG_OBJ_TYPE;
           Map<String,Object> map = new HashMap<>();
         SolrUtils.addSolrInfo(id,content,type,title);
           List<Map<String,Object>> rules=  mesManageDao.selectRules(map);
           int lab = autoFilter(rules,id);
           if(lab ==1){
               deleteFile(path+contentName);
               deleteFile(path+attName);
               response.getWriter().write("2");
               response.getWriter().flush();
               return;
           }
           mesManageDO.setAppr_state(MesInfoConstants.AUTOMATIC_APPROVAL);
           mesManageDao.insert(mesManageDO);
           map.clear();
           map.put("appr_time",mesManageDO.getAppr_time());
           List<Map<String,Object>> result = mesManageDao.selectMes(map);
           String msgId = result.get(0).get("msg_id").toString();
           String[] tags = tagId[0].split(",");
           for(int i=0;i<tags.length;i++){
               Map<String,Object> maptag = new HashMap<>();
               maptag.put("msg_id",msgId);
               maptag.put("tag_id",Integer.parseInt(tags[i]));
               mesManageDao.insertMesTag(maptag);
           }
           response.getWriter().write("1");
           response.getWriter().flush();
                 }catch (Exception e){
                     e.printStackTrace();
                 }
   }

   //生成文件路径
    public String createPath(){
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        String month = String.valueOf(date.get(Calendar.MONTH)+1);
        String day = String.valueOf(date.get(Calendar.DAY_OF_MONTH));
        int mo = Integer.parseInt(month);
        if(mo<=9){
            month = "0"+month;
        }
      String url =  request.getSession().getServletContext().getRealPath("/");
        String path = url+"upload"+"\\mesPublish"+"\\"+year+month+day+"\\";
        return  path;
   }

    //生成文件
    public File createFile(String path,String name) throws IOException {
        File fa = new File(path,name);
        if(!fa.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            fa.getParentFile().mkdirs();
        }
        if (!fa.exists()) {//文件不存在则创建
            fa.createNewFile();
        }else{
            fa.delete();
            fa.createNewFile();
        }
        return fa;
    }
    //将信息内容生成本地文件保存
    public void contentFile(FileOutputStream fos,File file,MesManageDO mesManageDO) throws IOException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currenttime = format.format(new Date());
        String msgsource = mesManageDO.getMsg_src_name();
        String msgclass = "密级："+mesManageDO.getMsg_class_name();
        String msgapprstate = null;
        String  appstate = null;
        if(mesManageDO.getAppr_state() != null && !mesManageDO.getAppr_state().equals("")){
            appstate = mesManageDO.getAppr_state().toString();
       switch (appstate){
            case "1":
                msgapprstate = "人工审核通过";
                break;
            case "2":
                msgapprstate = "人工审核拒绝";
                break;
           case "3":
               msgapprstate = "审核通过";
               break;
           case "4":
               msgapprstate = "审核拒绝";
               break;
       }}else {
            msgapprstate="审核通过";
        }
        String msgappr = "审核状态："+ msgapprstate;
       String secondrow = currenttime+"     "+msgsource+"           "+msgclass+"       "+msgappr;
        StringBuffer buffer = new StringBuffer();
        buffer.append(mesManageDO.getMsg_title());
        buffer.append(System.getProperty("line.separator"));
        buffer.append(secondrow);
        buffer.append(System.getProperty("line.separator"));
        buffer.append(mesManageDO.getMsg_digest());
        buffer.append(System.getProperty("line.separator"));
        buffer.append(mesManageDO.getMsgcontent());
        fos = new FileOutputStream(file);
        fos.write(buffer.toString().getBytes());
        fos.flush();
    }

    //将附件和信息各内容合并生成文件
    public  String  combinFile(File fa,MesManageDO mesManageDO) throws IOException {
        String nei = null;
        if(fa!=null ){
            try {
                nei = parseFile(fa);
            } catch (TikaException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
        }else{
            nei = "";
        }
        String content =   mesManageDO.getMsg_title()+  mesManageDO.getMsg_digest()+mesManageDO.getMsgcontent()+nei;
        return content;
    }

    //删除指定路径下的文件
    public static  void deleteFile(String path){
        File file = new File(path);
        if(file.exists() && file.isFile()) {
              file.delete();
        }
    }

  //Tika解析文件工具类
      public static String parseFile(File file) throws IOException, TikaException, SAXException {
              InputStream input = null;
            Metadata metadata = new Metadata();
            metadata.set(Metadata.CONTENT_ENCODING, "UTF-8");
            metadata.set(Metadata.RESOURCE_NAME_KEY, file.getName());
              input = TikaInputStream.get(file);
              BodyContentHandler handler = new BodyContentHandler();
              AutoDetectParser parser = new AutoDetectParser();
            ParseContext context = new ParseContext();
            context.set(Parser.class, parser);
            parser.parse(input, handler, metadata, context);
          if (input != null) {
                  try {
                      input.close();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
          return handler.toString();
      }

    //内容删除
    @RequestMapping(value = "deleteMsg")
    @ResponseBody
    public void  deleteMes() throws Exception {
        String msgId = QRequest.getString(request,"MSG_PARAM_ID_LIST");
        String[] msgs = msgId.split(",");
        Map<String,Object> map = new HashMap<>();
        for(int i=0;i<msgs.length;i++){
                if(!msgs[i].equals("")&& msgs[i]!= null){
                    map.put("msg_id",msgs[i]);
                   List<Map<String,Object>> res = mesManageDao.selectMes(map);
                    mesManageDao.deleteMsg(msgs[i]);
                    mesManageDao.deleteMesTag(Integer.parseInt(msgs[i]));
                    SolrUtils.deleteSolrInfo(res.get(0).get("msg_content").toString());
                    deleteFile(res.get(0).get("msg_content").toString());
                    deleteFile(res.get(0).get("msg_attachment").toString());
                }else {
                    response.getWriter().write("0");
                    response.getWriter().flush();
                }}
            try {
                response.getWriter().write("1");
                response.getWriter().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        response.getWriter().write("");
    }

    //内容管理-修改
    @RequestMapping(value="editMes",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void editMes(MesManageDO mesManageDO,String[] tagId,HttpServletRequest request,HttpServletResponse response,boolean MERGE) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");
        String title = mesManageDO.getMsg_title();
        Map<String,Object> map = new HashMap<>();
        map.put("msg_id",mesManageDO.getMsg_id());
        String path = createPath();
        File fa = null;
        //附件保存
        if( file!= null  && !file.isEmpty() ){
            deleteFile(mesManageDO.getMsg_attachment());
            String attName = file.getOriginalFilename(); //附件文件名
            fa = createFile(path,attName);
            file.transferTo(fa);
            map.put("msg_attachment",path+attName);
        }else {
            fa = new File(mesManageDO.getMsg_attachment());
        }
        //标签
        String[] tags = tagId[0].split(",");
        if(tagId[0]!=null && tags.length>0 && !tagId[0].equals("") ){
            mesManageDao.deleteMesTag(mesManageDO.getMsg_id());
            for(int i=0;i<tags.length;i++){
                Map<String,Object> maptag = new HashMap<>();
                maptag.put("msg_id",mesManageDO.getMsg_id());
                maptag.put("tag_id",Integer.parseInt(tags[i]));
                mesManageDao.insertMesTag(maptag);
            }}
        List<Map<String,Object>> list = mesManageDao.selectMes(map);
        map.clear();
        map = list.get(0);
        String contentName = mesManageDO.getMsg_title()+".txt"; //发布信息内容文件名
        FileOutputStream fos = null;
        //msg保存
            File fc = createFile(path,contentName);
            contentFile(fos,fc,mesManageDO);
            map.put("msg_content",path+contentName);
        //标题，摘要，内容，附件 文件
            String id = path+contentName;
            String type = SolrInfoConstants.MSG_OBJ_TYPE;
            String content = combinFile(fa,mesManageDO);
            SolrUtils.addSolrInfo(id,content,type,title);
            Map<String,Object> mapone = new HashMap<>();
            List<Map<String,Object>> rules=  mesManageDao.selectRules(mapone);
            int lab = autoFilter(rules,id);
            if(lab ==1){
                deleteFile(path+contentName);
                deleteFile(map.get("msg_attachment").toString());
                response.getWriter().write("2");
                response.getWriter().flush();
                return;
            }
            map.put("appr_state",MesInfoConstants.AUTOMATIC_APPROVAL);
        if(mesManageDO.getMsg_id()!= null && !mesManageDO.getMsg_id().equals("undefined") && !"null".equals(mesManageDO.getMsg_id())){
            map.put("msg_id",mesManageDO.getMsg_id());
        }
        if(mesManageDO.getMsg_attachment()!= null && !mesManageDO.getMsg_attachment().equals("undefined") && !"null".equals(mesManageDO.getMsg_attachment())){
            map.put("msg_attachement",mesManageDO.getMsg_attachment());
        }
        if(mesManageDO.getMsg_title()!= null && !mesManageDO.getMsg_title().equals("undefined") && !"null".equals(mesManageDO.getMsg_title())){
            map.put("msg_title",mesManageDO.getMsg_title());
        }
        if(mesManageDO.getMsg_digest()!= null && !mesManageDO.getMsg_digest().equals("undefined") && !"null".equals(mesManageDO.getMsg_digest())){
            map.put("msg_digest",mesManageDO.getMsg_digest());
        }
        if(mesManageDO.getMsg_class_id()!= null && !mesManageDO.getMsg_class_id().equals("undefined") && !"null".equals(mesManageDO.getMsg_class_id())){
            map.put("msg_class_id",mesManageDO.getMsg_class_id());
        }
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-HH hh:mm:ss");
        map.put("appr_time",format.format(date.getTime()));
        mesManageDao.msgedit(map);
        try {
            response.getWriter().write("1");
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //自动审核
    public static int autoFilter(List<Map<String,Object>> rules,String id) throws Exception {
        PageBounds pageBounds = new PageBounds();
        pageBounds.setPageNo(1);
        pageBounds.setPageSize(20);
        int i=0;
        while( i<rules.size()){
            Map<String,Object> solr = new HashMap<>();
            solr.put("keyword",rules.get(i).get("param_value"));
            SolrQuery query = SolrUtils.getAllSolrQuery(solr, pageBounds,"2");
            SolrDocumentList docList = SolrUtils.getSolrInfoDataByTitle(query);
            if (docList.getNumFound() >0){
                SolrUtils.deleteSolrInfo(id);
                return 1;
            }else {
                i++;
                continue;
            }
        }
        return 0;
    }


    //内容管理修改-获得初始数据
    @RequestMapping(value = "/getEditMes", method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getEditMes() throws Exception {
        String msgId = QRequest.getString(request,"msg_id");
        Map<String,Object> map = new HashMap<>();
        map.put("msg_id",msgId);
        List<Map<String,Object>> obj = mesManageDao.selectMes(map);
        Map<String,Object> a =obj.get(0);
        String msgcontent = a.get("msg_content").toString();
        String msgtext;
        if(msgcontent!= null && !msgcontent.equals("undefined") && !"null".equals(msgcontent)){
            msgtext = LoadContentByPath(msgcontent,3);
            a.put("msgcontent",msgtext);
        }
        return a;
    }

    //内容详细
    @RequestMapping(value = "getMsgDetail", method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getMsgDetail() throws IOException {
        String msgId = QRequest.getString(request,"msg_id");
        Map<String,Object> map = new HashMap<>();
        map.put("msg_id",msgId);
        List<Map<String,Object>> obj = mesManageDao.selectMes(map);
        Map<String,Object> a =obj.get(0);
        String msgcontent = a.get("msg_content").toString();
        String msgtext = null;
        if(msgcontent!= null && !msgcontent.equals("undefined") && !"null".equals(msgcontent)){
            msgtext = LoadContentByPath(msgcontent,3);
        }
            String pubtime = a.get("pub_time").toString();
            String title = a.get("msg_title").toString();
            String msgsource = a.get("msg_src_name").toString();
            String msgclass = "密级："+a.get("msg_class_name").toString();
            String msgapprstate = null;
            String  appstate = null;
            if(a.get("appr_state").toString() != null && !a.get("appr_state").toString().equals("")){
                appstate = a.get("appr_state").toString();
                switch (appstate){
                    case "1":
                        msgapprstate = "人工审核通过";
                        break;
                    case "2":
                        msgapprstate = "人工审核拒绝";
                        break;
                    case "3":
                        msgapprstate = "审核通过";
                        break;
                    case "4":
                        msgapprstate = "审核拒绝";
                        break;
                }}else {
                msgapprstate="审核通过";
            }
            String msgappr = "审核状态："+ msgapprstate;
            String secondrow = pubtime+"     "+msgsource+"           "+msgclass+"       "+msgappr;
            String msgdetail = title+"\r\n"+secondrow+"\r\n"+ msgtext;
            a.put("msg_content",msgdetail);
            return a;
    }

    public static String LoadContentByPath(String path,int index) throws IOException {
        InputStream is = new FileInputStream(path);
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        int i=1;
        while ((line = in.readLine()) != null){
            if(i<index){
                i++;
                continue;
            }
            buffer.append(line);
            buffer.append(System.getProperty("line.separator"));
        }
        if(is!=null){
            is.close();
        }
        return buffer.toString();

    }

   //密级下拉框
    @RequestMapping(value = "getMsgCs")
    @ResponseBody
    public  void getMsgCs(){
       List<Map<String,Object>> msgCs = mesManageDao.selectMsgCs();
      String msgC = getMsgCsJson(msgCs);
        try {
            response.getWriter().write(msgC);
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getMsgCsJson(List<Map<String,Object>> maps){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = null;
        for(Map<String,Object> data:maps){
            jsonObject = new JSONObject();
            jsonObject.put("msg_class_id",data.get("msg_class_id"));
            jsonObject.put("msg_class_name",data.get("msg_class_name"));
            jsonArray.put(jsonObject);
        }
        String jo = jsonArray.toString();
        return  jo;
    }

    //增加规则参数值--选择规则下拉框
    @RequestMapping(value = "getRules")
    @ResponseBody
    public  String getRules(){
        List<Map<String,Object>> list = mesManageDao.selectRule();
        String str = getRuleJson(list);
        try {
            response.getWriter().write(str);
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public String getRuleJson( List<Map<String,Object>> list){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = null;
        for(Map<String,Object> data:list){
            jsonObject = new JSONObject();
            jsonObject.put("rule_id",data.get("rule_id"));
            jsonObject.put("rule_type_name",data.get("rule_type_name"));
            jsonObject.put("param_name",data.get("param_name"));
            jsonArray.put(jsonObject);
        }
        return  jsonArray.toString();
    }

    //自动规则管理，新增规则
    @RequestMapping(value="insertParamValue")
    @ResponseBody
    public void saveParamValue(HttpServletResponse res,MesManageDO contentRuleDo){
           String[] data = null;
           Map<String,Object> map = new HashMap<>();
        if(contentRuleDo.getRuleValue()!=null && !contentRuleDo.getRuleValue().equals("undefined") && !"null".equals(contentRuleDo.getRuleValue())){
            data= contentRuleDo.getRuleValue().split("");
            String rule_id = data[0];
            String param_name = data[1];
            map.put("rule_id",rule_id);
            map.put("param_name",param_name);
        }
        if(contentRuleDo.getParam_value()!=null && !contentRuleDo.getParam_value().equals("undefined") && !"null".equals(contentRuleDo.getParam_value())){
            map.put("param_value",contentRuleDo.getParam_value());
        }
        Integer param_id = mesManageDao.selectMaxParam();
        map.put("param_id",param_id+1);
        mesManageDao.insertParamValue(map);
        try {
            res.getWriter().write("1");
            res.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
      //新增规则参数数据上传
    @RequestMapping(value = "uploadRules",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public DataStore saveRuleValues(HttpServletRequest request, HttpServletResponse response, boolean MERGE){
        try {
            //表头
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            List<Object> header=Arrays.asList("参数值01","参数值02","参数值03","参数值04","参数值05","参数值06");
            InputStream in = null;
            List<Map<String,Object>> listob = null;
            MultipartFile file = multipartRequest.getFile("file");
            long kb = 1024;
            long mb = kb * 1024;
            String type=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            if(!".xls".equals(type)&&!".xlsx".equals(type)){
                return new DataStore().setError("格式错误，请重新上传正确的格式");
            }
            if (file.isEmpty()) {
                return new DataStore().setError("解析失败，请检查文件后重新上传");
            }
            if(file.getSize()>(mb*10))
            {
                return new DataStore().setError("文件超过10M，请重现上传");
            }
               in = file.getInputStream();
            return mesManageService.saveExcel( request,in, file.getOriginalFilename(),QRequest.getString(request,"SYSID"), header,MERGE);
        }
        catch (Exception e){
            return new DataStore().setError("处理失败，请稍后重试");
        }
    }

    //规则参数数据导出
    @RequestMapping(value = "downloadRules")
    public void downloadRuleValues(HttpServletRequest res, HttpServletResponse response){
    try{
    Random random = new Random();
    int num = random.nextInt(100);
    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String format1 = format.format(System.currentTimeMillis());
         String filename = format1+num+"规则数据";
         //表中参数名称
             List<Object> header=Arrays.asList("参数值01","参数值02","参数值03","参数值04","参数值05","参数值06");
            List<Map<String,Object>> dataList = mesManageDao.exportExcel();
            //生成一个表格
          Workbook wb = new HSSFWorkbook();
            for(int i=0;i<dataList.size();i++){
                Map<String,Object> map = dataList.get(i);
                Integer ruleID = Integer.parseInt(map.get("rule_id").toString());
                String ruleType = map.get("rule_type_name").toString();
                String paramName = map.get("param_name").toString();
                //根据规则ID和规则类型生成表格
                Sheet sheet = wb.createSheet(Integer.toString(i));
                //设置表格默认列宽
                sheet.setDefaultColumnWidth((short)20);
                //根据格式赋值
                Row rowone = sheet.createRow(0);
                rowone.createCell(0).setCellValue("规则ID：");
                rowone.createCell(1).setCellValue(ruleID);
                Row rowtwo = sheet.createRow(1);
                rowtwo.createCell(0).setCellValue("规则类型：");
                rowtwo.createCell(1).setCellValue(ruleType);
                Row rowthr = sheet.createRow(2);
                rowthr.createCell(0).setCellValue("规则参数名称：");
                rowthr.createCell(1).setCellValue(paramName);
                Row row = sheet.createRow(3);
                for(int j=0;j<header.size();j++){
                    Cell cell = row.createCell(j);
                    String value = header.get(j).toString();
                    cell.setCellValue(value);
                }
                String[] data =map.get("paramvalue").toString().split(",");
                int index = 4;
                int n = 0;
                while(n<=data.length-1){
                    row = sheet.createRow(index);
                    for(int m=0;m<header.size();m++){
                        Cell cell = row.createCell(m);
                        if (data[0]!=null && !"".equals(data[0]) && n<data.length ){
                            cell.setCellValue(data[n]);
                            n++;
                        }else {
                            break;
                        }}
                    index++;
                }
            }
           OutputStream out = response.getOutputStream();
           response.reset();
           response.setCharacterEncoding("UTF-8");
           response.setContentType("application/msexel");
           response.addHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes("gb2312"),"ISO8859-1")+".xls");
            wb.write(out);
            out.close();
}
        catch (Exception e){
           e.printStackTrace();
        }

    }

    //自动审核规则管理-数据初始化
    @RequestMapping(value="ruleparam")
    @ResponseBody
    public  List<Map<String,Object>> contentlist(HttpServletRequest res,HttpServletResponse response,String paramValue){
        Map<String,Object> map = new HashMap<>();
         if(paramValue!=null && !"".equals(paramValue)){
             String[] data = paramValue.split(",");
             map.put("rule_id",data[0]);
             map.put("rule_type_name",data[1]);
             map.put("param_name",data[2]);
         }
        List<Map<String,Object>> list = mesManageDao.selectRules(map);
       return list;
    }

    @RequestMapping(value="editAppr")
    @ResponseBody
    public String editAppr(MesManageDO mesManageDO,String[] msgids){
        Map<String,Object> map = new HashMap<>();
        if(mesManageDO.getAppr_state()!= null && !mesManageDO.getAppr_state().equals("undefined") && !"null".equals(mesManageDO.getAppr_state())){
            map.put("appr_state",mesManageDO.getAppr_state());
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        map.put("appr_time",new Date());
        if(msgids!=null && msgids.length>0){
            String[] msgs = msgids[0].split(",");
            for (String data:msgs){
                map.put("msg_id",Integer.parseInt(data));
                mesManageDao.mesappr(map);
            }
        }
        try {
            response.getWriter().write("1");
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    //标签类型管理--删除标签类型
    @RequestMapping(value = "deleteParamValue")
    @ResponseBody
    public String deleteParamValue(){
        String str="[]";
        //需要删除的标签id
        String paramId= QRequest.getString(request,"RULE_PARAM_ID_LIST");
        Map<String, Object> p = new HashMap<String,Object>();
        //删除
        String[] paramIdArr = paramId.split(",");
        if (paramIdArr[0] == "") {
            try {
                response.getWriter().write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < paramIdArr.length; i++) {
            p.put("param_id", paramIdArr[i]);
            mesManageDao.deleteParamValue(p);
            p.clear();
        }
        try {
            response.getWriter().write("1");
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //标签管理初始化及查询
   @RequestMapping(value = "getTagData")
   @ResponseBody
    public String getTagMes(HttpServletResponse res ){
        String str="[]";
        //分页参数
       int pageSize= QRequest.getInteger(request,"pageSize",10);
       int pageNo=QRequest.getInteger(request,"pageNo",1);

       //获取表名
       String tableName = getTableName();

       // 排序处理
       String fieldOrder = getFieldOrder();
       String whereStr = getCondition();
       whereStr += getFilterCondition(); // 数据必须受限
       String fieldShow = getFieldShow();

       // 日期格式
       String dateTimeFormat = QRequest.getString(request, "dateTimeFormat",
               "yyyy-MM-dd HH:mm:ss");
       response.setCharacterEncoding("utf-8");
       response.setContentType(QRequest.getResponseType("json")); // 输出JS文件
       // 默认O条数据
       int recordCount = 0;
       Map<String, Object> queryMap = getQueryMap(request, fieldShow,
               tableName, whereStr, fieldOrder);
       PageBounds pager = new PageBounds(pageNo, pageSize);
       List<Map<String, Object>> dt = mesManageDao.selectTagMes(queryMap,pager);
       recordCount = mesManageDao.selectTagCount(queryMap);
       str = new JsonDataGrid(recordCount, dt).toJson(dateTimeFormat);
       try {
           response.getWriter().print(str);
           response.getWriter().flush();
   } catch (IOException e) {
        e.printStackTrace();
    }
      return null;
   }

   //标签管理-删除标签
    @RequestMapping(value = "deleteTag")
    @ResponseBody
    public String deleteTag(){
        String str="[]";
        //需要删除的标签id
        String tagsId= QRequest.getString(request,"TAG_ID_LIST");
        Map<String, Object> p = new HashMap<String,Object>();
        //删除
        String[] tagsIdArr = tagsId.split(",");
        if (tagsIdArr[0] == "") {
            try {
                 response.getWriter().write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < tagsIdArr.length; i++) {
            p.put("tagId", tagsIdArr[i]);
            mesManageDao.deleteTags(p);
            p.clear();
        }
        try {
            response.getWriter().write("1");
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //标签类型管理--删除标签类型
    @RequestMapping(value = "deleteTagType")
    @ResponseBody
    public String deleteTagType(){
        String str="[]";
        //需要删除的标签id
        String tagsTypeId= QRequest.getString(request,"TAG_TYPE_ID_LIST");
        Map<String, Object> p = new HashMap<String,Object>();
        //删除
        String[] tagsIdArr = tagsTypeId.split(",");
        if (tagsIdArr[0] == "") {
            try {
                response.getWriter().write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < tagsIdArr.length; i++) {
            p.put("tagTypeId", tagsIdArr[i]);
            mesManageDao.deleteTagsType(p);
            p.clear();
        }
        try {
            response.getWriter().write("1");
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

  //标签管理-编辑标签
    @RequestMapping(value="editTag")
    @ResponseBody
  public String editTag(TagManageDo tagManageDo){
        Map<String,Object> map = new HashMap<>();
        if(tagManageDo.getTagId()!= null && !tagManageDo.getTagId().equals("undefined") && !"null".equals(tagManageDo.getTagId())){
            map.put("tagId",tagManageDo.getTagId());
        }
        if(tagManageDo.getTagText()!= null && !tagManageDo.getTagText().equals("undefined") && !"null".equals(tagManageDo.getTagText())){
            map.put("tagText",tagManageDo.getTagText());
        }
        if(tagManageDo.getTagTypeId()!= null && !tagManageDo.getTagTypeId().equals("undefined") && !"null".equals(tagManageDo.getTagTypeId())){
            map.put("tagTypeId",tagManageDo.getTagTypeId());
        }
        mesManageDao.update(map);
        try {
            response.getWriter().write("1");
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
       return  null;
    }

    //标签类型管理--编辑标签类型
    @RequestMapping(value="editTagType")
    @ResponseBody
    public String tagType(TagManageDo tagManageDo){
        Map<String,Object> map = new HashMap<>();
        if(tagManageDo.getSuperTypeId()!= null && !tagManageDo.getSuperTypeId().equals("undefined") && !"null".equals(tagManageDo.getSuperTypeId())){
            map.put("superTypeId",tagManageDo.getSuperTypeId());
        }
        if(tagManageDo.getTagTypeName()!= null && !tagManageDo.getTagTypeName().equals("undefined") && !"null".equals(tagManageDo.getTagTypeName())){
            map.put("tagTypeName",tagManageDo.getTagTypeName());
        }
        if(tagManageDo.getTagTypeId()!= null && !tagManageDo.getTagTypeId().equals("undefined") && !"null".equals(tagManageDo.getTagTypeId())){
            map.put("tagTypeId",tagManageDo.getTagTypeId());
        }
        mesManageDao.updateTagType(map);
        try {
            response.getWriter().write("1");
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    //标签管理--新增标签
    @RequestMapping(value="insertTag")
    @ResponseBody
    public String insertTag(TagManageDo tagManageDo){
        Map<String,Object> map = new HashMap<>();
        if(tagManageDo.getTagText()!= null && !tagManageDo.getTagText().equals("undefined") && !"null".equals(tagManageDo.getTagText())){
            map.put("tagText",tagManageDo.getTagText());
        }
        if(tagManageDo.getTagTypeId()!= null && !tagManageDo.getTagTypeId().equals("undefined") && !"null".equals(tagManageDo.getTagTypeId())){
            map.put("tagTypeId",tagManageDo.getTagTypeId());
        }
       int tagId = mesManageDao.selectMaxTagId();
        map.put("tagId",tagId+1);
        mesManageDao.insertTag(map);
        try {
            response.getWriter().write("1");
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    //标签类型管理--新增标签类型
    @RequestMapping(value="insertTagType")
    @ResponseBody
    public String insertTagType(TagManageDo tagManageDo){
        Map<String,Object> map = new HashMap<>();
        if(tagManageDo.getTagTypeName()!= null && !tagManageDo.getTagTypeName().equals("undefined") && !"null".equals(tagManageDo.getTagTypeName())){
            map.put("tagTypeName",tagManageDo.getTagTypeName());
        }
        if(tagManageDo.getSuperTypeId()!= null && !tagManageDo.getSuperTypeId().equals("undefined") && !"null".equals(tagManageDo.getSuperTypeId())){
            map.put("superTypeId",tagManageDo.getSuperTypeId());
        }
        int tagId = mesManageDao.selectMaxTypeTagId();
        map.put("tagTypeId",tagId+1);
        mesManageDao.insertTagType(map);
        try {
            response.getWriter().write("1");
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    //弹出框--上级标签
    @RequestMapping(value = "/getSuperTag")
    @ResponseBody
    public String superTags(HttpServletResponse res) {
        try{
            String str="";
            int pageSize = QRequest.getInteger(request, "pageSize", 10); // 获取datagrid传来的行数
            // //每页显示条数
            int pageNo = QRequest.getInteger(request, "page", 1); // 获取datagrid传来的页码

            // 表名
            String tableName = getTableName();
            // 主键
            String primaryKey = getPrimaryKey();
            // 排序处理
            String fieldOrder = getFieldOrder();
            String whereStr = getCondition();
            whereStr += getFilterCondition(); // 数据必须受限
            String fieldShow = getFieldShow();

            // 日期格式
            String dateTimeFormat = QRequest.getString(request, "dateTimeFormat",
                    "yyyy-MM-dd HH:mm:ss");
            response.setCharacterEncoding("utf-8");
            response.setContentType(QRequest.getResponseType("json")); // 输出JS文件
            // 默认O条数据
            int recordCount = 0;
            Map<String, Object> queryMap = getQueryMap(request, fieldShow,
                    tableName, whereStr, fieldOrder);
            PageBounds pager = new PageBounds(pageNo, pageSize);
            List<Map<String, Object>> dt = mesManageDao.selectSuperTag(queryMap,pager);
                recordCount = mesManageDao.selectTagCount(queryMap);//查询总条数
            str = new JsonDataGrid(recordCount, dt).toJson(dateTimeFormat);
            response.getWriter().print(str);
            response.getWriter().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //下拉框--标签类型
    @RequestMapping(value = "/getTag")
    @ResponseBody
    public String getTag(HttpServletResponse res) {
        try{
            String str="";
            int pageSize = QRequest.getInteger(request, "pageSize", 10); // 获取datagrid传来的行数
            // //每页显示条数
            int pageNo = QRequest.getInteger(request, "page", 1); // 获取datagrid传来的页码
            // 表名
            String tableName = getTableName();
            // 主键
            String primaryKey = getPrimaryKey();
            // 排序处理
            String fieldOrder = getFieldOrder();
            String whereStr = getCondition();
            whereStr += getFilterCondition(); // 数据必须受限
            String fieldShow = getFieldShow();
            // 日期格式
            String dateTimeFormat = QRequest.getString(request, "dateTimeFormat",
                    "yyyy-MM-dd HH:mm:ss");
            response.setCharacterEncoding("utf-8");
            response.setContentType(QRequest.getResponseType("json")); // 输出JS文件
            // 默认O条数据
            int recordCount = 0;
            Map<String, Object> queryMap = getQueryMap(request, fieldShow,
                    tableName, whereStr, fieldOrder);
            PageBounds pager = new PageBounds(pageNo, pageSize);
            List<Map<String, Object>> dt = mesManageDao.selectTagType(queryMap,pager);
            recordCount = mesManageDao.countTagType(queryMap);//查询总条数
            str = new JsonDataGrid(recordCount, dt).toJson(dateTimeFormat);
            response.getWriter().print(str);
            response.getWriter().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping(value = "/getTagObj", method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getTagObj() throws Exception {

        String tagId = QRequest.getString(request,"tagId");
        Map<String,Object> map = new HashMap<>();
        map.put("tagId",tagId);
        Map<String,Object> obj = getBaseService().selectMap(map);
        return obj;
    }

    //
    @RequestMapping(value = "/getEditTag", method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> editTagType() throws Exception {

        String tagTypeId = QRequest.getString(request,"tagTypeId");
        Map<String,Object> map = new HashMap<>();
        map.put("tagTypeId",tagTypeId);
        List<Map<String,Object>> obj = mesManageDao.selectTagType(map);
        Map<String,Object> a =obj.get(0);
        return a;
    }

    //得到标签类型下数据
    @RequestMapping(value = "/getTagType")
    public void tagType(HttpServletResponse res,String tagTypeId) {
        HashMap<String,Object> map = new HashMap<>();
        if(tagTypeId!=null && !tagTypeId.equals("undefined") && !"null".equals(tagTypeId)){
            map.put("tagTypeId",tagTypeId);
        }
        List<Map<String,Object>> tagType = mesManageDao.selectTagType(map);
        String json = getTagJsonString(tagType);
        try {
            res.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getTagJsonString(List<Map<String,Object>> map) {
        JSONArray json = new JSONArray();
        JSONObject jo = null;
        for (Map roleMap:map) {
            jo = new JSONObject();
            jo.put("tagTypeId",roleMap.get("tag_type_id"));
            jo.put("tagTypeName",roleMap.get("tag_type_name"));
            json.put(jo);
        }
        return json.toString();
    }

    //内容管理查询--内容标签获取
    @RequestMapping("listAllTag")
    @ResponseBody
    public List<Map<String,Object>> listAllTag(){
        List<Map<String,Object>> list = mesManageDao.selectTagTree();
        Map<String, Object> map = list.get(0);
        int maxTagTypeId = Integer.parseInt(map.get("ma").toString());
        List<Map<String,Object>> data = mesManageDao.selectTagsTree();
        List<Map<String,Object>> result = new ArrayList<>();
        for (int i=0;i<data.size();i++){
            if(i<maxTagTypeId){
                result.add(data.get(i));
                continue;
            }
            Map<String,Object> map1 = data.get(i);
            Integer id = ++maxTagTypeId;
            map1.put("tag_type_id",id);
            map1.put("super_type_id",data.get(i).get("super_type_id"));
            map1.put("tag_type_name",data.get(i).get("tag_type_name"));
            result.add(map1);
        }
        return result;
    }

}
