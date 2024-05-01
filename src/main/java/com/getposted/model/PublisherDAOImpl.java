package com.getposted.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;

import com.getposted.logger.Logging;
import java.util.logging.Logger;

public class PublisherDAOImpl implements PublisherDAO {

    private static Logger logger = Logging.getLogger(PublisherDAOImpl.class.getName());

    @Override
    public Publisher get(int id) throws SQLException {
        Connection con = Database.getConnection();
        Publisher publisher = null;
        String sqlTemplate = "SELECT * FROM Publisher WHERE id =?";
        PreparedStatement select = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        select.setInt(1, id);

        try {
            rs = select.executeQuery();
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
            String firstName = rs.getString("firstName");
            String middleName = rs.getString("middleName");
            String lastName = rs.getString("lastName");
            String email = rs.getString("email");
            double hardCopyPageCommissionForAuthor = rs.getDouble("hardCopyPageCommissionForAuthor");
            String password = rs.getString("password");
            double hardCopyDiscount = rs.getDouble("hardCopyDiscount");
            double hardCopyPricePerPage = rs.getDouble("hardCopyPricePerPage");
            double softCopyCommission = rs.getDouble("softCopyCommission");
            double hardCopyPageCommission = rs.getDouble("hardCopyPageCommission");
            publisher = new Publisher(qId, address, webSite, firstName, middleName, lastName, email,
                    hardCopyPageCommissionForAuthor, password, hardCopyDiscount, hardCopyPricePerPage,
                    softCopyCommission, hardCopyPageCommission);
        }

        return publisher;
    }

    @Override
    public List<Publisher> getAll() throws SQLException {
        Connection con = Database.getConnection();
        List<Publisher> publisherList = new ArrayList<>();
        String sqlTemplate = "SELECT * FROM Publisher";
        PreparedStatement select = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        try {
            rs = select.executeQuery();
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
            String firstName = rs.getString("firstName");
            String middleName = rs.getString("middleName");
            String lastName = rs.getString("lastName");
            String email = rs.getString("email");
            double hardCopyPageCommissionForAuthor = rs.getDouble("hardCopyPageCommissionForAuthor");
            String password = rs.getString("password");
            double hardCopyDiscount = rs.getDouble("hardCopyDiscount");
            double hardCopyPricePerPage = rs.getDouble("hardCopyPricePerPage");
            double softCopyCommission = rs.getDouble("softCopyCommission");
            double hardCopyPageCommission = rs.getDouble("hardCopyPageCommission");
            publisherList.add(new Publisher(qId, address, webSite, firstName, middleName, lastName, email,
                    hardCopyPageCommissionForAuthor, password, hardCopyDiscount, hardCopyPricePerPage,
                    softCopyCommission, hardCopyPageCommission));
        }

        return publisherList;
    }

    @Override
    public int insert(Publisher publisher) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "INSERT INTO Publisher(id,address,webSite,firstName,middleName,lastName,email,hardCopyPageCommissionForAuthor,password,hardCopyDiscount,hardCopyPricePerPage,softCopyCommission,hardCopyPageCommission) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement st = con.prepareStatement(sqlTemplate);
        int result = -1;

        st.setInt(1, publisher.getId());
        st.setString(2, publisher.getAddress());
        st.setString(3, publisher.getWebSite());
        st.setString(4, publisher.getFirstName());
        st.setString(5, publisher.getMiddleName());
        st.setString(6, publisher.getLastName());
        st.setString(7, publisher.getEmail());
        st.setDouble(8, publisher.getHardCopyPageCommissionForAuthor());
        st.setString(9, publisher.getPassword());
        st.setDouble(10, publisher.getHardCopyDiscount());
        st.setDouble(11, publisher.getHardCopyPricePerPage());
        st.setDouble(12, publisher.getSoftCopyCommission());
        st.setDouble(13, publisher.getHardCopyPageCommission());

