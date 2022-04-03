package thelastick.jigsaw.controllers;

import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;
import thelastick.jigsaw.objects.GameUtils;
import static org.junit.jupiter.api.Assertions.*;

public class GameControllerTest {
    @Test
    public void clearRectanglesTableOnFullyNotClearRectanglesTableShouldMakeTableClear(){
        Rectangle[][] table = new Rectangle[9][9];
        for (int i = 0; i < table.length; ++i) {
            for (int j = 0; j < table[0].length; ++j) {
                table[i][j] = new Rectangle();
                table[i][j].setFill(GameUtils.FIGURE_COLOR);
            }
        }

        GameController.clearRectanglesTable(table);

        boolean result = true;
        for (int i = 0; i < table.length; ++i) {
            for (int j = 0; j < table[0].length; ++j) {
                result = result && (table[i][j].getFill() == GameUtils.EMPTY_CELL_COLOR);
            }
        }

        assertTrue(result);
    }
}
