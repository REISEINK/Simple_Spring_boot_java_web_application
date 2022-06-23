package com.huadi.education.entity;

public class User {
    private int userID;
    private String tel;
    private String name;
    private int access;
    private String passwd;
    private String email;
    private boolean status;
    private Org org;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", tel='" + tel + '\'' +
                ", name='" + name + '\'' +
                ", access=" + access +
                ", passwd='" + passwd + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", org=" + org +
                '}';
    }
}
