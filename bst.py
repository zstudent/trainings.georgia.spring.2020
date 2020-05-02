class Node:
    def __init__(self, key, value):
        self.left = None
        self.right = None
        self.key = key
        self.value = value

def put(root,node): 
    if root is None: 
        root = node 
    else: 
        if root.key < node.key: 
            if root.right is None: 
                root.right = node 
            else: 
                put(root.right, node) 
        else: 
            if root.left is None: 
                root.left = node 
            else: 
                put(root.left, node)

def get(node, key):
    if node == None:
        return None
    else:
        if key < node.key:
            return get(node.left, key)
        elif key > node.key:
            return get(node.right, key)
        else:
            return node.key

def inorder(root): 
    if root: 
        inorder(root.left) 
        print(root.value) 
        inorder(root.right) 
