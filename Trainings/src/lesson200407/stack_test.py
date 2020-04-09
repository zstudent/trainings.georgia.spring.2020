import unittest
import Stack

class Test(unittest.TestCase):
    stack = Stack.Stack()

    def setUp(self):
        for i in range(5):
            self.stack.push(i)
    
    
    def test_pop(self):
        self.assertEqual(4, self.stack.pop())
    
    def test_top(self):
        self.assertEqual(4, self.stack.top())

    
    

if __name__ == "__main__":
    unittest.main()