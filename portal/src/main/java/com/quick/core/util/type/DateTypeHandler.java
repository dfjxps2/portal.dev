package com.quick.core.util.type;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;


public class DateTypeHandler extends   BaseTypeHandler<Date> {
    
    public DateTypeHandler(Date systemDefaultValue){
        super(systemDefaultValue);
    }


    public boolean typeCheck(Object object) {
        boolean support = false;
        if(object instanceof String){
            support = true;
        }else if(object instanceof Long){
            support = true;
        }
        return support;
    }

    @Override
    public Date valueOf(Object object) throws TypeException{
        
        if (!typeCheck(object)) {
            throw new TypeException("DateTypeHandler只支持由Long,String类型转化成Date类型");
        }

        if(object instanceof Long){
           return toDate((Long)object);
        }else{
            try {
                return toDate( (String)object);
            } catch (ParseException e) {
                throw new TypeException(e.getMessage());
            }
        }
    }
    
    private Date toDate(String date) throws TypeException, ParseException{
        
        String format = getFormat(date);
        if(format == null){
            throw new TypeException(date+" 找不到匹配的日期格式");
        }
        return new SimpleDateFormat(format).parse(date);
    }
    
    private Date toDate(Long time){
        return new Date(time);
    }
    
    
    private String getFormat(String date){
        String format = null;
        
        String yyyy_MM_dd = "\\d{4}-\\d{2}-\\d{2}";
        
        String yyyyMMdd = "\\d{4}\\d{2}\\d{2}";
        
        String yyyy_MM_dd_hh_mm_ss = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";
        
        if (Pattern.matches(yyyy_MM_dd, date)) {
            format = "yyyy-MM-dd";
        }else if(Pattern.matches(yyyyMMdd, date)){
            format = "yyyyMMdd";
        }else if(Pattern.matches(yyyy_MM_dd_hh_mm_ss, date)){
            format = "yyyy-MM-dd HH:mm:ss";
        }else {
        	format="yyyy-MM-dd HH:mm:ss.SSS";
		}
        
        return format;
    }
}
