import java.awt.*;
import javax.swing.*;

/**
 * Display the startscreen for the game.
*/
public class StartScreen extends JPanel {

    public String state;

    StartScreen(String state) {
        this.state = state;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if ("start".equals(state)) {
            g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
            g.setColor(Color.BLUE);
            g.drawString("Press ENTER to start", 200, 400);
        } else if ("end".equals(state)) {
            g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
            g.setColor(Color.BLUE);
            g.drawString("Press ENTER to restart", 200, 400);
        }
    }
}
