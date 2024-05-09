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
	public Language get(int id) throws SQLException {
		Connection con = Database.getConnection();
		Language language = null;
		String sqlTemplate = "SELECT * FROM Language WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		ps.setInt(1, id);

		try {
			rs = ps.executeQuery();
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

		con.close();
		return language;
	}

	@Override
	public Language get(String language) throws SQLException {
		Connection con = Database.getConnection();
		Language languageOb = null;
		String sqlTemplate = "SELECT * FROM Language WHERE language = ?";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		ps.setString(1, language);

		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.LanguageDAOImpl class at get() method the id is %s. The exception message is %s",
					language, e.getMessage()));
			throw e;
		}

		if (rs.next()) {
			int qId = rs.getInt("id");
			String qLanguage = rs.getString("language");

			languageOb = new Language(qId, qLanguage);
		}

		con.close();
		return languageOb;
	}

	@Override
	public List<Language> getAll() throws SQLException {
		Connection con = Database.getConnection();
		List<Language> languageList = new ArrayList();
		String sqlTemplate = "SELECT * FROM Language ORDER BY language";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		try {
			rs = ps.executeQuery();
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

		con.close();
		return languageList;
	}

	@Override
	public List<Language> getList(int limit) throws SQLException {
		Connection con = Database.getConnection();
		List<Language> languageList = new ArrayList();
		String sqlTemplate = "SELECT * FROM Language ORDER BY language LIMIT ?";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		ps.setInt(1, limit);

		try {
			rs = ps.executeQuery();
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

		con.close();
		return languageList;
	}

	@Override
	public int insert(Language language) throws SQLException {
		Connection con = Database.getConnection();
		String sqlTemplate = "INSERT INTO Language(id,language) VALUES (?,?)";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		int rowsAffected = -1;

		ps.setInt(1, language.getId());
		ps.setString(2, language.getLanguage());

		try {
			rowsAffected = ps.executeUpdate();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.LanguageDAOImpl class at insert() method. The exception message is %s. The inserted Language name is %s and id is %d",
					e.getMessage(), language.getLanguage(), language.getId()));
			throw e;
		}

		con.close();
		return rowsAffected;
	}

	@Override
	public int update(Language language) throws SQLException {
		Connection con = Database.getConnection();
		String sqlTemplate = "UPDATE Language SET language = ? WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		int rowsAffected = -1;

		ps.setString(1, language.getLanguage());
		ps.setInt(2, language.getId());

		try {
			rowsAffected = ps.executeUpdate();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.LanguageDAOImpl class at update() method. The exception message is %s. The updated Language name is %s and id is %d",
					e.getMessage(), language.getLanguage(), language.getId()));
			throw e;
		}

		con.close();
		return rowsAffected;
	}

	@Override
	public int delete(Language language) throws SQLException {
		Connection con = Database.getConnection();
		String sqlTemplate = "DELETE FROM Language WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		int rowsAffected = -1;

		ps.setInt(1, language.getId());

		try {
			rowsAffected = ps.executeUpdate();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.LanguageDAOImpl class at delete() method. The exception message is %s. The deleted Language name is %s and id is %d",
					e.getMessage(), language.getLanguage(), language.getId()));
			throw e;
		}

		con.close();
		return rowsAffected;
	}

	@Override
	public List<Language> getLangugaesOnAPattern(String pattern) throws SQLException {
		pattern = "%" + pattern + "%";
		Connection con = Database.getConnection();
		List<Language> languageList = new ArrayList();
		String sqlTemplate = "SELECT * FROM Language WHERE LOWER(language) LIKE LOWER(?)";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		ps.setString(1, pattern);

		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.LanguageDAOImpl class at getLangugaesOnAPattern() .The exception message is %s",
					e.getMessage()));
			throw e;
		}

		while (rs.next()) {
			int qId = rs.getInt("id");
			String qLanguage = rs.getString("language");
			languageList.add(new Language(qId, qLanguage));
		}

		con.close();
		return languageList;
	}

	@Override
	public int getLanguagesCount() throws SQLException {
		Connection con = Database.getConnection();
		int count = 0;
		String sqlTemplate = "SELECT COUNT(id) AS count FROM Language";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.LanguageDAOImpl class at getLanguagesCount() .The exception message is %s",
					e.getMessage()));
			throw e;
		}

		if (rs.next()) {
			count = rs.getInt("count");
		}
		return count;
	}

	@Override
	public List<Language> getLanguagesByGivenIds(int... ids) throws SQLException {
		Connection con = Database.getConnection();
		List<Language> languageList = new ArrayList();
		String sqlTemplate = String.format("SELECT * FROM Language WHERE id IN %s",getListRepresentation(ids));
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.LanguageDAOImpl class at getLanguagesByGivenIds() .The exception message is %s",
					e.getMessage()));
			throw e;
		}

		while (rs.next()) {
			int qId = rs.getInt("id");
			String qLanguage = rs.getString("language");
			languageList.add(new Language(qId, qLanguage));
		}

		con.close();
		return languageList;
	}

	@Override
	public List<Language> getAllLanguagesExceptGivenIds(int... ids) throws SQLException {
		Connection con = Database.getConnection();
		List<Language> languageList = new ArrayList();
		String sqlTemplate = String.format("SELECT * FROM Language WHERE id NOT IN %s",getListRepresentation(ids));
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.LanguageDAOImpl class at getAllLanguagesExceptGivenIds() .The exception message is %s",
					e.getMessage()));
			throw e;
		}

		while (rs.next()) {
			int qId = rs.getInt("id");
			String qLanguage = rs.getString("language");
			languageList.add(new Language(qId, qLanguage));
		}

		con.close();
		return languageList;
	}
}
