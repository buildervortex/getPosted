package com.getposted.model;

import java.sql.Date;

public class User {
    private int id;
    private String email;
    private String password;
    private String userName;
    private Date dob;
    private String salt;
    private String pepper;
    private String firstName;
    private String middleName;
    private String lastName;

    public User() {
    }

    public User(int id, String email, String password, String userName, Date dob, String salt, String pepper,
            String firstName, String middleName, String lastName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.dob = dob;
        this.salt = salt;
        this.pepper = pepper;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPepper() {
        return pepper;
    }

    public void setPepper(String pepper) {
        this.pepper = pepper;
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

    @Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", password=" + password + ", userName=" + userName + ", dob="
                + dob + ", salt=" + salt + ", pepper=" + pepper + ", firstName=" + firstName + ", middleName="
                + middleName + ", lastName=" + lastName + "]";
    }

}