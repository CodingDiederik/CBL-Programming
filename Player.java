//import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;
//import java.lang.Math;

/**
 * Player class.
*/
public class Player extends JPanel {
    int x = 250; // start coordiantes for the player
    int y = 524;

    int spriteWidth = 50 / 2; // size of the player divided in half
    int spriteHeight = 50 / 2;

    int verticalSpeed = 0; // speed of the player
    int horizontalSpeed = 0;

    int verticalAcceleration = 0; // acceleration of the player
    int horizontalAcceleration = 0;

    int MAX_SPEED = 6; // maximum speed of the player

    int change_x = 0;
    int change_y = 0;

    boolean isJumping = false;
    boolean isFalling = false;

    String previousDirection;
    int jumpStep = 0;
    int gravityStep = 0;

    /**
     * Checks if the player is on the ground.
    */
    boolean isOnGround(int[][] level) {
    
        System.out.println("x: " + x + " y: " + y);
        if (level[(x - spriteWidth) / 50][(y + spriteHeight + 1) / 50] == 1 || level[(x + spriteWidth) / 50][(y + spriteHeight + 1) / 50] == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the player collides with an object.
    */
    boolean isValidMove(int[][] level, String direction) {

        calculateChangeX(direction, level); // calculate the change in x and y coordinates
 
        if ("left".equals(direction)) { 
            // now we need to check if the player can move to the left
            
            for (int tryx = 0; tryx > change_x + 1; tryx--) { // check for which x coordinate the player can move
                
                if (level[((this.x - this.spriteWidth - tryx) / 50)][(this.y /*+ this.spriteHeight*/) / 50] == 1) {
                    // if a collision is detected
                    change_x = tryx - 1; // set the change in x coordinates to the x coordinate where the player can move

                    if (tryx != 0) { // if the player can move, move the player
                        move();
                    } 

                    horizontalSpeed = 0; // set the horizontal speed to 0
                    return false;
                }
            }

            return true;
            
        } else { // direction == "right"
            // now we need to check if the player can move to the right
            
            for (int tryx = 0; tryx < change_x + 1; tryx++) {
                
                if (level[((this.x + this.spriteWidth + tryx) / 50)][(this.y + this.spriteHeight) / 50] == 1) {
                    change_x = tryx - 1; // set the change in x coordinates to the c coordinate where the player can move

                    if (tryx != 0) { // if the player can move, move the player
                        move();
                    }

                    horizontalSpeed = 0; // set the horizontal speed to 0
                    return false;
                }
            }

            return true;
        }
    }

    /**
     * The method for calulating the jump.
    */
    void jump(int[][] level) {
        verticalAcceleration = verticalAcceleration(level);

        verticalSpeed = verticalAcceleration;
    }

    /**
     * Calculates the change in x and y coordinates.
    */
    void calculateChangeX(String direction, int[][] level) {

        horizontalAcceleration = horizontalAcceleration(direction); // calculate the horizontal acceleration
        horizontalSpeed += horizontalAcceleration; // calculate the horizontal speed

        if (horizontalSpeed > MAX_SPEED) { // check if the speed is not too high
            horizontalSpeed = MAX_SPEED;
        } else if (horizontalSpeed < -MAX_SPEED) {
            horizontalSpeed = -MAX_SPEED;
        }
        change_x = horizontalSpeed;
    }

    // TODO: More advanced? Functions to calculate the acceleration

    /**
     * Calculates the horizontal acceleration.
    */
    int horizontalAcceleration(String direction) { 

        if ("stop".equals(direction)) {
            return decelerationCalculator(horizontalSpeed); // always decelerate
        }
        if (horizontalSpeed == 0) { // if the player is not moving, accelerate
            return accelerationCalculator(horizontalSpeed, direction);
        }
        if (horizontalSpeed > 0) { // if the action is wanted: accelerate else decelerate
            if ("right".equals(direction)) {
                return accelerationCalculator(horizontalSpeed, direction);
            } else {
                return decelerationCalculator(horizontalSpeed);
            }
            
        //speed < 0: action is wanted: decelerate else accelerate
        } else if ("left".equals(direction)) {
            return accelerationCalculator(horizontalSpeed, direction);
        } else {
            return decelerationCalculator(horizontalSpeed);
        }
        
    }

    /**
     * Calculates the acceleration.
    */
    int accelerationCalculator(int speed, String direction) { // we take the positive site of parabole
        // gently make the player move faster

        if ("left".equals(direction)) { // if the player is moving left
            if (speed < -10) { // if the player is moving faster than -10
                return -5;
            } else { // if the player is moving slower than -10
                return -3;
            }

        } else { // player is moving right
            if (speed > 10) { // if the player is moving faster than 10
                return 5;
            } else { // if the player is moving slower than 10
                return 3;
            }
        }
        
    }

    /**
     * Calculates the deceleration.
    */
    int decelerationCalculator(int speed) {
        // gently make the player move slower
        if (speed > -3 || speed < 3) { // if the player is moving slower than 3, make speed 0
            return 0;
        } else if (speed < 0) { // slowly make the player move slower
            return 3;
        } else {
            return -3;
        }
    }

    /**
     * Calculates the vertical acceleration.
     */
    int verticalAcceleration(int[][] level) {
        if (isJumping) { // if the player is jumping calculate the speed
            return jumpCalculator(verticalSpeed);

        } else if (isFalling) { // if the player is falling calculate the speed
            System.out.println("falling");

            if (isOnGround(level)) { // if the player is on the ground, stop falling
                isFalling = false;
                gravityStep = 0;
                return 0;

            } else {
                return gravityCalculator(verticalSpeed);
            }            
        } else {
            return 0;
        }   
    }

    /**
     * Calculates the jump.
    */
    int jumpCalculator(int speed) {
        // make a counter
        System.out.println("jumpStep: " + jumpStep);
        if (jumpStep < 25) {
            jumpStep++;
            return 8;
        } else if (jumpStep < 30) {
            jumpStep++;
            return 4;
        } else {
            isJumping = false;
            jumpStep = 0;
            isFalling = true;
            return 0;
        }
    }

    /**
     * Calculates the gravity.
    */
    int gravityCalculator(int speed) {
        if (gravityStep < 10) {
            gravityStep++;
            return -4;
        } else {
            return -8;
        }
    }

    /**
     * Move method for actually updating the coordinates.
    */
    void move() {
        x += horizontalSpeed;
        y -= verticalSpeed;
    }

    /**
     * Move method for when no input detected.
     * TODO: THIS DOES MORE THAN THAT
    */
    void notMovingHorizontal() {
        horizontalSpeed = horizontalAcceleration("stop");
        move();
    }
}