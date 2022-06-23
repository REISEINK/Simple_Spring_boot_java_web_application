package com.huadi.education.entity;

public class Comment {
    private int comID;
    private String userName;
    private int comType;
    private String comments;
    private User user;
    private User commentOn;

    public int getComID() {
        return comID;
    }

    public void setComID(int comID) {
        this.comID = comID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getComType() {
        return comType;
    }

    public void setComType(int comType) {
        this.comType = comType;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getCommentOn() {
        return commentOn;
    }

    public void setCommentOn(User commentOn) {
        this.commentOn = commentOn;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "comID=" + comID +
                ", userName='" + userName + '\'' +
                ", comType=" + comType +
                ", comments='" + comments + '\'' +
                ", user=" + user +
                ", commentOn=" + commentOn +
                '}';
    }
}
