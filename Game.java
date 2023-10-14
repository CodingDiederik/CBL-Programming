//import java.util.*;
import java.awt.event.*;
import javax.swing.*;
//import java.awt.*;


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

    public JFrame frame;
    public Player player = new Player(); // Create a new player
    public Level level = new Level(player); // Create a new level

    private int width = 800; // Width of the game
    private int height = 585; // Height of the game

    private boolean lose = false; // Boolean to check if the player has lost
    
    public Listener listener = new Listener(player, level); // Create a new listener

    Timer timer = new Timer(120 / 1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) { // This method is called every 120/1000 seconds or gameloop
                level.repaint();
            
                if (lose) {
                    timer.stop();
                    //LATER: restart game
                }
            }
        });

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
        frame.addKeyListener(listener);
    }

    void run() {
        timer.start();
    }
        

    public static void main(String[] args) {
        Game game = new Game(); // Create a new game
        game.run(); // Run the game
    }
}