package ch.fhnw.richards.lecture07.minimalApps;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * This is a minimal console-based server. It replies to each individual line
 * sent by the client, until the client closes the connection.
 */
public class MinimalServer {

	public static void main(String[] args) {
		int portNumber = 50001;
		try (Scanner in = new Scanner(System.in)) {
			System.out.println("Enter port number: ");
			portNumber = in.nextInt();
		}

		try {
			ServerSocket listener = new ServerSocket(portNumber, 10, null);
			try (Socket client = listener.accept();
					BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
					OutputStreamWriter out = new OutputStreamWriter(client.getOutputStream());) {
				String inString;
				while ((inString = in.readLine()) != null) {
					String answer = "Answer to message '" + inString + "'\n";
					System.out.println(answer);
					out.write("Answer to message '" + inString + "'\n");
					out.flush();
				}
				out.write("The client ended the communication\n");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
