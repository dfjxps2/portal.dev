package com.quick.portal.security.synchrodata.internal;


import java.net.URL;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.description.OperationDesc;
import org.apache.axis.description.ParameterDesc;
import org.apache.axis.encoding.XMLType;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 

/**
 * WebService通用访问辅助工具.可以动态的访问webService服务.
 * <p>包含Axis, Axis2, Xfire三种WebService</p>
 * 
 * @author Jason
 * @version 1.0, 2011/11/14
 */
public class WebServiceUtils {
 
    /**
     * seee#org.apache.axis.client.Call
     */
    private static Call call = null;
     
    /** <code>ResourceBundle<code> */
    private static ResourceBundle webServicesProperties;
 
    /** Slf4j logging instance. */
    private static final Logger logger = LoggerFactory.getLogger(WebServiceUtils.class);
     
    /** 配置文件myConfig.properties放在classpath下面otherConfigs目录下面   */
    public static final String WEBSERVICES_PROPERTIES_FILENAME = "config/webServices";
     
    /** Axis2 RPCServiceClient */
    private static RPCServiceClient serviceClient;
     
     
    /**
     * 在静态块中通过<code>ResourceBundle<code>类来加载配置的<code>webServices.properties</code>.
     */
    static {
        try {
            webServicesProperties = ResourceBundle.getBundle(WEBSERVICES_PROPERTIES_FILENAME);
        } catch (Exception e) {
            logger.error("加载WebServices配置文件错误！".concat(e.getMessage()));
            System.exit(0);
        }
    }
     
    /**
     * 通过在<code>webServices.properties</code>中定义的key来取得对应的value.
     * 
     * @param key <code>webServices.properties</code>的key.
     * @return 返回key对应的value，没有则返回"".
     */
    public static String getProValByKey(String key) {
        String str = webServicesProperties.getString(key);
        return StringUtils.isEmpty(str) ? "" : str;
    }
     
    /**
     * 格式 消息的信息.
     * @see java.text.MessageFormat#format(String, Object...)
     * 
     * @param pattern 需要格式的字符串
     * @param arguments 格式式参数
     * @return 成功返回格式化后的内容, 否则不进行格式化操作
     */
    public static String messageFormat(String pattern, Object ... arguments) {
        try {
            return MessageFormat.format(pattern, arguments);
        } catch (Exception e) {
            return pattern;
        }
    }
     
    /**
     * 使用Axis Call Client动态调用WebService地址.
     * 
     * @param webServiceAddr WebService地址
     * @param webServiceMethod WebService方法
     * @param soapActionURI SOAPActionURI
     * @param targetNamespace 命名空间
     * @param inputValues 输入参数值
     * @param inputNames 输入参数名称
     * @param inputXmlTypes 输入参数XML类型
     * @param inputJavaTypes 输入参数JAVA类型
     * @return 成功返回<code>Object</code>, 失败或异常返回null.
     */
    public static Object getObjectByAxisCallClient(String webServiceAddr, String webServiceMethod, String soapActionURI,
            String targetNamespace, Object[] inputValues, String[] inputNames, String[] inputXmlTypes, Class<?>[] inputJavaTypes) {
        return getObjectByAxisCallClient(webServiceAddr, webServiceMethod, soapActionURI, targetNamespace, inputValues, inputNames, 
                inputXmlTypes, inputJavaTypes, null, null);
    }
     
