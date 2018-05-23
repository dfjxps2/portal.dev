/**
 * <h3>标题 : Excel解析工具类 </h3>
 * <h3>描述 : Excel解析</h3>
 * <h3>日期 : 2017-05-17</h3>
 * <h3>版权 : Copyright (C) 海口鑫网计算机网络有限公司</h3>
 *
 * <p>
 * @author 符永枫
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
package com.quick.core.util.common;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/5/17.
 */
public class ExcelUtil {
    private final static String excel2003L =".xls";    //2003- 版本的excel
    private final static String excel2007U =".xlsx";   //2007+ 版本的excel

    /**
     * 获取IO流中的数据，组装成List<<List<Object>>>对象
     * @param in
     * @param fileName
     * @return
     * @throws Exception
     */
    public static   List<Map<String, Object>> getListByExcel(InputStream in, String fileName,List<Object> heaader) throws Exception{
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        //创建Excel工作薄
        Workbook work = getWorkbook(in,fileName);
        if(null == work){
            throw new Exception("解析失败，请检查文件后重新上传");
        }
        Sheet sheet = work.getSheetAt(0);
        if(sheet==null){
            throw new Exception("解析失败，请检查文件后重新上传");
        }
        int firstRow = sheet.getFirstRowNum();
        int lastRow = sheet.getLastRowNum();
        Row row = null;
        Cell cell = null;

        for (int j = 0; j < heaader.size(); j++) {
            cell = sheet.getRow(firstRow).getCell(j);
            Object value=getCellValue(cell);
            if(!value.toString().equals(heaader.get(j))){   //模板不对
                throw new Exception("解析失败，请检查文件后重新上传");
            }
        }

        try{
            //遍历当前sheet中的所有行
            for (int i = firstRow+1; i<lastRow+1; i++) {
                row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                Map map = new LinkedHashMap();
                Boolean isBlankRow = true;
                //System.out.println(i);
                    if(row.getFirstCellNum()!=-1)
                    for (int y = row.getFirstCellNum(); y < heaader.size(); y++) {
                        Cell headerCell = sheet.getRow(firstRow).getCell(y);
                        String key = headerCell.getStringCellValue();
                        cell = row.getCell(y);
                        Object value = cell==null?"":getCellValue(cell);
                        if (isBlankRow) {
                            if (value != null && !value.equals("")) {
                                isBlankRow = false;
                            }
                        }
                        map.put(key, value);

                    }
                    if (!isBlankRow)
                        list.add(map);
                }

        }catch (Exception e){
            throw new Exception("解析失败，请检查文件后重新上传");
        }
        return list;
    }
    /**
     * 获取IO流中的数据，组装成List<<List<Object>>>对象
     * @param in
     * @param fileName
     * @return
     * @throws Exception
     */
    public static   List<Map<String, Object>> getListByExcel(InputStream in, String fileName,List<Object> heaader,List<Object> heaader2) throws Exception{
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        //创建Excel工作薄
        Workbook work = getWorkbook(in,fileName);
        if(null == work){
            throw new Exception("解析失败，请检查文件后重新上传");
        }
        Sheet sheet = work.getSheetAt(0);
        if(sheet==null){
            throw new Exception("解析失败，请检查文件后重新上传");
        }
        int firstRow = sheet.getFirstRowNum();
        int lastRow = sheet.getLastRowNum();
        Row row = null;
        Cell cell = null;

        for (int j = 0; j < heaader.size(); j++) {
            cell = sheet.getRow(firstRow).getCell(j);
            Object value=getCellValue(cell);
            if(!value.toString().equals(heaader.get(j))){   //模板不对
                throw new Exception("模版错误，请检查文件后重新上传");
            }
        }

        try{
            //遍历当前sheet中的所有行
            for (int i = firstRow+1; i<lastRow+1; i++) {
                row = sheet.getRow(i);
                if(row==null){continue;}
                Map map = new LinkedHashMap();
                Boolean isBlankRow = true;
                if(row.getFirstCellNum()!=-1)
                for (int y = row.getFirstCellNum(); y < heaader.size(); y++) {
                    Cell headerCell = sheet.getRow(firstRow).getCell(y);
                    String key = headerCell.getStringCellValue();
                    cell = row.getCell(y);
                    Object value = cell==null?"":getCellValue(cell);
                    if(isBlankRow) {
                        if(value != null && !value.equals("")){
                            isBlankRow = false;
                        }
                    }
                    map.put(heaader2.get(y).toString(),value);

                }
                if(!isBlankRow)
                    list.add(map);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("解析失败，请检查文件后重新上传");
        }
        return list;
    }

    /**
     * 根据记录行数摘取数据生成新的EXCEL
     * @param in 输入流
     * @param fileName 文件名
     * @param list  要转换的list
     * @return
     * @throws Exception
     */
    public static Workbook ListToExcel(InputStream in,String fileName,List<Map<String,Object>> list)throws Exception{
            Workbook work = getWorkbook(in, fileName);
            if(null == work){
                throw new Exception("解析失败，请检查文件后重新上传");
            }
            Sheet sheet = work.getSheetAt(0);
            if(sheet==null){
                throw new Exception("解析失败，请检查文件后重新上传");
            }
        Workbook outWb = newWorkbook(fileName);
        CellStyle headertStyle=outWb.createCellStyle();
        CellStyle bodyStyle=outWb.createCellStyle();
        // 创建表单并设置cell宽度
        Sheet currentSheet = outWb.createSheet();

        // 创建表头
        copyRow(work,outWb,0,0,true,headertStyle);


        // 插入表内容
        int currentRow = 1;
        for (Map<String,Object> r : list) {
            // 行
            copyRow(work,outWb,((int)r.get("index"))+1,currentRow,true,bodyStyle);
            int cellNum=outWb.getSheetAt(0).getRow(0).getLastCellNum();
            outWb.getSheetAt(0).getRow(currentRow).createCell(cellNum);
            outWb.getSheetAt(0).getRow(currentRow).getCell(cellNum).setCellValue(r.get("error").toString());
            currentRow += 1;
        }
        return outWb;


    }

    /**
     * 复制行
     * @param fromWb  源工作簿
     * @param toWb   目标工作簿
     * @param rowIndex  被复制的行
     * @param targetRowIndex  复制到的目标行
     * @param copyValueFlag 是否复制值
     */
    public static void copyRow(Workbook fromWb,Workbook toWb,int rowIndex,int targetRowIndex,boolean copyValueFlag,CellStyle targetStyle){
        for (Iterator cellIt = fromWb.getSheetAt(0).getRow(rowIndex).cellIterator(); cellIt.hasNext();) {
            Cell tmpCell = (Cell) cellIt.next();
            if(toWb.getSheetAt(0)==null)
                toWb.createSheet();
            if(toWb.getSheetAt(0).getRow(targetRowIndex)==null)
                toWb.getSheetAt(0).createRow(targetRowIndex);
            Cell newCell = toWb.getSheetAt(0).getRow(targetRowIndex).createCell(tmpCell.getColumnIndex());
            copyCell(fromWb,toWb,tmpCell, newCell, copyValueFlag,targetStyle);
            toWb.getSheetAt(0).getRow(targetRowIndex).setHeightInPoints(fromWb.getSheetAt(0).getRow(rowIndex).getHeightInPoints());
        }
    }
    /**
     * 复制单元格
     *
     * @param srcCell
     * @param distCell
     * @param copyValueFlag
     *            true则连同cell的内容一起复制
     */
    public static void copyCell(Workbook fromWb,Workbook toWb,Cell srcCell, Cell distCell,
                                boolean copyValueFlag,CellStyle targetCellStyle) {

        targetCellStyle.cloneStyleFrom(srcCell.getCellStyle());

        distCell.setCellStyle(targetCellStyle);
        Object value=getCellValue(srcCell);
        int width= toWb.getSheetAt(0).getColumnWidth(srcCell.getColumnIndex());
        int newwidth=value.toString().length()*512;
        if(newwidth>width)
        toWb.getSheetAt(0).setColumnWidth(srcCell.getColumnIndex(),newwidth);

        //评论
        if (srcCell.getCellComment() != null) {
            distCell.setCellComment(srcCell.getCellComment());
        }
        // 不同数据类型处理
        int srcCellType = srcCell.getCellType();
        distCell.setCellType(srcCellType);
        if (copyValueFlag) {
            if (srcCellType == Cell.CELL_TYPE_NUMERIC) {
                if (DateUtil.isCellDateFormatted(srcCell)) {
                    distCell.setCellValue(srcCell.getDateCellValue());
                } else {
                    distCell.setCellValue(srcCell.getNumericCellValue());
                }
            } else if (srcCellType == Cell.CELL_TYPE_STRING) {
                distCell.setCellValue(srcCell.getRichStringCellValue());
            } else if (srcCellType == Cell.CELL_TYPE_BLANK) {
            } else if (srcCellType == Cell.CELL_TYPE_BOOLEAN) {
                distCell.setCellValue(srcCell.getBooleanCellValue());
            } else if (srcCellType == Cell.CELL_TYPE_ERROR) {
                distCell.setCellErrorValue(srcCell.getErrorCellValue());
            } else if (srcCellType == Cell.CELL_TYPE_FORMULA) {
                distCell.setCellFormula(srcCell.getCellFormula());
            } else { // nothing29
            }
        }
    }

    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     * @param inStr,fileName
     * @return
     * @throws Exception
     */
    public static   Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if(excel2003L.equals(fileType)){
            wb = new HSSFWorkbook(inStr);  //2003-
        }else if(excel2007U.equals(fileType)){
            wb = new XSSFWorkbook(inStr);  //2007+
        }else{
            throw new Exception("格式错误，请重新上传正确的格式");
        }
        return wb;
    }
    /**
     * 描述：根据文件后缀，自适应创建Excel
     * @param ,fileName
     * @return
     * @throws Exception
     */
    public static   Workbook newWorkbook(String fileName) throws Exception{
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if(excel2003L.equals(fileType)){
            wb = new HSSFWorkbook();  //2003-
        }else if(excel2007U.equals(fileType)){
            wb = new XSSFWorkbook();  //2007+
        }else{
            throw new Exception("格式错误，请重新上传正确的格式");
        }
        return wb;
    }
    /**
     * 描述：对表格中数值进行格式化
     * @param cell
     * @return
     */
    public static  Object getCellValue(Cell cell){
        DecimalFormat df = new DecimalFormat("0");  //格式化number String字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  //日期格式化

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return  cell.getRichStringCellValue().getString();
            case Cell.CELL_TYPE_NUMERIC:
                 if(DateUtil.isCellDateFormatted(cell)){
                     return sdf.format(cell.getDateCellValue());
                }else{
                     return df.format(cell.getNumericCellValue());
                }
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();
            default:
                return "";
        }
    }

    /**
     * 创建excel
     * @param title
     * @param list
     * @param headers
     */
    public static void createExcel(String title,List<Map<String,Object>> list,String[] headers,String excelName){
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(title);

        // 第三步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        //poi版本对应poi-ooxml 3.17，攀虎修改
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        // 第四步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {

            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);

        }



        // 第五步，写入数据
        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);
            Map<String,Object>  m = list.get(i);
            // ，创建单元格，并设置值
           for(int j=0;j<m.size();j++){
               row.createCell(j).setCellValue((String)m.get(headers[j]));
           }
        }
        // 第六步，将文件存到指定位置
        try
        {
            FileOutputStream fout = new FileOutputStream(excelName);
            wb.write(fout);
            fout.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



}
