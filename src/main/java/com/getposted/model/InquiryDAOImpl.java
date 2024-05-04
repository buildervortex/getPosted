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
        PreparedStatement select = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        select.setInt(1, id);

        try {
            rs = select.executeQuery();
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
        PreparedStatement select = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        try {
            rs = select.executeQuery();
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
        PreparedStatement st = con.prepareStatement(sqlTemplate);
        int result = -1;

        st.setInt(1, inquiry.getId());
        st.setDate(2, inquiry.getPurchasedDate());
        st.setString(3, inquiry.getShippingAddress());
        st.setString(4, inquiry.getPostalCode());
        st.setTime(5, inquiry.getPurchasedTime());
        st.setString(6, inquiry.getContactName());
        st.setInt(7, inquiry.getCount());
        st.setString(8, inquiry.getCountry());
        st.setDate(9, inquiry.getShippedDate());
        st.setString(10, inquiry.getState());
        st.setDouble(11, inquiry.getPrice());
        st.setInt(12, inquiry.getPublicationId());
        st.setInt(13, inquiry.getUserId());
        st.setInt(14, inquiry.getPublisherId());

        try {
            result = st.executeUpdate();
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

        return result;
    }

    @Override
    public int update(Inquiry inquiry) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "UPDATE Inquiry SET purchasedDate=?, shippingAddress=?, postalCode=?, purchasedTime=?, contactName=?, count=?, country=?, shippedDate=?, state=?, price=?, publicationId=?, userId=?, publisherId=? WHERE id=?";
        PreparedStatement st = con.prepareStatement(sqlTemplate);
        int result = -1;

        st.setDate(1, inquiry.getPurchasedDate());
        st.setString(2, inquiry.getShippingAddress());
        st.setString(3, inquiry.getPostalCode());
        st.setTime(4, inquiry.getPurchasedTime());
        st.setString(5, inquiry.getContactName());
        st.setInt(6, inquiry.getCount());
        st.setString(7, inquiry.getCountry());
        st.setDate(8, inquiry.getShippedDate());
        st.setString(9,inquiry.getState());
        st.setDouble(10, inquiry.getPrice());
        st.setInt(11, inquiry.getPublicationId());
        st.setInt(12, inquiry.getUserId());
        st.setInt(13, inquiry.getPublisherId());
        st.setInt(14,inquiry.getId());

        try {
            result = st.executeUpdate();
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
        return result;
    }

    @Override
    public int delete(Inquiry inquiry) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "DELETE FROM Inquiry WHERE id=?";
        PreparedStatement st = con.prepareStatement(sqlTemplate);
        int result = -1;

        st.setInt(1, inquiry.getId());

        try {
            result = st.executeUpdate();
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
        return result;
    }

}
