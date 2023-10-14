//import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;

/**
 * Player class.
*/
public class Player extends JPanel {
    int x = 750; // start coordiantes for the player
    int y = 400;

    int spriteWidth = 50 / 2; // size of the player
    int spriteHeight = 50 / 2;

    int verticalSpeed = 0; // speed of the player
    int horizontalSpeed = 0;

    int verticalAcceleration = 0;
    int horizontalAcceleration = 0;

    int MAX_SPEED = 6; // maximum speed of the player

    int change_x = 0; //SAME AS SPEED??
    int change_y = 0;

    boolean isJumping = false;
    boolean isFalling = false;

    String previousDirection;

    /**
     * Checks if the player is on the ground.
    */
    boolean isOnGround(int[][] level) { // TODO: MAKE IT WORK :) (level[][] indexes are in blocks of 50, but x and y in single pixels)
        if (level[x - spriteWidth][y + spriteHeight - 1] == 1 || level[x + spriteWidth][y + spriteHeight - 1] == 1) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Checks if the player collides with an object.
    */
    boolean isValidMove(int[][] level, String direction) {
 
        if ("left".equals(direction)) { 
            // now we need to check if the player can move to the left
            // for this we need to know how far the player is going to move
            calculateChangeXAndY(direction);
            if (level[(this.x - this.spriteWidth + change_x) / 50][(this.y - this.spriteHeight) / 50] == 1) {

                //TODO: FIGURE OUT WHAT WAY TO ROUND DOWN
                return false;

            } else { 
                return true; 
            }

        } else {
            // now we need to check if the player can move to the right
            // for this we need to know how far the player is going to move
            calculateChangeXAndY(direction);
            return true;

        }
    }

    /**
     * Calculates the change in x and y coordinates.
    */
    void calculateChangeXAndY(String direction) {
        // calculate the change in x coordiantes
        horizontalAcceleration = horizontalAcceleration(direction); // calculate the horizontal acceleration

        horizontalSpeed += horizontalAcceleration; // calculate the horizontal speed

        if (horizontalSpeed > MAX_SPEED) { // check if the speed is not too high
            horizontalSpeed = MAX_SPEED;
        } else if (horizontalSpeed < -MAX_SPEED) {
            horizontalSpeed = -MAX_SPEED;
        }
        
        //change_x = horizontalSpeed;


        verticalAcceleration = verticalAcceleration(); // calculate the vertical acceleration
        change_y += verticalAcceleration;
    }

    // TODO: More advanced? Functions to calculate the acceleration
    // TODO: RESET IF STOPPED PRESSING KEY

    /**
     * Calculates the acceleration.
    */
    int accelerationCalculator(int speed) { // we take the positive site of parabole
        // gently make the player move faster
        if (speed < 0) {
            if (speed > -10) {
                return -1;
            } else {
                return -2;
            }

        } else if (speed < 10) {
            return 1;
        } else {
            return 2;
        }
        
    }

    // TODO: More advanced? Functions to calculate the acceleration
    /**
     * Calculates the deceleration.
    */
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

        } else if ("left".equals(direction)) {
            return accelerationCalculator(horizontalSpeed);
        } else {
            return decelerationCalculator(horizontalSpeed);
        }
        
    }
    

    int jumpCalculator(int speed) {
        return 10;
    }

    int gravityCalculator(int speed) {
        return -10;
    }

    /**
     * Calculates the vertical acceleration.
     */
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
        //TODO : MAKE IT WORK
    }

    /**
     * Move method for moving.
    */
    void move() {
        //TODO : MAKE IT WORK
        x += horizontalSpeed;
    }
    
}