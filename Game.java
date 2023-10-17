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
    
    public MovementListener movementListener = new MovementListener(); 
    // Create a new listener for movement

    Timer timer = new Timer(120 / 1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                // This method is called every 120/1000 seconds or gameloop

                if (movementListener.isJumping) { // if the player is jumping
                    System.out.println("jumping");
                    if (player.isOnGround(level.level)) { // check if the player is on the ground
                        player.isJumping = true;
                    }

                    movementListener.isJumping = false;
                }

                if (!player.isOnGround(level.level) && !player.isJumping) {
                    player.isFalling = true;
                }

                if (player.isJumping || player.isFalling) { // if the player is jumping or falling
                    player.jump(level.level); // execute the jump method
                }                
                
                if (!movementListener.isKeyPressed) {
                    player.notMovingHorizontal();
                }

                if ("left".equals(movementListener.direction)) {
                    System.out.println("left");
                    if (!player.isValidMove(level.level, "left")) {
                        System.out.println("invalid move");
                    }
                } else if ("right".equals(movementListener.direction)) {
                    System.out.println("right");
                    System.out.println(movementListener.direction);
                    if (!player.isValidMove(level.level, "right")) {
                        System.out.println("invalid move");                        
                    }
                }
                
                player.move();
                
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
    }

    void run() {
        timer.start();
    }
        

    public static void main(String[] args) {
        Game game = new Game(); // Create a new game
        game.run(); // Run the game
    }
}