package com.quick.portal.security.synchrodata.internal;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserDataSyncMain {
	private UserDataSyncMain() {
	}

	public static void main(String[] args) {
	   test();
	}
	
	private static void test(){
		String userName = null;
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "/spring/app-webclient.xml" });
		IDataSynchronizedWsdl client = (IDataSynchronizedWsdl) context.getBean("client");
		String response = client.getUsersDataByIDOrName(userName);
		System.out.println("Response: test  " + response);
		System.exit(0);
	}
	

}
