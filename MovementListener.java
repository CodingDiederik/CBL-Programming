import java.awt.event.*;
import javax.swing.Timer;

/**
 * The listener class of the game.
 */
public class MovementListener implements KeyListener {

    public String direction = "none";
    public boolean isWKeyPressed = false;
    public boolean isJumping = false;
    public boolean pause = false;
    private boolean LeftKeyPressed = false;
    private boolean RightKeyPressed = false;
    private boolean UpKeyPressed = false;
    private boolean WKeyPressed = false;
    private boolean EscapeKeypressed = false;

    private Timer timer;

    public MovementListener() {
        timer = new Timer(5, e -> checkKeyPress());
        timer.start();
    }

    /**
    * Detect if a key is pressed. and execute the corresponding method described 
    * in the player class.
    * Print a message to the console to see what's going on in the program.
    */
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            EscapeKeypressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            LeftKeyPressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            RightKeyPressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
            UpKeyPressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            WKeyPressed = true;
        }
    }
    
    /**
     * Method to handle key releases.
    */
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            EscapeKeypressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            LeftKeyPressed = false;
            direction = "none";
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            RightKeyPressed = false;
            direction = "none";
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
            UpKeyPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            WKeyPressed = false;
        }
    }

    public void keyTyped(KeyEvent e) { // not used
    }

    public void checkKeyPress() {
        if (EscapeKeypressed) {
            pause = !pause;
        }

        if (LeftKeyPressed) {
            if (!"right".equals(direction)) {
                direction = "left";
            }
        }

        if (RightKeyPressed) {
            if (!"left".equals(direction)) {
                direction = "right";
            }
        }

        if (LeftKeyPressed && RightKeyPressed) {
            direction = "none";
        }

        if (UpKeyPressed) {
            isJumping = true;
        }

        if (WKeyPressed) {
            isWKeyPressed = true;
        }

    }
    
}