package com.quick.portal.security.synchrodata.internal;

import java.io.IOException;
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
	 * 同步单个用户数据
	 */
	public String getUsersDataByIDOrName(String userName){
		String xml = null;
		List<Map<String, Object>> retList = userDataSyncDao.getUsersDataByIDOrName(userName);
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
	
	/*
	 * 同步批量用户数据
	 */
	public String getAllUserData(){
		String xml = null;
		List dataMap = userDataSyncDao.getAllUsersData();
		if(dataMap.isEmpty() || dataMap.size()==0){
			xml = Dom4jUtil.creatErrXmlFile(SynchronizedDataConstants.SUCCESS_STATUS,SynchronizedDataConstants.DATA_ISNULL_FAIL_MSG);
		}else{
			try {
				xml = Dom4jUtil.writeFormatXML(dataMap);
			} catch (IOException e) {
				xml = Dom4jUtil.creatErrXmlFile(SynchronizedDataConstants.FAIL_STATUS,e.getLocalizedMessage());
			} catch (DocumentException e) {
				xml = Dom4jUtil.creatErrXmlFile(SynchronizedDataConstants.FAIL_STATUS,e.getLocalizedMessage());
			}
		}
		return xml;
	}
}
