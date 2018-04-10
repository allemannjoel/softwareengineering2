package ch.fhnw.richards.lecture02.email_validator;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EmailValidator_View {
    private EmailValidator_Model model;
    private Stage stage;

    protected TextField txtEmail;

    protected EmailValidator_View(Stage stage, EmailValidator_Model model) {
        this.stage = stage;
        this.model = model;
        
        stage.setTitle("Email Validator");
        
        GridPane root = new GridPane();
        txtEmail = new TextField();
        root.add(txtEmail, 0, 0);

        Scene scene = new Scene(root);
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
