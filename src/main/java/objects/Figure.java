package objects;

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

    public Figure(int type, int rotation) {
        GameUtils.FigureType figureType = GameUtils.FigureType.generate(type);
        this.name = figureType.toString();
        switch (figureType) {
            case HORSE_MOVE_RIGHT -> {
                coordinates[0][0] = true;
                coordinates[0][1] = true;
                coordinates[0][2] = true;
                coordinates[1][0] = true;
                rotateFigureByAngle(rotation);
            }
            case HORSE_MOVE_LEFT -> {
                coordinates[0][0] = true;
                coordinates[1][0] = true;
                coordinates[1][1] = true;
                coordinates[1][2] = true;
                rotateFigureByAngle(rotation);
            }
            case ZIG_ZAG_RIGHT -> {
                coordinates[0][1] = true;
                coordinates[0][2] = true;
                coordinates[1][0] = true;
                coordinates[1][1] = true;
                rotateFigureByAngle(rotation);
            }
            case ZIG_ZAG_LEFT -> {
                coordinates[0][0] = true;
                coordinates[0][1] = true;
                coordinates[1][1] = true;
                coordinates[1][2] = true;
                rotateFigureByAngle(rotation);
            }
            case BIG_L -> {
                coordinates[0][0] = true;
                coordinates[0][1] = true;
                coordinates[0][2] = true;
                coordinates[1][2] = true;
                coordinates[2][2] = true;
                rotateFigureByAngle(rotation);
            }
            case BIG_T -> {
                coordinates[0][0] = true;
                coordinates[1][0] = true;
                coordinates[2][0] = true;
                coordinates[1][1] = true;
                coordinates[1][2] = true;
                rotateFigureByAngle(rotation);
            }
            case STICK -> {
                coordinates[1][0] = true;
                coordinates[1][1] = true;
                coordinates[1][2] = true;
                rotateFigureByAngle(rotation);
            }
            case DOT -> {
                coordinates[1][1] = true;
            }
            case LITTLE_L -> {
                coordinates[0][0] = true;
                coordinates[0][1] = true;
                coordinates[1][1] = true;
                rotateFigureByAngle(rotation);
            }
            case LITTLE_T -> {
                coordinates[0][0] = true;
                coordinates[1][0] = true;
                coordinates[2][0] = true;
                coordinates[1][1] = true;
                rotateFigureByAngle(rotation);
            }
        }
    }

    private boolean[][] rotateFigure(boolean[][] matrix) {
        boolean[][] rotatedMatrix = new boolean[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                rotatedMatrix[i][j] = matrix[matrix.length - j - 1][i];
            }
        }

        return rotatedMatrix;
    }

    private void rotateFigureByAngle(int angle) {
        for (int i = 0; i < angle; i++) {
            coordinates = rotateFigure(coordinates);
        }
    }

    public static Figure generateRandomFigure() {
        return new Figure(GameUtils.RANDOM.nextInt(FIGURE_TYPES_COUNT), GameUtils.RANDOM.nextInt(4));
    }
}
