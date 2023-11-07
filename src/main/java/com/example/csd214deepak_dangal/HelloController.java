package com.example.csd214deepak_dangal;

import com.example.csd214deepak_dangal.Model.Appointment;
import com.example.csd214deepak_dangal.Model.AppointmentDAO;
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
import java.util.ResourceBundle;
import java.sql.*;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;

    public TextField iname;

    public TextField idoctor;

    public TextField iroom;

    public TextField iid;

    @FXML
    private TableView<Appointment> tableView;
    @FXML
    private TableColumn<Appointment, Integer> id;
    @FXML
    private TableColumn<Appointment, String> name;
    @FXML
    private TableColumn<Appointment, String> doctor;
    @FXML
    private TableColumn<Appointment, Integer> room;
    ObservableList<Appointment> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        doctor.setCellValueFactory(new PropertyValueFactory<>("doctor"));
        room.setCellValueFactory(new PropertyValueFactory<>("room"));
        tableView.setItems(list);
    }

    @FXML
    protected void onHelloButtonClick() {
        AppointmentDAO a = new AppointmentDAO(); // creating object
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
            String query = "SELECT * FROM appointment";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
// Populate the table with data from the database
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String doctor = resultSet.getString("doctor");
                int room = resultSet.getInt("room");
                tableView.getItems().add(new Appointment(id, name, doctor,room));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
public void InsertClick(ActionEvent actionEvent){
    String name= iname.getText();
    String doctor= idoctor.getText();
    int room= Integer.parseInt(iroom.getText());
    AppointmentDAO a = new AppointmentDAO(); // creating object
    a.InsertData(name,doctor,room); // calling method using object of that class and passing data

}
public void UpdateClick(ActionEvent actionEvent){
//Scaning value from text filed
    String name= iname.getText();
    String doctor= idoctor.getText();
    int room= Integer.parseInt(iroom.getText());
    int id= Integer.parseInt(iid.getText());
    AppointmentDAO a = new AppointmentDAO(); // creating object
    a.UpdateData(id,name,doctor, room);
}

public void DeleteClick(ActionEvent actionEvent){
    int id= Integer.parseInt(iid.getText());
    AppointmentDAO a = new AppointmentDAO(); // creating object
    a.DeleteData(id); // calling method using object of that class and deleting data

}

    public void OpenTest(ActionEvent actionEvent) {

        try {
// Load the FXML file for the second scene
            Parent secondScene =
                    FXMLLoader.load(getClass().getResource("hello-view2.fxml"));
// Create a new stage for the second scene
            Stage secondStage = new Stage();
            secondStage.setTitle("Second Scene");
            secondStage.setScene(new Scene(secondScene));
// Close the first scene's stage
            Stage firstSceneStage = (Stage)
                    tableView.getScene().getWindow();
            firstSceneStage.close();
// Show the second stage
            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void openload(ActionEvent actionEvent){
        try {
            int id = Integer.parseInt(iid.getText());
            AppointmentDAO a = new AppointmentDAO();
            Appointment appointment = a.getDataById(id);
            if (appointment != null) {

                iname.setText(appointment.getName());
                idoctor.setText(appointment.getDoctor());
                iroom.setText(String.valueOf(appointment.getRoom()));
            } else {
                // Handle the case where the ID doesn't exist
                iname.clear();
                idoctor.clear();
                iroom.clear();
            }
        } catch (NumberFormatException e) {
            // Handle invalid ID input
            // You may display an error message or take appropriate action
        }
    }
}


