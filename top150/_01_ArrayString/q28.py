class Solution:
    def strStr(self, haystack, needle):
        m = len(haystack)
        n = len(needle)
        l = 0
        while l < m-n+1:
            if haystack[l:l+n] == needle:
                return l
            else:
                l += 1
        return -1
