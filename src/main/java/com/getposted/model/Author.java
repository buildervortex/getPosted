package com.getposted.model;

import java.sql.Date;

public class Author {

	private int id = 0;
	private String email = null;
	private String phoneNumber = null;
	private String salt = null;
	private String bio = null;
	private String pepper = null;
	private String password = null;
	private Date dob = null;
	private String firstName = null;
	private String middleName = null;
	private String lastName = null;
	private String userName = null;
	private int countryId = 1;

	public Author() {
	}

	public Author(int id, String email, String phoneNumber, String salt, String bio, String pepper, String password,
			Date dob, String firstName, String middleName, String lastName, String userName, int countryId) {
		this.id = id;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.salt = salt;
		this.bio = bio;
		this.pepper = pepper;
		this.password = password;
		this.dob = dob;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.userName = userName;
		this.countryId = countryId;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getPepper() {
		return pepper;
	}

	public void setPepper(String pepper) {
		this.pepper = pepper;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", email=" + email + ", phoneNumber=" + phoneNumber + ", salt=" + salt + ", bio="
				+ bio + ", pepper=" + pepper + ", password=" + password + ", dob=" + dob + ", firstName=" + firstName
				+ ", middleName=" + middleName + ", lastName=" + lastName + ", userName=" + userName + ", countryId="
				+ countryId + "]";
	}

}
