import unittest


class Solution:
    def maxProfit(self, prices):
        cur_min = prices[0]
        result = 0
        for p in prices[1:]:
            if p < cur_min:
                cur_min = p
            else:
                result += (p - cur_min)
                cur_min = p
        return result


class Test(unittest.TestCase):
    def test_one(self):
        prices = [7, 1, 5, 3, 6, 4]
        sol = Solution()
        result = sol.maxProfit(prices)
        self.assertEqual(result, 7)

    def test_two(self):
        prices = [1, 2, 3, 4, 5]
        sol = Solution()
        result = sol.maxProfit(prices)
        self.assertEqual(result, 4)


# entry point
if __name__ == '__main__':
    unittest.main()
