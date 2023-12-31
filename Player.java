/**
 * Player class.
*/
public class Player {
    int x = 125; // start coordiantes for the player
    int y = 524;

    int spriteWidth = 50 / 2; // size of the player divided in half
    int spriteHeight = 50 / 2;

    int verticalSpeed = 0; // speed of the player
    int horizontalSpeed = 0;

    int verticalAcceleration = 0; // acceleration of the player
    int horizontalAcceleration = 0;

    int maximumVelocity = 9; // maximum speed of the player

    int changex = 0; // the amount the player is going to move in 1 step

    boolean isJumping = false; // boolean to check if the player is jumping or falling
    boolean isFalling = true; 

    int jumpStep = 0; // step counter for the jump and gravity
    int gravityStep = 0;

    /**
     * Checks if the player is on the ground.
     * First: If the player is below the level or above the level, don't check for collisions.
     * Second: If the player is falling, check if the player can move down the gravity speed,
     *        if not, move as far as possible.
     *        If the player is not falling, check if the player can move down 5 pixels, 
     *              if not, the player is on the ground.
     * (see documentation for more information)
    */
    boolean isOnGround(int[][] level) {
        
        if (y > level[0].length * 50 - 45 || y - spriteHeight < 0) { 
            return false;
        } 

        if (isFalling) { 
            if (level[(x - spriteWidth) / 50][(y + spriteHeight) / 50] == 1
                || level[(x + spriteWidth) / 50][(y + spriteHeight) / 50] == 1) {
                verticalSpeed = 0;
                move(level);
                this.isFalling = false;
                return true;

            } else {
                for (int tryY = 1; tryY < 18; tryY++) {
                    if (level[(x - spriteWidth) / 50][(y + spriteHeight + tryY) / 50] == 1 
                        || level[(x + spriteWidth) / 50][(y + spriteHeight + tryY) / 50] == 1) {

                        verticalSpeed = tryY;
                        move(level);

                        return true;
                    }
                }

                return false;
            }

        } else {
            return level[(x - spriteWidth + 10) / 50][(y + spriteHeight + 5) / 50] == 1 
                || level[(x + spriteWidth - 10) / 50][(y + spriteHeight + 5) / 50] == 1;
        }
    }


    /**
     * Checks if the player can move left.
     * First: If the player is below the level or above the level, don't check for collisions.
     * Second: Check for which x coordinate the player can move.
     * Third: If a collision is detected make the player only move as far as it is allowed to go.
     * (see documentation for more information)
    */
    boolean checkXLeft(int[][] level) {
        if (y < 0 || y > level[0].length * 50 - 45) {
            return true;
        }

        for (int tryX = -1; tryX >= changex; tryX--) { 
            // check for which x coordinate the player can move

            if (level[(this.x - this.spriteWidth + tryX) / 50]
                [(this.y + this.spriteHeight - 1) / 50] == 1 
                || level[(this.x - this.spriteWidth + tryX) / 50]
                [(this.y - this.spriteHeight) / 50] == 1) {
                // if a collision is detected

                changex = tryX + 1; // set the change in x coordinates to the 
                // x coordinate where the player can move

                horizontalSpeed = 0; // set the horizontal speed to 0
                return false;
            }
        }

        return true; // if no collision is detected return true
    }

    /**
     * Checks if the player can move right.
     * First: If the player is below the level or above the level, don't check for collisions.
     * Second: Check for which x coordinate the player can move.
     * Third: If a collision is detected make the player only move as far as it is allowed to go.
     * (see documentation for more information)
    */
    boolean checkXRight(int[][] level) {
        if (y < 0 || y > level[0].length * 50 - 45) {
            return true;
        }

        for (int tryX = 1; tryX <= changex; tryX++) { 
            // check for which x coordinate the player can move
                
            if (level[((this.x + this.spriteWidth + tryX) / 50)]
                [(this.y + this.spriteHeight - 1) / 50] == 1 
                || level[((this.x + this.spriteWidth + tryX) / 50)]
                [(this.y - this.spriteHeight) / 50] == 1) {
                // if a collision is detected

                changex = tryX - 1; // set the change in x coordinates to the
                // x coordinate where the player can move

                horizontalSpeed = 0; // set the horizontal speed to 0
                return false;
            }
        }

        return true; // if no collision is detected return true
    }

    /**
     * Checks if the player can move up.
     * First: If the player is below the level or above the level, don't check for collisions.
     * Second: Check for which y coordinate the player can move.
     * Third: if a collision is detected make the player only move as far as it is allowed to go.
     * (see documentation for more information)
     */
    void calculateChangeY(int[][] level) {
        if (y < 0 || y > level[0].length * 50 - 45) {
            return;
        }

        for (int tryY = 1; tryY >= verticalSpeed; tryY--) {
            if (level[(x - spriteWidth) / 50][(y - spriteHeight + tryY) / 50] == 1
                || level[(x + spriteWidth) / 50][(y - spriteHeight + tryY) / 50] == 1) {
                verticalSpeed = tryY + 1;
                isJumping = false;
                isFalling = true;
                gravityStep = 0;
                jumpStep = 0;
                break;
            }
        }
    }

    /**
     * Depending on the direction, the method calculates the change in x.
     * 
     * Then it checks if the player can move in that direction, by calling the corresponding method.
     * If the player can move, the method returns true, else it returns false.
     * 
     * The else statement implies the direction is "stop". 
     * It checks which side the player is moving to, depending on the speed,
     * and calls the corresponding method.
     * 
    */
    void isValidMove(int[][] level, String direction) {

        calculateChangeX(direction, level); 

        if ("left".equals(direction)) { 
            checkXLeft(level);
            
        } else if ("right".equals(direction)) { 
            checkXRight(level);
        } else { 
            if (horizontalSpeed < 0) { 
                checkXLeft(level);
            } else if (horizontalSpeed > 0) { 
                checkXRight(level);
            }
        }
    }

