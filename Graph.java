import java.util.Stack;

public class Graph {
	private int numV;
	private Stack<Integer> adj[];
	
	@SuppressWarnings("unchecked")
	public Graph(int numV) {
		this.numV = numV;
		this.adj = new Stack[numV];
		for(int i=0; i<numV; i++) {
			adj[i] = new Stack<Integer>();
		}
	}
	
	public void addEdge(int firstV, int secondV) {
		adj[firstV].push(secondV);
		adj[secondV].push(firstV);
	}
	
	public int getNumV() {
		return this.numV;
	}
	
	public Iterable<Integer> adj(int V){
		return adj[V];
	}
	
	public void printElems() {
		for(int i=0; i<adj.length; i++) {
			for(int currAdj : adj(i)) {
				System.out.println(i + " " + currAdj);
			}
		}
	}
	
}
