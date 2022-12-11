package com.dbmananger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RohitDB {
    private static final String url = "jdbc:postgresql://ec2-54-147-36-107.compute-1.amazonaws.com:5432/d2vm4bajrt6mvk";
    private static final String username = "nqvdewriwdctuf";
    private static final String password = "afbdb343b1ff55ecec69cc2ca09dc3f8b8dba4535dd033067cce047496e4b9cd";

    public List<String> getTables() {
        List<String> tables = new ArrayList<>();
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
//            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
//            String QUERY = "select table_name from tabs";
            String QUERY = "SELECT firstName FROM Student;";
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
