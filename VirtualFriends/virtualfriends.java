package kattis;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Scanner;

public class virtualfriends {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		// Scan in # of test cases
		int testCases = scn.nextInt();
		// For each test case, set each name to a number
		for (int i = 0; i < testCases; i++) {
			// Convert friend names to inedexs
			HashMap<String, Integer> friendIndex = new HashMap<>();
			// number of friendships
			int friendCount = 0;
			int friendships = scn.nextInt();
			int size[] = new int[friendships * 2];
			for (int j = 0; j < friendships; j++) {
				String friend1 = scn.next();
				String friend2 = scn.next();

				// If both friends don't exist
				if (!friendIndex.containsKey(friend1) && !friendIndex.containsKey(friend2)) {
					// Make friend 1 an index
					friendIndex.put(friend1, friendCount);
					friendCount++;
					// Make friend 1 the parent
					size[friendIndex.get(friend1)] = -2;
					// Make friend 2 an index
					friendIndex.put(friend2, friendCount);
					friendCount++;
					// Make friend 2 the child
					size[friendIndex.get(friend2)] = friendIndex.get(friend1);
					sb.append(-size[friendIndex.get(friend1)]).append("\n");
				}

				// If the first friend already exists
				else if (friendIndex.containsKey(friend1) && !friendIndex.containsKey(friend2)) {
					// Make friend 2 an index
					friendIndex.put(friend2, friendCount);
					friendCount++;
					// If first friend is a root
					int temp = size[find(friendIndex.get(friend1), size)];
					size[find(friendIndex.get(friend1), size)] = temp - 1;
					// Make second friend a child
					size[friendIndex.get(friend2)] = friendIndex.get(friend1);
					sb.append(-size[find(friendIndex.get(friend1), size)]).append("\n");
				}

				// If the second friend already exists
				else if (!friendIndex.containsKey(friend1) && friendIndex.containsKey(friend2)) {
					// Make friend 2 an index
					friendIndex.put(friend1, friendCount);
					friendCount++;
					// If first friend is a root
					int temp = size[find(friendIndex.get(friend2), size)];
					size[find(friendIndex.get(friend2), size)] = temp - 1;
					// Make second friend a child
					size[friendIndex.get(friend1)] = friendIndex.get(friend2);
					sb.append(-size[find(friendIndex.get(friend2), size)]).append("\n");
				}
				// If both friends already exist
				else if (friendIndex.containsKey(friend1) && friendIndex.containsKey(friend2)) {
					union(friendIndex.get(friend1), friendIndex.get(friend2), friendIndex, size, sb);

				}
			}
			

		}
		// Print file built string after all test cases completed
		System.out.println(sb.toString());
		scn.close();
	}

	// Union two sets together
	/**
	 * @param friend1
	 * @param friend2
	 * @param friendIndex
	 * @param size
	 */
	public static void union(int friend1, int friend2, HashMap<String, Integer> friendIndex, int[] size, StringBuilder sb) {
		int root1 = find(friend1, size);
		int root2 = find(friend2, size);
		// If nodes are in the same set, do nothing
		if (root1 == root2) {
			sb.append(-size[root1]).append("\n");
			return;
		}
		// If the first set has more nodes
		if (size[root1] < size[root2]) {
			// Add the sets together
			size[root1] += size[root2];
			size[root2] = root1;
			sb.append(-(size[root1])).append("\n");
		} else {
			// If the second set has more nodes
			size[root2] += size[root1];
			size[root1] = root2;
			sb.append((-size[root2])).append("\n");
		}

	}

	// Find the root node of a friend index
	/**
	 * @param friend
	 * @param size
	 * @return
	 */
	public static int find(int friend, int[] size) {
		if (size[friend] < 0) {
			return friend;
		}
		// Path compression
		size[friend] = find(size[friend], size);
		return size[friend];

	}

}
