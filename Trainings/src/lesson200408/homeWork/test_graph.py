import unittest
import collections
from Graph import Graph

class TestGrap(unittest.TestCase):

    def testConstructor(self):
        g = Graph(10)
        self.assertEqual(10, g.getNumberOfNodes())

    def testaddEdge(self):
        g = Graph(10)
        g.addEdge(2, 4)
        g.addEdge(2, 3)
        g.addEdge(2, 5)
        g.addEdge(2, 7)
        ret = [4,7,3,5]
        self.assertEqual(collections.Counter(ret),collections.Counter(g.getNeighbors(2)))

    def testremoveEdge(self):
        g = Graph(10)
        g.addEdge(2, 4)
        g.addEdge(2, 3)
        g.addEdge(2, 5)
        g.addEdge(2, 7)
        g.removeEdge(2, 3)
        g.removeEdge(2, 7)
        ret = [4,5]
        self.assertEqual(collections.Counter(ret),collections.Counter(g.getNeighbors(2)))
        
        

if __name__ == '__main__':
    unittest.main()