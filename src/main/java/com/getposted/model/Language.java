package com.getposted.model;

public class Language {

	private int id = 0;
	private String language = null;

	public Language() {
	}

	public Language(int id, String language) {
		this.id = id;
		this.language = language;
	}

	public int getId() {
		return this.id;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setId(int value) {
		this.id = value < 0 ? this.id : value;
	}

	public void setLanguage(String value) {
		this.language = value.length() == 0 ? this.language : value;
	}

	@Override
	public String toString() {
		return "Language [id=" + id + ", language=" + language + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((language == null) ? 0 : language.hashCode());
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
		Language other = (Language) obj;
		if (id != other.id)
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		return true;
	}

}
