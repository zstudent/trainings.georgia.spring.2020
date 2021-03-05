class Node: 
    def __init__(self, k, v): 
        self.left = None
        self.right = None
        self.val = v
        self.key = k

def insert(r, k, v):
    if k > r.key:
        if r.right is None:
            r.right = Node (k,v)
        else:
            insert(r.right, k, v)
    else:
        if r.left is None:
            r.left = Node (k,v)
        else:
            insert(r.left, k, v)

def get(r, k):
    if r is None:
        return None
    if r.key == k:
        return r.val
    if k > r.key:
        return get(r.right, k)
    return get(r.left, k)



root = Node("c","c")
insert(root,"b","b")
insert(root,"a","a")
print(get(root,"a"))
print(get(root,"b"))


