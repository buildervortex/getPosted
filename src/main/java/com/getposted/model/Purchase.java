package com.getposted.model;

import java.sql.Date;
import java.sql.Time;

public class Purchase {

    private int id = 0;
    private Date purchasedDate = null;
    private double price = 0.0;
    private Time purchasedTime = null;
    private int userId = 0;
    private int publisherId = 0;
    private int publicationId = 0;

    public Purchase() {
    }

    public Purchase(int id, Date purchasedDate, double price, Time purchasedTime, int userId, int publisherId,
            int publicationId) {
        this.id = id;
        this.purchasedDate = purchasedDate;
        this.price = price;
        this.purchasedTime = purchasedTime;
        this.userId = userId;
        this.publisherId = publisherId;
        this.publicationId = publicationId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(Date purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Time getPurchasedTime() {
        return purchasedTime;
    }

    public void setPurchasedTime(Time purchasedTime) {
        this.purchasedTime = purchasedTime;
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

    public int getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(int publicationId) {
        this.publicationId = publicationId;
    }

    @Override
    public String toString() {
        return "Purchase [id=" + id + ", purchasedDate=" + purchasedDate + ", price=" + price + ", purchasedTime="
                + purchasedTime + ", userId=" + userId + ", publisherId=" + publisherId + ", publicationId="
                + publicationId + "]";
    }
}
