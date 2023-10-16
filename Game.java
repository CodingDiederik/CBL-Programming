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

    public JFrame frame; // Create a new frame
    public Player player = new Player(); // Create a new player
    public Level level = new Level(player); // Create a new level

    private int width = 800; // Visible width of the game
    private int height = 600; // Visible height of the game

    private boolean lose = false; // Boolean to check if the player has lost
    
    public MovementListener movementListener = new MovementListener(); // Create a new listener for movement
    public JumpListener jumpListener = new JumpListener(player, level); // Create a new listener for jumping

    Timer timer = new Timer(120 / 1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) { // This method is called every 120/1000 seconds or gameloop

                if (!movementListener.isKeyPressed) {
                    player.notMovingHorizontal();
                    //System.out.println("not moving");
                }

                if ("left".equals(movementListener.direction)) {
                    System.out.println("left");
                    if (player.isValidMove(level.level, "left")) {
                        player.move();
                    } else {
                        System.out.println("invalid move");
                    }
                } else if ("right".equals(movementListener.direction)) {
                    System.out.println("right");
                    System.out.println(movementListener.direction);
                    if (player.isValidMove(level.level, "right")) {
                        player.move();
                    } else {
                        System.out.println("invalid move");
                    }
                }
                
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
        frame.addKeyListener(movementListener);
        frame.addKeyListener(jumpListener);
    }

    void run() {
        timer.start();
    }
        

    public static void main(String[] args) {
        Game game = new Game(); // Create a new game
        game.run(); // Run the game
    }
}