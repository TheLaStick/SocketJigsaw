package objects;

public class Figure {
    boolean[][] coordinates = new boolean[GameUtils.FIGURES_GRID_PANE_HEIGHT]
            [GameUtils.FIGURES_GRID_PANE_WIDTH];

    public Figure(int type) {
        GameUtils.FigureType figureType = GameUtils.FigureType.generate(type);
        switch (figureType) {
            case HORSE_MOVE_RIGHT -> {
                coordinates[0][0] = true;
                coordinates[0][1] = true;
                coordinates[0][2] = true;
                coordinates[1][0] = true;
            }
            case HORSE_MOVE_LEFT -> {
                coordinates[0][0] = true;
                coordinates[0][1] = true;
                coordinates[1][1] = true;
                coordinates[2][1] = true;
            }
        }
    }
}
