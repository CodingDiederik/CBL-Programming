import java.awt.event.*;
import javax.swing.Timer;

/**
 * The listener class of the game.
 * It check for the Escape, Left, Right, Up, W, Enter and Backspace keys.
 * It also check if the player is jumping or not.
 * It does this by checking every 10 milliseconds if a key is pressed.
 */
public class MovementListener implements KeyListener {

    public String direction = "none";

    // booleans to check if a key is pressed
    public boolean isWKeyPressed = false;
    public boolean isEnterKeyPressed = false;
    public boolean isJumping = false;
    public boolean pause = false;
    private boolean leftKeyPressed = false;
    private boolean rightKeyPressed = false;
    private boolean upKeyPressed = false;
    private boolean wKeyPressed = false;
    private boolean escapeKeyPressed = false;
    public boolean enterKeyPressed = false;
    public boolean backspaceKeyPressed = false;

    private Timer timer;

    /**
     * The timer is intiated in the constructor.
    */
    public MovementListener() {
        timer = new Timer(10, e -> checkKeyPress());
        timer.start();
    }

    /**
    * Detect if a key is pressed. and execute the corresponding method described 
    * in the player class.
    * Print a message to the console to see what's going on in the program.
    */
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            escapeKeyPressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            leftKeyPressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            rightKeyPressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
            upKeyPressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            wKeyPressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            enterKeyPressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            backspaceKeyPressed = true;
        }
    }
    
    /**
     * Method to handle key releases.
    */
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            leftKeyPressed = false;
            direction = "none";
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            rightKeyPressed = false;
            direction = "none";
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
            upKeyPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            wKeyPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            enterKeyPressed = false;
        }
    }

    public void keyTyped(KeyEvent e) { // not used
    }

    /**
     * Check which key's are pressed and set the direction accordingly.
    */
    public void checkKeyPress() {
        if (escapeKeyPressed) {
            escapeKeyPressed = false;
            pause = !pause; // toggle pause
        }

        if (leftKeyPressed) {
            if (!"right".equals(direction)) {
                direction = "left"; // set direction to left
            }
        }

        if (rightKeyPressed) {
            if (!"left".equals(direction)) {
                direction = "right"; // set direction to right
            }
        }

        if (leftKeyPressed && rightKeyPressed) { // if both left and right are pressed
            direction = "none"; // set direction to none
        }

        if (upKeyPressed) {
            isJumping = true;
        }

        if (wKeyPressed) {
            isWKeyPressed = true;
        } else {
            isWKeyPressed = false;
        }

        if (enterKeyPressed) {
            isEnterKeyPressed = true;
        }
    }
    
}