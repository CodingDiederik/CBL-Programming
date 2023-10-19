//import java.util.*;
//import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



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

    public JButton button = new JButton("Restart");
    
    public Player player = new Player(); // Create a new player
    public Level level = new Level(player, 0); // Create a new level 

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
                
                //Update the save Data after every move
                player.move();
                newSaveData = level.level_number + "\n" + player.x + "\n" + player.y;
                writer.createSaveFile(newSaveData);
                

                if (!(player.x - 300 < 0 || player.x - 300 + width > level.level.length * 50)) {
                    level.x0 = player.x - 300;
                }
                
                level.repaint();
                
            
                if (lose) {
                    timer.stop();
                    restart();
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

        // Set the player's position to the saved position. If the position is -1, the player will be placed at the default position.
        if (saveData[1] != -1 && saveData[2] != -1) {
            player.x = saveData[1];
            player.y = saveData[2];
        }
        
        level.level_number = saveData[0];

        frame.add(level);

        //button.setPrefferedSize(new Dimension(100, 50));
        //frame.add(button, BorderLayout.NORTH);
        frame.addKeyListener(movementListener);

        
    }

    void run() {
        timer.start();
    }
        
    void restart(){
        writer.createSaveFile(level.level_number + "");
        frame.setVisible(false);
        frame.dispose();
        Game game = new Game();
        game.run();
    }

    void nextLevel(){
        writer.createSaveFile((level.level_number + 1) + "");
        frame.setVisible(false);
        frame.dispose();
        Game game = new Game();
        game.run();
    }

    public static void main(String[] args) {
        Game game = new Game(); // Create a new game
        game.run(); // Run the game
    }
}