package com.getposted.model;

import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Before;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


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
	public void testConnectivityToDatabase() throws Exception{
		con = Database.getConnection();
		assertFalse(con.isClosed());
		con.close();
		assertTrue(con.isClosed());
	}

	@Test
	public void testConnectivityToDatabase2() throws SQLException{
		String databaseName = "testDatabase1";
		con = Database.getDBMSConnection();
		Database.createDatabase(databaseName, con);
		con.close();
		con = Database.getConnection(databaseName);
		assertFalse(con.isClosed());
		con.close();
		assertTrue(con.isClosed());
		con = Database.getDBMSConnection();
		Database.dropDatabase(databaseName, con);
		con.close();
	}

	@Test
	public void testConnectivityToDBMS() throws SQLException{
		con = Database.getDBMSConnection();
		assertFalse(con.isClosed());
		con.close();
		assertTrue(con.isClosed());
	}

	@Test
	public void testCreateDatabase() throws SQLException{
		String dataBaseName = "testDatabase";
		con = Database.getDBMSConnection();
		Database.createDatabase(dataBaseName, con);
		assertTrue(Database.isDatabaseExists(dataBaseName, con));
		Database.dropDatabase(dataBaseName, con);
		con.close();
	}

	@Test
	public void testDropDatabase() throws SQLException{
		String databaseName = "testDatabase";
		con = Database.getDBMSConnection();
		Database.createDatabase(databaseName, con);
		Database.dropDatabase(databaseName, con);
		assertFalse(Database.isDatabaseExists(databaseName, con));
		con.close();
	}

	@Test
	public void testisDatabaseExists() throws SQLException{
		String databaseName = "testDatabase";
		con = Database.getDBMSConnection();
		Database.createDatabase(databaseName, con);
		assertTrue(Database.isDatabaseExists(databaseName, con));
		Database.dropDatabase(databaseName, con);
		assertFalse(Database.isDatabaseExists(databaseName, con));
		con.close();
	}
	@Test
	public void testDatabaseExistancy() throws SQLException{
		con = Database.getDBMSConnection();
		Database.createDatabase("hello", con);
		assertTrue(Database.isDatabaseExists("hello", con));
		Database.dropDatabase("hello", con);
		con.close();
	}

	@Test
	public void testIsTableExists() throws SQLException{
		String tableName = "Publication";
		con = Database.getConnection();
		assertTrue(Database.isTableExists(tableName, con));
		assertFalse(Database.isTableExists("NonExistingTable", con));
		con.close();
	}

	@Test 
	public void testDropTable() throws SQLException{
		String query = "CREATE TABLE testingTableOnly(id INT PRIMARY KEY)";
		con = Database.getConnection();
		Statement st = con.createStatement();
		st.executeUpdate(query);
		assertTrue(Database.isTableExists("testingTableOnly", con));
		Database.dropTable("testingTableOnly", con);
		assertFalse(Database.isTableExists("testingTableOnly", con));
		con.close();

	}

	@Test
	public void testExecuteUpdateOnFile() throws SQLException, IOException{
		String path = Paths.get("src/test/resources/databaseTables.txt").toAbsolutePath().toString();

		// add the table names
		List<String> tableNames = new ArrayList<String>();
		tableNames.add("Language");
		tableNames.add("Category");
		tableNames.add("Skill");
		tableNames.add("Country");
		tableNames.add("Author");
		tableNames.add("Publisher");
		tableNames.add("User");
		tableNames.add("Publication");
		tableNames.add("SearchTag");
		tableNames.add("Save");
		tableNames.add("Inquiry");
		tableNames.add("PhoneNumber");
		tableNames.add("Purchase");
		tableNames.add("IsInformed");
		tableNames.add("Rates");
		tableNames.add("Request");
		tableNames.add("IsNotifiedBy");
		tableNames.add("Has");
		tableNames.add("Reviews");

		String databaseName = "testExecuteUpdateFile";
		con = Database.getDBMSConnection();
		Database.createDatabase(databaseName, con);
		con.close();


		con = Database.getConnection(databaseName);
		Database.executeUpdateOnFile(path, con);
		con.close();

		con = Database.getConnection(databaseName);
		for (String tableName: tableNames){
			assertTrue(Database.isTableExists(tableName, con));
		}
		assertFalse(Database.isTableExists("notexists1", con));
		assertFalse(Database.isTableExists("notexists2", con));
		assertFalse(Database.isTableExists("notexists4", con));
		assertFalse(Database.isTableExists("notexists5", con));
		
		con = Database.getDBMSConnection();
		Database.dropDatabase(databaseName, con);
		con.close();
	}

	@Test
	public void testExecuteUpdateOnResource() throws SQLException, IOException{
		List<String> tableNames = new ArrayList<String>();
		tableNames.add("Language");
		tableNames.add("Category");
		tableNames.add("Skill");
		tableNames.add("Country");
		tableNames.add("Author");
		tableNames.add("Publisher");
		tableNames.add("User");
		tableNames.add("Publication");
		tableNames.add("SearchTag");
		tableNames.add("Save");
		tableNames.add("Inquiry");
		tableNames.add("PhoneNumber");
		tableNames.add("Purchase");
		tableNames.add("IsInformed");
		tableNames.add("Rates");
		tableNames.add("Request");
		tableNames.add("IsNotifiedBy");
		tableNames.add("Has");
		tableNames.add("Reviews");

		String databaseName = "testExecuteUpdateFile";
		con = Database.getDBMSConnection();
		Database.createDatabase(databaseName, con);
		con.close();


		con = Database.getConnection(databaseName);
		Database.executeUpdateOnResourceFile("databaseTables.txt", con);
		con.close();

		con = Database.getConnection(databaseName);
		for (String tableName: tableNames){
			assertTrue(Database.isTableExists(tableName, con));
		}
		assertFalse(Database.isTableExists("notexists1", con));
		assertFalse(Database.isTableExists("notexists2", con));
		assertFalse(Database.isTableExists("notexists4", con));
		assertFalse(Database.isTableExists("notexists5", con));
		
		con = Database.getDBMSConnection();
		Database.dropDatabase(databaseName, con);
		con.close();		
	}
	@AfterClass
	public static void destroyDatabase(){
		TestDataBase.deleteDatabase();
	}
}
