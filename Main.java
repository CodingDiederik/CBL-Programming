import javax.swing.JFrame;

public class Main {
    
    Game game = new Game();

    boolean forceReset = false;

    void restartGame() {
        if (!forceReset) {
            game.writer.createSaveFile(game.levelNumber + "");
        } else {
            //game.writer.createSaveFile("1");
            game.writer.createSaveFile("0");
            game.levelNumber = 0;
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

    void nextLevel() {
        //System.out.println("next level");
        //game.writer.createSaveFile((game.level.level_number + 1) + "");
        game.writer.createSaveFile((game.levelNumber + 1) + "");
        game.frame.setVisible(false);
        game.frame.dispose();
        game.player.x = 125;
        game.player.y = 524;
        game.level.x0 = 0;

        initGame();
    }

    void initGame() {
        
        game.frame = new JFrame("Game"); // Create a new frame
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setSize(game.width, game.height);
        game.frame.setResizable(false);
        game.frame.setVisible(true);


        game.saveData = new ReadSaveFile().readSaveFile();
        game.levelNumber = game.saveData[0];

        game.level = new Level(game.player, game.levelNumber);
        
        game.win = false;
        game.lose = false;

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

        while (!game.lose && !game.win && !game.restartButtonPressed) {
            //sleep
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }

        //System.out.println("out of loop");

        if (game.restartButtonPressed) {
            game.restartButtonPressed = false;
            forceReset = true;
            System.out.println("game level number: " + game.levelNumber);
            
            restartGame();

        } else if (game.lose) {
            game.timer.stop();
            restartGame();

        } else if (game.win) {
            game.timer.stop();
            nextLevel();
        }
    }
    
    public static void main(String[] args) {
        new Main().initGame();
    }
}
