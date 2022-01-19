# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def recoverTree(self, root: Optional[TreeNode]) -> None:
        """
	Do not return anything, modify root in-place instead.

        You are given the root of a binary search tree (BST), 
        where the values of exactly two nodes of the tree were swapped by mistake.
        Recover the tree without changing its structure.
        
        
        Input: root = [1,3,null,null,2]
        Output: [3,1,null,null,2]
        Explanation: 3 cannot be a left child of 1 because 3 > 1. 
        Swapping 1 and 3 makes the BST valid.
        """
        
        node = root
        stack = []
        sml = big = predecessor = None
        while node or stack:
            
            while node:
                stack.append(node)
                node = node.left
                
            curNode = stack.pop()
            if predecessor and curNode.val<predecessor.val:
                sml = curNode
                if not big:
                    big = predecessor
                else:
                    break
            
            predecessor = curNode
            node = curNode.right
        sml.val,big.val=big.val, sml.val