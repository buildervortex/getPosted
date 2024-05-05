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

public class InquiryDAOImpl implements InquiryDAO {

    private static Logger logger = Logging.getLogger(InquiryDAOImpl.class.getName());

    @Override
    public Inquiry get(int id) throws SQLException {
        Connection con = Database.getConnection();
        Inquiry inquiry = null;
        String sqlTemplate = "SELECT * FROM Inquiry WHERE id =?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, id);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at get() method the id is %s. The exception message is %s",
                    id, e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            int qId = rs.getInt("id");
            Date purchasedDate = rs.getDate("purchasedDate");
            String shippingAddress = rs.getString("shippingAddress");
            String postalCode = rs.getString("postalCode");
            Time purchasedTime = rs.getTime("purchasedTime");
            String contactName = rs.getString("contactName");
            int count = rs.getInt("count");
            String country = rs.getString("country");
            Date shippedDate = rs.getDate("shippedDate");
            String state = rs.getString("state");
            Double price = rs.getDouble("price");
            int publicationId = rs.getInt("publicationId");
            int userId = rs.getInt("userId");
            int publisherId = rs.getInt("publisherId");

            inquiry = new Inquiry(qId, purchasedDate, shippingAddress, postalCode, purchasedTime, contactName, count,
                    country, shippedDate, state, price, publicationId, userId, publisherId);

        }

        return inquiry;
    }

    @Override
    public List<Inquiry> getAll() throws SQLException {
        Connection con = Database.getConnection();
        List<Inquiry> inquiries = new ArrayList<Inquiry>();
        String sqlTemplate = "SELECT * FROM Inquiry";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getAll().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int qId = rs.getInt("id");
            Date purchasedDate = rs.getDate("purchasedDate");
            String shippingAddress = rs.getString("shippingAddress");
            String postalCode = rs.getString("postalCode");
            Time purchasedTime = rs.getTime("purchasedTime");
            String contactName = rs.getString("contactName");
            int count = rs.getInt("count");
            String country = rs.getString("country");
            Date shippedDate = rs.getDate("shippedDate");
            String state = rs.getString("state");
            Double price = rs.getDouble("price");
            int publicationId = rs.getInt("publicationId");
            int userId = rs.getInt("userId");
            int publisherId = rs.getInt("publisherId");

            inquiries.add(new Inquiry(qId, purchasedDate, shippingAddress, postalCode, purchasedTime, contactName,
                    count, country, shippedDate, state, price, publicationId, userId, publisherId));
        }

        return inquiries;
    }

    @Override
    public int insert(Inquiry inquiry) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "INSERT INTO Inquiry (id, purchasedDate, shippingAddress, postalCode, purchasedTime, contactName, count, country, shippedDate, state, price, publicationId, userId, publisherId) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = -1;

        ps.setInt(1, inquiry.getId());
        ps.setDate(2, inquiry.getPurchasedDate());
        ps.setString(3, inquiry.getShippingAddress());
        ps.setString(4, inquiry.getPostalCode());
        ps.setTime(5, inquiry.getPurchasedTime());
        ps.setString(6, inquiry.getContactName());
        ps.setInt(7, inquiry.getCount());
        ps.setString(8, inquiry.getCountry());
        ps.setDate(9, inquiry.getShippedDate());
        ps.setString(10, inquiry.getState());
        ps.setDouble(11, inquiry.getPrice());
        ps.setInt(12, inquiry.getPublicationId());
        ps.setInt(13, inquiry.getUserId());
        ps.setInt(14, inquiry.getPublisherId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "SQLException occurred in the com.getposted.model.InquiryDAOImpl class at insert() method. Exception message: %s. Inquiry details: id=%d, shippingAddress=%s, postalCode=%s, purchasedTime=%s, purchasedDate=%s, contactName=%s, count=%d, country=%s, shippedDate=%s, state=%s, price=%s, publicationId=%d, userId=%d, publisherId=%d",
                    e.getMessage(), inquiry.getId(), inquiry.getShippingAddress(), inquiry.getPostalCode(),
                    inquiry.getPurchasedTime(),
                    inquiry.getPurchasedDate(), inquiry.getContactName(), inquiry.getCount(), inquiry.getCountry(),
                    inquiry.getShippedDate(), inquiry.getState(), inquiry.getPrice(),
                    inquiry.getPublicationId(), inquiry.getUserId(), inquiry.getPublisherId()));
            throw e;
        }

        return rowsAffected;
    }

    @Override
    public int update(Inquiry inquiry) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "UPDATE Inquiry SET purchasedDate=?, shippingAddress=?, postalCode=?, purchasedTime=?, contactName=?, count=?, country=?, shippedDate=?, state=?, price=?, publicationId=?, userId=?, publisherId=? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = -1;

        ps.setDate(1, inquiry.getPurchasedDate());
        ps.setString(2, inquiry.getShippingAddress());
        ps.setString(3, inquiry.getPostalCode());
        ps.setTime(4, inquiry.getPurchasedTime());
        ps.setString(5, inquiry.getContactName());
        ps.setInt(6, inquiry.getCount());
        ps.setString(7, inquiry.getCountry());
        ps.setDate(8, inquiry.getShippedDate());
        ps.setString(9, inquiry.getState());
        ps.setDouble(10, inquiry.getPrice());
        ps.setInt(11, inquiry.getPublicationId());
        ps.setInt(12, inquiry.getUserId());
        ps.setInt(13, inquiry.getPublisherId());
        ps.setInt(14, inquiry.getId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "SQLException occurred in the com.getposted.model.InquiryDAOImpl class at update() method. Exception message: %s. Inquiry details: id=%d, shippingAddress=%s, postalCode=%s, purchasedTime=%s, purchasedDate=%s, contactName=%s, count=%d, country=%s, shippedDate=%s, state=%s, price=%s, publicationId=%d, userId=%d, publisherId=%d",
                    e.getMessage(), inquiry.getId(), inquiry.getShippingAddress(), inquiry.getPostalCode(),
                    inquiry.getPurchasedTime(),
                    inquiry.getPurchasedDate(), inquiry.getContactName(), inquiry.getCount(), inquiry.getCountry(),
                    inquiry.getShippedDate(), inquiry.getState(), inquiry.getPrice(),
                    inquiry.getPublicationId(), inquiry.getUserId(), inquiry.getPublisherId()));
            throw e;
        }
        return rowsAffected;
    }

    @Override
    public int delete(Inquiry inquiry) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "DELETE FROM Inquiry WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = -1;

        ps.setInt(1, inquiry.getId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "SQLException occurred in the com.getposted.model.InquiryDAOImpl class at delete() method. Exception message: %s. Inquiry details: id=%d, shippingAddress=%s, postalCode=%s, purchasedTime=%s, purchasedDate=%s, contactName=%s, count=%d, country=%s, shippedDate=%s, state=%s, price=%s, publicationId=%d, userId=%d, publisherId=%d",
                    e.getMessage(), inquiry.getId(), inquiry.getShippingAddress(), inquiry.getPostalCode(),
                    inquiry.getPurchasedTime(),
                    inquiry.getPurchasedDate(), inquiry.getContactName(), inquiry.getCount(), inquiry.getCountry(),
                    inquiry.getShippedDate(), inquiry.getState(), inquiry.getPrice(),
                    inquiry.getPublicationId(), inquiry.getUserId(), inquiry.getPublisherId()));
            throw e;
        }
        return rowsAffected;
    }

}
