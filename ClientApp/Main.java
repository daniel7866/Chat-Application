import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {
    private static final int WIDTH = 1200, HEIGHT = 800;
    private static ServerPanel serverPanel;
    private static PrintWriter out = null;
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame();
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        serverPanel = new ServerPanel();
        frame.add(serverPanel);
        frame.setVisible(true);


        int port = 7777;
        Socket socket = null;

        BufferedReader in = null;
        String host = "10.100.102.9";

        try{
            socket = new Socket(host,port);
            out = new PrintWriter(socket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch (Exception e){
            System.out.println("CANT CONNECT");
            System.exit(1);
        }

        System.out.println("Connected");
        while(frame.isVisible()){
            serverPanel.txtMessage.setText("");
            serverPanel.txtChat.append(in.readLine() + "\n");
        }

        out.close();
        in.close();
        socket.close();
    }

    public static void send(){
        out.println(serverPanel.name + ": " +serverPanel.txtMessage.getText());
    }
}
