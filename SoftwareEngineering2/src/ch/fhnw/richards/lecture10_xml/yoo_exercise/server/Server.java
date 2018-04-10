package ch.fhnw.richards.lecture10_xml.yoo_exercise.server;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The Yoo server does strange things with its clients...
 */

public class Server extends Application {
    TextArea log = new TextArea();

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Note the dependencies between model, view and controller. Additionally,
     * the view needs the primaryStage created by JavaFX.
     */

    @Override
    public void start(Stage primaryStage) throws Exception {
        new ServerThread(20000, this).start();
        
        primaryStage.setTitle("Yoo Server");
        BorderPane root = new BorderPane();
        root.setCenter(log);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
