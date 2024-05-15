package com.getposted.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.getposted.logger.Logging;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PhoneNumberDAOImpl implements PhoneNumberDAO {

    private static Logger logger = Logging.getLogger(PhoneNumberDAO.class.getName());

    @Override
    public PhoneNumber get(int inquiryId) throws SQLException {
        PhoneNumber phoneNumber = null;
        return phoneNumber;
    }

    @Override
    public List<PhoneNumber> getAll() throws SQLException {
        Connection con = Database.getConnection();
        List<PhoneNumber> phoneNumberList = new ArrayList();
        String sqlTemplate = "SELECT * FROM PhoneNumber ORDER BY phoneNumber";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PhoneNumberDAOImpl class at getAll() .The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            String qphoneNumber = rs.getString("phoneNumber");
            int inquiryId = rs.getInt("inquiryId");
            phoneNumberList.add(new PhoneNumber(qphoneNumber, inquiryId));
        }
        con.close();
        return phoneNumberList;
    }

    @Override
    public int insert(PhoneNumber phoneNumber) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "INSERT INTO PhoneNumber (phoneNumber, inquiryId) VALUES (?,?)";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = -1;

        try {
            ps.setString(1, phoneNumber.getPhoneNumber());
            ps.setInt(2, phoneNumber.getInquiryId());
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PhoneNumberDAOImpl class at insert() .The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        con.close();
        return rowsAffected;
    }

    @Override
    public int update(PhoneNumber phoneNumber) throws SQLException {
        int rowsAffected = -1;
        return rowsAffected;
    }

    @Override
    public int delete(PhoneNumber phoneNumber) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "DELETE FROM PhoneNumber WHERE inquiryId = ? AND phoneNumber = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = -1;

        ps.setInt(1, phoneNumber.getInquiryId());
        ps.setString(2, phoneNumber.getPhoneNumber());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PhoneNumberDAOImpl class at delete() .The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        con.close();
        return rowsAffected;
    }

    @Override
    public List<PhoneNumber> getList(int inquiryId) throws SQLException {
        Connection con = Database.getConnection();
        List<PhoneNumber> phoneNumberList = new ArrayList();
        String sqlTemplate = "SELECT * FROM PhoneNumber WHERE inquiryId = ? ORDER BY phoneNumber";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, inquiryId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.PhoneNumberDAOImpl class at getList() .The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            String phoneNumber = rs.getString("phoneNumber");
            int qinquiryId = rs.getInt("inquiryId");
            phoneNumberList.add(new PhoneNumber(phoneNumber, qinquiryId));
        }
        con.close();
        return phoneNumberList;
    }

}
