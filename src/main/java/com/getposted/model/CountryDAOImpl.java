package com.getposted.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.getposted.logger.Logging;
import java.util.logging.Logger;

public class CountryDAOImpl implements CountryDAO {

	private static Logger logger = Logging.getLogger(CountryDAOImpl.class.getName());

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
	public Country get(int id) throws SQLException {
		Connection con = Database.getConnection();
		Country country = null;
		String sqlTemplate = "SELECT * FROM Country WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		ps.setInt(1, id);

		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CountryDAOImpl class at get(int) method the id is %d. The exception message is %s",
					id, e.getMessage()));
			throw e;
		}

		if (rs.next()) {
			int qId = rs.getInt("id");
			String qCountry = rs.getString("country");

			country = new Country(qId, qCountry);
		}

		con.close();
		return country;
	}

	public Country get(String country) throws SQLException {
		Connection con = Database.getConnection();
		Country countryOb = null;
		String sqlTemplate = "SELECT * FROM Country WHERE country = ?";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		ps.setString(1, country);

		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CountryDAOImpl class at get(string) method the id is %s. The exception message is %s",
					country, e.getMessage()));
			throw e;
		}

		if (rs.next()) {
			int qId = rs.getInt("id");
			String qCountry = rs.getString("country");

			countryOb = new Country(qId, qCountry);
		}

		con.close();
		return countryOb;
	}

	@Override
	public List<Country> getAll() throws SQLException {
		Connection con = Database.getConnection();
		List<Country> countryList = new ArrayList();
		String sqlTemplate = "SELECT * FROM Country ORDER BY country ASC";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		try {
			rs = ps.executeQuery();
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

		con.close();
		return countryList;
	}

	@Override
	public int insert(Country country) throws SQLException {
		Connection con = Database.getConnection();
		String sqlTemplate = "INSERT INTO Country(id,country) VALUES (?,?)";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		int rowsAffected = -1;

		ps.setInt(1, country.getId());
		ps.setString(2, country.getCountry());

		try {
			rowsAffected = ps.executeUpdate();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CountryDAOImpl class at insert() method. The exception message is %s. The inserted Country name is %s and id is %d",
					e.getMessage(), country.getCountry(), country.getId()));
			throw e;
		}

		con.close();
		return rowsAffected;
	}

	@Override
	public int update(Country country) throws SQLException {
		Connection con = Database.getConnection();
		String sqlTemplate = "UPDATE Country SET country = ? WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		int rowsAffected = -1;

		ps.setString(1, country.getCountry());
		ps.setInt(2, country.getId());

		try {
			rowsAffected = ps.executeUpdate();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CountryDAOImpl class at update() method. The exception message is %s. The updated Country name is %s and id is %d",
					e.getMessage(), country.getCountry(), country.getId()));
			throw e;
		}

		con.close();
		return rowsAffected;
	}

	@Override
	public int delete(Country country) throws SQLException {
		Connection con = Database.getConnection();
		String sqlTemplate = "DELETE FROM Country WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		int rowsAffected = -1;

		ps.setInt(1, country.getId());

		try {
			rowsAffected = ps.executeUpdate();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CountryDAOImpl class at delete() method. The exception message is %s. The deleted Country name is %s and id is %d",
					e.getMessage(), country.getCountry(), country.getId()));
			throw e;
		}

		con.close();
		return rowsAffected;
	}

	@Override
	public List<Country> getList(int limit) throws SQLException {
		Connection con = Database.getConnection();
		List<Country> countryList = new ArrayList();
		String sqlTemplate = "SELECT * FROM Country ORDER BY country LIMIT ?";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		ps.setInt(1, limit);

		try {
			rs = ps.executeQuery();
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

		con.close();
		return countryList;
	}

	@Override
	public List<Country> getCountriesOnAPattern(String pattern) throws SQLException {
		pattern = "%" + pattern + "%";
		Connection con = Database.getConnection();
		List<Country> countryList = new ArrayList();
		String sqlTemplate = "SELECT * FROM Country WHERE LOWER(country) LIKE LOWER(?)";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		ps.setString(1, pattern);

		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CountryDAOImpl class at getCountriesOnAPattern() .The exception message is %s",
					e.getMessage()));
			throw e;
		}

		while (rs.next()) {
			int qId = rs.getInt("id");
			String qCountry = rs.getString("country");
			countryList.add(new Country(qId, qCountry));
		}

		con.close();
		return countryList;
	}

	@Override
	public int getCountryCount() throws SQLException {
		Connection con = Database.getConnection();
		int count = 0;
		String sqlTemplate = "SELECT COUNT(id) AS count FROM Country";
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CountryDAOImpl class at getCountriesOnAPattern() .The exception message is %s",
					e.getMessage()));
			throw e;
		}

		if (rs.next()) {
			count = rs.getInt("count");
		}

		con.close();
		return count;
	}

	@Override
	public List<Country> getCountriesByGivenIds(int... ids) throws SQLException {
		Connection con = Database.getConnection();
		List<Country> countryList = new ArrayList();
		String sqlTemplate = String.format("SELECT * FROM Country WHERE id IN %s", getListRepresentation(ids));
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CountryDAOImpl class at getCountriesByGivenIds() .The exception message is %s",
					e.getMessage()));
			throw e;
		}

		while (rs.next()) {
			int qId = rs.getInt("id");
			String qCountry = rs.getString("country");
			countryList.add(new Country(qId, qCountry));
		}


		con.close();
		return countryList;
	}

	@Override
	public List<Country> getAllCountriesExceptGivenIds(int... ids) throws SQLException {
		Connection con = Database.getConnection();
		List<Country> countryList = new ArrayList();
		String sqlTemplate = String.format("SELECT * FROM Country WHERE id NOT IN %s", getListRepresentation(ids));
		PreparedStatement ps = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.CountryDAOImpl class at getCountriesByGivenIds() .The exception message is %s",
					e.getMessage()));
			throw e;
		}

		while (rs.next()) {
			int qId = rs.getInt("id");
			String qCountry = rs.getString("country");
			countryList.add(new Country(qId, qCountry));
		}

		con.close();
		return countryList;
	}
}
