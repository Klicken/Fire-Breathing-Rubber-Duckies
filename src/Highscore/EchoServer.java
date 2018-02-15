package Highscore;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class EchoServer {
    public static void main(String args[]){

        Socket s=null;
        ServerSocket ss=null;
        System.out.println("Server Listening......");

        try {
            ss = new ServerSocket(11111);
            }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("Server error");
            }

        while(true){
            try {
                s= ss.accept();
                System.out.println("connection Established");
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