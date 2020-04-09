package lesson200408;

import java.util.ArrayList;
import java.util.List;

public class Graph {

	private final int V;
	private final List<Integer> adj[];
	
	public Graph(int V) {
		this.V = V;
		adj = new ArrayList[V];
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new ArrayList<Integer>();
		}
	}
	
	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
	}
	
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
	
	public void print() {
		for (int v = 0; v < adj.length; v++) {
			for (Integer w : adj[v]) {
				System.out.println(v + " - " + w);
			}
		}
	}

}
