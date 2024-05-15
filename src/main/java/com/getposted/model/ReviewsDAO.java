package com.getposted.model;

import java.sql.SQLException;
import java.util.List;

public interface ReviewsDAO extends DAO<Reviews> {
    public Reviews get(int publicationId, int userId) throws SQLException;  // SELECT * FROM Reviews WHERE publicationId =1 AND userId = 1
    public double getRate(int publicationId) throws SQLException;           // SELECT SUM(value)/COUNT(value) AS rate FROM Reviews WHERE publicationId = 1 GROUP BY publicationId
    List<Integer> getRatedUserIds(int publicationId) throws SQLException;   // SELECT userId FROM Reviews WHERE publicationId = 2
    List<Reviews> getOrderByDate(int publicationId, boolean desc) throws SQLException;  // SELECT * FROM Reviews WHERE publicationId = 2 ORDER BY date DESC
    List<Reviews> getOrderByValue(int publicationId, boolean desc) throws SQLException; // SELECT * FROM Reviews WHERE publicationId = 2 ORDER BY value DESC
    List<Reviews> getReviesByValue(int publicationId, int value) throws SQLException;   // SELECT * FROM Reviews WHERE publicationId = 2 AND value = 4

    List<Integer> getAllPublicationsOrderByThereReviewValue(boolean desc) throws SQLException;  // SELECT id FROM (SELECT P.id,SUM(value) AS sum FROM Publication AS P LEFT JOIN Reviews AS R ON R.publicationId = P.id GROUP BY P.id ORDER BY sum DESC) AS table1
    List<Integer> getListOfPublicationsOrderByThereReviewValue(boolean desc, int limit) throws SQLException;    // SELECT id FROM (SELECT P.id,SUM(value) AS sum FROM Publication AS P LEFT JOIN Reviews AS R ON R.publicationId = P.id GROUP BY P.id ORDER BY sum DESC) AS table1 LIMIT 1
}
