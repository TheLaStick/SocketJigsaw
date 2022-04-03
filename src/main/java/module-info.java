module com.example.dorzhiev_donir_208_hw5 {
    requires javafx.controls;
    requires javafx.fxml;

    opens thelastick.jigsaw.controllers to javafx.fxml;
    exports thelastick.jigsaw.controllers;
    exports thelastick.jigsaw;
}