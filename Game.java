import java.awt.event.*;
import javax.swing.*;

/**
 * This is the class where the game is run from. It contains the method which 
 * is run every game tick. 
*/
public class Game {

    public JFrame frame; // Create a new frame
    public ReadSaveFile reader = new ReadSaveFile(); // Create a new reader
    public int[] saveData = reader.readSaveFile(); // Read the save file
    public CreateSaveFile writer = new CreateSaveFile(); // Create a new writer
    public String newSaveData = ""; // Create a new string to write to the save file

    public int levelNumber; // Get the level number from the save file
    
    public Player player = new Player(); // Create a new player
    public Level level; // Create a new level 

    int width = 1600; // Visible width of the game
    int height = 600; // Visible height of the game

    boolean lose; // Boolean to check if the player has lost
    boolean win; // Boolean to check if the player has won
    boolean restartButtonPressed = false; // Boolean to check if the restart button has been pressed
    
    // Create a new listener for movement
    public MovementListener movementListener = new MovementListener(); 

    // this is the timer that runs the game
    // it runs every 20 milliseconds
    Timer timer = new Timer(20, new ActionListener() {
        public void actionPerformed(ActionEvent e) { 
            
            if (movementListener.BackspaceKeypressed) { // if the backspace key is pressed
                movementListener.BackspaceKeypressed = false;
                levelNumber = 1; // set the level number to 1
                restartButtonPressed = true; // now in the main class the game will restart 

            } else if (!movementListener.pause) { // if the game is not paused

                // if the player has not won or lost the gamestate = running
                if (!("lose".equals(level.gameState) || "win".equals(level.gameState))) {
                    level.gameState = "running";
                }

                if (movementListener.isJumping) { // if the player is jumping
                    if (player.isOnGround(level.level)) { // check if the player is on the ground
                        player.isJumping = true;
                    }

                    movementListener.isJumping = false;
                }

                // if the player is not on the ground and not jumping
                if (!player.isOnGround(level.level) && !player.isJumping) { 
                    player.isFalling = true; // the player is falling
                }

                if (player.isJumping || player.isFalling) { // if the player is jumping or falling
                    player.jump(level.level); // execute the jump method
                }                

                // if no input is given
                if ("none".equals(movementListener.direction)) {

                    // make the player decelerate
                    if (!player.isValidMove(level.level, "stop")) {
                        ;
                    }
                }

                // if the player is moving left or right
                // check if it is a valid move, in this function the player will only move
                // as far as it can
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
                player.move(level.level);
                newSaveData = levelNumber + "\n" + player.x + "\n" + player.y + "\n" + level.x0;
                writer.createSaveFile(newSaveData);

                // if the playercoordinates are about to levave the screen
                // set the x0 coordinate to the player x coordinate
                // this will make the player stay in the middle of the screen
                // and make the level move around the player
                if (!(player.x - 300 < 0 || player.x - 300 + width > level.level.length * 50)) {
                    level.x0 = player.x - 300;
                }

                if (movementListener.isWKeyPressed) { // if the w key is pressed
                    movementListener.isWKeyPressed = false;
                    
                    // if the player is at a door
                    if (level.level[(player.x / 50)][(player.y / 50)] == 2) { 
                        if (levelNumber == 3) {
                            level.gameState = "win"; // the player has won
                            movementListener.pause = true; // the game is paused
                            movementListener.EnterKeypressed = true; // the enter key is pressed
                            // the game will now show the end endscreen
                            
                        } else {
                            level.gameState = "win"; // the player has won
                            movementListener.pause = true; // the game is paused
                            level.repaint(); // repaint the level, it will now show the winscreen
                        }
                    }
                }

                // if the player falls of the map
                if (player.y + player.spriteHeight > height - 40) {
                    level.gameState = "lose"; // the player has lost
                    movementListener.pause = true;
                    level.repaint(); // it will now show the lose screen
                    player.x = 250; // reset the player coordinates
                    player.y = 524;
                }

                // if the player touches a spike
                if (!(player.y - player.spriteHeight < 0)) {
                    int leftside = (player.x - player.spriteWidth) / 50;
                    int rightside = (player.x + player.spriteWidth) / 50;
                    int top = (player.y - player.spriteHeight) / 50;
                    int bottem = (player.y + player.spriteHeight) / 50;

                    if (level.level[leftside][bottem] == 3 
                        || level.level[leftside][top] == 3
                        || level.level[rightside][bottem] == 3 
                        || level.level[rightside][top] == 3) {

                        level.gameState = "lose"; // the player has lost
                        movementListener.pause = true; 
                        level.repaint(); // it will now show the lose screen
                    }
                }

            // if the game is paused and the player has won or lost
            } else if ("lose".equals(level.gameState) || "win".equals(level.gameState)) { 

                if (movementListener.isEnterKeypressed) {
                    movementListener.isEnterKeypressed = false;
                    movementListener.EnterKeypressed = false;

                    if ("win".equals(level.gameState)) { 
                        win = true; // the player has won
                    } else if ("lose".equals(level.gameState)) {
                        lose = true; // the player has lost
                    }
                    movementListener.pause = false;
                }
                
            } else {
                level.gameState = "paused"; // the game is paused
            }

            level.repaint();
        }
        });

    void run() {
        level.gameState = "running";
        timer.start(); // start the timer
    }
}