class Graph:

    def __init__(self, n):
        self.numberOfNodes = n
        self.graph = []
        for i in range(0,n):
            a = [0]*n
            self.graph.append(a)

    def getNumberOfNodes(self):
        return self.numberOfNodes
    
    def addEdge(self, a ,b):
        self.graph[a][b] = 1
        self.graph[b][a] = 1

    def removeEdge(self, a ,b):
        self.graph[a][b] = 0
        self.graph[b][a] = 0
    
    def getNeighbors(self, n):
        ret = []
        for i in range(0, self.numberOfNodes):
            if self.graph[n][i] == 1:
                ret.append(i)
        return ret

    