package lesson200408;

public class UseGraph {
	public static void main(String[] args) {
		Graph g = new Graph(5);
		
		g.addEdge(0, 1);
		g.addEdge(3, 4);
		g.addEdge(0, 2);
		g.addEdge(2, 3);
		g.addEdge(4, 1);
		
		g.print();
	}
}
