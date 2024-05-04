package com.getposted.model;

import java.sql.SQLException;

public interface RatesDAO extends DAO<Rates> {
    public Rates get(int authorId, int userId) throws SQLException;
}
