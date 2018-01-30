import java.io.*;
import java.util.*;

public class HighScoreManager {

    private ArrayList<Highscore> highscores;
    private static final String HIGHSCORE_FILE = "highscore.dat";
    //Initialising an in and outputStream for working with the file
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    public HighScoreManager() {
        //initialising the scores-arraylist
        highscores = new ArrayList<Highscore>();
    }

    public ArrayList<Highscore> getScores() {
        readFromFile();
        Collections.sort(highscores);
        return highscores;
    }

    public void addScore(String name, int score) {
        readFromFile();
        highscores.add(new Highscore(score, name));
        writeToFile();
    }



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
            // TODO Auto-generated catch block
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
