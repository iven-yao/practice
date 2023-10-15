import unittest


class Solution:
    def jump(self, nums):
        cur_farthest = 0
        cur_end = 0
        result = 0
        n = len(nums)
        for i in range(n-1):
            cur_farthest = max(cur_farthest, i+nums[i])
            if i == cur_end:
                result += 1
                cur_end = cur_farthest
                if cur_end >= n-1:
                    break
        return result


class Test(unittest.TestCase):
    def test_one(self):
        nums = [2, 3, 1, 1, 4]
        sol = Solution()
        result = sol.jump(nums)
        self.assertEqual(result, 2)

    def test_two(self):
        nums = [2, 3, 0, 1, 4]
        sol = Solution()
        result = sol.jump(nums)
        self.assertEqual(result, 2)


# entry point
if __name__ == '__main__':
    unittest.main()
