package com.dbmananger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleDBManager {
    // jdbc.url=jdbc:oracle:thin:@//HOSTNAME:PORT/SERVICE_NAME
    private static final String url = "jdbc:oracle:thin:@//ivlhdp985.informatica.com:1521/pam12cr2";
    private static final String username = "shashi01";
    private static final String password = "shashi01";

    public List<Integer> getActivePID() {
        List<Integer> ids = new ArrayList<>();
        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String QUERY = "SELECT ORDERID FROM SHASHI01.ORDERS";
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                int pid = resultSet.getInt(1);
                ids.add(pid);
            }
            statement.close();
            connection.close();
            return ids;
        } catch (Exception e) {
            System.out.println("ERROR: Not able to Connect to Oracle DB Exception" + e);
            throw new RuntimeException(e);
        }
    }

    public List<String> getTables() {
        List<String> tables = new ArrayList<>();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            //            String QUERY = "select table_name from tabs";
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
            System.out.println("ERROR: Not able to Connect to Oracle DB Exception" + e);
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
//            System.out.println(connection.getDa);
//            ResultSet rs = metaData.getSchemas(catalogName, null);
            ResultSet rs = metaData.getColumns("pam12cr2", null, "ORDERS", "%");
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString("COLUMN_NAME") + " " + rs.getString("TYPE_NAME"));
            }

            connection.close();
            return tables;
        } catch (Exception e) {
            System.out.println("ERROR: Not able to Connect to Oracle DB Exception" + e);
            throw new RuntimeException(e);
        }
    }

    public void print(String dbName, String tableName) throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection(url, username, password);
        populateObjectDetails(connection, dbName, tableName);
    }
    public void populateObjectDetails(Connection connection,  String dbName, String tableName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();

        ResultSet resultSet = metaData.getColumns(dbName, null, tableName, "%");
        while (resultSet.next()) {
            getAllMeta(resultSet);
//            String columnName = resultSet.getString("COLUMN_NAME");
//            String columnType= resultSet.getString("TYPE_NAME");
////            Integer precision = resultSet.getInt("DATA_PRECISION");
//            String precision = resultSet.getString("DATA_SCALE");
//            System.out.printf("%s, %s, %s %n", columnName, columnType, precision);
        }
    }
    public List<String> getAllMeta(ResultSet resultSet) throws SQLException {
        List<String> metas = new ArrayList<>();
        try {
            for (int i=0; i<resultSet.getMetaData().getColumnCount(); i++) {
                try {
                    String catalogName = resultSet.getMetaData().getCatalogName(i);
                    String columnName = resultSet.getMetaData().getColumnName(i);
                    int columnType = resultSet.getMetaData().getColumnType(i);
                    System.out.printf("%s, %s, %s %n", catalogName, columnName, columnType);
                }
                catch (Exception e) {

                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return metas;
    }

}
