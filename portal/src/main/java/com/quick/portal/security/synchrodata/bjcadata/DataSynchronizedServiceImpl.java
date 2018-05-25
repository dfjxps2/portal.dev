package com.quick.portal.security.synchrodata.bjcadata;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.springframework.stereotype.Service;

/*
 * 提供公服系统WS服务实现类
 */
@Service("dataSynchronizedService")
@WebService(serviceName = "dataSynchronizedService")
public class DataSynchronizedServiceImpl implements IDataSynchronizedService {
	
	@Resource(name = "syncDataInfoService")
	private ISynchronizedDataInfoService syncDataInfoService;
    
	public ISynchronizedDataInfoService getSyncDataInfoService() {
		return syncDataInfoService;
	}

	public void setSyncDataInfoService(
			ISynchronizedDataInfoService syncDataInfoService) {
		this.syncDataInfoService = syncDataInfoService;
	}
	
	/*
	 * operateID:操作类型  用户11、12、13， 角色21、22、23，机构 41、42、43  
	 * operateCode:操作码，同步用户时为用户32位码,同步机构时为机构编码,同步角色时为系统编码
	 * operateType:此参数仅用于区分个人用户和单位用户（此参数基本不用管）,个人用户 1，单位用户 2，其它内容（角色、部门，用户角色关系） 0
	 */
	@Override
	public boolean sendData(int operateID, String operateCode, String operateType) {
		// TODO Auto-generated method stub
		boolean bool = false;
		try {
			bool = syncDataInfoService.synchronizedDataInfo2(operateID, operateCode, operateType);
		} catch (Exception e) {
			bool = false;
			e.printStackTrace();
		}
		return bool;
	}



}
