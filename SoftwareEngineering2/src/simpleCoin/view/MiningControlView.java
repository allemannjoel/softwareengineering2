package simpleCoin.view;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class MiningControlView extends VBox {
	final String START = "Start";
	final String STOP = "Stop";
	Label lblTitle = new Label("Mining Control");
	Button btnStartStop = new Button(START);

	Label lblHashrate = new Label("Hashes/second");
	Label lblLastBlockFound = new Label("Time of last block");
	Label lblDifficulty = new Label("Current difficulty");
	Label lblTargetRate = new Label("Target seconds to find block");
	Label lblDifficultyAdjust = new Label("Difficulty adjustment (blocks)");

	Label lblValueHashrate = new Label("          ");
	Label lblValueLastBlockFound = new Label();
	Label lblValueDifficulty = new Label();
	Label lblValueTargetRate = new Label();
	Label lblValueDifficultyAdjust = new Label();

	MiningControlView() {
		super();

		GridPane gridInfo = new GridPane();
		gridInfo.addRow(0, lblHashrate, lblValueHashrate);
		gridInfo.addRow(1, lblLastBlockFound, lblValueLastBlockFound);
		gridInfo.addRow(2, lblDifficulty, lblValueDifficulty);
		gridInfo.addRow(3, lblTargetRate, lblValueTargetRate);
		gridInfo.addRow(4, lblDifficultyAdjust, lblValueDifficultyAdjust);

		this.getChildren().addAll(lblTitle, btnStartStop, gridInfo);

		// Formatting
		lblTitle.setStyle("-fx-font-size: 24");

		this.setSpacing(10);
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));

		Text text = new Text("Xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		text.applyCss();
		ColumnConstraints cc0 = new ColumnConstraints(text.getLayoutBounds().getWidth());
		text = new Text("0000000000");
		text.applyCss();
		ColumnConstraints cc1 = new ColumnConstraints(text.getLayoutBounds().getWidth());
		gridInfo.getColumnConstraints().addAll(cc0, cc1);
		
		
		gridInfo.setPadding(new Insets(10, 10, 10, 10));
		gridInfo.setBorder(
				new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
		gridInfo.setVgap(10);
		gridInfo.setHgap(10);
	}

	public Button getBtnStartStop() {
		return btnStartStop;
	}

	public void updateBtnStartStop(boolean isStart) {
		Platform.runLater(() -> {
			if (isStart)
				btnStartStop.setText(START);
			else
				btnStartStop.setText(STOP);
		});
	}
	
	public void updateHashRate(int hashRate) {
		Platform.runLater(() -> {
			lblValueHashrate.setText(Integer.toString(hashRate));
		});
	}
}