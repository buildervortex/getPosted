package com.getposted.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.getposted.logger.Logging;
import java.util.logging.Logger;

public class PublisherDAOImpl implements PublisherDAO {

    private static Logger logger = Logging.getLogger(PublisherDAOImpl.class.getName());

    @Override
    public Publisher get(int id) throws SQLException {
        Connection con = Database.getConnection();
        Publisher publisher = null;
        String sqlTemplate = "SELECT * FROM Publisher WHERE id =?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, id);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublisherDAOImpl class at getId() method the id is %d. The exception message is %s",
                    id, e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            int qId = rs.getInt("id");
            String address = rs.getString("address");
            String webSite = rs.getString("webSite");
            String name = rs.getString("name");
            String email = rs.getString("email");
            double hardCopyPageCommissionForAuthor = rs.getDouble("hardCopyPageCommissionForAuthor");
            String salt = rs.getString("salt");
            String password = rs.getString("password");
            double hardCopyDiscount = rs.getDouble("hardCopyDiscount");
            String pepper = rs.getString("pepper");
            double hardCopyPricePerPage = rs.getDouble("hardCopyPricePerPage");
            double softCopyCommission = rs.getDouble("softCopyCommission");
            double hardCopyCommission = rs.getDouble("hardCopyCommission");
            publisher = new Publisher(qId, address, webSite, name, email,
                    hardCopyPageCommissionForAuthor, salt, password, hardCopyDiscount, pepper, hardCopyPricePerPage,
                    softCopyCommission, hardCopyCommission);
        }
        con.close();
        return publisher;
    }

    @Override
    public List<Publisher> getAll() throws SQLException {
        Connection con = Database.getConnection();
        List<Publisher> publisherList = new ArrayList<>();
        String sqlTemplate = "SELECT * FROM Publisher";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublisherDAOImpl class at getAll().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int qId = rs.getInt("id");
            String address = rs.getString("address");
            String webSite = rs.getString("webSite");
            String name = rs.getString("name");
            String email = rs.getString("email");
            double hardCopyPageCommissionForAuthor = rs.getDouble("hardCopyPageCommissionForAuthor");
            String salt = rs.getString("salt");
            String password = rs.getString("password");
            double hardCopyDiscount = rs.getDouble("hardCopyDiscount");
            String pepper = rs.getString("pepper");
            double hardCopyPricePerPage = rs.getDouble("hardCopyPricePerPage");
            double softCopyCommission = rs.getDouble("softCopyCommission");
            double hardCopyCommission = rs.getDouble("hardCopyCommission");
            publisherList.add(new Publisher(qId, address, webSite, name, email,
                    hardCopyPageCommissionForAuthor, salt, password, hardCopyDiscount, pepper, hardCopyPricePerPage,
                    softCopyCommission, hardCopyCommission));
        }
        con.close();
        return publisherList;
    }

    @Override
    public int insert(Publisher publisher) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "INSERT INTO Publisher(id, address, webSite, name, email, hardCopyPageCommissionForAuthor, salt, password, hardCopyDiscount, pepper, hardCopyPricePerPage, softCopyCommission, hardCopyCommission) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = -1;

        ps.setInt(1, publisher.getId());
        ps.setString(2, publisher.getAddress());
        ps.setString(3, publisher.getWebSite());
        ps.setString(4, publisher.getName());
        ps.setString(5, publisher.getEmail());
        ps.setDouble(6, publisher.getHardCopyPageCommissionForAuthor());
        ps.setString(7, publisher.getSalt());
        ps.setString(8, publisher.getPassword());
        ps.setDouble(9, publisher.getHardCopyDiscount());
        ps.setString(10, publisher.getPepper());
        ps.setDouble(11, publisher.getHardCopyPricePerPage());
        ps.setDouble(12, publisher.getSoftCopyCommission());
        ps.setDouble(13, publisher.getHardCopyCommission());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublisherDAOImpl class at insert() method. The exception message is %s. The inserted Publisher id is %s. The inserted Publisher address is %s. The inserted Publisher webSite is %s. The inserted Publisher name is %s. The inserted Publisher email is %s. The inserted Publisher hardCopyPageCommissionForAuthor is %s. The inserted Publisher salt is %s. The inserted Publisher password is %s. The inserted Publisher hardCopyDiscount is %s. The inserted publisher pepper is %s. The inserted Publisher hardCopyPricePerPage is %s. The inserted Publisher softCopyCommission is %s. The inserted Publisher hardCopyCommission is %s. ",
                    e.getMessage(), publisher.getId(), publisher.getAddress(), publisher.getWebSite(),
                    publisher.getName(), publisher.getEmail(),
                    publisher.getHardCopyPageCommissionForAuthor(), publisher.getSalt(), publisher.getPassword(),
                    publisher.getHardCopyDiscount(), publisher.getPepper(), publisher.getHardCopyPricePerPage(),
                    publisher.getSoftCopyCommission(), publisher.getHardCopyCommission()));
            throw e;
        }
        con.close();
        return rowsAffected;
    }

    @Override
    public int update(Publisher publisher) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "UPDATE Publisher SET address =?, webSite =?, name =?, email =?, hardCopyPageCommissionForAuthor =?, salt =?, password =?, hardCopyDiscount =?, pepper =?, hardCopyPricePerPage =?, softCopyCommission =?, hardCopyCommission =? WHERE id =?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = -1;

        ps.setString(1, publisher.getAddress());
        ps.setString(2, publisher.getWebSite());
        ps.setString(3, publisher.getName());
        ps.setString(4, publisher.getEmail());
        ps.setDouble(5, publisher.getHardCopyPageCommissionForAuthor());
        ps.setString(6, publisher.getSalt());
        ps.setString(7, publisher.getPassword());
        ps.setDouble(8, publisher.getHardCopyDiscount());
        ps.setString(9, publisher.getPepper());
        ps.setDouble(10, publisher.getHardCopyPricePerPage());
        ps.setDouble(11, publisher.getSoftCopyCommission());
        ps.setDouble(12, publisher.getHardCopyCommission());
        ps.setInt(13, publisher.getId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublisherDAOImpl class at update() method. The exception message is %s. The inserted Publisher id is %s. The inserted Publisher address is %s. The inserted Publisher webSite is %s. The inserted Publisher name is %s. The inserted Publisher email is %s. The inserted Publisher hardCopyPageCommissionForAuthor is %s. The inserted Publisher salt is %s. The inserted Publisher password is %s. The inserted Publisher hardCopyDiscount is %s. The inserted publisher pepper is %s. The inserted Publisher hardCopyPricePerPage is %s. The inserted Publisher softCopyCommission is %s. The inserted Publisher hardCopyCommission is %s. ",
                    e.getMessage(), publisher.getId(), publisher.getAddress(), publisher.getWebSite(),
                    publisher.getName(), publisher.getEmail(),
                    publisher.getHardCopyPageCommissionForAuthor(), publisher.getSalt(), publisher.getPassword(),
                    publisher.getHardCopyDiscount(), publisher.getPepper(), publisher.getHardCopyPricePerPage(),
                    publisher.getSoftCopyCommission(), publisher.getHardCopyCommission()));
            throw e;
        }
        con.close();
        return rowsAffected;
    }

    @Override
    public int delete(Publisher publisher) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "DELETE FROM Publisher WHERE id =?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = -1;
        ps.setInt(1, publisher.getId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublisherDAOImpl class at delete() method. The exception message is %s. The inserted Publisher id is %s. The inserted Publisher address is %s. The inserted Publisher webSite is %s. The inserted Publisher name is %s. The inserted Publisher email is %s. The inserted Publisher hardCopyPageCommissionForAuthor is %s. The inserted Publisher salt is %s. The inserted Publisher password is %s. The inserted Publisher hardCopyDiscount is %s. The inserted publisher pepper is %s. The inserted Publisher hardCopyPricePerPage is %s. The inserted Publisher softCopyCommission is %s. The inserted Publisher hardCopyCommission is %s. ",
                    e.getMessage(), publisher.getId(), publisher.getAddress(), publisher.getWebSite(),
                    publisher.getName(), publisher.getEmail(),
                    publisher.getHardCopyPageCommissionForAuthor(), publisher.getSalt(), publisher.getPassword(),
                    publisher.getHardCopyDiscount(), publisher.getPepper(), publisher.getHardCopyPricePerPage(),
                    publisher.getSoftCopyCommission(), publisher.getHardCopyCommission()));
            throw e;
        }
        con.close();
        return rowsAffected;
    }
}