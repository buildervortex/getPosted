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

public class IsInformedDAOImpl implements IsInformedDAO {

    private static Logger logger = Logging.getLogger(IsInformedDAOImpl.class.getName());

    @Override
    public IsInformed get(int id) throws SQLException {
        Connection con = Database.getConnection();
        IsInformed isInformed = null;
        String sqlTemplate = "SELECT * FROM IsInformed WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, id);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.IsInformedDAOImpl class at getId() method the id is %d. The exception message is %s",
                    id, e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            int qid = rs.getInt("id");
            String notification = rs.getString("notification");
            Date notifiedDate = rs.getDate("notifiedDate");
            Time notifiedTime = rs.getTime("notifiedTime");
            int userId = rs.getInt("userId");
            int publisherId = rs.getInt("publisherId");

            isInformed = new IsInformed(qid, notification, notifiedDate, notifiedTime, userId, publisherId);
        }
        return isInformed;
    }

    @Override
    public List<IsInformed> getAll() throws SQLException {
        Connection con = Database.getConnection();
        List<IsInformed> isInformeds = new ArrayList<IsInformed>();
        String sqlTemplate = "SELECT * FROM IsInformed ";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.IsInformedDAOImpl class at getAll() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int id = rs.getInt("id");
            String notification = rs.getString("notification");
            Date notifiedDate = rs.getDate("notifiedDate");
            Time notifiedTime = rs.getTime("notifiedTime");
            int userId = rs.getInt("userId");
            int publisherId = rs.getInt("publisherId");

            isInformeds.add(new IsInformed(id, notification, notifiedDate, notifiedTime, userId, publisherId));
        }
        return isInformeds;
    }

    @Override
    public int insert(IsInformed isInformed) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "INSERT INTO IsInformed(id, notification, notifiedDate, notifiedTime, userId, publisherId) VALUES(?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int result = -1;

        ps.setInt(1, isInformed.getId());
        ps.setString(2, isInformed.getNotification());
        ps.setDate(3, isInformed.getNotifiedDate());
        ps.setTime(4, isInformed.getNotifiedTime());
        ps.setInt(5, isInformed.getUserId());
        ps.setInt(6, isInformed.getPublisherId());

        try {
            result = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.IsInformedDAOImpl class at insert() method . The exception message is %s. The id is %s. The notification is %s. The notifiedDate is %s. The notifiedTime is %s. The userId is %s. The publisherId is %s",
                    e.getMessage(), isInformed.getId(), isInformed.getNotification(), isInformed.getNotifiedDate(),
                    isInformed.getNotifiedTime(), isInformed.getUserId(), isInformed.getPublisherId()));
            throw e;
        }

        return result;
    }

    @Override
    public int update(IsInformed isInformed) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "UPDATE IsInformed SET notification = ?, notifiedDate = ?, notifiedTime = ?, userId = ?, publisherId = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int result = -1;

        ps.setString(1, isInformed.getNotification());
        ps.setDate(2, isInformed.getNotifiedDate());
        ps.setTime(3, isInformed.getNotifiedTime());
        ps.setInt(4, isInformed.getUserId());
        ps.setInt(5, isInformed.getPublisherId());
        ps.setInt(6, isInformed.getId());

        try {
            result = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.IsInformedDAOImpl class at update() method . The exception message is %s. The id is %s. The notification is %s. The notifiedDate is %s. The notifiedTime is %s. The userId is %s. The publisherId is %s",
                    e.getMessage(), isInformed.getId(), isInformed.getNotification(), isInformed.getNotifiedDate(),
                    isInformed.getNotifiedTime(), isInformed.getUserId(), isInformed.getPublisherId()));
            throw e;
        }

        return result;
    }

    @Override
    public int delete(IsInformed isInformed) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "DELETE FROM IsInformed WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int result = -1;

        ps.setInt(1, isInformed.getId());

        try {
            result = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.IsInformedDAOImpl class at delete() method . The exception message is %s. The id is %s. The notification is %s. The notifiedDate is %s. The notifiedTime is %s. The userId is %s. The publisherId is %s",
                    e.getMessage(), isInformed.getId(), isInformed.getNotification(), isInformed.getNotifiedDate(),
                    isInformed.getNotifiedTime(), isInformed.getUserId(), isInformed.getPublisherId()));
            throw e;
        }
        return result;
    }

    @Override
    public List<IsInformed> getAllInformedsFilterByDate(boolean desc) throws SQLException {
        String order = "ASC";
        if(desc) order = "DESC";

        Connection con = Database.getConnection();
        List<IsInformed> isInformeds = new ArrayList<IsInformed>();
        String sqlTemplate = String.format("SELECT * FROM IsInformed ORDER BY notifiedDate %s, notifiedTime",order);
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.IsInformedDAOImpl class at getAllInformedsFilterByDate() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int id = rs.getInt("id");
            String notification = rs.getString("notification");
            Date notifiedDate = rs.getDate("notifiedDate");
            Time notifiedTime = rs.getTime("notifiedTime");
            int userId = rs.getInt("userId");
            int publisherId = rs.getInt("publisherId");

            isInformeds.add(new IsInformed(id, notification, notifiedDate, notifiedTime, userId, publisherId));
        }
        return isInformeds;
    }

    @Override
    public List<IsInformed> getAllInformedsInADate(Date date) throws SQLException {
        Connection con = Database.getConnection();
        List<IsInformed> isInformeds = new ArrayList<IsInformed>();
        String sqlTemplate = "SELECT * FROM IsInformed WHERE notifiedDate = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setDate(1, date);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.IsInformedDAOImpl class at getAllInformedsInADate() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int id = rs.getInt("id");
            String notification = rs.getString("notification");
            Date notifiedDate = rs.getDate("notifiedDate");
            Time notifiedTime = rs.getTime("notifiedTime");
            int userId = rs.getInt("userId");
            int publisherId = rs.getInt("publisherId");

            isInformeds.add(new IsInformed(id, notification, notifiedDate, notifiedTime, userId, publisherId));
        }
        return isInformeds;
    }

    @Override
    public int getTotalInformedUsersCount() throws SQLException {
        Connection con = Database.getConnection();
        int count = 0;
        String sqlTemplate = "SELECT COUNT(userId) AS count FROM (SELECT userId FROM IsInformed GROUP BY userId) AS userId";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.IsInformedDAOImpl class at getTotalInformedUsersCount() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }
        if(rs.next()){
            count = rs.getInt("count");
        }


        return count;
    }

    @Override
    public List<IsInformed> getAllInformaInformedsForAUser(int userId) throws SQLException {
        Connection con = Database.getConnection();
        List<IsInformed> isInformeds = new ArrayList<IsInformed>();
        String sqlTemplate = "SELECT * FROM IsInformed WHERE userId = ? ORDER BY notifiedDate";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, userId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model. class at getAllInformaInformedsForAUser() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int id = rs.getInt("id");
            String notification = rs.getString("notification");
            Date notifiedDate = rs.getDate("notifiedDate");
            Time notifiedTime = rs.getTime("notifiedTime");
            int quserId = rs.getInt("userId");
            int publisherId = rs.getInt("publisherId");

            isInformeds.add(new IsInformed(id, notification, notifiedDate, notifiedTime, quserId, publisherId));
        }
        return isInformeds;        
    }

    @Override
    public int getTotalInformationCountForAUser(int userId) throws SQLException {
        Connection con = Database.getConnection();
        int count = 0;
        String sqlTemplate = "SELECT COUNT(id) as count FROM IsInformed WHERE userId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, userId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.IsInformedDAOImpl class at getTotalInformationCountForAUser() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }
        if(rs.next()){
            count = rs.getInt("count");
        }


        return count;        
    }

}
