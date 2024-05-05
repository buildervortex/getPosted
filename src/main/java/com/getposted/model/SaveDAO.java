package com.getposted.model;

import java.sql.SQLException;
import java.util.List;

public interface SaveDAO extends DAO<Save> {
    List<Save> getList(int userId) throws SQLException;
}