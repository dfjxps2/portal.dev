/**
 * <h3>标题 : potal统一门户-metric_privilege </h3>
 * <h3>描述 : menu_privilege服务实现类</h3>
 * <h3>日期 : 2018-04-13</h3>
 * <h3>版权 : Copyright (C) 北京东方金信科技有限公司</h3>
 * 
 * <p>
 * @author 你自己的姓名 cxh@seaboxdata.com
 * @version <b>v1.0.0</b>
 *          
 * <b>修改历史:</b>
 * -------------------------------------------
 * 修改人 修改日期 修改描述
 * -------------------------------------------
 *          
 *          
 * </p>
 */
package com.quick.portal.security.synchrodata.bjcadata;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bjca.uums.client.bean.DepartmentInformation;
import com.bjca.uums.client.bean.LoginInformation;
import com.bjca.uums.client.bean.PersonInformation;
import com.bjca.uums.client.bean.RoleInformation;

/**
 * syncDataService服务实现类
 */
@Transactional

@Service("syncDataService")
public class SynchronizedDataServiceImpl implements ISynchronizedDataService {

	/**
	 * 构造函数
	 */
	public SynchronizedDataServiceImpl() {
	}

	@Autowired
	private ISynchronizedDataDao dao;



	/*
	 * 41 新增机构、 42 修改机构、 43 删除机构
	 * 部门数据同步 
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean synchronizedDeptData(DepartmentInformation department,int operateID)
			throws Exception {
		boolean bool = true;
		int count = 0;
		try {
			//新增机构
			if(operateID == 41){
				//判断重复
				boolean flag = isExistDeptGlobalID(department);
				if(! flag){
					count = insertDeptData(department);
				}else{
					count = updateDeptData(department);
				}
				//修改上级机构编号
				updateSuperDepId(department);
			}else if(operateID == 42){
				//修改机构
				count = updateDeptData(department);
			}else if(operateID == 43){
				// 删除机构	
				count = removeDeptData(department);
			}
			if (count > 0) {
				bool = true;
			} else {
				System.out.println("操作部门表0条数据被处理，请查询！");
				bool = false;
			}
			bool = true;
		} catch (Exception e) {
			bool = false;
			throw new Exception(e.getMessage());
		}
		return bool;
	}
	
	/*
	 * 判断重复
	 * 通过部门编号查询数据是否重复
	 */
	public boolean isExistDeptGlobalID(DepartmentInformation department) {
		boolean bool = false;
		String depGlogbalID = department.getDepartCode();
		int count = dao.isExistDeptGlobalIDByDepGlogbalID(depGlogbalID);
		if(count >0){
			bool = true;
		}else{
			bool = false;
		}
		return bool ;
	}
	/*
	 * 新增部门表数据
	 */
	public int insertDeptData(DepartmentInformation department) {
		return dao.insertDeptData(department);
	}
	
	/*
	 * 修改部门上级编号数据
	 * 查询部门上级编号数据
	 * 
	 */
	public void updateSuperDepId(DepartmentInformation department) {
		Map<String,Object> paramMap = new HashMap();
		String supDepGlobalID  = null;
		String deptID = null;
		//公服标识查询部门上级编号数据
		List<Map<String, Object>> depList = this.searchFullDeptInfo();
		for (Map<String, Object> m : depList){
		    	//通过上级部门编号查询部门ID
		    	  supDepGlobalID = m.get("SUP_DEP_GLOBAL_ID").toString();
		    	  deptID = m.get("DEP_ID").toString();
		    	  System.out.println("deptID="+deptID+"supDepGlobalID="+supDepGlobalID);
		    	  paramMap.put("supDepGlobalID", supDepGlobalID);
		    	  paramMap.put("deptID", deptID);
				  dao.updateSuperDepId(paramMap);
		  }
		
	}
	/*
	 * 公服标识查询部门上级编号数据
	 */
	public List<Map<String, Object>> searchFullDeptInfo(){
		return dao.searchFullDeptInfo();
	}
	

	/*
	 * 修改部门表数据
	 */
	public int updateDeptData(DepartmentInformation department) {
		int count = dao.updateDeptDataByDeptCode(department);
		return count;
	}
	/*
	 * 删除部门表数据
	 */
	public int removeDeptData(DepartmentInformation department) {
		String deptCode = department.getDepartCode();
		int count = dao.removeDeptDataByDeptCode(deptCode);
		return count;
	}
	
	



