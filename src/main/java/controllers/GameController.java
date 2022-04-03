package controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import objects.Figure;
import objects.GameUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.io.IOException;

public class GameController {
    @FXML
    private Button endGameButton;

    @FXML
    private Label timeLabel;

    @FXML
    private GridPane figuresGridPane;

    @FXML
    private GridPane newFigureGridPane;

    private Figure currentFigure;
    private Rectangle[][] fieldRectangles;
    private Rectangle[][] newFigureRectangles;
    private int rowIndex;
    private int columnIndex;
    private int moveCount;
    private long time;
    private Timeline timeline;

    @FXML
    void onFiguresGridPaneDragDropped(DragEvent event) {
        Rectangle rectangle = (Rectangle) event.getPickResult().getIntersectedNode();
        if (canFigureFitGridPane(figuresGridPane, currentFigure,
                GridPane.getRowIndex(rectangle) - rowIndex, GridPane.getColumnIndex(rectangle) - columnIndex)) {
            setRectanglesFillByFigureColor(fieldRectangles, currentFigure,
                    GridPane.getRowIndex(rectangle) - rowIndex, GridPane.getColumnIndex(rectangle) - columnIndex);
            updateNewFigureGridPane();
            ++moveCount;
        }
    }

    @FXML
    void onNewFigureGridPaneDragDetected(MouseEvent event) {
        Dragboard dragboard = newFigureGridPane.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();

        Node node = event.getPickResult().getIntersectedNode();
        if (node instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) node;
            this.rowIndex = GridPane.getRowIndex(rectangle);
            this.columnIndex = GridPane.getColumnIndex(rectangle);

            // это костыль я знаю, но без этого не работает
            content.putString("");

            dragboard.setContent(content);
            event.consume();
        }
    }


    @FXML
    void oneEndGameButtonClick(ActionEvent event) throws IOException {
        timeline.stop();
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("result-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), GameUtils.RESULT_WINDOW_WIDTH, GameUtils.RESULT_WINDOW_HEIGHT);
        ResultsController controller = fxmlLoader.getController();

        controller.showGameInfo(moveCount, time);

        Stage stage = new Stage();

        stage.setTitle("New Person");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();


        if (controller.isGameNew()) {
            updateNewFigureGridPane();
            clearRectangles(fieldRectangles);
            initializeTimeline();
            moveCount = 0;
        } else {
            Stage currentStage = (Stage) endGameButton.getScene().getWindow();
            currentStage.close();
        }

    }

    @FXML
    void onFiguresGridPaneDragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.ANY);
    }


    @FXML
    public void initialize() {
        fieldRectangles = initializeRectangles(GameUtils.FIGURES_GRID_PANE_WIDTH, GameUtils.GAME_FIELD_HEIGHT,
                GameUtils.CELL_LENGTH, GameUtils.EMPTY_CELL_COLOR);
        newFigureRectangles = initializeRectangles(GameUtils.NEW_FIGURES_GRID_PANE_WIDTH, GameUtils.NEW_FIGURES_GRID_PANE_HEIGHT,
                GameUtils.CELL_LENGTH, GameUtils.EMPTY_CELL_COLOR);

        initializeGridPane(figuresGridPane, GameUtils.FIGURES_GRID_PANE_WIDTH, GameUtils.GAME_FIELD_HEIGHT,
                GameUtils.CELL_LENGTH, GameUtils.CELL_GAP_LENGTH, fieldRectangles);
        initializeGridPane(newFigureGridPane, GameUtils.NEW_FIGURES_GRID_PANE_WIDTH, GameUtils.NEW_FIGURES_GRID_PANE_HEIGHT,
                GameUtils.CELL_LENGTH, GameUtils.CELL_GAP_LENGTH, newFigureRectangles);

        updateNewFigureGridPane();
        rowIndex = 0;
        columnIndex = 0;
        moveCount = 0;

        initializeTimeline();
    }

    private void initializeTimeline() {
        time = 0;
        timeLabel.setText(String.valueOf(time));
        this.timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
            ++time;
            timeLabel.setText(String.valueOf(time));
        }));
        timeline.setCycleCount(1);
        timeline.setCycleCount(Animation.INDEFINITE);

        timeline.play();
    }

    private void clearRectangles(Rectangle[][] rectangles) {
        for (int i = 0; i < rectangles.length; ++i) {
            for (int j = 0; j < rectangles[0].length; ++j) {
                rectangles[i][j].setFill(GameUtils.EMPTY_CELL_COLOR);
            }
        }
    }

    private void updateNewFigureGridPane() {
        clearRectangles(newFigureRectangles);
        Figure generatedFigure = Figure.generateRandomFigure();
        setRectanglesFillByFigureColor(newFigureRectangles, generatedFigure, 0, 0);
        this.currentFigure = generatedFigure;
    }

    private Rectangle[][] initializeRectangles(int width, int height, int cellLength, Color color) {
        Rectangle[][] rectangles = new Rectangle[width][height];
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                Rectangle rectangle = new Rectangle(cellLength, cellLength, color);
                rectangles[i][j] = rectangle;
            }
        }
        return rectangles;
    }

    private void initializeGridPane(GridPane pane, int width, int height,
                                    int cellLength, int cellGapLength,
                                    Rectangle[][] rectangles) {
        pane.getColumnConstraints().clear();
        pane.getRowConstraints().clear();

        for (int i = 0; i < width; ++i) {
            pane.getColumnConstraints().add(new ColumnConstraints(cellLength));
        }
        for (int i = 0; i < height; ++i) {
            pane.getRowConstraints().add(new RowConstraints(cellLength));
        }

        pane.setHgap(cellGapLength);
        pane.setVgap(cellGapLength);

        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                Rectangle rectangle = rectangles[i][j];
                pane.add(rectangle, i, j);
            }
        }
    }

    private boolean canFigureFitGridPane(GridPane pane, Figure figure, int x, int y) {
        boolean[][] coordinates = figure.getCoordinates();
        for (int i = 0; i < coordinates.length; ++i) {
            for (int j = 0; j < coordinates[0].length; ++j) {
                // Если мы вышли за пределы поля
                if (i + x >= fieldRectangles.length ||
                        i + x < 0 ||
                        j + y >= fieldRectangles[0].length ||
                        j + y < 0) {
                    // то проверяем, есть ли за этим полем части передвигаемой фигуры
                    if (coordinates[i][j]) {
                        return false;
                    }
                    continue;
                }

                Rectangle rectangle = fieldRectangles[y + j][x + i];
                if (coordinates[i][j] && rectangle.getFill() == GameUtils.FIGURE_COLOR) {
                    return false;
                }
            }
        }

        return true;
    }

    private void setRectanglesFillByFigureColor(Rectangle[][] rectangles, Figure figure, int x, int y) {
        boolean[][] coordinates = figure.getCoordinates();
        for (int i = 0; i < coordinates.length; ++i) {
            for (int j = 0; j < coordinates[0].length; ++j) {
                if (x + i < rectangles.length && y + j < rectangles[0].length
                        && coordinates[i][j]) {
                    Rectangle rectangle = rectangles[y + j][x + i];
                    rectangle.setFill(GameUtils.FIGURE_COLOR);
                }
            }
        }
    }
}
