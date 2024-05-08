package com.getposted.model;

import java.util.List;
import java.sql.SQLException;

public interface AuthorDAO extends DAO<Author> {
	// add order by
	String getFullName(int authorId) throws SQLException;	// SELECT CONCAT(firstName," ",middleName," ",lastName) AS name FROM Author WHERE Id = 1
	List<Integer> getAllAuthorsIdByName(String patttern) throws SQLException;	// SELECT id FROM Author WHERE LOWER(CONCAT(firstName,middleName,lastName)) LIKE ("%John%")
	List<Integer> getAllAuthorsIdByUserName(String pattern) throws SQLException;	// SELECT id FROM Author WHERE LOWER(userName) LIKE ("%John%")
	List<Author> getAllAuthorsByName(String pattern) throws SQLException;	// SELECT * FROM Author WHERE LOWER(CONCAT(firstName,middleName,lastName)) LIKE ("%John%")
	List<Author> getAllAuthorsByUserName(String pattern) throws SQLException;	// SELECT * FROM Author WHERE LOWER(userName) LIKE ("%John%")
	int getAuthorCount() throws SQLException;		// SELECT COUNT(id) AS count FROM Author
	List<Author> getAllAuthorsInSpecificCountry(int countryId) throws SQLException;	// SELECT * FROM Author WHERE countryId = 1
	int getAuthorCountInSpecificCountry(int countryId) throws SQLException;	// SELECT COUNT(id) AS count FROM Author WHERE countryId = 1
	List<Author> getAuthorsByGivenAuthorIds(int... ids) throws SQLException;	// SELECT * FROM Author WHERE id IN (1,2)
	List<Author> getAllAuthorsExceptGivenAuthorIds(int... ids) throws SQLException;	// SELECT * FROM Author WHERE id NOT IN (1,2)
	List<Author> getAllAuthorsByEmail(String pattern) throws SQLException;	// SELECT * FROM Author WHERE LOWER(email) LIKE ("%@%")
	List<Author> getListOfAuthorsByEmail(String pattern, int limit) throws SQLException;	// SELECT * FROM Author WHERE LOWER(email) LIKE ("%@%") LIMIT 1
	List<Author> getListOfAuthorsByName(String pattern,int limit) throws SQLException;	// SELECT * FROM Author WHERE LOWER(CONCAT(firstName,middleName,lastName)) LIKE ("%John%") LIMIT 1
	List<Author> getListOfAuthorsByUserName(String pattern, int limit) throws SQLException;	// SELECT * FROM Author WHERE LOWER(userName) LIKE ("%John%") LIMIT 1
}
