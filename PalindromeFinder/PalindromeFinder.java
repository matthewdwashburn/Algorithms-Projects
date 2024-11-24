/**
 * The Palindrom Finder Program finds the longest palindrome subsequence in a string
 * 
 * 
 * @author Matthew Washburn
 * @version 2
 * @date 9/30/2024
 *
 */
package palindrome;

//main class 
/**
 * 
 */
public class PalindromeFinder {
	public static void main(String[] args) {
		// Non JUnit Test Recursive
//		System.out.println(findPalindromeRec("programming"));
		// Non JUnit Test Dynamic
//		System.out.println(findPalindromeDyn("programming"));
	}

	// Recursive method to find longest palindrome subsequence in string
	/**
	 * @param s
	 * @return
	 */
	public static String findPalindromeRec(String s) {

		// Base case: If string is 1 or less chars long, the longest palindrome sequence
		// is the inputed string
		if (s.length() <= 1) {
			return s;
		}
		// If first and last characters in string are the same
		if (s.charAt(0) == s.charAt(s.length() - 1)) {
			// Cut off the ends of the string and return them to the final answer
			return s.charAt(0) + findPalindromeRec(s.substring(1, s.length() - 1)) + s.charAt(s.length() - 1);
		}
		// Check if the first char matches the second to last char in the string and
		// call recursively until it meets one of the above cases, set to a variable
		String leftCheck = findPalindromeRec(s.substring(0, s.length() - 1));
		// Same as above but check if the last char matches the second char in the
		// string
		String rightCheck = findPalindromeRec(s.substring(1, s.length()));
		// Whichever found a match first, (longer final string) return that recursive
		// case
		if (leftCheck.length() > rightCheck.length()) {
			return leftCheck;
		} else {
			return rightCheck;
		}

	}
	// Dynamic method to find longest palindrome subsequence in string
	/**
	 * @param s
	 * @return
	 */
	public static String findPalindromeDyn(String s) {
		// Initialize a palindrome string array the size of the length of array
		String[][] palindrome = new String[s.length()][s.length()];
		// Fill the word diagonally down the middle of the array
		// Everything else filled with empty string
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < s.length(); j++) {
				if (j == i) {
					palindrome[i][j] = s.charAt(i) + "";
				} else {
					palindrome[i][j] = "";
				}
			}
		}
		// j is the y axis, j + i is the x axis
		// Nested for loop scheme to traverse diagonally
		for (int i = 1; i < s.length(); i++) {
			for (int j = 0; j < s.length() - i; j++) {
				// Focus index is always [j] [j+1], where the cross between top left and top
				// right will always be

				// Check if the top left of the array equals the bottom right on each iteration
				if (palindrome[j][j].equals(palindrome[j + i][j + i])) {
					// Take the palindrome inside the index - 1 from the j(y axis) and j+i (x axis)
					// Then concatenate that in the middle of the two indexes we found were equal
					palindrome[j][j + i] = palindrome[j][j] + palindrome[j + 1][j + i - 1] + palindrome[j + i][j + i];
				} else {
					// Otherwise the left and right sides are not equal
					// So we compare the index staying at the same y and x-1 to the index of the
					// same
					// x and y-1 from our current focus index and chose the larger
					// string of the two indexes to place inside our focus index
					if (palindrome[j + 1][j + i].length() >= palindrome[j][j + i - 1].length()) {
						palindrome[j][j + i] = palindrome[j + 1][j + i];
					} else {

						palindrome[j][j + i] = palindrome[j][j + i - 1];
					}
				} // This will insure we are pushing the longest palindrome up the chain and we
					// will always get the longest palindrome in the top right of the 2d array
			}

		}
		// Print out 2d string array (for testing)
//		for (int i = 0; i < palindrome.length; i++) {
//			for (int j = 0; j < palindrome.length; j++) {
//				System.out.print(palindrome[i][j] + ",");
//			}
//			System.out.println();
//
//		}
		// Return longest palindrome at the top right of 2d array
		return palindrome[0][s.length() - 1];
	}

}
