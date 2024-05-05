package com.getposted.model;

import java.sql.SQLException;
import java.util.List;

public interface SearchTagDAO extends DAO<SearchTag> {
    List<SearchTag> getList(int publicationId) throws SQLException;
}