	/*
	 * 角色数据同步
	 *  step1:删除角色临时表数据 step2:插入角色临时表数据 step3:MERGE角色表数据
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean synchronizedRoleData(RoleInformation roleInformation)
			throws Exception {
		boolean bool = true;
		int count = 0;
		removeTmpRoleData();
		try {
			count = insertTmpRoleData(roleInformation);
			if (count > 0) {
				mergeRoleData();
			} else {
				System.out.println("插入角色临时表0条数据，请查询！");
				bool = false;
			}
			bool = true;
		} catch (Exception e) {
			bool = false;
			throw new Exception(e.getMessage());
		}
		return bool;
	}

	/*
	 * 删除角色临时表数据
	 */
	public void removeTmpRoleData() {
		dao.removeTmpRoleData();
	}

	/*
	 * 新增角色临时表数据
	 */
	public int insertTmpRoleData(RoleInformation role) {
		return dao.insertTmpRoleData(role);
	}

	/*
	 * MERGE角色表数据
	 */
	public void mergeRoleData() {
		dao.mergeRoleData();
	}

	/*
	 * 11 新增用户 、12 修改用户、 13 删除用户
	 * 
	 * 用户数据同步 
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean synchronizedPersonData(PersonInformation person,LoginInformation loginInfo,int operateID)
			throws Exception {
		boolean bool = true;
		int count = 0;
		//用户登录名
		person.setUserDefault5(loginInfo.getLoginName());
		//用户昵称
		person.setUserDefault4(loginInfo.getLoginNickName());
		//用户密码
		person.setUserDefault3(loginInfo.getLoginPwd());
		
		//11 新增用户 、12 修改用户、 13 删除用户
		try {
			//新增用户
			if(operateID == 11){
				boolean isFlag = isExistUserGlobalID(person);
				if(! isFlag){
					count = insertPersonData(person);
				}else{
					count = updatePersonData(person);
				}
				
			}else if(operateID == 12){
			// 修改用户
				count = updatePersonData(person);
			//删除用户
			}else if(operateID == 13){
				count = removePersonData(person);
			}
			if (count > 0) {
				mergePersonDeptRelaDataInfo(person);
				bool = true;
			} else {
				System.out.println("操作用户表0条数据被处理，请查询！");
				bool = false;
			}
			bool = true;
		} catch (Exception e) {
			bool = false;
			throw new Exception(e.getMessage());
		}
		return bool;
	}
	
	/*
	 * 判断重复
	 * 通过用户编号查询数据是否重复
	 */
	public boolean isExistUserGlobalID(PersonInformation person) {
		boolean bool = false;
		String userGlogbalID = person.getUniqueid();
		int count = dao.isExistUserGlobalIDByUserGlogbalID(userGlogbalID);
		if(count >0){
			bool = true;
		}else{
			bool = false;
		}
		return bool ;
	}
	
	/*
	 * 通过用户编号修改用户表数据
	 */
	public int updatePersonData(PersonInformation person) {
		return dao.updatePersonData(person);
	}
	
	
	/*
	 * 新增用户数据
	 */
	public int insertPersonData(PersonInformation person) {
		return dao.insertPersonData(person);
	}
	
	/*
	 * 通过用户编号删除用户表数据
	 */
	public int removePersonData(PersonInformation person) {
		String uid = person.getUniqueid();
		return dao.removePersonDataByID(uid);
	}
	
	

	

	/*
	 * MERGE角色表数据
	 */
	public void mergePersonData() {
		dao.mergePersonData();
	}
	/*
	 * 同步用户与部门关系
	 * 1通过用户编号删除用户与部门关系
	 * 2新增用户与部门关系
	 */
	@Transactional(rollbackFor = Exception.class)
	public void mergePersonDeptRelaDataInfo(PersonInformation person){
		String globalID = person.getUniqueid();
		//通过用户编号删除用户与部门关系
		removePersonDeptRelaDataByUserID(globalID);
		insertPersonDeptRelaData(person);
	}
	
	
	/*
	 * 通过用户编号查询用户ID
	 */
	public String searchPersonByGlobalID(String userGlobalID) {
		String userID = "";
		List<Map<String, Object>> retList = dao.searchPersonByGlobalID(userGlobalID);
		if(null != retList && retList.size() >0){
			Map<String, Object> mp = retList.get(0);
			userID = mp.get("USER_ID").toString();
		}
		return userID;
	}
	
