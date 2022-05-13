package finalproject;

/**
* ListNode Model
* 
* @author Kalin Johnson
* @author Nicholas Pederson
* @version Spring 2022
* 
*/

public class Game {
    int highScore;
    int size;
    Direction direction;
    
    /**
     * Change the highscore
     * @param new highscore
     * @return nothing
     */
    
    public void setHighScore(int hScore) {
        highScore = hScore;
    }
    
    /** Give the highscore
     * @param nothing
     * @return the highscore
     */

    public int getHighScore() {
        return highScore;
    }
    
    /**
     * Change the size
     * @param new size
     * @return nothing
     */

    public void setSize(int s) {
        size = s;
    }
    
    /** Give the size
     * @param nothing
     * @return the size
     */

    public int getSize() {
        return size;
    }
    
    /**
     * Constructor for Game
     * @param hScore
     * @param s
     * @param d
     * @return nothing
     */

    Game (int hScore, int s, Direction d) {
        highScore = hScore;
        size = s;
        direction = d;
    }
}
