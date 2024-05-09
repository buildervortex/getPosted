package com.getposted.model;

import java.util.List;
import java.sql.SQLException;

public interface CountryDAO extends DAO<Country> {
	List<Country> getList(int limit) throws SQLException;

	List<Country> getCountriesOnAPattern(String pattern) throws SQLException;	// SELECT * FROM Country WHERE LOWER(country) LIKE LOWER("%au%")
	int getCountryCount() throws SQLException;	//	SELECT COUNT(id) AS count FROM Country
	List<Country> getCountriesByGivenIds(int... ids) throws SQLException;	// SELECT * FROM Country WHERE id IN (1,2,4)
	List<Country> getAllCountriesExceptGivenIds(int... ids) throws SQLException; // SELECT * FROM Country WHERE id NOT IN (1,2,4)
}