	/*
	 * 通过部门编号查询部门ID
	 */
	public String searchDeptByGlobalID(String deptGlobalID) {
		String depID = "";
		List<Map<String, Object>> retList = dao.searchDeptByGlobalID(deptGlobalID);
		if(null != retList && retList.size() >0){
			Map<String, Object> mp = retList.get(0);
			depID = mp.get("DEP_ID").toString();
		}
		return depID;
	}
	
	/*
	 * 通过用户编号删除用户与部门关系
	 */
	public boolean removePersonDeptRelaDataByUserID(String userID) {
		return dao.removePersonDeptRelaDataByUserID(userID);
	}
	
	/*
	 * 删除用户与部门关系临时表数据
	 */
	public void removeTmpPersonDeptRelaData() {
		dao.removeTmpPersonDeptRelaData();
	}
	/*
	 * 新增用户与部门关系表数据
	 */
	public void insertPersonDeptRelaData(PersonInformation person){
		Map<String,Object> paramMap = new HashMap();
		String userGlobalID = person.getUniqueid();
		// 通过部门编号查询部门ID
		String userID = searchPersonByGlobalID(userGlobalID);
        paramMap.put("userID",userID);
        String depID = "";
		Collection collection = person.getDeparts();
		Iterator it = collection.iterator();
		while (it.hasNext()) {
			DepartmentInformation depart = (DepartmentInformation) it
					.next(); 
			System.out.println("DepartCode=" + depart.getDepartCode());
			System.out.println("Default=" + depart.getDepartDefault());
			System.out.println("DepartUpcode=" + depart.getDepartUpcode());
			depID = searchDeptByGlobalID(depart.getDepartCode());
			paramMap.put("depID", depID);
			dao.insertPersonDeptRelaData(paramMap);
		}
	}
	
	
	/*public static void main(String[] args) {
		SynchronizedDataServiceImpl testService = new SynchronizedDataServiceImpl();
		boolean bool = false;
		RoleInformation roleInformation = new RoleInformation();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		roleInformation.setUrFlowno(uuid);
		roleInformation.setUserRoleCode("Test_code");
		roleInformation.setUserRoleName("Test_name");
		roleInformation.setUserRoleDescribe("Test_desc");
		try {
			 bool = testService.synchronizedRoleData(roleInformation);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	/*
	 * 批量部门信息
	 * (non-Javadoc)
	 * @see com.quick.portal.security.synchrodata.bjcadata.ISynchronizedDataService#synchronizedBatchDeptData(java.util.Collection)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean synchronizedBatchDeptData(Collection collection)
			throws Exception {
		boolean bool = true;
		int count = 0;
		//删除公服系统所有机构数据
		removeAllDeptData();
		Map<String,Object> paramMap = new HashMap();
		Iterator it = collection.iterator();
		while (it.hasNext()) {
			DepartmentInformation department = (DepartmentInformation) it
					.next(); 
			String departupcode = department.getDepartUpcode();
			String departcode = department.getDepartCode();
			String departname = department.getDepartName();
			String departDesc = department.getDepartDescript();
			String departDeflt = department.getDepartDefault();
			System.out.println("部门上级编码====" + departupcode);
			System.out.println("部门编码为=====" + departcode);
			System.out.println("部门名称=====" + departname);
			paramMap.put("departCode", department.getDepartCode());
			paramMap.put("departUpcode",department.getDepartUpcode());
			paramMap.put("departName",department.getDepartName());
			dao.saveDeptData(paramMap);
		}
		try {
			if (! collection.isEmpty()) {
				bool = true;
			} else {
				System.out.println("插入部门临时表0条数据，请查询！");
				bool = false;
			}
			bool = true;
		} catch (Exception e) {
			bool = false;
			throw new Exception(e.getMessage());
		}
		return bool;
	}
	
	/*
	 * 删除公服系统所有机构数据
	 */
	public void removeAllDeptData() {
		dao.removeAllDeptData();
	}
	
	
	
	
}