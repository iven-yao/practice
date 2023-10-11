class Solution:
    # T: O(N), S: O(1)
    def isSubsequence(self, s, t):
        p1 = 0
        p2 = 0
        n1 = len(s)
        n2 = len(t)
        while p1 < n1 and p2 < n2:
            if s[p1] == t[p2]:
                p1 += 1
                p2 += 1
            else:
                p2 += 1
        return p1 == n1
