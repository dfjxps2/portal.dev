package com.quick.portal.mesManage;

import com.quick.core.base.ISysBaseService;
import com.quick.core.base.model.DataStore;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by GaoPh on 2018/5/3.
 */

public interface MesManageService extends ISysBaseService<MesManageDO> {
    public  DataStore saveExcel(HttpServletRequest request, InputStream in, String fileName, String sysid,  List<Object> heaader, boolean MERGE) throws Exception;
//    public  void exportExcel(OutputStream out, List<Object> header);
}
