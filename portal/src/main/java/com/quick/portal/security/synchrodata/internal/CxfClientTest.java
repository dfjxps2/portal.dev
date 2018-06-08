package com.quick.portal.security.synchrodata.internal;
import javax.xml.namespace.QName;

import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class CxfClientTest {

	public static void main(String[] args) throws Exception {
		testCxfClient();
	}
	public static void testCxfClient() throws Exception{
				JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
				org.apache.cxf.endpoint.Client client = dcf.createClient(SOAP_WSDL_ADDRESS);
				// url为调用webService的wsdl地址
				QName name = new QName(
						SOAP_TARGET_NAMESPACE,
						WSDL_OPERATION_NAME);
				// namespace是命名空间，methodName是方法名
				String xmlStr = "admin";
				// paramvalue为参数值
				Object[] objects = client.invoke(name, xmlStr);
				// 调用web Service//输出调用结果
				System.out.println(objects[0].toString());
	}
	
    
    private final static String SOAP_WSDL_ADDRESS = "http://127.0.0.1:18001/portal/intlDataSynchronizedService?wsdl";

	private final static String SOAP_TARGET_NAMESPACE = "http://internal.synchrodata.security.portal.quick.com/";
	
	private final static String WSDL_SERVICE_NAME = "intlDataSynchronizedService";
	
	private final static String WSDL_OPERATION_NAME = "getUsersDataByUserID";

}
