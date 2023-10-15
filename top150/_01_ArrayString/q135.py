class Solution:
    def candy(self, ratings):
        n = len(ratings)
        if n <= 1:
            return n
        result = [1] * n
        for i in range(1, n):
            if ratings[i] > ratings[i-1]:
                result[i] = result[i-1] + 1
        for j in range(n-2, -1, -1):
            if ratings[j] > ratings[j+1] and result[j] <= result[j+1]:
                result[j] = result[j+1] + 1
        return sum(result)
