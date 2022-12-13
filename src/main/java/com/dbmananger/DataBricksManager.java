package com.dbmananger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBricksManager {
    private static final String url = "jdbc:spark://adb-5367625637145207.7.azuredatabricks.net:443/default;transportMode=http;ssl=1;AuthMech=3;httpPath=/sql/1.0/warehouses/3393612e8cb11a78;/default";

    private static final String username = "token";
    private static final String password = "";

    public List<String> getTables() {
        List<String> tables = new ArrayList<>();
        try {
            Class.forName("com.simba.spark.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url);
//            Connection connection = DriverManager.getConnection(url, username, password);
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
}
