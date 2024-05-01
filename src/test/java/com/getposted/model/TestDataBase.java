package com.getposted.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileReader;

import static org.junit.Assert.fail;

import java.io.BufferedReader;

import com.getposted.model.Database;
import com.getposted.system.Sysenv;

public class TestDataBase {

    private static Connection con;
    public static final String DATABASENAME = "testGetPosted";
    public static final String DATABASEURL = "jdbc:mariadb://localhost:3306/";
    private static final String DATABASETABLEFILE = "databaseTables.txt";
    private static final String DATABASETESTDATAFILE = "databaseTestData.txt";

    public static void createDatabase() {
        try{
            Sysenv.setEnv("DATABASEURL",DATABASEURL);
            Sysenv.setEnv("DATABASENAME","");
            con = Database.getConnection();
            Statement st = con.createStatement();
            st.executeUpdate("CREATE DATABASE "+DATABASENAME);
        }
        catch(SQLException e){}
        catch(Exception e){
            fail("The exception occoured at createDatabase method on TestDataBase class. The Exception is "+e);
        }
    }

    public static void createTables() {
        setDatabase();
        ExecuteSQLFile(DATABASETABLEFILE);
    }
    public static void addTestData() {
        setDatabase();
        ExecuteSQLFile(DATABASETESTDATAFILE);
    }

    public static void deleteDatabase() {
        try{
        Sysenv.setEnv("DATABASEURL",DATABASEURL);
        Sysenv.setEnv("DATABASENAME","");
        con = Database.getConnection();
        Statement st = con.createStatement();

        st.executeUpdate("DROP DATABASE "+DATABASENAME);
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
    }

    private static void ExecuteSQLFile(String FileName) {
        try{
        con = Database.getConnection();
        Statement st = con.createStatement();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(FileName);
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line = bufferedReader.readLine();

        while(line != null){
            st.executeUpdate(line);
            line = bufferedReader.readLine();
        }
    }
    catch(SQLException e){}
    catch(Exception e){
        fail("The exception occoured at ExecuteSQLFile method on TestDataBase class. The Exception is "+e);
    }
    }
}