package com.getposted.model;

import java.util.List;
import java.sql.SQLException;

public interface CountryDAO extends DAO<Country> {
	List<Country> getList(int limit) throws SQLException;
}
