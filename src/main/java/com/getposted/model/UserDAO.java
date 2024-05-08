package com.getposted.model;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO extends DAO<User> {
    List<User> getUsersForGivenUserIds(int... ids) throws SQLException;             // SELECT * FROM User WHERE id IN (1,2)
    List<User> getAllUsersExceptGivenUsersIds(int... ids) throws SQLException;      // SELECT * FROM User WHERE id NOT IN (1,2)
    String getUserFullName(int userId) throws SQLException;                         // SELECT CONCAT(firstName," ",middleName," ",lastName) AS name FROM User WHERE id = 1
    List<User> getAllUsersForGivenNamePattern(String pattern) throws SQLException;  // SELECT * FROM User WHERE LOWER(CONCAT(firstName," ",middleName," ",lastName)) LIKE LOWER("%al%")
    List<User> getListOfUsersForGivenNamePattern(String pattern, int limit) throws SQLException;  // SELECT * FROM User WHERE LOWER(CONCAT(firstName," ",middleName," ",lastName)) LIKE LOWER("%al%") LIMIT 1
    List<User> getAllUsersByGivenUserNamePattern(String pattern) throws SQLException;  // SELECT * FROM User WHERE LOWER(userName) LIKE LOWER("%1%")
    List<User> getListOfUsersByGivenUserNamePattern(String pattern, int limit) throws SQLException;  // SELECT * FROM User WHERE LOWER(userName) LIKE LOWER("%1%") LIMIT 1
    List<User> getAllUsersByGivenEmailPattern(String pattern) throws SQLException;     // SELECT * FROM User WHERE LOWER(email) LIKE LOWER("%1%")
    List<User> getListOfUsersByGivenEmailPattern(String pattern,int limit) throws SQLException;     // SELECT * FROM User WHERE LOWER(email) LIKE LOWER("%1%") LIMIT 1
}
