package com.huadi.education.entity;

public class Teacher {
    private Integer tchid;
    private Integer orgid;
    private String tchname;
    private String tchgender;
    private String tchcertnum;
    private String tchentrytime;
    private String tcheducation;
    private String tchschool;
    private String tchisforeign;
    private String tchpermitnuminchina;
    private String tchcontent;

    public Integer getTchid() {
        return tchid;
    }

    public void setTchid(Integer tchid) {
        this.tchid = tchid;
    }

    public Integer getOrgid() {
        return orgid;
    }

    public void setOrgid(Integer orgid) {
        this.orgid = orgid;
    }

    public String getTchname() {
        return tchname;
    }

    public void setTchname(String tchname) {
        this.tchname = tchname == null ? null : tchname.trim();
    }

    public String getTchgender() {
        return tchgender;
    }

    public void setTchgender(String tchgender) {
        this.tchgender = tchgender == null ? null : tchgender.trim();
    }

    public String getTchcertnum() {
        return tchcertnum;
    }

    public void setTchcertnum(String tchcertnum) {
        this.tchcertnum = tchcertnum == null ? null : tchcertnum.trim();
    }

    public String getTchentrytime() {
        return tchentrytime;
    }

    public void setTchentrytime(String tchentrytime) {
        this.tchentrytime = tchentrytime == null ? null : tchentrytime.trim();
    }

    public String getTcheducation() {
        return tcheducation;
    }

    public void setTcheducation(String tcheducation) {
        this.tcheducation = tcheducation;
    }

    public String getTchschool() {
        return tchschool;
    }

    public void setTchschool(String tchschool) {
        this.tchschool = tchschool == null ? null : tchschool.trim();
    }

    public String getTchisforeign() {
        return tchisforeign;
    }

    public void setTchisforeign(String tchisforeign) {
        this.tchisforeign = tchisforeign;
    }

    public String getTchpermitnuminchina() {
        return tchpermitnuminchina;
    }

    public void setTchpermitnuminchina(String tchpermitnuminchina) {
        this.tchpermitnuminchina = tchpermitnuminchina;
    }

    public String getTchcontent() {
        return tchcontent;
    }

    public void setTchcontent(String tchcontent) {
        this.tchcontent = tchcontent;
    }

    public Teacher(Integer tchid, Integer orgid, String tchname, String tchgender, String tchcertnum, String tchentrytime, String tcheducation, String tchschool, String tchisforeign, String tchpermitnuminchina, String tchcontent) {
        this.tchid = tchid;
        this.orgid = orgid;
        this.tchname = tchname;
        this.tchgender = tchgender;
        this.tchcertnum = tchcertnum;
        this.tchentrytime = tchentrytime;
        this.tcheducation = tcheducation;
        this.tchschool = tchschool;
        this.tchisforeign = tchisforeign;
        this.tchpermitnuminchina = tchpermitnuminchina;
        this.tchcontent = tchcontent;
    }
}
