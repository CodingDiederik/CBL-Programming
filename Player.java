import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * Player class.
*/
public class Player extends JPanel{
    int x = 50;
    int y = 50;
    int verticalspeed;
    int horizontalspeed;
    int verticalacceleration;
    int horizontalAcceleration;
    int gravity;
    int change_x;
    int change_y;

    ImageIcon image = new ImageIcon("Image.jpeg");
    
    // constructor
    Player() {
        Listener listener = new Listener();
        //this.addKeyListener(listener);
    }

    
    /**
     * Jump method.
    */
    void jump() {
        // smooth acceleration jumping

        if (verticalspeed != 0) { // check if player is already jumping
            // check if space key is pressed

            boolean spacekeypressed = false;
            

            // if space key is pressed, set verticalspeed to 10

            if (spacekeypressed) {
                verticalspeed = 10;
            }


        } 
    }
    void moveLeft(){
        
    }
    void moveRight(){
        
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(x, y, 50, 50);
    }
}


