import unittest


class Solution:
    def canJump(self, nums):
        n = len(nums)
        result = [True] + [False] * (n-1)
        steps = nums[0]
        i = 0
        while i+1 < n and steps > 0:
            i += 1
            steps -= 1
            result[i] = True
            steps = max(steps, nums[i])
        return result[-1]


class Test(unittest.TestCase):
    def test_large_steps(self):
        nums = [2, 3, 1, 1, 4]
        sol = Solution()
        result = sol.canJump(nums)
        self.assertEqual(result, True)

    def test_small_steps(self):
        nums = [3, 2, 1, 0, 4]
        sol = Solution()
        result = sol.canJump(nums)
        self.assertEqual(result, False)


# entry point
if __name__ == '__main__':
    unittest.main()
