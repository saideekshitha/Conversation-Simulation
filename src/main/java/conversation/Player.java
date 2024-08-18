package conversation;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Player implements Runnable {
	private final String name;
	private final BlockingQueue<String> msgQueue;
	public int msgCounter;
	int tempmsgCounter = 0;

	public int getMsgCounter() {
		return msgCounter;
	}

	public void setMsgCounter(int msgCounter) {
		this.msgCounter = msgCounter;
	}

	public Player(String name) {
		this.name = name;
		this.msgQueue = new LinkedBlockingQueue<String>();
		this.msgCounter = 0;
	}

	public void receiveMessage(String msg) {
		msgQueue.offer(msg);
	}

	public void sendMessage(PrintWriter out, String message) {
		System.out.println(name + " sends: " + message);
		out.println(message);
		out.flush(); // Ensure the message is sent immediately
	}

	public void sendMessage(Player receiver, String msg) {
		System.out.println(name + " sends: " + msg);
		//receiver.receiveMessage(msg + " (message " + ++msgCounter + ")");
		receiver.receiveMessage(msg);
	}

	public void run() {
		try {
			while (this.msgCounter < 10) {
				String message = msgQueue.take();
				message = message + " (message " + ++this.msgCounter + ")";
				sendMessage(InitiateComm.getOtherPlayer(this), message);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

	}
}
