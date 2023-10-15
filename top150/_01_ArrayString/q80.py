import unittest


class Solution:
    def removeDuplicates(self, nums):
        n = len(nums)
        l = 0
        r = 0
        count = 0
        while r < n:
            if r == 0 or nums[r] != nums[r-1]:
                count = 1
            else:
                count += 1
            if count <= 2:
                nums[l] = nums[r]
                l += 1
            r += 1
        return l


class Test(unittest.TestCase):
    def test_one(self):
        nums = [1, 1, 1, 2, 2, 3]
        sol = Solution()
        result = sol.removeDuplicates(nums)
        self.assertEqual(result, 5)

    def test_two(self):
        nums = [0, 0, 1, 1, 1, 1, 2, 3, 3]
        sol = Solution()
        result = sol.removeDuplicates(nums)
        self.assertEqual(result, 7)


# entry point
if __name__ == '__main__':
    unittest.main()
