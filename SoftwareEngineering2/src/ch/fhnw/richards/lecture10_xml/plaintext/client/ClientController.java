package ch.fhnw.richards.lecture10_xml.plaintext.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class ClientController {
    
    final private ClientModel model;
    final private ClientView view;
    
    protected ClientController(ClientModel model, ClientView view) {
        this.model = model;
        this.view = view;
        
        // register ourselves to listen for button clicks
        view.btnGo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String ip = view.txtIP.getText();
                Integer port = new Integer(view.txtPort.getText());
                model.init(ip, port);
                view.txtMessages.setText("initialized");
            }
        });

        // register ourselves to listen for button clicks
        view.btnHello.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String result = model.sayHello();
                view.txtMessages.appendText("\nSaid 'hello', received: " + result);
            }
        });

        // register ourselves to listen for button clicks
        view.btnCustom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String result = model.sendCustomMessage(view.txtCustom.getText());
                view.txtMessages.appendText("\nSend custom message, received: " + result);
            }
        });

        // register ourselves to listen for button clicks
        view.btnGoodbye.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String result = model.sayGoodbye();
                view.txtMessages.appendText("\nSaid 'goodbye', received: " + result);
            }
        });

        // register ourselves to handle window-closing event
        view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                view.stop();
                Platform.exit();
            }
        });
    }
}
