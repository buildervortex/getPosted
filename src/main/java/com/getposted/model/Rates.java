package com.getposted.model;

import java.sql.Date;

public class Rates {
    private int value = 0;
    private String review = null;
    private Date date = null;
    private int authorId = 0;
    private int userId = 0;

    public Rates(){}

    public Rates(int value, String review, Date date, int authorId, int userId) {
        this.value = value;
        this.review = review;
        this.date = date;
        this.authorId = authorId;
        this.userId = userId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Rates [value=" + value + ", review=" + review + ", date=" + date + ", authorId=" + authorId
                + ", userId=" + userId + "]";
    }   
}
