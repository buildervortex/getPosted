package com.getposted.model;

public class PhoneNumber {

    private String phoneNumber = null;
    private int inquiryId = 0;

    public PhoneNumber(){}
    
    public PhoneNumber(String phoneNumber, int inquiryId) {
        this.phoneNumber = phoneNumber;
        this.inquiryId = inquiryId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(int inquiryId) {
        this.inquiryId = inquiryId;
    }

    @Override
    public String toString() {
        return "PhoneNumber [phoneNumber=" + phoneNumber + ", inquiryId=" + inquiryId + "]";
    }

    
    
}
