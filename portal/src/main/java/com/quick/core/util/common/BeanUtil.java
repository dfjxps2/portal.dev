package com.quick.core.util.common;

import com.quick.core.util.type.TypeUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;

public class BeanUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    /** 
     * 此方法可以把ApplicationContext对象inject到当前类中作为一个静态成员变量。 
     * @param applicationContext ApplicationContext 对象. 
     * @throws BeansException 
     */  
    @Override  
    public void setApplicationContext( ApplicationContext applicationContext ) throws BeansException {  
        this.applicationContext = applicationContext;  
    }
    /**
     * 获取Spring应用上下文环境
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
      return applicationContext;
    }
    /** 
     * 这是一个便利的方法，帮助我们快速得到一个BEAN 
     * @param beanName bean的名字 
     * @return 返回一个bean对象 
     */
    public static Object getBean( String beanName ) {
        return applicationContext.getBean( beanName );
    }
    
   /**
    * 获取类型为requiredType的对象
    * 如果bean不能被类型转换，相应的异常将会被抛出（BeanNotOfRequiredTypeException）
    * @param name       bean注册名
    * @param requiredType 返回对象类型
    * @return Object 返回requiredType类型对象
    * @throws BeansException
    */
    public static Object getBean(String name, Class requiredType) throws BeanNotOfRequiredTypeException {
        return applicationContext.getBean(name, requiredType);
    }
 
   /**
    * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true 
    * @param name
    * @return boolean
    */
    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }
 
   /**
    * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
    * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）   
    * @param name
    * @return boolean
    * @throws NoSuchBeanDefinitionException
    */
    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.isSingleton(name);
    }
 
   /**
    * 获取BEAN类型
    * @param name
    * @return Class 注册对象的类型
    * @throws NoSuchBeanDefinitionException
    */
    public static Class getType(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.getType(name);
    }
 
   /**
    * 如果给定的bean名字在bean定义中有别名，则返回这些别名   
    * @param name
    * @return
    * @throws NoSuchBeanDefinitionException
    */
    public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
      return applicationContext.getAliases(name);
    }
    
    public static <T> T getFormBean(HttpServletRequest request, Class<T> k) {
    	T o = null;
        try {
            o = k.newInstance();
            for(Method m : k.getDeclaredMethods()) {
                String mName = m.getName();
                if(!mName.startsWith("set"))
                        continue;
                String value = request.getParameter(setLower(mName.substring(3)));
                if(value == null || "".equals(value)) continue;
                else {
                    if(m.getParameterTypes()[0].equals(int.class) || 
                                    m.getParameterTypes()[0].equals(Integer.class)) {
                            m.invoke(o, TypeUtil.parseObject(Integer.class, value));
                    } 
                    else if(m.getParameterTypes()[0].equals(boolean.class) || 
                                    m.getParameterTypes()[0].equals(Boolean.class)) {
                            m.invoke(o, TypeUtil.parseObject(Boolean.class, value));
                    }
                    else if(m.getParameterTypes()[0].equals(String.class)) {
                            m.invoke(o, value);
                    }
                    else if(m.getParameterTypes()[0].equals(float.class) || 
                                    m.getParameterTypes()[0].equals(Float.class)) {
                            m.invoke(o, TypeUtil.parseObject(Float.class, value));
                    }
                    else if(m.getParameterTypes()[0].equals(long.class) || 
                                    m.getParameterTypes()[0].equals(Long.class)) {
                            m.invoke(o, TypeUtil.parseObject(Long.class, value));
                    }
                    else if(m.getParameterTypes()[0].equals(Date.class)) {
                            m.invoke(o, TypeUtil.parseObject(Date.class, value));
                    }
                    else if(m.getParameterTypes()[0].equals(Timestamp.class)) {
                            m.invoke(o, TypeUtil.parseObject(Timestamp.class, value));
                    }
                }
            }
            return o;
        } catch (IllegalArgumentException e) {
                e.printStackTrace();
        } catch (IllegalAccessException e) {
                e.printStackTrace();
        } catch (InvocationTargetException e) {
                e.printStackTrace();
        }catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        return null;
    }

    private static String setLower(String s) {
        StringBuilder builder = new StringBuilder(s);
        String first = s.substring(0, 1).toLowerCase();
        builder.setCharAt(0, first.charAt(0));
        return builder.toString();
    }
}
