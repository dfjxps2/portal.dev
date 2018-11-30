/**
 * DepartmentInformation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Aug 08, 2005 (11:49:10 PDT) WSDL2Java emitter.
 */

package com.quick.portal.security.synchrodata.bjcadata.uums.client.bean;

public class DepartmentInformation  implements java.io.Serializable {
    private String departAddress;
    private String departCode;
    private String departDefault;
    private String departDescript;
    private String departName;
    private String departPhone;
    private String departPostCode;
    private String departSortNum;
    private String departUnitCode;
    private String departUpcode;
    private String departWEBAddress;

    public DepartmentInformation() {
    }

    public DepartmentInformation(
           String departAddress,
           String departCode,
           String departDefault,
           String departDescript,
           String departName,
           String departPhone,
           String departPostCode,
           String departSortNum,
           String departUnitCode,
           String departUpcode,
           String departWEBAddress) {
           this.departAddress = departAddress;
           this.departCode = departCode;
           this.departDefault = departDefault;
           this.departDescript = departDescript;
           this.departName = departName;
           this.departPhone = departPhone;
           this.departPostCode = departPostCode;
           this.departSortNum = departSortNum;
           this.departUnitCode = departUnitCode;
           this.departUpcode = departUpcode;
           this.departWEBAddress = departWEBAddress;
    }


    /**
     * Gets the departAddress value for this DepartmentInformation.
     * 
     * @return departAddress
     */
    public String getDepartAddress() {
        return departAddress;
    }


    /**
     * Sets the departAddress value for this DepartmentInformation.
     * 
     * @param departAddress
     */
    public void setDepartAddress(String departAddress) {
        this.departAddress = departAddress;
    }


    /**
     * Gets the departCode value for this DepartmentInformation.
     * 
     * @return departCode
     */
    public String getDepartCode() {
        return departCode;
    }


    /**
     * Sets the departCode value for this DepartmentInformation.
     * 
     * @param departCode
     */
    public void setDepartCode(String departCode) {
        this.departCode = departCode;
    }


    /**
     * Gets the departDefault value for this DepartmentInformation.
     * 
     * @return departDefault
     */
    public String getDepartDefault() {
        return departDefault;
    }


    /**
     * Sets the departDefault value for this DepartmentInformation.
     * 
     * @param departDefault
     */
    public void setDepartDefault(String departDefault) {
        this.departDefault = departDefault;
    }


    /**
     * Gets the departDescript value for this DepartmentInformation.
     * 
     * @return departDescript
     */
    public String getDepartDescript() {
        return departDescript;
    }


    /**
     * Sets the departDescript value for this DepartmentInformation.
     * 
     * @param departDescript
     */
    public void setDepartDescript(String departDescript) {
        this.departDescript = departDescript;
    }


    /**
     * Gets the departName value for this DepartmentInformation.
     * 
     * @return departName
     */
    public String getDepartName() {
        return departName;
    }


    /**
     * Sets the departName value for this DepartmentInformation.
     * 
     * @param departName
     */
    public void setDepartName(String departName) {
        this.departName = departName;
    }


    /**
     * Gets the departPhone value for this DepartmentInformation.
     * 
     * @return departPhone
     */
    public String getDepartPhone() {
        return departPhone;
    }


    /**
     * Sets the departPhone value for this DepartmentInformation.
     * 
     * @param departPhone
     */
    public void setDepartPhone(String departPhone) {
        this.departPhone = departPhone;
    }


    /**
     * Gets the departPostCode value for this DepartmentInformation.
     * 
     * @return departPostCode
     */
    public String getDepartPostCode() {
        return departPostCode;
    }


    /**
     * Sets the departPostCode value for this DepartmentInformation.
     * 
     * @param departPostCode
     */
    public void setDepartPostCode(String departPostCode) {
        this.departPostCode = departPostCode;
    }


    /**
     * Gets the departSortNum value for this DepartmentInformation.
     * 
     * @return departSortNum
     */
    public String getDepartSortNum() {
        return departSortNum;
    }


    /**
     * Sets the departSortNum value for this DepartmentInformation.
     * 
     * @param departSortNum
     */
    public void setDepartSortNum(String departSortNum) {
        this.departSortNum = departSortNum;
    }


