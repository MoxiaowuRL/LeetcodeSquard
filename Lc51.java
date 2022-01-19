// The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

// Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.

// Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.

// Input: n = 4
// Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
// Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above

public class Lc51 {
	public List<List<String>> solveNQueens(int n) {
		List<List<String>> result = new ArrayList<>();
		int[] cur = new int[n];
		dfs(0, n, cur, result);
		return result;
	}

	private void dfs(int row, int n, int[] cur, List<List<String>> result) {
		if (row == n) {
			result.add(new ArrayList(arrayToString(cur)));
			return;
		}
		for (int col = 0; col < n; col++) {
			if (valid(col, row, cur)) {
				cur[row] = col;
				dfs(row + 1, n, cur, result);
				// cur[row] = 0;
			}
		}
	}

	private boolean valid(int col, int row, int[] cur) {
		for (int i = 0; i < row; i++) {
			if (col == cur[i] || Math.abs(col - cur[i]) == (row - i)) {
				return false;
			}
		}
		return true;
	}

	private List<String> arrayToString(int[] cur) {
		List<String> tempString = new ArrayList<>();
		for (int i = 0; i < cur.length; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < cur.length; j++) {
				if (j == cur[i]) {
					sb.append("Q");
				} else {
					sb.append(".");
				}
			}
			tempString.add(sb.toString());

		}
		return tempString;
	}
}
