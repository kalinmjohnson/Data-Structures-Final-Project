package finalproject;

public class Game {
    int highScore;
    int size;

    public void setHighScore(int hScore) {
        highScore = hScore;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setSize(int s) {
        size = s;
    }

    public int getSize() {
        return size;
    }

    Game (int hScore, int s) {
        highScore = hScore;
        size = s;
    }
}
