def topologicalSort(jobs, deps):
    
    def backtrack(res, temp, D, indegree, q):
	    if len(temp) == len(jobs):
			res.append(temp[:])
			return
		if q:
			for v in q:
				temp.append(v)
				next_q = q[:]
				next_q.remove(v)
				for child in D[v]:
					indegree[child] -= 1
					if indegree[child] == 0:
						next_q.append(child)
				backtrack(res, temp, D, indegree, next_q)
				for child in D[v]:
					indegree[child] += 1
				temp.remove(v)
	
    indegree = {i:0 for i in jobs}
    D = {i:[] for i in jobs}
	for a, b in deps:
		D[a].append(b)
		indegree[b] += 1
	q = [i for i in jobs if indegree[i]==0]
	res = []
	backtrack(res, [], D, indegree, q)
	return [] if not res else res[0]