
class Graph:

    def __init__(self, V = 0):
        self.V = V
        self.adj = [list() for _ in range(V)]
    
    
    def add_edge(self,v,w):
        self.adj[v].append(w)
        self.adj[w].append(v)
    
    def print_graph(self):
        for v in range(self.V):
            for w in self.adj[v]:
                yield str(v) + " - " + str(w)
    
    def degrees(self,v):
        degree = 0
        for w in self.adj[v]:
            degree += 1
        return degree
          



    
