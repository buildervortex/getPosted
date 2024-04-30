package com.getposted.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.getposted.logger.Logging;
import java.util.logging.Logger;

public class LanguageDAOImpl implements LanguageDAO {

	private static Logger logger = Logging.getLogger(LanguageDAOImpl.class.getName());

	@Override
	public Language get(int id) throws SQLException {
		Connection con = Database.getConnection();
		Language language = null;
		String sqlTemplate = "SELECT * FROM Language WHERE id = ?";
		PreparedStatement select = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		select.setInt(1, id);

		try {
			rs = select.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.LanguageDAOImpl class at getId() method the id is %d. The exception message is %s",
					id, e.getMessage()));
			throw e;
		}

		if (rs.next()) {
			int qId = rs.getInt("id");
			String qLanguage = rs.getString("language");

			language = new Language(qId, qLanguage);
		}

		return language;
	}

	@Override
	public List<Language> getAll() throws SQLException {
		Connection con = Database.getConnection();
		List<Language> languageList = new ArrayList();
		String sqlTemplate = "SELECT * FROM Language";
		PreparedStatement select = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		try {
			rs = select.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.LanguageDAOImpl class at getAll() .The exception message is %s",
					e.getMessage()));
			throw e;
		}

		while (rs.next()) {
			int qId = rs.getInt("id");
			String qLanguage = rs.getString("language");
			languageList.add(new Language(qId, qLanguage));
		}

		return languageList;
	}

	@Override
	public int insert(Language language) throws SQLException {
		Connection con = Database.getConnection();
		String sqlTemplate = "INSERT INTO Language(language) VALUES (?)";
		PreparedStatement st = con.prepareStatement(sqlTemplate);
		int result = -1;

		st.setString(1, language.getLanguage());

		try {
			result = st.executeUpdate();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.LanguageDAOImpl class at insert() method. The exception message is %s. The inserted Language name is %s and id is %d",
					e.getMessage(), language.getLanguage(), language.getId()));
			throw e;
		}

		return result;
	}

	@Override
	public int update(Language language) throws SQLException {
		Connection con = Database.getConnection();
		String sqlTemplate = "UPDATE Language SET language = ? WHERE id = ?";
		PreparedStatement st = con.prepareStatement(sqlTemplate);
		int result = -1;

		st.setString(1, language.getLanguage());
		st.setInt(2, language.getId());

		try {
			result = st.executeUpdate();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.LanguageDAOImpl class at update() method. The exception message is %s. The updated Language name is %s and id is %d",
					e.getMessage(), language.getLanguage(), language.getId()));
			throw e;
		}

		return result;
	}

	@Override
	public int delete(Language language) throws SQLException {
		Connection con = Database.getConnection();
		String sqlTemplate = "DELETE FROM Language WHERE id = ?";
		PreparedStatement st = con.prepareStatement(sqlTemplate);
		int result = -1;

		st.setInt(1, language.getId());

		try {
			result = st.executeUpdate();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.LanguageDAOImpl class at delete() method. The exception message is %s. The deleted Language name is %s and id is %d",
					e.getMessage(), language.getLanguage(), language.getId()));
			throw e;
		}

		return result;
	}

	@Override
	public List<Language> getList(int limit) throws SQLException {
		Connection con = Database.getConnection();
		List<Language> languageList = new ArrayList();
		String sqlTemplate = "SELECT * FROM Language LIMIT ?";
		PreparedStatement select = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		select.setInt(1, limit);

		try {
			rs = select.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.LanguageDAOImpl class at getList(). The limit is %d .The exception message is %s",
					limit, e.getMessage()));
			throw e;
		}

		while (rs.next()) {
			int qId = rs.getInt("id");
			String qLanguage = rs.getString("language");
			languageList.add(new Language(qId, qLanguage));
		}

		return languageList;
	}
}
