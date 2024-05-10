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

public class PurchaseDAOImpl implements PurchaseDAO {

    private static Logger logger = Logging.getLogger(PurchaseDAOImpl.class.getName());

    @Override
    public Purchase get(int id) throws SQLException {
        Connection con = Database.getConnection();
        Purchase purchase = null;
        String sqlTemplate = "SELECT * FROM Purchase WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, id);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getId() method the id is %d. The exception message is %s",
                    id, e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            int qid = rs.getInt("id");
            Date purchasedDate = rs.getDate("purchasedDate");
            double price = rs.getDouble("price");
            Time purchasedTime = rs.getTime("purchasedTime");
            int userId = rs.getInt("userId");
            int publisherId = rs.getInt("publisherId");
            int publicationId = rs.getInt("publicationId");

            purchase = new Purchase(qid, purchasedDate, price, purchasedTime, userId, publisherId, publicationId);
        }
        con.close();
        return purchase;
    }

    @Override
    public List<Purchase> getAll() throws SQLException {
        Connection con = Database.getConnection();
        List<Purchase> purchases = new ArrayList<Purchase>();
        String sqlTemplate = "SELECT * FROM Purchase ";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getAll() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int id = rs.getInt("id");
            Date purchasedDate = rs.getDate("purchasedDate");
            double price = rs.getDouble("price");
            Time purchasedTime = rs.getTime("purchasedTime");
            int userId = rs.getInt("userId");
            int publisherId = rs.getInt("publisherId");
            int publicationId = rs.getInt("publicationId");

            purchases.add(new Purchase(id, purchasedDate, price, purchasedTime, userId, publisherId, publicationId));
        }
        con.close();
        return purchases;
    }

    @Override
    public int insert(Purchase purchase) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "INSERT INTO Purchase(id, purchasedDate, price, purchasedTime, userId, publisherId, publicationId) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = -1;

        ps.setInt(1, purchase.getId());
        ps.setDate(2, purchase.getPurchasedDate());
        ps.setDouble(3, purchase.getPrice());
        ps.setTime(4, purchase.getPurchasedTime());
        ps.setInt(5, purchase.getUserId());
        ps.setInt(6, purchase.getPublisherId());
        ps.setInt(7, purchase.getPublicationId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at insert() method . The exception message is %s. The id is %s. The purchasedDate is %s. the price is %s. the purchasedTime is %s. The userId is %s. The publisherId is %s. The publicationId    is %s",
                    e.getMessage(), purchase.getId(), purchase.getPurchasedDate().toString(), purchase.getPrice(),
                    purchase.getPurchasedTime().toString(), purchase.getUserId(), purchase.getPublisherId(),
                    purchase.getPublicationId()));
            throw e;
        }
        con.close();
        return rowsAffected;
    }

    @Override
    public int update(Purchase purchase) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "UPDATE Purchase SET purchasedDate = ?, price = ?, purchasedTime = ?, userId = ?, publisherId = ?, publicationId = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = -1;

        ps.setDate(1, purchase.getPurchasedDate());
        ps.setDouble(2, purchase.getPrice());
        ps.setTime(3, purchase.getPurchasedTime());
        ps.setInt(4, purchase.getUserId());
        ps.setInt(5, purchase.getPublisherId());
        ps.setInt(6, purchase.getPublicationId());
        ps.setInt(7, purchase.getId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at update() method . The exception message is %s. The id is %s. The purchasedDate is %s. the price is %s. the purchasedTime is %s. The userId is %s. The publisherId is %s. The publicationId    is %s",
                    e.getMessage(), purchase.getId(), purchase.getPurchasedDate().toString(), purchase.getPrice(),
                    purchase.getPurchasedTime().toString(), purchase.getUserId(), purchase.getPublisherId(),
                    purchase.getPublicationId()));
            throw e;
        }
        con.close();
        return rowsAffected;
    }

    @Override
    public int delete(Purchase purchase) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "DELETE FROM Purchase WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = -1;

        ps.setInt(1, purchase.getId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at delete() method . The exception message is %s. The id is %s. The purchasedDate is %s. the price is %s. the purchasedTime is %s. The userId is %s. The publisherId is %s. The publicationId    is %s",
                    e.getMessage(), purchase.getId(), purchase.getPurchasedDate().toString(), purchase.getPrice(),
                    purchase.getPurchasedTime().toString(), purchase.getUserId(), purchase.getPublisherId(),
                    purchase.getPublicationId()));
            throw e;
        }
        con.close();
        return rowsAffected;
    }

    @Override
    public int getAllPurchasesCount(int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        int count = 0;
        String sqlTemplate = "SELECT COUNT(id) AS count FROM Purchase WHERE publisherId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getAllPurchasesCount() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getInt("count");
        }
        con.close();
        return count;
    }

    @Override
    public double getSumOfAllSoftCopyPurchasesPrices(int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        double sum = 0;
        String sqlTemplate = "SELECT SUM(price) AS sum FROM Purchase WHERE publisherId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getSumOfAllSoftCopyPurchasesPrices() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            sum = rs.getDouble("sum");
        }
        con.close();
        return sum;
    }

    @Override
    public int getTotalUserCountWhoPurchasedSoftCopies(int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        int count = 0;
        String sqlTemplate = "SELECT COUNT(userId) AS count FROM (SELECT userId FROM Purchase WHERE publisherId = ? GROUP BY userId) AS table1";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getTotalUserCountWhoPurchasedSoftCopies() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getInt("count");
        }
        con.close();
        return count;
    }

    @Override
    public List<Purchase> getAllOrderedPurchasesInAPublisher(int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        List<Purchase> purchases = new ArrayList<Purchase>();
        String sqlTemplate = "SELECT * FROM Purchase WHERE publisherId = ? ORDER BY purchasedDate DESC, purchasedTime DESC";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getAllOrderedPurchasesInAPublisher() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int id = rs.getInt("id");
            Date purchasedDate = rs.getDate("purchasedDate");
            double price = rs.getDouble("price");
            Time purchasedTime = rs.getTime("purchasedTime");
            int userId = rs.getInt("userId");
            int qpublisherId = rs.getInt("publisherId");
            int publicationId = rs.getInt("publicationId");

            purchases.add(new Purchase(id, purchasedDate, price, purchasedTime, userId, qpublisherId, publicationId));
        }
        con.close();
        return purchases;
    }

    @Override
    public List<Purchase> getAllPurchasesOrderdByPrice(boolean desc, int publisherId) throws SQLException {
        String order = desc ? "DESC" : "ASC";
        Connection con = Database.getConnection();
        List<Purchase> purchases = new ArrayList<Purchase>();
        String sqlTemplate = String.format(
                "SELECT * FROM Purchase WHERE publisherId = ? ORDER BY purchasedDate %s, purchasedTime DESC", order);
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getAllOrderedPurchasesInAPublisher() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int id = rs.getInt("id");
            Date purchasedDate = rs.getDate("purchasedDate");
            double price = rs.getDouble("price");
            Time purchasedTime = rs.getTime("purchasedTime");
            int userId = rs.getInt("userId");
            int qpublisherId = rs.getInt("publisherId");
            int publicationId = rs.getInt("publicationId");

            purchases.add(new Purchase(id, purchasedDate, price, purchasedTime, userId, qpublisherId, publicationId));
        }
        con.close();
        return purchases;
    }

    @Override
    public List<Purchase> getAllPurchasesInSpecifiedDate(Date date, int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        List<Purchase> purchases = new ArrayList<Purchase>();
        String sqlTemplate = "SELECT * FROM Purchase WHERE publisherId = ? AND purchasedDate = ? ORDER BY purchasedTime DESC";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setDate(2, date);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getAllPurchasesInSpecifiedDate() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int id = rs.getInt("id");
            Date purchasedDate = rs.getDate("purchasedDate");
            double price = rs.getDouble("price");
            Time purchasedTime = rs.getTime("purchasedTime");
            int userId = rs.getInt("userId");
            int qpublisherId = rs.getInt("publisherId");
            int publicationId = rs.getInt("publicationId");

            purchases.add(new Purchase(id, purchasedDate, price, purchasedTime, userId, qpublisherId, publicationId));
        }
        con.close();
        return purchases;
    }

    @Override
    public List<Purchase> getLIstOfPurchasesInSpecifiedDate(Date date, int limit, int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        List<Purchase> purchases = new ArrayList<Purchase>();
        String sqlTemplate = "SELECT * FROM Purchase WHERE publisherId = ? AND purchasedDate = ? ORDER BY purchasedTime DESC LIMIT 1";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setDate(2, date);
        ps.setInt(3, limit);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getLIstOfPurchasesInSpecifiedDate() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int id = rs.getInt("id");
            Date purchasedDate = rs.getDate("purchasedDate");
            double price = rs.getDouble("price");
            Time purchasedTime = rs.getTime("purchasedTime");
            int userId = rs.getInt("userId");
            int qpublisherId = rs.getInt("publisherId");
            int publicationId = rs.getInt("publicationId");

            purchases.add(new Purchase(id, purchasedDate, price, purchasedTime, userId, qpublisherId, publicationId));
        }
        con.close();
        return purchases;
    }

    @Override
    public List<Purchase> getAllPurchasesInSpecifiedDateRange(Date startDate, Date endDate, int publisherId)
            throws SQLException {
        Connection con = Database.getConnection();
        List<Purchase> purchases = new ArrayList<Purchase>();
        String sqlTemplate = "SELECT * FROM Purchase WHERE publisherId = ? AND purchasedDate BETWEEN ? AND ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setDate(2, startDate);
        ps.setDate(4 - 1, endDate);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getAllPurchasesInSpecifiedDateRange() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int id = rs.getInt("id");
            Date purchasedDate = rs.getDate("purchasedDate");
            double price = rs.getDouble("price");
            Time purchasedTime = rs.getTime("purchasedTime");
            int userId = rs.getInt("userId");
            int qpublisherId = rs.getInt("publisherId");
            int publicationId = rs.getInt("publicationId");

            purchases.add(new Purchase(id, purchasedDate, price, purchasedTime, userId, qpublisherId, publicationId));
        }
        con.close();
        return purchases;
    }

    @Override
    public List<Purchase> getAllPurchasesInSpecifiedPriceRange(double startingPrice, double endPrice, int publisherId)
            throws SQLException {
        Connection con = Database.getConnection();
        List<Purchase> purchases = new ArrayList<Purchase>();
        String sqlTemplate = "SELECT * FROM Purchase WHERE publisherId = ? AND price BETWEEN ? AND ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setDouble(2, startingPrice);
        ps.setDouble(3, endPrice);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getAllPurchasesInSpecifiedPriceRange() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int id = rs.getInt("id");
            Date purchasedDate = rs.getDate("purchasedDate");
            double price = rs.getDouble("price");
            Time purchasedTime = rs.getTime("purchasedTime");
            int userId = rs.getInt("userId");
            int qpublisherId = rs.getInt("publisherId");
            int publicationId = rs.getInt("publicationId");

            purchases.add(new Purchase(id, purchasedDate, price, purchasedTime, userId, qpublisherId, publicationId));
        }
        con.close();
        return purchases;
    }

    @Override
    public List<Purchase> getAllPurchasesOfAUser(int userId, int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        List<Purchase> purchases = new ArrayList<Purchase>();
        String sqlTemplate = "SELECT * FROM Purchase WHERE publisherId = ? AND userId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, userId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getAllPurchasesOfAUser() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int id = rs.getInt("id");
            Date purchasedDate = rs.getDate("purchasedDate");
            double price = rs.getDouble("price");
            Time purchasedTime = rs.getTime("purchasedTime");
            int quserId = rs.getInt("userId");
            int qpublisherId = rs.getInt("publisherId");
            int publicationId = rs.getInt("publicationId");

            purchases.add(new Purchase(id, purchasedDate, price, purchasedTime, quserId, qpublisherId, publicationId));
        }
        con.close();
        return purchases;
    }

    @Override
    public int getAUserPurchasesCount(int userId, int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        int count = 0;
        String sqlTemplate = "SELECT COUNT(id) AS count FROM Purchase WHERE publisherId = ? AND userId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, userId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getAUserPurchasesCount() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getInt("count");
        }
        con.close();
        return count;
    }

    @Override
    public List<Purchase> getAllPurchasesOfAPublicationId(int publicationId, int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        List<Purchase> purchases = new ArrayList<Purchase>();
        String sqlTemplate = "SELECT * FROM Purchase WHERE publisherId = ? AND publicationId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, publicationId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getAllPurchasesOfAPublicationId() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int id = rs.getInt("id");
            Date purchasedDate = rs.getDate("purchasedDate");
            double price = rs.getDouble("price");
            Time purchasedTime = rs.getTime("purchasedTime");
            int quserId = rs.getInt("userId");
            int qpublisherId = rs.getInt("publisherId");
            int qpublicationId = rs.getInt("publicationId");

            purchases.add(new Purchase(id, purchasedDate, price, purchasedTime, quserId, qpublisherId, qpublicationId));
        }
        con.close();
        return purchases;
    }

    @Override
    public int getAPublicationPurchasesCount(int publicationId, int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        int count = 0;
        String sqlTemplate = "SELECT COUNT(id) AS count FROM Purchase WHERE publisherId = ? AND publicationId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, publicationId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getAPublicationPurchasesCount() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            count = rs.getInt("count");
        }
        con.close();
        return count;
    }

    @Override
    public double getSumOfAllPurchasesPricesOfAUser(int userId, int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        double sum = 0;
        String sqlTemplate = "SELECT SUM(price) AS sum FROM Purchase WHERE publisherId = ? AND userId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, userId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getSumOfAllPurchasesPricesOfAUser() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            sum = rs.getDouble("sum");
        }
        con.close();
        return sum;
    }

    @Override
    public double getSumOfAllPurchasesPricesOfAPulication(int publicationId, int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        double sum = 0;
        String sqlTemplate = "SELECT SUM(price) AS sum FROM Purchase WHERE publisherId = ? AND publicationId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, publicationId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getSumOfAllPurchasesPricesOfAPulication() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            sum = rs.getDouble("sum");
        }
        con.close();
        return sum;
    }

    @Override
    public List<Integer> getUserPurchasedPublicationsIds(int userId, int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        List<Integer> publicationsIds = new ArrayList<Integer>();
        String sqlTemplate = "SELECT publicationId FROM Purchase WHERE publisherId = ? AND userId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, userId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getUserPurchasedPublicationsIds() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            publicationsIds.add(rs.getInt("publicationId"));
        }
        con.close();
        return publicationsIds;
    }

    @Override
    public List<Integer> getPublicationPurchasedUserIds(int publicationId, int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        List<Integer> userIds = new ArrayList<Integer>();
        String sqlTemplate = "SELECT userId FROM Purchase WHERE publisherId = ? AND publicationId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, publicationId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getPublicationPurchasedUserIds() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            userIds.add(rs.getInt("userId"));
        }
        con.close();
        return userIds;
    }

    @Override
    public List<Integer> getAllPulicationIdsOrderdInTopSelling(int publisherId, boolean desc) throws SQLException {
        String order = desc ? "DESC" : "ASC";
        Connection con = Database.getConnection();
        List<Integer> publicationIds = new ArrayList<Integer>();
        String sqlTemplate = String.format(
                "SELECT publicationId FROM (SELECT COUNT(id) AS counts,publicationId FROM Purchase WHERE publisherId = ? GROUP BY publicationId ORDER BY counts %s) AS table1",
                order);
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getAllPulicationIdsOrderdInTopSelling() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            publicationIds.add(rs.getInt("publicationId"));
        }
        con.close();
        return publicationIds;
    }

    @Override
    public List<Integer> getListOfPulicationIdsOrderdInTopSelling(int publisherId, boolean desc, int limit)
            throws SQLException {
        String order = desc ? "DESC" : "ASC";
        Connection con = Database.getConnection();
        List<Integer> publicationIds = new ArrayList<Integer>();
        String sqlTemplate = String.format(
                "SELECT publicationId FROM (SELECT COUNT(id) AS counts,publicationId FROM Purchase WHERE publisherId = ? GROUP BY publicationId ORDER BY counts %s) AS table1 LIMIT ?",
                order);
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, limit);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getListOfPulicationIdsOrderdInTopSelling() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            publicationIds.add(rs.getInt("publicationId"));
        }
        con.close();
        return publicationIds;
    }

    @Override
    public double getSumOfAllDiscountsGivenToUsersByAllAuthors(int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        double sum = 0;
        String sqlTemplate = "SELECT SUM(PU.softCopyDiscount) AS sum FROM Purchase AS P LEFT JOIN Publication AS PU ON P.publicationId = PU.id WHERE publisherId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getSumOfAllDiscountsGivenToUsersByAllAuthors() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            sum = rs.getDouble("sum");
        }
        con.close();
        return sum;
    }

    @Override
    public double getSumOfAllDiscountsGivenToUsersByAnAuthor(int authorId, int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        double sum = 0;
        String sqlTemplate = "SELECT SUM(PU.softCopyDiscount) AS sum FROM Purchase AS P LEFT JOIN Publication AS PU ON P.publicationId = PU.id WHERE publisherId = ? AND authorId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, authorId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getSumOfAllDiscountsGivenToUsersByAnAuthor() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            sum = rs.getDouble("sum");
        }
        con.close();
        return sum;
    }

    @Override
    public double getTotalDiscountsGotByAnUser(int userId, int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        double sum = 0;
        String sqlTemplate = "SELECT SUM(PU.softCopyDiscount) AS sum FROM Purchase AS P LEFT JOIN Publication AS PU ON P.publicationId = PU.id WHERE publisherId = ? AND userId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, userId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getSumOfAllDiscountsGivenToUsersByAnAuthor() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            sum = rs.getDouble("sum");
        }
        con.close();
        return sum;
    }

    @Override
    public double getRecivedSoftCopyCommissionFromAllPurchases(int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        double sum = 0;
        String sqlTemplate = "SELECT SUM(softCopyCommission) AS sum FROM Purchase AS PU LEFT JOIN Publisher  AS P ON PU.publisherId = P.id WHERE PU.publisherId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getRecivedSoftCopyCommissionFromAllPurchases() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            sum = rs.getDouble("sum");
        }
        con.close();
        return sum;
    }

    @Override
    public double getRecivedSoftCopyCommissionFromAPublication(int publicationId, int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        double sum = 0;
        String sqlTemplate = "SELECT SUM(softCopyCommission) AS sum FROM Purchase AS PU LEFT JOIN Publisher  AS P ON PU.publisherId = P.id WHERE PU.publicationId = ? AND publisherId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publicationId);
        ps.setInt(2, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getRecivedSoftCopyCommissionFromAPublication() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            sum = rs.getDouble("sum");
        }
        con.close();
        return sum;
    }

    @Override
    public double getRecivedSoftCopyCommissionFromAUser(int userId, int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        double sum = 0;
        String sqlTemplate = "SELECT SUM(softCopyCommission) AS sum FROM Purchase AS PU LEFT JOIN Publisher  AS P ON PU.publisherId = P.id WHERE publisherId = ? AND userId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, userId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getRecivedSoftCopyCommissionFromAUser() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            sum = rs.getDouble("sum");
        }
        con.close();
        return sum;
    }

    @Override
    public double getRecivedSoftCopyCommissionFromAnAuthor(int authorId, int publisherId) throws SQLException {
        Connection con = Database.getConnection();
        double sum = 0;
        String sqlTemplate = "SELECT SUM(softCopyCommission) AS sum FROM Purchase AS PU LEFT JOIN Publisher  AS P ON PU.publisherId =P.id LEFT JOIN Publication AS Pb ON PU.publicationId = Pb.id LEFT JOIN Author AS A ON A.id = Pb.authorId = P.id WHERE publisherId = ? AND authorId = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, authorId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getRecivedSoftCopyCommissionFromAnAuthor() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            sum = rs.getDouble("sum");
        }
        con.close();
        return sum;
    }

    @Override
    public List<Integer> getAllAuthorIdsOrderdInTopSelling(int publisherId, boolean desc) throws SQLException {
        String order = desc ? "DESC" : "ASC";
        Connection con = Database.getConnection();
        List<Integer> publicationIds = new ArrayList<Integer>();
        String sqlTemplate = String.format(
                "SELECT authorId FROM (SELECT COUNT(PU.authorId) AS authorCount,PU.authorId FROM Purchase AS P LEFT JOIN Publication AS PU ON PU.id = P.publicationId LEFT JOIN Author AS A ON A.id = PU.authorId WHERE publisherId = ? GROUP BY PU.authorId ORDER BY authorCount %s) AS table1",
                order);
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getAllAuthorIdsOrderdInTopSelling() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            publicationIds.add(rs.getInt("authorId"));
        }
        con.close();
        return publicationIds;
    }

    @Override
    public List<Integer> getListOfAuthorIdsOrderdInTopSelling(int publisherId, boolean desc, int limit)
            throws SQLException {
        String order = desc ? "DESC" : "ASC";
        Connection con = Database.getConnection();
        List<Integer> publicationIds = new ArrayList<Integer>();
        String sqlTemplate = String.format(
                "SELECT authorId FROM (SELECT COUNT(PU.authorId) AS authorCount,PU.authorId FROM Purchase AS P LEFT JOIN Publication AS PU ON PU.id = P.publicationId LEFT JOIN Author AS A ON A.id = PU.authorId WHERE publisherId = ? GROUP BY PU.authorId ORDER BY authorCount %s) AS table1 LIMIT ?",
                order);
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, publisherId);
        ps.setInt(2, limit);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getListOfAuthorIdsOrderdInTopSelling() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            publicationIds.add(rs.getInt("authorId"));
        }
        con.close();
        return publicationIds;
    }

    @Override
    public List<Integer> getAllCategoryIdsOrderdInSelling(boolean desc) throws SQLException {
        String order = desc ? "DESC" : "ASC";
        Connection con = Database.getConnection();
        List<Integer> categoryIds = new ArrayList<Integer>();
        String sqlTemplate = String.format(
                "SELECT C.id AS id FROM Purchase AS P LEFT JOIN Publication AS PU ON P.publicationId = PU.id RIGHT JOIN Category AS C ON C.id = PU.categoryId GROUP BY C.id ORDER BY COUNT(C.id) %s",
                order);
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getAllCategoryIdsOrderdInSelling() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            categoryIds.add(rs.getInt("id"));
        }
        con.close();
        return categoryIds;
    }

    @Override
    public List<Integer> getListOfCategoryIdsOrderedInSelling(boolean desc, int limit) throws SQLException {
        String order = desc ? "DESC" : "ASC";
        Connection con = Database.getConnection();
        List<Integer> categoryIds = new ArrayList<Integer>();
        String sqlTemplate = String.format(
                "SELECT C.id AS id FROM Purchase AS P LEFT JOIN Publication AS PU ON P.publicationId = PU.id RIGHT JOIN Category AS C ON C.id = PU.categoryId GROUP BY C.id ORDER BY COUNT(C.id) %s LIMIT ?",
                order);
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, limit);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getListOfCategoryIdsOrderedInSelling() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            categoryIds.add(rs.getInt("id"));
        }
        con.close();
        return categoryIds;
    }

    @Override
    public List<Integer> getAllAuthorIdsOrderedInSelling(boolean desc) throws SQLException {
        String order = desc ? "DESC" : "ASC";
        Connection con = Database.getConnection();
        List<Integer> authorIds = new ArrayList<Integer>();
        String sqlTemplate = String.format(
                "SELECT A.id FROM Purchase AS P LEFT JOIN Publication AS PU ON PU.id = P.publicationId RIGHT JOIN Author AS A ON A.id = PU.authorId GROUP BY A.id ORDER BY COUNT(A.id) %s",
                order);
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getAllAuthorIdsOrderedInSelling() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            authorIds.add(rs.getInt("id"));
        }
        con.close();
        return authorIds;
    }

    @Override
    public List<Integer> getListOfAuthorIdsOrderedInSelling(boolean desc, int limit) throws SQLException {
        String order = desc ? "DESC" : "ASC";
        Connection con = Database.getConnection();
        List<Integer> authorIds = new ArrayList<Integer>();
        String sqlTemplate = String.format(
                "SELECT A.id FROM Purchase AS P LEFT JOIN Publication AS PU ON PU.id = P.publicationId RIGHT JOIN Author AS A ON A.id = PU.authorId GROUP BY A.id ORDER BY COUNT(A.id) %s LIMIT ?",
                order);
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1,limit);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getListOfAuthorIdsOrderedInSelling() method . The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            authorIds.add(rs.getInt("id"));
        }
        con.close();
        return authorIds;
    }

    @Override
    public List<Integer> getAllPublicationIdsOrderedInSelling(boolean desc) throws SQLException {
        String order = desc ? "DESC" : "ASC";
        Connection con = Database.getConnection();
        List<Integer> publicationIds = new ArrayList<Integer>();
        String sqlTemplate = String.format(
                "SELECT PU.id FROM Purchase AS P RIGHT JOIN Publication AS PU ON P.publicationId = PU.id GROUP BY PU.id ORDER BY COUNT(PU.id) %s",
                order);
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getAllPublicationIdsOrderedInSelling() method . The exception message is %s",
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
    public List<Integer> getListOfPublicationIdsOrderedInSelling(boolean desc, int limit) throws SQLException {
        String order = desc ? "DESC" : "ASC";
        Connection con = Database.getConnection();
        List<Integer> publicationIds = new ArrayList<Integer>();
        String sqlTemplate = String.format(
                "SELECT PU.id FROM Purchase AS P RIGHT JOIN Publication AS PU ON P.publicationId = PU.id GROUP BY PU.id ORDER BY COUNT(PU.id) %s LIMIT ?",
                order);
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1,limit);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PurchaseDAOImpl class at getListOfPublicationIdsOrderedInSelling() method . The exception message is %s",
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
