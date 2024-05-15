package com.getposted.model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface IsNotifiedByDAO extends DAO<IsNotifiedBy> {

    // for admin
    List<IsNotifiedBy> getAllNotificationsFilterByDate(boolean desc) throws SQLException;
    List<IsNotifiedBy> getAllNotificationsInADate(Date date) throws SQLException;
    int getTotalInformedAuthorCount() throws SQLException;

    // for author
    List<IsNotifiedBy> getAllInformationsOfAuthor(int authorId) throws SQLException;
    int getTotalNotificationCountForAnAuthor(int authorId) throws SQLException;
}
