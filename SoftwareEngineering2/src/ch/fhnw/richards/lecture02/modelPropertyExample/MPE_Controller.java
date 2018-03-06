package ch.fhnw.richards.lecture02.modelPropertyExample;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class MPE_Controller {
	
	final private MPE_Model model;
	final private MPE_View view;
	
	protected MPE_Controller(MPE_Model model, MPE_View view) {
		this.model = model;
		this.view = view;
		
		// register ourselves to listen for property changes in the model. Each change results in a short animation.
		model.getValueProperty().addListener( (observable, oldValue, newValue) -> {
		        String newText = Integer.toString(model.getValue());
		        
		        // Move to the JavaFX thread
		        Platform.runLater(new Runnable() {
		            @Override public void run() {
				        view.lblNumber.setText(newText);        
						view.doAnimate();
		            }
		        });
			}
        );
		
		
		// register ourselves to listen for button clicks
		view.btnClick.setOnAction((event) -> {
		        model.incrementValue();
		        String newText = Integer.toString(model.getValue());
		        view.lblNumber.setText(newText);        
			}
		);

		// register ourselves to handle window-closing event
		view.getStage().setOnCloseRequest((event) -> {
				view.stop();
				model.stop(); // End the simulation
			}
		);
	}
}
