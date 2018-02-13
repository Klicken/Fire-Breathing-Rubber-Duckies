package Highscore;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;

/*
 *  This class will manage a server with socketconnection available on its IP adress and port for a client to connect to.
 *  The server can receive and send highscores to a client. If a client sends and highscore, it will be saved on a dat-file.
 *  If the client requests all highscores, the server will send all highscores saved on the dat-file back sorted.
 */

public class Server {

    public static void main(String[] args) {
        new Server().runServer();
    }

    // A list for highscores
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


    /*
     * This method will start the server, it will open a ServerSocket on decided port, to make the server available
     * to connect to. It will then open an ObjectOutputStream and ObjectInputStream to read and write highscore objects
     * between the connected client. If the client sends a highscore, it will be added to the dat-file. If the client
     * wants to get all saved highscores, they will be retrieved from the dat-file, sorted and sent back to client.
     */
    public void runServer() {
        while(true) {
            try {
                ServerSocket socketConnection = new ServerSocket(3000);
                System.out.println("Server Waiting");
                Socket pipe = socketConnection.accept();

                serverOutputStream = new
                        ObjectOutputStream(pipe.getOutputStream());
                serverOutputStream.writeObject(getScores());

                serverInputStream = new
                        ObjectInputStream(pipe.getInputStream());
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
    }

    // This method will read all highscores from the dat-file and sort them.
    public ArrayList<Highscore> getScores() {
        readFromFile();
        Collections.sort(tmpList);
        return tmpList;
    }

    /*This method will first read the already saved scores,
    add a new highscore and write it to the file unsorted.*/
    public void addScore(Highscore score) {
        readFromFile();
        tmpList.add(score);
        writeToFile();
    }


    //This method will write our highscore objects to a dat-file
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

    //This method will read objects from the saved dat-file
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