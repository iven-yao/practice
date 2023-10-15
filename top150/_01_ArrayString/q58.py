class Solution:
    def lengthOfLastWord(s):
        l = 0
        n = len(s)
        r = n-1
        count = 0
        while r >= 0:
            if s[r] != ' ':
                count += 1
            elif count != 0:
                return count
            r -= 1
        return count
