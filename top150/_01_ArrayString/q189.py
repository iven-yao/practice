import unittest


class Solution:
    def rotate(self, nums, k):
        """
        Do not return anything, modify nums in-place instead.
        """
        n = len(nums)
        k %= n

        def getReverse(arr, l, r):
            while l < r:
                arr[l], arr[r] = arr[r], arr[l]
                l += 1
                r -= 1

        getReverse(nums, 0, n-1)
        getReverse(nums, 0, k-1)
        getReverse(nums, k, n-1)


class Test(unittest.TestCase):
    def test_one(self):
        nums = [1, 2, 3, 4, 5, 6, 7]
        k = 3
        sol = Solution()
        sol.rotate(nums, k)
        self.assertEqual(nums, [5, 6, 7, 1, 2, 3, 4])

    def test_two(self):
        nums = [-1, -100, 3, 99]
        k = 2
        sol = Solution()
        sol.rotate(nums, k)
        self.assertEqual(nums, [3, 99, -1, -100])


# entry point
if __name__ == '__main__':
    unittest.main()
