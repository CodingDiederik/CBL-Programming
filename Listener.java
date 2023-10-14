//import java.util.*;
//import java.util.Timer;
//import javax.swing.*;
//import java.awt.List;
//import java.awt.*;
import java.awt.event.*;

/**
 * The listener class of the game.
 */
public class Listener implements KeyListener {

    private Player player;
    private Level level;

    /**
     * Constructor for the listener.
     */
    public Listener(Player player, Level level) {
        this.player = player;
        this.level = level;
    }

    /**
    * detect if a key is pressed.
    */
    public void keyPressed(KeyEvent e) {
        
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            System.out.println("space");
            
            player.isJumping = true;
            player.jump();
        }

        //make sure the player can't move through blocks by checking if the move is valid
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            System.out.println("left");

            if (player.isValidMove(level.level, "left")) {
                player.moveLeft();
            } else {
                System.out.println("invalid move");
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            System.out.println("right");
            
            if (player.isValidMove(level.level, "right")) {
                player.moveRight();
            } else {
                System.out.println("invalid move");
            }

        }
    }
    
    /**
     * Method to handle key releases.
    */
    public void keyReleased(KeyEvent e) {
    }
    
    public void keyTyped(KeyEvent e) { // not used
    }
    
}