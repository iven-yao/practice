import unittest


class Solution:
    def removeDuplicates(self, nums):
        n = len(nums)
        l = 1
        for r in range(1, n):
            if nums[r-1] != nums[r]:
                nums[l] = nums[r]
                l += 1
        return l


class Test(unittest.TestCase):
    def test_one(self):
        nums = [1, 1, 2]
        sol = Solution()
        result = sol.removeDuplicates(nums)
        self.assertEqual(result, 2)

    def test_two(self):
        nums = [0, 0, 1, 1, 1, 2, 2, 3, 3, 4]
        sol = Solution()
        result = sol.removeDuplicates(nums)
        self.assertEqual(result, 5)


# entry point
if __name__ == '__main__':
    unittest.main()
