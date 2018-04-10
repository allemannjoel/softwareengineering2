package ch.fhnw.richards.lecture02.modelPropertyExample;

import javafx.application.Application;
import javafx.stage.Stage;

public class MPE_Main extends Application {
	private MPE_View view;
	private MPE_Controller controller;
	private MPE_Model model;

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Note the dependencies between model, view and controller. Additionally,
	 * the view needs the primaryStage created by JavaFX.
	 */
	@Override
	public void start(Stage primaryStage) {
		// Initialize the GUI
		model = new MPE_Model();
		view = new MPE_View(primaryStage, model);
		controller = new MPE_Controller(model, view);

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
