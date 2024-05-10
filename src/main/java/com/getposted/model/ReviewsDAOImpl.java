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

public class ReviewsDAOImpl implements ReviewsDAO {

    private static Logger logger = Logging.getLogger(ReviewsDAOImpl.class.getName());

    @Override
    public Reviews get(int id) throws SQLException {
        Connection con = Database.getConnection();
        Reviews reviews = null;
        String sqlTemplate = "SELECT * FROM Reviews WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, id);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.ReviewsDAOImpl class at get() .The exception message is %s. The id is %s.",
                    e.getMessage(), id));
            throw e;
        }

        if (rs.next()) {
            int qid = rs.getInt("id");
            int value = rs.getInt("value");
            String review = rs.getString("review");
            Date date = rs.getDate("date");
            int publicationId = rs.getInt("publicationId");
            int userId = rs.getInt("userId");

            reviews = new Reviews(qid, value, review, date, publicationId, userId);
        }
        con.close();
        return reviews;
    }

    @Override
    public List<Reviews> getAll() throws SQLException {
        Connection con = Database.getConnection();
        List<Reviews> reviews = new ArrayList();
        String sqlTemplate = "SELECT * FROM Reviews";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.ReviewsDAOImpl class at getAll() .The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int qid = rs.getInt("id");
            int value = rs.getInt("value");
            String review = rs.getString("review");
            Date date = rs.getDate("date");
            int publicationId = rs.getInt("publicationId");
            int userId = rs.getInt("userId");

            reviews.add(new Reviews(qid, value, review, date, publicationId, userId));
        }
        con.close();
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
        ps.setInt(5, reviews.getPublicationId());
        ps.setInt(6, reviews.getUserId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.ReviewsDAOImpl class at insert() method . The exception message is %s. The id is %s. The value is %s. The review is %s. The date is %s. The authorId is %s. The userId is %s.",
                    e.getMessage(), reviews.getId(), reviews.getValue(), reviews.getReview(),
                    reviews.getDate().toString(), reviews.getPublicationId(), reviews.getUserId()));
            throw e;
        }
        con.close();
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
                    "There is SQLException happend in the com.getposted.model.ReviewsDAOImpl class at update() method . The exception message is %s. The id is %s. The value is %s. The review is %s. The date is %s. The authorId is %s. The userId is %s.",
                    e.getMessage(), reviews.getId(), reviews.getValue(), reviews.getReview(),
                    reviews.getDate().toString(), reviews.getPublicationId(), reviews.getUserId()));
            throw e;
        }
        con.close();
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
                    "There is SQLException happend in the com.getposted.model.ReviewsDAOImpl class at delete() method . The exception message is %s. The id is %s. The value is %s. The review is %s. The date is %s. The authorId is %s. The userId is %s.",
                    e.getMessage(), reviews.getValue(), reviews.getReview(), reviews.getDate().toString(),
                    reviews.getPublicationId(), reviews.getUserId()));
            throw e;
        }
        con.close();
        return rowsAffected;
    }

    @Override
    public Reviews get(int publicationId, int userId) throws SQLException {
        Connection con = Database.getConnection();
        Reviews reviews = null;
        String sqlTemplate = "SELECT * FROM Reviews WHERE publicationId = ? AND userId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publicationId);
        ps.setInt(2, userId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.ReviewsDAOImpl class at get() .The exception message is %s.",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            int qid = rs.getInt("id");
            int value = rs.getInt("value");
            String review = rs.getString("review");
            Date date = rs.getDate("date");
            int qpublicationId = rs.getInt("publicationId");
            int quserId = rs.getInt("userId");

            reviews = new Reviews(qid, value, review, date, qpublicationId, quserId);
        }
        con.close();
        return reviews;
    }

    @Override
    public double getRate(int publicationId) throws SQLException {
        Connection con = Database.getConnection();
        double rate = 0;
        String sqlTemplate = "SELECT SUM(value)/COUNT(value) AS rate FROM Reviews WHERE publicationId = ? GROUP BY publicationId";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publicationId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.ReviewsDAOImpl class at get() .The exception message is %s.",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            rate = rs.getDouble("rate");
        }

        con.close();
        return rate;
    }

    @Override
    public List<Integer> getRatedUserIds(int publicationId) throws SQLException {
        Connection con = Database.getConnection();
        List<Integer> userIds = new ArrayList<Integer>();
        String sqlTemplate = "SELECT userId FROM Reviews WHERE publicationId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publicationId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.ReviewsDAOImpl class at getRatedUserIds() .The exception message is %s.",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            userIds.add(rs.getInt("userId"));
        }
        con.close();
        return userIds;
    }

    @Override
    public List<Reviews> getOrderByDate(int publicationId, boolean desc) throws SQLException {
        String order = desc ? "DESC" : "ASC";
        Connection con = Database.getConnection();
        List<Reviews> reviews = new ArrayList();
        String sqlTemplate = String.format("SELECT * FROM Reviews WHERE publicationId = ? ORDER BY date %s", order);
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publicationId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.ReviewsDAOImpl class at getOrderByDate() .The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int qid = rs.getInt("id");
            int value = rs.getInt("value");
            String review = rs.getString("review");
            Date date = rs.getDate("date");
            int qpublicationId = rs.getInt("publicationId");
            int userId = rs.getInt("userId");

            reviews.add(new Reviews(qid, value, review, date, qpublicationId, userId));
        }
        con.close();
        return reviews;
    }

    @Override
    public List<Reviews> getOrderByValue(int publicationId, boolean desc) throws SQLException {
        String order = desc ? "DESC" : "ASC";
        Connection con = Database.getConnection();
        List<Reviews> reviews = new ArrayList();
        String sqlTemplate = String.format("SELECT * FROM Reviews WHERE publicationId = ? ORDER BY value %s", order);
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publicationId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.ReviewsDAOImpl class at getOrderByValue() .The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int qid = rs.getInt("id");
            int value = rs.getInt("value");
            String review = rs.getString("review");
            Date date = rs.getDate("date");
            int qpublicationId = rs.getInt("publicationId");
            int userId = rs.getInt("userId");

            reviews.add(new Reviews(qid, value, review, date, qpublicationId, userId));
        }
        con.close();
        return reviews;
    }

    @Override
    public List<Reviews> getReviesByValue(int publicationId, int value) throws SQLException {
        Connection con = Database.getConnection();
        List<Reviews> reviews = new ArrayList();
        String sqlTemplate = "SELECT * FROM Reviews WHERE publicationId = ? AND value = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publicationId);
        ps.setInt(2, value);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.ReviewsDAOImpl class at getReviesByValue() .The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int qid = rs.getInt("id");
            int qvalue = rs.getInt("value");
            String review = rs.getString("review");
            Date date = rs.getDate("date");
            int qpublicationId = rs.getInt("publicationId");
            int userId = rs.getInt("userId");

            reviews.add(new Reviews(qid, qvalue, review, date, qpublicationId, userId));
        }
        con.close();
        return reviews;
    }

    @Override
    public List<Integer> getAllPublicationsOrderByThereReviewValue(boolean desc) throws SQLException {
        String order = desc ? "DESC" : "ASC";
        Connection con = Database.getConnection();
        List<Integer> publicationIds = new ArrayList<Integer>();
        String sqlTemplate = String.format(
                "SELECT id FROM (SELECT P.id,SUM(value) AS sum FROM Publication AS P LEFT JOIN Reviews AS R ON R.publicationId = P.id GROUP BY P.id ORDER BY sum %s) AS table1",
                order);
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;



        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.ReviewsDAOImpl class at getAllPublicationsOrderByThereReviewValue() .The exception message is %s.",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            publicationIds.add(rs.getInt("id"));
        }
        con.close();
        return publicationIds;
    }

    @Override
    public List<Integer> getListOfPublicationsOrderByThereReviewValue(boolean desc, int limit) throws SQLException {
        String order = desc ? "DESC" : "ASC";
        Connection con = Database.getConnection();
        List<Integer> publicationIds = new ArrayList<Integer>();
        String sqlTemplate = String.format(
                "SELECT id FROM (SELECT P.id,SUM(value) AS sum FROM Publication AS P LEFT JOIN Reviews AS R ON R.publicationId = P.id GROUP BY P.id ORDER BY sum %s) AS table1 LIMIT ?",
                order);
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, limit);
        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.ReviewsDAOImpl class at getAllPublicationsOrderByThereReviewValue() .The exception message is %s.",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            publicationIds.add(rs.getInt("id"));
        }
        con.close();
        return publicationIds;
    }
}