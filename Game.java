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
public class Game {

    public JFrame frame;
    public JPanel panel;
    public Level level = new Level();

    private int width = 800;
    private int height = 585;

    

    public Player player = new Player();

    // Initialize the frame and panel
    Game() {
        frame = new JFrame("Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setVisible(true);

        //panel = new JPanel();
        //panel.setLayout(null);
        //panel.setPreferredSize(new Dimension(width, height));
        //panel.setBackground(Color.BLACK);
        frame.add(level);
        frame.add(player);
    }

    /**
     * Constructor for objects of class Game.
     */
    public void run() {
        boolean run = true;
        boolean lose = false;

        System.out.print(player.getX());
        while (run) {


            if (lose) {
                run = false;
            }
        }
    }


    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}