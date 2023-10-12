import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Player class.
*/
public class Player extends JPanel {
    int x = 50; // start coordiantes for the player
    int y = 50;

    int spriteWidth = 50/2; // size of the player
    int spriteHeight = 50/2;

    int verticalSpeed = 0; // speed of the player
    int horizontalSpeed = 0;

    int verticalAcceleration;
    int horizontalAcceleration;

    int gravity = 5; // gravity of the player

    int change_x;
    int change_y;
    boolean spacekeyPressed = false;

    /**
     * Checks if the player is on the ground.
    */
    boolean isOnGround(int[][] level) {
        if (level[x - spriteWidth][y - spriteHeight - 1] == 1 || level[x + spriteWidth][y - spriteHeight - 1] == 1) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Checks if the player collides with an object.
    */
    boolean isValidMove(int[][] level) {
        // TODO: check if the player collides with an object
        if (isOnGround(level)) {
            return false;
        }
        return false;
    }


    /**
     * Jump method.
    */
    void jump() {
        // smooth acceleration jumping

        if (verticalSpeed != 0) { // check if player is already jumping
            // check if space key is pressed
            // if space key is pressed, set verticalspeed to 10

            if (spacekeyPressed) {
                verticalSpeed = 10;
            }


        } 
    }
    
    void moveLeft() {
        // Later: let the player move smoothly to the left

        // if the player is already moving to the right, increase the horizontal speed
        // check if the maximum horizontal speed has been reached
        if (horizontalSpeed < 10) {
            // if the maximum horizontal speed has not been reached, 
            // determine the horizontal acceleration
            horizontalAcceleration = 5;
        } else {
            horizontalAcceleration = 0;
        }
        horizontalSpeed += horizontalAcceleration;

        change_x = horizontalSpeed;
        x -= change_x;
        
    }
    
    void moveRight() {
        // let the player move smoothly to the right

        // if the player is already moving to the right, increase the horizontal speed
        // check if the maximum horizontal speed has been reached
        if (horizontalSpeed < 10) {
            // if the maximum horizontal speed has not been reached, 
            // determine the horizontal acceleration
            horizontalAcceleration = 10;
        } else {
            horizontalAcceleration = 0;
        }
        horizontalSpeed += horizontalAcceleration;

        change_x = horizontalSpeed;
        x += change_x;
    } 
}