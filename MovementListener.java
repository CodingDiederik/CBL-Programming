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

    public boolean isKeyPressed = false;
    public String direction = "none";


    /**
    * Detect if a key is pressed. and execute the corresponding method described 
    * in the player class.
    * Print a message to the console to see what's going on in the program.
    * @var isKeyPressed is used to check if a key is pressed, to reset speeds if you repress a key.
    */
    public void keyPressed(KeyEvent e) {
        
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            isKeyPressed = true;
            if (!"right".equals(direction)) {
                direction = "left";
            }

        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            isKeyPressed = true;
            if (!"left".equals(direction)) {
                direction = "right";
            }
            
        }
    }
    
    /**
     * Method to handle key releases.
    */
    public void keyReleased(KeyEvent e) {
        isKeyPressed = false;
        direction = "none";
    }

    public void keyTyped(KeyEvent e) { // not used
    }
    
}