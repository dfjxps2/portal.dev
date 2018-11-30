/**
 * LoginInformation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Aug 08, 2005 (11:49:10 PDT) WSDL2Java emitter.
 */

package com.quick.portal.security.synchrodata.bjcadata.uums.client.bean;

public class LoginInformation  implements java.io.Serializable {
    private String loginName;
    private String loginNickName;
    private String loginPwd;
    private String loginType;

    public LoginInformation() {
    }

    public LoginInformation(
           String loginName,
           String loginNickName,
           String loginPwd,
           String loginType) {
           this.loginName = loginName;
           this.loginNickName = loginNickName;
           this.loginPwd = loginPwd;
           this.loginType = loginType;
    }


    /**
     * Gets the loginName value for this LoginInformation.
     * 
     * @return loginName
     */
    public String getLoginName() {
        return loginName;
    }


    /**
     * Sets the loginName value for this LoginInformation.
     * 
     * @param loginName
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }


    /**
     * Gets the loginNickName value for this LoginInformation.
     * 
     * @return loginNickName
     */
    public String getLoginNickName() {
        return loginNickName;
    }


    /**
     * Sets the loginNickName value for this LoginInformation.
     * 
     * @param loginNickName
     */
    public void setLoginNickName(String loginNickName) {
        this.loginNickName = loginNickName;
    }


    /**
     * Gets the loginPwd value for this LoginInformation.
     * 
     * @return loginPwd
     */
    public String getLoginPwd() {
        return loginPwd;
    }


    /**
     * Sets the loginPwd value for this LoginInformation.
     * 
     * @param loginPwd
     */
    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }


    /**
     * Gets the loginType value for this LoginInformation.
     * 
     * @return loginType
     */
    public String getLoginType() {
        return loginType;
    }


    /**
     * Sets the loginType value for this LoginInformation.
     * 
     * @param loginType
     */
    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof LoginInformation)) return false;
        LoginInformation other = (LoginInformation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.loginName==null && other.getLoginName()==null) || 
             (this.loginName!=null &&
              this.loginName.equals(other.getLoginName()))) &&
            ((this.loginNickName==null && other.getLoginNickName()==null) || 
             (this.loginNickName!=null &&
              this.loginNickName.equals(other.getLoginNickName()))) &&
            ((this.loginPwd==null && other.getLoginPwd()==null) || 
             (this.loginPwd!=null &&
              this.loginPwd.equals(other.getLoginPwd()))) &&
            ((this.loginType==null && other.getLoginType()==null) || 
             (this.loginType!=null &&
              this.loginType.equals(other.getLoginType())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getLoginName() != null) {
            _hashCode += getLoginName().hashCode();
        }
        if (getLoginNickName() != null) {
            _hashCode += getLoginNickName().hashCode();
        }
        if (getLoginPwd() != null) {
            _hashCode += getLoginPwd().hashCode();
        }
        if (getLoginType() != null) {
            _hashCode += getLoginType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
