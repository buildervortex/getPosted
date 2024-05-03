package com.getposted.model;

import java.sql.Connection;
import static org.junit.Assert.fail;

import com.getposted.system.Sysenv;

public class TestDataBase {

    private static Connection con;
    public static final String DATABASENAME = "testGetPosted";
    public static final String DATABASEURL = "jdbc:mariadb://localhost:3306/";
    private static final String DATABASETABLEFILE = "databaseTables.txt";
    private static final String DATABASETESTDATAFILE = "databaseTestData.txt";

    public static void createDatabase(){
        setDatabase();
        Database.reload();
        try{
            con = Database.getDBMSConnection();
            Database.createDatabase(DATABASENAME, con);
            con.close();
        }
        catch (Exception e){
            fail("The exception occoured at createDatabase method on TestDataBase class. The Exception is "+e);
        }
    }

    public static void createTables(){
        setDatabase();
        try{
            con = Database.getConnection();
            Database.executeUpdateOnResourceFile(DATABASETABLEFILE, con);
            con.close();
        }
        catch (Exception e){
            fail("The exception occoured at createTables method on TestDataBase class. The Exception is "+e);
        }
    }
    public static void addTestData(){
        setDatabase();
        try{
            con = Database.getConnection();
            Database.executeUpdateOnResourceFile(DATABASETESTDATAFILE, con);
            con.close();
        }
        catch (Exception e){
            fail("The exception occoured at addTestData method on TestDataBase class. The Exception is "+e);
        }
    }

    public static void deleteDatabase() {
        setDatabase();
        try{
            con = Database.getDBMSConnection();
            Database.dropDatabase(DATABASENAME, con);
        }
        catch(Exception e){
            fail("The exception occoured at deleteDatabase method on TestDataBase class. The Exception is "+e);
        }
    }

    public static void createAll(){
        createDatabase();
        createTables();
        addTestData();   
    }

    public static void setDatabase(){
        Sysenv.setEnv("DATABASEURL",DATABASEURL);
        Sysenv.setEnv("DATABASENAME",DATABASENAME);
        Database.reload();
    }
}