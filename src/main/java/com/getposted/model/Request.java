package com.getposted.model;

public class Request {
    // (id, requestedContent, authorId, publisherId)

    private int id = 0;
    private String requestedContent = null;
    private int authorId = 0;
    private int publisherId = 0;

    public Request(){}

    public Request(int id, String requestedContent, int authorId, int publisherId) {
        this.id = id;
        this.requestedContent = requestedContent;
        this.authorId = authorId;
        this.publisherId = publisherId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequestedContent() {
        return requestedContent;
    }

    public void setRequestedContent(String requestedContent) {
        this.requestedContent = requestedContent;
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
        return "Request [id=" + id + ", requestedContent=" + requestedContent + ", authorId=" + authorId
                + ", publisherId=" + publisherId + "]";
    }
    
    
}
