package com.getposted.model;

public class SearchTag {

    // tagName, publicationId
    private String tagName = null;
    private int publicationId = 0;

    public SearchTag(){}

    public SearchTag(String tagName, int publicationId) {
        this.tagName = tagName;
        this.publicationId = publicationId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(int publicationId) {
        this.publicationId = publicationId;
    }

    @Override
    public String toString() {
        return "SearchTag [tagName=" + tagName + ", publicationId=" + publicationId + "]";
    }
    
    
}