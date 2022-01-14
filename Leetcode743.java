public class Leetcode743 {
	// You are given a network of n nodes, labeled from 1 to n. You are also given
	// times, a list of travel times as directed edges times[i] = (ui, vi, wi),
	// where ui is the source node, vi is the target node, and wi is the time it
	// takes for a signal to travel from source to target.

	// We will send a signal from a given node k. Return the time it takes for all
	// the n nodes to receive the signal. If it is impossible for all the n nodes to
	// receive the signal, return -1
	class Leetcode743 {
		public int networkDelayTime(int[][] times, int n, int k) {
			List<List<int[]>> graph = new ArrayList<>();
			for (int i = 0; i <= n; i++) {
				graph.add(new ArrayList<>());
			}
			for (int[] edge : times) {
				graph.get(edge[0]).add(new int[] { edge[1], edge[2] });
			}

			PriorityQueue<int[]> minq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
			Map<Integer, Integer> visited = new HashMap<>();
			minq.add(new int[] { k, 0 });
			int total = 0;

			while (!minq.isEmpty()) {
				int[] cur = minq.poll();
				if (visited.containsKey(cur[0]))
					continue;
				visited.put(cur[0], cur[1]);

				for (int[] nei : graph.get(cur[0])) {
					if (!visited.containsKey(nei[0])) {
						minq.add(new int[] { nei[0], cur[1] + nei[1] });
						// visited.put(nei[0], cur[1] + nei[1]);
					}
				}
			}

			if (visited.size() != n)
				return -1;
			for (int i : visited.keySet()) {
				total = Math.max(total, visited.get(i));
			}

			return total;

		}
	}

}
