package com.getposted.model;

import org.junit.Test;
import static org.junit.Assert.*;

import com.getposted.model.Database;
import java.sql.Connection;

import java.sql.SQLException;
import java.lang.ClassNotFoundException;

public class TestDatabase{

	private static Connection con;

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

}
