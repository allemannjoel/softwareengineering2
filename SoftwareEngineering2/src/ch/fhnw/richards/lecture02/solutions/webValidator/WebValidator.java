package ch.fhnw.richards.lecture02.solutions.webValidator;

import ch.fhnw.richards.lecture02.mvc.ButtonClickController;
import ch.fhnw.richards.lecture02.mvc.ButtonClickModel;
import ch.fhnw.richards.lecture02.mvc.ButtonClickView;
import javafx.application.Application;
import javafx.stage.Stage;

public class WebValidator extends Application {
    private WebValidator_View view;
    private WebValidator_Controller controller;
    private WebValidator_Model model;

    @Override
    public void start(Stage primaryStage) throws Exception {
        model = new WebValidator_Model();
        view = new WebValidator_View(primaryStage, model);
        controller = new WebValidator_Controller(model, view);

        // Display the GUI after all initialization is complete
        view.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
