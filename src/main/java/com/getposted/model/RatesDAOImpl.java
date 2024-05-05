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

public class RatesDAOImpl implements RatesDAO{

    private static Logger logger = Logging.getLogger(RatesDAOImpl.class.getName());

    @Override
    public Rates get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Rates> getAll() throws SQLException {
        Connection con = Database.getConnection();
        List<Rates> rates = new ArrayList();
        String sqlTemplate = "SELECT * FROM Rates";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        try{
			rs = ps.executeQuery();
		}
		catch(SQLException e){
			logger.warning(String.format("There is SQLException happend in the com.getposted.model.RatesDAOImpl class at getAll() .The exception message is %s",e.getMessage()));
			throw e;
		}

        while(rs.next()){
            int value = rs.getInt("value");
            String review = rs.getString("review");
            Date date = rs.getDate("date");
            int authorId = rs.getInt("authorId");
            int userId = rs.getInt("userId");

            rates.add(new Rates(value, review, date, authorId, userId));
        }
        return rates;
    }

    @Override
    public int insert(Rates rates) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "INSERT INTO Rates (value, review, date, authorId, userId) VALUES(?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = -1;

        ps.setInt(1, rates.getValue());
        ps.setString(2, rates.getReview());
        ps.setDate(3, rates.getDate());
        ps.setInt(4,rates.getAuthorId());
        ps.setInt(5,rates.getUserId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.RatesDAOImpl class at insert() method . The exception message is %s. The value is %s. The review is %s. The date is %s. The authorId is %s. The userId is %s.",
                    e.getMessage(),rates.getValue(),rates.getReview(),rates.getDate().toString(),rates.getAuthorId(), rates.getUserId()));
            throw e;
        }
        return rowsAffected;
    }

    @Override
    public int update(Rates rates) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "UPDATE Rates SET value = ?, review = ?, date = ? WHERE authorId = ? AND userId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = -1;

        ps.setInt(1, rates.getValue());
        ps.setString(2, rates.getReview());
        ps.setDate(3, rates.getDate());
        ps.setInt(4, rates.getAuthorId());
        ps.setInt(5, rates.getUserId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.RatesDAOImpl class at update() method . The exception message is %s. The value is %s. The review is %s. The date is %s. The authorId is %s. The userId is %s.",
                    e.getMessage(),rates.getValue(),rates.getReview(),rates.getDate().toString(),rates.getAuthorId(), rates.getUserId()));
            throw e;
        }
        return rowsAffected;
    }

    @Override
    public int delete(Rates rates) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "DELETE FROM Rates WHERE authorId = ? AND userId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = -1;

        ps.setInt(1, rates.getAuthorId());
        ps.setInt(2,rates.getUserId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.RatesDAOImpl class at delete() method . The exception message is %s. The value is %s. The review is %s. The date is %s. The authorId is %s. The userId is %s.",
                    e.getMessage(),rates.getValue(),rates.getReview(),rates.getDate().toString(),rates.getAuthorId(), rates.getUserId()));
            throw e;
        }
        return rowsAffected;
    }

    @Override
    public Rates get(int authorId, int userId) throws SQLException {
        Connection con = Database.getConnection();
        Rates rates = null;
        String sqlTemplate = "SELECT * FROM Rates WHERE authorId = ? AND userId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1,authorId);
        ps.setInt(2, userId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.RatesDAOImpl class at get(int,int) method. The exception message is %s. The authorId is %s. The userId is %s.",
                    e.getMessage(),authorId,userId));
            throw e;
        }

        if (rs.next()) {
            int value = rs.getInt("value");
            String review = rs.getString("review");
            Date date = rs.getDate("date");
            int qauthorId = rs.getInt("authorId");
            int quserId = rs.getInt("userId");

            rates = new Rates(value, review, date, qauthorId, quserId);
        }
        return rates;
    }   
}