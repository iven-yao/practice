class Solution:
    # T: O(N), S: O(N)
    def reverseWords(self, s):
        res = s.split()
        return ' '.join(res[::-1])
