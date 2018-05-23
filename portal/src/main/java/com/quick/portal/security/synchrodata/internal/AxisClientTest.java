package com.quick.portal.security.synchrodata.internal;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.Call;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.client.Service;

public class AxisClientTest {

	public static void main(String[] args) throws ServiceException, RemoteException, MalformedURLException {  
		 axisWebServiceTest();
	      
	}  
	
	 private static void axisWebServiceTest() {  
		 String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"  
	             + "     <facelook>"  
	             + "        <condition>"  
	             + "            <name>家</name>"  
	             + "            <description></description>"  
	             + "            <pageno></pageno>"  
	             + "            <pagesize></pagesize>"  
	             + "        </condition>"  
	             + "     </facelook>";  
	      
	      Service service = new Service();  
	      Call call;
	      String result = "";
		try {
			call = (Call) service.createCall();
		      call.setTargetEndpointAddress(WSDL_URL);  
		      QName qName = new QName(DOMAIN_NAME, METHOD_NAME);  
		      call.setOperationName(qName);  
		      //这下面两行一定要加上，否则接收在服务器端收不到。  
		      call.addParameter("xmlStr", XMLType.XSD_STRING, ParameterMode.IN);  
		      call.setReturnType(XMLType.XSD_STRING);  
		      try {
				result = (String) call.invoke(new Object[] { xmlStr });
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

	      System.out.println(result);  
	      
	    //将返回的字符串转换成list集合  
	    //JSONArray array = JSONArray.fromObject(result);  
	    //List<Album> list = JSONArray.toList(array,Album.class); 
	 }
	 private final static String METHOD_NAME = "UserDataSync";
	 private final static String DOMAIN_NAME = "http://userdt.synchrodata.security.portal.quick.com/";
	 private final static String WSDL_URL = "http://127.0.0.1:6080/portal/UserDataSync?wsdl";
	 
	 
	 /*private static void axisWebServiceTest1() {
		 Call call;
		 Object[] obj =new Object[]{20,50};
		 try {
		 Service service = new Service();
		 call = (Call) service.createCall();
		 call.setTargetEndpointAddress(WSDL_URL);//设置服务地址，指明远程调用的类
		 call.setEncodingStyle("utf-8");//设置传入服务端的字符集格式如utf-8等，注意：                                                          //  Integer,int,Date想要传递必须有此方法,
		 call.setOperationName(METHOD_NAME);//设置远程调用类中的方法
		 //参数必须按照XSD中的顺序添加到Call中，否则则不能与Object数组的值对应，会报错。
		 call.addParameter("arg0", XMLType.XSD_INT, ParameterMode.IN);//参数名，参数类                                                                                       //参数模式
		 call.addParameter("arg1", XMLType.XSD_INT, ParameterMode.IN);//IN:输入参数，                                                                       //OUT输出参数，INOUT输入输出参数
		 call.setReturnType(XMLType.XSD_INT);//设置返回参数类型
		 int m =   (Integer) call.invoke(obj);//调用服务的方法，并传参
		 System.out.println(m);
		 } catch (ServiceException e) {
		 e.printStackTrace();
		 } catch (RemoteException e) {
		 e.printStackTrace();
		 }
		 
	 }*/


}
