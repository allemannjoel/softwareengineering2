package ch.fhnw.richards.lecture02.email_validator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class EmailValidator_Controller {    
    final private EmailValidator_Model model;
    final private EmailValidator_View view;
    
    protected EmailValidator_Controller(EmailValidator_Model model, EmailValidator_View view) {
        this.model = model;
        this.view = view;
        
        // With an anonymous class
        view.txtEmail.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                validateEmailAddress(newValue);
            }            
        });
        
        // Using a lambda expression
        view.txtEmail.textProperty().addListener(
                // Parameters of any PropertyChangeListener
                (observable, oldValue, newValue) -> {
                    validateEmailAddress(newValue);
                });
    }
    
    private void validateEmailAddress(String newValue) {
        boolean valid = false;
        
        if (newValue.charAt(newValue.length()-1) != '@') {
            String[] addressParts = newValue.split("@");
            if (addressParts.length == 2 && !addressParts[0].isEmpty()) {
                String[] domainParts = addressParts[1].split("\\.");
                if (domainParts.length >= 2) {
                    valid = true;
                    for (String s : domainParts) {
                        if (s.length() < 2) {
                            valid = false;
                            break;
                        }
                    }                
                }
            }
        }
        
        if (valid) {
            view.txtEmail.setStyle("-fx-text-inner-color: green;");
        } else {
            view.txtEmail.setStyle("-fx-text-inner-color: red;");
        }
    }
}
