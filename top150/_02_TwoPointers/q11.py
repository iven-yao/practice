import unittest


class Solution:
    # T: O(N), S: O(1)
    def maxArea(self, height):
        result = 0
        n = len(height)
        l = 0
        r = n-1
        while l < r:
            if height[l] < height[r]:
                result = max(result, height[l]*(r-l))
                l += 1
            else:
                result = max(result, height[r]*(r-l))
                r -= 1
        return result


class Test(unittest.TestCase):
    def test_one(self):
        nums = [1, 8, 6, 2, 5, 4, 8, 3, 7]
        sol = Solution()
        result = sol.maxArea(nums)
        self.assertEqual(result, 49)

    def test_two(self):
        nums = [1, 1]
        sol = Solution()
        result = sol.maxArea(nums)
        self.assertEqual(result, 1)


# entry point
if __name__ == '__main__':
    unittest.main()
