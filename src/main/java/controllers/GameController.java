package controllers;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import objects.Figure;
import objects.GameUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.Timer;

public class GameController {

    @FXML
    private Button endGameButton;

    @FXML
    private Label figureNameLabel;

    @FXML
    private GridPane figuresGridPane;

    @FXML
    private GridPane newFigureGridPane;

    private Figure currentFigure;
    private Rectangle[][] fieldRectangles;
    private Rectangle[][] newFigureRectangles;
    private int moveCount;
    private long time;

    @FXML
    void onFiguresGridPaneDragDropped(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        GridPane pane = (GridPane) event.getGestureTarget();

        Rectangle rectangle = (Rectangle) event.getPickResult().getIntersectedNode();
        if (canFigureFitGridPane(figuresGridPane, currentFigure,
                pane.getRowIndex(rectangle), pane.getColumnIndex(rectangle))) {
            setRectanglesFillByFigureColor(fieldRectangles, currentFigure,
                    pane.getRowIndex(rectangle), pane.getColumnIndex(rectangle));
            updateNewFigureGridPane();
        }
    }

    @FXML
    void onNewFigureGridPaneDragDetected(MouseEvent event) {
        Dragboard dragboard = newFigureGridPane.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();

        content.putString("");
        dragboard.setContent(content);
        event.consume();
    }

    @FXML
    void onendGameButtonClick(ActionEvent event) {
        updateNewFigureGridPane();
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
    }

    private void updateNewFigureGridPane() {
        for (int i = 0; i < newFigureRectangles.length; ++i) {
            for (int j = 0; j < newFigureRectangles[0].length; ++j) {
                newFigureRectangles[i][j].setFill(GameUtils.EMPTY_CELL_COLOR);
            }
        }
        Figure generatedFigure = Figure.generateRandomFigure();
        setRectanglesFillByFigureColor(newFigureRectangles, generatedFigure, 0, 0);
        this.currentFigure = generatedFigure;
        figureNameLabel.setText(generatedFigure.getName());
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
                        j + y >= fieldRectangles[0].length) {
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
