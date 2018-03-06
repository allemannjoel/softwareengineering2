package ch.fhnw.richards.lecture10_xml.camt054;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Camt054_View {
	private Camt054_Model model;
	protected Stage stage;
	
	protected Button btnOpenFile = new Button("Open file");
	
	protected Camt054_View(Stage stage, Camt054_Model model) {
        this.stage = stage;
        this.model = model;
        
        stage.setTitle("ISO 20022 camt.054 Test");
        
        HBox topBar = new HBox(btnOpenFile);
        topBar.getStyleClass().add("pane");

        // Set up TextArea to receive logger messages
        TextAreaHandler textAreaHandler = new TextAreaHandler();
        textAreaHandler.setLevel(Level.INFO);
        Logger defaultLogger = Logger.getLogger("");
        defaultLogger.addHandler(textAreaHandler);
        
        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(textAreaHandler.getTextArea());
        root.getStyleClass().add("pane");
        
        Scene scene = new Scene(root, 800, 500);
        scene.getStylesheets().add(
                getClass().getResource("camt054.css").toExternalForm());
        stage.setScene(scene);
	}

	protected void start() {
		stage.show();
	}
	
	protected void stop() {
		stage.hide();
	}
}
