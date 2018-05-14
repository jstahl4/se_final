package sample;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer implements Runnable{
    Socket csocket;

    MultiThreadServer(Socket csocket) {
        this.csocket = csocket;
    }

    @Override
    public void run() {
        try {
            PrintStream pstream = new PrintStream(csocket.getOutputStream());
            pstream.println("hello");
            BufferedReader input = new BufferedReader(new InputStreamReader(csocket.getInputStream()));
            String url = input.readLine();

            /* GET ANALYSIS HERE */
            System.out.println(url);

            pstream.close();
            csocket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
