package com.quick.portal.security.synchrodata.internal;


import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAP11Constants;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.xml.namespace.QName;
 
 //调用WebService 适用于车源，货源的删除
public class WebClient {
	 
 	private final static String SOAP_WSDL_ADDRESS = "/portal/intlDataSynchronizedService?wsdl";

	private final static String SOAP_TARGET_NAMESPACE = "http://internal.synchrodata.security.portal.quick.com/";
	
	private final static String WSDL_SERVICE_NAME = "intlDataSynchronizedService";
	
	private final static String WSDL_OPERATION_NAME = "getUsersDataByUserID";
	
	private final static String GET_ALLUSER_WSDL_OPERATION_NAME = "getAllUserData";
 
 	private static EndpointReference targetAirline = new EndpointReference(
		 SOAP_WSDL_ADDRESS);         //这里是要调用的targetUrl
 
 
  //设置发送请求的URL
  //@param: param:参数类型   paramValue:参数值  method:方法名
  //@return： 请求的URL

 private static OMElement buildParam(String param,String paramValue,String method){
	  OMFactory fac = OMAbstractFactory.getOMFactory();
	  OMNamespace omNs = fac.createOMNamespace(SOAP_TARGET_NAMESPACE, ""); //http://tempuri.org/是命名空间
	  OMElement data = fac.createOMElement(WSDL_OPERATION_NAME, omNs);      //获得要调用的方法名
	  OMElement inner = fac.createOMElement(new QName(param));      //获得该方法名要调用的参数名
	  inner.setText(paramValue);             //输入参数
	  data.addChild(inner);             //将该参数加入要调用的方法节点
	  return data;
	 }
 
	private static OMElement buildNoParam(String param, String paramValue,
			String method) {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace(SOAP_TARGET_NAMESPACE, ""); // http://tempuri.org/是命名空间
		OMElement data = fac.createOMElement(GET_ALLUSER_WSDL_OPERATION_NAME, omNs);
		// 将该参数加入要调用的方法节点
	/*	QName qname = new QName(param);
		OMElement inner = fac.createOMElement(qname);
		inner.setText(paramValue);
		data.addChild(inner);*/
		return data;
	}
 

 
 private static Options buildOptions(String method){
	  Options options = new Options();
	  options.setSoapVersionURI(SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI);
//	  options.setAction(SOAP_TARGET_NAMESPACE+method);     //设置调用的命名空间加方法
//	  options.setTo(targetAirline);
	  options.setTo(targetAirline);
	  options.setTransportInProtocol(Constants.TRANSPORT_HTTP);     //设置传输协议
	  return options;
}

 
 public static String getResultByCode(String param,String paramValue,String method){
	  try{
		  ServiceClient sender = new ServiceClient();
		  sender.setOptions(buildOptions(method));

	   OMElement result = sender.sendReceive(buildParam(param,paramValue,method));
//	   OMElement result = sender.sendReceive(buildNoParam(param,paramValue,method));
	//   System.out.println("解析之前的数据："+result.toString());
	   String str = result.getFirstElement().getText();  
	   System.out.println("解析之前的数据："+str.toString());
	   return result.toString();
	  }
	  catch(Exception e)
	  {
	   e.printStackTrace();
	   System.out.println("调用出错！");
	   return "调用出错！";
	  }
	  
	 }
 
  //dom4j解析WebService返回的数据 
  //@param: param:参数类型   paramValue:参数值  method:方法名
  //@return: string类型的数据
 public static String getResultByDom(String param,String paramValue,String method){
  try {
   Document doc= DocumentHelper.parseText(getResultByCode(param,paramValue,method));
   Element root = doc.getRootElement();
   Element rn=root.element(method+"Result");  //节点名
   System.out.println("解析之后的数据："+rn.getData());
   return (String) rn.getData();
  } catch (DocumentException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
   System.out.println("解析出错！");
   return "解析出错！";
  }
 }
 
 
 @SuppressWarnings("static-access")
 public static void main(String[] args) throws Exception {
  WebClient web=new WebClient();
  web.getResultByDom("arg0","admin", WSDL_OPERATION_NAME); //传入参数名，参数值，方法名
  
 }
 
 

}


