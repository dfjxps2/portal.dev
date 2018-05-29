package com.quick.portal.security.synchrodata.internal;

import java.io.IOException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;


/**
 * 
 * <p>Title: ServiceClient.java</p>
 * <p>Description:Service编程实现服务端调用</p>
 * 
 */
public class UserDataSyncClientTest {

	public static void main(String[] args) throws IOException {
		//创建WSDL的URL，注意不是服务地址
		URL url = new URL("http://127.0.0.1:6080/portal/UserDataSync?wsdl");
		//创建服务名称
		//1.namespaceURI - 命名空间地址
		//2.localPart - 服务视图名
		QName qname = new QName("http://userdt.synchrodata.security.portal.quick.com/", "UserDataSync");
		//创建服务视图
		//参数解释：
		//1.wsdlDocumentLocation - wsdl地址
		//2.serviceName - 服务名称
		Service service = Service.create(url, qname);
		//获取服务实现类
		IDataSynchronizedWsdl wsSoap = service.getPort(IDataSynchronizedWsdl.class);
		//调用查询方法
		String result = wsSoap.getUsersDataByUserID("World");
		System.out.println("----------="+result);
	}
}
