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
    * detect if a key is pressed.
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