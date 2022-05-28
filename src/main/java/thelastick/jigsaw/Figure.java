package thelastick.jigsaw;

import java.util.Arrays;
import java.util.Objects;

public class Figure {
    private static int FIGURE_TYPES_COUNT = 10;

    private String name;

    boolean[][] coordinates = new boolean[GameUtils.NEW_FIGURES_GRID_PANE_HEIGHT]
            [GameUtils.NEW_FIGURES_GRID_PANE_WIDTH];

    public boolean[][] getCoordinates() {
        return coordinates;
    }

    public String getName() {
        return name;
    }

    /**
     * Конструктор для фигуры
     * @param type Тип фигуры
     * @param rotationCount Количество поворотов
     */
    public Figure(int type, int rotationCount) {
        GameUtils.FigureType figureType = GameUtils.FigureType.generate(type);
        this.name = figureType.toString();
        switch (figureType) {
            case HORSE_MOVE_RIGHT -> {
                coordinates[0][0] = true;
                coordinates[0][1] = true;
                coordinates[0][2] = true;
                coordinates[1][0] = true;
                rotateFigureByAngle(rotationCount);
            }
            case HORSE_MOVE_LEFT -> {
                coordinates[0][0] = true;
                coordinates[1][0] = true;
                coordinates[1][1] = true;
                coordinates[1][2] = true;
                rotateFigureByAngle(rotationCount);
            }
            case ZIG_ZAG_RIGHT -> {
                coordinates[0][1] = true;
                coordinates[0][2] = true;
                coordinates[1][0] = true;
                coordinates[1][1] = true;
                rotateFigureByAngle(rotationCount);
            }
            case ZIG_ZAG_LEFT -> {
                coordinates[0][0] = true;
                coordinates[0][1] = true;
                coordinates[1][1] = true;
                coordinates[1][2] = true;
                rotateFigureByAngle(rotationCount);
            }
            case BIG_L -> {
                coordinates[0][0] = true;
                coordinates[0][1] = true;
                coordinates[0][2] = true;
                coordinates[1][2] = true;
                coordinates[2][2] = true;
                rotateFigureByAngle(rotationCount);
            }
            case BIG_T -> {
                coordinates[0][0] = true;
                coordinates[1][0] = true;
                coordinates[2][0] = true;
                coordinates[1][1] = true;
                coordinates[1][2] = true;
                rotateFigureByAngle(rotationCount);
            }
            case STICK -> {
                coordinates[1][0] = true;
                coordinates[1][1] = true;
                coordinates[1][2] = true;
                rotateFigureByAngle(rotationCount);
            }
            case DOT -> {
                coordinates[1][1] = true;
            }
            case LITTLE_L -> {
                coordinates[0][0] = true;
                coordinates[0][1] = true;
                coordinates[1][1] = true;
                rotateFigureByAngle(rotationCount);
            }
            case LITTLE_T -> {
                coordinates[0][0] = true;
                coordinates[1][0] = true;
                coordinates[2][0] = true;
                coordinates[1][1] = true;
                rotateFigureByAngle(rotationCount);
            }
        }
    }

    /**
     * Поворацивает фигуру на 90 градусов заданное количество раз
     * @param rotationCount Количество поворотов
     */
    public void rotateFigureByAngle(int rotationCount) {
        for (int i = 0; i < rotationCount; i++) {
            coordinates = rotateMatix(coordinates);
        }
    }

    /**
     * Поворачивает матрицу на 90 градусов
     * @param matrix Текущая матрица
     * @return Повернутая на 90 градусов матрица
     */
    public static boolean[][] rotateMatix(boolean[][] matrix) {
        boolean[][] rotatedMatrix = new boolean[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                rotatedMatrix[i][j] = matrix[matrix.length - j - 1][i];
            }
        }

        return rotatedMatrix;
    }

    /**
     * Генерирует случайную фигуру со случайным поворотом
     * @return Случайная фигура со случайным поворотом
     */
    public static Figure generateRandomFigure() {
        return new Figure(GameUtils.RANDOM.nextInt(FIGURE_TYPES_COUNT), GameUtils.RANDOM.nextInt(4));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Figure figure = (Figure) o;
        return Objects.equals(name, figure.name) && Arrays.equals(coordinates, figure.coordinates);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name);
        result = 31 * result + Arrays.hashCode(coordinates);
        return result;
    }
}
