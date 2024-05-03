package com.getposted.model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.getposted.logger.Logging;
import java.util.logging.Logger;
import com.getposted.system.Sysenv;

import java.sql.SQLException;
import java.sql.SQLInvalidAuthorizationSpecException;
import java.sql.Statement;
import java.lang.ClassNotFoundException;

public class Database {

	private static String driver = "org.mariadb.jdbc.Driver";
	// get the Logger object to log the messages
	private static Logger logger = Logging.getLogger(Database.class.getName());

	private Database() {
	}

	public static Connection getConnection() throws SQLException {

		String url = Sysenv.getEnv("DATABASEURL"); // "jdbc:mariadb://localhost:3306/"
		String database = Sysenv.getEnv("DATABASENAME"); // "getPosted"
		String userName = Sysenv.getEnv("DATABASEUSERNAME");
		String password = Sysenv.getEnv("DATABASEPASSWORD");

		// create connection data type variable
		Connection connection = null;

		// load the driver and register it.
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			logger.severe(String.format(
					"The Driver class not found exception occured. the Driver class name is %s. The exception message is %s",
					driver, e.getMessage()));
		}

		// get the connection object
		try {
			connection = DriverManager.getConnection((url + database), userName, password);
		} catch (SQLInvalidAuthorizationSpecException e) {
			logger.severe(String.format(
					"The SQLInvalidAuthorizationSpecException occured (invalid username or password). The url is correct and the registration is also completed.when tring to get the connection object using DriverManager.getConnection method. The url is %s. The password is %s. The username is %s. The Driver was loaded successfully. The Driver name is %s. The Exception message is %s",
					url, password, userName, driver, e.getMessage()));
		} catch (SQLException e) {
			logger.severe(String.format(
					"The SQLEXception occured when tring to get the connection object using DriverManager.getConnection method. The Driver loading was successful. The url is %s. The password is %s. The username is %s. The Driver was loaded successfully. The Driver name is %s. The Exception message is %s",
					url, password, userName, driver, e.getMessage()));
			throw e;
		}

		// return the connection object
		return connection;
	}

	public static Connection getDatabaseConnection(String url, String userName, String password) throws SQLException{

		// create connection data type variable
		Connection connection = null;

		// load the driver and register it.
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			logger.severe(String.format(
					"The Driver class not found exception occured. the Driver class name is %s. The exception message is %s",
					driver, e.getMessage()));
		}

		// get the connection object
		try {
			connection = DriverManager.getConnection((url), userName, password);
		} catch (SQLInvalidAuthorizationSpecException e) {
			logger.severe(String.format(
					"The SQLInvalidAuthorizationSpecException occured (invalid username or password). The url is correct and the registration is also completed.when tring to get the connection object using DriverManager.getConnection method. The url is %s. The password is %s. The username is %s. The Driver was loaded successfully. The Driver name is %s. The Exception message is %s",
					url, password, userName, driver, e.getMessage()));
		} catch (SQLException e) {
			logger.severe(String.format(
					"The SQLEXception occured when tring to get the connection object using DriverManager.getConnection method. The Driver loading was successful. The url is %s. The password is %s. The username is %s. The Driver was loaded successfully. The Driver name is %s. The Exception message is %s",
					url, password, userName, driver, e.getMessage()));
			throw e;
		}

		// return the connection object
		return connection;
	}

	public static boolean createDatabase(String name, Connection con) throws SQLException{
		boolean result = false;
		String query = "CREATE DATABASE "+name;
		Statement statement = con.createStatement();

		try{
			statement.executeUpdate(query);
		}
		catch(SQLException e){
			logger.severe(String.format("The SQLException occoured in createDatabase method of the Database.java file. The exception message is %s",e.getMessage()));
			throw e;
		}

		return result;
	}

	public static boolean dropDatabase(String name, Connection con) throws SQLException{
		boolean result = false;
		String query = "DROP DATABASE "+name;
		Statement statement = con.createStatement();

		try{
			statement.executeUpdate(query);
		}
		catch(SQLException e){
			logger.severe(String.format("The SQLException occoured in dropDatabase method of the Database.java file. The exception message is %s",e.getMessage()));
			throw e;
		}

		return result;
	}

	public static boolean checkDatabaseExists(String name, Connection connection) throws SQLException{
		boolean result = false;
		DatabaseMetaData metaData = null;
		try{
			metaData = connection.getMetaData();
		}
		catch (SQLException e){
			logger.severe(String.format("The SQLException occoured in checkDatabaseExists method of the Database.java file. The exception message is %s.",e.getMessage()));
			throw e;
		}

		// get the list of databases
		ResultSet resultSet = metaData.getCatalogs();

		// check for database existancy
		while(resultSet.next()){
			String databaseName = resultSet.getString(1);
			if(databaseName.equals(name)){
				result = true;
			}
		}

		resultSet.close();
		connection.close();
		return result;
	}

	public static boolean checkTableExists(String tableName, Connection con) throws SQLException{
		boolean result = false;
		DatabaseMetaData databaseMetaData =null;

		try{
			databaseMetaData = con.getMetaData();
		}
		catch (SQLException e){
			logger.severe(String.format("There is SQLException occoured in the checkTableExists method of the Database.java file. The error message is %s",e.getMessage()));
			throw e;
		}

		ResultSet resultSet = databaseMetaData.getTables(null,null,tableName,null);

		if(resultSet.next()){
			result = true;
		}

		return result;
	}

}
