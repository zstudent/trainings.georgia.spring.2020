import java.util.ArrayList;
import java.util.List;

public class Graph {

    private int [][] graph;
    private int numberOfNodes;

    public Graph(int n){
        graph = new int[n][n];
        numberOfNodes = n;
    }

    public int getNumberOfNodes(){
        return numberOfNodes;
    }

    public void addEdge(int a, int b){
        graph[a][b] = 1;
        graph[b][a] = 1;
    }

    public void removeEdge(int a, int b){
        graph[a][b] = 0;
        graph[b][a] = 0;
    }

    public List<Integer> getNeighbors(int node){
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < numberOfNodes; i++){
            if(graph[node][i] == 1){
                list.add(i);
            }
        }
        return list;
    }
}
