package ch.fhnw.richards.lecture02.modelPropertyExample;

import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MPE_View {
    private MPE_Model model;
    private Stage stage;

	protected Label lblNumber;
	protected Button btnClick;
	
	private RotateTransition rotate;

	protected MPE_View(Stage stage, MPE_Model model) {
		this.stage = stage;
		this.model = model;
		
		stage.setTitle("Button Click MVC");
		
		GridPane root = new GridPane();
		lblNumber = new Label();
		lblNumber.setText(Integer.toString(model.getValue()));
		root.add(lblNumber, 0, 0);
		
		btnClick = new Button();
		btnClick.setText("Click Me!");
		root.add(btnClick, 0, 1);
		
		// Prepare animation for use
		rotate = new RotateTransition(Duration.millis(500));
		rotate.setByAngle(360);
		rotate.setCycleCount(1);
		rotate.setNode(lblNumber);

		Scene scene = new Scene(root);
		scene.getStylesheets().add(
				getClass().getResource("MPE.css").toExternalForm());
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
		Platform.exit();
	}
	
	/**
	 * Getter for the stage, so that the controller can access window events
	 */
	public Stage getStage() {
		return stage;
	}
	
	/**
	 * Method to allow the controller to trigger an animation
	 */
	public void doAnimate() {
		rotate.play();
	}
}