package ch.fhnw.richards.lecture11_chatLab.v2_client;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;

public class Model {
	protected SimpleStringProperty newestMessage = new SimpleStringProperty();
	
	private Logger logger = Logger.getLogger("");
	private Socket socket;
	
	public void connect(String ipAddress, int Port, String name) {
		logger.info("Connect");
		try {
			socket = new Socket(ipAddress, Port);
		} catch (Exception e) {
			logger.warning(e.toString());
		}
	}
	
	public void disconnect() {
		logger.info("Disconnect");
		if (socket != null)
			try {
				socket.close();
			} catch (IOException e) {
				// Uninteresting
			}
	}
	
	public void sendMessage(String message) {
		logger.info("Send message");
	}

	public String receiveMessage() {
		logger.info("Receive message");
		return newestMessage.get();
	}
}
