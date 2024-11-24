package palindrome;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
/**
 * Some basic sanity checks of the Palindrome problem
 * Recursive version tests
 * @author Nathan Gossett
 * @version Spring 2023
 *
 */
class TestsOfPalindromeFinderRecursive {
	
	/**
	 * Check to see if the input is a palindrome.  
	 * Does not ignore spaces or punctuation
	 * @param s the string to check
	 * @return true if s is a palindrome, false if not
	 */
	public static boolean isPalindrome(String s){
		if(s.length() == 0) return true;
		s = s.toLowerCase();
		for(int i = 0; i <= s.length()/2; i++){
			if(s.charAt(i) != s.charAt(s.length()-i-1)){
				return false;
			}
		}
		return true;
	}

	@Test
	public void testLegalInputsRec() {
		String answer = PalindromeFinder.findPalindromeRec("programming");
		assertTrue(isPalindrome(answer));
		assertEquals(answer.length(), 4);
		
		answer = PalindromeFinder.findPalindromeRec("expialidociouse");
		assertTrue(isPalindrome(answer));
		assertEquals(answer.length(), 5);
		
		//will take a few seconds
		answer = PalindromeFinder.findPalindromeRec("supercalifragilisticexpialidocious");
		assertTrue(isPalindrome(answer));
		assertEquals(answer.length(), 15);
	}
	
	//@Test //uncomment @Test if you want to run this one.  Should take too long for recursive
	public void testLongRec(){
		
		//This one should take several seconds (up to 25 seconds before you kill it)
		String answer = PalindromeFinder.findPalindromeRec("loremipsumdolorsitametconsectetu");
		assertTrue(isPalindrome(answer));
		assertEquals(answer.length(), 13);
	}

}