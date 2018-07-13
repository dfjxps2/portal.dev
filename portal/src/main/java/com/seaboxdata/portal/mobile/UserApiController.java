package com.seaboxdata.portal.mobile;

import com.quick.core.base.SysApiController;
import com.quick.core.util.common.QCookie;
import com.quick.portal.sysUser.ISysUserDao;
import com.quick.portal.sysUser.ISysUserService;
import com.quick.portal.sysUser.SysUserDO;
import com.quick.portal.web.model.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by GaoPh on 2018/7/9.
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/mobile/sysUser")
public class UserApiController extends SysApiController {
    @Resource(name = "sysUserService")
    private ISysUserService sysUserService;

    @Autowired
    private ISysUserDao<SysUserDO> iSysUserDao;
    //App修改密码
    @RequestMapping(value = "/changPwApp")
    @ResponseBody
    public DataResult changeAppPassword(Integer user_id, String new_password, HttpServletResponse res)  {
        SysUserDO sysUserDO = new SysUserDO();
        sysUserDO.setUser_password(new_password);
        sysUserDO.setUser_id(user_id);
        iSysUserDao.updatePassword(sysUserDO);
        DataResult result = new DataResult();
        result.setCode(1);
        result.setMsg("OK");
        result.setVersion("1.0");
        return result;
    }

    //App密码修改--旧密码校验
    @RequestMapping(value="checkAppOldPw")
    @ResponseBody
    public DataResult checkAppOldPw(HttpServletResponse res,Integer user_id, String user_old_pw) throws IOException {
        Map<String,Object> map = new HashMap<>();
        DataResult result = new DataResult();
        map.put("user_password",user_old_pw);
        map.put("user_id",user_id);
        List<Map<String,Object>> list = sysUserService.select(map);
        if(list.size()!=0){
            result.setCode(1);
            result.setMsg("OK");
            result.setVersion("1.0");
        }else {
            result.setCode(0);
            result.setMsg("输入的密码有错，请重新输入！");
            result.setVersion("1.0");
        }
        return result;
    }

}
