package com.huadi.education.entity;

public class Mark {
    private int markID;
    private int score;
    private Course course;

    public int getMarkID() {
        return markID;
    }

    public void setMarkID(int markID) {
        this.markID = markID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "markID=" + markID +
                ", score=" + score +
                ", course=" + course +
                '}';
    }
}
