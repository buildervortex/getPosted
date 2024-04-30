package com.getposted.model;

public class Category {

	private int id = 0;
	private String category = null;

	public Category() {
	}

	public Category(int id, String category) {
		this.id = id;
		this.category = category;
	}

	public int getId() {
		return this.id;
	}

	public String getCategory() {
		return this.category;
	}

	public void setId(int value) {
		this.id = value < 0 ? this.id : value;
	}

	public void setCategory(String value) {
		this.category = value.length() <= 1 ? this.category : value;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", category=" + category + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
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
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		return true;
	}

}
