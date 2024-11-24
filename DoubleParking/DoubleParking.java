package doublePark;

/**
 * The double parking program calculates the number of ways to position c number of
 * cars into p number of parking spots such that there is 2 open parking spots right
 * next to each other
 * 
 * 
 * 
 * @author Matthew Washburn
 * @version 1
 * @date 9/24/2024
 *
 */

//main class 
public class DoubleParking {

	public static void main(String[] args) {
		// Test without JUnit
//		System.out.println(doubleParkMemo(500, 180));
//		System.out.println(doubleParkRec(500, 180));
	}

// Recursive solution method (very slow)
	public static long doubleParkRec(int p, int c) {

		// Less than 2 parking spots, 0 ways to get 2 open spots adjacent to each other
		if (p < 2) {
			return 0;
		}
		// No cars and more than 2 spots, one way to position empty parking lot
		if (c == 0 && p >= 2) {
			return 1;
		}
		// If all edge cases are passed, recursively call and add the # of ways in each
		// possible case
		// 1. Car parked in the last spot, ignore that spot and shrink parking lot by 1
		// + 2. No car in last spot but car in the second to last spot, ignore those
		// last 2 spots
		// + 3. Last 2 spots are both empty and any arrangement of c cars in the
		// remaining spots
		return doubleParkRec(p - 1, c - 1) + doubleParkRec(p - 2, c - 1) + binomial(p - 2, c);
	}

	// Binomial method to calculate the number of of ways arrange cars in remaining
	// spots
	private static long binomial(int n, int k) {
		if (k > n)
			return 0;
		if (k > n - k)
			k = n - k;
		long b = 1;
		for (int i = 1, m = n; i <= k; i++, m--)
			b = b * m / i;
		return b;
	}

//Defining long 2d array as memo
	private static long[][] memo;

	// Memoized solution method (much faster)
	public static long doubleParkMemo(int p, int c) {
		// Create memo array with size 1 larger than p and c on each axis so the value
		// of the parked cars and spots matches the index of the array (0 based)
		memo = new long[p + 1][c + 1];
		// Loop through every index and initialize every value in the array to illegal
		// value so we know what calculations have already been done
		for (int i = 0; i < p + 1; i++) {
			for (int j = 0; j < c + 1; j++) {
				memo[i][j] = -1;
			}
		}
		// Visual representation of memo array
//		doubleParkMemoRec(p, c);
//		for (int i = 0; i < p+1; i++) {
//			for (int j = 0; j < c+1; j++) {
//				System.out.print(memo[i][j] + ",");
//			}
//			System.out.println();
//		}
		// Call memo recursive function once
		return doubleParkMemoRec(p, c);
	}

	private static long doubleParkMemoRec(int p, int c) {
		// If this spot in the memo array has a value, skip it because we've already
		// done that calculation and return it to doubleParkMemoRec recursive call
		if (memo[p][c] != -1) {
			return memo[p][c];
		}
		// Same recursive method as before but stores value 0 in memo array and returns
		// to doubleParkMemoRec recursive call
		if (p < 2) {
			memo[p][c] = 0;
			return memo[p][c];
		}
		// Same recursive method as before but stores value 0 in memo array and returns
		// to doubleParkMemoRec recursive call
		if (c == 0 && p >= 2) {
			memo[p][c] = 1;
			return memo[p][c];
		}
		// If all edge cases are passed, recursively call and add the # of ways in each
		// possible case
		// Except this time store that value to the current index in the memo array
		memo[p][c] = doubleParkMemoRec(p - 1, c - 1) + doubleParkMemoRec(p - 2, c - 1) + binomial(p - 2, c);
		// Return total # of ways given p and c to doubleParkMemo
		return memo[p][c];
	}
}
