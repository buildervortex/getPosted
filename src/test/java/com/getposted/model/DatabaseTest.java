package com.getposted.model;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;

import com.getposted.model.Database;
import com.getposted.system.Sysenv;
import java.sql.Connection;

import java.sql.SQLException;

public class DatabaseTest{

	private static Connection con;

	@BeforeClass
	public static void initializeDatabase(){
		TestDataBase.createDatabase();
	}

	@Before
	public void testCreateTables() throws SQLException{
		TestDataBase.createTables();
		TestDataBase.addTestData();
	}

	@Test
	public void testConnectionNullability() throws Exception {
		con = Database.getConnection();
		assertNotNull(con);
	}

	@Test
	public void testConnectionObjectDataType() throws Exception{
		con = Database.getConnection();
		assertTrue(con instanceof Connection);
	}

	@Test
	public void testConnectivity() throws Exception{
		con = Database.getConnection();
		assertFalse(con.isClosed());
	}

	@AfterClass
	public static void destroyDatabase(){
		TestDataBase.deleteDatabase();
	}
}
