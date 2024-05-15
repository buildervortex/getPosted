package com.getposted.model;

import java.sql.SQLException;
import java.util.List;

public interface SaveDAO extends DAO<Save> {
    List<Save> getList(int userId) throws SQLException;
    List<Save> getAllSavesOrderByDate(int userId, boolean desc) throws SQLException;              //  SELECT * FROM Save WHERE userId = 1 ORDER BY date ASC
    List<Save> getListOfSavesOrderByDate(int userId, int limit, boolean desc) throws SQLException;// SELECT * FROM Save WHERE userId = 1 ORDER BY date ASC LIMIT 1
}