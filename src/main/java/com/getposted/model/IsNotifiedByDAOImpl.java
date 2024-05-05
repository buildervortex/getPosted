package com.getposted.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.getposted.logger.Logging;

public class IsNotifiedByDAOImpl implements IsNotifiedByDAO {

    private static Logger logger = Logging.getLogger(IsNotifiedByDAOImpl.class.getName());

    @Override
    public IsNotifiedBy get(int id) throws SQLException {
        Connection con = Database.getConnection();
        IsNotifiedBy isNotifiedBy = null;
        String sqlTemplate = "SELECT * FROM IsNotifiedBy WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, id);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.IsNotifiedByDAOImpl class at get() method the id is %d. The exception message is %s",
                    id, e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            int qid = rs.getInt("id");
            String notification = rs.getString("notification");
            Date notifiedDate = rs.getDate("notifiedDate");
            Time notifiedTime = rs.getTime("notifiedTime");
            int authorId = rs.getInt("authorId");
            int publisherId = rs.getInt("publisherId");

            isNotifiedBy = new IsNotifiedBy(qid, notification, notifiedDate, notifiedTime, authorId, publisherId);
        }
        return isNotifiedBy;
    }

    @Override
    public List<IsNotifiedBy> getAll() throws SQLException {
        Connection con = Database.getConnection();
        List<IsNotifiedBy> isInformeds = new ArrayList<IsNotifiedBy>();
        String sqlTemplate = "SELECT * FROM IsNotifiedBy ";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.IsNotifiedByDAOImpl class at getAll() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int id = rs.getInt("id");
            String notification = rs.getString("notification");
            Date notifiedDate = rs.getDate("notifiedDate");
            Time notifiedTime = rs.getTime("notifiedTime");
            int authorId = rs.getInt("authorId");
            int publisherId = rs.getInt("publisherId");

            isInformeds.add(new IsNotifiedBy(id, notification, notifiedDate, notifiedTime, authorId, publisherId));
        }
        return isInformeds;
    }

    @Override
    public int insert(IsNotifiedBy isNotifiedBy) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "INSERT INTO IsNotifiedBy(id, notification, notifiedDate, notifiedTime, authorId, publisherId) VALUES(?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = -1;

        ps.setInt(1, isNotifiedBy.getId());
        ps.setString(2, isNotifiedBy.getNotification());
        ps.setDate(3, isNotifiedBy.getNotifiedDate());
        ps.setTime(4, isNotifiedBy.getNotifiedTime());
        ps.setInt(5, isNotifiedBy.getAuthorId());
        ps.setInt(6, isNotifiedBy.getPublisherId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.IsNotifiedByDAOImpl class at insert() method . The exception message is %s. The id is %s. The notification is %s. The notifiedDate is %s. The notifiedTime is %s. The authorId is %s. The publisherId is %s",
                    e.getMessage(), isNotifiedBy.getId(), isNotifiedBy.getNotification(),
                    isNotifiedBy.getNotifiedDate(), isNotifiedBy.getNotifiedTime(), isNotifiedBy.getAuthorId(),
                    isNotifiedBy.getPublisherId()));
            throw e;
        }

        return rowsAffected;
    }

    @Override
    public int update(IsNotifiedBy isNotifiedBy) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "UPDATE IsNotifiedBy SET notification = ?, notifiedDate = ?, notifiedTime = ?, authorId = ?, publisherId = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = -1;

        ps.setString(1, isNotifiedBy.getNotification());
        ps.setDate(2, isNotifiedBy.getNotifiedDate());
        ps.setTime(3, isNotifiedBy.getNotifiedTime());
        ps.setInt(4, isNotifiedBy.getAuthorId());
        ps.setInt(5, isNotifiedBy.getPublisherId());
        ps.setInt(6, isNotifiedBy.getId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.IsNotifiedByDAOImpl class at update() method . The exception message is %s. The id is %s. The notification is %s. The notifiedDate is %s. The notifiedTime is %s. The authorId is %s. The publisherId is %s",
                    e.getMessage(), isNotifiedBy.getId(), isNotifiedBy.getNotification(),
                    isNotifiedBy.getNotifiedDate(), isNotifiedBy.getNotifiedTime(), isNotifiedBy.getAuthorId(),
                    isNotifiedBy.getPublisherId()));
            throw e;
        }

        return rowsAffected;
    }

    @Override
    public int delete(IsNotifiedBy isNotifiedBy) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "DELETE FROM IsNotifiedBy WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = -1;

        ps.setInt(1, isNotifiedBy.getId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.IsNotifiedByDAOImpl class at delete() method . The exception message is %s. The id is %s. The notification is %s. The notifiedDate is %s. The notifiedTime is %s. The authorId is %s. The publisherId is %s",
                    e.getMessage(), isNotifiedBy.getId(), isNotifiedBy.getNotification(),
                    isNotifiedBy.getNotifiedDate(), isNotifiedBy.getNotifiedTime(), isNotifiedBy.getAuthorId(),
                    isNotifiedBy.getPublisherId()));
            throw e;
        }
        return rowsAffected;
    }
}