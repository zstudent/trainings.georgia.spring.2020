import java.util.LinkedList;

public class Graph{
	int Verticies;	
	LinkedList<Integer> allEdges[];	

	public Graph( int V ){	
		Verticies = V;
		allEdges = new LinkedList[Verticies];
		for(int i=0; i<Verticies; i++) allEdges[i] = new LinkedList<Integer>();
	}
	
	public void addEdge( int v1, int v2 ){
		allEdges[v1].add(v2);
		allEdges[v2].add(v1);
	}
		
	public int numVerticies(){
		return Verticies;
	}
	
	public LinkedList<Integer> neighbours(int v){
		return allEdges[v];
	}
	
}