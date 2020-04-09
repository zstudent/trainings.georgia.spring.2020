import unittest
import graph

class Test(unittest.TestCase):


    def fill_graph(self,v):
        gr = graph.Graph(5)
        gr.add_edge(0, 1)
        gr.add_edge(0, 2)
        gr.add_edge(1, 2)
        gr.add_edge(1, 3)
        gr.add_edge(2, 1)
        gr.add_edge(2, 3)
        return gr.degrees(v)

    
    def test0(self):
        self.assertEqual(2, self.fill_graph(0))
    
    def test1(self):
        self.assertEqual(4, self.fill_graph(1))
    
    def test2(self):
        self.assertEqual(4, self.fill_graph(2))
    



if __name__ == "__main__":
    unittest.main()