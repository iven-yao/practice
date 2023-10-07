class Solution:
    def longestCommonPrefix(self, strs):
        strs.sort(key=lambda x: (len(x)))
        result = ""
        n = len(strs)
        for i, ch in enumerate(strs[0]):
            for j in range(1, n):
                if ch != strs[j][i]:
                    return result
            result += ch
        return result
