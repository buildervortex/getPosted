package com.getposted.model;

import java.sql.SQLException;
import java.util.List;

public interface RequestDAO extends DAO<Request> {
    List<Request> getAllRequestFromSpecificAuthor(int publisherId, int authorId) throws SQLException; // SELECT * FROM Request WHERE authorId = 1 AND publisherId = 1
    List<Request> getAllRequestForSpecificPublisher(int publisherId) throws SQLException; // SELECT * FROM Request WHERE  publisherId = 1
    int getAllRequestCountFromASpecificAuthor(int publisherId, int authorId) throws SQLException; // SELECT COUNT(id) AS count FROM Request WHERE authorId = 1 AND publisherId = 1

}
