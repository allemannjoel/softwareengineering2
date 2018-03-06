package ch.fhnw.richards.lecture11_chatLab.v0_server;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class View {
	protected Stage stage;
	private Model model;
	
	Label lblHello = new Label("Hi, I am a server");
	
	public View(Stage stage, Model model) {
		this.stage = stage;
		this.model = model;
		
		Scene scene = new Scene(lblHello);
		stage.setScene(scene);
		stage.setTitle("Server");
	}
	
	protected void start() {
		stage.show();
	}
}
