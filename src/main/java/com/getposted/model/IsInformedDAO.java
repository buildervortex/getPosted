package com.getposted.model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface IsInformedDAO extends DAO<IsInformed> {
    List<IsInformed> getAllInformedsFilterByDate(boolean desc) throws SQLException;

    
    // for admin panel
    List<IsInformed> getAllInformedsInADate(Date date) throws SQLException;
    int getTotalInformedUsersCount() throws SQLException;

    // for a user
    List<IsInformed> getAllInformedOfAUser(int userId) throws SQLException;
    int getTotalInformationCountForAUser(int userId) throws SQLException;

}
