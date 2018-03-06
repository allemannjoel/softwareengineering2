package ch.fhnw.richards.lecture02.solutions.webValidator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class WebValidator_Controller {
	final private WebValidator_Model model;
	final private WebValidator_View view;

	// Validity checks for the two text fields
	private boolean webAddressValid = false;
	private boolean portValid = false;

	protected WebValidator_Controller(WebValidator_Model model,
			WebValidator_View view) {
		this.model = model;
		this.view = view;

		// ChangeListener for the text-property of the web address
		view.txtIpAddress.textProperty().addListener(
				// Parameters of any PropertyChangeListener
				(observable, oldValue, newValue) -> {
					validateWebAddress(newValue);
				});

		// ChangeListener for the text-property of the port number
		view.txtPort.textProperty().addListener(
				// Parameters of any PropertyChangeListener
				(observable, oldValue, newValue) -> {
					validatePortNumber(newValue);
				});
	}

	/**
	 * Two options: numeric (IPv4) or symbolic (e.g., www.fhnw.ch). In either
	 * case, we split on the periods.
	 */
	private void validateWebAddress(String newValue) {
		ValueType type = model.getValueType(newValue);

		// Change text color
		if (type == ValueType.IPV4) {
			view.txtIpAddress.setStyle("-fx-text-inner-color: green;");
		} else if (type == ValueType.WebAddress) {
			view.txtIpAddress.setStyle("-fx-text-inner-color: orange;");
		} else{
			view.txtIpAddress.setStyle("-fx-text-inner-color: red;");
		}

		// Save result
		webAddressValid = (type != ValueType.None);

		// Enable or disable button, as appropriate
		enableDisableButton();
	}

	/**
	 * Must be an integer 1-65535
	 */
	private void validatePortNumber(String newValue) {
		boolean valid = model.isValidPortNumber(newValue);

		// Change text color
		if (valid) {
			view.txtPort.setStyle("-fx-text-inner-color: green;");
		} else {
			view.txtPort.setStyle("-fx-text-inner-color: red;");
		}

		// Save result
		portValid = valid;

		// Enable or disable button, as appropriate
		enableDisableButton();
	}

	/**
	 * Enable or disable the Connect button, based on the validity of the two
	 * text controls
	 */
	private void enableDisableButton() {
		boolean valid = webAddressValid & portValid;
		view.btnConnect.setDisable(!valid);
	}
}
