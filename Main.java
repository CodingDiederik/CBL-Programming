public class Main {
    
    Game game; 

    void restartGame() {
        System.out.println("restart");
        game.writer.createSaveFile(game.level.level_number + "");
        //game.frame.setVisible(false);
        //game.frame.dispose();
        //game = null;
        game.level.gameState = "paused";
        game.lose = false;
        initGame();
            
    }

    void nextLevel() {
        System.out.println("next level");
        game.writer.createSaveFile((game.level.level_number + 1) + "");
        //game.frame.setVisible(false);
        //game.frame.dispose();
        //game = null;
        game.level.gameState = "paused";
        game.win = false;
        initGame();
    }

    void initGame() {
        game = new Game();
        game.run();

        while (!game.lose && !game.win) {
            //sleep
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("out of loop");
        if (game.lose) {
            restartGame();
        } else if (game.win) {
            nextLevel();
        }
    }
    
    public static void main(String[] args) {
        new Main().initGame();
    }
}
