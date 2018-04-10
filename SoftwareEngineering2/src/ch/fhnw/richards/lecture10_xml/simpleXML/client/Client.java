package ch.fhnw.richards.lecture10_xml.simpleXML.client;

import javafx.application.Application;
import javafx.stage.Stage;

public class Client extends Application {
    private ClientView view;
    private ClientController controller;
    private ClientModel model;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Note the dependencies between model, view and controller. Additionally,
     * the view needs the primaryStage created by JavaFX.
     */

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initialize the GUI
        model = new ClientModel();
        view = new ClientView(primaryStage, model);
        controller = new ClientController(model, view);

        // Display the GUI after all initialization is complete
        view.start();
    }

    /**
     * The stop method is the opposite of the start method. It provides an
     * opportunity to close down the program gracefully, when the program has
     * been closed.
     */
    @Override
    public void stop() {
        if (view != null)
            view.stop();
    }
}
