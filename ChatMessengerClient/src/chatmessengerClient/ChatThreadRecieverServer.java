/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatmessengerClient;

import ThreadFiles.*;
import java.io.DataInputStream;
import java.net.ServerSocket;

import java.net.Socket;

/**
 *
 * @author somanbaqai
 */
public class ChatThreadRecieverServer implements Runnable {

    String conn;
    int port;
    Socket socket;

    public ChatThreadRecieverServer(String conn) {
        this.conn = conn;
    }

    public ChatThreadRecieverServer(String conn, int port, Socket socket) {
        this.conn = conn;
        this.port = port;
        this.socket = socket;
    }

    public ChatThreadRecieverServer() {
    }

    @Override
    public void run() {
        try {
            //   System.out.println(conn + " connection established");
            
             DataInputStream dataInputStreamRec = new DataInputStream(socket.getInputStream());
            while (true) {
                String strRec = dataInputStreamRec.readLine();
                System.out.println("Message Recived at " + conn + " is: " + strRec);

                if (strRec.equalsIgnoreCase("exit")) {
                    socket.close();
                    break;

                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
