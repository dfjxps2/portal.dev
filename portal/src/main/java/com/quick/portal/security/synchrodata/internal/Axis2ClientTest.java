package com.quick.portal.security.synchrodata.internal;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;




public class Axis2ClientTest {

	public static void main(String[] args) throws ServiceException, RemoteException, MalformedURLException {  
		 axis2WebService();
//		 axis2WebServiceTest();
	      
	}  
	
	 private static void axis2WebService() {  
	        try {  
	            String soapBindingAddress = "http://127.0.0.1:6080/portal/UserDataSync?wsdl";  
	            ServiceClient sender = new ServiceClient();  
	            EndpointReference endpointReference = new EndpointReference(soapBindingAddress);  
	            Options options = new Options();  
	        
	            options.setTo(endpointReference);  
	            sender.setOptions(options);  
	            OMFactory fac = OMAbstractFactory.getOMFactory();  
	            // 这个和qname差不多，设置命名空间  
	            OMNamespace omNs = fac.createOMNamespace("http://userdt.synchrodata.security.portal.quick.com/",  "UserDataSync");  
	            OMElement data = fac.createOMElement("sayHello", omNs);  
	            // 对应参数的节点  
	            String[] strs = new String[] { "arg0" };  
	            // 参数值  
	            String[] val = new String[] { "zhangyg" };  
	            for (int i = 0; i < strs.length; i++) {  
	                QName qname=new QName(strs[i]);
	                OMElement inner = fac.createOMElement(qname);  
	                inner.setText(val[i]);  
	                data.addChild(inner);  
	            }  
	            // 发送数据，返回结果  
	            OMElement result = sender.sendReceive(data);  
	            System.out.println(result.toString());  
	        } catch (AxisFault ex) {  
	            ex.printStackTrace();  
	        }  
	  
	    }  


}
