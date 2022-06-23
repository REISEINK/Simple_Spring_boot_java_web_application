package com.huadi.education.entity;

public class Course {
    private Integer cozid;
    private Integer orgid;
    private String cozname;
    private Integer coztype;
    private String shift;
    private String teacher;
    private String hours;
    private String teachingform;
    private String starttime;
    private String endtime;
    private Integer studentsize;
    private String enrltarget;
    private Integer cozcharges;
    private String cozcontent;
    private String cozobjective;
    private String cozfeature;
    private Float cozscore;

    public Integer getCozid() {
        return cozid;
    }

    public void setCozid(Integer cozid) {
        this.cozid = cozid;
    }

    public Integer getOrgid() {
        return orgid;
    }

    public void setOrgid(Integer orgid) {
        this.orgid = orgid;
    }

    public String getCozname() {
        return cozname;
    }

    public void setCozname(String cozname) {
        this.cozname = cozname == null ? null : cozname.trim();
    }

    public Integer getCoztype() {
        return coztype;
    }

    public void setCoztype(Integer coztype) {
        this.coztype = coztype;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours == null ? null : hours.trim();
    }

    public String getTeachingform() {
        return teachingform;
    }

    public void setTeachingform(String teachingform) {
        this.teachingform = teachingform == null ? null : teachingform.trim();
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public Integer getStudentsize() {
        return studentsize;
    }

    public void setStudentsize(Integer studentsize) {
        this.studentsize = studentsize;
    }

    public String getEnrltarget() {
        return enrltarget;
    }

    public void setEnrltarget(String enrltarget) {
        this.enrltarget = enrltarget;
    }

    public Integer getCozcharges() {
        return cozcharges;
    }

    public void setCozcharges(Integer cozcharges) {
        this.cozcharges = cozcharges;
    }

    public String getCozcontent() {
        return cozcontent;
    }

    public void setCozcontent(String cozcontent) {
        this.cozcontent = cozcontent;
    }

    public String getCozobjective() {
        return cozobjective;
    }

    public void setCozobjective(String cozobjective) {
        this.cozobjective = cozobjective;
    }

    public String getCozfeature() {
        return cozfeature;
    }

    public void setCozfeature(String cozfeature) {
        this.cozfeature = cozfeature;
    }

    public Float getCozscore() {
        return cozscore;
    }

    public void setCozscore(Float cozscore) {
        this.cozscore = cozscore;
    }

    public Course(Integer cozid, Integer orgid, String cozname, Integer coztype, String shift, String teacher, String hours, String teachingform, String starttime, String endtime, Integer studentsize, String enrltarget, Integer cozcharges, String cozcontent, String cozobjective, String cozfeature, Float cozscore) {
        this.cozid = cozid;
        this.orgid = orgid;
        this.cozname = cozname;
        this.coztype = coztype;
        this.shift = shift;
        this.teacher = teacher;
        this.hours = hours;
        this.teachingform = teachingform;
        this.starttime = starttime;
        this.endtime = endtime;
        this.studentsize = studentsize;
        this.enrltarget = enrltarget;
        this.cozcharges = cozcharges;
        this.cozcontent = cozcontent;
        this.cozobjective = cozobjective;
        this.cozfeature = cozfeature;
        this.cozscore = cozscore;
    }
}
