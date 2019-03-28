/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatmessengerClient;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author somanbaqai
 */
public class MainController implements Initializable {

    @FXML
    private TextField IpField;
    @FXML
    private TextField portField;
    @FXML
    private Button connectButton;

    String ip;
    @FXML
    private Label connectionLabel;
    public static int port;
    public static Socket socket = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        IpField.setText("localhost");
    }

    @FXML
    private void handleConnectAction(ActionEvent event) throws IOException, InterruptedException {
        ip = IpField.getText().trim();

        connectionLabel.setText(ip);
        try {
            port = Integer.parseInt(portField.getText());
            System.out.println(port);
            try {
                socket = new Socket(ip, port);
                System.out.println("socket established");
                ((Stage) IpField.getScene().getWindow()).close();
                Parent parent = FXMLLoader.load(getClass().getResource("MainChat.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Home - Chat Messenger Client");
                stage.setScene(new Scene(parent));
                stage.show();
            } catch (IOException ex) {
                connectionLabel.setText("Port Connection failed");
                System.out.println("socket connection failed");
            }

            System.out.println("Changing scene to main menu");
        } catch (NumberFormatException nfe) {
            connectionLabel.setText("Port Connection failed");
            System.out.println(nfe);
        }

    }

}
