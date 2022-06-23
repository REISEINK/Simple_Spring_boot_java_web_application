package com.huadi.education.entity;

public class OrgApply {
    private int applyID;
    private String applicantName;
    private String applicantID;
    private String applicantEmail;
    private String applicantTel;
    private int applyStatus;
    private Org org;

    public int getApplyID() {
        return applyID;
    }

    public void setApplyID(int applyID) {
        this.applyID = applyID;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantID() {
        return applicantID;
    }

    public void setApplicantID(String applicantID) {
        this.applicantID = applicantID;
    }

    public String getApplicantEmail() {
        return applicantEmail;
    }

    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }

    public String getApplicantTel() {
        return applicantTel;
    }

    public void setApplicantTel(String applicantTel) {
        this.applicantTel = applicantTel;
    }

    public int getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(int applyStatus) {
        this.applyStatus = applyStatus;
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    @Override
    public String toString() {
        return "OrgApply{" +
                "applyID=" + applyID +
                ", applicantName='" + applicantName + '\'' +
                ", applicantID='" + applicantID + '\'' +
                ", applicantEmail='" + applicantEmail + '\'' +
                ", applicantTel='" + applicantTel + '\'' +
                ", applyStatus=" + applyStatus +
                ", org=" + org +
                '}';
    }
}
