public class Game {
    private String name;
    private int score;

    public Game(String name) {
        this.name = name;
        this.score = 0;
    }

    public void play() {
        // code to play the game
        this.score += 10;
    }

    public int getScore() {
        return this.score;
    }
}
