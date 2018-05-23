package com.quick.core.util.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2017/4/18.
 */
public class ConfigTool extends PropertyPlaceholderConfigurer {
    //配置文件键值对
    private static Map<String,String> propertyMap;
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        propertyMap = new HashMap<String, String>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            propertyMap.put(keyStr, value);
        }

    }

    /**
     * 获取配置的属性值
     * @param name 属性名
     * @return
     */
    public static Object getProperty(String name) {
        if(!propertyMap.containsKey(name))
            return null;
        return propertyMap.get(name);
    }

    /**
     * 获取相应代码前八位
     * @param methohName 方法名
     */
    public static String GetCode(String methohName){
        if(methohName==null)
            return null;
        if(getProperty(methohName.toLowerCase())==null)
            return null;
         return "1000"+getProperty(methohName.toLowerCase()).toString();
    }

    /**
     * 获取完整的服务代码,仅可在第三级调用
     * @param msgCode
     * @return
     */
    public static String GetRespCode(String msgCode){
        String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
        return GetRespCode(methodName, msgCode);
    }

    /**
     * 获取完整的服务代码
     * @param methodName
     * @param msgCode
     * @return
     */
    public static String GetRespCode(String methodName, String msgCode){
        if(methodName == null || methodName.equals(""))
            return "-1";    //未知错误请求
        String code = ConfigTool.GetCode(methodName);
        if(code == null || code.equals(""))
            return "-1";    //未知错误代码
        code += msgCode;
        return code;
    }
}
