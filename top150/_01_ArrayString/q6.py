class Solution:
    # T: O(N), S: O(M*N); N: len(s), M: numRows
    def convert(self, s: str, numRows: int) -> str:
        if numRows == 1 or len(s) < numRows:
            return s

        rows = [''] * numRows
        n = len(s)
        r = 0
        order = -1

        for ch in s:
            rows[r] += ch
            if r == 0 or r == numRows-1:
                order *= (-1)
            if order == 1:
                r += 1
            elif order == -1:
                r -= 1
        return ''.join(rows)
