import unittest


class Solution:
    def merge(self, nums1, m, nums2, n):
        """
        Do not return anything, modify nums1 in-place instead.
        """
        while m > 0 and n > 0:
            if nums1[m-1] < nums2[n-1]:
                nums1[m+n-1] = nums2[n-1]
                n -= 1
            else:
                nums1[m+n-1] = nums1[m-1]
                m -= 1
        if n > 0:
            nums1[:n] = nums2[:n]


class Test(unittest.TestCase):
    def test_one(self):
        nums1 = [1, 2, 3, 0, 0, 0]
        m = 3
        nums2 = [2, 5, 6]
        n = 3
        sol = Solution()
        sol.merge(nums1, m, nums2, n)
        self.assertEqual(nums1, [1, 2, 2, 3, 5, 6])

    def test_two(self):
        nums1 = [1]
        m = 1
        nums2 = []
        n = 0
        sol = Solution()
        sol.merge(nums1, m, nums2, n)
        self.assertEqual(nums1, [1])

    def test_three(self):
        nums1 = [0]
        m = 0
        nums2 = [1]
        n = 1
        sol = Solution()
        sol.merge(nums1, m, nums2, n)
        self.assertEqual(nums1, [1])


# entry point
if __name__ == '__main__':
    unittest.main()
