package com.getposted.model;

import java.sql.Date;

public class Publication {

    private int id = 0;
    private String description = null;
    private Date date = null;
    private int size = 0;
    private String pdfPath = null;
    private double softCopyPrice = 0.0;
    private int pageCount = 0;
    private double softCopyDiscount = 0.0;
    private String title = null;
    private Date publishedDate = null;
    private int categoryId = 0;
    private int languageId = 0;
    private int authorId = 0;

    public Publication() {
    }

    public Publication(int id, String description, Date date, int size, String pdfPath, double softCopyPrice,
            int pageCount, double softCopyDiscount, String title, Date publishedDate, int categoryId, int languageId,
            int authorId) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.size = size;
        this.pdfPath = pdfPath;
        this.softCopyPrice = softCopyPrice;
        this.pageCount = pageCount;
        this.softCopyDiscount = softCopyDiscount;
        this.title = title;
        this.publishedDate = publishedDate;
        this.categoryId = categoryId;
        this.languageId = languageId;
        this.authorId = authorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }

    public double getSoftCopyPrice() {
        return softCopyPrice;
    }

    public void setSoftCopyPrice(double softCopyPrice) {
        this.softCopyPrice = softCopyPrice;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public double getSoftCopyDiscount() {
        return softCopyDiscount;
    }

    public void setSoftCopyDiscount(double softCopyDiscount) {
        this.softCopyDiscount = softCopyDiscount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "Publication [id=" + id + ", description=" + description + ", date=" + date + ", size=" + size
                + ", pdfPath=" + pdfPath + ", softCopyPrice=" + softCopyPrice + ", pageCount=" + pageCount
                + ", softCopyDiscount=" + softCopyDiscount + ", title=" + title + ", publishedDate=" + publishedDate
                + ", categoryId=" + categoryId + ", languageId=" + languageId + ", authorId=" + authorId + "]";
    }

}
