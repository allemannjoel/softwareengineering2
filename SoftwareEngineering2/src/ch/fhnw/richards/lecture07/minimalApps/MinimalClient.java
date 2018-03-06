package ch.fhnw.richards.lecture07.minimalApps;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * This is a minimal console-based client. It asks the user for messasges to
 * send, until the user enters "stop"
 */
public class MinimalClient {

	public static void main(String[] args) {
		String ipAddress = "127.0.0.1";
		int portNumber = 50001;
		try (Scanner in = new Scanner(System.in)) {
			System.out.println("Enter IP address: ");
			ipAddress = in.nextLine();
			ipAddress = ipAddress.trim();

			System.out.println("Enter port number: ");
			portNumber = in.nextInt();
			in.nextLine(); // Eat end-of-line character

			try (Socket socket = new Socket(ipAddress, portNumber);
					BufferedReader netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					OutputStreamWriter netOut = new OutputStreamWriter(socket.getOutputStream());) {

				String message = "";
				while (!message.equals("stop")) {
					System.out.println("Enter message to send, or 'stop' to end: ");
					message = in.nextLine();
					netOut.write(message + "\n");
					netOut.flush();
					System.out.println("Received: " + netIn.readLine());
				}

			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

}
