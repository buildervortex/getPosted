package com.getposted.model;

// userId, publicationId

public class Save {
    private int userId = 0;
    private int publicationId = 0;

    public Save(){}

    public Save(int userId, int publicationId) {
        this.userId = userId;
        this.publicationId = publicationId;
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
        return "Save [userId=" + userId + ", publicationId=" + publicationId + "]";
    }    
}