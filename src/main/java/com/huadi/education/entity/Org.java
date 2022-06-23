package com.huadi.education.entity;

import java.util.List;

public class Org {
    private Integer orgID;

    private String orgName;

    private String licenseKey;

    private String licenseFile;

    private String orgType;

    private String legalPerson;

    private String principal;

    private Integer operationType;

    private String publicTel;

    private Integer regStatus;

    private String address;

    private Integer complainNum;

    private String blackReason;

    private Boolean haveAdm;

    private String operationInfo;

    public Integer getOrgID() {
        return orgID;
    }

    public void setOrgID(Integer orgID) {
        this.orgID = orgID;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getLicenseKey() {
        return licenseKey;
    }

    public void setLicenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
    }

    public String getLicenseFile() {
        return licenseFile;
    }

    public void setLicenseFile(String licenseFile) {
        this.licenseFile = licenseFile;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public String getPublicTel() {
        return publicTel;
    }

    public void setPublicTel(String publicTel) {
        this.publicTel = publicTel;
    }

    public Integer getRegStatus() {
        return regStatus;
    }

    public void setRegStatus(Integer regStatus) {
        this.regStatus = regStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getComplainNum() {
        return complainNum;
    }

    public void setComplainNum(Integer complainNum) {
        this.complainNum = complainNum;
    }

    public String getBlackReason() {
        return blackReason;
    }

    public void setBlackReason(String blackReason) {
        this.blackReason = blackReason;
    }

    public Boolean getHaveAdm() {
        return haveAdm;
    }

    public void setHaveAdm(Boolean haveAdm) {
        this.haveAdm = haveAdm;
    }

    public String getOperationInfo() {
        return operationInfo;
    }

    public void setOperationInfo(String operationInfo) {
        this.operationInfo = operationInfo;
    }

    @Override
    public String toString() {
        return "Org{" +
                "orgID=" + orgID +
                ", orgName='" + orgName + '\'' +
                ", licenseKey='" + licenseKey + '\'' +
                ", licenseFile='" + licenseFile + '\'' +
                ", orgType='" + orgType + '\'' +
                ", legalPerson='" + legalPerson + '\'' +
                ", principal='" + principal + '\'' +
                ", operationType=" + operationType +
                ", publicTel='" + publicTel + '\'' +
                ", regStatus=" + regStatus +
                ", address='" + address + '\'' +
                ", complainNum=" + complainNum +
                ", blackReason='" + blackReason + '\'' +
                ", haveAdm=" + haveAdm +
                ", operationInfo='" + operationInfo + '\'' +
                '}';
    }
}