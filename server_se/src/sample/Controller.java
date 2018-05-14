package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Controller{
    public Button start_button;
    public TextArea log_area;
    public ServerSocket ssock;

    private String listening = "listening...";
    private String listen = "listen";
    private int port = 8556;


    public void start_or_stop(ActionEvent actionEvent) throws Exception{
        if(ssock == null) {
            ssock = new ServerSocket(port);
        }

        Socket clientSocket = null;
        try {
            clientSocket = ssock.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        clientSocket.getInputStream()));
        String inputLine, sentiment;
        inputLine = in.readLine();
        sentiment = handle_url(inputLine);
        out.println(sentiment);
        log_area.appendText(inputLine + ": " + sentiment + "\n");

        out.close();
        in.close();
        clientSocket.close();
        ssock.close();
        ssock = null;
    }

    private String handle_url(String url) {
        /* ANGJELO'S CODE HERE */
        return "positive";
    }
}


