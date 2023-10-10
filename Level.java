import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * The level class of the game.
*/
public class Level extends JPanel{
    
    int[][] level;

    private int BLOCK_WIDTH = 50;
    private int BLOCK_HEIGHT = 50;
    
    Level() {
        
        this.level = new int[320][120];
        for (int x = 0; x < this.level.length; x++) {
            for (int y = 0; y < this.level[0].length; y++) {
                if (y == 0) {
                    this.level[x][y] = 1;
                } else if (y == 5 && x > 4) {
                    this.level[x][y] = 1;
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int x = 0; x < level.length; x++) {
            for (int y = 0; y < level[0].length; y++) {
                if (level[x][y] == 1) { // check if the coordiante is a block
                    // draw block the block in the JPanel
                    Block block = new Block();
                    g.setColor(block.color);
                    g.fillRect(x * BLOCK_WIDTH, y * BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
                    
                }
                if (level[x][y] == 2) {
                    // draw player
                }

            }
        }
    }
}
