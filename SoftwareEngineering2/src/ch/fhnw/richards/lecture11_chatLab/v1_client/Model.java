package ch.fhnw.richards.lecture11_chatLab.v1_client;

import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;

public class Model {
	protected SimpleStringProperty newestMessage = new SimpleStringProperty();
	
	private Logger logger = Logger.getLogger("");
	
	public void connect(String ipAddress, int Port, String name) {
		logger.info("Connect");
	}
	
	public void disconnect() {
		logger.info("Disconnect");
	}
	
	public void sendMessage(String message) {
		logger.info("Send message");
	}

	public String receiveMessage() {
		logger.info("Receive message");
		return newestMessage.get();
	}
}
