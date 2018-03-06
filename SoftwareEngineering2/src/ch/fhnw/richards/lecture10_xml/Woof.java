package ch.fhnw.richards.lecture10_xml;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Woof extends Application {
	Button btn = new Button("X");

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane root = new Pane();
		root.getChildren().add(btn);
		btn.setLayoutX(50);
		btn.setLayoutY(50);

		Scene s = new Scene(root, 300, 300);
		primaryStage.setScene(s);
		primaryStage.show();

		Animator animator = new Animator(btn);
		animator.start();
	}

	public static void main(String[] args) {
		launch();
	}

	private static class Animator extends Thread {
		Button btn;

		public Animator(Button btn) {
			this.btn = btn;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(200);

				for (int i = 0; i < 100; i++) {
					btn.setTranslateX(i * 1);
					btn.setTranslateY(i * 2);
					Thread.sleep(10);
				}
			} catch (InterruptedException e) {
			}
		}
	}

}
