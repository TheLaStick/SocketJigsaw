package thelastick.jigsaw.objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FigureTest {
    @Test
    public void rotateMatrixOnFigureHorseMoveRightShouldReturnFigureHorseMoveRightRotatedBy90Degrees() {
        Figure figure = new Figure(0, 0);

        boolean[][] figureTable = Figure.rotateMatix(figure.getCoordinates());
        boolean[][] expectingResult = new boolean[GameUtils.NEW_FIGURES_GRID_PANE_HEIGHT]
                [GameUtils.NEW_FIGURES_GRID_PANE_WIDTH];

        expectingResult[0][0] = false;
        expectingResult[0][1] = true;
        expectingResult[0][2] = true;
        expectingResult[1][0] = false;
        expectingResult[1][1] = false;
        expectingResult[1][2] = true;
        expectingResult[2][0] = false;
        expectingResult[2][1] = false;
        expectingResult[2][2] = true;

        boolean result = true;
        for (int i = 0; i < expectingResult.length; i++) {
            for (int j = 0; j < expectingResult.length; j++) {
                result = result && (figureTable[i][j] == expectingResult[i][j]);
            }
        }

        assertTrue(result);
    }

    @Test
    public void rotateFigureByAngleOnFigureHorseMoveRightAndRotationCount3ShouldReturnFigureHorseMoveRightRotatedBy270Degrees() {
        Figure figure = new Figure(0, 0);

        figure.rotateFigureByAngle(3);
        boolean[][] figureTable = figure.getCoordinates();
        boolean[][] expectingResult = new boolean[GameUtils.NEW_FIGURES_GRID_PANE_HEIGHT]
                [GameUtils.NEW_FIGURES_GRID_PANE_WIDTH];

        expectingResult[0][0] = true;
        expectingResult[0][1] = false;
        expectingResult[0][2] = false;
        expectingResult[1][0] = true;
        expectingResult[1][1] = false;
        expectingResult[1][2] = false;
        expectingResult[2][0] = true;
        expectingResult[2][1] = true;
        expectingResult[2][2] = false;

        boolean result = true;
        for (int i = 0; i < expectingResult.length; i++) {
            for (int j = 0; j < expectingResult.length; j++) {
                result = result && (figureTable[i][j] == expectingResult[i][j]);
            }
        }

        assertTrue(result);
    }
}
