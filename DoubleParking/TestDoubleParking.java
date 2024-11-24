package doublePark;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Some basic sanity checks of the Double Parking problem
 * @author Nathan Gossett
 * @version Fall 2024
 *
 */
public class TestDoubleParking {
	
	@Test
	public void testRecursive() {
		assertEquals(DoubleParking.doubleParkRec(12, 8), 369);
		assertEquals(DoubleParking.doubleParkRec(5, 8), 0);
		assertEquals(DoubleParking.doubleParkRec(50, 18), 18053528883775L);
		//Recursive algorithm will likely take too long to complete
//		assertEquals(DoubleParking.doubleParkRec(500, 180), 3261637937208161392L);
				
	}
	
//	@Test
	public void testMemoized() {
		assertEquals(DoubleParking.doubleParkMemo(12, 8), 369);
		
		assertEquals(DoubleParking.doubleParkMemo(5, 8), 0);
		
		assertEquals(DoubleParking.doubleParkMemo(50, 18), 18053528883775L);
		
		assertEquals(DoubleParking.doubleParkMemo(500, 180), 3261637937208161392L);
	}

}