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

public class CountryDAOImpl implements CountryDAO {

	private static Logger logger = Logging.getLogger(CountryDAOImpl.class.getName());

	@Override
	public Country get(int id) throws SQLException {
		Connection con = Database.getConnection();
		Country country = null;
		String sqlTemplate = "SELECT * FROM Country WHERE id = ?";
		PreparedStatement select = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		select.setInt(1, id);

		try {
			rs = select.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CountryDAOImpl class at getId() method the id is %d. The exception message is %s",
					id, e.getMessage()));
			throw e;
		}

		if (rs.next()) {
			int qId = rs.getInt("id");
			String qCountry = rs.getString("country");

			country = new Country(qId, qCountry);
		}

		return country;
	}

	@Override
	public List<Country> getAll() throws SQLException {
		Connection con = Database.getConnection();
		List<Country> countryList = new ArrayList();
		String sqlTemplate = "SELECT * FROM Country";
		PreparedStatement select = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		try {
			rs = select.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CountryDAOImpl class at getAll() .The exception message is %s",
					e.getMessage()));
			throw e;
		}

		while (rs.next()) {
			int qId = rs.getInt("id");
			String qCountry = rs.getString("country");
			countryList.add(new Country(qId, qCountry));
		}

		return countryList;
	}

	@Override
	public int insert(Country country) throws SQLException {
		Connection con = Database.getConnection();
		String sqlTemplate = "INSERT INTO Country(country) VALUES (?)";
		PreparedStatement st = con.prepareStatement(sqlTemplate);
		int result = -1;

		st.setString(1, country.getCountry());

		try {
			result = st.executeUpdate();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CountryDAOImpl class at insert() method. The exception message is %s. The inserted Country name is %s and id is %d",
					e.getMessage(), country.getCountry(), country.getId()));
			throw e;
		}

		return result;
	}

	@Override
	public int update(Country country) throws SQLException {
		Connection con = Database.getConnection();
		String sqlTemplate = "UPDATE Country SET country = ? WHERE id = ?";
		PreparedStatement st = con.prepareStatement(sqlTemplate);
		int result = -1;

		st.setString(1, country.getCountry());
		st.setInt(2, country.getId());

		try {
			result = st.executeUpdate();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CountryDAOImpl class at update() method. The exception message is %s. The updated Country name is %s and id is %d",
					e.getMessage(), country.getCountry(), country.getId()));
			throw e;
		}

		return result;
	}

	@Override
	public int delete(Country country) throws SQLException {
		Connection con = Database.getConnection();
		String sqlTemplate = "DELETE FROM Country WHERE id = ?";
		PreparedStatement st = con.prepareStatement(sqlTemplate);
		int result = -1;

		st.setInt(1, country.getId());

		try {
			result = st.executeUpdate();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CountryDAOImpl class at delete() method. The exception message is %s. The deleted Country name is %s and id is %d",
					e.getMessage(), country.getCountry(), country.getId()));
			throw e;
		}

		return result;
	}

	@Override
	public List<Country> getList(int limit) throws SQLException {
		Connection con = Database.getConnection();
		List<Country> countryList = new ArrayList();
		String sqlTemplate = "SELECT * FROM Country LIMIT ?";
		PreparedStatement select = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		select.setInt(1, limit);

		try {
			rs = select.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CountryDAOImpl class at getList(). The limit is %d .The exception message is %s",
					limit, e.getMessage()));
			throw e;
		}

		while (rs.next()) {
			int qId = rs.getInt("id");
			String qCountry = rs.getString("country");
			countryList.add(new Country(qId, qCountry));
		}

		return countryList;
	}
}
