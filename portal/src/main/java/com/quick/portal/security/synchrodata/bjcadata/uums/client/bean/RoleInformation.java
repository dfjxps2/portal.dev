/**
 * RoleInformation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Aug 08, 2005 (11:49:10 PDT) WSDL2Java emitter.
 */

package com.quick.portal.security.synchrodata.bjcadata.uums.client.bean;

public class RoleInformation  implements java.io.Serializable {
    private String urFlowno;
    private String userRoleCode;
    private String userRoleDescribe;
    private String userRoleName;
    private String userRoleState;

    public RoleInformation() {
    }

    public RoleInformation(
           String urFlowno,
           String userRoleCode,
           String userRoleDescribe,
           String userRoleName,
           String userRoleState) {
           this.urFlowno = urFlowno;
           this.userRoleCode = userRoleCode;
           this.userRoleDescribe = userRoleDescribe;
           this.userRoleName = userRoleName;
           this.userRoleState = userRoleState;
    }


    /**
     * Gets the urFlowno value for this RoleInformation.
     * 
     * @return urFlowno
     */
    public String getUrFlowno() {
        return urFlowno;
    }


    /**
     * Sets the urFlowno value for this RoleInformation.
     * 
     * @param urFlowno
     */
    public void setUrFlowno(String urFlowno) {
        this.urFlowno = urFlowno;
    }


    /**
     * Gets the userRoleCode value for this RoleInformation.
     * 
     * @return userRoleCode
     */
    public String getUserRoleCode() {
        return userRoleCode;
    }


    /**
     * Sets the userRoleCode value for this RoleInformation.
     * 
     * @param userRoleCode
     */
    public void setUserRoleCode(String userRoleCode) {
        this.userRoleCode = userRoleCode;
    }


    /**
     * Gets the userRoleDescribe value for this RoleInformation.
     * 
     * @return userRoleDescribe
     */
    public String getUserRoleDescribe() {
        return userRoleDescribe;
    }


    /**
     * Sets the userRoleDescribe value for this RoleInformation.
     * 
     * @param userRoleDescribe
     */
    public void setUserRoleDescribe(String userRoleDescribe) {
        this.userRoleDescribe = userRoleDescribe;
    }


    /**
     * Gets the userRoleName value for this RoleInformation.
     * 
     * @return userRoleName
     */
    public String getUserRoleName() {
        return userRoleName;
    }


    /**
     * Sets the userRoleName value for this RoleInformation.
     * 
     * @param userRoleName
     */
    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }


    /**
     * Gets the userRoleState value for this RoleInformation.
     * 
     * @return userRoleState
     */
    public String getUserRoleState() {
        return userRoleState;
    }


    /**
     * Sets the userRoleState value for this RoleInformation.
     * 
     * @param userRoleState
     */
    public void setUserRoleState(String userRoleState) {
        this.userRoleState = userRoleState;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof RoleInformation)) return false;
        RoleInformation other = (RoleInformation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.urFlowno==null && other.getUrFlowno()==null) || 
             (this.urFlowno!=null &&
              this.urFlowno.equals(other.getUrFlowno()))) &&
            ((this.userRoleCode==null && other.getUserRoleCode()==null) || 
             (this.userRoleCode!=null &&
              this.userRoleCode.equals(other.getUserRoleCode()))) &&
            ((this.userRoleDescribe==null && other.getUserRoleDescribe()==null) || 
             (this.userRoleDescribe!=null &&
              this.userRoleDescribe.equals(other.getUserRoleDescribe()))) &&
            ((this.userRoleName==null && other.getUserRoleName()==null) || 
             (this.userRoleName!=null &&
              this.userRoleName.equals(other.getUserRoleName()))) &&
            ((this.userRoleState==null && other.getUserRoleState()==null) || 
             (this.userRoleState!=null &&
              this.userRoleState.equals(other.getUserRoleState())));
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
        if (getUrFlowno() != null) {
            _hashCode += getUrFlowno().hashCode();
        }
        if (getUserRoleCode() != null) {
            _hashCode += getUserRoleCode().hashCode();
        }
        if (getUserRoleDescribe() != null) {
            _hashCode += getUserRoleDescribe().hashCode();
        }
        if (getUserRoleName() != null) {
            _hashCode += getUserRoleName().hashCode();
        }
        if (getUserRoleState() != null) {
            _hashCode += getUserRoleState().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
