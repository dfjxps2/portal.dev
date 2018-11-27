/**
 * Department.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Aug 08, 2005 (11:49:10 PDT) WSDL2Java emitter.
 */

package com.quick.portal.security.synchrodata.bjcadata.uumsinterface.services.Department;

public interface Department extends java.rmi.Remote {
    public com.quick.portal.security.synchrodata.bjcadata.uums.client.bean.DepartmentInformation findDepartByDepartID(String in0) throws java.rmi.RemoteException;
    public Object[] getAllDepart() throws java.rmi.RemoteException;
    public com.quick.portal.security.synchrodata.bjcadata.uums.client.bean.DepartmentInformation findDepartByDepartCode(String in0) throws java.rmi.RemoteException;
    public com.quick.portal.security.synchrodata.bjcadata.uums.client.bean.DepartmentInformation findDepartByDepartCodeForDC(String in0) throws java.rmi.RemoteException;
    public Object[] getAllDepartForDC() throws java.rmi.RemoteException;
}
