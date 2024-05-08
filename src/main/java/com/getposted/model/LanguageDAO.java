package com.getposted.model;

import java.util.List;
import java.sql.SQLException;

public interface LanguageDAO extends DAO<Language> {
	List<Language> getList(int limit) throws SQLException;

	Language get(String language) throws SQLException;
	List<Language> getLangugaesOnAPattern(String pattern) throws SQLException;	// SELECT * FROM Language WHERE LOWER(language) LIKE LOWER("%ar%")
	int getLanguagesCount() throws SQLException;	// SELECT COUNT(id) AS count FROM Language
	List<Language> getLanguagesByGivenIds(int... ids) throws SQLException;	// SELECT * FROM Language WHERE id IN (1,2,4)
	List<Language> getAllLanguagesExceptGivenIds(int... ids) throws SQLException;	// SELECT * FROM Language WHERE id NOT IN (1,2,4)
}
