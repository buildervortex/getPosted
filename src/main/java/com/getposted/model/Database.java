package com.getposted.model;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

import com.getposted.logger.Logging;
import java.util.logging.Level;
import java.lang.ClassNotFoundException;

public class Database{

	private static String url = "jdbc:mariadb://localhost:3306/";
	private static String database = "getPosted";
	private static String userName = "root";
	private static String password = "Lahiru";
	private static String driver = "org.mariadb.jdbc.Driver";

	private Database(){}

	public static Connection getConnection(){
		// create connection data type variable
		Connection connection = null;

		// load the driver and register it.
		try{
			Class.forName(driver);
		}
		catch(Exception )

		return connection;
	}
}
