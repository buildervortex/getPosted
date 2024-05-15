package com.getposted.model;

import java.sql.Date;
import java.sql.Time;

public class IsInformed {

    private int id = 0;
    private String notification = null;
    private Date notifiedDate = null;
    private Time notifiedTime = null;
    private int userId = 0;
    private int publisherId = 0;

    public IsInformed() {
    }

    public IsInformed(int id, String notification, Date notifiedDate, Time notifiedTime, int userId, int publisherId) {
        this.id = id;
        this.notification = notification;
        this.notifiedDate = notifiedDate;
        this.notifiedTime = notifiedTime;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    @Override
    public String toString() {
        return "IsInformed [id=" + id + ", notification=" + notification + ", notifiedDate=" + notifiedDate
                + ", notifiedTime=" + notifiedTime + ", userId=" + userId + ", publisherId=" + publisherId + "]";
    }

}
