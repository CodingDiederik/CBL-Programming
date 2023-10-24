import java.awt.event.*;
import javax.swing.*;

/**
 * The main class of the game.
 * @author Diederik Webster
 * @studentnumber 1957880
 * 
 * @author Matthijs Smulders
 * @studentnumber 1973169
 * 
 */
public class Game extends JPanel {

    public JFrame frame; // Create a new frame
    public ReadSaveFile reader = new ReadSaveFile(); // Create a new reader
    public int[] saveData = reader.readSaveFile(); // Read the save file
    public CreateSaveFile writer = new CreateSaveFile(); // Create a new writer
    public String newSaveData = "";
    //private int counter = 0;

    public int levelNumber /*= saveData[0]*/; // Get the level number from the save file
    
    public Player player = new Player(); // Create a new player
    public Level level /*= new Level(player, levelNumber) */; // Create a new level 

    int width = 1600; // Visible width of the game
    int height = 600; // Visible height of the game

    boolean lose /*= false*/; // Boolean to check if the player has lost
    boolean win /*= false*/; // Boolean to check if the player has won
    boolean restartButtonPressed = false; // Boolean to check if the restart button has been pressed
    
    public MovementListener movementListener = new MovementListener(); 
    // Create a new listener for movement


    Timer timer = new Timer(10, new ActionListener() {
        public void actionPerformed(ActionEvent e) { 
            
            if (movementListener.BackspaceKeypressed) {
                movementListener.BackspaceKeypressed = false;
                levelNumber = 1;
                restartButtonPressed = true;

            } else if (!movementListener.pause) {
                if (!("lose".equals(level.gameState) || "win".equals(level.gameState))) {
                    level.gameState = "running";
                }

                if (movementListener.isJumping) { // if the player is jumping
                    //System.out.println("jumping");
                    if (player.isOnGround(level.level)) { // check if the player is on the ground
                        player.isJumping = true;
                    }

                    movementListener.isJumping = false;
                }

                if (!player.isOnGround(level.level) && !player.isJumping) {
                    player.isFalling = true;
                }

                if (player.isJumping || player.isFalling) { // if the player is jumping or falling
                    player.jump(level.level); // execute the jump method
                }                

                if ("none".equals(movementListener.direction)) {
                    if (!player.isValidMove(level.level, "stop")) {
                        ;
                    }
                }

                if ("left".equals(movementListener.direction)) {
                    if (!player.isValidMove(level.level, "left")) {
                        ;
                    }

                } else if ("right".equals(movementListener.direction)) {
                    if (!player.isValidMove(level.level, "right")) {
                        ;
                    }
                }

                //Update the save Data after every move
                player.move();
                newSaveData = level.level_number + "\n" + player.x + "\n" + player.y + "\n" + level.x0;
                writer.createSaveFile(newSaveData);

                if (!(player.x - 300 < 0 || player.x - 300 + width > level.level.length * 50)) {
                    level.x0 = player.x - 300;
                }

                if (movementListener.isWKeyPressed) {
                    movementListener.isWKeyPressed = false;
                    if (level.level[(player.x / 50)][(player.y / 50)] == 2) {
                        level.gameState = "win";
                        level.repaint();
                    }
                }

                if (!(player.y - player.spriteHeight < 0)) {
                    if (level.level[(player.x - player.spriteWidth) / 50][(player.y + player.spriteHeight) / 50] == 3 || level.level[(player.x - player.spriteWidth) / 50][(player.y - player.spriteHeight) / 50] == 3
                    || level.level[(player.x + player.spriteWidth) / 50][(player.y + player.spriteHeight) / 50] == 3 || level.level[(player.x + player.spriteWidth) / 50][(player.y - player.spriteHeight) / 50] == 3) {
                    level.gameState = "lose";
                    level.repaint();
                    }
                }

                if (player.y > height - 40) {
                    level.gameState = "lose";
                    level.repaint();
                    player.x = 250;
                    player.y = 524;
                }

                if (movementListener.isEnterKeypressed) {
                    movementListener.isEnterKeypressed = false;
                    movementListener.EnterKeypressed = false;

                    if ("win".equals(level.gameState)) {
                        win = true;
                    } else if ("lose".equals(level.gameState)) {
                        lose = true;
                    }
                }
    
                if (player.y > height) {
                    level.gameState = "lose";
                    level.repaint();
                }

            } else {
                level.gameState = "paused";
            }

            level.repaint();

        }
        });

    void run() {
        level.gameState = "running";
        timer.start();
    }
}