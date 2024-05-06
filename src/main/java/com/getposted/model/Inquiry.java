package com.getposted.model;

import java.sql.Date;
import java.sql.Time;

public class Inquiry {

    public static final String PENDING = "Pending";
    public static final String ACCEPTED = "Accepted";
    public static final String REJECTED = "Rejected";
    public static final String PROCESSING = "Processing";
    public static final String SHIPPED = "Shipped";

    private int id = 0;
    private Date purchasedDate = null;
    private String shippingAddress = null;
    private String postalCode = null;
    private Time purchasedTime = null;
    private String contactName = null;
    private int count = 0;
    private String country = null;
    private Date shippedDate = null;
    private String state = Inquiry.PENDING;
    private double price = 0.0;
    private int publicationId = 0;
    private int userId = 0;
    private int publisherId = 0;

    public Inquiry() {
    }

    public Inquiry(int id, Date purchasedDate, String shippingAddress, String postalCode, Time purchasedTime,
            String contactName, int count, String country, Date shippedDate, String state, double price,
            int publicationId, int userId, int publisherId) {
        this.id = id;
        this.purchasedDate = purchasedDate;
        this.shippingAddress = shippingAddress;
        this.postalCode = postalCode;
        this.purchasedTime = purchasedTime;
        this.contactName = contactName;
        this.count = count;
        this.country = country;
        this.shippedDate = shippedDate;
        this.state = state;
        this.price = price;
        this.publicationId = publicationId;
        this.userId = userId;
        this.publisherId = publisherId;
    }

    public static String getPending() {
        return PENDING;
    }

    public static String getAccepted() {
        return ACCEPTED;
    }

    public static String getRejected() {
        return REJECTED;
    }

    public static String getProcessing() {
        return PROCESSING;
    }

    public static String getShipped() {
        return SHIPPED;
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

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Time getPurchasedTime() {
        return purchasedTime;
    }

    public void setPurchasedTime(Time purchasedTime) {
        this.purchasedTime = purchasedTime;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    @Override
    public String toString() {
        return "Inquiry [id=" + id + ", purchasedDate=" + purchasedDate + ", shippingAddress=" + shippingAddress
                + ", postalCode=" + postalCode + ", purchasedTime=" + purchasedTime + ", contactName=" + contactName
                + ", count=" + count + ", country=" + country + ", shippedDate=" + shippedDate + ", state=" + state
                + ", price=" + price + ", publicationId=" + publicationId + ", userId=" + userId + ", publisherId="
                + publisherId + "]";
    }

}
