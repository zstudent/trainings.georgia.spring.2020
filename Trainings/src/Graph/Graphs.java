package Graph;

import java.util.ArrayList;
import java.util.List;

import lesson200407.LinkedList;
import java.util.*;

public class Graphs {
	
	private int Verticies;	//Number of vertices.
	private java.util.LinkedList<Integer> adjList[];	//Array of linked lists.
	
	//Public constructor.
	public Graphs( int V )
	{	
		this.Verticies = V;	//Initialize num. of vertices.		
		
		adjList = new java.util.LinkedList[ this.Verticies ];
		for(int i=0; i<this.Verticies; i++)
		{
			adjList[i] = new java.util.LinkedList<Integer>();
			
		}
	}
	
	//Adding edge between two vertices.
	public void addEdge( int v1, int v2 )
	{
		adjList[v1].add(v2);
		adjList[v2].add(v1);
	}
		
	
	//returns number of vertices.
	public Integer numVerticies()
	{
		return this.Verticies;
	}
	
	
	//return neighbors of wanted vertex.
	public Iterable<Integer> neighbours(int v)
	{
		return adjList[v];
	}
	
	//Prints graph as a string.
	@Override
	public String toString()
	{	
		String res = "";
		for(int i=0; i<this.Verticies; i++)
		{
			res += "Node: " + i +" ----> [ ";
			for(Integer element : adjList[i])
			{
				res+= element + ", ";
			}
			res += "]\n";
		}
		return res;
	}
	
}
