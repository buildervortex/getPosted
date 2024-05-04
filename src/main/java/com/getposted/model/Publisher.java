
package com.getposted.model;

public class Publisher {
    private int id;
    private String address;
    private String webSite;
    private String name;
    private String email;
    private double hardCopyPageCommissionForAuthor;
    private String salt;
    private String password;
    private double hardCopyDiscount;
    private String pepper;
    private double hardCopyPricePerPage;
    private double softCopyCommission;
    private double hardCopyCommission;

    public Publisher(){}

    public Publisher(int id, String address, String webSite, String name, String email,
            double hardCopyPageCommissionForAuthor, String salt, String password, double hardCopyDiscount,
            String pepper, double hardCopyPricePerPage, double softCopyCommission, double hardCopyCommission) {
        this.id = id;
        this.address = address;
        this.webSite = webSite;
        this.name = name;
        this.email = email;
        this.hardCopyPageCommissionForAuthor = hardCopyPageCommissionForAuthor;
        this.salt = salt;
        this.password = password;
        this.hardCopyDiscount = hardCopyDiscount;
        this.pepper = pepper;
        this.hardCopyPricePerPage = hardCopyPricePerPage;
        this.softCopyCommission = softCopyCommission;
        this.hardCopyCommission = hardCopyCommission;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getHardCopyPageCommissionForAuthor() {
        return hardCopyPageCommissionForAuthor;
    }

    public void setHardCopyPageCommissionForAuthor(double hardCopyPageCommissionForAuthor) {
        this.hardCopyPageCommissionForAuthor = hardCopyPageCommissionForAuthor;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getHardCopyDiscount() {
        return hardCopyDiscount;
    }

    public void setHardCopyDiscount(double hardCopyDiscount) {
        this.hardCopyDiscount = hardCopyDiscount;
    }

    public String getPepper() {
        return pepper;
    }

    public void setPepper(String pepper) {
        this.pepper = pepper;
    }

    public double getHardCopyPricePerPage() {
        return hardCopyPricePerPage;
    }

    public void setHardCopyPricePerPage(double hardCopyPricePerPage) {
        this.hardCopyPricePerPage = hardCopyPricePerPage;
    }

    public double getSoftCopyCommission() {
        return softCopyCommission;
    }

    public void setSoftCopyCommission(double softCopyCommission) {
        this.softCopyCommission = softCopyCommission;
    }

    public double getHardCopyCommission() {
        return hardCopyCommission;
    }

    public void setHardCopyCommission(double hardCopyCommission) {
        this.hardCopyCommission = hardCopyCommission;
    }

    @Override
    public String toString() {
        return "Publisher [id=" + id + ", address=" + address + ", webSite=" + webSite + ", name=" + name + ", email="
                + email + ", hardCopyPageCommissionForAuthor=" + hardCopyPageCommissionForAuthor + ", salt=" + salt
                + ", password=" + password + ", hardCopyDiscount=" + hardCopyDiscount + ", pepper=" + pepper
                + ", hardCopyPricePerPage=" + hardCopyPricePerPage + ", softCopyCommission=" + softCopyCommission
                + ", hardCopyCommission=" + hardCopyCommission + "]";
    }

}
