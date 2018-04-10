package ch.fhnw.richards.lecture01.kissSolutions;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * The biggest problem with the original code is repitition. When the same code appears over and
 * over, you know that you need a loop. In this case, we have six buttons, all being handled
 * identically. Put the buttons into an array, and put the code into a loop.
 * 
 * The processing for each individual button: We put the six texts into an array, and use the
 * incoming number as an index into that array.
 */
public class SetButtons extends Application {
    private static final int numButtons = 6; // No magic numbers
    private static final String[] texts = { "One", "Two", "Three", "Four", "Five", "Six" };

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create the buttons
        Button btn[] = new Button[numButtons];
        for (int i = 0; i < numButtons; i++)
            btn[i] = new Button();

        int[] numbers = getRandomNumbers();

        setButtons(btn, numbers);

        // Add buttons to the layout
        HBox root = new HBox();
        for (int i = 0; i < numButtons; i++)
            root.getChildren().add(btn[i]);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("KISS example: SetButtons");
        primaryStage.show();
    }

    private int[] getRandomNumbers() {
        int[] numbers = new int[numButtons];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (int) (Math.random() * numButtons + 1);
        }
        return numbers;
    }

    public void setButtons(Button[] buttons, int[] numbers) {
        for (int i = 0; i < numButtons; i++) {
            String text = texts[numbers[i]];
            buttons[i].setText(text);
        }
    }
}