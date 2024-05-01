package com.getposted.model;

public class Country {

	private int id = 0;
	private String country = null;

	public Country() {
	}

	public Country(int id, String country) {
		this.id = id;
		this.country = country;
	}

	public int getId() {
		return this.id;
	}

	public String getCountry() {
		return this.country;
	}

	public void setId(int value) {
		this.id = value < 0 ? this.id : value;
	}

	public void setCountry(String value) {
		this.country = value.length() <= 1 ? this.country : value;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", country=" + country + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (id != other.id)
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		return true;
	}

}
