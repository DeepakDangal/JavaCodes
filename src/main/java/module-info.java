module com.example.csd214deepak_dangal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.csd214deepak_dangal to javafx.fxml;
    exports com.example.csd214deepak_dangal;
    exports com.example.csd214deepak_dangal.Model;
    opens com.example.csd214deepak_dangal.Model to javafx.fxml;


}