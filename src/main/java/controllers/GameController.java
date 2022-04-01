package controllers;

import objects.GameUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private Button endGameButton;

    @FXML
    private GridPane figuresGridPane;

    @FXML
    private GridPane newFigureGridPane;

    @FXML
    void onFiguresGridPaneDragDropped(DragEvent event) {

    }

    @FXML
    void onNewFigureGridPaneDragDetected(MouseEvent event) {

    }

    @FXML
    void onendGameButtonClick(ActionEvent event) {

    }

    @FXML
    void onfiguresGridPaneDragOver(DragEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < GameUtils.GAME_FIELD_WIDTH; i++) {
            figuresGridPane.getColumnConstraints().add(new ColumnConstraints(67));
        }

        for (int i = 0; i < GameUtils.GAME_FIELD_HEIGHT; i++) {
            figuresGridPane.getRowConstraints().add(new RowConstraints(67));
        }

        figuresGridPane.setHgap(1);
        figuresGridPane.setVgap(1);

        for (int i = 0; i < GameUtils.FIGURES_GRID_PANE_WIDTH; ++i) {
            newFigureGridPane.getColumnConstraints().add(new ColumnConstraints(67));
        }
        for (int i = 0; i < GameUtils.FIGURES_GRID_PANE_WIDTH; ++i) {
            newFigureGridPane.getRowConstraints().add(new RowConstraints(67));
        }

        newFigureGridPane.setHgap(1);
        newFigureGridPane.setVgap(1);
    }
}
