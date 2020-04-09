package Graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class GraphSearch {
	public static boolean DFS(Integer from,Integer to,Graphs g)
	{	
		Set<Integer> visited = new HashSet<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(from);
		visited.add(from);
		while( !stack.isEmpty() )
		{
			Integer current = stack.pop();
			if(to.equals(current))return true;
			Iterable<Integer> neighbors = g.neighbours(current);
			for( Integer element : neighbors )
			{
				if(!visited.contains(element))
				{
					stack.add(element);
					visited.add(element);
				}
			}
		}
		return false;
	}
}
