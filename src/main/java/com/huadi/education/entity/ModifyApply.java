package com.huadi.education.entity;

public class ModifyApply {
    private Integer modID;
    private Integer modStatus;
    private String legalPerson;
    private String principal;
    private String publicTel;
    private String address;
    private String operationInfo;
    private String feedback;
    private Org org;

    public ModifyApply() {
    }

    public ModifyApply(Integer modID, Integer modStatus, String legalPerson, String principal, String publicTel, String address, String operationInfo, String feedback, Org org) {
        this.modID = modID;
        this.modStatus = modStatus;
        this.legalPerson = legalPerson;
        this.principal = principal;
        this.publicTel = publicTel;
        this.address = address;
        this.operationInfo = operationInfo;
        this.feedback = feedback;
        this.org = org;
    }

    public Integer getModID() {
        return modID;
    }

    public void setModID(Integer modID) {
        this.modID = modID;
    }

    public Integer getModStatus() {
        return modStatus;
    }

    public void setModStatus(Integer modStatus) {
        this.modStatus = modStatus;
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

    public String getPublicTel() {
        return publicTel;
    }

    public void setPublicTel(String publicTel) {
        this.publicTel = publicTel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOperationInfo() {
        return operationInfo;
    }

    public void setOperationInfo(String operationInfo) {
        this.operationInfo = operationInfo;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    @Override
    public String toString() {
        return "ModifyApply{" +
                "modID=" + modID +
                ", modStatus=" + modStatus +
                ", legalPerson='" + legalPerson + '\'' +
                ", principal='" + principal + '\'' +
                ", publicTel='" + publicTel + '\'' +
                ", address='" + address + '\'' +
                ", operationInfo='" + operationInfo + '\'' +
                ", feedback='" + feedback + '\'' +
                ", org=" + org +
                '}';
    }
}