    /**
     * 使用Axis Call Client动态调用WebService地址.
     * 
     * @param webServiceAddr WebService地址
     * @param webServiceMethod WebService方法
     * @param soapActionURI SOAPActionURI
     * @param targetNamespace 命名空间
     * @param inputValues 输入参数值
     * @param inputNames 输入参数名称
     * @param inputXmlTypes 输入参数XML类型
     * @param inputJavaTypes 输入参数JAVA类型
     * @param username 用户名(HTTP Basic Authentication) 
     * @param password 密码(HTTP Basic Authentication)
     * @return 成功返回<code>Object</code>, 失败或异常返回null.
     */
    public static Object getObjectByAxisCallClient(String webServiceAddr, String webServiceMethod, String soapActionURI,
            String targetNamespace, Object[] inputValues, String[] inputNames, String[] inputXmlTypes, Class<?>[] inputJavaTypes, 
            String username, String password) {
        Object resObj = null;
        try {
            if (call == null) {
                Service service = new Service();
                call = (Call) service.createCall();
            }
            // 设置wsdl
            call.setTargetEndpointAddress(new URL(webServiceAddr));
            // 定义参数对象
            call.setUseSOAPAction(true);
            call.setSOAPActionURI(soapActionURI + webServiceMethod);
            // 设置访问的方法名
            call.setOperationName(webServiceMethod);
            OperationDesc oper = new OperationDesc();
            if (!ArrayUtils.isEmpty(inputNames)) {
                for (int i = 0; i < inputNames.length; i++) {
                    // String类型
                    oper.addParameter(new QName(targetNamespace, inputNames[i]), new QName(targetNamespace, inputXmlTypes[i]),
                            inputJavaTypes[i], ParameterDesc.IN, false, false);
                }
            }
            oper.setReturnType(XMLType.XSD_ANYTYPE);
            call.setOperation(oper);
            call.setTimeout(30000);
 
            // 访问权限验证.
            if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
                call.setUsername(username);
                call.setPassword(password);
            }
            resObj = call.invoke(inputValues);
        } catch (Exception e) {
            // TODO Auto/generated catch block
            String message = messageFormat("使用AxisCallClient调用WebService地址｛{0}｝方法｛{1}｝出现错误！", webServiceAddr, webServiceMethod);
            logger.error(message, e);
            try {
				throw new Exception(message);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        return resObj;
    }
 
    /**
     * 通过Axis2 取得Web Services 结果.
     * 可以使用身份验证(HTTP Basic Auchntication)
     * 
     * @param webServiceAddr Web Service的URL地址（需要加?wsdl）
     * @param webServiceMethod WebService所操作的方法名称
     * @param targetNamespace  WebServiceNamespace
     * @param inputValues 对所操作方法所需要输入的参数值，如果些操作方法无参则 使用 new Object[] {}，不允许输入 null
     * @param returnJavaTypes 指定所返回结果数据的Java Class类型数组, 如果无参数则使用 new Object[] {}，不允许输入 null
     * @return 成功返回<code>Object[]</code>, 失败或异常返回null.
     */
/*    public static Object[] geObjectByAxis2RPCClient(String webServiceAddr, String webServiceMethod, String targetNamespace,
            Object[] inputValues, Class<?>[] returnJavaTypes) {
        return geObjectByAxis2RPCClient(webServiceAddr, webServiceMethod, targetNamespace, inputValues, returnJavaTypes, null, null);
    }*/
    /**
     * 通过Axis2 取得Web Services 结果.
     * 可以使用身份验证(HTTP Basic Auchntication)
     * 
     * @param webServiceAddr Web Service的URL地址（需要加?wsdl）
     * @param webServiceMethod WebService所操作的方法名称
     * @param targetNamespace  WebServiceNamespace
     * @param inputValues 对所操作方法所需要输入的参数值，如果些操作方法无参则 使用 new Object[] {}，不允许输入 null
     * @param returnJavaTypes 指定所返回结果数据的Java Class类型数组, 如果无参数则使用 new Object[] {}，不允许输入 null
     * @param username 用户名(HTTP Basic Auchntication)
     * @param password 密码(HTTP Basic Auchntication)
     * @return 成功返回<code>Object[]</code>, 失败或异常返回null.
     */
   /* public static Object[] geObjectByAxis2RPCClient(String webServiceAddr, String webServiceMethod, String targetNamespace, 
            Object[] inputValues, Class<?>[] returnJavaTypes, String username, String password) {
        Object[] resultObjArr = null;
        try {
            // 使用RPC方式调用WebService
            if (serviceClient == null) {
                serviceClient = new RPCServiceClient();
            }
            Options options = serviceClient.getOptions();
            options.setAction(targetNamespace + webServiceMethod);
            // 指定调用WebService的URL
            EndpointReference targetEPR = new EndpointReference(webServiceAddr);
            options.setTo(targetEPR);
             
            if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
                //身份验证(HTTP Basic Auchntication)
                HttpTransportProperties.Authenticator basicAuth = new HttpTransportProperties.Authenticator();
                basicAuth.setPreemptiveAuthentication(true);
                basicAuth.setUsername(username);
                basicAuth.setPassword(password);
                options.setProperty(HTTPConstants.AUTHENTICATE, basicAuth);
            }
             
            //连接超时间设置 1min.
            options.setTimeOutInMilliSeconds(60000);
             
            QName opAddEntry = new QName(targetNamespace, webServiceMethod);
            resultObjArr = serviceClient.invokeBlocking(opAddEntry, inputValues, returnJavaTypes);
        } catch (Exception e) {
            // TODO: handle exception
            resultObjArr = null;
            String message = messageFormat("使用Axis2RPCClient调用WebService地址｛{0}｝方法｛{1}｝出现错误！", webServiceAddr, webServiceMethod);
            logger.error(message, e);
        }
        return resultObjArr;
    }
 */
    /**
     * 通过Xfire动态调用WebService并取得结果.
     * 
     * @param webServiceAddr Web Service的URL地址（需要加?wsdl）
     * @param webServiceMethod WebService所操作的方法名称
     * @param inputValues 对所操作方法所需要输入的参数值，如果些操作方法无参则 使用 new Object[] {}，不允许输入 null
     * @return 成功返回<code>Object[]</code>, 失败或异常返回null.
     */
  /*  public static Object[] getObjectByXfireClient(String webServiceAddr, String webServiceMethod, Object[] inputValues) {
        Object[] resultObjArr = null;
        try {
            org.codehaus.xfire.client.Client client = new org.codehaus.xfire.client.Client(new URL(webServiceAddr));
            resultObjArr = client.invoke(webServiceMethod, inputValues);
        } catch (Exception e) {
            // TODO: handle exception
            resultObjArr = null;
            String message = messageFormat("使用XfireClient调用WebService地址｛{0}｝方法｛{1}｝出现错误！", webServiceAddr, webServiceMethod);
            logger.error(message, e);
        }
        return resultObjArr;
    }*/
     
     
    public static void main(String[] args) {
        System.out.println(WebServiceUtils.getProValByKey("webservice.portal.task.addr"));
    }
}