    /**
     * This method calls method to change the y coordinates, as far as the player can move.
     * Then it calculates the vertical speed, depending on the acceleration.
    */
    void jump(int[][] level) {
        // check if the player can move up
        verticalSpeed = verticalAcceleration(level); // calculate the vertical acceleration
        calculateChangeY(level);
    }

    /**
     * Calculates the change in x and y coordinates.
     * It does this by calling the horizontalAcceleration method.
    */
    void calculateChangeX(String direction, int[][] level) {

        horizontalAcceleration = horizontalAcceleration(direction); 
        // calculate the horizontal acceleration
        
        horizontalSpeed += horizontalAcceleration; // calculate the horizontal speed

        if (horizontalSpeed > maximumVelocity) { // check if the speed is not too high
            horizontalSpeed = maximumVelocity;
        } else if (horizontalSpeed < -maximumVelocity) {
            horizontalSpeed = -maximumVelocity;
        }
        changex = horizontalSpeed;
    }

    /**
     * Calculates the horizontal acceleration.
     * It checks if the player needs to decelerate or to accelerate.
     * It does this by taking the current speed and checking if it matches with 
     * the direction.
    */
    int horizontalAcceleration(String direction) { 

        if ("stop".equals(direction)) {
            return decelerationCalculator(horizontalSpeed); // always decelerate
        }

        if (horizontalSpeed == 0) { // if the player is not moving, accelerate
            return accelerationCalculator(horizontalSpeed, direction);
        }

        // if the action is wanted: accelerate else decelerate
        if ("right".equals(direction) && horizontalSpeed > 0) {
            return accelerationCalculator(horizontalSpeed, direction);
        } 
        
        if (horizontalSpeed > 0) {
            return decelerationCalculator(horizontalSpeed);
        }
        
        //speed < 0: action is wanted: decelerate else accelerate
        if ("left".equals(direction)) {
            return accelerationCalculator(horizontalSpeed, direction);
        }

        return decelerationCalculator(horizontalSpeed);
        
        
    }

    /**
     * Calculates the acceleration.
     * It does so by checking if the speed is less than 10 and it will then accelerate faster.
    */
    int accelerationCalculator(int speed, String direction) { 
        // we take the positive site of parabole
        // to gently make the player move faster.

        if ("left".equals(direction)) { // if the player is moving left
            if (speed < -5) { // if the player is moving faster than -10
                return -2;
            } else { // if the player is moving slower than -10
                return -1;
            }

        } else { // player is moving right
            if (speed > 5) { // if the player is moving faster than 10
                return 2;
            } else { // if the player is moving slower than 10
                return 1;
            }
        }
        
    }

    /**
     * Calculates the deceleration.
     * It does by checking the speed and making it decrease by 3 units per iteration.
    */
    int decelerationCalculator(int speed) {
        // gently make the player move slower
        if (speed > -3 && speed < 3) { // if the player is moving slower than 3, make speed 0
            horizontalSpeed = 0;
            return 0;
        } else if (speed < 0) { // slowly make the player move slower
            return 2;
        } else {
            return -2;
        }
    }

    /**
     * Calculates the vertical acceleration.
     */
    int verticalAcceleration(int[][] level) {
        if (isJumping) { // if the player is jumping calculate the speed
            return jumpCalculator(verticalSpeed);

        } else if (isFalling) { // if the player is falling calculate the speed

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
        if (jumpStep < 9) { // use steps to determine the speed when jumping
            jumpStep++;
            return -16;
        } else if (jumpStep < 17) {
            jumpStep++;
            return -8;
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
            return 8;
        } else {
            return 16;
        }
    }

    /**
     * Move method for actually updating the coordinates.
    */
    void move(int[][] level) {
        if (verticalSpeed != 0 && y > 0) {
            for (int tryY = 1; tryY <= verticalSpeed; tryY++) {
                
                for (int tryX = 1; tryX <= changex; tryX++) {
                    if (level[(x + spriteWidth + tryX) / 50]
                        [(y + spriteHeight + tryY - 1) / 50] == 1) {

                        verticalSpeed = tryY - 1;
                        horizontalSpeed = tryX - 1;
                        break;
                    }
                }

                for (int tryX = -1; tryX >= changex; tryX--) {
                    if (level[(x - spriteWidth + tryX) / 50]
                        [(y + spriteHeight + tryY - 1) / 50] == 1) {

                        verticalSpeed = tryY - 1;
                        horizontalSpeed = tryX + 1;
                        break;
                    }
                }
                
            }

            for (int tryY = 1; tryY >= verticalSpeed; tryY--) {
                    
                for (int tryX = 1; tryX <= changex; tryX++) {
                    if (level[(x + spriteWidth + tryX) / 50]
                        [(y - spriteHeight + tryY - 1) / 50] == 1) {

                        verticalSpeed = tryY + 1;
                        horizontalSpeed = tryX - 1;
                        break;
                    }
                }
    
                for (int tryX = -1; tryX >= changex; tryX--) {
                    if (level[(x - spriteWidth + tryX) / 50]
                        [(y - spriteHeight + tryY - 1) / 50] == 1) {
                        verticalSpeed = tryY + 1;
                        horizontalSpeed = tryX + 1;
                        break;
                    }
                }


            }
        }

        x += horizontalSpeed;
        y += verticalSpeed;
    }

}