    /**
     * Gets the departUnitCode value for this DepartmentInformation.
     * 
     * @return departUnitCode
     */
    public String getDepartUnitCode() {
        return departUnitCode;
    }


    /**
     * Sets the departUnitCode value for this DepartmentInformation.
     * 
     * @param departUnitCode
     */
    public void setDepartUnitCode(String departUnitCode) {
        this.departUnitCode = departUnitCode;
    }


    /**
     * Gets the departUpcode value for this DepartmentInformation.
     * 
     * @return departUpcode
     */
    public String getDepartUpcode() {
        return departUpcode;
    }


    /**
     * Sets the departUpcode value for this DepartmentInformation.
     * 
     * @param departUpcode
     */
    public void setDepartUpcode(String departUpcode) {
        this.departUpcode = departUpcode;
    }


    /**
     * Gets the departWEBAddress value for this DepartmentInformation.
     * 
     * @return departWEBAddress
     */
    public String getDepartWEBAddress() {
        return departWEBAddress;
    }


    /**
     * Sets the departWEBAddress value for this DepartmentInformation.
     * 
     * @param departWEBAddress
     */
    public void setDepartWEBAddress(String departWEBAddress) {
        this.departWEBAddress = departWEBAddress;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof DepartmentInformation)) return false;
        DepartmentInformation other = (DepartmentInformation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.departAddress==null && other.getDepartAddress()==null) || 
             (this.departAddress!=null &&
              this.departAddress.equals(other.getDepartAddress()))) &&
            ((this.departCode==null && other.getDepartCode()==null) || 
             (this.departCode!=null &&
              this.departCode.equals(other.getDepartCode()))) &&
            ((this.departDefault==null && other.getDepartDefault()==null) || 
             (this.departDefault!=null &&
              this.departDefault.equals(other.getDepartDefault()))) &&
            ((this.departDescript==null && other.getDepartDescript()==null) || 
             (this.departDescript!=null &&
              this.departDescript.equals(other.getDepartDescript()))) &&
            ((this.departName==null && other.getDepartName()==null) || 
             (this.departName!=null &&
              this.departName.equals(other.getDepartName()))) &&
            ((this.departPhone==null && other.getDepartPhone()==null) || 
             (this.departPhone!=null &&
              this.departPhone.equals(other.getDepartPhone()))) &&
            ((this.departPostCode==null && other.getDepartPostCode()==null) || 
             (this.departPostCode!=null &&
              this.departPostCode.equals(other.getDepartPostCode()))) &&
            ((this.departSortNum==null && other.getDepartSortNum()==null) || 
             (this.departSortNum!=null &&
              this.departSortNum.equals(other.getDepartSortNum()))) &&
            ((this.departUnitCode==null && other.getDepartUnitCode()==null) || 
             (this.departUnitCode!=null &&
              this.departUnitCode.equals(other.getDepartUnitCode()))) &&
            ((this.departUpcode==null && other.getDepartUpcode()==null) || 
             (this.departUpcode!=null &&
              this.departUpcode.equals(other.getDepartUpcode()))) &&
            ((this.departWEBAddress==null && other.getDepartWEBAddress()==null) || 
             (this.departWEBAddress!=null &&
              this.departWEBAddress.equals(other.getDepartWEBAddress())));
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
        if (getDepartAddress() != null) {
            _hashCode += getDepartAddress().hashCode();
        }
        if (getDepartCode() != null) {
            _hashCode += getDepartCode().hashCode();
        }
        if (getDepartDefault() != null) {
            _hashCode += getDepartDefault().hashCode();
        }
        if (getDepartDescript() != null) {
            _hashCode += getDepartDescript().hashCode();
        }
        if (getDepartName() != null) {
            _hashCode += getDepartName().hashCode();
        }
        if (getDepartPhone() != null) {
            _hashCode += getDepartPhone().hashCode();
        }
        if (getDepartPostCode() != null) {
            _hashCode += getDepartPostCode().hashCode();
        }
        if (getDepartSortNum() != null) {
            _hashCode += getDepartSortNum().hashCode();
        }
        if (getDepartUnitCode() != null) {
            _hashCode += getDepartUnitCode().hashCode();
        }
        if (getDepartUpcode() != null) {
            _hashCode += getDepartUpcode().hashCode();
        }
        if (getDepartWEBAddress() != null) {
            _hashCode += getDepartWEBAddress().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
