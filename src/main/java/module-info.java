module com.example.sos_game {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sos_game to javafx.fxml;
    exports com.example.sos_game;
}