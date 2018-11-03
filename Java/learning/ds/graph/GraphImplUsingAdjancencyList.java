package learning.ds.graph;

import java.util.LinkedList;

public class GraphImplUsingAdjancencyList {

	static class Graph {
		int v;
		LinkedList<Integer> adjListArray[];

		Graph(int v) {
			this.v = v;
			adjListArray = new LinkedList[v];

			for (int i = 0; i < v; i++) {
				adjListArray[i] = new LinkedList<>();
			}
		}
	}

	public static void addEdge(Graph graph, int src, int des) {
		graph.adjListArray[src].add(des);
		graph.adjListArray[des].add(src);
	}

	public static void printGraph(Graph graph) {
		for (int i = 0; i < graph.v; i++) {
			System.out.println("adjacency list of vertex " + i);
			System.out.print("head");
			for (Integer adjacentVertex : graph.adjListArray[i]) {
				System.out.print(" ->" + adjacentVertex);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int vertices = 5;
		Graph graph = new GraphImplUsingAdjancencyList.Graph(vertices);
		addEdge(graph, 0, 1);
		addEdge(graph, 0, 4);
		addEdge(graph, 1, 2);
		addEdge(graph, 1, 3);
		addEdge(graph, 1, 4);
		addEdge(graph, 2, 3);
		addEdge(graph, 3, 4);

		printGraph(graph);
	}
}
