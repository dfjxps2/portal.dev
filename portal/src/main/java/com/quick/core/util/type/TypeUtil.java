package com.quick.core.util.type;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;

//import javax.lang.model.type.TypeKind;

public class TypeUtil {
    private final static TypeHandlerFactory factory = TypeHandlerFactory.newInstance();
    public static final Log log = LogFactory.getLog(TypeUtil.class);
    
    @SuppressWarnings("unchecked")
	public static <T> T parseObject( Class<T> clazz ,Object value,T deflautValue){
        TypeHandler typeHandler = factory.getTypeHandler(clazz);
        T result = null;;
        try {
            result = (T)typeHandler.parse(value, deflautValue);
        } catch (TypeException e) {
        	log.error("[类型转换]转换成" + clazz.toString() + "出错,值" + value);
            e.printStackTrace();
            result = deflautValue;
        }
        return result;
    }
    public static <T> T toArray(Class<T> returnType) {
    	return null;
    }
    
	@SuppressWarnings("unchecked")
	public static <T> T parseObject( Class<T> clazz ,Object value) {
        TypeHandler<?> typeHandler = factory.getTypeHandler(clazz);
        T result = null;
        try {
        	result = (T)typeHandler.parse(value);
        } catch (TypeException e) {
        	log.error("[类型转换]转换成" + clazz.toString() + "出错,值" + value);
            e.printStackTrace();
        }
		return result;
    }
	
	@SuppressWarnings("unchecked")
	public static Object parse( Class<?> clazz ,Object value) {
        TypeHandler<?> typeHandler = factory.getTypeHandler(clazz);
        Object result=new Object();
        try {
        	result=typeHandler.parse(value);
        } catch (TypeException e) {
        	log.error("[类型转换]转换成" + clazz.toString() + "出错,值" + value);
            e.printStackTrace();
        }
		return result;
    }
    
    public static void main(String[] args) throws Exception {
        //System.out.println(TypeUtil.parseObject(Date.class,"2012-08-23 23:23:23"));
        
        System.out.println(new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse("2012-01-05 23:23:23"));
    }
    
    
    
}
