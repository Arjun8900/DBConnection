package com.dbmananger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AWSDBManager {
//    private static final String url = "jdbc:oracle:thin:@//ivlhdp985.informatica.com:1521/pam12cr2";
//    private static final String url = "jdbc:driver://hostname:port/dbName?user=userName&password=password";
    private static final String url = "jdbc:driver://aws-simplified.c4rxgpomdbzu.us-east-1.rds.amazonaws.com:3306/student";
//    private static final String url = "jdbc:driver://0.0.0.0:0/student?user=admin&password=adminroot";

    private static final String username = "admin";
    private static final String password = "adminroot";

    public List<String> getTables() {
        List<String> tables = new ArrayList<>();
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
//            Connection connection = DriverManager.getConnection(url, username, password);
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
//            String QUERY = "select table_name from tabs";
//            String QUERY = "SELECT firstName FROM Student;";
            String QUERY = "select * from user_tables;";
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
}
