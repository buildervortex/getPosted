package com.getposted.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

import com.getposted.model.Database;
import com.getposted.system.Sysenv;

public class TestDataBase {

    private static Connection con;
    public static final String DATABASENAME = "testGetPosted";
    public static final String DATABASEURL = "jdbc:mariadb://localhost:3306/";
    private static final String DATABASETABLEFILE = "databaseTables.txt";
    private static final String DATABASETESTDATAFILE = "databaseTestData.txt";

    public static void createDatabase() throws SQLException{
        Sysenv.setEnv("DATABASEURL",DATABASEURL);
        Sysenv.setEnv("DATABASENAME","");
        con = Database.getConnection();
        Statement st = con.createStatement();

        st.executeUpdate("CREATE DATABASE "+DATABASENAME);
    }

    public static void createTables() throws SQLException,IOException{
        setEnv();
        ExecuteSQLFile(DATABASETABLEFILE);

    }
    public static void addTestData() throws SQLException,IOException {
        setEnv();
        ExecuteSQLFile(DATABASETESTDATAFILE);
    }

    public static void deleteDatabase() throws SQLException{
        Sysenv.setEnv("DATABASEURL",DATABASEURL);
        Sysenv.setEnv("DATABASENAME","");
        con = Database.getConnection();
        Statement st = con.createStatement();

        st.executeUpdate("DROP DATABASE "+DATABASENAME);
    }

    private static void setEnv(){
        Sysenv.setEnv("DATABASEURL",TestDataBase.DATABASEURL);
		Sysenv.setEnv("DATABASENAME",TestDataBase.DATABASENAME);
    }
    private static void ExecuteSQLFile(String FileName) throws SQLException,IOException{
        con = Database.getConnection();
        Statement st = con.createStatement();

        FileReader fileReader = new FileReader(FileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = bufferedReader.readLine();

        while(line != null){
            st.executeUpdate(line);
            line = bufferedReader.readLine();
        }
    }
}