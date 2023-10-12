import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The main class of the game.
 * @author Diederik Webster
 * @studentnumber 1957880
 * 
 * @author Matthijs Smulders
 * @studentnumber 1973169
 * 
 */
public class Game extends JPanel implements KeyListener {

    public JFrame frame;
    public Player player = new Player(); // Create a new player
    public Level level = new Level(player); // Create a new level

    private int width = 800; // Width of the game
    private int height = 585; // Height of the game
    


    JLayeredPane layeredPane = new JLayeredPane();

    /**
     * Constructor for the game.
    */
    Game() {
        frame = new JFrame("Game"); // Create a new frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.add(level);
        frame.addKeyListener(this);
        
    }

    /**
     * Method to run the game.
     */
    public void run() {
        boolean run = true;
        boolean lose = false;

        while (run) {
            level.repaint();
            if (lose) {
                run = false;
            }
        }
    }

    /**
     * Method to handle key presses. 
     * LATER: should we add wait methods to control animation and speed?
    */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            System.out.println("space");
            
            player.spacekeyPressed = true;
            player.jump();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("left");

            player.isValidMove(level.level);

            player.moveLeft();
            
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("right");
            player.moveRight();
        }
    }
    
    /**
     * Method to handle key releases.
    */
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.spacekeyPressed = false;
            //player.verticalAcceleration = 0;
        }
    }
    
    public void keyTyped(KeyEvent e) {
    }


    public static void main(String[] args) {
        Game game = new Game(); // Create a new game
        game.run(); // Run the game
    }
}