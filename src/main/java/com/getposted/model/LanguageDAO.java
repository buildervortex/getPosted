package com.getposted.model;

import java.util.List;
import java.sql.SQLException;

public interface LanguageDAO extends DAO<Language>{
	List<Language> getList(int limit) throws SQLException;
	Language get(String language) throws SQLException;
}
