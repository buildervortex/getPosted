package com.getposted.model;

import java.util.List;
import java.sql.SQLException;

public interface CategoryDAO extends DAO<Category> {
	List<Category> getList(int limit) throws SQLException;

	Category get(String category) throws SQLException;
}
