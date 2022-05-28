module Jigsaw {
    requires javafx.controls;
    requires javafx.fxml;

    opens thelastick.UI.controllers to javafx.fxml;
    exports thelastick.UI.controllers;
    exports thelastick.UI;
}