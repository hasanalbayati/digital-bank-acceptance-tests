package co.wedevx.digitalbank.automation.ui.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static co.wedevx.digitalbank.automation.ui.utils.ConfigReader.getPropertiesValue;

public class DBUtils {

    //method to establish connection with db
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    public static void establishConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    getPropertiesValue("DBank_DB_Url"),
                    getPropertiesValue("DBank_DB_username"),
                    getPropertiesValue("DBank_DB_password"));

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    // a method that can dynamically send select statement
    // and returns a list of Map of all columns
    public static List<Map<String, Object>> runSQLSelectQuery(String sqlQuery){

        List<Map<String,Object>> dbResultList = new ArrayList<>();
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

            //getMetaData returns info about your info
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            while(resultSet.next()) {
                Map<String, Object> rowMap = new HashMap<>();
                for(int col=1; col<= columnCount; col++){
                    rowMap.put(resultSetMetaData.getColumnName(col), resultSet.getObject(col));
                }
                dbResultList.add(rowMap);

            }

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dbResultList;
    }


    // create a method that inserts into db
    //return the number of rows updated or 0 when action is not taken

    //update a query
    public static  int runSQLUpdateQuery(String sqlQuery){
        int rowsAffected = 0;
        try{
            statement= connection.createStatement();
            rowsAffected = statement.executeUpdate(sqlQuery);
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return  rowsAffected;
    }



    //close a connection
    public static void closeConnection(){
        try{
            if(resultSet !=null){
                resultSet.close();
            }
            if(statement !=null){
                statement.close();
            }
            if(connection !=null){
                connection.close();
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



}