package thelastick.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import thelastick.jigsaw.GameUtils;

public class ServerApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ServerApplication.class.
                getResource("controllers/serverMainMenu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),
                GameUtils.RESULT_WINDOW_WIDTH, GameUtils.RESULT_WINDOW_HEIGHT);
        stage.setTitle("Jigsaw");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
