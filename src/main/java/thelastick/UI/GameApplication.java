package thelastick.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import thelastick.jigsaw.GameUtils;

import java.io.IOException;

public class GameApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("controllers/game-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), GameUtils.GAME_WINDOW_WIDTH, GameUtils.GAME_WINDOW_HEIGHT);
        stage.setTitle("Jigsaw");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}