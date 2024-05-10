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

public class UserDAOImpl implements UserDAO {

    private static Logger logger = Logging.getLogger(UserDAOImpl.class.getName());

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

    @Override
    public User get(int id) throws SQLException {
        Connection con = Database.getConnection();
        User user = null;
        String sqlTemplate = "SELECT * FROM User WHERE id =?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, id);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.UserDAOImpl class at get() method the id is %s. The exception message is %s",
                    id, e.getMessage()));
            throw e;
        }

        if (rs.next()) {
            int qId = rs.getInt("id");
            String email1 = rs.getString("email");
            String password = rs.getString("password");
            String userName = rs.getString("userName");
            Date dob = rs.getDate("dob");
            String salt = rs.getString("salt");
            String pepper = rs.getString("pepper");
            String firstName = rs.getString("firstName");
            String middleName = rs.getString("middleName");
            String lastName = rs.getString("lastName");
            user = new User(qId, email1, password, userName, dob, salt, pepper, firstName, middleName, lastName);
        }
        con.close();
        return user;
    }

    @Override
    public List<User> getAll() throws SQLException {
        Connection con = Database.getConnection();
        List<User> userList = new ArrayList<User>();
        String sqlTemplate = "SELECT * FROM User";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.UserDAOImpl class at getAll().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int qId = rs.getInt("id");
            String email1 = rs.getString("email");
            String password = rs.getString("password");
            String userName = rs.getString("userName");
            Date dob = rs.getDate("dob");
            String salt = rs.getString("salt");
            String pepper = rs.getString("pepper");
            String firstName = rs.getString("firstName");
            String middleName = rs.getString("middleName");
            String lastName = rs.getString("lastName");
            userList.add(new User(qId, email1, password, userName, dob, salt, pepper, firstName, middleName, lastName));
        }
        con.close();
        return userList;
    }

    public int insert(User user) throws SQLException {
        // id,email, password, userName, dob, firstName, middleName, lastName
        Connection con = Database.getConnection();
        String sqlTemplate = "INSERT INTO User (id, email, password, userName, dob, salt, pepper, firstName, middleName, lastName) VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = -1;

        ps.setInt(1, user.getId());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getPassword());
        ps.setString(4, user.getUserName());
        ps.setDate(5, user.getDob());
        ps.setString(6, user.getSalt());
        ps.setString(7, user.getPepper());
        ps.setString(8, user.getFirstName());
        ps.setString(9, user.getMiddleName());
        ps.setString(10, user.getLastName());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.UserDAOImpl class at insert() method. The exception message is %s. The inserted id is %d. The inserted email is %s. The inserted userName is %s. The inserted dob is %s. The inserted salt is %s. The inserted pepper is %s. The inserted firstName is %s. The inserted middleName is %s. The inserted lastName is %s.",
                    e.getMessage(), user.getId(), user.getEmail(), user.getUserName(), user.getDob(), user.getSalt(),
                    user.getPepper(), user.getFirstName(), user.getMiddleName(), user.getLastName()));
            throw e;
        }
        con.close();
        return rowsAffected;
    }

    @Override
    public int update(User user) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "UPDATE User SET email = ? ,password = ? ,userName = ? ,dob = ? ,salt = ? ,pepper = ? ,firstName = ? ,middleName = ? ,lastName = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = -1;

        ps.setString(1, user.getEmail());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getUserName());
        ps.setDate(4, user.getDob());
        ps.setString(5, user.getSalt());
        ps.setString(6, user.getPepper());
        ps.setString(7, user.getFirstName());
        ps.setString(8, user.getMiddleName());
        ps.setString(9, user.getLastName());
        ps.setInt(10, user.getId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.UserDAOImpl class at update() method. The exception message is %s. The inserted id is %d. The inserted email is %s. The inserted userName is %s. The inserted dob is %s. The inserted salt is %s. The inserted pepper is %s. The inserted firstName is %s. The inserted middleName is %s. The inserted lastName is %s.",
                    e.getMessage(), user.getId(), user.getEmail(), user.getUserName(), user.getDob(), user.getSalt(),
                    user.getPepper(), user.getFirstName(), user.getMiddleName(), user.getLastName()));
            throw e;
        }
        con.close();
        return rowsAffected;
    }

    @Override
    public int delete(User user) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "DELETE FROM User WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        int rowsAffected = -1;

        ps.setInt(1, user.getId());

        try {
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.UserDAOImpl class at delete() method. The exception message is %s. The inserted id is %d. The inserted email is %s. The inserted userName is %s. The inserted dob is %s. The inserted salt is %s. The inserted pepper is %s. The inserted firstName is %s. The inserted middleName is %s. The inserted lastName is %s.",
                    e.getMessage(), user.getId(), user.getEmail(), user.getUserName(), user.getDob(), user.getSalt(),
                    user.getPepper(), user.getFirstName(), user.getMiddleName(), user.getLastName()));
            throw e;
        }
        con.close();
        return rowsAffected;
    }

    @Override
    public List<User> getUsersForGivenUserIds(int... ids) throws SQLException {
        Connection con = Database.getConnection();
        List<User> userList = new ArrayList<User>();
        String sqlTemplate = String.format("SELECT * FROM User WHERE id IN %s", getListRepresentation(ids));
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.UserDAOImpl class at getUsersForGivenUserIds().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int qId = rs.getInt("id");
            String email1 = rs.getString("email");
            String password = rs.getString("password");
            String userName = rs.getString("userName");
            Date dob = rs.getDate("dob");
            String salt = rs.getString("salt");
            String pepper = rs.getString("pepper");
            String firstName = rs.getString("firstName");
            String middleName = rs.getString("middleName");
            String lastName = rs.getString("lastName");
            userList.add(new User(qId, email1, password, userName, dob, salt, pepper, firstName, middleName, lastName));
        }
        con.close();
        return userList;
    }

    @Override
    public List<User> getAllUsersExceptGivenUsersIds(int... ids) throws SQLException {
        Connection con = Database.getConnection();
        List<User> userList = new ArrayList<User>();
        String sqlTemplate = String.format("SELECT * FROM User WHERE id NOT IN %s", getListRepresentation(ids));
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.UserDAOImpl class at getAllUsersExceptGivenUsersIds().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int qId = rs.getInt("id");
            String email1 = rs.getString("email");
            String password = rs.getString("password");
            String userName = rs.getString("userName");
            Date dob = rs.getDate("dob");
            String salt = rs.getString("salt");
            String pepper = rs.getString("pepper");
            String firstName = rs.getString("firstName");
            String middleName = rs.getString("middleName");
            String lastName = rs.getString("lastName");
            userList.add(new User(qId, email1, password, userName, dob, salt, pepper, firstName, middleName, lastName));
        }
        con.close();
        return userList;
    }

    @Override
    public String getUserFullName(int userId) throws SQLException {
        Connection con = Database.getConnection();
        String fullName = "";
        String sqlTemplate = "SELECT CONCAT(firstName,\" \",middleName,\" \",lastName) AS name FROM User WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setInt(1, userId);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.UserDAOImpl class at getUserFullName().The exception message is %s",
                    e.getMessage()));
            throw e;
        }
        if (rs.next()) {
            fullName = rs.getString("name");
        }
        con.close();
        return fullName;
    }

    @Override
    public List<User> getAllUsersForGivenNamePattern(String pattern) throws SQLException {
        pattern = "%" + pattern + "%";
        Connection con = Database.getConnection();
        List<User> userList = new ArrayList<User>();
        String sqlTemplate = "SELECT * FROM User WHERE LOWER(CONCAT(firstName,\" \",middleName,\" \",lastName)) LIKE LOWER(?)";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setString(1, pattern);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.UserDAOImpl class at getAllUsersForGivenNamePattern().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int qId = rs.getInt("id");
            String email1 = rs.getString("email");
            String password = rs.getString("password");
            String userName = rs.getString("userName");
            Date dob = rs.getDate("dob");
            String salt = rs.getString("salt");
            String pepper = rs.getString("pepper");
            String firstName = rs.getString("firstName");
            String middleName = rs.getString("middleName");
            String lastName = rs.getString("lastName");
            userList.add(new User(qId, email1, password, userName, dob, salt, pepper, firstName, middleName, lastName));
        }
        con.close();
        return userList;
    }

    @Override
    public List<User> getListOfUsersForGivenNamePattern(String pattern, int limit) throws SQLException {
        pattern = "%" + pattern + "%";
        Connection con = Database.getConnection();
        List<User> userList = new ArrayList<User>();
        String sqlTemplate = "SELECT * FROM User WHERE LOWER(CONCAT(firstName,\" \",middleName,\" \",lastName)) LIKE LOWER(?) LIMIT ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setString(1, pattern);
        ps.setInt(2, limit);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.UserDAOImpl class at getListOfUsersForGivenNamePattern().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int qId = rs.getInt("id");
            String email1 = rs.getString("email");
            String password = rs.getString("password");
            String userName = rs.getString("userName");
            Date dob = rs.getDate("dob");
            String salt = rs.getString("salt");
            String pepper = rs.getString("pepper");
            String firstName = rs.getString("firstName");
            String middleName = rs.getString("middleName");
            String lastName = rs.getString("lastName");
            userList.add(new User(qId, email1, password, userName, dob, salt, pepper, firstName, middleName, lastName));
        }
        con.close();
        return userList;
    }

    @Override
    public List<User> getAllUsersByGivenUserNamePattern(String pattern) throws SQLException {
        pattern = "%" + pattern + "%";
        Connection con = Database.getConnection();
        List<User> userList = new ArrayList<User>();
        String sqlTemplate = "SELECT * FROM User WHERE LOWER(userName) LIKE LOWER(?)";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setString(1, pattern);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.UserDAOImpl class at getAllUsersByGivenUserNamePattern().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int qId = rs.getInt("id");
            String email1 = rs.getString("email");
            String password = rs.getString("password");
            String userName = rs.getString("userName");
            Date dob = rs.getDate("dob");
            String salt = rs.getString("salt");
            String pepper = rs.getString("pepper");
            String firstName = rs.getString("firstName");
            String middleName = rs.getString("middleName");
            String lastName = rs.getString("lastName");
            userList.add(new User(qId, email1, password, userName, dob, salt, pepper, firstName, middleName, lastName));
        }
        con.close();
        return userList;
    }

    @Override
    public List<User> getListOfUsersByGivenUserNamePattern(String pattern, int limit) throws SQLException {
        pattern = "%" + pattern + "%";
        Connection con = Database.getConnection();
        List<User> userList = new ArrayList<User>();
        String sqlTemplate = "SELECT * FROM User WHERE LOWER(userName) LIKE LOWER(?) LIMIT ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setString(1, pattern);
        ps.setInt(2, limit);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.UserDAOImpl class at getListOfUsersByGivenUserNamePattern().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int qId = rs.getInt("id");
            String email1 = rs.getString("email");
            String password = rs.getString("password");
            String userName = rs.getString("userName");
            Date dob = rs.getDate("dob");
            String salt = rs.getString("salt");
            String pepper = rs.getString("pepper");
            String firstName = rs.getString("firstName");
            String middleName = rs.getString("middleName");
            String lastName = rs.getString("lastName");
            userList.add(new User(qId, email1, password, userName, dob, salt, pepper, firstName, middleName, lastName));
        }
        con.close();
        return userList;
    }

    @Override
    public List<User> getAllUsersByGivenEmailPattern(String pattern) throws SQLException {
        pattern = "%" + pattern + "%";
        Connection con = Database.getConnection();
        List<User> userList = new ArrayList<User>();
        String sqlTemplate = "SELECT * FROM User WHERE LOWER(email) LIKE LOWER(?)";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setString(1, pattern);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.UserDAOImpl class at getAllUsersByGivenEmailPattern().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int qId = rs.getInt("id");
            String email1 = rs.getString("email");
            String password = rs.getString("password");
            String userName = rs.getString("userName");
            Date dob = rs.getDate("dob");
            String salt = rs.getString("salt");
            String pepper = rs.getString("pepper");
            String firstName = rs.getString("firstName");
            String middleName = rs.getString("middleName");
            String lastName = rs.getString("lastName");
            userList.add(new User(qId, email1, password, userName, dob, salt, pepper, firstName, middleName, lastName));
        }
        con.close();
        return userList;
    }

    @Override
    public List<User> getListOfUsersByGivenEmailPattern(String pattern, int limit) throws SQLException {
        pattern = "%" + pattern + "%";
        Connection con = Database.getConnection();
        List<User> userList = new ArrayList<User>();
        String sqlTemplate = "SELECT * FROM User WHERE LOWER(email) LIKE LOWER(?) LIMIT ?";
        PreparedStatement ps = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        ps.setString(1, pattern);
        ps.setInt(2, limit);

        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.UserDAOImpl class at getListOfUsersByGivenEmailPattern().The exception message is %s",
                    e.getMessage()));
            throw e;
        }

        while (rs.next()) {
            int qId = rs.getInt("id");
            String email1 = rs.getString("email");
            String password = rs.getString("password");
            String userName = rs.getString("userName");
            Date dob = rs.getDate("dob");
            String salt = rs.getString("salt");
            String pepper = rs.getString("pepper");
            String firstName = rs.getString("firstName");
            String middleName = rs.getString("middleName");
            String lastName = rs.getString("lastName");
            userList.add(new User(qId, email1, password, userName, dob, salt, pepper, firstName, middleName, lastName));
        }
        con.close();
        return userList;
    }

}