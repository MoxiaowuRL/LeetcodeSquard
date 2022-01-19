# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def closestKValues(self, root: Optional[TreeNode], target: float, k: int) -> List[int]:
        """
        Given the root of a binary search tree, a target value, and an integer k, 
        return the k values in the BST that are closest to the target. 
        You may return the answer in any order.

        You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
        
        Input: root = [4,2,5,1,3], target = 3.714286, k = 2
        Output: [4,3]
        """
        ans = collections.deque()
        
        node = root
        stack = []
        
        while node or stack:
            if node:
                stack.append(node)
                node = node.left
            else:
                curNode = stack.pop()
                if len(ans)<k:
                    ans.append(curNode.val)
                elif target-ans[0]>curNode.val-target:
                    ans.popleft()
                    ans.append(curNode.val)
                else:
                    return list(ans)
                node = curNode.right
        return list(ans)