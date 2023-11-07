package com.example.csd214deepak_dangal.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class TestDAO {
    ObservableList<Test> list = FXCollections.observableArrayList();

    public ObservableList<Test> populateTable() {
// Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214lab1";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl,
                dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM tbl_test";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
// Populate the table with data from the database
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                list.add(new Test(id,name));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void InsertData(String name) {
// Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214lab1";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl,
                dbUser,
                dbPassword)) {
// Execute a SQL query to insert data in the database
            String query = "INSERT INTO `tbl_test`( `name`) VALUES ('"+name+"')";
            Statement statement = connection.createStatement();
            statement.execute(query);
// Populate the table with data from the database
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void UpdateData(String name,int id) {
// Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214lab1";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl,
                dbUser,
                dbPassword)) {
// Execute a SQL query to Upataing data in the database
            String query = "UPDATE `tbl_test` SET`name`='"+name+"' WHERE id ='"+id+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);
// Populate the table with data from the database
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void DeleteData(int id) {
// Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214lab1";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl,
                dbUser,
                dbPassword)) {
// Execute a SQL query to Upataing data in the database
            String query = "DELETE FROM `tbl_test` WHERE id ='"+id+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);
// Populate the table with data from the database
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
