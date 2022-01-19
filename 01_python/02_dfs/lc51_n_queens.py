class Solution:
    def solveNQueens(self, n: int) -> List[List[str]]:
        
        """
        The n-queens puzzle is the problem of placing n queens on an n x n chessboard such      that no two queens attack each other.

        Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.

        Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
        
        Input: n = 4
        Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
        
        """
        ans = []
        board = [["."]*n for _ in range(n)]
        startRow = 0
        colSet, diagSet, antiSet = set(), set(), set()
        numSolution = self.dfs(startRow, n, board, ans, colSet, diagSet, antiSet)
        print (numSolution)
        return ans
    
    def dfs(self, row, n, board, ans, colSet, diagSet, antiSet):
        
        if row==n:
            ans.append(["".join(row) for row in board])
            return 1
        
        solutionCnt = 0
        
        for col in range(n):
            diag = row-col
            anti = row+col
            if col not in colSet and diag not in diagSet and anti not in antiSet:
                colSet.add(col)
                diagSet.add(diag)
                antiSet.add(anti)
                board[row][col]="Q"
                solutionCnt += self.dfs(row+1, n, board, ans, colSet, diagSet, antiSet)
                colSet.remove(col)
                diagSet.remove(diag)
                antiSet.remove(anti)
                board[row][col]="."
        
        return solutionCnt