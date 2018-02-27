package Highscore;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * This class will manage highscores, makes it possible to read and add highscores to a dat-file saved on a server.
 *
 * @author Oscar Nilsson
 * @version 2018-02-21
 */

public class HighScoreManager {

    // A temporary list to handle highscores
    private ArrayList<Highscore> highscores = new ArrayList<Highscore>();
    // A socket to handle connection between client/server
    private Socket socketConnection = null;
    // An objectinputstream and objectoutputstream to receive and send highscores between client/server.
    private ObjectOutputStream clientOutputStream = null;
    private ObjectInputStream clientInputStream = null;

    /**
     *  Open a socketconnection to a server with its IP adress and port.
     */
    private void connectToServer() {
        try {
            socketConnection = new Socket("127.0.0.1", 3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will add a new highscore to the server through a socketconnection and objectoutputstream.
     *
     * @param score the score to be sent to server
     */
    public void writeHighScoreToServer(Highscore score) {
        connectToServer();
        try {
            ObjectInputStream  clientInputStream = new ObjectInputStream(socketConnection.getInputStream());
            highscores = (ArrayList<Highscore>) clientInputStream.readObject();
            clientOutputStream = new ObjectOutputStream(socketConnection.getOutputStream());
            clientOutputStream.writeObject(score);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (clientOutputStream != null) {
                    clientOutputStream.flush();
                    clientOutputStream.close();
                    socketConnection.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get all highscores saved on the server, passing null to the outputstream to tell the server
     * to only read highscores and send them back, without adding a new highscore.
     *
     * @return an arraylist of all saved highscores
     */
    public ArrayList<Highscore> readHighscoreFromServer() {
        connectToServer();
        try {
            clientInputStream = new ObjectInputStream(socketConnection.getInputStream());
            highscores = (ArrayList<Highscore>) clientInputStream.readObject();
            clientOutputStream = new ObjectOutputStream(socketConnection.getOutputStream());
            clientOutputStream.writeObject(null);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (clientInputStream != null) {
                    clientInputStream.close();
                    socketConnection.close();
                }
                if (clientOutputStream != null) {
                    clientOutputStream.close();
                    socketConnection.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return highscores;
    }
}