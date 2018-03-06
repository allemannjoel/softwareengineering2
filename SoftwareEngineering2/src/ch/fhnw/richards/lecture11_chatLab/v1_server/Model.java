package ch.fhnw.richards.lecture11_chatLab.v1_server;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {
	protected ObservableList<Client> clients =  FXCollections.observableArrayList();
	
	private Logger logger = Logger.getLogger("");

	public void startServer(int Port) {
		logger.info("Start server");
	}
	
	public void stopServer() {
		logger.info("Stop server");
	}
	
	public ObservableList<Client> getClientList() {
		logger.info("Get client list");
		return clients;
	}
}
