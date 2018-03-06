package ch.fhnw.richards.lecture10_xml.yoo_exercise.server;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import ch.fhnw.richards.lecture10_xml.yoo_exercise.commons.Message;
import javafx.application.Platform;

public class ClientThread extends Thread{
	
	Socket s;
	BufferedReader in;
	PrintWriter out;
	ServerThread server;
	
	public ClientThread(Socket s, ServerThread server) throws Exception{
		this.s = s;
		this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		this.out = new PrintWriter(s.getOutputStream(), true);
		this.server = server;
	}
	
	public void run(){
		try{
			YooProtocol protocol = new YooProtocol(server);
			
			listening: while(true){
				Message messageIn = Message.receive(s);
				Platform.runLater(() -> {
				    server.gui.log.appendText(messageIn.toString() + "\n");
			    });
				switch(messageIn.getType()){
				case YOO:
					protocol.processInput(messageIn);
					server.broadcast();
					break;
				case GOODBYE:
					server.state.remove(this);
					break listening;
				}
			}
		}
		catch (Exception e){
			System.out.println(e.toString());
		}
	}	
}
