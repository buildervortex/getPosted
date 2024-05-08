package com.getposted.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.getposted.logger.Logging;
import java.util.logging.Logger;

public class CategoryDAOImpl implements CategoryDAO {

	private static Logger logger = Logging.getLogger(CategoryDAOImpl.class.getName());

	private static String getListRepresentation(int[] ids) {
        String listRepresentation = "";
        listRepresentation += "( ";
        for (int i = 0; i < ids.length; i++) {
            listRepresentation += ids[i];
            if (i != ids.length - 1)
                listRepresentation += ",";
        }
        listRepresentation += " )";
        return listRepresentation;
    }
	@Override
	public Category get(int id) throws SQLException {
		Connection con = Database.getConnection();
		Category category = null;
		String sqlTemplate = "SELECT * FROM Category WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		ps.setInt(1, id);

		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CategoryDAOImpl class at get() method the id is %d. The exception message is %s",
					id, e.getMessage()));
			throw e;
		}

		if (rs.next()) {
			int qId = rs.getInt("id");
			String qCategory = rs.getString("category");

			category = new Category(qId, qCategory);
		}

		return category;
	}

	@Override
	public Category get(String category) throws SQLException {
		Connection con = Database.getConnection();
		Category categoryOb = null;
		String sqlTemplate = "SELECT * FROM Category WHERE category = ?";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		ps.setString(1, category);

		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CategoryDAOImpl class at get(String) method the id is %s. The exception message is %s",
					category, e.getMessage()));
			throw e;
		}

		if (rs.next()) {
			int qId = rs.getInt("id");
			String qCategory = rs.getString("category");

			categoryOb = new Category(qId, qCategory);
		}

		return categoryOb;
	}

	@Override
	public List<Category> getAll() throws SQLException {
		Connection con = Database.getConnection();
		List<Category> categoryList = new ArrayList();
		String sqlTemplate = "SELECT * FROM Category ORDER BY category";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CategoryDAOImpl class at getAll() .The exception message is %s",
					e.getMessage()));
			throw e;
		}

		while (rs.next()) {
			int qId = rs.getInt("id");
			String qCategory = rs.getString("category");
			categoryList.add(new Category(qId, qCategory));
		}

		return categoryList;
	}

	@Override
	public List<Category> getList(int limit) throws SQLException {
		Connection con = Database.getConnection();
		List<Category> categoryList = new ArrayList();
		String sqlTemplate = "SELECT * FROM Category ORDER BY category LIMIT ?";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		ps.setInt(1, limit);

		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CategoryDAOImpl class at getList(). The limit is %d .The exception message is %s",
					limit, e.getMessage()));
			throw e;
		}

		while (rs.next()) {
			int qId = rs.getInt("id");
			String qCategory = rs.getString("category");
			categoryList.add(new Category(qId, qCategory));
		}

		return categoryList;
	}

	@Override
	public int insert(Category category) throws SQLException {
		Connection con = Database.getConnection();
		String sqlTemplate = "INSERT INTO Category(id,category) VALUES (?,?)";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		int rowsAffected = -1;

		ps.setInt(1, category.getId());
		ps.setString(2, category.getCategory());

		try {
			rowsAffected = ps.executeUpdate();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CategoryDAOImpl class at insert() method. The exception message is %s. The inserted Language name is %s and id is %d",
					e.getMessage(), category.getCategory(), category.getId()));
			throw e;
		}

		return rowsAffected;
	}

	@Override
	public int update(Category category) throws SQLException {
		Connection con = Database.getConnection();
		String sqlTemplate = "UPDATE Category SET category = ? WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		int rowsAffected = -1;

		ps.setString(1, category.getCategory());
		ps.setInt(2, category.getId());

		try {
			rowsAffected = ps.executeUpdate();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CategoryDAOImpl class at update() method. The exception message is %s. The updated Language name is %s and id is %d",
					e.getMessage(), category.getCategory(), category.getId()));
			throw e;
		}

		return rowsAffected;
	}

	@Override
	public int delete(Category category) throws SQLException {
		Connection con = Database.getConnection();
		String sqlTemplate = "DELETE FROM Category WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		int rowsAffected = -1;

		ps.setInt(1, category.getId());

		try {
			rowsAffected = ps.executeUpdate();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CategoryDAOImpl class at delete() method. The exception message is %s. The deleted Language name is %s and id is %d",
					e.getMessage(), category.getCategory(), category.getId()));
			throw e;
		}

		return rowsAffected;
	}

	@Override
	public List<Category> getCategoriesOnAPattern(String pattern) throws SQLException {
		pattern = "%" + pattern + "%";
		Connection con = Database.getConnection();
		List<Category> categoryList = new ArrayList();
		String sqlTemplate = "SELECT * FROM Category WHERE LOWER(category) LIKE LOWER(?)";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		ps.setString(1, pattern);

		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CategoryDAOImpl class at getCategoriesOnAPattern() .The exception message is %s",
					e.getMessage()));
			throw e;
		}

		while (rs.next()) {
			int qId = rs.getInt("id");
			String qCategory = rs.getString("category");
			categoryList.add(new Category(qId, qCategory));
		}

		return categoryList;
	}

	@Override
	public int getCategoryCount() throws SQLException {
		Connection con = Database.getConnection();
		int count = 0;
		String sqlTemplate = "SELECT COUNT(id) AS count FROM Category";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CategoryDAOImpl class at getCategoryCount() .The exception message is %s",
					e.getMessage()));
			throw e;
		}

		if(rs.next()){
			count = rs.getInt("count");
		}
		return count;
	}

	@Override
	public List<Category> getCategoriesByGivenIds(int... ids) throws SQLException {
		Connection con = Database.getConnection();
		List<Category> categoryList = new ArrayList();
		String sqlTemplate = String.format("SELECT * FROM Category WHERE id IN %s",getListRepresentation(ids));
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CategoryDAOImpl class at getCategoriesOnAPattern() .The exception message is %s",
					e.getMessage()));
			throw e;
		}

		while (rs.next()) {
			int qId = rs.getInt("id");
			String qCategory = rs.getString("category");
			categoryList.add(new Category(qId, qCategory));
		}

		return categoryList;
	}

	@Override
	public List<Category> getAllCategoriesExceptGivenIds(int... ids) throws SQLException {
		Connection con = Database.getConnection();
		List<Category> categoryList = new ArrayList();
		String sqlTemplate = String.format("SELECT * FROM Category WHERE id NOT IN %s",getListRepresentation(ids));
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CategoryDAOImpl class at getCategoriesOnAPattern() .The exception message is %s",
					e.getMessage()));
			throw e;
		}

		while (rs.next()) {
			int qId = rs.getInt("id");
			String qCategory = rs.getString("category");
			categoryList.add(new Category(qId, qCategory));
		}

		return categoryList;
	}
}