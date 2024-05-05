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

        return rowsAffected;
    }

}
