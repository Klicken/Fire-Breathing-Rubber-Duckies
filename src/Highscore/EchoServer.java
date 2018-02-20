package Highscore;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class will create new connections to the server when a client try to connect to the correct IP adress
 *  and port, by creating new Server objects which creates a new thread for every connected client.
 */

public class EchoServer {
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
                Server st=new Server(s);
                st.runServer();
                }

            catch(Exception e){
                e.printStackTrace();
                System.out.println("Connection Error");
                }
        }

    }

}