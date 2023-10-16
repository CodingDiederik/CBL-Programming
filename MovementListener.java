//import java.util.*;
//import java.util.Timer;
//import javax.swing.*;
//import java.awt.List;
//import java.awt.*;
import java.awt.event.*;

/**
 * The listener class of the game.
 */
public class MovementListener implements KeyListener {

    private Player player;
    private Level level;
    public boolean isKeyPressed = false;

    /**
     * Constructor for the listener.
     */
    public MovementListener(Player player, Level level) {
        this.player = player;
        this.level = level;
    }

    /**
    * Detect if a key is pressed. and execute the corresponding method described in the player class.
    * Print a message to the console to see what's going on in the program.
    * @var isKeyPressed is used to check if a key is pressed, to reset speeds if you repress a key.
    */
    public void keyPressed(KeyEvent e) {
        
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            System.out.println("left");
            isKeyPressed = true;

            if (player.isValidMove(level.level, "left")) {
                player.move();
            } else {
                System.out.println("invalid move");
            }

        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            System.out.println("right");
            isKeyPressed = true;
            
            if (player.isValidMove(level.level, "right")) {
                player.move();
            } else {
                System.out.println("invalid move");
            }
        }
    }
    
    /**
     * Method to handle key releases.
    */
    public void keyReleased(KeyEvent e) {
        isKeyPressed = false;
  
    }

    public void keyTyped(KeyEvent e) { // not used
    }
    
}