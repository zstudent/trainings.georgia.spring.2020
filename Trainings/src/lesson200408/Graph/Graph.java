package lesson200408;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	private final int V;
	public final ArrayList<Integer> adj[];
	
	public Graph(int V) {
		this.V = V;
		adj = new ArrayList[V];
		for(int v = 0; v < V; v++) {
			adj[v] = new ArrayList<>();
		}
	}
	
	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
	}
	
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	public void print() {
		for(int v = 0; v < V; v++) {
			for(Integer w: adj[v]) {
				System.out.println(v + " - " + w);
			}
		}
	}
	
	public int degree(int v) {
		int degree = 0;
		for(int w: adj[v]) {
			degree++;
		}
		return degree;
	}
	
	public ArrayList<String> graphList(){
		ArrayList<String> arr = new ArrayList<String>();
		for(int v = 0; v < V; v++) {
			for(Integer w: adj[v]) {
				StringBuilder sb = new StringBuilder();
				sb.append(v).append(" - ").append(w);
				arr.add(sb.toString());
			}
		}
		return arr;
	}
}
