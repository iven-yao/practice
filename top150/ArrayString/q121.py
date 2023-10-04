import unittest


class Solution:
    def maxProfit(self, prices):
        n = len(prices)
        result = 0
        cur_min = prices[0]
        for p in prices[1:]:
            if p < cur_min:
                cur_min = p
            else:
                result = max(result, p-cur_min)

        return result


class Test(unittest.TestCase):
    def test_one(self):
        prices = [7, 1, 5, 3, 6, 4]
        sol = Solution()
        result = sol.maxProfit(prices)
        self.assertEqual(result, 5)

    def test_two(self):
        prices = [7, 6, 4, 3, 1]
        sol = Solution()
        result = sol.maxProfit(prices)
        self.assertEqual(result, 0)


# entry point
if __name__ == '__main__':
    unittest.main()
