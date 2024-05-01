package com.getposted.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.getposted.logger.Logging;
import java.util.logging.Logger;

public class CategoryDAOImpl implements CategoryDAO {

	private static Logger logger = Logging.getLogger(CategoryDAOImpl.class.getName());

	@Override
	public Category get(int id) throws SQLException {
		Connection con = Database.getConnection();
		Category category = null;
		String sqlTemplate = "SELECT * FROM Category WHERE id = ?";
		PreparedStatement select = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		select.setInt(1, id);

		try {
			rs = select.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CategoryDAOImpl class at getId() method the id is %d. The exception message is %s",
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
	public List<Category> getAll() throws SQLException {
		Connection con = Database.getConnection();
		List<Category> categoryList = new ArrayList();
		String sqlTemplate = "SELECT * FROM Category";
		PreparedStatement select = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		try {
			rs = select.executeQuery();
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
	public int insert(Category category) throws SQLException {
		Connection con = Database.getConnection();
		String sqlTemplate = "INSERT INTO Category(id,category) VALUES (?,?)";
		PreparedStatement st = con.prepareStatement(sqlTemplate);
		int result = -1;

		st.setInt(1, category.getId());
		st.setString(2, category.getCategory());

		try {
			result = st.executeUpdate();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CategoryDAOImpl class at insert() method. The exception message is %s. The inserted Language name is %s and id is %d",
					e.getMessage(), category.getCategory(), category.getId()));
			throw e;
		}

		return result;
	}

	@Override
	public int update(Category category) throws SQLException {
		Connection con = Database.getConnection();
		String sqlTemplate = "UPDATE Category SET category = ? WHERE id = ?";
		PreparedStatement st = con.prepareStatement(sqlTemplate);
		int result = -1;

		st.setString(1, category.getCategory());
		st.setInt(2, category.getId());

		try {
			result = st.executeUpdate();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CategoryDAOImpl class at update() method. The exception message is %s. The updated Language name is %s and id is %d",
					e.getMessage(), category.getCategory(), category.getId()));
			throw e;
		}

		return result;
	}

	@Override
	public int delete(Category category) throws SQLException {
		Connection con = Database.getConnection();
		String sqlTemplate = "DELETE FROM Category WHERE id = ?";
		PreparedStatement st = con.prepareStatement(sqlTemplate);
		int result = -1;

		st.setInt(1, category.getId());

		try {
			result = st.executeUpdate();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CategoryDAOImpl class at delete() method. The exception message is %s. The deleted Language name is %s and id is %d",
					e.getMessage(), category.getCategory(), category.getId()));
			throw e;
		}

		return result;
	}

	@Override
	public List<Category> getList(int limit) throws SQLException {
		Connection con = Database.getConnection();
		List<Category> categoryList = new ArrayList();
		String sqlTemplate = "SELECT * FROM Category LIMIT ?";
		PreparedStatement select = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		select.setInt(1, limit);

		try {
			rs = select.executeQuery();
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
	public Category get(String category) throws SQLException {
		Connection con = Database.getConnection();
		Category categoryOb = null;
		String sqlTemplate = "SELECT * FROM Category WHERE category = ?";
		PreparedStatement select = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		select.setString(1, category);

		try {
			rs = select.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CategoryDAOImpl class at getId() method the id is %s. The exception message is %s",
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
}
