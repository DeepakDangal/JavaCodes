package com.example.csd214deepak_dangal.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class AppointmentDAO {
    ObservableList<Appointment> list = FXCollections.observableArrayList();

    public ObservableList<Appointment> populateTable() {
// Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214lab1";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl,
                dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM tbl_appointment";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
// Populate the table with data from the database
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String doctor = resultSet.getString("doctor");
                int room = resultSet.getInt("room");
                list.add(new Appointment(id,name,doctor,room));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void InsertData(String name,String doctor,int room) {
// Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214lab1";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl,
                dbUser,
                dbPassword)) {
// Execute a SQL query to insert data in the database
            String query = "INSERT INTO `tbl_appointment`( `name`, `doctor`,`room`) VALUES ('"+name+"','"+doctor+"','"+room+"')";
            Statement statement = connection.createStatement();
            statement.execute(query);
// Populate the table with data from the database
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void UpdateData(int id,String name,String doctor, int room) {
// Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214lab1";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl,
                dbUser,
                dbPassword)) {
// Execute a SQL query to Upataing data in the database
            String query = "UPDATE `tbl_appointment` SET`name`='"+name+"',`doctor`='"+doctor+"',`room`='"+room+"' WHERE id ='"+id+"'";
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
            String query = "DELETE FROM `tbl_appointment` WHERE id ='"+id+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);
// Populate the table with data from the database
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Appointment getDataById(int id) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214lab1";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "SELECT * FROM tbl_appointment WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int appointmentId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String doctor = resultSet.getString("doctor");
                int room = resultSet.getInt("room");
                return new Appointment(appointmentId, name, doctor, room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
