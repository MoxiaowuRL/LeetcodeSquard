// You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.

// You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.

// Return the answers to all queries. If a single answer cannot be determined, return -1.0.

// Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.

// Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
// Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
// Explanation: 
// Given: a / b = 2.0, b / c = 3.0
// queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
// return: [6.0, 0.5, -1.0, 1.0, -1.0 ]

public class Lc399 {
	public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
		HashMap<String, HashMap<String, Double>> map = new HashMap<>();
		int i = 0;
		for (List<String> eq : equations) {
			String a = eq.get(0);
			String b = eq.get(1);
			if (!map.containsKey(a)) {
				map.put(a, new HashMap());
			}
			map.get(a).put(b, values[i]);
			if (!map.containsKey(b)) {
				map.put(b, new HashMap());
			}
			map.get(b).put(a, 1 / values[i]);
			i++;
		}
		i = 0;
		double[] result = new double[queries.size()];
		for (List<String> query : queries) {
			String a = query.get(0);
			String b = query.get(1);
			if (!map.containsKey(a) || !map.containsKey(b)) {
				result[i++] = -1.0;
			} else if (a.equals(b)) {
				result[i++] = 1.0;
			} else {
				HashSet<String> visited = new HashSet<>();
				result[i++] = dfs(map, a, b, 1, visited);
			}
		}
		return result;
	}

	private double dfs(HashMap<String, HashMap<String, Double>> map, String a, String b, double prev,
			Set<String> visited) {
		visited.add(a);
		double result = -1.0;
		Map<String, Double> neighbors = map.get(a);
		if (neighbors.containsKey(b)) {
			result = prev * neighbors.get(b);
		} else {

			for (Map.Entry<String, Double> nei : neighbors.entrySet()) {
				String next = nei.getKey();
				if (visited.contains(next)) {
					continue;
				}

				result = dfs(map, next, b, prev * nei.getValue(), visited);
				if (result != -1) {
					break;
				}

			}
		}
		visited.remove(a);
		return result;

	}
}
