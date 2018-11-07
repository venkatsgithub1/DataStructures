package learning.ds.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class GraphBreadthFirstSearch {

	public static void main(String[] args) {
		Graph graph = new Graph(4);

		graph.addEdge(0, 1);
		// 0__1
		graph.addEdge(0, 2);
		// 0__1
		// |
		// 2
		graph.addEdge(1, 2);
		// 0__1
		// | |
		// 2__|
		graph.addEdge(2, 0);
		// 0___1
		// || |
		// 2___|
		graph.addEdge(2, 3);
		// 0___1
		// || |
		// 2___|
		// |
		// 3
		graph.addEdge(3, 3);
		// 0___1
		// || |
		// 2___|
		// |
		// 3__
		// |__|

		graph.bfs(1);
	}

}

class Graph {
	private int numOfVertices;
	private LinkedList<Integer> adjLists[];

	/**
	 * Graph constructor creates a graph of 
	 * provided number of vertices.
	 * @param numOfVertices
	 */
	Graph(int numOfVertices) {
		this.numOfVertices = numOfVertices;

		adjLists = new LinkedList[numOfVertices];

		for (int i = 0; i < numOfVertices; i++)
			adjLists[i] = new LinkedList<>();
	}

	/**
	 * The method adds edges to the
	 * elements.
	 * @param source
	 * @param dest
	 */
	void addEdge(int source, int dest) {
		adjLists[source].add(dest);
	}

	/**
	 * The method prints out
	 * elements in the graph
	 * based on breadth.
	 * Breadth first search.
	 * @param first
	 */
	void bfs(int first) {
		boolean visited[] = new boolean[this.numOfVertices];

		Queue<Integer> queue = new LinkedList<>();

		visited[first] = true;
		queue.add(first);

		while (!queue.isEmpty()) {
			Integer current = queue.poll();
			System.out.println(current);

			Iterator<Integer> adjs = adjLists[current].iterator();

			while (adjs.hasNext()) {
				Integer next = adjs.next();
				if (!visited[next]) {
					visited[next] = true;
					queue.add(next);
				}
			}
		}
	}
}
