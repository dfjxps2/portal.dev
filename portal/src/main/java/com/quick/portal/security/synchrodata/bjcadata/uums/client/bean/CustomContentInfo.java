/**
 * CustomContentInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Aug 08, 2005 (11:49:10 PDT) WSDL2Java emitter.
 */

package com.quick.portal.security.synchrodata.bjcadata.uums.client.bean;

public class CustomContentInfo  implements java.io.Serializable {
    private long contentFlowno;
    private long informationSystem;
    private String shareName;
    private String shareUserType;

    public CustomContentInfo() {
    }

    public CustomContentInfo(
           long contentFlowno,
           long informationSystem,
           String shareName,
           String shareUserType) {
           this.contentFlowno = contentFlowno;
           this.informationSystem = informationSystem;
           this.shareName = shareName;
           this.shareUserType = shareUserType;
    }


    /**
     * Gets the contentFlowno value for this CustomContentInfo.
     * 
     * @return contentFlowno
     */
    public long getContentFlowno() {
        return contentFlowno;
    }


    /**
     * Sets the contentFlowno value for this CustomContentInfo.
     * 
     * @param contentFlowno
     */
    public void setContentFlowno(long contentFlowno) {
        this.contentFlowno = contentFlowno;
    }


    /**
     * Gets the informationSystem value for this CustomContentInfo.
     * 
     * @return informationSystem
     */
    public long getInformationSystem() {
        return informationSystem;
    }


    /**
     * Sets the informationSystem value for this CustomContentInfo.
     * 
     * @param informationSystem
     */
    public void setInformationSystem(long informationSystem) {
        this.informationSystem = informationSystem;
    }


    /**
     * Gets the shareName value for this CustomContentInfo.
     * 
     * @return shareName
     */
    public String getShareName() {
        return shareName;
    }


    /**
     * Sets the shareName value for this CustomContentInfo.
     * 
     * @param shareName
     */
    public void setShareName(String shareName) {
        this.shareName = shareName;
    }


    /**
     * Gets the shareUserType value for this CustomContentInfo.
     * 
     * @return shareUserType
     */
    public String getShareUserType() {
        return shareUserType;
    }


    /**
     * Sets the shareUserType value for this CustomContentInfo.
     * 
     * @param shareUserType
     */
    public void setShareUserType(String shareUserType) {
        this.shareUserType = shareUserType;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof CustomContentInfo)) return false;
        CustomContentInfo other = (CustomContentInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.contentFlowno == other.getContentFlowno() &&
            this.informationSystem == other.getInformationSystem() &&
            ((this.shareName==null && other.getShareName()==null) || 
             (this.shareName!=null &&
              this.shareName.equals(other.getShareName()))) &&
            ((this.shareUserType==null && other.getShareUserType()==null) || 
             (this.shareUserType!=null &&
              this.shareUserType.equals(other.getShareUserType())));
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
        _hashCode += new Long(getContentFlowno()).hashCode();
        _hashCode += new Long(getInformationSystem()).hashCode();
        if (getShareName() != null) {
            _hashCode += getShareName().hashCode();
        }
        if (getShareUserType() != null) {
            _hashCode += getShareUserType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
