package conversation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InitiateComm {
	private static Player p1;
	private static Player p2;

	public static void main(String[] args) throws InterruptedException, IOException {
		if (args.length > 0 && "separate-process".equals(args[0])) {
			ProcessBuilder pb1 = new ProcessBuilder("java", "-cp", "target/conversation-0.0.1-SNAPSHOT.jar",
					"conversation.PlayerComm", "Player1");
			ProcessBuilder pb2 = new ProcessBuilder("java", "-cp", "target/conversation-0.0.1-SNAPSHOT.jar",
					"conversation.PlayerComm", "Player2");

			Process p1 = pb1.start();
			Process p2 = pb2.start();

			InputStream stdout = p1.getInputStream();
			InputStream stdout1 = p2.getInputStream();
			BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(stdout));
			BufferedReader stdoutReader1 = new BufferedReader(new InputStreamReader(stdout1));

			String line;
			String line1;

			while ((line = stdoutReader.readLine()) != null) {
				System.out.println("STDOUT: pb1 " + line);
			}

			while ((line1 = stdoutReader1.readLine()) != null) {
				System.out.println("STDOUT: pb2 " + line1);
			}

			p1.waitFor();
			p2.waitFor();
		} else {
			p1 = new Player("p1");
			p2 = new Player("p2");

			Thread t1 = new Thread(p1);
			Thread t2 = new Thread(p2);

			t1.start();
			t2.start();

			p1.sendMessage(p2, "X");
			p1.msgCounter++;

			while (p1.msgCounter < 10) {
				Thread.sleep(100);
			}

			t1.interrupt();
			t2.interrupt();
		}
	}

	public static Player getOtherPlayer(Player player) {
		return player == p1 ? p2 : p1;
	}

}
