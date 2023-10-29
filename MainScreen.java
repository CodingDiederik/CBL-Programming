import java.awt.*;
import javax.swing.*;

/**
 * Display the start or end screen for the game.
 * Extends JPanel to be able to draw the start or end screen.
*/
public class MainScreen extends JPanel {

    public String state; // The state of the game: start or end.
    int length; // Length of a string in pixels

    MainScreen(String state) {
        this.state = state;
    }

    /**
     * Paint the start or end screen.
     * 
     * Calculate the length of a string, and draw the string centered on the screen.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if ("start".equals(state)) {
            g.setColor(Color.BLUE);

            g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
            length = stringLength("CBL PLATFORMER", g);
            g.drawString("CBL PLATFORMER", 800 - length / 2, 150);

            g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
            length = stringLength("Made By Diederik and Matthijs", g);
            g.drawString("Made By Diederik and Matthijs", 800 - length / 2, 200);

            g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
            length = stringLength("Moving", g);
            g.drawString("Moving", 400 - length / 2, 250);
            length = stringLength("<-, -> / A, D", g);
            g.drawString("<-, -> / A, D", 400 - length / 2, 300);
            length = stringLength("Jumping", g);
            g.drawString("Jumping", 400 - length / 2, 400);
            length = stringLength("UP / SPACE", g);
            g.drawString("UP / SPACE", 400 - length / 2, 450);

            length = stringLength("System", g);
            g.drawString("System", 1200 - length / 2, 250);
            length = stringLength("Pause: Esc", g);
            g.drawString("Pause: Esc", 1200 - length / 2, 350);
            length = stringLength("Restart game: BACKSPACE", g);
            g.drawString("Restart game: BACKSPACE", 1200 - length / 2, 400);
            length = stringLength("Enter door: W", g);
            g.drawString("Enter door: W", 1200 - length / 2, 450);

            length = stringLength("Press ENTER to start", g);
            g.drawString("Press ENTER to start", 800 - length / 2, 500);
            
        } else if ("end".equals(state)) {
            g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
            g.setColor(Color.BLUE);
            length = stringLength("THE END", g);
            g.drawString("THE END", 800 - length / 2, 200);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
            length = stringLength("Thanks for playing!", g);
            g.drawString("Thanks for playing!", 800 - length / 2, 300);
            length = stringLength("Press ENTER to restart", g);
            g.drawString("Press ENTER to restart", 800 - length / 2, 400);
        }
    }

    /** 
     * Calculate the length of a string for use in paintComponent.
     * @param s The string to calculate the length of.
     * @param g The graphics object.
     * @return The length of the string.
     */
    int stringLength(String s, Graphics g) {
        return (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
    }
}
