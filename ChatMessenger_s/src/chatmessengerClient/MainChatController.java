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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author somanbaqai
 */
public class MainChatController implements Initializable{

    @FXML
    private ListView <String> chatMessageList;
    public static ObservableList<String> chatMessages = FXCollections.observableArrayList();
    @FXML
    private Button sendButton;
    @FXML
    private TextField messagaeField;
    public static String txt;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Thread recieverThread = new Thread(new ChatThreadRecieverServer("Server",MainController.port,MainController.socket,chatMessages));
        recieverThread.start();    
        Thread senderThread = new Thread(new ChatThreadSenderServer("Server",MainController.serverSocket,MainController.socket,MainController.port,chatMessages,txt));
        senderThread.start();
        chatMessageList.setItems(chatMessages);
        
        
    }    

    @FXML
    private void handleSendButton(ActionEvent event) {
        txt = messagaeField.getText().trim();
        ChatThreadSenderServer.setTXT(messagaeField.getText());
        chatMessages.add("Server: " + messagaeField.getText());
        messagaeField.setText("");
        //System.out.println("test " + chatMessages.get(chatMessages.size()-1));
        
    }
    
}
