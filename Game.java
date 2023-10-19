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
    public ReadSaveFile reader = new ReadSaveFile(); // Create a new reader
    public int[] saveData = reader.readSaveFile(); // Read the save file
    public CreateSaveFile writer = new CreateSaveFile(); // Create a new writer
    public String newSaveData = "";
    
    public Player player = new Player(); // Create a new player
    public Level level = new Level(player, 1); // Create a new level 

    private int width = 1600; // Visible width of the game
    private int height = 600; // Visible height of the game

    private boolean lose = false; // Boolean to check if the player has lost
    
    public MovementListener movementListener = new MovementListener(); 
    // Create a new listener for movement


    Timer timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                // This method is called every 120/1000 seconds or gameloop

                if (movementListener.isJumping) { // if the player is jumping
                    //System.out.println("jumping");
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
                
                if ("none".equals(movementListener.direction)) {
                    player.notMovingHorizontal();
                }

                if ("left".equals(movementListener.direction)) {
                    //System.out.println("left");
                    if (!player.isValidMove(level.level, "left")) {
                        //System.out.println("invalid move");
                    }
                } else if ("right".equals(movementListener.direction)) {
                    //System.out.println("right");
                    if (!player.isValidMove(level.level, "right")) {
                        //System.out.println("invalid move");                        
                    }
                }

                if (movementListener.isWKeyPressed) {
                    movementListener.isWKeyPressed = false;
                    if (level.level[(player.x / 50)][(player.y / 50)] == 2) {
                        lose = true;
                    }
                }
                
                player.move();
                newSaveData = player.x + "\n" + player.y + "\n" + level.level_number;
                writer.createSaveFile(newSaveData);
                

                if (!(player.x - 300 < 0 || player.x - 300 + width > level.level.length * 50)) {
                    level.x0 = player.x - 300;
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

        

        player.x = saveData[0];
        player.y = saveData[1];
        
        level.level_number = saveData[2];

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