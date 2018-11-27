/**
 * UserServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Aug 08, 2005 (11:49:10 PDT) WSDL2Java emitter.
 */

package com.quick.portal.security.synchrodata.bjcadata.uumsinterface.services.User;

import com.quick.portal.security.synchrodata.bjcadata.util.Config;

public class UserServiceLocator extends org.apache.axis.client.Service implements com.quick.portal.security.synchrodata.bjcadata.uumsinterface.services.User.UserService {

    public UserServiceLocator() {
    }


    public UserServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public UserServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for User
    private static String  User_address =null;
static
{
	Config config=new Config();
	User_address=config.getProperValue("User_address");
	System.out.println("User_address::"+User_address);
}
    public String getUserAddress() {
        return User_address;
    }

    // The WSDD service name defaults to the port name.
    private String UserWSDDServiceName = "User";

    public String getUserWSDDServiceName() {
        return UserWSDDServiceName;
    }

    public void setUserWSDDServiceName(String name) {
        UserWSDDServiceName = name;
    }

    public com.quick.portal.security.synchrodata.bjcadata.uumsinterface.services.User.User getUser() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(User_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getUser(endpoint);
    }

    public com.quick.portal.security.synchrodata.bjcadata.uumsinterface.services.User.User getUser(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.quick.portal.security.synchrodata.bjcadata.uumsinterface.services.User.UserSoapBindingStub _stub = new com.quick.portal.security.synchrodata.bjcadata.uumsinterface.services.User.UserSoapBindingStub(portAddress, this);
            _stub.setPortName(getUserWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setUserEndpointAddress(String address) {
        User_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.quick.portal.security.synchrodata.bjcadata.uumsinterface.services.User.User.class.isAssignableFrom(serviceEndpointInterface)) {
                com.quick.portal.security.synchrodata.bjcadata.uumsinterface.services.User.UserSoapBindingStub _stub = new com.quick.portal.security.synchrodata.bjcadata.uumsinterface.services.User.UserSoapBindingStub(new java.net.URL(User_address), this);
                _stub.setPortName(getUserWSDDServiceName());
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
        if ("User".equals(inputPortName)) {
            return getUser();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://processor.client.uums.bjca.com", "UserService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://processor.client.uums.bjca.com", "User"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {
        
if ("User".equals(portName)) {
            setUserEndpointAddress(address);
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
