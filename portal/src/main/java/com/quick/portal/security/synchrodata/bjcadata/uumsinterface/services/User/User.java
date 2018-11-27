/**
 * User.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Aug 08, 2005 (11:49:10 PDT) WSDL2Java emitter.
 */

package com.quick.portal.security.synchrodata.bjcadata.uumsinterface.services.User;

public interface User extends java.rmi.Remote {
    public Object[] findSystemInfosAccessedByUserID(String in0) throws java.rmi.RemoteException;
    public com.quick.portal.security.synchrodata.bjcadata.uums.client.bean.UserInformation findUserInfosByUserIDForDC(String in0) throws java.rmi.RemoteException;
    public com.quick.portal.security.synchrodata.bjcadata.uums.client.bean.PersonInformation findPersonInfosByUserIDForDC(String in0) throws java.rmi.RemoteException;
    public com.quick.portal.security.synchrodata.bjcadata.uums.client.bean.UnitInformation findUnitInfosByUserIDFroDC(String in0) throws java.rmi.RemoteException;
    public String getAuthorityByUsernameAndPw(String in0, String in1) throws java.rmi.RemoteException;
    public Object[] findCustomContentInfosBySystemCodeAndUserType(String in0, String in1) throws java.rmi.RemoteException;
    public com.quick.portal.security.synchrodata.bjcadata.uums.client.bean.SystemInformation findSystemInfoBySystemCode(String in0) throws java.rmi.RemoteException;
    public com.quick.portal.security.synchrodata.bjcadata.uums.client.bean.UserCredenceInformation findCredenceInfoByUserID(String in0) throws java.rmi.RemoteException;
    public com.quick.portal.security.synchrodata.bjcadata.uums.client.bean.LoginInformation getLoginInformationByUserID(String in0) throws java.rmi.RemoteException;
    public Object[] findRoleInfosBySystemCodeAndUserID(String in0, String in1) throws java.rmi.RemoteException;
    public com.quick.portal.security.synchrodata.bjcadata.uums.client.bean.UserInformation findUserInfosByUserSIDForDC(String in0) throws java.rmi.RemoteException;
    public com.quick.portal.security.synchrodata.bjcadata.uums.client.bean.RoleInformation findRoleInfoByRoleId(String in0) throws java.rmi.RemoteException;
    public com.quick.portal.security.synchrodata.bjcadata.uums.client.bean.DepartmentInformation findDepartByDepartID(String in0) throws java.rmi.RemoteException;
    public com.quick.portal.security.synchrodata.bjcadata.uums.client.bean.SystemInformation findSystemInfoBySystemId(String in0) throws java.rmi.RemoteException;
    public com.quick.portal.security.synchrodata.bjcadata.uums.client.bean.UserInformation findUserInfosByUserID(String in0) throws java.rmi.RemoteException;
    public Object[] findRoleInfosByUserID(String in0) throws java.rmi.RemoteException;
    public Object[] findRoleInfosBySystemID(String in0) throws java.rmi.RemoteException;
    public Object[] findUserInfosBySystemID(String in0) throws java.rmi.RemoteException;
    public int updateUserpw(String in0, String in1) throws java.rmi.RemoteException;
    public com.quick.portal.security.synchrodata.bjcadata.uums.client.bean.PersonInformation findPersonInfosByUserID(String in0) throws java.rmi.RemoteException;
    public com.quick.portal.security.synchrodata.bjcadata.uums.client.bean.UnitInformation findUnitInfosByUserID(String in0) throws java.rmi.RemoteException;
    public com.quick.portal.security.synchrodata.bjcadata.uums.client.bean.RoleInformation findRoleInfoByRoleCode(String in0) throws java.rmi.RemoteException;
    public String findRoleInfosByUserIDForStrType(String in0) throws java.rmi.RemoteException;
    public String getAuthorityByUserIDAndPw(String in0, String in1) throws java.rmi.RemoteException;
    public com.quick.portal.security.synchrodata.bjcadata.uums.client.bean.PersonInformation findWholePersonInfosByUserIDForDC(String in0) throws java.rmi.RemoteException;
    public Object[] findUserInfosBySystemIDForDC(String in0) throws java.rmi.RemoteException;
    public com.quick.portal.security.synchrodata.bjcadata.uums.client.bean.UserInformation findWholeUserInfosByUserIDForDC(String in0) throws java.rmi.RemoteException;
    public String getAuthorityAndSystemIDByUsernameAndPw(String in0, String in1) throws java.rmi.RemoteException;
    public com.quick.portal.security.synchrodata.bjcadata.uums.client.bean.CustomContentInfo findCustomContentInfoByCustomID(String in0) throws java.rmi.RemoteException;
    public Object[] findAllSystemInfos() throws java.rmi.RemoteException;
    public int updateUserpwByUserNameAndPwd(String in0, String in1) throws java.rmi.RemoteException;
    public Object[] findDepartsByUserID(String in0) throws java.rmi.RemoteException;
    public Object[] findResourceInfosByUserID(String in0) throws java.rmi.RemoteException;
    public Object[] findResourceInfosBySystemCodeAndUserID(String in0, String in1) throws java.rmi.RemoteException;
    public Object[] findResourceInfosBySystemCodeAndRolecodeAndUserID(String in0, String in1, String in2) throws java.rmi.RemoteException;
    public Object[] findResourceInfosByRolecode(String in0) throws java.rmi.RemoteException;
}
