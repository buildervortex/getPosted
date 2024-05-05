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

public class ReviewsDAOImpl implements ReviewsDAO{

    private static Logger logger = Logging.getLogger(ReviewsDAOImpl.class.getName());

    @Override
    public Reviews get(int id) throws SQLException {
        Connection con = Database.getConnection();
        Reviews reviews = null;
        String sqlTemplate = "SELECT * FROM Reviews WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, id);

        try{
			rs = ps.executeQuery();
		}
		catch(SQLException e){
			logger.warning(String.format("There is SQLException happend in the com.getposted.model.Reviews class at get() .The exception message is %s. The id is %s.",e.getMessage(),id));
			throw e;
		}

        if(rs.next()){
            int qid = rs.getInt("id");
            int value = rs.getInt("value");
            String review = rs.getString("review");
            Date date = rs.getDate("date");
            int publicationId = rs.getInt("publicationId");
            int userId = rs.getInt("userId");

            reviews = new Reviews(qid, value, review, date, publicationId, userId);
        }
        return reviews;
    }

    @Override
    public List<Reviews> getAll() throws SQLException {
        Connection con = Database.getConnection();
        List<Reviews> reviews = new ArrayList();
        String sqlTemplate = "SELECT * FROM Reviews";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        try{
			rs = ps.executeQuery();
		}
		catch(SQLException e){
			logger.warning(String.format("There is SQLException happend in the com.getposted.model.Reviews class at getAll() .The exception message is %s",e.getMessage()));
			throw e;
		}

        while(rs.next()){
            int qid = rs.getInt("id");
            int value = rs.getInt("value");
            String review = rs.getString("review");
            Date date = rs.getDate("date");
            int publicationId = rs.getInt("publicationId");
            int userId = rs.getInt("userId");

            reviews.add(new Reviews(qid, value, review, date, publicationId, userId));
        }
        return reviews;
    }

    @Override
    public int insert(Reviews reviews) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "INSERT INTO Reviews (id, value, review, date, publicationId, userId) VALUES(?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = -1;

        ps.setInt(1, reviews.getId());
        ps.setInt(2, reviews.getValue());
        ps.setString(3, reviews.getReview());
        ps.setDate(4, reviews.getDate());
        ps.setInt(5,reviews.getPublicationId());
        ps.setInt(6,reviews.getUserId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.Reviews class at insert() method . The exception message is %s. The id is %s. The value is %s. The review is %s. The date is %s. The authorId is %s. The userId is %s.",
                    e.getMessage(), reviews.getId(),reviews.getValue(),reviews.getReview(),reviews.getDate().toString(),reviews.getPublicationId(), reviews.getUserId()));
            throw e;
        }
        return rowsAffected;
    }

    @Override
    public int update(Reviews reviews) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "UPDATE Reviews SET value = ?, review = ?, date = ?, publicationId = ?, userId = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = -1;

        ps.setInt(1, reviews.getValue());
        ps.setString(2, reviews.getReview());
        ps.setDate(3, reviews.getDate());
        ps.setInt(4, reviews.getPublicationId());
        ps.setInt(5, reviews.getUserId());
        ps.setInt(6, reviews.getId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.Reviews class at update() method . The exception message is %s. The id is %s. The value is %s. The review is %s. The date is %s. The authorId is %s. The userId is %s.",
                    e.getMessage(), reviews.getId(),reviews.getValue(),reviews.getReview(),reviews.getDate().toString(),reviews.getPublicationId(), reviews.getUserId()));
            throw e;
        }
        return rowsAffected;
    }

    @Override
    public int delete(Reviews reviews) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "DELETE FROM Reviews WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = -1;

        ps.setInt(1, reviews.getId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.Reviews class at delete() method . The exception message is %s. The id is %s. The value is %s. The review is %s. The date is %s. The authorId is %s. The userId is %s.",
                    e.getMessage(),reviews.getValue(),reviews.getReview(),reviews.getDate().toString(),reviews.getPublicationId(), reviews.getUserId()));
            throw e;
        }
        return rowsAffected;
    }    
}