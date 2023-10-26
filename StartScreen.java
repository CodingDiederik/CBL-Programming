import java.awt.*;
import javax.swing.*;

/**
 * Display the startscreen for the game.
*/
public class StartScreen extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
        g.setColor(Color.BLUE);
        g.drawString("Press ENTER to start", 200, 400);
    }
}
