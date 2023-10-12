import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * The level class of the game.
*/
public class Level extends JPanel {
    
    int[][] level; // the level is a 2D array of integers, evey coordinate is a block or not a block

    private int BLOCK_WIDTH = 50; // Width of a block in pixels
    private int BLOCK_HEIGHT = 50; //bHeight of a block in pixels

    int x0 = 0; //var to scroll the level

    private Player player;
    

    /*
     * Constructor for objects of class Level.
     */
    public Level(Player player) {
        this.player = player;
        
        // determine dimentions of the level: 32 blocks wide, 12 blocks high
        // The screen is 16 blocks wide, 12 blocks high, so level 1 is 2 screens wide.
        this.level = new int[32][12];
        
        // fill the level with blocks: 1 = block, 0 = no block 
        // This level now consists of a floor and a platform on the right side.
        for (int x = 0; x < this.level.length; x++) {
            for (int y = 0; y < this.level[0].length; y++) {
                if (y == 0) {
                    this.level[x][y] = 1;
                } else if (y == 5 && x > 4 && x < 10) {
                    this.level[x][y] = 1;
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw the level
        for (int x = x0; x < level.length; x++) { 
            for (int y = 0; y < level[0].length; y++) { 

                if (level[x][y] == 1) { // check if the coordinate is a block
                    // draw block the block in the JPanel
                    Block block = new Block(); //LATER: Is this nececary?
                    g.setColor(block.color); // set the color of the block LATER: use sprites
                    g.fillRect((x - x0) * BLOCK_WIDTH, ((level[0].length - 2) - y) //Reverse y axis
                        * BLOCK_HEIGHT, BLOCK_WIDTH, 
                        BLOCK_HEIGHT); // draw the block: LATER: draw the sprite for the block
                    
                }

                /*
                 * LATER: draw other objects in the level by checking if the coordinate is a different number.
                 */

            }
        }
        ImageIcon image = new ImageIcon("Assets/sprite.png");
        g.drawImage(image.getImage(), player.x, player.y, null); 
        //TODO: update the position to be relative to the center of the player
    }
}
