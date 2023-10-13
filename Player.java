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

    int verticalAcceleration = 0;
    int horizontalAcceleration = 0;

    int MAX_SPEED = 30; // maximum speed of the player

    int change_x = 0;
    int change_y = 0;

    boolean isJumping = false;
    boolean isFalling = false;

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
 
        if (direction == "left") { 
            // now we need to check if the player can move to the left
            // for this we need to know how far the player is going to move

        } else {
            // now we need to check if the player can move to the right
            // for this we need to know how far the player is going to move

        }

        return false;
    }

    /**
     * Calculates the change in x and y coordinates.
     * @return 
    */
    void calculateChangeXAndY(String direction) {
        // calculate the change in x coordiantes
        horizontalAcceleration = horizontalAcceleration(direction); // calculate the horizontal acceleration

        change_x += horizontalAcceleration;

        verticalAcceleration = verticalAcceleration(); // calculate the vertical acceleration
        change_y += verticalAcceleration;
    }

    // TODO: More advanced? Functions to calculate the acceleration
    int accelerationCalculator(int speed) { // we take the positive site of parabole
        // gently make the player move faster
        if (speed < 0) {
            if (speed > -10) {
                return -5;
            } else {
                return -10;
            }

        } else if (speed < 10) {
            return 5;
        } else {
            return 10;
        }
        
    }

    // TODO: More advanced? Functions to calculate the acceleration
    int decelerationCalculator(int speed) {
        // gently make the player move slower
        if (speed < 0) {
            return 15;

        } else {
            return -15;
        }
    }

    /**
     * Calculates the horizontal acceleration.
    */
    int horizontalAcceleration(String direction) { 
        if (horizontalSpeed > 0) {
            if ("right".equals(direction)) {
                return accelerationCalculator(horizontalSpeed);
            } else {
                return decelerationCalculator(horizontalSpeed);
            }

        }  else  {
            if ("left".equals(direction)) {
                return accelerationCalculator(horizontalSpeed);
            } else {
                return decelerationCalculator(horizontalSpeed);
            }
        }
    }

    int jumpCalculator(int speed) {
        return 10;
    }

    int gravityCalculator(int speed) {
        return -10;
    }

    int verticalAcceleration() {
        if (isJumping) {
            return jumpCalculator(verticalSpeed);
        } else if (isFalling) {
            return gravityCalculator(verticalSpeed);
        } else {
            return 0;
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