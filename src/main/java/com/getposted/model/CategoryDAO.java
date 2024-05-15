package com.getposted.model;

import java.util.List;
import java.sql.SQLException;

public interface CategoryDAO extends DAO<Category> {
	List<Category> getList(int limit) throws SQLException;

	Category get(String category) throws SQLException;
	List<Category> getCategoriesOnAPattern(String pattern) throws SQLException;	// SELECT * FROM Category WHERE LOWER(category) LIKE LOWER("%ar%")
	int getCategoryCount() throws SQLException;	// SELECT COUNT(id) AS count FROM Category
	List<Category> getCategoriesByGivenIds(int... ids) throws SQLException;	// SELECT * FROM Category WHERE id IN (1,2,4)
	List<Category> getAllCategoriesExceptGivenIds(int... ids) throws SQLException;	// SELECT * FROM Category WHERE id NOT IN (1,2,4)
}
