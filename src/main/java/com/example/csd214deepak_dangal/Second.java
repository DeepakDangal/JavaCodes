package com.example.csd214deepak_dangal;

import com.example.csd214deepak_dangal.Model.Appointment;
import com.example.csd214deepak_dangal.Model.AppointmentDAO;
import com.example.csd214deepak_dangal.Model.Test;
import com.example.csd214deepak_dangal.Model.TestDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Second implements Initializable {
    @FXML
    private Label welcomeText;

    public TextField iname;


    public TextField iid;

    @FXML
    private TableView<Test> tableView;
    @FXML
    private TableColumn<Test, Integer> id;
    @FXML
    private TableColumn<Test, String> name;

    ObservableList<Test> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableView.setItems(list);
    }

    @FXML
    protected void onHelloButtonClick() {
        TestDAO a = new TestDAO(); // creating object
        list = a.populateTable(); // calling method using object of that class
        tableView.setItems(list);//setting ObservableList list into tableview.

         populateTable();

    }

    public void populateTable() {
// Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214lab1";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM tbl_test";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
// Populate the table with data from the database
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                tableView.getItems().add(new Test(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
public void InsertClick(ActionEvent actionEvent){
    String name= iname.getText();

    TestDAO a = new TestDAO(); // creating object
    a.InsertData(name); // calling method using object of that class and passing data

}
public void UpdateClick(ActionEvent actionEvent){
//Scaning value from text filed
    String name= iname.getText();

    int id= Integer.parseInt(iid.getText());
    TestDAO a = new TestDAO(); // creating object
    a.UpdateData(name,id);
}

public void DeleteClick(ActionEvent actionEvent){
    int id= Integer.parseInt(iid.getText());
    TestDAO a = new TestDAO(); // creating object
    a.DeleteData(id); // calling method using object of that class and deleting data

}



}


