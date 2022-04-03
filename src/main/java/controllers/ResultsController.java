package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import objects.GameUtils;

import java.util.concurrent.TimeUnit;

public class ResultsController {
    boolean isGameNew;

    @FXML
    private Button exitButton;

    @FXML
    private Button newGameButton;

    @FXML
    private Label resultScoreLabel;

    @FXML
    private Label resultTimeLabel;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label timeLabel;

    public boolean isGameNew() {
        return isGameNew;
    }

    @FXML
    void onExitButtonClick(ActionEvent event) {
        this.isGameNew = false;
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void onNewGameButtonClick(ActionEvent event) {
        this.isGameNew = true;
        Stage currentStage = (Stage) newGameButton.getScene().getWindow();
        currentStage.close();
    }

    /**
     * Отображает количество ходов и время игры в этом окне.
     * @param moveCount
     * @param time
     */
    public void showGameInfo(int moveCount, long time) {
        resultScoreLabel.setText(String.valueOf(moveCount));
        resultTimeLabel.setText(String.valueOf(time));
    }

}

