package com.dbmananger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AWSOracleDBManager {
//    private static final String url = "jdbc:driver://aws-oracle.c4rxgpomdbzu.us-east-1.rds.amazonaws.com:1521/ORCL";
    private static final String url = "jdbc:oracle:thin:@//aws-oracle.c4rxgpomdbzu.us-east-1.rds.amazonaws.com:1521/ORCL";
//    private static final String url = "jdbc:oracle:thin:@//ivlhdp985.informatica.com:1521/pam12cr2";

    private static final String username = "admin";
    private static final String password = "adminroot";

    public List<String> getTablesWithCache() {
        List<String> tables = new ArrayList<>();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String QUERY = "select * from user_tables";
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                String pid = resultSet.getString(1);
                tables.add(pid);
            }
            statement.close();
            connection.close();
            return tables;
        } catch (Exception e) {
            System.out.println("ERROR: Not able to Connect to AWS DB Exception" + e);
            throw new RuntimeException(e);
        }
    }

    public List<String> getTables(String dbName) {
        List<String> tables = new ArrayList<>();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String QUERY = "select * from " + dbName;
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                String pid = resultSet.getString(1);
                tables.add(pid);
            }
            statement.close();
            connection.close();
            return tables;
        } catch (Exception e) {
            System.out.println("ERROR: Not able to Connect to AWS DB Exception" + e);
            throw new RuntimeException(e);
        }
    }
    public List<String> getTables() {
        List<String> tables = new ArrayList<>();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String QUERY = "select * from user_tables";
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                String pid = resultSet.getString(1);
                tables.add(pid);
            }
            statement.close();
            connection.close();
            return tables;
        } catch (Exception e) {
            System.out.println("ERROR: Not able to Connect to AWS DB Exception" + e);
            throw new RuntimeException(e);
        }
    }

    public List<String> getTablesUsingAPI() {
        List<String> tables = new ArrayList<>();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(url, username, password);
            DatabaseMetaData metaData = connection.getMetaData();
            metaData.getSchemas();
//            ResultSet rs = metaData.getSchemas(catalogName, null);
            ResultSet rs = metaData.getSchemas();
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }

            connection.close();
            return tables;
        } catch (Exception e) {
            System.out.println("ERROR: Not able to Connect to Oracle DB Exception" + e);
            throw new RuntimeException(e);
        }
    }
    public List<String> getTablesUsingAPI3() {
        List<String> tables = new ArrayList<>();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(url, username, password);
            DatabaseMetaData metaData = connection.getMetaData();
//            ResultSet rs = metaData.getSchemas(catalogName, null);
            ResultSet rs = metaData.getColumns(null, "ORCL", "STUDENT", null);
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString("COLUMN_NAME"));


            }

            connection.close();
            return tables;
        } catch (Exception e) {
            System.out.println("ERROR: Not able to Connect to Oracle DB Exception" + e);
            throw new RuntimeException(e);
        }
    }



    public List<String> getTablesUsingAPI2() {
        List<String> tables = new ArrayList<>();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(url, username, password);
            DatabaseMetaData metaData = connection.getMetaData();

            Statement st = connection.createStatement();
            ResultSet rset = st.executeQuery("SELECT * FROM \"STUDENT\"");
            ResultSetMetaData md = rset.getMetaData();

            metaData.getSchemas();
//            ResultSet rs = metaData.getSchemas(catalogName, null);
            ResultSet rs = metaData.getSchemas();
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }

            connection.close();
            return tables;
        } catch (Exception e) {
            System.out.println("ERROR: Not able to Connect to Oracle DB Exception" + e);
            throw new RuntimeException(e);
        }
    }

    public List<String> getTablesData() {
        List<String> tables = new ArrayList<>();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String QUERY = "select FIRST_NAME from Student";
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                String pid = resultSet.getString(1);
                tables.add(pid);
            }
            statement.close();
            connection.close();
            return tables;
        } catch (Exception e) {
            System.out.println("ERROR: Not able to Connect to AWS DB Exception" + e);
            throw new RuntimeException(e);
        }
    }
}
