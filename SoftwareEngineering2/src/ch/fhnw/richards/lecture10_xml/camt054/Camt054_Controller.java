package ch.fhnw.richards.lecture10_xml.camt054;

import java.io.File;

import javafx.application.Platform;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class Camt054_Controller {
	final private Camt054_View view;
	final private Camt054_Model model;
	
	protected Camt054_Controller(Camt054_Model model, Camt054_View view) {
		this.model = model;
		this.view = view;
		
		// register ourselves to handle button-clicks
		view.btnOpenFile.setOnAction((event) -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open payments file");
			fileChooser.getExtensionFilters().add(new ExtensionFilter("XML Files", "*.xml"));
			File selectedFile = fileChooser.showOpenDialog(view.stage);
			if (selectedFile != null) model.importFile(selectedFile);
		});
		
		// register ourselves to handle window-closing event
        view.stage.setOnCloseRequest( event -> {
                view.stop();
                Platform.exit();
            }
        );
	}

}
