package com.quick.portal.security.synchrodata.internal;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;  

import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class CxfClientTest {

	public static void main(String[] args) throws Exception {
//		test();
//		test1();
		test2();
	}
	public static void test() throws Exception{
		// 这个是用cxf 客户端访问cxf部署的webservice服务
				// 千万记住，访问cxf的webservice必须加上namespace ,否则通不过
				// 现在又另外一个问题，传递过去的参数服务端接收不到
				JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
				org.apache.cxf.endpoint.Client client = dcf.createClient("http://127.0.0.1:6080/portal/UserDataSync?wsdl");
				// url为调用webService的wsdl地址
				QName name = new QName(
						"http://userdt.synchrodata.security.portal.quick.com/",
						"UserDataSync");
				// namespace是命名空间，methodName是方法名
		/*		String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
						+ "     <facelook>" + "        <condition>"
						+ "            <name>家</name>"
						+ "            <description></description>"
						+ "            <pageno></pageno>"
						+ "            <pagesize></pagesize>" + "        </condition>"
						+ "     </facelook>";*/
				String xmlStr = "test";
				// paramvalue为参数值
				Object[] objects = client.invoke(name, xmlStr);
				// 调用web Service//输出调用结果
				System.out.println(objects[0].toString());
	}
	
	public static void test1() throws Exception{
		 JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();  
	        Client client = factory.createClient("http://127.0.0.1:6080/portal/UserDataSync?wsdl");  
	        Object[] inputs = {"aaa"};  
	        Object[] result = client.invoke("UserDataSync", inputs);  
	        System.out.println(result[0]);  
	        
	      
	}
	
    public static void test2() {  
        //创建WebService客户端代理工厂    
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();    
        //注册WebService接口    
        factory.setServiceClass(IDataSynchronizedService.class);    
        //设置WebService地址    
        factory.setAddress("http://127.0.0.1:6080/portal/UserDataSync");    
        IDataSynchronizedWsdl service = (IDataSynchronizedWsdl)factory.create();    
        System.out.println("开始调用webservice...");    
        String result = service.getUsersDataByIDOrName("米饭");
        System.out.println("------------="+result);  
  
    }  

}
