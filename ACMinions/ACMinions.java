package acminions;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The AC Minions program finds the least amount of rooms required for an evil
 * lair based on the temperature requests of each minion.
 * 
 * 
 * @author Matthew Washburn
 * @version 1
 * @date 9/12/2024
 *
 */

//main class 
class ACMinions {
	public static void main(String[] args) {
		{
			// The final number of rooms required
			int rooms = 0;
			// Scanner takes in #of minions and lists their temp ranges
			Scanner scanner = new Scanner(System.in);
			// The first integer scanned in, number of minions to sort
			int numberOfMinions = scanner.nextInt();
			// Creates an array list of Minion objects to store each minion's temps
			ArrayList<Minion> minionList = new ArrayList<>();
			// Loop that iterates for the number of minions
			for (int i = 0; i < numberOfMinions; i++) {
				// temporary variable holders for the min and max tolerable temps
				int minTemp = scanner.nextInt();
				int maxTemp = scanner.nextInt();
				// Creates a new minion and stores both temps in array list
				minionList.add(new Minion(minTemp, maxTemp));

			}

//			System.out.println(minionList);
			// Sorts the minions in the array list by lowest min temp to highest min temp
			Collections.sort(minionList);
//			System.out.println(minionList);

			// While the minion list is not empty
			while (!minionList.isEmpty()) {
				// Save the value of the lowest min temp minion
				int intTemp = minionList.get(0).end;
				// Remove it, because we know
				minionList.remove(0);
				// Add one room cause we know we will need at least one more room for the
				// current minion that is still in the array list
				rooms++;
				// For the length of the minion list
				for (int i = 0; i < minionList.size(); i++) {
//					System.out.println(i+" < " +minionList.size());
					// If the min value of the current minion in index i is less than or equal to
					// the max temp of the lowest min temp minion who was removed above
					if (minionList.get(i).start <= intTemp) {
//						System.out.println("It entered");
						// Remove that minion because both are in the same temp range, and thus can be
						// contained in the same room
						minionList.remove(i);
						// Iterate one back because array list automatically shifts to index 0 after
						// .remove so without this the loop would skip a value for each iteration
						i--;
					}

				}

			}
			System.out.println(rooms);

		}
	}

	// Minion object class creates an array to store each minion's temps
	private static class Minion implements Comparable<Minion> {
		int start, end;

		/**
		 * @param s
		 * @param e
		 */
		Minion(int s, int e) {
			start = s;
			end = e;
		}
		// Compare to function used to sort the array list
		public int compareTo(Minion other) {

			if (this.end < other.end) {
				return -1; // this max temp is less
			} else if (this.end > other.end) {
				return 1; // this max temp is more
			} else {
				return 0; // this max temp is the same value
			}
		}

//    	@Override
//    	public String toString() {
//    		return "("+start+","+end+")";
//    	}
	}

}
