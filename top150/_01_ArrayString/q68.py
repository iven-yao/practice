import unittest


class Solution:
    def fullJustify(self, words, maxWidth):
        result = []
        w = 0
        n = len(words)
        cur_len = 0
        cur_list = []
        j = 0
        while j < n:
            if cur_len + len(words[j]) + len(cur_list) > maxWidth:
                gap_num = max(1, len(cur_list)-1)

                for i in range(maxWidth-cur_len):
                    idx = i % gap_num
                    cur_list[idx] += ' '

                result.append("".join(cur_list))

                cur_list = []
                cur_len = 0
                continue
            cur_len += len(words[j])
            cur_list.append(words[j])
            j += 1
        result.append(" ".join(cur_list).ljust(maxWidth))
        return result


class Test(unittest.TestCase):
    def test_one(self):
        words = ["This", "is", "an", "example", "of", "text", "justification."]
        maxWidth = 16
        sol = Solution()
        result = sol.fullJustify(words, maxWidth)

        ans = [
            "This    is    an",
            "example  of text",
            "justification.  "
        ]
        self.assertEqual(result, ans)

    def test_two(self):
        words = ["What", "must", "be", "acknowledgment", "shall", "be"]
        maxWidth = 16
        sol = Solution()
        result = sol.fullJustify(words, maxWidth)

        ans = [
            "What   must   be",
            "acknowledgment  ",
            "shall be        "
        ]
        self.assertEqual(result, ans)

    def test_three(self):
        words = ["Science", "is", "what", "we", "understand", "well", "enough", "to",
                 "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"]
        maxWidth = 20
        sol = Solution()
        result = sol.fullJustify(words, maxWidth)

        ans = [
            "Science  is  what we",
            "understand      well",
            "enough to explain to",
            "a  computer.  Art is",
            "everything  else  we",
            "do                  "
        ]
        self.assertEqual(result, ans)


# entry point
if __name__ == '__main__':
    unittest.main()
