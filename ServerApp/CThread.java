import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CThread extends Thread {


    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    public String msg;

    public CThread(Socket socket, String msg){
        this(socket);
        this.msg = msg;
    }

    public CThread(Socket socket){
        this.socket = socket;
        try{
            out = new PrintWriter(socket.getOutputStream(),true);
            Server.outs.add(out);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch (IOException e){
            System.out.println("Could not find i/o connection");
        }
    }

    @Override
    public void run() {
        String inputLine;

        try {
            while ((inputLine = in.readLine())!= null){
                //out.println(inputLine);
                msg = inputLine;
                for (PrintWriter writer:Server.outs) {
                    writer.println(msg);
                }
                System.out.println(msg);
            }
        }catch (IOException e){
            System.out.println("Could not read from connection");
        }
    }
}
