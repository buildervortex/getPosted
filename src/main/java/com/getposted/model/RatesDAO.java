package com.getposted.model;

import java.sql.SQLException;
import java.util.List;

public interface RatesDAO extends DAO<Rates> {
    public Rates get(int authorId, int userId) throws SQLException;
    double getRate(int authorId) throws SQLException;                               // SELECT SUM(value)/COUNT(value) AS rate FROM Rates WHERE authorId = 2 GROUP BY authorId
    List<Integer> getRatedUserIds(int authorId) throws SQLException;                // SELECT userId FROM Rates WHERE authorId = 2
    List<Rates> getOrderedByDate(int authorId, boolean desc) throws SQLException;   // SELECT * FROM Rates WHERE authorId = 2 ORDER BY date DESC
    List<Rates> getOrderByValue(int authorId, boolean desc) throws SQLException;    // SELECT * FROM Rates WHERE authorId = 2 ORDER BY value DESC
    List<Rates> getRatesByValue(int authorId, int value) throws SQLException;       // SELECT * FROM Rates WHERE authorId = 2 AND value = 4

    List<Integer> getAllAuthorsOrderByThereRateValue(boolean desc) throws SQLException;         //  SELECT id FROM (SELECT A.id,SUM(value) AS sum FROM Author AS A LEFT JOIN Rates AS R ON R.authorId = A.id GROUP BY A.id ORDER BY sum DESC) AS table1
    List<Integer> getListOfAuthorsOrderByThereRateValue(boolean desc, int limit) throws SQLException;      // SELECT id FROM (SELECT A.id,SUM(value) AS sum FROM Author AS A LEFT JOIN Rates AS R ON R.authorId = A.id GROUP BY A.id ORDER BY sum DESC) AS table1 LIMIT 1
}
