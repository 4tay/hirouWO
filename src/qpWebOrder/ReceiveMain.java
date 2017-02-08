package qpWebOrder;

import java.net.*;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

public class ReceiveMain {

    public static void main(String[] args) throws IOException, JSONException {

        int portNumber = 4000;
        Date date = new java.util.Date();

        while(true)
        {
            try (ServerSocket serverSocket = new ServerSocket(portNumber))
            {
                while(true)
                {
                    Socket clientSocket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader incoming = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                    {
                        JSONObject input =new JSONObject(incoming.readLine());
                        ReceivingProtocol receivingProto = new ReceivingProtocol();

                        if(clientSocket.isConnected())
                        {
                            System.out.println("<" + date + "> *********************New Connection from " + clientSocket.getRemoteSocketAddress().toString() + "*********************");
                            out.print(1);
                            System.out.println("<" + date + "> New Message Received from " + clientSocket.getRemoteSocketAddress().toString());
                            receivingProto.run(input);
                            out.println(input);
                            out.flush();
                        }
                        serverSocket.close();
                    }
                }
            }
            catch (IOException e)
            {
//	            System.out.println("Exception caught when trying to listen on port "
//	                + portNumber + " or listening for a connection");
                System.out.println(e.getMessage());
            }
        }
    }
}