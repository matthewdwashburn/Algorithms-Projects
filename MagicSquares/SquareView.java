package mvcsquares;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * The SquareView class creates a GUI for users to check if squares are magic,
 * normal magic, or both using the MagicSquareModel class
 * 
 * @author Matthew Washburn
 * @version 1
 * @date 11/2/2024
 *
 */
public class SquareView extends Application {

	// Create instance of TicTacToeModel
	private TicTacToeModel myModel;

	// Create GUI components
	private Label titleLabel;

	private Label feedbackLabel;

	private TextField[][] squareTF;

	private ComboBox<Integer> sizeSelect;

	private Button clearButton;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Create square model object
		myModel = new TicTacToeModel(3);
		// Start listening for square property changes and update feedback
		myModel.addPropertyChangeListener(e -> {
			feedbackLabel.setText(myModel.getFeedback());
		});
		try {
			// Create new grid pane
			GridPane root = new GridPane();
			Scene scene = new Scene(root, 500, 400);

			// Set Title Label (unchanging)
			titleLabel = new Label(myModel.getTitle());
			root.add(titleLabel, 0, 0);

			// Display current square properties
			feedbackLabel = new Label(myModel.getFeedback());
			root.add(feedbackLabel, 0, 2);

			// Set up size selection box
			sizeSelect = new ComboBox<>();
			sizeSelect.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5));
			root.add(sizeSelect, 0, 3);

			// Listen for size changes in selection box
			sizeSelect.setOnAction(e -> {
				// Clear all values
				myModel.clear();
				Integer selectedSize = sizeSelect.getSelectionModel().getSelectedItem();
				myModel.setSize(selectedSize);

				// Remove all previous text fields when size changed
				root.getChildren().removeIf(node -> node instanceof TextField);

				// Set up a 2D array to store text fields of the square
				squareTF = new TextField[myModel.getSize()][myModel.getSize()];

				// Populate the text field array with text fields
				for (int row = 0; row < myModel.getSize(); row++) {
					for (int col = 0; col < myModel.getSize(); col++) {
						TextField tempTF = new TextField();
						tempTF.setPrefWidth(50);
						tempTF.setPrefHeight(30);
						squareTF[row][col] = tempTF;

						int finalRow = row;
						int finalCol = col;
						// Listen for typing in each text field
						tempTF.textProperty().addListener((a, oldValue, newValue) -> {
							// Set the value of each square to the input if correct
							// This logic is handled in setValueAt method
							myModel.setValueAt(tempTF.getText(), finalRow, finalCol);
							tempTF.setText(myModel.getValueAt(finalRow, finalCol));

						});

						root.add(tempTF, col + 10, row + 10);
					}
				}
			});
			// Set up clear button
			clearButton = new Button("Clear");
			clearButton.setOnAction(e -> {
				myModel.clear();
				// Clear all TFs in the scene
				root.getChildren().forEach(node -> {
					if (node instanceof TextField) {
						((TextField) node).clear();
					}
					;
				});
			});
			root.add(clearButton, 11, 20);
			// Display primary scenes
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Launch Java FX GUI
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
