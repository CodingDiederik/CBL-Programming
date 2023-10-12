//import java.awt.*;
//import java.awt.event.*;
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
    int MAX_SPEED = 10; // maximum speed of the player

    int change_x;
    int change_y;

    boolean spacekeyPressed = false;
    String previousDirection;

    /**
     * Checks if the player is on the ground.
    */
    boolean isOnGround(int[][] level) { // TODO: TEST
        if (level[x - spriteWidth][y - spriteHeight - 1] == 1 || level[x + spriteWidth][y - spriteHeight - 1] == 1) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Checks if the player collides with an object.
    */
    boolean isValidMove(int[][] level, String direction) {
 
        if (isOnGround(level)) { // checks if the sprite is on the ground
            if (direction == "left") { 
                // now we need to check if the player can move to the left
                // for this we need to know how far the player is going to move


            } else {
                // now we need to check if the player can move to the right
                // for this we need to know how far the player is going to move

            }


        }

        return false;
    }

    /**
     * Calculates the change in x and y coordinates.
     * @return 
    */
    void calculateChangeXAndY(String direction) {
        // calculate the change in x coordiantes
        if (horizontalSpeed > 0) { // Is the player moving to the right?
            if (direction == "right") {
                if (horizontalSpeed >= MAX_SPEED) { // first we calculate the speed to know how far the player is going to move
                    horizontalSpeed = MAX_SPEED; 
                } else {
                    horizontalSpeed += horizontalAcceleration; // In the future we need to make this smooth
                    if (horizontalSpeed >= MAX_SPEED) {
                        horizontalSpeed = MAX_SPEED;
                    }
                }

            } else {
                horizontalSpeed = 0; // Maybe in the future make it smooth
            }

        } else if (horizontalSpeed < 0) { // Is the player moving to the left?

        } else { // The player is not moving horizontally

        }
    }


    /**
     * Jump method.
    */
    void jump() {
        // smooth acceleration jumping
        //
        //if (verticalSpeed != 0) { // check if player is already jumping
        //    // check if space key is pressed
        //    // if space key is pressed, set verticalspeed to 10
        //
        //    if (spacekeyPressed) {
        //        verticalSpeed = 10;
        //    }
        //
        //
        //} 
    }
    
    void moveLeft() {
        // Later: let the player move smoothly to the left

        // if the player is already moving to the right, increase the horizontal speed
        // check if the maximum horizontal speed has been reached
        //if (horizontalSpeed < 10) {
        //    // if the maximum horizontal speed has not been reached, 
        //    // determine the horizontal acceleration
        //    horizontalAcceleration = 5;
        //} else {
        //    horizontalAcceleration = 0;
        //}
        //horizontalSpeed += horizontalAcceleration;
        //
        //change_x = horizontalSpeed;
        //x -= change_x;
        
    }
    
    void moveRight() {
        // let the player move smoothly to the right

        // if the player is already moving to the right, increase the horizontal speed
        // check if the maximum horizontal speed has been reached
        //if (horizontalSpeed < 10) {
        //    // if the maximum horizontal speed has not been reached, 
        //    // determine the horizontal acceleration
        //    horizontalAcceleration = 10;
        //} else {
        //    horizontalAcceleration = 0;
        //}
        //horizontalSpeed += horizontalAcceleration;
        //
        //change_x = horizontalSpeed;
        //x += change_x;
    } 
}