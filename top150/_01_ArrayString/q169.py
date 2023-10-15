import unittest


class Solution:
    # Boyer-Moore Voting Algorithm
    def majorityElement(self, nums):
        cand = None
        count = 0
        for n in nums:
            if n == cand:
                count += 1
            elif count == 0:
                cand = n
            else:
                count -= 1
        return cand


class Test(unittest.TestCase):
    def test_one(self):
        nums = [3, 2, 3]
        sol = Solution()
        result = sol.majorityElement(nums)
        self.assertEqual(result, 3)

    def test_two(self):
        nums = [2, 2, 1, 1, 1, 2, 2]
        sol = Solution()
        result = sol.majorityElement(nums)
        self.assertEqual(result, 2)


# entry point
if __name__ == '__main__':
    unittest.main()
