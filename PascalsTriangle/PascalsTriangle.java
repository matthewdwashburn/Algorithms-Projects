package pascal;

/**
 * The Pascals Triangle class provides two methods for calculating values from
 * Pascal's Triangle using both recursive and dynamic programming.
 * 
 * @author Matthew Washburn
 */
public class PascalsTriangle {

	public static void main(String[] args) {
	}

//	 Calculates the value at row n and column r of Pascal's Triangle using
//	 recursion.

	public static int pascalsTriangleRec(int n, int r) {
		if (r > n) {
			throw new IllegalArgumentException("Invalid input");
		}
		if (r == 0 || r == n) {
			return 1; // Base case
		}
		// Recursively sum of the two values above in the triangle
		return pascalsTriangleRec(n - 1, r) + pascalsTriangleRec(n - 1, r - 1);
	}

//		Calculates the value at row n and column r of Pascal's Triangle using dynamic
//		programming.

	public static int pascalsTriangleDyn(int n, int r) {
		int pascal[][] = new int[n + 1][n + 1]; // 2D array to store computed values
		if (r > n) {
			throw new IllegalArgumentException("Invalid input");
		}
		// Initialize the edges of the triangle
		for (int i = 0; i <= n; i++) {
			pascal[i][0] = 1; // First column
			pascal[i][i] = 1; // Diagonal
		}
		// Fill the rest of the triangle
		for (int i = 0; i <= n; i++) {
			for (int j = 1; j < i; j++) {
				pascal[i][j] = pascal[i - 1][j] + pascal[i - 1][j - 1];
			}
		}
		return pascal[n][r]; // Return the desired value
	}
}
