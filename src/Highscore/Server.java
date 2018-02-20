package Highscore;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 *  Manage a server with socketconnection available on its IP adress and port for a client to connect to.
 *  Will start a new thread when called upon.
 *  The server can receive and send highscores to a client. If a client sends and highscore, it will be saved on a dat-file.
 *  If the client requests all highscores, the server will send all highscores saved on the dat-file back sorted.
 */

public class Server extends Thread{


    // A temporary list for highscores
    ArrayList<Highscore> tmpList = new ArrayList<Highscore>();
    Highscore highscore = null;
    // An in and outputstream for objects, to read and write to a dat-file.
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;
    // An in and outputstream for objects, to read and send objects between server and client.
    ObjectOutputStream serverOutputStream = null;
    ObjectInputStream serverInputStream = null;
    // The name of the dat-file of saved highscores.
    private static final String HIGHSCORE_FILE = "highscore.dat";
    // A socket of the connection with the client
    private Socket s;

    /**
     * Constructor of server, will take a socket which contains a connection from client.
     *
     * @param s the socket connection from client
     */
    public Server(Socket s){
        this.s=s;
    }

    /**
     * It will open an ObjectOutputStream and ObjectInputStream to read and write highscore objects
     * between the connected client. If the client sends a highscore, it will be added to the dat-file. If the client
     * wants to get all saved highscores, they will be retrieved from the dat-file, sorted and sent back to client.
     */
    public void runServer() {
            try {
                serverOutputStream = new
                        ObjectOutputStream(s.getOutputStream());
                serverOutputStream.writeObject(getScores());

                serverInputStream = new
                        ObjectInputStream(s.getInputStream());
                highscore = (Highscore) serverInputStream.readObject();

                if (highscore != null)
                    addScore(highscore);

            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException e) {
                System.out.println("Error initializing stream");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (serverOutputStream != null) {
                        serverOutputStream.flush();
                        serverOutputStream.close();
                    }
                    if(serverInputStream != null) {
                        serverInputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    /**
     *  Get all highscores from the dat-file and sort them.
     *
     *  @return a sorted arraylist with all saved highscores
     */
    public ArrayList<Highscore> getScores() {
        readFromFile();
        Collections.sort(tmpList);
        return tmpList;
    }

    /**
     * Read the already saved scores, add a new highscore and write it to the file unsorted.
     *
     * @param score the score to be saved on the server
     */
    public void addScore(Highscore score) {
        readFromFile();
        tmpList.add(score);
        writeToFile();
    }


    /**
     * Write highscore objects to the higscore-file
     */
    public void writeToFile() {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
            outputStream.writeObject(tmpList);

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
                e.printStackTrace();
            }
        }
    }

    /**
     * Read all highscores from the saved highscore-file and save them in an arraylist
     */
    public void readFromFile() {
        try {
            inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
            tmpList = (ArrayList<Highscore>) inputStream.readObject();

        } catch (FileNotFoundException e) {
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
                e.printStackTrace();
            }
        }
    }
}