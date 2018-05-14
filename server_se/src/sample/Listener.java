package sample;

import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener implements Runnable {
    ServerSocket ssock = null;
    TextArea log_area = null;
    public Listener(ServerSocket ssock, TextArea log_area) {
        this.ssock = ssock;
        this.log_area = log_area;
    }

    public void run(){
        Socket sock = null;
        while (true) {
            try {
                sock = ssock.accept();
            } catch (IOException e) {
                System.out.println(e.toString());
            }
            log_area.appendText("Client Connected! " + sock.getInetAddress());
            new Thread(new MultiThreadServer(sock)).start();
        }
    }
}
