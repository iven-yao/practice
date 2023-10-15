import unittest


class Solution:
    def removeElement(self, nums, val):
        n = len(nums)
        l = 0
        for i in range(n):
            if nums[i] != val:
                nums[l] = nums[i]
                l += 1
        return l


class Test(unittest.TestCase):
    def test_one(self):
        nums = [3, 2, 2, 3]
        val = 3
        sol = Solution()
        result = sol.removeElement(nums, val)
        self.assertEqual(result, 2)

    def test_two(self):
        nums = [0, 1, 2, 2, 3, 0, 4, 2]
        val = 2
        sol = Solution()
        result = sol.removeElement(nums, val)
        self.assertEqual(result, 5)


# entry point
if __name__ == '__main__':
    unittest.main()
