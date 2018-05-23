package com.quick.core.util.mail;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;  
import javax.mail.Session;  
import javax.mail.Transport;  
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage; 

import com.sun.mail.util.MailSSLSocketFactory;

public class mailTool{
	
	 // 设置服务器
	protected static String KEY_SMTP = "mail.smtp.host";
	protected static String VALUE_SMTP = "smtp.qq.com";
    // 服务器验证
	protected static String KEY_PROPS = "mail.smtp.auth";
	protected static boolean VALUE_PROPS = true;
    // 发件人用户名、密码
	protected static String SEND_USER = "3521927451@qq.com";
	protected static String SEND_UNAME = "海建鑫网";
	protected static String SEND_PWD = "cyejixpyqdzzdajf";
     
	 /**  
     * 创建Session对象，此时需要配置传输的协议，是否身份认证  
     */ 
    public static Session createSession(String protocol) {  
        Properties property = new Properties();  
        property.setProperty("mail.transport.protocol", protocol);  
        property.setProperty(KEY_SMTP, VALUE_SMTP);
        property.setProperty(KEY_PROPS, KEY_PROPS);  
 
        MailSSLSocketFactory sf;
		try {
			sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
			property.put("mail.smtp.ssl.enable", "true");
		    property.put("mail.smtp.ssl.socketFactory", sf);
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
       
        
        Session session = Session.getInstance(property);  
          
        // 启动JavaMail调试功能，可以返回与SMTP服务器交互的命令信息  
        // 可以从控制台中看一下服务器的响应信息  
        session.setDebug(true);   
        return session;  
    }  
 
    /**  
     * 传入Session、MimeMessage对象，创建 Transport 对象发送邮件  
     */ 
    public static void sendMail(String targetMail,String title,String msg) throws Exception {  
    	  // 指定使用SMTP协议来创建Session对象  
    	 Session session= mailTool.createSession("smtp"); 
    	   
    	 MimeMessage message = new MimeMessage(session);//由邮件会话新建一个消息对象  
         message.setFrom(new InternetAddress(SEND_USER));//设置发件人的地址  
         message.setRecipient(Message.RecipientType.TO, new InternetAddress(targetMail));//设置收件人,并设置其接收类型为TO  
         message.setSubject(title);//设置标题  
         //设置信件内容  
//         message.setText(mailContent); //发送 纯文本 邮件 todo  
         message.setContent(msg, "text/html;charset=utf-8"); //发送HTML邮件，内容样式比较丰富  
         message.setSentDate(new Date());//设置发信时间  
         message.saveChanges();//存储邮件信息  
         
         Transport transport = session.getTransport();  
         // 发送用户名、密码连接到指定的 smtp 服务器  
         transport.connect( SEND_USER, SEND_PWD);  
         transport.sendMessage(message, message.getAllRecipients());//发送邮件,其中第二个参数是所有已设好的收件人地址  
         transport.close();  
         
    }  
 
   
}