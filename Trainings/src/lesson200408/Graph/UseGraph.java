package lesson200408;

import java.util.ArrayList;

public class UseGraph {
	
	public static void main(String[] args) {

		Graph gr = new Graph(10);
		
		
		for(int i = 0; i < gr.adj.length; i++) {
			System.out.println(gr.adj[i]);
			
		}
	}
	
	
}
