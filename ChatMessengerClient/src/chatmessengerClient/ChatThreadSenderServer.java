/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatmessengerClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.collections.ObservableList;

/**
 *
 * @author somanbaqai
 */
public class ChatThreadSenderServer implements Runnable {

    String conn;
    int port = 8001;
    ServerSocket serverSocket;//= new ServerSocket(port);
    Socket socket;// = serverSocket.accept();
    ObservableList<String> chatMessages;
    int size = 0;
   static String TXT="";

    public static void setTXT(String TXTs) {
        TXT = TXTs;
    }

    public ChatThreadSenderServer(String conn, ServerSocket serverSocket, Socket socket, int port) {
        this.conn = conn;
        this.serverSocket = serverSocket;
        this.socket = socket;
        this.port = port;
    }

    public ChatThreadSenderServer() {
    }

    public ChatThreadSenderServer(String conn, Socket socket, ObservableList<String> chatMessages,String t) {
        this.conn = conn;
        this.socket = MainController.socket;
        this.chatMessages = chatMessages;
    }

    @Override
    public void run() {
        try {

            // System.out.println( conn + " connection established");
//            String initialString = "text";
//            InputStream targetStream = new ByteArrayInputStream(conn.getBytes());
            DataInputStream dataInputStreamSen = new DataInputStream(System.in);
            DataOutputStream os = new DataOutputStream(socket.getOutputStream());
            PrintStream printStream = new PrintStream(socket.getOutputStream());
                                os.flush();

            while (true) {
                // System.out.print("Enter msg from " + conn + ": ");
              //  System.out.println(chatMessages.size());
              
                if (TXT=="") {
                    os.flush();
                    System.out.println("no new chat");
                    os.flush();
                 // Thread.sleep(1000);
                } else {
                    String strRec = chatMessages.get(chatMessages.size()-1);//dataInputStreamSen.readLine();
                    os.flush();
               //     os.writeUTF(chatMessages.get(chatMessages.size()-1));
                  

             //       System.out.println(strRec);
//                    if (!strRec.isEmpty()) {
                        printStream.println(TXT);
                        printStream.flush();
                          size = chatMessages.size();
                          TXT="";
                        
                       
                  //      chatMessages=null;
//
//                    }

                    if (strRec.equalsIgnoreCase("exit")) {
                        serverSocket.close();
                        break;
                    }
                }

            }

        } catch (Exception e) {
            System.out.println("sender");
            System.out.println(e);
        }
        
     
    }

}
