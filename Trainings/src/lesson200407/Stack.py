
class StackIterator:
    def __init__(self, head):
        self.current = head

    def __iter__(self):
        return self

    def __next__(self):
        if not self.current:
            raise StopIteration
        else:
            item = self.current.data
            self.current = self.current.next
            return item

class Node:
    def __init__(self,data=None):
        self.data = data
        self.next = None

class Stack:
    def __init__(self):
        self.head = Node()
    
    
    # def __iter__(self):
    #     return StackIterator(self.head)


    def push(self,item):
        oldfirst = self.head
        first = Node(item)
        self.head = first
        first.next = oldfirst
    
    def pop(self):
        if self.head.data == None:
            return "null"
        item = self.head.data
        self.head = self.head.next
        return item
    
    def top(self):
        if self.head.data == None:
            return "null"
        return self.head.data

    def traverse(self):
        current = self.head
        while current:
            yield current.data
            current = current.next
