package com.quick.core.util.common;


import com.quick.core.base.model.DataStore;
import org.springframework.util.FileCopyUtils;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QUpload {

    public static DataStore saveImage(HttpServletRequest request, String savePath, String saveFileName, String base64Data){
        FileOutputStream outputStream=null;
        DataStore ds = new DataStore().setOk("保存成功");
        try{
            base64Data=base64Data.replaceAll(" ", "+");//关键的语句，当其不将空格转化为"+"时，其解析为png图片时，会出现图片内容无法正确显示的情况
            byte[] bytes = Base64.decodeBase64(base64Data.getBytes());
            File dirPath = new File(request.getSession().getServletContext().getRealPath("") + savePath);
            if (!dirPath.exists()) {
                dirPath.mkdirs();
            }
            File uploadFile = new File(dirPath+"\\"+ saveFileName);
            OutputStream out = new FileOutputStream(uploadFile);
            out.write(bytes, 0, bytes.length);
            out.flush();
            out.close();
            return ds;
        }catch(Exception e){
            e.printStackTrace();
            return ds.setError(e.getMessage());
        }finally{
            /*if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
        }
    }
    public static DataStore saveFile(DefaultMultipartHttpServletRequest request, String savePath, String fixName){

        DataStore ds = new DataStore().setOk("保存成功");
        List<String> names = new ArrayList<>();
        try{
            File dirPath = new File(request.getSession().getServletContext().getRealPath("") + savePath);
            if (!dirPath.exists()) {
                dirPath.mkdirs();
            }
            Map<String, MultipartFile> fileMap = request.getFileMap();
            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
                // 获取上传文件
                MultipartFile mf = entity.getValue();
                // 获取文件名
                String fileName = mf.getOriginalFilename();
                // 获取文件名后缀
                String suffix = !QCommon.isNullOrEmpty(fixName) ? fixName : fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".")) : "";
                // 构成新文件名
                String newFileName = QCommon.getUUID() + suffix;
                // 保存上传文件
                File uploadFile = new File(dirPath+"\\"+ newFileName);
                FileCopyUtils.copy(mf.getBytes(), uploadFile);

                names.add(newFileName);
            }
            ds.setValue(names);
            return ds;
        }catch(Exception e){
            e.printStackTrace();
            return ds.setError(e.getMessage());
        }
    }
}
