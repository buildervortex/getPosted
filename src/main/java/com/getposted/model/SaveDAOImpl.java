package com.getposted.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.getposted.logger.Logging;

public class SaveDAOImpl implements SaveDAO {
    private static Logger logger = Logging.getLogger(SaveDAOImpl.class.getName());

    @Override
    public Save get(int save) throws SQLException {
        Save saveOb = null;
        return saveOb;
    }

    @Override
    public List<Save> getAll() throws SQLException {
        Connection con = Database.getConnection();
        List<Save> saves = new ArrayList();
        String sqlTemplate = "SELECT * FROM Save";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.SaveDAOImpl class at getAll() .The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            Date date = rs.getDate("date");
            int userId = rs.getInt("userId");
            int publicationId = rs.getInt("publicationId");

            saves.add(new Save(date, userId, publicationId));
        }

        return saves;
    }

    @Override
    public int insert(Save save) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "INSERT INTO Save (date,userId, publicationId) VALUES (?,?,?)";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = 0;

        ps.setDate(1, save.getDate());
        ps.setInt(2, save.getUserId());
        ps.setInt(3, save.getPublicationId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.SaveDAOImpl class at insert() method. The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        return rowsAffected;
    }

    @Override
    public int update(Save save) throws SQLException {
        int rowsAffected = -1;
        return rowsAffected;
    }

    @Override
    public int delete(Save save) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "DELETE FROM Save WHERE userId =? AND publicationId =?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = 0;

        ps.setInt(1, save.getUserId());
        ps.setInt(2, save.getPublicationId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.SaveDAOImpl class at delete() method. The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        return rowsAffected;
    }

    @Override
    public List<Save> getList(int userId) throws SQLException {
        Connection con = Database.getConnection();
        List<Save> saves = new ArrayList();
        String sqlTemplate = "SELECT * FROM Save WHERE userId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, userId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.SaveDAOImpl class at getList() .The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            Date date = rs.getDate("date");
            int quserId = rs.getInt("userId");
            int publicationId = rs.getInt("publicationId");

            saves.add(new Save(date, quserId, publicationId));
        }

        return saves;
    }

    @Override
    public List<Save> getAllSavesOrderByDate(int userId, boolean desc) throws SQLException {
        String order = desc ? "DESC" : "ASC";
        Connection con = Database.getConnection();
        List<Save> saves = new ArrayList();
        String sqlTemplate = String.format("SELECT * FROM Save WHERE userId = ? ORDER BY date %s", order);
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, userId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.SaveDAOImpl class at getAllSavesOrderByDate() .The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            Date date = rs.getDate("date");
            int quserId = rs.getInt("userId");
            int publicationId = rs.getInt("publicationId");

            saves.add(new Save(date, quserId, publicationId));
        }

        return saves;
    }

    @Override
    public List<Save> getListOfSavesOrderByDate(int userId, int limit, boolean desc) throws SQLException {
        String order = desc ? "DESC" : "ASC";
        Connection con = Database.getConnection();
        List<Save> saves = new ArrayList();
        String sqlTemplate = String.format("SELECT * FROM Save WHERE userId = ? ORDER BY date %s LIMIT ?", order);
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, userId);
        ps.setInt(2,limit);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.SaveDAOImpl class at getListOfSavesOrderByDate() .The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            Date date = rs.getDate("date");
            int quserId = rs.getInt("userId");
            int publicationId = rs.getInt("publicationId");

            saves.add(new Save(date, quserId, publicationId));
        }

        return saves;
    }
}