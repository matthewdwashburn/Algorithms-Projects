package kayak;

/**
 * The Kayak program traverses 2D arrays looking for the word "KAYAK" in any
 * direction
 * 
 * @author Matthew Washburn
 * @version 1
 *
 */
public class Kayak {
	// Boolean method returns true if char array contains "KAYAK"
	public static boolean containsKayak(char[][] data) {
		// Loop traverses length of 2d array by row and column
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				// As soon as the letter K is found, start searching in each direction
				if (data[i][j] == 'K') {
					// Each direction works both backwards and forwards as kayak is a palindrome
					// and checks whether the word length of kayak can fit without going off the
					// 2D array grid. This prevents out of bounds failures or checks starting on the
					// far right and continuing on the far left of a row or column, where the
					// program could identify a disconnected kayak and return true by mistake.

					// Check vertical direction
					if (i + 4 < data.length && data[i + 1][j] == 'A' && data[i + 2][j] == 'Y' && data[i + 3][j] == 'A'
							&& data[i + 4][j] == 'K') {
						return true;
					}
					// Check horizontal direction
					if (j + 4 < data.length && data[i][j + 1] == 'A' && data[i][j + 2] == 'Y' && data[i][j + 3] == 'A'
							&& data[i][j + 4] == 'K') {
						return true;
					}
					// Check diagonal down right direction
					if (i + 4 < data.length && j + 4 < data.length && data[i + 1][j + 1] == 'A'
							&& data[i + 2][j + 2] == 'Y' && data[i + 3][j + 3] == 'A' && data[i + 4][j + 4] == 'K') {
						return true;
					}
					// Check diagonal down left direction
					if (i + 4 < data.length && j - 4 >= 0 && data[i + 1][j - 1] == 'A' && data[i + 2][j - 2] == 'Y'
							&& data[i + 3][j - 3] == 'A' && data[i + 4][j - 4] == 'K') {
						return true;
					}
				}

			}

		}
		// If kayak is not found, method returns false
		return false;
	}
}