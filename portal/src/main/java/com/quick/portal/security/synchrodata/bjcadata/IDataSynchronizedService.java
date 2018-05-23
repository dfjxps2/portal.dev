package com.quick.portal.security.synchrodata.bjcadata;

import javax.jws.WebParam;
import javax.jws.WebService;

/*
 * 提供外围WS服务接口
 */
@WebService
public interface IDataSynchronizedService {
	public boolean sendData(@WebParam(name = "operateID") int operateID,
			@WebParam(name = "operateCode") String operateCode,
			@WebParam(name = "operateType") String operateType);
}
