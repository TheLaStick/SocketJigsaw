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

    public Figure(int type) {
        GameUtils.FigureType figureType = GameUtils.FigureType.generate(type);
        this.name = figureType.toString();
        switch (figureType) {
            case HORSE_MOVE_RIGHT -> {
                coordinates[0][0] = true;
                coordinates[0][1] = true;
                coordinates[0][2] = true;
                coordinates[1][0] = true;
            }
            case HORSE_MOVE_LEFT -> {
                coordinates[0][0] = true;
                coordinates[1][0] = true;
                coordinates[1][1] = true;
                coordinates[1][2] = true;
            }
            case ZIG_ZAG_RIGHT -> {
                coordinates[0][1] = true;
                coordinates[0][2] = true;
                coordinates[1][0] = true;
                coordinates[1][1] = true;
            }
            case ZIG_ZAG_LEFT -> {
                coordinates[0][0] = true;
                coordinates[0][1] = true;
                coordinates[1][1] = true;
                coordinates[1][2] = true;
            }
            case BIG_L -> {
                coordinates[0][0] = true;
                coordinates[0][1] = true;
                coordinates[0][2] = true;
                coordinates[1][2] = true;
                coordinates[2][2] = true;
            }
            case BIG_T -> {
                coordinates[0][0] = true;
                coordinates[1][0] = true;
                coordinates[2][0] = true;
                coordinates[1][1] = true;
                coordinates[1][2] = true;
            }
            case STICK -> {
                coordinates[1][0] = true;
                coordinates[1][1] = true;
                coordinates[1][2] = true;
            }
            case DOT -> {
                coordinates[0][0] = true;
            }
            case LITTLE_L -> {
                coordinates[0][0] = true;
                coordinates[0][1] = true;
                coordinates[1][1] = true;
            }
            case LITTLE_T -> {
                coordinates[0][0] = true;
                coordinates[1][0] = true;
                coordinates[2][0] = true;
                coordinates[1][1] = true;
            }
        }
    }

    public static Figure generateRandomFigure() {
        return new Figure(GameUtils.RANDOM.nextInt(FIGURE_TYPES_COUNT));
    }
}
