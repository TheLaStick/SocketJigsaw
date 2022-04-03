package objects;

import javafx.scene.paint.Color;

import java.util.Random;

public class GameUtils {
    public static final int NEW_FIGURES_GRID_PANE_WIDTH = 3;
    public static final int NEW_FIGURES_GRID_PANE_HEIGHT = 3;
    public static final int FIGURES_GRID_PANE_WIDTH = 9;
    public static final int GAME_FIELD_HEIGHT = 9;

    public static final int CELL_LENGTH = 60;
    public static final int CELL_GAP_LENGTH = 2;

    public static final Random RANDOM = new Random();
    public static final Color FIGURE_COLOR = Color.BLUE;
    public static final Color EMPTY_CELL_COLOR = Color.DIMGRAY;

    enum FigureType {
        HORSE_MOVE_RIGHT,
        HORSE_MOVE_LEFT,
        ZIG_ZAG_RIGHT,
        ZIG_ZAG_LEFT,
        BIG_L,
        BIG_T,
        STICK,
        DOT,
        LITTLE_L,
        LITTLE_T;

        static public FigureType generate(int type) {
            switch (type) {
                case 0 -> {
                    return HORSE_MOVE_RIGHT;
                }
                case 1 -> {
                    return HORSE_MOVE_LEFT;
                }
                case 2 -> {
                    return ZIG_ZAG_RIGHT;
                }
                case 3 -> {
                    return ZIG_ZAG_LEFT;
                }
                case 4 -> {
                    return BIG_L;
                }
                case 5 -> {
                    return BIG_T;
                }
                case 6 -> {
                    return STICK;
                }
                case 7 -> {
                    return DOT;
                }
                case 8 -> {
                    return LITTLE_L;
                }
                default -> {
                    return LITTLE_T;
                }
            }
        }
    }
}
