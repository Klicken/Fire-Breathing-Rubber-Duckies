package Highscore;

import java.io.Serializable;

/**
 * A highscore class which creates an object of an int score and string name
 */
public class Highscore implements Serializable, Comparable<Highscore> {
    private int score;
    private String name;

    /**
     * Constructor of highscore, takes a score and name.
     *
     * @param score the score from player
     * @param name the name of the player
     */
    public Highscore(int score, String name) {
        this.score = score;
        this.name = name;
    }

    /**
     * Gets the score of a highscore
     *
     * @return name of a highscore
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Gets the name of a highscore
     *
     * @return name of a highscore
     */
    public String getName() {
        return this.name;
    }

    /**
     * Overrides Comparables method, to be able to sort a list of highscores.
     * This methoed compares this highscore with the specified highscore for order.
     *
     * @param score1 the highscore to be compared
     * @return a negative integer, zero, or a positive integer as this highscore is less than, equal to,
     * or greater than the specified highscore.
     * @see Comparable
     */
    @Override
    public int compareTo(Highscore score1) {
        return ((Integer)(score1.getScore())).compareTo(getScore());
    }
}
