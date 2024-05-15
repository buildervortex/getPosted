package com.getposted.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;

import com.getposted.logger.Logging;
import java.util.logging.Logger;

public class AuthorDAOImpl implements AuthorDAO {

	private static Logger logger = Logging.getLogger(AuthorDAOImpl.class.getName());

	@Override
	public Author get(int id) throws SQLException {
		Connection con = Database.getConnection();
		Author author = null;
		String sqlTemplate = "SELECT * FROM Author WHERE id = ?";
		PreparedStatement select = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		select.setInt(1, id);

		try {
			rs = select.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.AuthorDAOImpl class at getId() method the id is %d. The exception message is %s",
					id, e.getMessage()));
			throw e;
		}

		if (rs.next()) {
			int qId = rs.getInt("id");
			String email = rs.getString("email");
			String phoneNumber = rs.getString("phoneNumber");
			String bio = rs.getString("bio");
			String password = rs.getString("password");
			Date dob = rs.getDate("dob");
			String firstName = rs.getString("firstName");
			String middleName = rs.getString("middleName");
			String lastName = rs.getString("lastName");
			String userName = rs.getString("userName");
			author = new Author(qId, email, phoneNumber, bio, password, dob, firstName, middleName, lastName,
					userName);
		}

		return author;
	}

	@Override
	public List<Author> getAll() throws SQLException {
		Connection con = Database.getConnection();
		List<Author> authorList = new ArrayList<Author>();
		String sqlTemplate = "SELECT * FROM Author";
		PreparedStatement select = con.prepareStatement(sqlTemplate);
		ResultSet rs = null;

		try {
			rs = select.executeQuery();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.AuthorDAOImpl class at getAll() .The exception message is %s",
					e.getMessage()));
			throw e;
		}

		while (rs.next()) {
			int qId = rs.getInt("id");
			String email = rs.getString("email");
			String phoneNumber = rs.getString("phoneNumber");
			String bio = rs.getString("bio");
			String password = rs.getString("password");
			Date dob = rs.getDate("dob");
			String firstName = rs.getString("firstName");
			String middleName = rs.getString("middleName");
			String lastName = rs.getString("lastName");
			String userName = rs.getString("userName");
			authorList.add(new Author(qId, email, phoneNumber, bio, password, dob, firstName, middleName, lastName,
                    userName));
		}

		return authorList;
	}

	@Override
	public int insert(Author author) throws SQLException {
		Connection con = Database.getConnection();
		String sqlTemplate = "INSERT INTO Author(id,email,phoneNumber,bio,password,dob,firstName,middleName,lastName,userName) VALUES(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sqlTemplate);
		int result = -1;

		st.setInt(1, author.getId());
		st.setString(2, author.getEmail());
		st.setString(3, author.getPhoneNumber());
		st.setString(4, author.getBio());
		st.setString(5, author.getPassword());
		st.setDate(6, author.getDob());
		st.setString(7, author.getFirstName());
		st.setString(8, author.getMiddleName());
		st.setString(9, author.getLastName());
		st.setString(10, author.getUserName());

		try {
			result = st.executeUpdate();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.AuthorDAOImpl class at insert() method. The exception message is %s. The inserted Author id is %d. The inserted Author email is %s. The inserted Author phoneNumber is %s. The inserted Author bio is %s. The inserted Author dob is %s. The inserted Author firstName is %s. The inserted Author middleName is %s. The inserted Author lastName is %s. The inserted Author username is %s. ",
					e.getMessage(), author.getId(),author.getEmail(),author.getPhoneNumber(),author.getBio(),author.getDob().toString(),author.getFirstName(),author.getMiddleName(),author.getLastName(),author.getUserName()));
			throw e;
		}

		return result;
	}

	@Override
	public int update(Author author) throws SQLException {
		Connection con = Database.getConnection();
		// INSERT INTO Author (id,email, phoneNumber, bio, password, dob, firstName, middleName, lastName, userName)
		String sqlTemplate = "UPDATE Author SET email = ?, phoneNumber = ?, bio = ?, password = ?, dob = ?, firstName = ?, middleName = ?, lastName = ?, userName = ? WHERE id = ?";
		PreparedStatement st = con.prepareStatement(sqlTemplate);
		int result = -1;

		st.setString(1, author.getEmail());
		st.setString(2, author.getPhoneNumber());
		st.setString(3, author.getBio());
		st.setString(4, author.getPassword());
		st.setDate(5, author.getDob());
		st.setString(6, author.getFirstName());
		st.setString(7, author.getMiddleName());
		st.setString(8, author.getLastName());
		st.setString(9, author.getUserName());
		st.setInt(10, author.getId());

		try {
			result = st.executeUpdate();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.AuthorDAOImpl class at update() method. The exception message is %s. The inserted Author id is %s. The inserted Author email is %s. The inserted Author phoneNumber is %s. The inserted Author bio is %s. The inserted Author dob is %s. The inserted Author firstName is %s. The inserted Author middleName is %s. The inserted Author lastName is %s. The inserted Author username is %s. ",
					e.getMessage(), author.getId(),author.getEmail(),author.getPhoneNumber(),author.getBio(),author.getDob(),author.getFirstName(),author.getMiddleName(),author.getLastName(),author.getUserName()));
			throw e;
		}

		return result;
	}

	@Override
	public int delete(Author author) throws SQLException {
		Connection con = Database.getConnection();
		String sqlTemplate = "DELETE FROM Author WHERE id = ?";
		PreparedStatement st = con.prepareStatement(sqlTemplate);
		int result = -1;

		st.setInt(1, author.getId());

		try {
			result = st.executeUpdate();
		} catch (SQLException e) {
			logger.warning(String.format(
					"There is SQLException happend in the com.getposted.model.AuthorDAOImpl class at delete() method. The exception message is %s. The inserted Author id is %s. The inserted Author email is %s. The inserted Author phoneNumber is %s. The inserted Author bio is %s. The inserted Author dob is %s. The inserted Author firstName is %s. The inserted Author lastName is %s. The inserted Author middleName is %s. The inserted Author lastName is %s. The inserted Author username is %s. ",
					e.getMessage(), author.getId(),author.getEmail(),author.getPhoneNumber(),author.getBio(),author.getDob(),author.getFirstName(),author.getMiddleName(),author.getLastName(),author.getUserName()));
			throw e;
		}

		return result;
	}

}