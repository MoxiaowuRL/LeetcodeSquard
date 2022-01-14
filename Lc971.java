// You are given the root of a binary tree with n nodes, where each node is uniquely assigned a value from 1 to n. You are also given a sequence of n values voyage, 
// which is the desired pre-order traversal of the binary tree.

// Any node in the binary tree can be flipped by swapping its left and right subtrees. For example, flipping node 1 will have the following effect:

// Flip the smallest number of nodes so that the pre-order traversal of the tree matches voyage.

// Return a list of the values of all flipped nodes. You may return the answer in any order. 
// If it is impossible to flip the nodes in the tree to make the pre-order traversal match voyage, return the list [-1].

public class Lc971 {
	int index = 0;
	List<Integer> result = new ArrayList<>();

	public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
		helper(root, voyage);
		if (index != voyage.length) {
			result.clear();
			result.add(-1);
		}

		return result;
	}

	private boolean helper(TreeNode root, int[] voyage) {
		if (root == null) {
			return true;
		}
		if (root.val != voyage[index]) {
			return false;
		}
		index++;
		if (helper(root.left, voyage) && helper(root.right, voyage)) {
			return true;
		}
		if (helper(root.right, voyage) && helper(root.left, voyage)) {
			result.add(root.val);
			return true;
		}
		return false;
	}
}
