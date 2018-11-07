package learning.ds.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class GraphDepthFirstSearch {

	public static void main(String[] args) {
		GraphImpl graph = new GraphImpl(5);

		graph.addEdge(1, 0);
		// 1___0
		graph.addEdge(0, 2);
		// 1___0___2
		graph.addEdge(2, 1);
		// 1___0___2
		// |_______|
		graph.addEdge(0, 3);
		//     3
		//     |
		// 1___0___2
		// |_______|
		graph.addEdge(1, 4);
		// 4   3
		// |   |
		// 1___0___2
		// |_______|

		graph.dfs(0);
	}

}

class GraphImpl {
	private int numOfVertices;
	private LinkedList<Integer> adjLists[];

	/**
	 * Graph constructor creates a graph of provided number of vertices.
	 * 
	 * @param numOfVertices
	 */
	GraphImpl(int numOfVertices) {
		this.numOfVertices = numOfVertices;

		adjLists = new LinkedList[numOfVertices];

		for (int i = 0; i < numOfVertices; i++)
			adjLists[i] = new LinkedList<>();
	}

	/**
	 * The method adds edges to the elements.
	 * 
	 * @param source
	 * @param dest
	 */
	void addEdge(int source, int dest) {
		adjLists[source].add(dest);
	}

	void dfs(int first) {
		boolean visited[] = new boolean[this.numOfVertices];

		Stack<Integer> stack = new Stack<>();

		stack.push(first);

		while (!stack.isEmpty()) {
			Integer current = stack.pop();

			if (!visited[current]) {
				System.out.println(current);
				visited[current] = true;
			}

			Iterator<Integer> adjs = adjLists[current].iterator();

			while (adjs.hasNext()) {
				Integer next = adjs.next();

				if (!visited[next]) {
					stack.push(next);
				}
			}
		}
	}
}
