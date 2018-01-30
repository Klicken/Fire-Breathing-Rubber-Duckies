import java.io.Serializable;
import java.util.Comparator;

public class Highscore implements Serializable, Comparable<Highscore>
{
    private int score;
    private String name;

    public Highscore(int score, String name)
    {
        this.score = score;
        this.name = name;
    }

    public int getScore()
    {
        return this.score;
    }

    public String getName()
    {
        return this.name;
    }

    @Override
    public int compareTo(Highscore score1) {
        return ((Integer)(score1.getScore())).compareTo(getScore());
    }
}
