package com.getposted.model;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.AfterClass;

import com.getposted.model.Database;
import com.getposted.system.Sysenv;
import java.sql.Connection;

import java.sql.SQLException;

public class TestDatabase{

	private static Connection con;

	@BeforeClass
	public static void initializeDatabase() throws SQLException{
		TestDataBase.createDatabase();
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
	public static void destroyDatabase() throws SQLException{
		TestDataBase.deleteDatabase();
	}
}
