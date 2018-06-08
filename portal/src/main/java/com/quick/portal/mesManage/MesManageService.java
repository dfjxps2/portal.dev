package com.quick.portal.mesManage;

import com.quick.core.base.ISysBaseService;
import com.quick.core.base.model.DataStore;
import org.apache.poi.ss.formula.functions.T;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by GaoPh on 2018/5/3.
 */

public interface MesManageService extends ISysBaseService<MesManageDO> {
    public  DataStore saveExcel(HttpServletRequest request, InputStream in, String fileName, String sysid,  List<Object> heaader, boolean MERGE) throws Exception;

    public void publishMes(MesManageDO mesManageDO,String[] tagId,HttpServletRequest request,HttpServletResponse response,boolean MERGE) throws Exception;
    public void editMes(MesManageDO mesManageDO,String[] tagId,HttpServletRequest request,HttpServletResponse response,boolean MERGE) throws Exception ;
    public void addDatum(MesManageDO mesManageDO,String keywords,HttpServletRequest request,HttpServletResponse response,boolean MERGE) throws IOException, TikaException, SAXException;
    public void  deleteMes(HttpServletRequest request,HttpServletResponse response) throws Exception;
    public void editDatum(MesManageDO mesManageDO,String keywords,HttpServletRequest request,HttpServletResponse response,boolean MERGE) throws Exception;
    public void  delDatum(HttpServletRequest request,HttpServletResponse response) throws Exception;


}
