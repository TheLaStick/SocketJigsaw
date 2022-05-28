module Jigsaw {
    requires javafx.controls;
    requires javafx.fxml;

    opens thelastick.UI.controllers.server to javafx.fxml;
    opens thelastick.UI.controllers to javafx.fxml;
    exports thelastick.UI.controllers.server;
    exports thelastick.UI.controllers.client;

    exports thelastick.UI;
    opens thelastick.UI to javafx.fxml;
    opens thelastick.UI.controllers.client to javafx.fxml;
}