package conversation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PlayerComm {

    public static void main(String[] args) throws Exception {
    	System.out.println("PlayerComm");
        String playerName = args[0];
        Player player = new Player(playerName);

        if ("Player1".equals(playerName)) {
            try {
                ServerSocket serverSocket = new ServerSocket(12345);
                System.out.println("Player1 is waiting for connection...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Player1 connected.");

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                player.sendMessage(out, "X");
                player.msgCounter++;

                while (player.getMsgCounter() < 10) {
                    String receivedMessage = in.readLine();
                    player.receiveMessage(receivedMessage);
                    receivedMessage = receivedMessage + " (message " + ++player.msgCounter + ")";
                    player.sendMessage(out, receivedMessage);
                }

                serverSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                Socket socket = new Socket("localhost", 12345);
                System.out.println(playerName + " connected to Player1.");

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                while (player.getMsgCounter() < 10) {
                    String receivedMessage = in.readLine();
                    player.receiveMessage(receivedMessage);
                    receivedMessage = receivedMessage + " (message " + ++player.msgCounter + ")";
                    player.sendMessage(out, receivedMessage);
                }

                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
