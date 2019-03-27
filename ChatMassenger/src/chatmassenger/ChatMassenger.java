/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatmassenger;

import java.net.*;
import java.io.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author somanbaqai
 */
public class ChatMassenger {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int port = 8001;
        Socket socket=null;
        try {
            socket = new Socket("localhost", port);
        } catch (IOException ex) {
            System.out.println("port number");
        }
        
        Thread recieverThread = new Thread(new ChatThreadRecieverServer("Client",port,socket));
        recieverThread.start();
        Thread senderThread = new Thread(new ChatThreadSenderServer("Client",socket));
        senderThread.start();
        
        
         

    }

}
