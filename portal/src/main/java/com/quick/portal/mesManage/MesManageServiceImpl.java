package com.quick.portal.mesManage;

import com.quick.core.base.ISysBaseDao;
import com.quick.core.base.SysBaseService;
import com.quick.core.base.model.DataStore;
import com.quick.core.base.model.PageBounds;
import com.quick.core.util.common.QCookie;
import com.quick.core.util.common.QRequest;
import com.quick.portal.search.infomng.SolrInfoConstants;
import com.quick.portal.search.infomng.SolrUtils;
import com.quick.portal.security.authority.metric.PropertiesUtil;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.quick.core.util.common.ExcelUtil.getCellValue;
import static com.quick.core.util.common.ExcelUtil.getWorkbook;

/**
 * Created by GaoPh on 2018/5/3.
 */
@Service("mesManageService")
public class MesManageServiceImpl extends SysBaseService<MesManageDO> implements MesManageService {

    public MesManageServiceImpl() {
        BaseTable = "tags";
        BaseComment = "tags";
        PrimaryKey = "tag_id";
        NameKey = "tag_text";
    }

    @Autowired
    private MesManageDao mesManageDao;

    @Override
    public ISysBaseDao getDao(){
        return mesManageDao;
    }

    @Override
    public DataStore saveExcel(HttpServletRequest request, InputStream in, String fileName, String sysid, List<Object> header, boolean MERGE) {
        try {
            //excel转换成列表
            List<Map<String, Object> > list = getListByExcel(in, fileName, header);
            if(list.size()==0) //文件为空
                return ActionMsg.setError("解析失败，请检查文件内容是否重复！");
            int countParam = mesManageDao.countParam();
            int paramId ;
            if (countParam == 0){
                paramId =0;
            }else{
                paramId =  mesManageDao.selectMaxParam();
            }
            for(Map<String, Object> data:list){
                paramId++;
               data.put("param_id",paramId);
               mesManageDao.insertParamValue(data);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            return ActionMsg.setError("处理失败，请稍后重试");
        }
        finally {
            try {
                if (in != null)
                    in.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return ActionMsg.setOk("处理完成");
    }

    @Override
    @Transactional
    public void publishMes(MesManageDO mesManageDO, String[] tagId, HttpServletRequest request, HttpServletResponse response, boolean MERGE) throws Exception {
        String tyname = "mesPublish";
        String path = createPath(request,tyname);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");
        String title = mesManageDO.getMsg_title();
        String attName = null;
        File fa =null;
        if(mesManageDO.getMsg_class_name()!= null && !mesManageDO.getMsg_class_name().equals("undefined") && !"null".equals(mesManageDO.getMsg_class_name())){
            String[] str = mesManageDO.getMsg_class_name().split(",");
            mesManageDO.setMsg_class_name(str[0]);
            mesManageDO.setMsg_class_id(Integer.parseInt(str[1]));
        }
        if (file!=null && !file.isEmpty()){
            attName = file.getOriginalFilename(); //附件文件名
            try {
                fa = createFile(path+"attachment/",attName);
                file.transferTo(fa);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mesManageDO.setMsg_attachment(path+"attachment/"+attName);//上传附件存放路径
        }
        String contentName = title+".txt"; //发布信息内容文件名
        String id = path+"content/"+contentName;
        File fc = null;
        try {
            fc = createFile(path+"content/",contentName);
            FileOutputStream fos = null;
            mesManageDO.setMsg_src_name("平台内部发布");
            contentFile(fos,fc,mesManageDO);
        } catch (IOException e) {
            e.printStackTrace();
        }
            mesManageDO.setMsg_content(id); //上传信息内容存放路径
            mesManageDO.setMsg_src_id(MesInfoConstants.PLATFORM_SOURCE_ID);
            mesManageDO.setMsg_type_id(MesInfoConstants.PLATFORM_MES_TYPE);
            Calendar date = Calendar.getInstance();
            Date date1 =date.getTime();
            mesManageDO.setPub_time(date1);
            mesManageDO.setAppr_time(date1);
            Map<String,Object> map = new HashMap<>();
            List<Map<String,Object>> rules=  mesManageDao.selectRules(map);
        String attach = mesManageDO.getMsg_attachment();
        int lab = 0;
            if(rules.size()>0){
                String content = combinFile(fa,mesManageDO);
                String type = SolrInfoConstants.MSG_OBJ_TYPE;
                SolrUtils.addSolrInfo(id,content,type,title,attach);
                lab = autoFilter(rules,id);
            }
            if(lab ==1){
                deleteFile(id);
                deleteFile(path+"attachment/"+attName);
                SolrUtils.deleteSolrInfo(id);
                response.getWriter().write("2");
                response.getWriter().flush();
            }else{
                mesManageDO.setAppr_state(MesInfoConstants.AUTOMATIC_APPROVAL);
                String userId = QCookie.getValue(request,"ids");
                mesManageDO.setPub_user_id(Integer.parseInt(userId));
                mesManageDao.insert(mesManageDO);
                map.clear();
               Integer result = mesManageDao.selectMsgId(map);
                String msgId = result.toString();
                String[] tags = tagId[0].split(",");
                map.clear();
                for(int i=0;i<tags.length;i++){
                    map = new HashMap<>();
                    map.put("msg_id",msgId);
                    map.put("tag_id",Integer.parseInt(tags[i]));
                    mesManageDao.insertMesTag(map);
                }
                response.getWriter().write("1");
                response.getWriter().flush();
            }
    }

    @Override
    @Transactional
    public void editMes(MesManageDO mesManageDO, String[] tagId, HttpServletRequest request, HttpServletResponse response, boolean MERGE) throws Exception {
        String tyname = "mesPublish";
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");
//        String msg_attachment = multipartRequest.getParameter("msg_attachment");
        String msg_attachment = "";
        if(mesManageDO.getMsg_attachment()!= null && !mesManageDO.getMsg_attachment().equals("undefined") && !"null".equals(mesManageDO.getMsg_attachment())){
            String[]  msgatt = mesManageDO.getMsg_attachment().split(",");
            if (msgatt.length>1){
                msg_attachment = msgatt[1];
            }
        }
        String title = null;
        String path = createPath(request,tyname);
        String newpath = null;
        String newattachment =null;
        String id = null;
        //将源文件保存
        Map<String,Object> map = new HashMap<>();
        if(mesManageDO.getMsg_content()!= null && !mesManageDO.getMsg_content().equals("undefined") && !"null".equals(mesManageDO.getMsg_content())){
            id = mesManageDO.getMsg_content();
            newpath = path+"content/"+"ori"+mesManageDO.getMsg_title();
            copyFile(id,newpath);
            map.put("msg_content",id);
        }
        if(mesManageDO.getMsg_id()!= null && !mesManageDO.getMsg_id().equals("undefined") && !"null".equals(mesManageDO.getMsg_id())){
            map.put("msg_id",mesManageDO.getMsg_id());
        }
        if(msg_attachment!= null && !msg_attachment.equals("") && !"null".equals(msg_attachment)){
            int index = msg_attachment.lastIndexOf(".");
            String att = msg_attachment.substring(0,index);
            String at = msg_attachment.substring(index);
            newattachment = att+"edit"+at;
        }
        if(mesManageDO.getMsg_title()!= null && !mesManageDO.getMsg_title().equals("undefined") && !"null".equals(mesManageDO.getMsg_title())){
            map.put("msg_title",mesManageDO.getMsg_title());
            title = mesManageDO.getMsg_title();
        }
        if(mesManageDO.getMsg_digest()!= null && !mesManageDO.getMsg_digest().equals("undefined") && !"null".equals(mesManageDO.getMsg_digest())){
            map.put("msg_digest",mesManageDO.getMsg_digest());
        }
        if(mesManageDO.getMsg_class_id()!= null && !mesManageDO.getMsg_class_id().equals("undefined") && !"null".equals(mesManageDO.getMsg_class_id())){
            map.put("msg_class_id",mesManageDO.getMsg_class_id());
        }
        File fa = null;
        //附件保存
        if( file!= null  && !file.isEmpty()  ){
            String attName = file.getOriginalFilename(); //附件文件名
            fa = createFile(path+"attachment/",attName);
            file.transferTo(fa);
            if (attName != null && !attName.equals("")) {
                title = attName.split("\\.")[0];
            }
            map.put("msg_attachment",path+"attachment/"+attName);
        }else if(newattachment!= null && !newattachment.equals(" ") && !"null".equals(newattachment)){
            fa = new File(msg_attachment);
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
            }
        }
        String contentName = mesManageDO.getMsg_title()+".txt"; //发布信息内容文件名
        FileOutputStream fos = null;
        //msg保存
        File fc = createFile(path+"content/",contentName);
        contentFile(fos,fc,mesManageDO);
        //标题，摘要，内容，附件 文件

        Object attpath = map.get("msg_attachment");
        String attach = "";
        int lab = 0;
        if(attpath!= null && !attpath.equals(" ") && !"null".equals(attpath)&& msg_attachment!=null){
            attach = attpath.toString();
        }else{
            attach = msg_attachment;
        }
        Map<String,Object> mapone = new HashMap<>();
        List<Map<String,Object>> rules=  mesManageDao.selectRules(mapone);
        if(rules.size()>0){
            String type = SolrInfoConstants.MSG_OBJ_TYPE;
            String content = combinFile(fa,mesManageDO);
            SolrUtils.addSolrInfo(id,content,type,title,attach);
            lab  = autoFilter(rules,id);
        }
        if(lab ==1){
            deleteFile(id);
            if(attpath!= null && !attpath.equals(" ") && !"null".equals(attpath)){
                deleteFile(attpath.toString());
            }
            copyFile(newpath,id);
            deleteFile(newpath);
            response.getWriter().write("2");
            response.getWriter().flush();
            return;
        }else {
            if(attpath == null || attpath.equals(" ") || "null".equals(attpath)){
                map.put("msg_attachment",msg_attachment);
            }
            if(attpath!= null && !attpath.equals(" ") && !"null".equals(attpath)&& msg_attachment!=null){
                deleteFile(msg_attachment);
            }
            map.put("appr_state",MesInfoConstants.AUTOMATIC_APPROVAL);
            deleteFile(newpath);
           Calendar calendar = Calendar.getInstance();
           Date date = calendar.getTime();
            String useId = QCookie.getValue(request,"ids");
            map.put("pub_user_id",Integer.parseInt(useId));
            map.put("pub_time",date);
            map.put("appr_time",date);
            mesManageDao.msgedit(map);
        }

        try {
            response.getWriter().write("1");
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void addDatum(MesManageDO mesManageDO, String keywords, HttpServletRequest request, HttpServletResponse response, boolean MERGE) throws IOException, TikaException, SAXException {
        Integer msgId =null;
        String tyname = "material";
            MultipartHttpServletRequest re = (MultipartHttpServletRequest) request;
            Map<String, Object> map = new HashMap<>();
            MultipartFile file = re.getFile("file");
            String path = createPath(request,tyname) ;
            String name = file.getOriginalFilename();
            String title = null;
            if (name != null && !name.equals("")) {
                title = name.split("\\.")[0];
            }
            String content = path+name;
            File uploadFile = createFile(path, name);
            file.transferTo(uploadFile);
            String datacontent = parseFile(uploadFile);
            String[] key = null;
            if (keywords != null && !keywords.equals("")) {
                datacontent=datacontent+keywords;
                key = keywords.split(" ");
            }
            String type = SolrInfoConstants.DATA_OBJ_TYPE;
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            mesManageDO.setPub_time(date);
            mesManageDO.setAppr_time(date);
            mesManageDO.setMsg_title(title);
            mesManageDO.setMsg_content(content);
            mesManageDO.setMsg_src_id(0);
            if (mesManageDO.getMsg_type_id() == null ||mesManageDO.getMsg_type_id().equals("")) {
                mesManageDO.setMsg_type_id(mesManageDO.getSup_msg_type_id());
            }
            mesManageDao.insert(mesManageDO);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           Map<String,Object> mas = new HashMap<>();
        Integer result = mesManageDao.selectMsgId(mas);
            msgId = result;
            int tagId = mesManageDao.selectMaxTagId();
            tagFilter(key,map,tagId,msgId);
            String attach = "";
             SolrUtils.addSolrInfo(content,datacontent,type,title,attach);
            response.getWriter().write("1");
            response.getWriter().flush();
    }

    @Override
    public void deleteMes(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String msgId = QRequest.getString(request,"MSG_PARAM_ID_LIST");
        String[] msgs = msgId.split(",");
        Map<String,Object> map = new HashMap<>();
        for(int i=0;i<msgs.length;i++){
            if(!msgs[i].equals("")&& msgs[i]!= null){
                map.put("msg_id",msgs[i]);
                List<Map<String,Object>> res = mesManageDao.selectMes(map);
                mesManageDao.deleteMsg(msgs[i]);
                mesManageDao.deleteMesTag(Integer.parseInt(msgs[i]));
                String msgc = res.get(0).get("msg_content").toString();
                Object msga = res.get(0).get("msg_attachment");
                if(msgc!=null && !msgc.equals("") && !msgc.equals("undefined")){
                    SolrUtils.deleteSolrInfo(msgc);
                    deleteFile(msgc);
                }
                if(msga!=null && !msga.equals("") && !msga.equals("undefined")){
                    String ms = msga.toString();
                    deleteFile(ms);
                }
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

    }

    @Override
    @Transactional
    public void editDatum(MesManageDO mesManageDO, String keywords, HttpServletRequest request, HttpServletResponse response, boolean MERGE) throws Exception {
        String tyname = "material";
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");
        String path = createPath(request,tyname) ;
        Integer id = null;
        String msg_content = null;
        List<Integer> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        String title = null;
        if(mesManageDO.getMsg_id()!= null && !mesManageDO.getMsg_id().equals("undefined") && !"null".equals(mesManageDO.getMsg_id())){
            id = mesManageDO.getMsg_id();
            map.put("msg_id",id);
            list.add(id);
        }
        if(mesManageDO.getMsg_title()!= null && !mesManageDO.getMsg_title().equals("undefined") && !"null".equals(mesManageDO.getMsg_title())){
            map.put("msg_title",mesManageDO.getMsg_title());
            title = mesManageDO.getMsg_title();
        }
        if(mesManageDO.getPub_time()!= null && !mesManageDO.getPub_time().equals("undefined") && !"null".equals(mesManageDO.getPub_time())){
            map.put("pub_time",mesManageDO.getPub_time());
        }

        if(mesManageDO.getMsg_type_id()!= null && !mesManageDO.getMsg_type_id().equals("undefined") && !"null".equals(mesManageDO.getMsg_type_id())){
            map.put("msg_type_id",mesManageDO.getMsg_type_id());
        }

        if(mesManageDO.getMsg_type_id()== null || mesManageDO.getMsg_type_id().equals("undefined")){
            map.put("msg_type_id",mesManageDO.getSup_msg_type_id());
        }
        if(mesManageDO.getMsg_content()!= null && !mesManageDO.getMsg_content().equals("undefined") && !"null".equals(mesManageDO.getMsg_content())){
            String[]  msgatt = mesManageDO.getMsg_content().split(",");
            if (msgatt.length>1){
                msg_content = msgatt[1];
                map.put("msg_content",msg_content);
            }
        }
        String content = "";
        String datacontent = "";
        String type = SolrInfoConstants.DATA_OBJ_TYPE;

        String[] key  = null;
        if (keywords != null && !keywords.equals("")) {
            datacontent= keywords;
            key = keywords.split(" ");
        }
        File fa = null;
        String attach = "";
        //附件保存
        if( file!= null  && !file.isEmpty()  ){
            String attName = file.getOriginalFilename(); //附件文件名
            fa = createFile(path,attName);
            file.transferTo(fa);
            datacontent= parseFile(fa)+datacontent;
            content = path+attName;
            if (attName != null && !attName.equals("")) {
                title = attName.split("\\.")[0];
            }
            SolrUtils.addSolrInfo(content,datacontent,type,title,attach);
            map.put("msg_content",content);
            map.put("msg_title",title);
        }else {
            File fi = new File(msg_content);
            datacontent = parseFile(fi)+datacontent;
            SolrUtils.addSolrInfo(msg_content,datacontent,type,title,attach);
        }
        Date date = new Date();
        String useId = QCookie.getValue(request,"ids");
        map.put("pub_user_id",Integer.parseInt(useId));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-HH hh:mm:ss");
        map.put("appr_time",format.format(date.getTime()));
        mesManageDao.msgedit(map);
        List<Integer> tagid = mesManageDao.selectMesByTag(list);
        if(tagid.size()>0){
            mesManageDao.deleteTags(tagid);
            mesManageDao.deleteMesTag(id);
        }

        int tagId = mesManageDao.selectMaxTagId();
        tagFilter(key,map,tagId,id);
        try {
            response.getWriter().write("1");
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delDatum(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Integer> list = new ArrayList<>();
        Integer msgId = QRequest.getInteger(request,"msg_id");
        String content = QRequest.getString(request,"msg_content");
        list.add(msgId);
        List<Integer> tagid = mesManageDao.selectMesByTag(list);
        if(tagid.size()>0){
            mesManageDao.deleteTags(tagid);
            mesManageDao.deleteMesTag(msgId);
        }
        mesManageDao.deleteMsg(msgId.toString());
        if(content!=null && !content.equals("") && !content.equals("undefined")){
            deleteFile(content);
            SolrUtils.deleteSolrInfo(content);
        }
        try {
            response.getWriter().write("1");
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void insertSupClass(HttpServletResponse res, MesManageDO mesManageDO) throws IOException {
        Map<String,Object> map = new HashMap<>();
        if(mesManageDO.getSup_msg_type_id()!=null ){
            map.put("sup_msg_type_id",mesManageDO.getSup_msg_type_id());
        }
        if(mesManageDO.getMsg_type_name()!=null && !mesManageDO.getMsg_type_name().equals("")){
            map.put("msg_type_name",mesManageDO.getMsg_type_name());
        }
        mesManageDao.insertMsgTy(map);
        res.getWriter().write("1");
        res.getWriter().flush();
    }

    @Override
    @Transactional
    public void editSubClass(HttpServletResponse response, MesManageDO mesManageDO) throws Exception {
        Map<String,Object> map = new HashMap<>();
        if(mesManageDO.getSup_msg_type_id()!=null ){
            map.put("sup_msg_type_id",mesManageDO.getSup_msg_type_id());
        }
        if(mesManageDO.getMsg_type_name()!=null && !mesManageDO.getMsg_type_name().equals("")){
            map.put("msg_type_name",mesManageDO.getMsg_type_name());
        }
        if(mesManageDO.getMsg_type_id()!=null && !mesManageDO.getMsg_type_id().equals("undefined")){
            map.put("msg_type_id",mesManageDO.getMsg_type_id());
        }
        mesManageDao.updateMsgTy(map);
        response.getWriter().write("1");
        response.getWriter().flush();
    }

    //判断关键词插入
    public  void tagFilter(String[] tags,Map<String,Object> map,Integer tagId,Integer msgId ){
        int i =0;
        for(String data:tags){
            if(data!=null && !data.equals("")){
                map = new HashMap<>();
                map.put("tagId",tagId+1+i);
                map.put("tagText",data);
                mesManageDao.insertTag(map);
                map.put("tag_id",tagId+1+i);
                map.put("msg_id", msgId);
                mesManageDao.insertMesTag(map);
                i++;
            }
        }
    }
    public  String createPath(HttpServletRequest request,  String tyname){
        String baseDir = PropertiesUtil.getPropery("file.dir");
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        String month = String.valueOf(date.get(Calendar.MONTH)+1);
        String day = String.valueOf(date.get(Calendar.DAY_OF_MONTH));
        int mo = Integer.parseInt(month);
        if(mo<=9){
            month = "0"+month;
        }
        String path = baseDir+"/"+tyname+"/"+year+month+day+"/";
        return  path;
    }

    //删除指定路径下的文件
    public static  void deleteFile(String path){
        File file = new File(path);
        if(file.exists() && file.isFile()) {
            file.delete();
        }

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
        String secondrow = "       "+currenttime+"     "+msgsource+"           "+msgclass+"       "+msgappr;
        StringBuffer buffer = new StringBuffer();
        buffer.append("                                   "+mesManageDO.getMsg_title());
        buffer.append(System.getProperty("line.separator"));
        buffer.append(secondrow);
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    摘要："+mesManageDO.getMsg_digest());
        buffer.append(System.getProperty("line.separator"));
        buffer.append(mesManageDO.getMsgcontent());
        fos = new FileOutputStream(file);
        fos.write(buffer.toString().getBytes());
        if(fos != null){
            fos.close();
        }
        fos.flush();
    }

    //将附件和信息各内容合并生成新的内容
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
        String content = mesManageDO.getMsg_title()+  mesManageDO.getMsg_digest()+mesManageDO.getMsgcontent()+nei;
        return content;
    }

    //将一个文件复制到另一个目录下
    public  static  void  copyFile(String oldpath,String newpath){
        try
        {
            File fOldFile = new File(oldpath);
            if (fOldFile.exists())
            {
                int bytesum = 0;
                int byteread = 0;
                InputStream inputStream = new FileInputStream(fOldFile);
                FileOutputStream fileOutputStream = new FileOutputStream(newpath);
                byte[] buffer = new byte[1444];
                while ( (byteread = inputStream.read(buffer)) != -1)
                {
                    bytesum += byteread; //这一行是记录文件大小的，可以删去
                    fileOutputStream.write(buffer, 0, byteread);//三个参数，第一个参数是写的内容，
                    //第二个参数是从什么地方开始写，第三个参数是需要写的大小
                }
                inputStream.close();
                fileOutputStream.close();
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("复制单个文件出错");
            e.printStackTrace();
        }
    }



    public List<Map<String, Object>> getListByExcel(InputStream in, String fileName,List<Object> header) throws Exception{
        List<Map<String, Object>> result = new ArrayList<>();
        //创建Excel工作薄
        Workbook work = getWorkbook(in,fileName);
        if(null == work){
            throw new Exception("上传的工作簿为空！");
        }
        for(int a=0;a<work.getNumberOfSheets();a++) {
            Sheet sheet = work.getSheetAt(a);
            if (sheet == null) {
                throw new Exception("解析失败，请检查文件后重新上传");
            }
            int firstRow = sheet.getFirstRowNum();
            int lastRow = sheet.getLastRowNum();
            Row row = null;
            Cell cell = null;
            if(sheet.getRow(firstRow)==null){
                continue;
            }
            Cell cellone = sheet.getRow(firstRow).getCell(0);
            String ruleID = getCellValue(cellone).toString();
            Cell cello = sheet.getRow(firstRow).getCell(1);
            Integer ruleIDValue = Integer.parseInt(getCellValue(cello).toString());
            Cell celltwo = sheet.getRow(firstRow + 1).getCell(0);
            String ruleParamName = getCellValue(celltwo).toString();
            Cell cellt = sheet.getRow(firstRow + 1).getCell(1);
            String ruleParamValue = getCellValue(cellt).toString();

            if (!ruleID.equals("规则ID：") || !ruleParamName.equals("规则参数名：")) {
                throw new Exception("模版错误，请检查文件后重新上传");
            }
            Map<String, Object> map = new HashMap<>();
            map.put("rule_id", ruleIDValue);
            map.put("param_name", ruleParamValue);
             List<Map<String,Object>> list = mesManageDao.selectRules(map);
            for (int j = 0; j < header.size(); j++) {
                cell = sheet.getRow(firstRow + 2).getCell(j);
                Object value = getCellValue(cell);
                if (!value.toString().equals(header.get(j))) {
                    throw new Exception("模版错误，请检查文件后重新上传");
                }
            }
            try {
                //遍历当前sheet中的所有行
                for (int i = firstRow + 3; i < lastRow + 1; i++) {
                    row = sheet.getRow(i);
                    if (row == null) {
                        continue;
                    }
                    if (row.getFirstCellNum() != -1)
                        map.clear();
                        for (int y = row.getFirstCellNum(); y < header.size(); y++) {
                             map = new LinkedHashMap();
                            map.put("rule_id", ruleIDValue);
                            map.put("param_name", ruleParamValue);
                            cell = row.getCell(y);
                            Object value;
                            if (cell != null && !"".equals(cell)) {
                                value = cell == null ? "" : getCellValue(cell);
                            } else {
                                break;
                            }
                            for(int d=0;d<list.size();d++){
                                Map<String,Object> da = list.get(d);
                                String va = da.get("param_value").toString();
                                if(value.equals(va) ){
                                    break;
                                }else if(d==list.size()-1){
                                    map.put("param_value", value);
                                }
                            }
                            if(result.size()!=0 && map.get("param_value")!=null && !"".equals(map.get("param_value"))){
                                for(int t=0;t<result.size();t++){
                                    Map<String,Object> da = result.get(t);
                                    String va = da.get("param_value").toString();
                                    if(value.equals(va) ){
                                        break;
                                    }else if(t==result.size()-1){
                                        result.add(map);
                                    }
                                }
                            }else if(result.size()==0 && map.get("param_value")!=null && !"".equals(map.get("param_value"))){
                                result.add(map);
                            }else if(list.size() == 0){
                                map.put("param_value", value);
                                result.add(map);
                            }
                        }
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("解析失败，请检查文件后重新上传");
            }
        }
        return result;
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
            boolean bool = SolrUtils.getSolrDataByRule(docList,id);
            if (bool){
                SolrUtils.deleteSolrInfo(id);
                return 1;
            }else {
                i++;
                continue;
            }
        }
        return 0;
    }

}
