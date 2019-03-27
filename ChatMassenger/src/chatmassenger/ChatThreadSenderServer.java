/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatmassenger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author somanbaqai
 */
public class ChatThreadSenderServer implements Runnable {

    String conn;
    int port = 8001;
    ServerSocket serverSocket ;//= new ServerSocket(port);
    Socket socket;// = serverSocket.accept();

    public ChatThreadSenderServer(String conn, ServerSocket serverSocket, Socket socket,int port) {
        this.conn = conn;
        this.serverSocket = serverSocket;
        this.socket = socket;
        this.port = port;
    }

    public ChatThreadSenderServer() {
    }

    public ChatThreadSenderServer(String conn,Socket socket) {
        this.conn = conn;
        this.socket = socket;
    }

   

    @Override
    public void run() {
        try {
            
           // System.out.println( conn + " connection established");

            DataInputStream dataInputStreamSen = new DataInputStream(System.in);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            
            while (true) {
//                Thread.sleep(1000);
                System.out.print("Enter msg from " + conn + ": ");
                
                String strRec = dataInputStreamSen.readLine();
                if(!strRec.isEmpty()){
                    printStream.println(strRec);
                    out.writeBytes(strRec + "\n");
                    
                }

                if (strRec.equalsIgnoreCase("exit")) {
                    serverSocket.close();
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
