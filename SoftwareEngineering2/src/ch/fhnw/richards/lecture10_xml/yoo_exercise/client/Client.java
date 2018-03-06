package ch.fhnw.richards.lecture10_xml.yoo_exercise.client;

import ch.fhnw.richards.lecture10_xml.yoo_exercise.commons.Card;
import ch.fhnw.richards.lecture10_xml.yoo_exercise.commons.Message;
import ch.fhnw.richards.lecture10_xml.yoo_exercise.commons.MessageType;
import ch.fhnw.richards.lecture10_xml.yoo_exercise.commons.Position;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Client extends Application {
    Button b = new Button("yoo");
    Stage stage;
    ClientModel c;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        
        new ClientModel(this);
        
        primaryStage.setTitle("Yoo Client");
        BorderPane root = new BorderPane();
        root.setCenter(b);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        
        b.setPrefSize(400, 200);
        this.setCard(Card.getRandomCard());
        this.setPosition(Position.getRandomPosition(1000, 600));
        
        b.setOnAction((ActionEvent event) -> {
            Message m = new Message(MessageType.YOO);
            try{
                c.send(m);
            }
            catch(Exception ex){
                System.out.println(ex.toString());
            }
        });
        
        // register ourselves to handle window-closing event
        primaryStage.setOnCloseRequest( (WindowEvent event) -> {
            Message m = new Message(MessageType.GOODBYE);
            try {
                c.send(m);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            Platform.exit();
        });
        
        primaryStage.show();
    }
    
    public void setCard(Card card){
        Color c = new Color(card.getRed(), card.getGreen(), card.getBlue(), 1);
        Background bg = new Background(new BackgroundFill(c, CornerRadii.EMPTY, Insets.EMPTY));
        b.setBackground(bg);
    }
    
    public void setPosition(Position p){
        stage.setX(p.getX());
        stage.setY(p.getY());
    }
}
