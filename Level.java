import java.awt.*;
import javax.swing.*;

/**
 * The level class of the game.
*/
public class Level extends JPanel {
    
    int[][] level; // the level is a 2D array of integers, evey coordinate is a block or not a block

    private int blWdth = 50; // Width of a block in pixels
    private int blHgth = 50; // Height of a block in pixels


    int x0 = 0; //var to scroll the level

    private Player player;
    public int lvlNum;
    public boolean isPaused = false;
    public ReadLevelFile reader = new ReadLevelFile(); // Create a new reader

    String gameState = "start"; // The state of the game: running, paused, lost, won

    int length;
    
    /**
     * Constructor for objects of class Level.
     * 
     * The level is a 2D array of integers.
     * The dimensions of the level are 64 blocks wide, 12 blocks high.
     *      The screen is 32 blocks wide, 12 blocks high.
     * 
     * @param player The player object, to be able to draw the player
     * @param lvlNum The level number, to be able to load the correct level
     */
    public Level(Player player, int lvlNum) {
        this.player = player;
        this.lvlNum = lvlNum;
        
        // determine dimentions of the level: 64 blocks wide, 12 blocks high
        // The screen is 32 blocks wide, 12 blocks high, so level 1 is 2 screens wide.
        this.level = new int[64][12];
        createLevel();
    }


    void createLevel() {
        if (lvlNum == 4) {
            gameState = "end";
            return;
        }

        String[] levelConverter = reader.readSaveFile(lvlNum);
        if (levelConverter == null) {
            System.out.println("Level not found");
            levelConverter = reader.readSaveFile(1);
        }
        
        for (int i = 0; i < levelConverter.length; i++) {
            String line = levelConverter[i];
            String data[] = line.split(" ");
            if ("RB".equals(data[0])) {
                createRowBlocks(Integer.valueOf(data[1]),Integer.valueOf(data[2]), Integer.valueOf(data[3]));
            } else if ("CB".equals(data[0])) {
                createCollumnBlocks(Integer.valueOf(data[1]),Integer.valueOf(data[2]), Integer.valueOf(data[3]));
            } else if ("RS".equals(data[0])) {
                createRowSpikes(Integer.valueOf(data[1]),Integer.valueOf(data[2]), Integer.valueOf(data[3]));
            } else if ("CS".equals(data[0])) {
                createCollumnSpikes(Integer.valueOf(data[1]),Integer.valueOf(data[2]), Integer.valueOf(data[3]));
            } else if ("ED".equals(data[0])) {
                createEndLevelDoor(Integer.valueOf(data[1]), Integer.valueOf(data[2]));
            }
        }

        createCollumnBlocks(this.level.length - 2, this.level[0].length - 1, this.level[0].length);
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

    void createRowSpikes(int x, int y, int length) {
        for (int r = x; r < x + length; r++) {
            this.level[r][y] = 3;
        }
    }

    void createCollumnSpikes(int x, int y, int height) {
        for (int c = y; c > y - height; c--) {
            this.level[x][c] = 3;
        }
    }

    void createEndLevelDoor(int x, int y) {
        // create a door at the end of the level
        level[x][y] = 2;
    }

    
    /**
     * Draw the level depending on the gameState.
     * 
     * If the gameState is "running", draw the level and the player.
     *      Use a for loop to draw the level in a grid of 50x50 pixels.
     *          Use the x0 variable to scroll the level. 
     *      Then draw the player outside the for loop,
     *           so it is drawn without constraints of the level grid
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw the level
        if ("running".equals(gameState)) {
            for (int x = x0 / 50; x < level.length; x++) { 
                for (int y = 0; y < level[0].length; y++) { 
                    if (level[x][y] == 1) { 
                        g.setColor(Color.BLACK);
                        g.fillRect((x  * blWdth) - x0, y * blHgth, blWdth, 
                            blHgth); 
                    }
                    if (level[x][y] == 2) {
                        g.setColor(Color.GREEN);
                        g.fillRect((x * blWdth) - x0, y * blHgth, blWdth, blHgth);
                    }
                    if (level[x][y] == 3) {
                        g.setColor(Color.RED);
                        g.fillRect((x  * blWdth) - x0, y * blHgth, blWdth, blHgth);
                    }

                }
            }

            g.setColor(Color.CYAN); //use a simple color for now.
            g.fillRect((player.x - player.spriteWidth - x0), (player.y - player.spriteHeight), 50, 50);
            /*Draw the image of the player. Using the width and heigth of the player, 
            draw the center of the image to the corresponding center of the player. 
            Make it flush with the groud by adusting the y-position*/
        } else if ("paused".equals(gameState)) {
            g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
            g.setColor(Color.BLUE);
            length = stringLength("PAUSED", g);
            g.drawString("PAUSED", 800 - length / 2, 300);

        } else if ("win".equals(gameState)) {
            g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
            g.setColor(Color.BLUE);
            length = stringLength("YOU WIN", g);
            g.drawString("YOU WIN", 800 - length / 2, 300);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
            length = stringLength("Press ENTER for next level ", g);
            g.drawString("Press ENTER for next level ", 800 - length / 2, 400);

        } else if ("lose".equals(gameState)) {
            g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
            g.setColor(Color.BLUE);
            length = stringLength("YOU LOSE", g);
            g.drawString("YOU LOSE", 800 - length / 2, 300);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
            length = stringLength("Press ENTER to restart", g);
            g.drawString("Press ENTER to restart", 800 - length / 2, 400);
        } 
    }
    
    /**
     * Calculate the length of a string for use in paintComponent.
     * @param s The string to calculate the length of.
     * @param g The graphics object.
     * @return The length of the string.
     */
    int stringLength(String s, Graphics g) {
        return (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
    }
}
