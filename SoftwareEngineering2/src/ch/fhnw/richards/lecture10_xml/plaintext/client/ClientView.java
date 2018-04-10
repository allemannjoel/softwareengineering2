package ch.fhnw.richards.lecture10_xml.plaintext.client;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ClientView {
    private ClientModel model;
    private Stage stage;

    protected Label lblIP = new Label("IP");
    protected TextField txtIP = new TextField("127.0.0.1");
    protected Label lblPort = new Label("Port");
    protected TextField txtPort = new TextField("8080");
    protected Button btnGo = new Button("Go");
    protected Button btnHello = new Button("Hello");
    protected TextField txtCustom = new TextField("Custom message");
    protected Button btnCustom = new Button("Custom");
    protected Button btnGoodbye = new Button("Goodbye");
    protected TextArea txtMessages = new TextArea();
    
    protected ClientView(Stage stage, ClientModel model) {
        this.stage = stage;
        this.model = model;
        
        stage.setTitle("Plain text client");
        
        BorderPane root = new BorderPane();

        HBox topBox = new HBox();
        topBox.setId("TopBox");
        Region spacer = new Region();
        Region spacer2 = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        root.setTop(topBox);
        
        topBox.getChildren().addAll(lblIP, txtIP, spacer, lblPort, txtPort, spacer2, btnGo);
        txtIP.setId("IP");
        txtPort.setId("Port");
        
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        root.setCenter(scrollPane);
        scrollPane.setContent(txtMessages);
        txtMessages.setWrapText(true);

        HBox bottomBox = new HBox();
        topBox.setId("BottomBox");
        bottomBox.getChildren().addAll(btnHello, spacer, txtCustom, btnCustom, spacer2, btnGoodbye);
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        root.setBottom(bottomBox);
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(
                getClass().getResource("Example.css").toExternalForm());
        stage.setScene(scene);;
    }
    
    public void start() {
        stage.show();
    }
    
    /**
     * Stopping the view - just make it invisible
     */
    public void stop() {
        stage.hide();
    }
    
    /**
     * Getter for the stage, so that the controller can access window events
     */
    public Stage getStage() {
        return stage;
    }
}
