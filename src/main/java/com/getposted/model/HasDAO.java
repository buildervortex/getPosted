package com.getposted.model;

import java.sql.SQLException;
import java.util.List;

public interface HasDAO extends DAO<Has> {
    List<Has> getList(int authorId) throws SQLException;
}
