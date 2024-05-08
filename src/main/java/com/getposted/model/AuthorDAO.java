package com.getposted.model;

import java.util.List;
import java.sql.SQLException;

public interface AuthorDAO extends DAO<Author> {
	// add order by
	String getFullName(int authorId) throws SQLException;	// SELECT CONCAT(firstName," ",middleName," ",lastName) AS name FROM Author WHERE Id = 1
	List<Integer> getAuthorsIdByName(String name) throws SQLException;	// SELECT id FROM Author WHERE LOWER(CONCAT(firstName,middleName,lastName)) LIKE ("%John%")
	List<Integer> getAuthorsIdByUserName(String userName) throws SQLException;	// SELECT id FROM Author WHERE LOWER(userName) LIKE ("%John%")
	List<Author> getAuthorsByName(String name) throws SQLException;	// SELECT * FROM Author WHERE LOWER(CONCAT(firstName,middleName,lastName)) LIKE ("%John%")
	List<Author> getAuthorsByUserName(String userName) throws SQLException;	// SELECT * FROM Author WHERE LOWER(userName) LIKE ("%John%")
	int getAuthorCount() throws SQLException;		// SELECT COUNT(id) AS count FROM Author
	List<Author> getAuthorsInSpecificCountry(int countryId) throws SQLException;	// SELECT * FROM Author WHERE countryId = 1
	int getAuthorCountInSpecificCountry(int countryId) throws SQLException;	// SELECT COUNT(id) AS count FROM Author WHERE countryId = 1
	List<Author> getAuthorsByGivenAuthorIds(int... ids) throws SQLException;	// SELECT * FROM Author WHERE id IN (1,2)
	List<Author> getAllAuthorsExceptGivenAuthorIds(int... ids) throws SQLException;	// SELECT * FROM Author WHERE id NOT IN (1,2)
}
