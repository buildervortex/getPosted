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

    private static String getListRepresentation(int[] ids) {
        String listRepresentation = "";
        listRepresentation += "( ";
        for (int i = 0; i < ids.length; i++) {
            listRepresentation += ids[i];
            if (i != ids.length - 1)
                listRepresentation += ",";
        }
        listRepresentation += " )";
        return listRepresentation;
    }

    public static String getListRepresentation(String[] ids) {
        String listRepresentation = "";
        listRepresentation += "( ";
        for (int i = 0; i < ids.length; i++) {
            listRepresentation += "\"" + ids[i] + "\"";
            if (i != ids.length - 1)
                listRepresentation += ",";
        }
        listRepresentation += " )";
        return listRepresentation;
    }

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

    @Override
    public List<Inquiry> getAllHardCopyInquiriesByPurchasedDate(boolean desc, int publisherId) throws SQLException {
        String orderBy = "ASC";
        if (desc)
            orderBy = "DESC";

        Connection con = Database.getConnection();
        List<Inquiry> inquiries = new ArrayList<Inquiry>();
        String sqlTemplate = String.format(
                "SELECT * FROM Inquiry WHERE publisherId = ? ORDER BY purchasedDate %s, purchasedTime DESC", orderBy);
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getAllHardCopyInquiriesByPurchasedDate().The exception message is %s",
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
            int qpublisherId = rs.getInt("publisherId");

            inquiries.add(new Inquiry(qId, purchasedDate, shippingAddress, postalCode, purchasedTime, contactName,
                    count, country, shippedDate, state, price, publicationId, userId, qpublisherId));
        }

        return inquiries;
    }

    @Override
    public List<Inquiry> getListOfHardCopyInquiriesByPurchasedDate(int limit, boolean desc, int publisherId)
            throws SQLException {
        String orderBy = "ASC";
        if (desc)
            orderBy = "DESC";

        Connection con = Database.getConnection();
        List<Inquiry> inquiries = new ArrayList<Inquiry>();
        String sqlTemplate = String.format(
                "SELECT * FROM Inquiry WHERE publisherId = ? ORDER BY purchasedDate %s, purchasedTime DESC LIMIT ?",
                orderBy);
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, limit);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getListOfHardCopyInquiriesByPurchasedDate().The exception message is %s",
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
            int qpublisherId = rs.getInt("publisherId");

            inquiries.add(new Inquiry(qId, purchasedDate, shippingAddress, postalCode, purchasedTime, contactName,
                    count, country, shippedDate, state, price, publicationId, userId, qpublisherId));
        }

        return inquiries;
    }

    @Override
    public List<Inquiry> getAllHardCopyInquiriesByPrice(boolean desc, int publisherId) throws SQLException {
        String orderBy = "ASC";
        if (desc)
            orderBy = "DESC";

        Connection con = Database.getConnection();
        List<Inquiry> inquiries = new ArrayList<Inquiry>();
        String sqlTemplate = String.format("SELECT * FROM Inquiry WHERE publisherId = ? ORDER BY price %s", orderBy);
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getAllHardCopyInquiriesByPrice().The exception message is %s",
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
            int qpublisherId = rs.getInt("publisherId");

            inquiries.add(new Inquiry(qId, purchasedDate, shippingAddress, postalCode, purchasedTime, contactName,
                    count, country, shippedDate, state, price, publicationId, userId, qpublisherId));
        }

        return inquiries;
    }

    @Override
    public List<Inquiry> getListOfHardCopyInquiriesByPrice(int limit, boolean desc, int publisherId)
            throws SQLException {
        String orderBy = "ASC";
        if (desc)
            orderBy = "DESC";

        Connection con = Database.getConnection();
        List<Inquiry> inquiries = new ArrayList<Inquiry>();
        String sqlTemplate = String.format("SELECT * FROM Inquiry WHERE publisherId = ? ORDER BY price %s LIMIT ?",
                orderBy);
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, limit);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getListOfHardCopyInquiriesByPrice().The exception message is %s",
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
            int qpublisherId = rs.getInt("publisherId");

            inquiries.add(new Inquiry(qId, purchasedDate, shippingAddress, postalCode, purchasedTime, contactName,
                    count, country, shippedDate, state, price, publicationId, userId, qpublisherId));
        }

        return inquiries;
    }

    @Override
    public List<Inquiry> getAllOfHardCopyInquiriesForAState(String state, int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        List<Inquiry> inquiries = new ArrayList<Inquiry>();
        String sqlTemplate = "SELECT * FROM Inquiry WHERE publisherId = ? AND state = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setString(2, state);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getAllOfHardCopyInquiriesForAState().The exception message is %s",
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
            String qstate = rs.getString("state");
            Double price = rs.getDouble("price");
            int publicationId = rs.getInt("publicationId");
            int userId = rs.getInt("userId");
            int qpublisherId = rs.getInt("publisherId");

            inquiries.add(new Inquiry(qId, purchasedDate, shippingAddress, postalCode, purchasedTime, contactName,
                    count, country, shippedDate, qstate, price, publicationId, userId, qpublisherId));
        }

        return inquiries;
    }

    @Override
    public List<Inquiry> getListOfHardCopyInquiriesForAState(String state, int limit, int publisherId)
            throws SQLException {
        Connection con = Database.getConnection();
        List<Inquiry> inquiries = new ArrayList<Inquiry>();
        String sqlTemplate = "SELECT * FROM Inquiry WHERE publisherId = ? AND state = ? LIMIT ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setString(2, state);
        ps.setInt(3, limit);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getListOfHardCopyInquiriesForAState().The exception message is %s",
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
            String qstate = rs.getString("state");
            Double price = rs.getDouble("price");
            int publicationId = rs.getInt("publicationId");
            int userId = rs.getInt("userId");
            int qpublisherId = rs.getInt("publisherId");

            inquiries.add(new Inquiry(qId, purchasedDate, shippingAddress, postalCode, purchasedTime, contactName,
                    count, country, shippedDate, qstate, price, publicationId, userId, qpublisherId));
        }

        return inquiries;
    }

    @Override
    public List<Inquiry> getALLHardCopyInquiriesForAPurchasedDate(Date date, int publisherid) throws SQLException {
        Connection con = Database.getConnection();
        List<Inquiry> inquiries = new ArrayList<Inquiry>();
        String sqlTemplate = "SELECT * FROM Inquiry WHERE publisherId = ? AND purchasedDate = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherid);
        ps.setDate(2, date);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at testGetALLHardCopyInquiriesForAPurchasedDate().The exception message is %s",
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
            int qpublisherId = rs.getInt("publisherId");

            inquiries.add(new Inquiry(qId, purchasedDate, shippingAddress, postalCode, purchasedTime, contactName,
                    count, country, shippedDate, state, price, publicationId, userId, qpublisherId));
        }

        return inquiries;
    }

    @Override
    public double getTotalSumOfHardCopyInquriesPrices(int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        double sum = 0;
        String sqlTemplate = "SELECT SUM(price) AS sum FROM Inquiry WHERE publisherId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getTotalSumOfHardCopyInquriesPrices().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            sum = rs.getDouble("sum");
        }

        return sum;
    }

    @Override
    public int getTotalHardCopyInquiryCount(int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        int count = 0;
        String sqlTemplate = "SELECT COUNT(id) AS count FROM Inquiry WHERE publisherId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getTotalHardCopyInquiryCount().The exception message is %s",
                    e.getMessage()));
            throw e;
        }
        if (rs.next()) {
            count = rs.getInt("count");
        }

        return count;
    }

    @Override
    public int getTotalHardCopiesSold(int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        int count = 0;
        String sqlTemplate = "SELECT SUM(count) AS sum FROM Inquiry WHERE publisherId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getTotalHardCopiesSold().The exception message is %s",
                    e.getMessage()));
            throw e;
        }
        if (rs.next()) {
            count = rs.getInt("sum");
        }

        return count;
    }

    public int getTotalHardCopiesSoldInGivenStates(int publisherId, String... states) throws SQLException {
        Connection con = Database.getConnection();
        int count = 0;
        String sqlTemplate = String.format(
                "SELECT SUM(count) AS sum FROM Inquiry WHERE publisherId = ? AND state IN %s",
                getListRepresentation(states));
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getTotalHardCopiesSoldInGivenStates().The exception message is %s",
                    e.getMessage()));
            throw e;
        }
        if (rs.next()) {
            count = rs.getInt("sum");
        }

        return count;
    }

    public int getTotalHardCopiesSoldInAllStatesExceptTheGiveStates(int publisherId, String... states)
            throws SQLException {
        Connection con = Database.getConnection();
        int count = 0;
        String sqlTemplate = String.format(
                "SELECT SUM(count) AS sum FROM Inquiry WHERE publisherId = ? AND state NOT IN %s",
                getListRepresentation(states));
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getTotalHardCopiesSoldInAllStatesExceptTheGiveStates().The exception message is %s",
                    e.getMessage()));
            throw e;
        }
        if (rs.next()) {
            count = rs.getInt("sum");
        }

        return count;
    }

    @Override
    public int TotalUsersWhoPerchasedPublications(int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        int count = 0;
        String sqlTemplate = "SELECT COUNT(userId) AS count FROM (SELECT * FROM Inquiry WHERE publisherId = ? GROUP BY userId) AS user";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at TotalUsersWhoPerchasedPublications().The exception message is %s",
                    e.getMessage()));
            throw e;
        }
        if (rs.next()) {
            count = rs.getInt("count");
        }

        return count;
    }

    @Override
    public List<Inquiry> getALLHardCopyInquiriesOfAGiveUser(int userId, int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        List<Inquiry> inquiries = new ArrayList<Inquiry>();
        String sqlTemplate = "SELECT * FROM Inquiry WHERE publisherId = ? AND userId = ? ORDER BY purchasedDate DESC, purchasedTime DESC";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, userId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getALLHardCopyInquiriesOfAGiveUser().The exception message is %s",
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
            int quserId = rs.getInt("userId");
            int qpublisherId = rs.getInt("publisherId");

            inquiries.add(new Inquiry(qId, purchasedDate, shippingAddress, postalCode, purchasedTime, contactName,
                    count, country, shippedDate, state, price, publicationId, quserId, qpublisherId));
        }

        return inquiries;
    }

    @Override
    public int getTotalHardCopyInquiryCountOfAGiveUser(int userId, int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        int count = 0;
        String sqlTemplate = "SELECT COUNT(id) AS count  FROM Inquiry WHERE publisherId = ? AND userId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, userId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getTotalHardCopyInquiryCountOfAGiveUser().The exception message is %s",
                    e.getMessage()));
            throw e;
        }
        if (rs.next()) {
            count = rs.getInt("count");
        }

        return count;
    }

    @Override
    public double getTotalHardCopyInquriyPayementFromAUser(int userId, int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        double sum = 0;
        String sqlTemplate = "SELECT SUM(price) AS sum FROM Inquiry WHERE publisherId = ? AND userId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, userId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getTotalHardCopyInquriyPayementFromAUser().The exception message is %s",
                    e.getMessage()));
            throw e;
        }
        if (rs.next()) {
            sum = rs.getDouble("sum");
        }

        return sum;
    }

    @Override
    public int getTotalPublicationsCopiesPurchasedByUser(int userId, int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        int count = 0;
        String sqlTemplate = "SELECT SUM(count) AS sum FROM Inquiry WHERE publisherId = ? AND userId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, userId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getTotalPublicationsCopiesPurchasedByUser().The exception message is %s",
                    e.getMessage()));
            throw e;
        }
        if (rs.next()) {
            count = rs.getInt("sum");
        }

        return count;
    }

    @Override
    public List<Inquiry> getALLHardCopyInquiriesForAPublication(int publicationId, int publisherId)
            throws SQLException {
        Connection con = Database.getConnection();
        List<Inquiry> inquiries = new ArrayList<Inquiry>();
        String sqlTemplate = "SELECT * FROM Inquiry WHERE publisherId = ? AND publicationId = ? ORDER BY purchasedDate DESC, purchasedTime DESC";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, publicationId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getALLHardCopyInquiriesForAPublication().The exception message is %s",
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
            int qpublicationId = rs.getInt("publicationId");
            int userId = rs.getInt("userId");
            int qpublisherId = rs.getInt("publisherId");

            inquiries.add(new Inquiry(qId, purchasedDate, shippingAddress, postalCode, purchasedTime, contactName,
                    count, country, shippedDate, state, price, qpublicationId, userId, qpublisherId));
        }

        return inquiries;
    }

    @Override
    public int getTotalHardCopyInquiryCountForAPublication(int publicationId, int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        int count = 0;
        String sqlTemplate = "SELECT COUNT(id) AS count FROM Inquiry WHERE publisherId = ? AND publicationId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, publicationId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getTotalHardCopyInquiryCountForAPublication().The exception message is %s",
                    e.getMessage()));
            throw e;
        }
        if (rs.next()) {
            count = rs.getInt("count");
        }

        return count;
    }

    @Override
    public double getTotalHardCopyInquriyPayementsForAPublication(int publicationId, int publisherId)
            throws SQLException {
        Connection con = Database.getConnection();
        double sum = 0;
        String sqlTemplate = "SELECT SUM(price) AS sum FROM Inquiry WHERE publisherId = ? AND publicationId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, publicationId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getTotalHardCopyInquriyPayementsForAPublication().The exception message is %s",
                    e.getMessage()));
            throw e;
        }
        if (rs.next()) {
            sum = rs.getDouble("sum");
        }

        return sum;
    }

    @Override
    public int getTotalHardCopiesSoldFromAPublication(int publicationId, int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        int count = 0;
        String sqlTemplate = "SELECT SUM(count) AS sum FROM Inquiry WHERE publisherId = ? AND publicationId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, publicationId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getTotalHardCopiesSoldFromAPublication().The exception message is %s",
                    e.getMessage()));
            throw e;
        }
        if (rs.next()) {
            count = rs.getInt("sum");
        }

        return count;
    }

    @Override
    public int getTotalHardCopyPagesCountSoldFromAPublication(int publicationId, int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        int count = 0;
        String sqlTemplate = "SELECT SUM(totalCount) AS sum FROM (SELECT (I.count*P.pageCount) AS totalCount FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id WHERE publisherId = ? AND publicationId = ?) AS table1";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, publicationId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getTotalHardCopyPagesCountSoldFromAPublication().The exception message is %s",
                    e.getMessage()));
            throw e;
        }
        if (rs.next()) {
            count = rs.getInt("sum");
        }

        return count;
    }

    @Override
    public List<Integer> getUserPurchasedAllHardCopyPublicationsIds(int userId, int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        List<Integer> publicationIds = new ArrayList<Integer>();
        String sqlTemplate = "SELECT publicationId FROM Inquiry WHERE publisherId = ? AND userId = ? GROUP BY publicationId";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, userId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getUserPurchasedAllHardCopyPublicationsIds().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int publicationId = rs.getInt("publicationId");
            publicationIds.add(publicationId);
        }

        return publicationIds;
    }

    @Override
    public List<Integer> getAllUserIdsWhoPurchasedHardCopiesOfTheGiveNPublication(int publicationId, int publisherId)
            throws SQLException {
        Connection con = Database.getConnection();
        List<Integer> userIds = new ArrayList<Integer>();
        String sqlTemplate = "SELECT * FROM (SELECT userId FROM Inquiry WHERE publisherId = ? AND publicationId = ?) AS table1 GROUP BY userId";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, publicationId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getAllUserIdsWhoPurchasedHardCopiesOfTheGiveNPublication().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int userId = rs.getInt("userId");
            userIds.add(userId);
        }

        return userIds;
    }

    @Override
    public int getCountOfHardCopyInquriesInAState(String state, int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        int count = 0;
        String sqlTemplate = "SELECT COUNT(id) AS count FROM Inquiry WHERE publisherId = ? AND state = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setString(2, state);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getCountOfHardCopyInquriesInAState().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getInt("count");
        }
        return count;
    }

    @Override
    public List<Inquiry> getListOfHardCopyInquiriesFromGivenInquryIdList(int publisherId, int... inqueryIds)
            throws SQLException {
        Connection con = Database.getConnection();
        List<Inquiry> inquiries = new ArrayList<Inquiry>();
        String sqlTemplate = String.format("SELECT * FROM Inquiry WHERE publisherId = ? AND id IN %s",
                InquiryDAOImpl.getListRepresentation(inqueryIds));
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getListOfHardCopyInquiriesFromGivenInquryIdList().The exception message is %s",
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
            int qpublisherId = rs.getInt("publisherId");

            inquiries.add(new Inquiry(qId, purchasedDate, shippingAddress, postalCode, purchasedTime, contactName,
                    count, country, shippedDate, state, price, publicationId, userId, qpublisherId));
        }

        return inquiries;
    }

    @Override
    public List<Inquiry> getAllHardCopyInquiriesByAPublisher(int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        List<Inquiry> inquiries = new ArrayList<Inquiry>();
        String sqlTemplate = " select * from Inquiry WHERE PublisherId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getAllHardCopyInquiriesByAPublisher().The exception message is %s",
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
            int qpublisherId = rs.getInt("publisherId");

            inquiries.add(new Inquiry(qId, purchasedDate, shippingAddress, postalCode, purchasedTime, contactName,
                    count, country, shippedDate, state, price, publicationId, userId, qpublisherId));
        }

        return inquiries;
    }

    @Override
    public List<Inquiry> getAllHardCopyInquiriesInEachStateExceptTheGivenState(int publisherId, String state)
            throws SQLException {
        Connection con = Database.getConnection();
        List<Inquiry> inquiries = new ArrayList<Inquiry>();
        String sqlTemplate = "select * from Inquiry WHERE PublisherId = ? AND state <> ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setString(2, state);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getAllHardCopyInquiriesInEachStateExceptTheGivenState().The exception message is %s",
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
            String qstate = rs.getString("state");
            Double price = rs.getDouble("price");
            int publicationId = rs.getInt("publicationId");
            int userId = rs.getInt("userId");
            int qpublisherId = rs.getInt("publisherId");

            inquiries.add(new Inquiry(qId, purchasedDate, shippingAddress, postalCode, purchasedTime, contactName,
                    count, country, shippedDate, qstate, price, publicationId, userId, qpublisherId));
        }

        return inquiries;
    }

    @Override
    public int getTotalCountOfHardCopyInquriesInGivenState(int publisherId, String state) throws SQLException {
        Connection con = Database.getConnection();
        int count = 0;
        String sqlTemplate = "select SUM(count) AS count from Inquiry WHERE PublisherId = ? AND state = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setString(2, state);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getTotalCountOfHardCopyInquriesInGivenState().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getInt("count");
        }
        return count;
    }

    @Override
    public double getSumOfAllDiscountGivenForEveryHardCopyInqury(int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        double count = 0;
        String sqlTemplate = "SELECT SUM(totalDiscount) AS sum FROM (select (I.count*P.hardCopyDiscount) AS totalDiscount from Inquiry AS I LEFT JOIN Publisher AS P ON I.publisherId = P.id WHERE PublisherId = ?) AS discount";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getSumOfAllDiscountGivenForEveryHardCopyInqury().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getDouble("sum");
        }
        return count;
    }

    @Override
    public double getSumOFAllDiscountGivenToEveryHardCopyInquryInGivenState(int publisherId, String state)
            throws SQLException {
        Connection con = Database.getConnection();
        double count = 0;
        String sqlTemplate = "SELECT SUM(totalDiscount) AS sum FROM (select (I.count*P.hardCopyDiscount) AS totalDiscount from Inquiry AS I LEFT JOIN Publisher AS P ON I.publisherId = P.id WHERE PublisherId = ? AND state = ?) AS discount";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setString(2, state);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getSumOFAllDiscountGivenToEveryHardCopyInquryInGivenState().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getDouble("sum");
        }
        return count;
    }

    @Override
    public double getSumOfAllDiscountGivenToEveryHardCopyInquriyInAnyStateExceptGivenState(int publisherId,
            String state) throws SQLException {
        Connection con = Database.getConnection();
        double count = 0;
        String sqlTemplate = "SELECT SUM(totalDiscount) AS sum FROM (select (I.count*P.hardCopyDiscount) AS totalDiscount from Inquiry AS I LEFT JOIN Publisher AS P ON I.publisherId = P.id WHERE PublisherId = ? AND state <> ?) AS discount";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setString(2, state);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getSumOfAllDiscountGivenToEveryHardCopyInquriyInAnyStateExceptGivenState().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getDouble("sum");
        }
        return count;
    }

    @Override
    public double getSumOfAllDiscountGivenForEveryHardCopyInquryInAnyStateExceptGivenStates(int publisherId,
            String... state) throws SQLException {
        Connection con = Database.getConnection();
        double count = 0;
        String sqlTemplate = String.format(
                "SELECT SUM(totalDiscount) AS sum FROM (select (I.count*P.hardCopyDiscount) AS totalDiscount from Inquiry AS I LEFT JOIN Publisher AS P ON I.publisherId = P.id WHERE PublisherId = ? AND state NOT IN %s) AS discount",
                getListRepresentation(state));
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getSumOfAllDiscountGivenForEveryHardCopyInquryInAnyStateExceptGivenStates().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getDouble("sum");
        }
        return count;
    }

    @Override
    public double getSumOfAllDiscountGivenForEveryHardCopyInquryInGivenStates(int publisherId, String... state)
            throws SQLException {
        Connection con = Database.getConnection();
        double count = 0;
        String sqlTemplate = String.format(
                "SELECT SUM(totalDiscount) AS sum FROM (select (I.count*P.hardCopyDiscount) AS totalDiscount from Inquiry AS I LEFT JOIN Publisher AS P ON I.publisherId = P.id WHERE PublisherId = ? AND state IN %s) AS discount",
                getListRepresentation(state));
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getSumOfAllDiscountGivenForEveryHardCopyInquryInGivenStates().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getDouble("sum");
        }
        return count;
    }

    @Override
    public double getSumOfAllCommissionGotFormEveryHardCopyInqury(int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        double count = 0;
        String sqlTemplate = "SELECT SUM(totalCommission) AS sum FROM (SELECT (count*hardCopyCommission) as totalCommission, I.id FROM Inquiry AS I LEFT JOIN Publisher AS P ON I.publisherId = P.id WHERE publisherId = ?) AS commission";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getSumOfAllDiscountGivenForEveryHardCopyInqury().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getDouble("sum");
        }
        return count;
    }

    @Override
    public double getSumOfAllCommissionGotFormEveryHardCopyInquryInGivenStates(int publisherId, String... state)
            throws SQLException {
        Connection con = Database.getConnection();
        double count = 0;
        String sqlTemplate = String.format(
                "SELECT SUM(totalCommission) AS sum FROM (SELECT (count*hardCopyCommission) as totalCommission, I.id FROM Inquiry AS I LEFT JOIN Publisher AS P ON I.publisherId = P.id WHERE publisherId = ? AND state In %s) AS commission",
                getListRepresentation(state));
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getSumOfAllCommissionGotFormEveryHardCopyInquryInGivenStates().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getDouble("sum");
        }
        return count;
    }

    @Override
    public double getSumOfAllCommissionGotFormEveryHardCopyInquryInAnyStateExceptGivenStates(int publisherId,
            String... state) throws SQLException {
        Connection con = Database.getConnection();
        double count = 0;
        String sqlTemplate = String.format(
                "SELECT SUM(totalCommission) AS sum FROM (SELECT (count*hardCopyCommission) as totalCommission, I.id FROM Inquiry AS I LEFT JOIN Publisher AS P ON I.publisherId = P.id WHERE publisherId = ? AND state NOT IN %s) AS commission",
                getListRepresentation(state));
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getSumOfAllCommissionGotFormEveryHardCopyInquryInAnyStateExceptGivenStates().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getDouble("sum");
        }
        return count;
    }

    @Override
    public double getSumOfTotalCostNeedPrintPagesInEachHardCopyInquryInEachState(int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        double count = 0;
        String sqlTemplate = "SELECT SUM(totalCostForPages) AS sum FROM (SELECT (I.count*P.pageCount*PU.hardCopyPricePerPage) AS totalCostForPages FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON PU.id = I.publisherId WHERE publisherId = ?) AS cost";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getSumOfTotalCostNeedPrintPagesInEachHardCopyInquryInEachState().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getDouble("sum");
        }
        return count;
    }

    @Override
    public double getSumOfTotalCostNeedPrintPagesInEachHardCopyInquryInEachStateExceptGivenStates(int publisherId,
            String... state) throws SQLException {
        Connection con = Database.getConnection();
        double count = 0;
        String sqlTemplate = String.format(
                "SELECT SUM(totalCostForPages) AS sum FROM (SELECT (I.count*P.pageCount*PU.hardCopyPricePerPage) AS totalCostForPages FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON PU.id = I.publisherId WHERE publisherId = ? AND state NOT IN %s) AS cost",
                getListRepresentation(state));
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getSumOfTotalCostNeedPrintPagesInEachHardCopyInquryInEachStateExceptGivenStates().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getDouble("sum");
        }
        return count;
    }

    @Override
    public double getSumOfTotalCostNeedPrintPagesInEachHardCopyInquryInGivenStates(int publisherId, String... state)
            throws SQLException {
        Connection con = Database.getConnection();
        double count = 0;
        String sqlTemplate = String.format(
                "SELECT SUM(totalCostForPages) AS sum FROM (SELECT (I.count*P.pageCount*PU.hardCopyPricePerPage) AS totalCostForPages FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON PU.id = I.publisherId WHERE publisherId = ? AND state In %s) AS cost",
                getListRepresentation(state));
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getSumOfTotalCostNeedPrintPagesInEachHardCopyInquryInGivenStates().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getDouble("sum");
        }
        return count;
    }

    @Override
    public double getSumOfEveryAuthorTotalInquryCommission(int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        double count = 0;
        String sqlTemplate = "SELECT SUM(commission) AS sum FROM (SELECT (count*pageCount*hardCopyPageCommissionForAuthor) AS commission FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON I.publisherId = PU.id  WHERE publisherId = 1) AS totalCommission";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getSumOfAllDiscountGivenForEveryHardCopyInqury().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getDouble("sum");
        }
        return count;
    }

    @Override
    public double getSumOfEveryAuthorTotalInquryCommissionInGiveStates(int publisherId, String... state)
            throws SQLException {
        Connection con = Database.getConnection();
        double count = 0;
        String sqlTemplate = String.format(
                "SELECT SUM(commission) AS sum FROM (SELECT (count*pageCount*hardCopyPageCommissionForAuthor) AS commission FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON I.publisherId = PU.id  WHERE publisherId = ? AND state IN %s) AS totalCommission",
                getListRepresentation(state));
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getSumOfEveryAuthorTotalInquryCommissionInGiveStates().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getDouble("sum");
        }
        return count;
    }

    @Override
    public double getSumOfEveryAuthorTotalInquryCommissionInAnyStateExceptGivenStates(int publisherId, String... state)
            throws SQLException {
        Connection con = Database.getConnection();
        double count = 0;
        String sqlTemplate = String.format(
                "SELECT SUM(commission) AS sum FROM (SELECT (count*pageCount*hardCopyPageCommissionForAuthor) AS commission FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON I.publisherId = PU.id  WHERE publisherId = ? AND state NOT IN %s) AS totalCommission",
                getListRepresentation(state));
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getSumOfEveryAuthorTotalInquryCommissionInAnyStateExceptGivenStates().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getDouble("sum");
        }
        return count;
    }

    @Override
    public double getSumOfAnAuthorTotalCommissionInEveryHardCopyInqury(int publisherId, int authorId)
            throws SQLException {
        Connection con = Database.getConnection();
        double count = 0;
        String sqlTemplate = "SELECT SUM(commission) AS sum FROM (SELECT (count*pageCount*hardCopyPageCommissionForAuthor) AS commission FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON I.publisherId = PU.id LEFT JOIN Author AS A ON A.id = P.authorId WHERE publisherId = ? AND authorId = ?) AS totalCommission";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, authorId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getSumOfAnAuthorTotalCommissionInEveryHardCopyInqury().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getDouble("sum");
        }
        return count;
    }

    @Override
    public double getSumOfAnAuthorTotalCommissionInGivenStatesHardCopyInquries(int publisherId, int authorId,
            String... state) throws SQLException {
        Connection con = Database.getConnection();
        double count = 0;
        String sqlTemplate = String.format(
                "SELECT SUM(commission) AS sum FROM (SELECT (count*pageCount*hardCopyPageCommissionForAuthor) AS commission FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON I.publisherId = PU.id LEFT JOIN Author AS A ON A.id = P.authorId WHERE publisherId = ? AND authorId = ? AND state IN %s) AS totalCommission",
                getListRepresentation(state));
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, authorId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getSumOfAnAuthorTotalCommissionInGivenStatesHardCopyInquries().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getDouble("sum");
        }
        return count;
    }

    @Override
    public double getSumOfAnAuthorTotalCommissionInAnyStateExceptGivenStatesHardCopyInquries(int publisherId,
            int authorId,
            String... state) throws SQLException {
        Connection con = Database.getConnection();
        double count = 0;
        String sqlTemplate = String.format(
                "SELECT SUM(commission) AS sum FROM (SELECT (count*pageCount*hardCopyPageCommissionForAuthor) AS commission FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON I.publisherId = PU.id LEFT JOIN Author AS A ON A.id = P.authorId WHERE publisherId = ? AND authorId = ? AND state NOT IN %s) AS totalCommission",
                getListRepresentation(state));
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, authorId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getSumOfAnAuthorTotalCommissionInAnyStateExceptGivenStatesHardCopyInquries().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getDouble("sum");
        }
        return count;
    }

    @Override
    public double getSumOfAuthorHardCopyCommissionInAllInquryInAllStates(int publisherId, int publicationId)
            throws SQLException {
        Connection con = Database.getConnection();
        double count = 0;
        String sqlTemplate = "SELECT SUM(commission) AS sum FROM (SELECT (count*pageCount*hardCopyPageCommissionForAuthor) AS commission FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON I.publisherId = PU.id  WHERE publisherId = ? AND I.publicationId = ? ) AS totalCommission";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, publicationId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getSumOfAuthorHardCopyCommissionInAllInquryInAllStates().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getDouble("sum");
        }
        return count;
    }

    @Override
    public double getSumOfAuthorHardCopyCommissionInGivenStatesInquries(int publisherId, int publicationId,
            String... state) throws SQLException {
        Connection con = Database.getConnection();
        double count = 0;
        String sqlTemplate = String.format(
                "SELECT SUM(commission) AS sum FROM (SELECT (count*pageCount*hardCopyPageCommissionForAuthor) AS commission FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON I.publisherId = PU.id  WHERE publisherId = ? AND I.publicationId = ? AND state IN %s ) AS totalCommission",
                getListRepresentation(state));
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, publicationId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getSumOfAnAuthorTotalCommissionInAnyStateExceptGivenStatesHardCopyInquries().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getDouble("sum");
        }
        return count;
    }

    @Override
    public double getSumOfAuthorHardCopyCommissionInAnyStateExceptGivenStatesInquries(int publisherId,
            int publicationId, String... state) throws SQLException {
        Connection con = Database.getConnection();
        double count = 0;
        String sqlTemplate = String.format(
                "SELECT SUM(commission) AS sum FROM (SELECT (count*pageCount*hardCopyPageCommissionForAuthor) AS commission FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Publisher AS PU ON I.publisherId = PU.id  WHERE publisherId = ? AND I.publicationId = ? AND state NOT IN %s ) AS totalCommission",
                getListRepresentation(state));
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, publicationId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at getSumOfAuthorHardCopyCommissionInAnyStateExceptGivenStatesInquries().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getDouble("sum");
        }
        return count;
    }

    @Override
    public List<Integer> getAuthorIdsWhosPublicationsWasSoldAsHardCopies(int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        List<Integer> authorIds = new ArrayList<Integer>();
        String sqlTemplate = "SELECT A.id FROM Inquiry AS I LEFT JOIN Publication AS P ON I.publicationId = P.id LEFT JOIN Author AS A ON A.id = P.authorId WHERE publisherId = ? GROUP BY A.id";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.InquiryDAOImpl class at  getAuthorIdsWhosPublicationsWasSoldAsHardCopies().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int authorId = rs.getInt("id");
            authorIds.add(authorId);
        }

        return authorIds;  
    }

}
