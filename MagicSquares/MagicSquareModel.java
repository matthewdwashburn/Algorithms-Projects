package mvcsquares;

import java.util.*;

/**
 * The MagicSquareModel class provides methods to facilitate creating a square
 * matrix and checking if that square is magic, normal magic, both or neither.
 * 
 * 
 * @author Matthew Washburn
 * @version 2
 * @date 11/2/2024
 *
 */
public class MagicSquareModel extends SquareModel {
	
	// Create 2d array to store values of the square 
	private Integer[][] square;
	// Store size of the square 
	private int size;

	/**
	 * @param Constructor creates new square with given size
	 */
	public MagicSquareModel(int size) {
		this.size = size;
		square = new Integer[size][size];
	}

	/**
	 * Gives description of current square based on magic results
	 */
	@Override
	public String getFeedback() {
		if (isMagic() && !isNormalMagic()) {
			return "Your square is magic but not normal magic.";
		}
		if (isMagic() && isNormalMagic()) {
			return "Your square is magic and normal magic.";
		}
		return "Your square is not magic or normal magic.";
	}

	/**
	 * Sets square size to given size
	 */
	@Override
	public void setSize(int size) {
		int oldSize = this.size;
		this.size = size;
		square = new Integer[this.size][this.size];
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
		return "Magic Squares";
	}

	@Override
	public void clear() {
		// Reinitialize the current square with same size
		square = new Integer[size][size];
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
		return String.valueOf(square[row][col]);
	}

	/**
	 * Sets the the given square space to the given value
	 */
	@Override
	public void setValueAt(String data, int row, int col) {
		// If input is not an integer, set it to null
		try {
			square[row][col] = Integer.parseInt(data);
		} catch (NumberFormatException e) {
			return;

		}
		// Send out a notification that a value changed in the square
		this.pcs.firePropertyChange("setValueAt", null, null);
	}

	/**
	 * @param array
	 * @return True if square array contains all duplicate row and col sums
	 */
	public static boolean isArrayIdentical(Integer array[]) {
		// Create hash set to remove duplicate elements
		Set<Integer> set = new HashSet<>(Arrays.asList(array));

		// If there's one or 0 elements left, all elements were the same
		if (set.size() <= 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param array
	 * @return True if square array contains all integers up to size squared
	 */
	public boolean doesArrayContainAllValues(Integer array[]) {
		// Fill a truth array to compare with matrix values
		int sizeSquared = size * size;
		boolean[] found = new boolean[sizeSquared + 1];

		// Mark each element in the array as found if it’s in the valid range
		for (Integer value : array) {
			if (value >= 1 && value <= sizeSquared) {
				found[value] = true;
			}
		}

		// Check if every number from 1 to sizeSquared is marked as found
		for (int i = 1; i <= sizeSquared; i++) {
			if (!found[i]) {
				return false; // If any number is missing, return false
			}
		}

		// If all numbers from 1 to size squared are present
		return true;
	}

	/**
	 * @return True if square has no null spaces
	 */
	public boolean isArrayComplete() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (square[i][j] == null) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * @return True if current square is magic
	 */
	public boolean isMagic() {
		// Check if square is complete first to prevent null pointer exception
		if (!isArrayComplete()) {
			return false;
		}
		// Create an array the sums of each col, row, and main diagonal
		Integer[] rowSumArray = new Integer[size];
		Integer[] colSumArray = new Integer[size];
		Integer[] diagSumArray = new Integer[2];

		// Put each row sum into array
		for (int i = 0; i < size; i++) {
			int curRowSum = 0;
			for (int j = 0; j < size; j++) {
				curRowSum += square[i][j];
			}
			rowSumArray[i] = curRowSum;
		}

		// Put each column sum into array
		for (int i = 0; i < size; i++) {
			int curColSum = 0;
			for (int j = 0; j < size; j++) {
				curColSum += square[j][i];
			}
			colSumArray[i] = curColSum;
		}

		// Put top left to bottom right main diagonal sum into array
		int leftToRightDiagSum = 0;
		for (int i = 0; i < size; i++) {
			leftToRightDiagSum += square[i][i];
		}
		diagSumArray[0] = leftToRightDiagSum;

		// Put top right to bottom left main diagonal sum into array
		int rightToLeftDiagSum = 0;
		for (int row = 0, col = size - 1; col >= 0; col--, row++) {
			rightToLeftDiagSum += square[row][col];
		}
		diagSumArray[1] = rightToLeftDiagSum;

		// Verify all three cases have all matching sums and that the square is complete
		if (isArrayIdentical(rowSumArray) && isArrayIdentical(colSumArray) && isArrayIdentical(diagSumArray)
				&& isArrayComplete())
			return true;
		else {
			return false;
		}
	}

	/**
	 * @return True if current square is normal magic
	 */
	public boolean isNormalMagic() {
		// Check if square is complete first to prevent null pointer exception
		if (!isArrayComplete()) {
			return false;
		}
		// Make a copy of the 2D array square and turn it into a 1D array
		int sizeSquared = size * size;
		Integer[] matrixElementArray = new Integer[sizeSquared];

		int index = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				matrixElementArray[index++] = square[i][j];
			}
		}

		// Verify the square is magic, normal, and complete
		if (doesArrayContainAllValues(matrixElementArray) && isMagic()) {
			return true;
		} else {
			return false;
		}

	}

}