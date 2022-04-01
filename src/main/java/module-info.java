module com.example.dorzhiev_donir_208_hw5 {
    requires javafx.controls;
    requires javafx.fxml;

    opens controllers to javafx.fxml;
    exports controllers;
}