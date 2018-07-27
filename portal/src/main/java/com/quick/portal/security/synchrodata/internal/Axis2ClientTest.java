package com.quick.portal.security.synchrodata.internal;

  

import java.rmi.RemoteException;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.rpc.client.RPCServiceClient;
  
public class Axis2ClientTest {  
  
    public static void main(String[] args) throws RemoteException {  
    	Axis2ClientTest test = new Axis2ClientTest();  
//        System.out.println("2    " + test.testAxis2ClientByRpc());  
        System.out.println("3    " + test.testAxis2ClientByDoc());  
        
//        Endpoint.publish("http://127.0.0.1:18001/portal/intlDataSynchronizedService", new HelloService());
        System.out.println("server ready...");
    }  
  
    /** 
     * 方法一：通过 wsdl2java反向生成的类 调用 
     * @return 
     * @throws RemoteException 
     */  
/*    public String method1() throws RemoteException {  
  
        TestWeb web = new TestWebStub();  
  
        GetName getName = new GetName();  
        getName.setName("admin ... ");  
        GetNameResponse res = web.getName(getName);  
  
        System.out.println(res.get_return());  
  
        return res.get_return();  
    } */ 
  
    /**  
     * 方法二：  
     * 应用rpc的方式调用 这种方式就等于远程调用，  
     * 即通过url定位告诉远程服务器，告知方法名称，参数等， 调用远程服务，得到结果。  
     * 使用 org.apache.axis2.rpc.client.RPCServiceClient类调用WebService  
     *  
        【注】：  
          
            如果被调用的WebService方法有返回值 应使用 invokeBlocking 方法 该方法有三个参数  
              第一个参数的类型是QName对象，表示要调用的方法名；  
              第二个参数表示要调用的WebService方法的参数值，参数类型为Object[]；  
                当方法没有参数时，invokeBlocking方法的第二个参数值不能是null，而要使用new Object[]{}。  
              第三个参数表示WebService方法的 返回值类型的Class对象，参数类型为Class[]。  
              
              
            如果被调用的WebService方法没有返回值 应使用 invokeRobust 方法  
              该方法只有两个参数，它们的含义与invokeBlocking方法的前两个参数的含义相同。  
      
            在创建QName对象时，QName类的构造方法的第一个参数表示WSDL文件的命名空间名，  
            也就是 <wsdl:definitions>元素的targetNamespace属性值。  
     *  
     */  
    public String testAxis2ClientByRpc() throws AxisFault {  
        // 使用RPC方式调用WebService    
        RPCServiceClient serviceClient = new RPCServiceClient();  
        // 指定调用WebService的URL    
        EndpointReference targetEPR = new EndpointReference(SOAP_WSDL_ADDRESS);  
        Options options = serviceClient.getOptions();  
        //确定目标服务地址    
        options.setTo(targetEPR);  
        /**  
         * 指定要调用的getPrice方法及WSDL文件的命名空间  
         * 如果 webservice 服务端由axis2编写  
         * 命名空间 不一致导致的问题  
         * org.apache.axis2.AxisFault: java.lang.RuntimeException: Unexpected subelement arg0  
         */  
        QName qname = new QName(SOAP_TARGET_NAMESPACE, WSDL_OPERATION_NAME);  
        // 指定getPrice方法的参数值    
        Object[] parameters = new Object[] { "admin" };  
        // 指定getPrice方法返回值的数据类型的Class对象    
        Class[] returnTypes = new Class[] { String.class };  
        // 调用方法一 传递参数，调用服务，获取服务返回结果集    
        OMElement element = serviceClient.invokeBlocking(qname, parameters);  
        //值得注意的是，返回结果就是一段由OMElement对象封装的xml字符串。    
        //我们可以对之灵活应用,下面我取第一个元素值，并打印之。因为调用的方法返回一个结果    
        String result = element.getFirstElement().getText();  
        System.out.println("rpc="+result);  
        return result;  
    }  
  
    /**  
     * 方法三： 应用document方式调用  
     * 用ducument方式应用现对繁琐而灵活。现在用的比较多。因为真正摆脱了我们不想要的耦合  
     */  
    public String testAxis2ClientByDoc() {  
        OMElement result = null; 
        String retStr = null;
        try {  
            Options options = new Options();  
            // 指定调用WebService的URL    
            EndpointReference targetEPR = new EndpointReference(SOAP_WSDL_ADDRESS);  
            options.setTo(targetEPR);  
            ServiceClient sender = new ServiceClient();  
            sender.setOptions(options);  
            OMFactory fac = OMAbstractFactory.getOMFactory();  
            // 命名空间，有时命名空间不增加没事，不过最好加上，因为有时有事，你懂的    
            OMNamespace omNs = fac.createOMNamespace(SOAP_TARGET_NAMESPACE,"");  
            OMElement method = fac.createOMElement("getAllUserData", omNs);
//            OMElement symbol = fac.createOMElement(new QName("arg0"));  
//            symbol.addChild(fac.createOMText(symbol, "")); 
//            symbol.addChild(fac.createOMText(symbol, ""));  
//            method.addChild(symbol);  
            method.build();  
            result = sender.sendReceive(method);  
            retStr = result.getFirstElement().getText();
            System.out.println("*************** " + retStr);  
  
        } catch (AxisFault axisFault) {  
            axisFault.printStackTrace();  
        }  
        return retStr + "";  
    }  


 
  
    private final static String SOAP_WSDL_ADDRESS = "http://127.0.0.1:18001/portal/intlDataSynchronizedService?wsdl";

	private final static String SOAP_TARGET_NAMESPACE = "http://internal.synchrodata.security.portal.quick.com/";
	
	private final static String WSDL_SERVICE_NAME = "intlDataSynchronizedService";
	
	private final static String WSDL_OPERATION_NAME = "getUsersDataByUserID";
	
}  