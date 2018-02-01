import java.io.*;
import java.util.*;

/* This class will manage highscores. Its possible to read highscores
    from a saved dat-file, add new scores, sort and print them out
 */
public class HighScoreManager {
    //List of all highscores
    private ArrayList<Highscore> highscores;
    //The name of the file we will save all highscores too
    private static final String HIGHSCORE_FILE = "highscore.dat";
    //An in and outputstream
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    public HighScoreManager() {
        highscores = new ArrayList<Highscore>();
    }

    //This method will read from the dat-file, sort and return the saved highscores
    public ArrayList<Highscore> getScores() {
        readFromFile();
        Collections.sort(highscores);
        return highscores;
    }

    /*This method will first read the already saved scores,
    add a new highscore and write it to the file unsorted.*/
    public void addScore(String name, int score) {
        readFromFile();
        highscores.add(new Highscore(score, name));
        writeToFile();
    }


    //This method will write our highscore objects to a dat-file
    public void writeToFile()
    {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
            outputStream.writeObject(highscores);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Update] Error: " + e.getMessage());
            }
        }

    }

    //This method will read objects from the saved dat-file
    public void readFromFile()
    {

        try
        {
            inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
            highscores = (ArrayList<Highscore>) inputStream.readObject();

        }  catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Laad] IO Error: " + e.getMessage());
            }
        }

    }

    // This method will translate our highscore objects to Strings.
    public String getHighscoreString() {
        String highscoreString = "";
        int max = 10;

        ArrayList<Highscore> scores;
        scores = getScores();

        int i = 0;
        int x = scores.size();
        if (x > max) {
            x = max;
        }
        while (i < x) {
            highscoreString += (i + 1) + ".\t" + scores.get(i).getName() + "\t\t" + scores.get(i).getScore() + "\n";
            i++;
        }
        return highscoreString;
    }
}
