# print out all topological sort valid path given a list of vertex and their connections. 

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
    return res

print("input: [0,1,2,3], [[0, 1], [1, 2]])")
print("output:", topologicalSort([0,1,2,3], [[0, 1], [1, 2]]))

print("input: [1, 2, 3, 4], [[1, 2],[1, 3],[3, 2],[4, 2],[4, 3]]")
print("output: ", topologicalSort([1, 2, 3, 4], [[1, 2],[1, 3],[3, 2],[4, 2],[4, 3]]))

print("input: [1, 2, 3, 4, 5, 6, 7, 8, 9], [[1, 2], [2, 3], [3, 4],[4, 5],[5, 6],[7, 6],[7, 8],[8, 1]]")
print(topologicalSort([1, 2, 3, 4, 5, 6, 7, 8, 9], [[1, 2], [2, 3], [3, 4],[4, 5],[5, 6],[7, 6],[7, 8],[8, 1]]))
