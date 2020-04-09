import unittest
from collections import Counter

class Graph:
    def __init__(self, numV):
        self.numV = numV
        self.adj = []
        for i in range(numV):
            self.adj.append([])
    
    def addEdge(self, firstV, secV):
        self.adj[firstV].append(secV)
        self.adj[secV].append(firstV)

    def getNumV(self):
        return self.numV

    def getIterator(self, v):
        return iter(self.adj[v])

    def printElems(self):
        for i in range(len(self.adj)):
            for currAdj in self.getIterator(i):
                print(i, currAdj)

class graphTest(unittest.TestCase):
    
    def test(self):
        graph = Graph(4)
        graph.addEdge(0, 1)
        graph.addEdge(1, 2)
        graph.addEdge(2, 3)
        graph.addEdge(3, 0)

        self.assertEqual(Counter(graph.getIterator(0)), Counter([1, 3]))
        self.assertEqual(Counter(graph.getIterator(1)), Counter([2, 0]))
        self.assertEqual(Counter(graph.getIterator(2)), Counter([3, 1]))
        self.assertEqual(Counter(graph.getIterator(3)), Counter([2, 0]))
        self.assertEqual(4, graph.getNumV())

if __name__ == '__main__':
    unittest.main()
