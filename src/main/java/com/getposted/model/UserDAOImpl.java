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

    @Override
    public User get(int id) throws SQLException {
        Connection con = Database.getConnection();
        User user = null;
        String sqlTemplate = "SELECT * FROM User WHERE id =?";
        PreparedStatement select = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        select.setInt(1, id);

        try {
            rs = select.executeQuery();
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
            String firstName = rs.getString("firstName");
            String middleName = rs.getString("middleName");
            String lastName = rs.getString("lastName");
            user = new User(qId, email1, password, userName, dob, firstName, middleName, lastName);
        }

        return user;
    }

    @Override
    public List<User> getAll() throws SQLException {
        Connection con = Database.getConnection();
        List<User> userList = new ArrayList<User>();
        String sqlTemplate = "SELECT * FROM User";
        PreparedStatement select = con.prepareStatement(sqlTemplate);
        ResultSet rs = null;

        try {
            rs = select.executeQuery();
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
            String firstName = rs.getString("firstName");
            String middleName = rs.getString("middleName");
            String lastName = rs.getString("lastName");
            userList.add(new User(qId, email1, password, userName, dob, firstName, middleName, lastName));
        }

        return userList;
    }

    public int insert(User user) throws SQLException {
        // id,email, password, userName, dob, firstName, middleName, lastName
        Connection con = Database.getConnection();
        String sqlTemplate = "INSERT INTO User (id, email, password, userName, dob, firstName, middleName, lastName) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement st = con.prepareStatement(sqlTemplate);
        int result = -1;

        st.setInt(1, user.getId());
        st.setString(2, user.getEmail());
        st.setString(3, user.getPassword());
        st.setString(4, user.getUserName());
        st.setDate(5, user.getDob());
        st.setString(6, user.getFirstName());
        st.setString(7, user.getMiddleName());
        st.setString(8, user.getLastName());

        try {
            result = st.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.UserDAOImpl class at insert() method. The exception message is %s. The inserted id is %d. The inserted email is %s. The inserted userName is %s. The inserted dob is %s. The inserted firstName is %s. The inserted middleName is %s. The inserted lastName is %s.",
                    e.getMessage(), user.getId(), user.getEmail(), user.getUserName(), user.getDob(),
                    user.getFirstName(), user.getMiddleName(), user.getLastName()));
            throw e;
        }

        return result;
    }

    @Override
    public int update(User user) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "UPDATE User SET email=?,password=?,userName=?,dob=?,firstName=?,middleName=?,lastName=? WHERE id=?";
        PreparedStatement st = con.prepareStatement(sqlTemplate);
        int result = -1;

        st.setString(1, user.getEmail());
        st.setString(2, user.getPassword());
        st.setString(3, user.getUserName());
        st.setDate(4, user.getDob());
        st.setString(5, user.getFirstName());
        st.setString(6, user.getMiddleName());
        st.setString(7, user.getLastName());
        st.setInt(8, user.getId());

        try {
            result = st.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.UserDAOImpl class at update() method. The exception message is %s. The inserted id is %d. The inserted email is %s. The inserted userName is %s. The inserted dob is %s. The inserted firstName is %s. The inserted middleName is %s. The inserted lastName is %s.",
                    e.getMessage(), user.getId(), user.getEmail(), user.getUserName(), user.getDob(),
                    user.getFirstName(), user.getMiddleName(), user.getLastName()));
            throw e;
        }
        return result;
    }

    @Override
    public int delete(User user) throws SQLException {
        Connection con = Database.getConnection();
        String sqlTemplate = "DELETE FROM User WHERE id=?";
        PreparedStatement st = con.prepareStatement(sqlTemplate);
        int result = -1;

        st.setInt(1, user.getId());

        try {
            result = st.executeUpdate();
        } catch (SQLException e) {
            logger.warning(String.format(
                    "There is SQLException happend in the com.getposted.model.UserDAOImpl class at delete() method. The exception message is %s. The inserted id is %d. The inserted email is %s. The inserted userName is %s. The inserted dob is %s. The inserted firstName is %s. The inserted middleName is %s. The inserted lastName is %s.",
                    e.getMessage(), user.getId(), user.getEmail(), user.getUserName(), user.getDob(),
                    user.getFirstName(), user.getMiddleName(), user.getLastName()));
            throw e;
        }
        return result;
    }
}