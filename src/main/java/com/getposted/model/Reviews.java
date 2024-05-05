package com.getposted.model;

import java.sql.Date;

public class Reviews {

    private int id = 0;
    private int value = 0;
    private String review = null;
    private Date date = null;
    private int publicationId = 0;
    private int userId = 0;

    public Reviews(){}

    public Reviews(int id, int value, String review, Date date, int publicationId, int userId) {
        this.id = id;
        this.value = value;
        this.review = review;
        this.date = date;
        this.publicationId = publicationId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(int publicationId) {
        this.publicationId = publicationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Reviews [id=" + id + ", value=" + value + ", review=" + review + ", date=" + date + ", publicationId="
                + publicationId + ", userId=" + userId + "]";
    }

    
    
}
