package ch.fhnw.richards.lecture04.sampleSolution.appClasses;

import java.util.Optional;

import ch.fhnw.richards.lecture04.sampleSolution.ServiceLocator;
import ch.fhnw.richards.lecture04.sampleSolution.abstractClasses.Controller;
import ch.fhnw.richards.lecture04.sampleSolution.commonClasses.Translator;
import ch.fhnw.richards.lecture04.sampleSolution.optionsWindow.WebValidator_Controller;
import ch.fhnw.richards.lecture04.sampleSolution.optionsWindow.WebValidator_Model;
import ch.fhnw.richards.lecture04.sampleSolution.optionsWindow.WebValidator_View;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */
public class App_Controller extends Controller<App_Model, App_View> {
	ServiceLocator serviceLocator;
	Translator t;

	public App_Controller(App_Model model, App_View view) {
		super(model, view);

		// register to listen for button clicks
		view.btnClick.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				buttonClick();
			}
		});

		// register to handle window-closing event
		view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				Platform.exit();
			}
		});

		// register for menu item "File:Options"
		view.menuFileOptions.setOnAction((event) -> {
			Stage optionsStage = new Stage();

	        // Initialize the option MVC components
			WebValidator_Model oModel = new WebValidator_Model();
			WebValidator_View oView = new WebValidator_View(optionsStage, oModel);
	        new WebValidator_Controller(oModel, oView);

	        // Display the options window
	        oView.start();
		});
		
		// register for menu item "Help:About"
		view.menuHelpAbout.setOnAction((event) -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(t.getString("HelpAboutTitle"));
			alert.setHeaderText(t.getString("HelpAboutHeader"));
			alert.setContentText(t.getString("HelpAboutMessage"));
			alert.showAndWait();
		});

		serviceLocator = ServiceLocator.getServiceLocator();
		t = serviceLocator.getTranslator();
		serviceLocator.getLogger().info("Application controller initialized");
	}

	public void buttonClick() {
		model.incrementValue();
		String newText = Integer.toString(model.getValue());

		view.lblNumber.setText(newText);
	}
}
