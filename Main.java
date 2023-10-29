import javax.swing.JFrame;

/**
 * The main class of the game.
 * @author Diederik Webster
 * @studentnumber 1957880
 * 
 * @author Matthijs Smulders
 * @studentnumber 1973169
 * 
*/
public class Main {
    
    Game game = new Game();

    boolean forceReset = false;

    /**
     * Restarts the game by resetting the players and cameras position,
     *  disposing the current frame and re-initiating the game.
     * If this function gets called from losing, forceReset is not set to true, 
     *      so the level number is not reset.
     *      Else, the function gets called from the pressing the backspace, for resetting the game,
     *      and forceReset is true, then the level number is set to 1.
     * 
     * To prevent re-initiating the game before the new save file is correctly created,
     *     the thread sleeps for 100 milliseconds.
     */
    void restartGame() {
        if (!forceReset) {
            game.writer.createSaveFile(game.levelNumber + "");
        } else {
            game.writer.createSaveFile("1");
            forceReset = false;
        }
        
        game.player.x = 125;
        game.player.y = 524;
        game.level.x0 = 0;
        game.frame.setVisible(false);
        game.frame.dispose();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        initGame();
    }

    /**
     * Creates a new save file for the next level, resets the players and cameras position,
     * disposes of the old frame and re-initiates the game.
     */
    void nextLevel() {
        game.writer.createSaveFile((game.levelNumber + 1) + "");
        game.frame.setVisible(false);
        game.frame.dispose();
        game.player.x = 125;
        game.player.y = 524;
        game.level.x0 = 0;

        // also a check after level 3
        if (game.levelNumber == 3) {
            game.levelNumber = 1; // then the game is reset to the first level
            game.writer.createSaveFile("1");
            endScreen();
        } else {
            initGame();
        }
    }

    /**
     * Works as a sort constructor for the Game class. We prevent using a lot of new Game()s.
     *      (see documentation for more info on why we use the Main class)
     * Adds a frame to the game, reads the save file and creates a new level.
     * If the save file is not empty on the lines for the player x, player y and level x0, 
     *      it updates the players and cameras position. 
     * Else it resets them to their default values.
     * 
     * Then start up the game by calling the run() function.
     * 
     * While the game is not won, lost or restarted, the thread sleeps for 1 millisecond.
     * If one of these conditions is reached, it exits the loop, 
     *      checks which of the conditions is true, and excutes the appropriate function.
     */
    void initGame() {
        
        game.frame = new JFrame("Game"); // Create a new frame
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setSize(game.width, game.height);
        game.frame.setResizable(false);
        game.frame.setVisible(true);

        game.movementListener = new MovementListener();

        game.saveData = new ReadSaveFile().readSaveFile();

        game.levelNumber = game.saveData[0]; 

        game.level = new Level(game.player, game.levelNumber);
        
        game.win = false;
        game.lose = false;

        // if the rest of the savefile is not empty, take the default values
        if (game.saveData[1] != -1 && game.saveData[2] != -1 && game.saveData[3] != -1) {
            game.player.x = game.saveData[1];
            game.player.y = game.saveData[2];
            game.level.x0 = game.saveData[3];
        } else {
            game.player.x = 125;
            game.player.y = 524;
            game.level.x0 = 0;
        }

        game.frame.add(game.level);

        game.frame.addKeyListener(game.movementListener);

        game.run();

        // as long as the game is not over, do nothing
        while (!game.lose && !game.win && !game.restartButtonPressed) {
            //sleep
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }

        if (game.restartButtonPressed) {
            // force reset the game to the default state
            game.restartButtonPressed = false;
            forceReset = true;
            System.out.println("game level number: " + game.levelNumber);
            
            restartGame(); 

        } else if (game.lose) { // if the game is lost, restart the game
            game.timer.stop();
            restartGame();

        } else if (game.win) { // if the game is won, go to the next level
            game.timer.stop();
            nextLevel();
        }
    }

    /**
     * When the game is intialized for the first time, 
     *      it shows the mainScreen. It creates a new frame,
     *      adds a keylistener and the mainScreen to the frame.
     * After that when the Enter key is pressed, it closes the frame and disposes it.
     * And then intializes the game.
    */
    void mainScreen() {
        MainScreen mainScreen = new MainScreen("start");
        JFrame startframe = new JFrame();
        MovementListener listener = new MovementListener();

        startframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startframe.setSize(1600, 600);
        startframe.setResizable(false);
        startframe.setVisible(true);
        startframe.addKeyListener(listener);
        startframe.add(mainScreen);
        mainScreen.repaint();
        

        while (!listener.enterKeyPressed) { // while not the enterkey is pressed do nothing
            try {
                Thread.sleep(1);
                mainScreen.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        startframe.setVisible(false);
        startframe.dispose();

        // We sleep the thread to prevent an error where the frame is not fully disposed yet
        //      and a new frame is created before the old one is disposed.
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        initGame();
    }

    /**
     * When the game is won or lost, it shows the endscreen. It creates a new frame,
     *      adds a keylistener and the endscreen to the frame.
     * After that when the Enter key is pressed, it closes the frame and disposes it.
     * And then intializes the game again.
    */
    void endScreen() {
        MainScreen mainScreen = new MainScreen("end");
        JFrame startframe = new JFrame();
        MovementListener listener = new MovementListener();

        startframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startframe.setSize(1600, 600);
        startframe.setResizable(false);
        startframe.setVisible(true);
        startframe.addKeyListener(listener);
        startframe.add(mainScreen);
        mainScreen.repaint();
        
        // while not the enterkey is pressed do nothing
        while (!listener.backspaceKeyPressed || !listener.enterKeyPressed) { 
            try {
                Thread.sleep(1);
                mainScreen.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        startframe.setVisible(false);
        startframe.dispose();
    
        // we sleep the tread to prevent an error where the frame is not fully disposed yet
        //      and a new frame is created before the old one is disposed.
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        initGame();
    }
    
    public static void main(String[] args) {
        new Main().mainScreen(); // start the game, by showing the mainScreen
    }
}
