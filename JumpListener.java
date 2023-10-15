import java.awt.event.*;

public class JumpListener implements KeyListener {
    private Player player;
    private Level level;

    /**
     * Constructor for the listener.
    */
    public JumpListener(Player player, Level level) {
        this.player = player;
        this.level = level;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            System.out.println("space");
             
            player.isJumping = true;
            player.jump();
        }

    }
    
    /**
     * Method to handle key releases.
    */
    public void keyReleased(KeyEvent e) {
    }
    
    public void keyTyped(KeyEvent e) { // not used
    }

    
}
