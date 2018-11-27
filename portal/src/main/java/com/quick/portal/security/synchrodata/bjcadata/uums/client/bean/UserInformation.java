/**
 * UserInformation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Aug 08, 2005 (11:49:10 PDT) WSDL2Java emitter.
 */

package com.quick.portal.security.synchrodata.bjcadata.uums.client.bean;

public class UserInformation  implements java.io.Serializable {
    private String default1;
    private String uniqueid;
    private String userIdcode;
    private String userState;
    private String userType;

    public UserInformation() {
    }

    public UserInformation(
           String default1,
           String uniqueid,
           String userIdcode,
           String userState,
           String userType) {
           this.default1 = default1;
           this.uniqueid = uniqueid;
           this.userIdcode = userIdcode;
           this.userState = userState;
           this.userType = userType;
    }


    /**
     * Gets the default1 value for this UserInformation.
     * 
     * @return default1
     */
    public String getDefault1() {
        return default1;
    }


    /**
     * Sets the default1 value for this UserInformation.
     * 
     * @param default1
     */
    public void setDefault1(String default1) {
        this.default1 = default1;
    }


    /**
     * Gets the uniqueid value for this UserInformation.
     * 
     * @return uniqueid
     */
    public String getUniqueid() {
        return uniqueid;
    }


    /**
     * Sets the uniqueid value for this UserInformation.
     * 
     * @param uniqueid
     */
    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }


    /**
     * Gets the userIdcode value for this UserInformation.
     * 
     * @return userIdcode
     */
    public String getUserIdcode() {
        return userIdcode;
    }


    /**
     * Sets the userIdcode value for this UserInformation.
     * 
     * @param userIdcode
     */
    public void setUserIdcode(String userIdcode) {
        this.userIdcode = userIdcode;
    }


    /**
     * Gets the userState value for this UserInformation.
     * 
     * @return userState
     */
    public String getUserState() {
        return userState;
    }


    /**
     * Sets the userState value for this UserInformation.
     * 
     * @param userState
     */
    public void setUserState(String userState) {
        this.userState = userState;
    }


    /**
     * Gets the userType value for this UserInformation.
     * 
     * @return userType
     */
    public String getUserType() {
        return userType;
    }


    /**
     * Sets the userType value for this UserInformation.
     * 
     * @param userType
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof UserInformation)) return false;
        UserInformation other = (UserInformation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.default1==null && other.getDefault1()==null) || 
             (this.default1!=null &&
              this.default1.equals(other.getDefault1()))) &&
            ((this.uniqueid==null && other.getUniqueid()==null) || 
             (this.uniqueid!=null &&
              this.uniqueid.equals(other.getUniqueid()))) &&
            ((this.userIdcode==null && other.getUserIdcode()==null) || 
             (this.userIdcode!=null &&
              this.userIdcode.equals(other.getUserIdcode()))) &&
            ((this.userState==null && other.getUserState()==null) || 
             (this.userState!=null &&
              this.userState.equals(other.getUserState()))) &&
            ((this.userType==null && other.getUserType()==null) || 
             (this.userType!=null &&
              this.userType.equals(other.getUserType())));
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
        if (getDefault1() != null) {
            _hashCode += getDefault1().hashCode();
        }
        if (getUniqueid() != null) {
            _hashCode += getUniqueid().hashCode();
        }
        if (getUserIdcode() != null) {
            _hashCode += getUserIdcode().hashCode();
        }
        if (getUserState() != null) {
            _hashCode += getUserState().hashCode();
        }
        if (getUserType() != null) {
            _hashCode += getUserType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
