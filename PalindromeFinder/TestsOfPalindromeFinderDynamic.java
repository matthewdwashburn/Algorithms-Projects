package palindrome;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Some basic sanity checks of the Palindrome problem
 * Dynamic Programming tests
 * @author Nathan Gossett
 * @version Spring 2023
 *
 */
public class TestsOfPalindromeFinderDynamic {
	
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
	public void testLegalInputsDyn() {
		String answer = PalindromeFinder.findPalindromeDyn("programming");
		assertTrue(isPalindrome(answer));
		assertEquals(answer.length(), 4);
		
		answer = PalindromeFinder.findPalindromeDyn("supercalifragilisticexpialidocious");
		assertTrue(isPalindrome(answer));
		assertEquals(answer.length(), 15);
		
		answer = PalindromeFinder.findPalindromeDyn("supercalifragilistsupercalifragilisticexpialidociousicexpialidocious");
		assertTrue(isPalindrome(answer));
		assertEquals(answer.length(), 29);
	}
	
	@Test
	public void testLongDyn(){
		
		//This one should go almost instantly and still get the right answer
		String answer = PalindromeFinder.findPalindromeDyn("loremipsumdolorsitametconsecteturadipisicingelitseddoeiusmodtemporincididuntutlaboreetdoloremagnaaliquautenimadminimveniamquisnostrudexercitationullamcolaborisnisiutaliquipexeacommodoconsequatduisauteiruredolorinreprehenderitinvoluptatevelitessecillumdoloreeufugiatnullapariaturexcepteursintoccaecatcupidatatnonproidentsuntinculpaquiofficiadeseruntmollitanimidestlaborum");
		assertTrue(isPalindrome(answer));
		assertEquals(answer.length(), 154);
	}

}