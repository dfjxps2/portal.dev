package com.quick.portal.security.synchrodata.internal;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quick.portal.security.synchrodata.bjcadata.ISynchronizedDataDao;
import com.quick.portal.security.synchrodata.bjcadata.SynchronizedDataConstants;


@Transactional

@Service("userDataSyncService")
public class DataSynchronzedServiceImpl implements IDataSynchronizedService {
	
	@Autowired
	private ISynchronizedDataDao userDataSyncDao;


	/*
	 * 通过应用名称、用户帐号获取单个用户数据接口，返回报文数据。
	 */
	@Override
	public String getUsersDataByUserID(String appName, String userID) {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("appName", appName);
		paramMap.put("userID", userID);
		List<Map<String, Object>> retList = userDataSyncDao.getUsersDataByUserID(paramMap);
		String xml = getXmlInstall(retList) ;
		return xml;
	}
	
	/*
	 * 通过应用名称获取该应用系统下所有的用户应用权限数据接口，返回报文数据
	 */
	@Override
	public String getUserBatchDataByAppName(String appName,
			String startdt, String enddt) {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("appName", appName);
		paramMap.put("startdt", startdt);
		paramMap.put("enddt", enddt);
		List<Map<String, Object>> retList = userDataSyncDao.getUserBatchDataByAppName(paramMap);
		String xml = getXmlInstall(retList) ;
		return xml;
	}

	/*
	 * 通过用户帐号获取机构数据接口，返回报文数据
	 */
	@Override
	public String getOrgDataByUserID(String userID) {
		List<Map<String, Object>> retList = userDataSyncDao.getOrgDataByUserID(userID);
		String xml = getXmlInstall(retList) ;
		return xml;
	}

	/*
	 * 批量获取机构数据接口，返回报文数据
	 */
	@Override
	public String getOrgBatchData() {
		List<Map<String, Object>> retList = userDataSyncDao.getOrgBatchData();
		String xml = getXmlInstall(retList) ;
		return xml;
	}


	public static String getXmlInstall (List<Map<String, Object>> retList ){
		String xml = "";
		if(retList.isEmpty() || retList.size()==0){
			xml = Dom4jUtil.creatErrXmlFile(SynchronizedDataConstants.FAIL_STATUS,SynchronizedDataConstants.DATA_ISNULL_FAIL_MSG);
		}else{
			try {
				xml = Dom4jUtil.writeFormatXML(retList);
			} catch (IOException e) {
				xml = Dom4jUtil.creatErrXmlFile(SynchronizedDataConstants.FAIL_STATUS,e.getLocalizedMessage());
				// TODO Auto-generated catch block
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				xml = Dom4jUtil.creatErrXmlFile(SynchronizedDataConstants.FAIL_STATUS,e.getLocalizedMessage());
			}
		}
		return xml;
	}
}
