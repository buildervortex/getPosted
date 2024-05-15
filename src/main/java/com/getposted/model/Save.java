package com.getposted.model;

import java.sql.Date;

public class Save {
    private Date date = null;
    private int userId = 0;
    private int publicationId = 0;

    public Save() {
    }

    public Save(Date date, int userId, int publicationId) {
        this.date = date;
        this.userId = userId;
        this.publicationId = publicationId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(int publicationId) {
        this.publicationId = publicationId;
    }

    @Override
    public String toString() {
        return "Save [date=" + date + ", userId=" + userId + ", publicationId=" + publicationId + "]";
    }

}