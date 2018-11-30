/**
 * DepartmentServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Aug 08, 2005 (11:49:10 PDT) WSDL2Java emitter.
 */

package com.quick.portal.security.synchrodata.bjcadata.uumsinterface.services.Department;

import com.quick.portal.security.synchrodata.bjcadata.util.Config;

public class DepartmentServiceLocator extends org.apache.axis.client.Service implements com.quick.portal.security.synchrodata.bjcadata.uumsinterface.services.Department.DepartmentService {

    public DepartmentServiceLocator() {
    }


    public DepartmentServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public DepartmentServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Department
    private static String Department_address = null;

    static
    {
    	Config config=new Config();
    	Department_address=	config.getProperValue("deptAddress");
    	System.out.println("Department_address::"+Department_address);
    }
    public String getDepartmentAddress() {
        return Department_address;
    }

    // The WSDD service name defaults to the port name.
    private String DepartmentWSDDServiceName = "Department";

    public String getDepartmentWSDDServiceName() {
        return DepartmentWSDDServiceName;
    }

    public void setDepartmentWSDDServiceName(String name) {
        DepartmentWSDDServiceName = name;
    }

    public com.quick.portal.security.synchrodata.bjcadata.uumsinterface.services.Department.Department getDepartment() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Department_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getDepartment(endpoint);
    }

    public com.quick.portal.security.synchrodata.bjcadata.uumsinterface.services.Department.Department getDepartment(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.quick.portal.security.synchrodata.bjcadata.uumsinterface.services.Department.DepartmentSoapBindingStub _stub = new com.quick.portal.security.synchrodata.bjcadata.uumsinterface.services.Department.DepartmentSoapBindingStub(portAddress, this);
            _stub.setPortName(getDepartmentWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setDepartmentEndpointAddress(String address) {
        Department_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.quick.portal.security.synchrodata.bjcadata.uumsinterface.services.Department.Department.class.isAssignableFrom(serviceEndpointInterface)) {
                com.quick.portal.security.synchrodata.bjcadata.uumsinterface.services.Department.DepartmentSoapBindingStub _stub = new com.quick.portal.security.synchrodata.bjcadata.uumsinterface.services.Department.DepartmentSoapBindingStub(new java.net.URL(Department_address), this);
                _stub.setPortName(getDepartmentWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("Department".equals(inputPortName)) {
            return getDepartment();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://processor.client.uums.bjca.com", "DepartmentService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://processor.client.uums.bjca.com", "Department"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {
        
if ("Department".equals(portName)) {
            setDepartmentEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
