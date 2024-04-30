
package com.getposted.model;

public class Publisher {
    private int id;
    private String address;
    private String webSite;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private double hardCopyPageCommissionForAuthor;
    private String password;
    private double hardCopyDiscount;
    private double hardCopyPricePerPage;
    private double softCopyCommission;
    private double hardCopyPageCommission;

    public Publisher(int id, String address, String webSite, String firstName, String middleName, String lastName,
            String email, double hardCopyPageCommissionForAuthor, String password, double hardCopyDiscount,
            double hardCopyPricePerPage, double softCopyCommission, double hardCopyPageCommission) {
        this.id = id;
        this.address = address;
        this.webSite = webSite;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.hardCopyPageCommissionForAuthor = hardCopyPageCommissionForAuthor;
        this.password = password;
        this.hardCopyDiscount = hardCopyDiscount;
        this.hardCopyPricePerPage = hardCopyPricePerPage;
        this.softCopyCommission = softCopyCommission;
        this.hardCopyPageCommission = hardCopyPageCommission;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public double getHardCopyPageCommission() {
        return hardCopyPageCommission;
    }

    public void setHardCopyPageCommission(double hardCopyPageCommission) {
        this.hardCopyPageCommission = hardCopyPageCommission;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", webSite='" + webSite + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", hardCopyPageCommissionForAuthor=" + hardCopyPageCommissionForAuthor +
                ", password='" + password + '\'' +
                ", hardCopyDiscount=" + hardCopyDiscount +
                ", hardCopyPricePerPage=" + hardCopyPricePerPage +
                ", softCopyCommission=" + softCopyCommission +
                ", hardCopyPageCommission=" + hardCopyPageCommission +
                '}';
    }
}
