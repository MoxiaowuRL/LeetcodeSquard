
Given the root of a binary tree, collect a tree's nodes as if you were doing this:

Collect all the leaf nodes.
Remove all the leaf nodes.
Repeat until the tree is empty.

Input: root = [1,2,3,4,5]
Output: [[4,5,3],[2],[1]]
class Lc366 {

	public List<List<Integer>> findLeaves(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		while (true) {
			List<Integer> temp = new ArrayList();
			int cur = dfs(root, temp);
			if (cur == 0) {
				result.add(temp);
				break;
			}
			result.add(temp);
		}
		return result;
	}

	private int dfs(TreeNode root, List<Integer> temp) {
		if (root == null) {
			return -1;
		}
		int left = dfs(root.left, temp);
		int right = dfs(root.right, temp);
		if (root.left == null && root.right == null) {
			temp.add(root.val);
			return 0;
		}
		if (left == 0) {
			root.left = null;
		}
		if (right == 0) {
			root.right = null;
		}
		return -1;
	}
	// public List<List<Integer>> findLeaves(TreeNode root) {
	// List<List<Integer>> result = new ArrayList<>();
	// helper(root, result);
	// return result;
	// }
	// private int helper(TreeNode root, List<List<Integer>> result){
	// if(root == null){
	// return -1;
	// }
	// int left = helper(root.left, result);
	// int right = helper(root.right, result);
	// int cur = Math.max(left, right) + 1;
	// if(result.size() == cur){
	// result.add(new ArrayList<>());
	// }
	// result.get(cur).add(root.val);
	// return cur;
	// }
}