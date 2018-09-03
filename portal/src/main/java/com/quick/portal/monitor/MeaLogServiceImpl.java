package com.quick.portal.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Sc on 2018/8/27.
 */
@Transactional
@Service("MeaLog")
public class MeaLogServiceImpl implements IMeaLogService{

    @Autowired
    private IMeaLogDao meaLogDao;

    public void sendLog(Map map){
        meaLogDao.insert(map);
    }


}
