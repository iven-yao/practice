class Solution:
    def isPalindrome(self, s):
        n = len(s)
        l = 0
        r = n-1
        while l < r:
            while l < r and s[l].isalnum() == False:
                l += 1
            while l < r and s[r].isalnum() == False:
                r -= 1
            if s[l].lower() != s[r].lower():
                return False
            l += 1
            r -= 1
        return True
