package kattis;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * The shortestpath1 program takes in a weighted, directed graph and calculates
 * the shortest path to every queried node
 * 
 * 
 * @author Matthew Washburn
 * @version 1
 * @date 11/13/2024
 *
 */
public class shortestpath1 {
	// Create edge object class
	public static class Edge {
		int to;
		int weight;

		/**
		 * @param to,     the node this edge points to
		 * @param weight, the weight of the edge
		 */
		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Scanner to read in graph parameters
		Scanner scn = new Scanner(System.in);
		// Loop for as many graphs as inputted
		while (true) {
			// Set initial graph parameters
			int nodes = scn.nextInt();
			int edges = scn.nextInt();
			int queries = scn.nextInt();
			int startNode = scn.nextInt();
			// If all parameters are 0, we know there are no more graphs
			if (nodes == 0 && edges == 0 && queries == 0 && startNode == 0) {
				break;
			}

			// Create egde storage as array of linked lists
			LinkedList<Edge>[] edgeList = new LinkedList[nodes];

			// Fill all nodes with linked lists to store edges
			for (int i = 0; i < nodes; i++) {
				edgeList[i] = new LinkedList<>();
			}

			// Fill edge storage with edges
			for (int i = 0; i < edges; i++) {
				int startVertex = scn.nextInt();
				int endVertex = scn.nextInt();
				int weight = scn.nextInt();
				edgeList[startVertex].add(new Edge(endVertex, weight));
			}

			// Create distance from start node array
			int[] distances = new int[nodes];
			// Initialize all values to max distance
			Arrays.fill(distances, Integer.MAX_VALUE);

			// Set the StartNode distance from itself to 0
			distances[startNode] = 0;

			// Create Priority Queue that sorts edges by weight
			PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
			queue.add(new Edge(startNode, 0));

			// Loop until no edges are left in the queue
			while (!queue.isEmpty()) {
				// Pop the lowest weight edge from the queue and begin checking neighbors
				Edge current = queue.poll();
				int currentNode = current.to;

				// Skip this edge if the neighboring nodes distance from start is already
				// shorter than this new edge's weight
				if (current.weight > distances[currentNode]) {
					continue;
				}

				// Otherwise check neighboring nodes
				// For every edge that starts at the current node
				for (Edge edge : edgeList[currentNode]) {
					// Name the node its pointing to neighbor
					int neighborNode = edge.to;
					// Calculate weight with this new path
					int neighborEdgeWeight = distances[currentNode] + edge.weight;
					// If new weight is less than neighbors current distance from start
					if (neighborEdgeWeight < distances[neighborNode]) {
						// Replace current distance with new neighbor path weight
						distances[neighborNode] = neighborEdgeWeight;
						// Add this neighboring node to the queue with new distance
						queue.add(new Edge(neighborNode, distances[neighborNode]));
					}
				}

			}
			// Output shortest distance from starting node to each node in the queries
			for (int i = 0; i < queries; i++) {
				int currentQuery = scn.nextInt();
				// If distance is still maxed, theres no node pointing to it so its impossible
				// to get there
				if (distances[currentQuery] == Integer.MAX_VALUE) {
					System.out.println("Impossible");
				} else {
					System.out.println(distances[currentQuery]);
				}

			}

		}
		// Close scanner after all loops
		scn.close();
	}
}