        try {
            result = st.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublisherDAOImpl class at insert() method. The exception message is %s. The inserted Publisher id is %s. The inserted Publisher address is %s. The inserted Publisher webSite is %s. The inserted Publisher firstName is %s. The inserted Publisher middleName is %s. The inserted Publisher lastName is %s. The inserted Publisher email is %s. The inserted Publisher hardCopyPageCommissionForAuthor is %s. The inserted Publisher password is %s. The inserted Publisher hardCopyDiscount is %s. The inserted Publisher hardCopyPricePerPage is %s. The inserted Publisher softCopyCommission is %s. The inserted Publisher hardCopyPageCommission is %s. ",
                    e.getMessage(), publisher.getId(), publisher.getAddress(), publisher.getWebSite(),
                    publisher.getFirstName(), publisher.getMiddleName(), publisher.getLastName(), publisher.getEmail(),
                    publisher.getHardCopyPageCommissionForAuthor(), publisher.getPassword(),
                    publisher.getHardCopyDiscount(), publisher.getHardCopyPricePerPage(),
                    publisher.getSoftCopyCommission(), publisher.getHardCopyPageCommission()));
            throw e;
        }

        return result;
    }

    @Override
    public int update(Publisher publisher) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "UPDATE Publisher SET address =?, webSite =?, firstName =?, middleName =?, lastName =?, email =?, hardCopyPageCommissionForAuthor =?, password =?, hardCopyDiscount =?, hardCopyPricePerPage =?, softCopyCommission =?, hardCopyPageCommission =? WHERE id =?";
        PreparedStatement st = con.prepareStatement(sqlTemplate);
        int result = -1;

        st.setString(1, publisher.getAddress());
        st.setString(2, publisher.getWebSite());
        st.setString(3, publisher.getFirstName());
        st.setString(4, publisher.getMiddleName());
        st.setString(5, publisher.getLastName());
        st.setString(6, publisher.getEmail());
        st.setDouble(7, publisher.getHardCopyPageCommissionForAuthor());
        st.setString(8, publisher.getPassword());
        st.setDouble(9, publisher.getHardCopyDiscount());
        st.setDouble(10, publisher.getHardCopyPricePerPage());
        st.setDouble(11, publisher.getSoftCopyCommission());
        st.setDouble(12, publisher.getHardCopyPageCommission());
        st.setInt(13, publisher.getId());

        try {
            result = st.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublisherDAOImpl class at insert() method. The exception message is %s. The inserted Publisher id is %s. The inserted Publisher address is %s. The inserted Publisher webSite is %s. The inserted Publisher firstName is %s. The inserted Publisher middleName is %s. The inserted Publisher lastName is %s. The inserted Publisher email is %s. The inserted Publisher hardCopyPageCommissionForAuthor is %s. The inserted Publisher password is %s. The inserted Publisher hardCopyDiscount is %s. The inserted Publisher hardCopyPricePerPage is %s. The inserted Publisher softCopyCommission is %s. The inserted Publisher hardCopyPageCommission is %s. ",
                    e.getMessage(), publisher.getId(), publisher.getAddress(), publisher.getWebSite(),
                    publisher.getFirstName(), publisher.getMiddleName(), publisher.getLastName(), publisher.getEmail(),
                    publisher.getHardCopyPageCommissionForAuthor(), publisher.getPassword(),
                    publisher.getHardCopyDiscount(), publisher.getHardCopyPricePerPage(),
                    publisher.getSoftCopyCommission(), publisher.getHardCopyPageCommission()));
            throw e;
        }
        return result;
    }

    @Override
    public int delete(Publisher publisher) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "DELETE FROM Publisher WHERE id =?";
        PreparedStatement st = con.prepareStatement(sqlTemplate);
        int result = -1;
        st.setInt(1, publisher.getId());

        try {
            result = st.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PublisherDAOImpl class at delete() method. The exception message is %s. The inserted Publisher id is %s. The inserted Publisher address is %s. The inserted Publisher webSite is %s. The inserted Publisher firstName is %s. The inserted Publisher middleName is %s. The inserted Publisher lastName is %s. The inserted Publisher email is %s. The inserted Publisher hardCopyPageCommissionForAuthor is %s. The inserted Publisher password is %s. The inserted Publisher hardCopyDiscount is %s. The inserted Publisher hardCopyPricePerPage is %s. The inserted Publisher softCopyCommission is %s. The inserted Publisher hardCopyPageCommission is %s. ",
                    e.getMessage(), publisher.getId(), publisher.getAddress(), publisher.getWebSite(),
                    publisher.getFirstName(), publisher.getMiddleName(), publisher.getLastName(), publisher.getEmail(),
                    publisher.getHardCopyPageCommissionForAuthor(), publisher.getPassword(),
                    publisher.getHardCopyDiscount(), publisher.getHardCopyPricePerPage(),
                    publisher.getSoftCopyCommission(), publisher.getHardCopyPageCommission()));
            throw e;
        }
        return result;
    }
}