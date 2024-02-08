# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Codec:
    def encode(self, node, arr):
        if node is None:
            arr.append("X")
        else:
            arr.append(str(node.val))
            self.encode(node.left, arr)
            self.encode(node.right, arr)

    def serialize(self, root):
        """Encodes a tree to a single string.
        
        :type root: TreeNode
        :rtype: str
        """
        arr = []
        self.encode(root, arr)
        print(arr)
        return ",".join(arr)           

    def decode(self, arr):
        if self.i < len(arr):
            cur = arr[self.i]
            self.i = self.i+1

            if cur == "X":
                return None
            
            node = TreeNode(int(cur))
            node.left = self.decode(arr)
            node.right = self.decode(arr)
            return node
        
        return None

    def deserialize(self, data):
        """Decodes your encoded data to tree.
        
        :type data: str
        :rtype: TreeNode
        """
        arr = data.split(",")
        self.i = 0

        return self.decode(arr)

# Your Codec object will be instantiated and called as such:
ser = Codec()
deser = Codec()
code = "1,2,X,X,3,4,X,X,5,X,X"
ans = ser.serialize(deser.deserialize(code))
print(ans == code)