package com.huadi.education.entity;

public class Report {
    private int repoID;
    private String repoType;
    private String repoEvent;
    private String fileSrc;
    private String whistleName;
    private String whistleID;
    private String whistleTel;
    private String orgName;

    public int getRepoID() {
        return repoID;
    }

    public void setRepoID(int repoID) {
        this.repoID = repoID;
    }

    public String getRepoType() {
        return repoType;
    }

    public void setRepoType(String repoType) {
        this.repoType = repoType;
    }

    public String getRepoEvent() {
        return repoEvent;
    }

    public void setRepoEvent(String repoEvent) {
        this.repoEvent = repoEvent;
    }

    public String getFileSrc() {
        return fileSrc;
    }

    public void setFileSrc(String fileSrc) {
        this.fileSrc = fileSrc;
    }

    public String getWhistleName() {
        return whistleName;
    }

    public void setWhistleName(String whistleName) {
        this.whistleName = whistleName;
    }

    public String getWhistleID() {
        return whistleID;
    }

    public void setWhistleID(String whistleID) {
        this.whistleID = whistleID;
    }

    public String getWhistleTel() {
        return whistleTel;
    }

    public void setWhistleTel(String whistleTel) {
        this.whistleTel = whistleTel;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Override
    public String toString() {
        return "Report{" +
                "repoID=" + repoID +
                ", repoType='" + repoType + '\'' +
                ", repoEvent='" + repoEvent + '\'' +
                ", fileSrc='" + fileSrc + '\'' +
                ", whistleName='" + whistleName + '\'' +
                ", whistleID='" + whistleID + '\'' +
                ", whistleTel='" + whistleTel + '\'' +
                ", orgName='" + orgName + '\'' +
                '}';
    }
}
