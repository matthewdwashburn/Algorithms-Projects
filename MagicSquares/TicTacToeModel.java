package mvcsquares;

import java.util.*;

/**
 * The TicTacToeModel class provides methods to facilitate creating a square
 * matrix that can play tic tac toe
 * 
 * 
 * @author Matthew Washburn
 * @version 1
 * @date 11/6/2024
 *
 */
public class TicTacToeModel extends SquareModel {

	// Create 2d array to store values of the square
	private String[][] square;
	// Store size of the square
	private int size;
	private String currentPlayer;

	/**
	 * @param Constructor creates new square with given size
	 */
	public TicTacToeModel(int size) {
		this.size = size;
		square = new String[size][size];
		currentPlayer = "X";
	}

	/**
	 * Gives description of current square based on Game results
	 */
	@Override
	public String getFeedback() {
		if (isGameWonX()) {
			return "Team X Wins!";
		}
		if (isGameWonO()) {
			return "Team O Wins!";
		}
		if (isArrayComplete()) {
			return "Tie!";
		}

		return "Your game is not yet finished.";
	}

	/**
	 * Sets square size to given size
	 */
	@Override
	public void setSize(int size) {
		int oldSize = this.size;
		this.size = size;
		square = new String[this.size][this.size];
		// Send out a notification that the size of the square changed
		this.pcs.firePropertyChange("size", oldSize, this.size);
	}

	/**
	 * Returns size of the square
	 */
	@Override
	public int getSize() {
		return size;
	}

	/**
	 * Returns title of the model
	 */
	@Override
	public String getTitle() {
		return "Tic Tac Toe";
	}

	@Override
	public void clear() {
		// Reinitialize the current square with same size
		square = new String[size][size];
		currentPlayer = "X";
		// Send out a notification that a value changed in the square
		this.pcs.firePropertyChange("clear", null, null);
	}

	/**
	 * Returns value of a given square space
	 */
	@Override
	public String getValueAt(int row, int col) {
		// Handle when null space selected
		if (square[row][col] == null) {
			return "";
		}
		return square[row][col];
	}

	/**
	 * Sets the the given square space to the given value
	 */
	@Override
	public void setValueAt(String data, int row, int col) {
		if(data.equals("x")) {
			data = "X";
		}
		if(data.equals("o")) {
			data = "O";
		}

		if (isGameWonX() || isGameWonO() || isArrayComplete()) {
			return; // Prevent moves after game is over
		}
		if (!data.equals("X") && !data.equals("O")) {
			return; // Only allow X and O as input
		}
		if (square[row][col] != null && !square[row][col].isEmpty()) {
			return; // Prevent changing an already claimed cell
		}
		if (!data.equals(currentPlayer)) {
			return; // Prevent someone from playing out of turn
		}

		square[row][col] = data; // Update square value
		currentPlayer = currentPlayer.equals("X") ? "O" : "X"; // Change player

		// Notify listeners that the value has changed
		this.pcs.firePropertyChange("setValueAt", null, null);
	}

	/**
	 * @return True if square has no null spaces
	 */
	public boolean isArrayComplete() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (square[i][j] == null || square[i][j] == "") {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * @return True if game is won by X
	 */
	public boolean isGameWonX() {

		// Create an array the for the of amount of X in each col, row, and main
		// diagonal
		Integer[] rowSumArrayX = new Integer[size];
		Integer[] colSumArrayX = new Integer[size];
		Integer[] diagSumArrayX = new Integer[2];

		// Put each row amount of X each into array
		for (int i = 0; i < size; i++) {
			int curRowSumX = 0;
			for (int j = 0; j < size; j++) {
				try {
					if (square[i][j].equals("X")) {
						curRowSumX++;
					}
				} catch (NullPointerException e) {

				}

			}
			rowSumArrayX[i] = curRowSumX;

		}

		// Put each col amount of X each into array
		for (int i = 0; i < size; i++) {
			int curColSumX = 0;
			for (int j = 0; j < size; j++) {
				try {
					if (square[j][i].equals("X")) {
						curColSumX++;
					}
				} catch (NullPointerException e) {

				}

			}
			colSumArrayX[i] = curColSumX;
		}

		// Put top left to bottom right main diagonal amount of X into array
		int leftToRightDiagSumX = 0;
		for (int i = 0; i < size; i++) {
			try {
				if (square[i][i].equals("X")) {
					leftToRightDiagSumX++;
				}
			} catch (NullPointerException e) {

			}

		}
		diagSumArrayX[0] = leftToRightDiagSumX;

		// Put top right to bottom left main diagonal amount of X into array
		int rightToLeftDiagSumX = 0;
		for (int row = 0, col = size - 1; col >= 0; col--, row++) {
			try {
				if (square[row][col].equals("X")) {
					rightToLeftDiagSumX++;
				}
			} catch (NullPointerException e) {

			}

		}
		diagSumArrayX[1] = rightToLeftDiagSumX;

		if (doesArrayContainSize(rowSumArrayX) || doesArrayContainSize(colSumArrayX)
				|| doesArrayContainSize(diagSumArrayX)) {
			return true;
		}

		return false;

	}

	/**
	 * @return True if game is won by O
	 */
	public boolean isGameWonO() {

		// Create an array the for the of amount of O in each col, row, and main
		// diagonal

		Integer[] rowSumArrayO = new Integer[size];
		Integer[] colSumArrayO = new Integer[size];
		Integer[] diagSumArrayO = new Integer[2];

		// Put each row amount of O into array
		for (int i = 0; i < size; i++) {
			int curRowSumO = 0;
			for (int j = 0; j < size; j++) {

				try {
					if (square[i][j].equals("O")) {
						curRowSumO++;
					}
				} catch (NullPointerException e) {

				}

			}
			rowSumArrayO[i] = curRowSumO;
		}

		// Put each col amount of O into array
		for (int i = 0; i < size; i++) {
			int curColSumO = 0;
			for (int j = 0; j < size; j++) {

				try {
					if (square[j][i].equals("O")) {
						curColSumO++;
					}
				} catch (NullPointerException e) {

				}

			}
			colSumArrayO[i] = curColSumO;
		}

		// Put top left to bottom right main diagonal amount of O into array
		int leftToRightDiagSumO = 0;
		for (int i = 0; i < size; i++) {

			try {
				if (square[i][i].equals("O")) {
					leftToRightDiagSumO++;
				}
			} catch (NullPointerException e) {

			}
		}
		diagSumArrayO[0] = leftToRightDiagSumO;

		// Put top right to bottom left main diagonal amount of O into array
		int rightToLeftDiagSumO = 0;
		for (int row = 0, col = size - 1; col >= 0; col--, row++) {

			try {
				if (square[row][col].equals("O")) {
					rightToLeftDiagSumO++;
				}
			} catch (NullPointerException e) {

			}
		}
		diagSumArrayO[1] = rightToLeftDiagSumO;

		if (doesArrayContainSize(rowSumArrayO) || doesArrayContainSize(colSumArrayO)
				|| doesArrayContainSize(diagSumArrayO)) {
			return true;
		}

		return false;

	}

	/**
	 * @param array
	 * @return True if square array contains size
	 */
	public boolean doesArrayContainSize(Integer array[]) {
		// Create hash set to remove duplicate elements
		Set<Integer> set = new HashSet<>(Arrays.asList(array));

		// If there's one or 0 elements left, all elements were the same
		if (set.contains(size)) {
			return true;
		} else {
			return false;
		}
	}

}