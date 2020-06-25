import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static String msg;
    public static ArrayList<PrintWriter> outs = new ArrayList<>();
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        boolean listening = true;
        int port = 7777;
        try {
            serverSocket = new ServerSocket(port);
        }catch (Exception e){
            System.out.println("Problem with server");
        }

        System.out.println("Server ready");

        Socket socket = null;

        while (listening){
            try{
                socket = serverSocket.accept();
                new CThread(socket,msg).start();
            }catch (Exception e){
                System.out.println("Accept failed");
                System.exit(1);
            }
        }

        try {
            serverSocket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
