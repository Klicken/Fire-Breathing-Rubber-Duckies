package Highscore;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class will create new connections to the server when a client try to connect to the correct IP adress
 * and port. If the client is successfully connected, a new ServerThread will be created for each client.
 *
 * @author Oscar Nilsson
 * @version 2018-02-21
 */

public class Server {
    public static void main(String args[]){

        Socket s=null;
        ServerSocket ss=null;
        System.out.println("Server Waiting");

        try {
            ss = new ServerSocket(3000);
            }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("Server error");
            }

        while(true){
            try {
                s= ss.accept();
                System.out.println("Connection Established");
                ServerThread st=new ServerThread(s);
                st.runServer();
                }

            catch(Exception e){
                e.printStackTrace();
                System.out.println("Connection Error");
                }
        }

    }

}