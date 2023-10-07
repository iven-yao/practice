import unittest


class Solution:
    # T: O(N), S: O(N)
    def hIndex(self, citations):
        n = len(citations)
        paper_count = [0]*(n+1)
        for citation in citations:
            paper_count[(min(citation, n))] += 1
        h = n
        num = paper_count[h]
        while h > num:
            h -= 1
            num += paper_count[h]
        return h


class Test(unittest.TestCase):
    def test_one(self):
        citations = [3, 0, 6, 1, 5]
        sol = Solution()
        result = sol.hIndex(citations)
        self.assertEqual(result, 3)

    def test_two(self):
        citations = [1, 3, 1]
        sol = Solution()
        result = sol.hIndex(citations)
        self.assertEqual(result, 1)


# entry point
if __name__ == '__main__':
    unittest.main()
