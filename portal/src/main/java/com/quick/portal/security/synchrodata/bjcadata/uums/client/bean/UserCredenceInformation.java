/**
 * UserCredenceInformation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Aug 08, 2005 (11:49:10 PDT) WSDL2Java emitter.
 */

package com.quick.portal.security.synchrodata.bjcadata.uums.client.bean;

public class UserCredenceInformation  implements java.io.Serializable {
    private String credenceAppend;
    private String credenceCert;
    private String credenceClass;
    private String credenceDefault1;
    private String credenceDescribe;
    private String credenceState;
    private String credenceUniqueid;
    private String loginFailNum;
    private String uniqueid;

    public UserCredenceInformation() {
    }

    public UserCredenceInformation(
           String credenceAppend,
           String credenceCert,
           String credenceClass,
           String credenceDefault1,
           String credenceDescribe,
           String credenceState,
           String credenceUniqueid,
           String loginFailNum,
           String uniqueid) {
           this.credenceAppend = credenceAppend;
           this.credenceCert = credenceCert;
           this.credenceClass = credenceClass;
           this.credenceDefault1 = credenceDefault1;
           this.credenceDescribe = credenceDescribe;
           this.credenceState = credenceState;
           this.credenceUniqueid = credenceUniqueid;
           this.loginFailNum = loginFailNum;
           this.uniqueid = uniqueid;
    }


    /**
     * Gets the credenceAppend value for this UserCredenceInformation.
     * 
     * @return credenceAppend
     */
    public String getCredenceAppend() {
        return credenceAppend;
    }


    /**
     * Sets the credenceAppend value for this UserCredenceInformation.
     * 
     * @param credenceAppend
     */
    public void setCredenceAppend(String credenceAppend) {
        this.credenceAppend = credenceAppend;
    }


    /**
     * Gets the credenceCert value for this UserCredenceInformation.
     * 
     * @return credenceCert
     */
    public String getCredenceCert() {
        return credenceCert;
    }


    /**
     * Sets the credenceCert value for this UserCredenceInformation.
     * 
     * @param credenceCert
     */
    public void setCredenceCert(String credenceCert) {
        this.credenceCert = credenceCert;
    }


    /**
     * Gets the credenceClass value for this UserCredenceInformation.
     * 
     * @return credenceClass
     */
    public String getCredenceClass() {
        return credenceClass;
    }


    /**
     * Sets the credenceClass value for this UserCredenceInformation.
     * 
     * @param credenceClass
     */
    public void setCredenceClass(String credenceClass) {
        this.credenceClass = credenceClass;
    }


    /**
     * Gets the credenceDefault1 value for this UserCredenceInformation.
     * 
     * @return credenceDefault1
     */
    public String getCredenceDefault1() {
        return credenceDefault1;
    }


    /**
     * Sets the credenceDefault1 value for this UserCredenceInformation.
     * 
     * @param credenceDefault1
     */
    public void setCredenceDefault1(String credenceDefault1) {
        this.credenceDefault1 = credenceDefault1;
    }


    /**
     * Gets the credenceDescribe value for this UserCredenceInformation.
     * 
     * @return credenceDescribe
     */
    public String getCredenceDescribe() {
        return credenceDescribe;
    }


    /**
     * Sets the credenceDescribe value for this UserCredenceInformation.
     * 
     * @param credenceDescribe
     */
    public void setCredenceDescribe(String credenceDescribe) {
        this.credenceDescribe = credenceDescribe;
    }


    /**
     * Gets the credenceState value for this UserCredenceInformation.
     * 
     * @return credenceState
     */
    public String getCredenceState() {
        return credenceState;
    }


    /**
     * Sets the credenceState value for this UserCredenceInformation.
     * 
     * @param credenceState
     */
    public void setCredenceState(String credenceState) {
        this.credenceState = credenceState;
    }


    /**
     * Gets the credenceUniqueid value for this UserCredenceInformation.
     * 
     * @return credenceUniqueid
     */
    public String getCredenceUniqueid() {
        return credenceUniqueid;
    }


    /**
     * Sets the credenceUniqueid value for this UserCredenceInformation.
     * 
     * @param credenceUniqueid
     */
    public void setCredenceUniqueid(String credenceUniqueid) {
        this.credenceUniqueid = credenceUniqueid;
    }


    /**
     * Gets the loginFailNum value for this UserCredenceInformation.
     * 
     * @return loginFailNum
     */
    public String getLoginFailNum() {
        return loginFailNum;
    }


    /**
     * Sets the loginFailNum value for this UserCredenceInformation.
     * 
     * @param loginFailNum
     */
    public void setLoginFailNum(String loginFailNum) {
        this.loginFailNum = loginFailNum;
    }


    /**
     * Gets the uniqueid value for this UserCredenceInformation.
     * 
     * @return uniqueid
     */
    public String getUniqueid() {
        return uniqueid;
    }


    /**
     * Sets the uniqueid value for this UserCredenceInformation.
     * 
     * @param uniqueid
     */
    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof UserCredenceInformation)) return false;
        UserCredenceInformation other = (UserCredenceInformation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.credenceAppend==null && other.getCredenceAppend()==null) || 
             (this.credenceAppend!=null &&
              this.credenceAppend.equals(other.getCredenceAppend()))) &&
            ((this.credenceCert==null && other.getCredenceCert()==null) || 
             (this.credenceCert!=null &&
              this.credenceCert.equals(other.getCredenceCert()))) &&
            ((this.credenceClass==null && other.getCredenceClass()==null) || 
             (this.credenceClass!=null &&
              this.credenceClass.equals(other.getCredenceClass()))) &&
            ((this.credenceDefault1==null && other.getCredenceDefault1()==null) || 
             (this.credenceDefault1!=null &&
              this.credenceDefault1.equals(other.getCredenceDefault1()))) &&
            ((this.credenceDescribe==null && other.getCredenceDescribe()==null) || 
             (this.credenceDescribe!=null &&
              this.credenceDescribe.equals(other.getCredenceDescribe()))) &&
            ((this.credenceState==null && other.getCredenceState()==null) || 
             (this.credenceState!=null &&
              this.credenceState.equals(other.getCredenceState()))) &&
            ((this.credenceUniqueid==null && other.getCredenceUniqueid()==null) || 
             (this.credenceUniqueid!=null &&
              this.credenceUniqueid.equals(other.getCredenceUniqueid()))) &&
            ((this.loginFailNum==null && other.getLoginFailNum()==null) || 
             (this.loginFailNum!=null &&
              this.loginFailNum.equals(other.getLoginFailNum()))) &&
            ((this.uniqueid==null && other.getUniqueid()==null) || 
             (this.uniqueid!=null &&
              this.uniqueid.equals(other.getUniqueid())));
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
        if (getCredenceAppend() != null) {
            _hashCode += getCredenceAppend().hashCode();
        }
        if (getCredenceCert() != null) {
            _hashCode += getCredenceCert().hashCode();
        }
        if (getCredenceClass() != null) {
            _hashCode += getCredenceClass().hashCode();
        }
        if (getCredenceDefault1() != null) {
            _hashCode += getCredenceDefault1().hashCode();
        }
        if (getCredenceDescribe() != null) {
            _hashCode += getCredenceDescribe().hashCode();
        }
        if (getCredenceState() != null) {
            _hashCode += getCredenceState().hashCode();
        }
        if (getCredenceUniqueid() != null) {
            _hashCode += getCredenceUniqueid().hashCode();
        }
        if (getLoginFailNum() != null) {
            _hashCode += getLoginFailNum().hashCode();
        }
        if (getUniqueid() != null) {
            _hashCode += getUniqueid().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
