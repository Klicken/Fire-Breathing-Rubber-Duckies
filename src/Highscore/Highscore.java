package Highscore;

import java.io.Serializable;

//A highscore class which creates an object of an int score and string name

public class Highscore implements Serializable, Comparable<Highscore>
{
    private int score;
    private String name;

    public Highscore(int score, String name)
    {
        this.score = score;
        this.name = name;
    }

    //This method returns the score of a highscore
    public int getScore()
    {
        return this.score;
    }

    //This method returns the name of a highscore
    public String getName()
    {
        return this.name;
    }

    /*This method overrides the compareTo method in Comparable,
    which is used to sort the highscores in highscoremanager*/
    @Override
    public int compareTo(Highscore score1) {
        return ((Integer)(score1.getScore())).compareTo(getScore());
    }
}
