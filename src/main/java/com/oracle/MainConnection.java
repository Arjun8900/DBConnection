package com.oracle;

import com.dbmananger.AWSOracleDBManager;
import com.dbmananger.OracleDBManager;
import com.dbmananger.RohitDB;

import java.sql.SQLException;
import java.util.List;

public class MainConnection {
    private static final OracleDBManager oracleDBManager = new OracleDBManager();
    private static final AWSOracleDBManager awsdbManager = new AWSOracleDBManager();
//    private static final RohitDB awsdbManager = new RohitDB();
    public static void main(String[] args) throws SQLException, ClassNotFoundException {


//        System.out.println(oracleDBManager.getActivePID());
//        List<String> allTales = oracleDBManager.getTablesUsingAPI();
//        System.out.printf("Size = %s, allTables = %s%n", allTales.size(), allTales);

        List<String> allTales = awsdbManager.getTables();
        System.out.printf("Size = %s, allTables = %s%n", allTales.size(), allTales);

//        List<String> allTales = oracleDBManager.getTablesUsingAPI2();
//        System.out.printf("Size = %s, allTables = %s%n", allTales.size(), allTales);

        oracleDBManager.print("pam12cr2", "ORDERS");
    }
}
