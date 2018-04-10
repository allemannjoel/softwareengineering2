package ch.fhnw.richards.lecture10_xml.camt054;

import javafx.application.Application;
import javafx.stage.Stage;

public class Camt054_Main extends Application {
    private Camt054_View view;
    private Camt054_Controller controller;
    private Camt054_Model model;

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
        model = new Camt054_Model();
        view = new Camt054_View(primaryStage, model);
        controller = new Camt054_Controller(model, view);

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