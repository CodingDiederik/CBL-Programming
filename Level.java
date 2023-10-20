import java.awt.*;
import javax.swing.*;

/**
 * The level class of the game.
*/
public class Level extends JPanel {
    
    int[][] level; // the level is a 2D array of integers, evey coordinate is a block or not a block

    private int BL_WTH = 50; // Width of a block in pixels
    private int BL_HGT = 50; // Height of a block in pixels


    int x0 = 0; //var to scroll the level

    private Player player;
    public int level_number;
    public boolean isPaused = false;
    
    /**
     * Constructor for objects of class Level.
     */
    public Level(Player player, int level_number) {
        this.player = player;
        this.level_number = level_number;
        
        // determine dimentions of the level: 32 blocks wide, 12 blocks high
        // The screen is 16 blocks wide, 12 blocks high, so level 1 is 2 screens wide.
        this.level = new int[64][12];
        
        // fill the level with platforms and walls: 1 = block, 0 = no block
        for (int x = 0; x < this.level.length; x++) {
            for (int y = 0; y < this.level[0].length; y++) {
                createCollumnBlocks(0, 11, 12);
                createRowBlocks(0, 11, 30);
                createRowBlocks(5, 8, 6);
                createRowBlocks(10, 7, 2);
                createCollumnBlocks(1, 11, 6);
                createCollumnBlocks(10, 11, 6);
                createRowBlocks(17, 5, 4);
                createCollumnBlocks(19, 6, 3);
                createRowBlocks(20, 9, 5);
                createEndLevelDoor(19, 10);

                createEndLevelCollumn(this.level.length - 1, 0, this.level[0].length);
            }
        }
    }

    /**
     * Easily create a row of blocks (platform) in the level.
     */
    void createRowBlocks(int x, int y, int length) {
        for (int r = x; r < x + length; r++) {
            this.level[r][y] = 1;
        }
    }

    /**
     * Easily create a column of blocks (wall) in the level.
     */
    void createCollumnBlocks(int x, int y, int height) {
        for (int c = y; c > y - height; c--) {
            this.level[x][c] = 1;
        }
    }

    void createEndLevelDoor(int x, int y) {
        // create a door at the end of the level
        level[x][y] = 2;
    }

    /**
     * Make a collumn row of blocks at the end of the level.
     */
    void createEndLevelCollumn(int x, int y, int length) {
        for (int r = y; r < y + length; r++) {
            this.level[x][r] = 1;
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw the level
        for (int x = x0 / 50; x < level.length; x++) { 
            for (int y = 0; y < level[0].length; y++) { 

                if (level[x][y] == 1) { // check if the coordinate is a block
                    // draw block the block in the JPanel
                    Block block = new Block(); //LATER: Is this nececary?
                    g.setColor(block.color); // set the color of the block LATER: use sprites
                    g.fillRect((x  * BL_WTH) - x0, y * BL_HGT, BL_WTH, 
                        BL_HGT); // draw the block: LATER: draw the sprite for the block
                //System.out.println("x: " + x + " y: " + y);
                }
                if (level[x][y] == 2) {
                    
                    g.setColor(Color.GREEN);
                    g.fillRect((x * BL_WTH) - x0, y * BL_HGT , BL_WTH, BL_HGT);
                    // draw the door
                    //ImageIcon doorimage1 = new ImageIcon(".Assets/tiles/door1.png");
                    //ImageIcon doorimage2 = new ImageIcon(".Assets/tiles/door2.png");
//
                    //g.drawImage(doorimage1.getImage(), x * BL_WTH, y * BL_HGT, null);
                    //g.drawImage(doorimage2.getImage(), x * BL_WTH, y * BL_HGT + 20, null);
                }

                // LATER: draw other objects in the level by checking if the coordinate 
                // is a different number.
            }
        }

        //ImageIcon doorimage1 = new ImageIcon(".Assets/tiles/door1.png");
        //ImageIcon doorimage2 = new ImageIcon(".Assets/tiles/door2.png");
//
        //g.drawImage(doorimage1.getImage(), doorCoordinateX, doorCoordinateY, null);
        //g.drawImage(doorimage2.getImage(), doorCoordinateX, doorCoordinateY + 20, null);


        //ImageIcon image = new ImageIcon("Assets/sprite.png");
        //g.drawImage(image.getImage(), (player.x - player.spriteWidth), 
        //(player.y - player.spriteHeight), null);
        g.setColor(Color.CYAN); //use a simple color for now.
        g.fillRect((player.x - player.spriteWidth - x0), (player.y - player.spriteHeight), 50, 50);
        /*Draw the image of the player. Using the width and heigth of the player, 
        draw the center of the image to the corresponding center of the player. 
        Make it flush with the groud by adusting the y-position*/

        if (isPaused) {
            g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
            g.setColor(Color.BLUE);
            g.drawString("PAUSED", 600, 300);
        }
    }
}
