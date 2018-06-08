package com.quick.portal.security.synchrodata.internal;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.quick.portal.security.synchrodata.bjcadata.SynchronizedDataConstants;


/**
 * 
 * @author cxh
 * @版本: V1.0
 * @创建日期: 2018-03-11
 * @类描述: dom解析xml工具类。
 * @修改时间:
 * @修改备注:
 */
public class Dom4jUtil {
	
	public static void main(String [] args) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("USER_ID", "1400");
		map.put("USER_NAME", "DD060302");
		map.put("USER_PASSWORD", "11222");
		map.put("USER_REAL_NAME" , "aa");
		map.put("USER_STATE", "1");
		map.put("JOB_ID", "22");
		map.put("JOB_NAME", "ddd");
		map.put("USER_ADDR", "江苏省电力公司");
		map.put("USER_TEL", "1358899111");
		map.put("CRE_TIME", "2015-03-31");
		map.put("UPD_TIME", "2015-03-31");
	
		
		
		Map<String,String> map1 = new HashMap<String,String>();
		map1.put("USER_ID", "1600");
		map1.put("USER_NAME", "DD070302");
		map1.put("USER_PASSWORD", "5666");
		map1.put("USER_REAL_NAME" , "bb");
		map1.put("USER_STATE", "1");
		map1.put("JOB_ID", "262");
		map1.put("JOB_NAME", "ddd");
		map1.put("USER_ADDR", "江苏省电力公司");
		map1.put("USER_TEL", "111199111");
		map1.put("CRE_TIME", "2015-03-31");
		map1.put("UPD_TIME", "2015-03-31");
		List<Map<String,String>> listMap = new ArrayList<Map<String,String>>();
		listMap.add(map);
		listMap.add(map1);
		String xml = writeFormatXML(listMap);
	}
	
	/**
	 * 格式化输出document到控制台
	 * @param str 字符串类型的document
	 * @throws DocumentException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static void prettyPrintDocument(String str)
			throws DocumentException, UnsupportedEncodingException, IOException {
		Document document = DocumentHelper.parseText(str);
		OutputFormat format = OutputFormat.createPrettyPrint(); 
		format.setEncoding("UTF-8");
		
		XMLWriter writer = new XMLWriter(System.out, format);
		writer.write(document);   
		writer.close();
	}
	
	/**
	 *返回xml
	 * @param filePath
	 * @return
	 */
	public static String creatErrXmlFile(String status,String msg) {
		StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<response>").append("<status>").append(status).append("</status>")
			 .append("<msg>").append(msg).append("</msg>")
			 .append("<total>").append(SynchronizedDataConstants.ZERO_TOTAL).append("</total>")
			 .append("<response>");
			String xml = sb.toString();
		return xml;
	}
	
	public  static <K,V>  String writeFormatXML(List<Map<K,V>> dataMap) throws IOException, DocumentException{
		String str = "";
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response");
		Element body = root.addElement("body");
		body.addElement("status").addText(""+SynchronizedDataConstants.SUCCESS_STATUS+"");
		body.addElement("msg").addText("");
		body.addElement("total").addText(""+dataMap.size()+"");
		if(dataMap.size() >0 && !dataMap.isEmpty()){
				for(int i = 0; i < dataMap.size(); i++){
					Map<K,V> map = dataMap.get(i);
					Element items= body.addElement("items").addText("");
					Iterator<Map.Entry<K, V>> iterator = map.entrySet().iterator();
					while(iterator.hasNext()){
						Map.Entry<K, V> entry = iterator.next();
						Element fieldInfo = items.addElement("fieldinfo");
						fieldInfo.addElement("fieldname").addText(entry.getKey().toString());
						String fieldText = entry.getValue() == null ? "":entry.getValue().toString();
						fieldInfo.addElement("fieldvale").addText(fieldText);
					}
				}
		}
		str = doc.asXML();
		//格式化输出拼装的xml
		prettyPrintDocument(str);
		return str;
	}
	
	
	public  static <K,V>  String writeFormatXML1(List<Map<K,V>> dataMap) throws IOException, DocumentException{
		String str = "";
		if(dataMap.size() >0 && !dataMap.isEmpty()){
			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement("response");
			    Element body = root.addElement("body");
			    body.addElement("total").addText(""+dataMap.size()+"");
				for(int i = 0; i < dataMap.size(); i++){
					Map<K,V> map = dataMap.get(i);
					Element items= body.addElement("items").addText("");
					Iterator<Map.Entry<K, V>> iterator = map.entrySet().iterator();
					while(iterator.hasNext()){
						Map.Entry<K, V> entry = iterator.next();
						Element fieldInfo = items.addElement("fieldinfo");
						fieldInfo.addElement("fieldname").addText(entry.getKey().toString());
						String fieldText = entry.getValue() == null ? "":entry.getValue().toString();
						fieldInfo.addElement("fieldvale").addText(fieldText);
					}
				}
			str = doc.asXML();
			//格式化输出拼装的xml
			//prettyPrintDocument(str);
		}
		return str;
	}
	
}	

