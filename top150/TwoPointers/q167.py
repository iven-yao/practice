from collections import defaultdict


class Solution:
    # T: O(N), S: O(N)
    def twoSum(self, numbers, target):
        num_dict = defaultdict(int)
        n = len(numbers)
        for i in range(n):
            lack = target - numbers[i]
            if lack in num_dict:
                return [num_dict[lack], i+1]
            else:
                num_dict[numbers[i]] = i+1
