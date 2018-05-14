package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Controller {
    public Button query_button;
    public TextArea log_area;
    public TextField ip_box;
    public TextField port_box;
    public TextField site_box;
    private Socket socket;

    public void run_query(ActionEvent actionEvent) {
        try {
            String host = ip_box.getText();
            InetAddress addr = InetAddress.getByName(host);
            int port = Integer.parseInt(port_box.getText());
            socket = new Socket(addr, port);
            String url = site_box.getText();

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            bw.write(url);
            bw.flush();
            log_area.appendText("Sent " + host + ":" + port + " to the server\n");

            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            String response = br.readLine();
            log_area.appendText("Response: " + response + "\n");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
