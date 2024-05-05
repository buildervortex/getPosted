package com.getposted.model;

import java.sql.Date;
import java.sql.Time;

public class IsNotifiedBy {
    private int id = 0;
    private String notification = null;
    private Date notifiedDate = null;
    private Time notifiedTime = null;
    private int authorId = 0;
    private int publisherId = 0;

    public IsNotifiedBy() {
    }

    public IsNotifiedBy(int id, String notification, Date notifiedDate, Time notifiedTime, int authorId,
            int publisherId) {
        this.id = id;
        this.notification = notification;
        this.notifiedDate = notifiedDate;
        this.notifiedTime = notifiedTime;
        this.authorId = authorId;
        this.publisherId = publisherId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public Date getNotifiedDate() {
        return notifiedDate;
    }

    public void setNotifiedDate(Date notifiedDate) {
        this.notifiedDate = notifiedDate;
    }

    public Time getNotifiedTime() {
        return notifiedTime;
    }

    public void setNotifiedTime(Time notifiedTime) {
        this.notifiedTime = notifiedTime;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    @Override
    public String toString() {
        return "IsNotifiedBy [id=" + id + ", notification=" + notification + ", notifiedDate=" + notifiedDate
                + ", notifiedTime=" + notifiedTime + ", authorId=" + authorId + ", publisherId=" + publisherId + "]";
    }

}
