/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatmassenger;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author somanbaqai
 */
public class ChatMassengerServer {

    public static void main(String[] args) throws IOException {
        
        int port = 8001;
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();
        
        Thread recieverThread = new Thread(new ChatThreadRecieverServer("Server",port,socket));
        recieverThread.start();    
        Thread senderThread = new Thread(new ChatThreadSenderServer("Server",serverSocket,socket,port));
        senderThread.start();
        

//    

    }

}
