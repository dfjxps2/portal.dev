package com.quick.portal.mesManage;

import com.quick.core.base.ISysBaseDao;
import com.quick.core.base.SysBaseService;
import com.quick.core.base.model.DataStore;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.quick.core.util.common.ExcelUtil.getCellValue;
import static com.quick.core.util.common.ExcelUtil.getWorkbook;

/**
 * Created by GaoPh on 2018/5/3.
 */
@Transactional
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
                return ActionMsg.setError("解析失败，请检查文件后重新上传");
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


    public static  List<Map<String, Object>> getListByExcel(InputStream in, String fileName,List<Object> header) throws Exception{
        List<Map<String, Object>> result = new ArrayList<>();
        //创建Excel工作薄
        Workbook work = getWorkbook(in,fileName);
        if(null == work){
            throw new Exception("解析失败，请检查文件后重新上传");
        }
        for(int a=0;a<work.getNumCellStyles();a++) {
            Sheet sheet = work.getSheetAt(a);
            if (sheet == null) {
                throw new Exception("解析失败，请检查文件后重新上传");
            }
            int firstRow = sheet.getFirstRowNum();
            int lastRow = sheet.getLastRowNum();
            Row row = null;
            Cell cell = null;
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
                        for (int y = row.getFirstCellNum(); y < header.size(); y++) {
                            Map<String, Object> map = new LinkedHashMap();
                            map.put("rule_id", ruleIDValue);
                            map.put("param_name", ruleParamValue);
                            cell = row.getCell(y);
                            Object value;
                            if (cell != null && !"".equals(cell)) {
                                value = cell == null ? "" : getCellValue(cell);
                            } else {
                                break;
                            }
                            map.put("param_value", value);
                            result.add(map);
                        }
                }

            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("解析失败，请检查文件后重新上传");
            }
        }
        return result;
    }}
