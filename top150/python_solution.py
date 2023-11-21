import collections
from collections import defaultdict
import bisect
from collections import deque
import math
from math import factorial
from itertools import product
import heapq
import random

# =============== Array ===============
class Solution238:
    # T: O(N)
    # S: O(N)
    def productExceptSelf(self, nums):
        n = len(nums)
        left = [1] * n
        right = [1] * n
        result = [1] * n
        for i in range(1, n):
            left[i] = left[i-1] * nums[i-1]
        for j in range(n-2, -1, -1):
            right[j] = right[j+1] * nums[j+1]
        for k in range(n):
            result[k] = left[k] * right[k]
        return result
    
    # T: O(N)
    # S: O(1)
    def productExceptSelf(self, nums):
        n = len(nums)
        result = [1] * n
        R = 1
        for i in range(1, n): # as previous method, but store it in result array
            result[i] = result[i-1] * nums[i-1]
        for i in range(n-1, -1, -1):
            result[i] = result[i] * R
            R *= nums[i]
        return result

# =============== String ===============
# 14. Longest Common Prefix
class Solution14:
    def longestCommonPrefix(self, strs):
        strs.sort(key=lambda x: (len(x)))
        # ["flower","flow","flight"] -> ['flow', 'flower', 'flight']
        result = ""
        n = len(strs)
        for i, ch in enumerate(strs[0]):
            for j in range(1, n):
                if ch != strs[j][i]:
                    return result
            result += ch
        return result
    
    def longestCommonPrefix(self, strs):
        n = len(strs)
        strs.sort() # in alphabetical order
        # ["flower","flow","flight"] -> ['flight', 'flow', 'flower']
        smallest_word = strs[0]
        for i in range(len(smallest_word)): # will NOT have index out of range case
            # if len(smallest_word) <= len(strs[-1]): works fine in the for loop, smallest_word might be prefix of strs[-1], or smaller word
            # if len(smallest_word) > len(strs[-1]): for loop will MUST stop at the different character (within the length of strs[-1]) between these 2 words
            #                                        Not possible that len(smallest_word) > len(strs[-1]) and len(smallest_word) is the prefix of len(strs[-1]), bc not meet the sort case!
            #                                        Ex: ["flower","flz"] -> ["flower","flz"] => stop at i=2
            if smallest_word[i] != strs[-1][i]:
                return smallest_word[:i]
        return smallest_word



# =============== Sliding Window ===============

# 209. Miniize Size Subarray Sum
class Solution209:
    def minSubArrayLen(self, target, nums):
        l = 0
        r = 0
        n = len(nums)
        result = float('inf')
        window_sum = 0
        while r < n:
            window_sum += nums[r]
            r += 1
            while window_sum >= target: # a subarray whose sum is greater than or equal to target
                result = min(result, r-l)
                window_sum -= nums[l]
                l += 1
            
        return result if result != float('inf') else 0

    
# 3. Longest Substring Without Repeating Characters
class Solution3:
    def lengthOfLongestSubstring(self, s):
        if len(s) <= 1:
            return len(s)
        l = 0
        r = 0
        seen = defaultdict(int)
        result = 0
        for r in range(len(s)):
            if s[r] not in seen: 
                result = max(r - l + 1, result)
            else:
                if seen[s[r]] < l:
                    result = max(r - l + 1, result)  
                else:
                    l = seen[s[r]] + 1
                    
            seen[s[r]] = r # update s[r] in the dictionary
        return result
    
# 30. Substring with Concatenation of All Words
class Solution30:
    def findSubstring(self, s, words):
        word_len = len(words[0])
        ori_word_dict = defaultdict(int)
		
        for word in words:
            ori_word_dict[word] += 1
        
        all_word_len = len(words) * word_len
        result = []
        for i in range(word_len):
            queue = deque()
            word_dict = ori_word_dict.copy()
            for j in range(i, len(s) - word_len + 1, word_len):
                word = s[j:j + word_len]
                if word_dict.get(word, 0) != 0:
                    word_dict[word] -= 1
                    queue.append(word)
                    if sum(word_dict.values()) == 0:
                        result.append(j - all_word_len + word_len)
                        last_element = queue.popleft()
                        word_dict[last_element] = word_dict.get(last_element, 0) + 1
                else:
                    while len(queue):
                        last_element = queue.popleft()
                        if last_element == word:
                            queue.append(word)
                            break
                        else:
                            word_dict[last_element] = word_dict.get(last_element, 0) + 1
                            if word_dict[last_element] > ori_word_dict[last_element]:
                                word_dict = ori_word_dict.copy()

        return result
    
# 76. Minimum Window Substring
class Solution76:
    def minWindow(self, s, t):
        need, missing = collections.Counter(t), len(t)
        i = I = J = 0
        for j, c in enumerate(s, 1):
            missing -= need[c] > 0
            need[c] -= 1
            if not missing:
                while i < j and need[s[i]] < 0:
                    need[s[i]] += 1
                    i += 1
                if not J or j - i <= J - I:
                    I, J = i, j
        return s[I:J]


        
# =============== Matrix ===============
# 36. Valid Sudoku
class Solution:
    def isValidSudoku(self, board):
        n = len(board)
        rows = [set() for _ in range(n)]
        cols = [set() for _ in range(n)]
        boxes = [[set() for _ in range(3)] for _ in range(3)]
        for i in range(n):
            for j in range(len(board)):
                if board[i][j] != '.':
                    if board[i][j] in rows[i]:
                        return False
                    else:
                        rows[i].add(board[i][j])
                    if board[i][j] in cols[j]:
                        return False
                    else:
                        cols[j].add(board[i][j])
                    
                    if board[i][j] in boxes[i//3][j//3]:
                        return False
                    else:
                        boxes[i//3][j//3].add(board[i][j])

        return True
        
# 54. Spiral Matrix
class Solution54:
    def spiralOrder(self, matrix):
        m = len(matrix)
        n = len(matrix[0])
        left = 0
        right = n-1
        top = 0
        down = m-1
        result = []
        i = 0
        j = 0
        while len(result) < m*n:
            # right
            for col in range(left, right+1):
                result.append(matrix[top][col])
            top += 1
            # down
            for row in range(top, down+1):
                result.append(matrix[row][right])
            right -= 1
            # check if we should stop if the edge is invalid
            if left > right or top > down:
                break
            # left
            for col in range(right, left-1, -1):
                result.append(matrix[down][col])
            down -= 1
            # up
            for row in range(down, top-1, -1):
                result.append(matrix[row][left])
            left += 1
        return result
    
# 48. Rotate Image
class Solution:
    # T: O(M*N)
    # S: O(1)
    def rotate(self, matrix):
        """
        Do not return anything, modify matrix in-place instead.
        """
        n = len(matrix[0])
        # transpose
        for i in range(n):
            for j in range(i+1, n):  
                matrix[i][j] , matrix[j][i] = matrix[j][i], matrix[i][j]
        # mirror horizontally
        for i in range(n):
            matrix[i].reverse()
        
# 73. Set Matrix Zeroes
class Solution:
    def setZeroes(self, matrix):
        """
        Do not return anything, modify matrix in-place instead.
        """
        m = len(matrix)
        n = len(matrix[0])
        first_col = False
        first_row = False
        for i in range(m):
            if matrix[i][0] == 0:
                first_col = True
                break
        for j in range(n):
            if matrix[0][j] == 0:
                first_row = True
                break

        for i in range(1, m):
            for j in range(1, n):
                if matrix[i][j] == 0:
                    matrix[i][0] = 0
                    matrix[0][j] = 0
		
        # based on first row and first col val to update the value in matrix[1:m][1:n]
        for i in range(1, m):
            for j in range(1, n):
                if matrix[i][0] == 0 or matrix[0][j] == 0:
                    matrix[i][j] = 0
        
        if first_col:
            for i in range(m):
                matrix[i][0] = 0
        
        if first_row:
            for j in range(n):
                matrix[0][j] = 0

# 289. Game of Life
class Solution289:
    def gameOfLife(self, board):
        """
        Do not return anything, modify board in-place instead.
        """
        neighbors = [(1,0), (1,-1), (0,-1), (-1,-1), (-1,0), (-1,1), (0,1), (1,1)]

        rows = len(board)
        cols = len(board[0])

        for row in range(rows):
            for col in range(cols):
                liveNeighbors = 0
                for neighbor in neighbors:
                    r = (row + neighbor[0])
                    c = (col + neighbor[1])

                    if (r < rows and r >= 0) and (c < cols and c >= 0) and abs(board[r][c]) == 1:
                        liveNeighbors += 1

                if board[row][col] == 1 and (liveNeighbors < 2 or liveNeighbors > 3):
                    board[row][col] = -1
                if board[row][col] == 0 and liveNeighbors == 3:
                    board[row][col] = 2

        for row in range(rows):
            for col in range(cols):
                if board[row][col] > 0:
                    board[row][col] = 1
                else:
                    board[row][col] = 0

# 73. Set Matrix Zero
class Solution73:
    def setZeroes(self, matrix):
        """
        Do not return anything, modify matrix in-place instead.
        """
        first_row = -1
        first_col = -1
        m = len(matrix)
        n = len(matrix[0])
        for i in range(m):
            if matrix[i][0] == 0:
                first_col = 0
        for j in range(n):
            if matrix[0][j] == 0:
                first_row = 0
        for i in range(1, m):
            for j in range(1, n):
                if matrix[i][j] == 0:
                    matrix[i][0] = 0
                    matrix[0][j] = 0
        for i in range(1, m):
            for j in range(1, n):
                if matrix[0][j] == 0 or matrix[i][0] == 0:
                    matrix[i][j] = 0

        if first_row == 0:
            for j in range(n):
                matrix[0][j] = 0
        if first_col == 0:
            for i in range(m):
                matrix[i][0] = 0
        


# =============== Hash map ===============
# 383. Ransom Note
class Solution383:
    # 1. one hash map
    # T: O(max(N, M)), N: length of ransomNote, M: length of magazine
    # S: O(M)
    def canConstruct(self, ransomNote, magazine):
        m_dict = collections.Counter(magazine) # O(M)
        for ch in ransomNote: # O(N)
            '''
            if ch not in m_dict.keys():
               return False
            else:
                m_dict[ch] -= 1
                if m_dict[ch] < 0:
                    return False
            '''
            if m_dict[ch] > 0:
                m_dict[ch] -= 1
            else:
                return False
        return True
    
    # 2. .find() and .replace()
    # T: O(M*N) or O(max(M,N)) ?
    # S: O(1)
    def canConstruct(self, ransomNote, magazine):
        for ch in ransomNote: # O(N)
            if magazine.find(ch) != -1: # O(M)
                magazine = magazine.replace(ch, '', 1) # O(M)
            else:
                return False
        return True
    
class Solution205:
    # 1. one hashmap
    # T: O(N), N: length of s and t
    # S: O(N)
    def isIsomorphic(self, s, t):
        if len(set(s)) != len(set(t)):
            return False
        d = collections.defaultdict(str)
        for i, ch in enumerate(s):
            if ch in d.keys():
                if d[ch] != t[i]:
                    return False
            else:
                d[ch] = t[i]
        return True

    # 2. two hashmaps to check from s -> t, t -> s
    # T: O(N), N: length of s and t
    # S: O(N)
    def isIsomorphic(self, s, t):
        d_s_t = defaultdict(str)
        d_t_s = defaultdict(str)
        for i in range(len(s)):
            if s[i] in d_s_t.keys():
                if d_s_t[s[i]] != t[i]:
                    return False
            else:
                d_s_t[s[i]] = t[i]
            if t[i] in d_t_s.keys():
                if d_t_s[t[i]] != s[i]:
                    return False
            else:
                d_t_s[t[i]] = s[i]
        return True        
        
class Solution290():
    # 1. one hashmap
    # T: O(max(M,N)), M: length of pattern; N: length of s
    # S: O(max(M,N)), M: length of pattern; N: length of s
    def wordPattern(self, pattern, s):
        s_arr = s.split()
        if len(pattern) != len(s_arr) or len(set(pattern)) != len(set(s_arr)):
            return False
        d_p_s = defaultdict(str)
        for i in range(len(pattern)):
            if pattern[i] in d_p_s.keys():
                if d_p_s[pattern[i]] != s_arr[i]:
                    return False
            else:
                d_p_s[pattern[i]] = s_arr[i]
        return True
    
    # 2. two hashmaps
    # T: O(max(M,N)), M: length of pattern; N: length of s
    # S: O(max(M,N)), M: length of pattern; N: length of s
    def wordPattern(self, pattern, s):
        s_arr = s.split()
        if len(pattern) != len(s_arr):
            return False
        d_p_s = defaultdict(str)
        d_s_p = defaultdict(str)
        '''
        for i in range(len(pattern)):
            if pattern[i] in d_p_s.keys():
                if d_p_s[pattern[i]] != s_arr[i]:
                    return False
            else:
                d_p_s[pattern[i]] = s_arr[i]
            if s_arr[i] in d_s_p.keys():
                if d_s_p[s_arr[i]] != pattern[i]:
                    return False
            else:
                d_s_p[s_arr[i]] = pattern[i]
        '''
        for a, b in zip(pattern, s_arr):
            if a not in d_p_s.keys():
                if b in d_s_p.keys():
                    return False
                else:
                    d_p_s[a] = b
                    d_s_p[b] = a
            else:
                if d_p_s[a] != b:
                    return False
        return True
    
class Solution242:
    # T: O(max(M,N)), M: length of s; N: length of t
    # S: O(N)
    def isAnagram(self, s, t):
        counter_t = collections.Counter(t)
        for ch in s:
            if ch not in counter_t.keys() or counter_t[ch] <= 0:
                return False
            else:
                counter_t[ch] -= 1
                if counter_t[ch] == 0:
                    del counter_t[ch]
        
        return len(counter_t) == 0
        '''
        return Counter(s) == Counter(t)
        '''

class Solution49:
    def groupAnagrams(self, strs):
        d = defaultdict(list)
        for s in strs:
            sorted_s = sorted(s)
            d[tuple(sorted_s)].append(s)
        result = []
        for val in d.values():
            result.append(val)
        return result
    
    # Rabin-Karp Algorithm OAO?????


class Solution48:
    def rotate(self, matrix):
        """
        Do not return anything, modify matrix in-place instead.
        """
        n = len(matrix[0])
        for i in range(n):
            for j in range(i+1, n):  
                matrix[i][j] , matrix[j][i] = matrix[j][i], matrix[i][j]
        for i in range(n):
            matrix[i].reverse()

class Solution202:
    # T: O(N) ~ O(logN)
    # S: O(N) ~ O(logN)
    def isHappy(self, n):
        def get_next(n):
            total = 0
            while n != 0:
                digits = n%10
                n = n // 10
                total += digits * digits
            return total

        seen = set()
        while n != 1 and n not in seen:
            seen.add(n)
            n = get_next(n)

        return n == 1

    # Floyd’s Cycle Finding Algorithm
    # T: O(N) or O(logN)
    # S: O(1)

class Solution219:
    def containsNearbyDuplicate(self, nums, k):
        d = defaultdict(int)
        for i, n in enumerate(nums):
            if n in d.keys():
                if i - d[n] <= k:
                    return True
            d[n] = i # if n in dict, but the distance is over k OR n not in dict case -> update new position of n in dict
            
        return False  

# 128
class Solution128:
    def longestConsecutive(self, nums):
        nums = set(nums)   
        maxLength = 0  
        while nums:
            first = last = nums.pop()
            while first - 1 in nums:
                first -= 1
                nums.remove(first)  
            while last + 1 in nums:
                last += 1   
                nums.remove(last)  
            maxLength = max(maxLength, last - first + 1)  
            
        return maxLength

# 228. Summary Ranges
class Solution228:
    # while loop
    # T: O(N)
    # S: O(1)
    def summaryRanges(self, nums):
        n = len(nums)
        result = []
        if n == 0:
            return result
        l = 0
        r = 0
        # l: consecutive start; r: consecutive end
        while r < n:
            while r+1 < n and nums[r]+1 == nums[r+1]:
                r += 1
            if l != r:
                cur_res = str(nums[l]) + '->' + str(nums[r])
                result.append(cur_res)
            else:
                result.append(str(nums[l]))
            
            r += 1
            l = r
        return result

    # for loop
    # T: O(N)
    # S: O(1)
    def summaryRanges(self, nums):
        n = len(nums)
        result = []
        if n == 0:
            return result
        minIdx = 0
        maxIdx = 0

        for r in range(1, n):
            if nums[r] - nums[r-1] == 1:
                maxIdx = r
            else:
                if minIdx == maxIdx:
                    result.append("{}".format(nums[minIdx]))
                else:
                    result.append("{}->{}".format(nums[minIdx], nums[maxIdx]))
                minIdx = r
                maxIdx = r
        # add the last minIndex and maxIndex
        if minIdx == maxIdx:
            result.append("{}".format(nums[minIdx]))
        else:
            result.append("{}->{}".format(nums[minIdx], nums[maxIdx]))
        return result


class Solution56():
    # T: O(NlogN)
    # S: O(N), bc sort
    def merge(self, intervals):
        result = []
        intervals.sort(key=lambda x:x[0])
        for s, e in intervals:
            if not result or s > result[-1][1]:
                result.append([s, e])
            else:
                result[-1][1] = max(result[-1][1], e)
        return result

# 57. Insert Interval    
class Solution57():
    # T: O(NlogN)
    # S: O(N), bc sort
    def insert(self, intervals, newInterval):
        intervals.append(newInterval)
        intervals.sort(key=lambda x:x[0])
        result = []
        for s, e in intervals:
            if not result or s > result[-1][1]:
                result.append([s, e])
            else:
                result[-1][1] = max(result[-1][1], e)
        return result
    
    # T: O(N), use BS bc original intervals are sorted!
    # S: O(N)
    def insert(self, intervals, newInterval):
        # .bisect_left()
        starts = [s[0] for s in intervals]
        idx = bisect.bisect_left(starts, newInterval[0])
        # .insert()
        intervals.insert(idx, newInterval)
        result = []
        for s, e in intervals:
            if not result or s > result[-1][1]:
                result.append([s, e])
            else:
                result[-1][1] = max(result[-1][1], e)
        return result

# 452. Minimum Number of Arrows to Burst Balloons
class Solution452:
    # T: O(NlogN)
    # S: O(N)
    def findMinArrowShots(self, points):
        count = 1
        n = len(points)
        points.sort(key = lambda x:x[1])
        cur_e = points[0][1]
        for s, e in points:
            if s <= cur_e:
                continue
            else:
                count += 1
                cur_e = e
        return count

class Solution2390:
    def removeStars(self, s):
        stack = []
        for ch in s:
            if ch != '*':
                stack.append(ch)
            else:
                if stack:
                    stack.pop()
        return ''.join(stack)


class Solution20():
    def isValid(self, s):
        d = {'(': ')', '[': ']', '{': '}'}
        stack = []
        for ch in s:
            if ch in d.keys():
                stack.append(d[ch])
            else:
                if not stack or ch != stack[-1]:
                    return False
                else:
                    stack.pop()
        return len(stack) == 0

# 71. Simplify Path
class Solution71():
    # T: O(N)
    # S: O(N)
    def simplifyPath(self, path):
        path_arr = path.split('/')
        stack = []
        for ch in path_arr:
            if ch == '..':
                if stack:
                    stack.pop()
            elif ch == '' or ch == '.':
                continue
            else:
                stack.append(ch)
        return '/' + '/'.join(stack)

# 155. Min Stack
class MinStack:
    
    def __init__(self):
        self.s = []

    # T: O(1)
    def push(self, val):
        if self.s:
            min_val = min(self.s[-1][1], val)
        else:
            min_val = val
        self.s.append((val, min_val))

    # T: O(1)
    def pop(self):
        if self.s:
            self.s.pop()

    # T: O(1)
    def top(self):
        if self.s: 
            return self.s[-1][0]

    # T: O(1)
    def getMin(self):
        if self.s:
            return self.s[-1][1]

# 150. Evaluate Reverse Polish Notation
class Solution150:
    def evalRPN(self, tokens):
        operations = ['+', '-', '*', '/']
        stack = []
        for ch in tokens:
            if ch not in operations:
                stack.append(int(ch))
            else:
                second = stack.pop()
                first = stack.pop()
                if ch == '+':
                    stack.append(first + second)
                if ch == '-':
                    stack.append(first - second)
                if ch == '*':
                    stack.append(first * second)
                if ch == '/':
                    stack.append(int(first / second)) # truncate toward zero via change float into integer, works for positive and negative cases!
        return stack.pop()

# 224. Basic Calculator
class Solution:
    def calculate(self, s):
        num, sign, stack = 0, 1, [0]

        for c in s:
            if c.isdigit():
                num = num*10 + int(c)
            elif c==' ':
                continue

            elif c == '+':
                stack[-1] += num * sign
                sign = 1
                num = 0
            elif c == '-':
                stack[-1] += num * sign
                sign = -1
                num = 0
            elif c == '(':
                stack.extend([sign,0])
                sign = 1
                num = 0
            elif c == ')':
                lastNum = (stack.pop() + num*sign) * stack.pop()
                stack[-1] += lastNum
                sign = 1
                num = 0
                
        return stack[-1]+num*sign


# ========== Greedy ==========
class Solution649:
    # T: O(N^3)
    # S: O(1)
    def predictPartyVictory(self, senate):
        i = 0
        while i < len(senate):
            if senate[i] == 'R':
                # .find(), T: O(N)
                idx = senate.find('D')
                if idx == -1:
                    return 'Radiant'
                # .replace(), only replace the first word we want to find
                # O(N) for search, O(N) for replace -> O(N^2)
                senate = senate.replace('R', '', 1)
                senate = senate.replace('D', '', 1)
                senate += 'R'
            elif senate[i] == 'D':
                idx = senate.find('R')
                if idx == -1:
                    return 'Dire'
                senate = senate.replace('D', '', 1)
                senate = senate.replace('R', '', 1)
                senate += 'D'
    # Use 2 queues
    # T: O(N)
    # S: O(N)
    def predictPartyVictory(self, senate):
        r_queue = deque()
        d_queue = deque()
        # In each queue, store the index of the character
        for i, ch in enumerate(senate):
            if ch == 'R':
                r_queue.append(i)
            elif ch == 'D':
                d_queue.append(i)
        idx = len(senate)
        while r_queue and d_queue:
            r_idx = r_queue.popleft()
            d_idx = d_queue.popleft()
            if r_idx < d_idx:
                r_queue.append(idx)
            else:
                d_queue.append(idx)
            idx += 1
        return 'Radiant' if len(r_queue) > 0 else 'Dire'

# 334. Increasing Triplet Subsequence
class Solution334:
    # T: O(N)
    # S: O(1)
    def increasingTriplet(self, nums):
        n = len(nums)
        first = float('inf')
        second = float('inf')
        for i in range(n):
            if nums[i] < first:
                first = nums[i]
            elif nums[i] < second:
                second = nums[i]
            else:
                return True
        return False

# =============== Linked List ===============
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

# 21. Merge Two Sorted Lists
class Solution21:
    def mergeTwoLists(self, list1, list2):
        p1 = list1
        p2 = list2
        p = dummy = ListNode(0)
        while p1 != None and p2 != None:
            if p1.val <= p2.val:
                p.next = p1
                p1 = p1.next
                p = p.next
            else:
                p.next = p2
                p2 = p2.next
                p = p.next
        if p1 != None:
            p.next = p1
        if p2 != None:
            p.next = p2
        return dummy.next

class Solution141:
    # T: O(N)
    # S: O(1)
    def hasCycle(self, head):
        slow = head
        fast = head
        while fast != None and fast.next != None: # bc need to avoid None.next error !
        # while fast and fast.next:
            slow = slow.next
            fast = fast.next.next
            if slow == fast:
                return True
        return False

class Solution2:
    def addTwoNumbers(self, l1, l2):
        dummy = ListNode(0)
        p = dummy
        carry = 0
        while l1 or l2 or carry:
            if l1:
                carry += l1.val
                l1 = l1.next
            if l2:
                carry += l2.val
                l2 = l2.next
            p.next = ListNode(carry%10)
            p = p.next
            carry //= 10
        return dummy.next

class Solution206:
    def reverseList(self, head):
        prev = None
        cur = head
        while cur:
            nxt = cur.next # store the next element befoe re-assign .next to prevent lose original next element
            cur.next = prev
            prev = cur
            cur = nxt
        return prev
    
    def reverseList(self, head):
        cur = head
        prev = None
        while cur != None:
            cur.next, prev, cur = prev, cur, cur.next
            # prev, cur, cur.next = cur, cur.next, prev # 不能寫成這樣 -> 會有 None.next 的錯誤 => cur.next 要寫在前面
        return prev

# 83. Remove Duplicates from Sorted List 
class Solution:
    def deleteDuplicates(self, head):
        cur = head
        while cur and cur.next:
            if cur.val == cur.next.val:
                cur.next = cur.next.next
            else:
                cur = cur.next
        return head

# 82. Remove Duplicates from Sorted List II
class Solution82:
    # T: O(N)
    # S: O(1)
    def deleteDuplicates(self, head):
        dummy = ListNode(0, head)
        pre = dummy
        cur = head
        while cur:
            if cur.next and cur.val == cur.next.val:
                while cur.next and cur.val == cur.next.val:
                    cur = cur.next
                pre.next = cur.next # skip all duplicates
                # although current cur pointer might point to a new duplicate num
                # in the next while loop, we will still go into if statement, and reconnect it to the right next num
            else:
                pre = pre.next
            cur = cur.next # move forward
        return dummy.next

# 1836. Remove Duplicates From an Unsorted Linked List
class Solution1836:
    # if multiple duplicates connect together -> skip at one time
    # T: O(N)
    # S: O(N), dict
    def deleteDuplicatesUnsorted(self, head):
        d = defaultdict(int)
        cur = head
        while cur:
            d[cur.val] += 1
            cur = cur.next

        dummy = ListNode(0, head)
        cur = head
        pre = dummy
        while cur:
            if d[cur.val] > 1:
                while cur and d[cur.val] > 1:
                    cur = cur.next
                pre.next = cur
            else:
                pre = pre.next
                cur = cur.next
        return dummy.next

    # iterate one by one
    # T: O(N)
    # S: O(N), dict
    def deleteDuplicatesUnsorted(self, head):
        d = defaultdict(int)
        cur = head
        while cur:
            d[cur.val] += 1
            cur = cur.next

        dummy = ListNode(0, head)
        cur = head
        pre = dummy
        while cur:
            if d[cur.val] > 1:
                pre.next = cur.next
            else:
                pre = pre.next
            cur = cur.next
        return dummy.next

class Solution:
    def reverseLinkedList(self, head, k):
        new_head = None
        cur = head
        while k:
            next_node = cur.next
            cur.next = new_head
            new_head = cur
            cur = next_node
            k -= 1
        return new_head

    def reverseKGroup(self, head, k):
        count = 0
        cur = head
        while count < k and cur:
            cur = cur.next
            count += 1
        if count == k:
            reversedHead = self.reverseLinkedList(head, k)
            head.next = self.reverseKGroup(cur, k)
            return reversedHead
        return head
    
# 19. Remove Nth Node From End of List
class Solution19:
    # calculate total length of linked list
    def removeNthFromEnd(self, head, n):
        dummy = ListNode(0, head)
        cur = head # set cur point to head for counting node number
        count = 0
        while cur:
            count += 1
            cur = cur.next
        cur = dummy # for the case the removal node is the 1st node, we should set cur point to dummy node, but not head node
        for i in range(count-n):
            cur = cur.next

		# after for loop, cur point to the parent of the removal node
		# In this case, even though the removal node is the last node, there will not None.next error case
        cur.next = cur.next.next 
        return dummy.next
    
    # math, don't need to calculate total length of linked list
    def removeNthFromEnd(self, head, n):
        dummy = ListNode(0, head)
        slow = head
        fast = head
        for _ in range(n):
            fast = fast.next
        if not fast: # 1st node removal case
            return slow.next
        while fast.next:
            slow = slow.next
            fast = fast.next
		# after while loop, slow will point to the parent of the removal node
        # slow and fast point keep diff by n
        slow.next = slow.next.next
        return dummy.next


# 92. Reverse Linked List II
class Solution92:
    # while solution
    def reverseBetween(self, head, left, right):
        # dummy = ListNode(0)
        # dummy.next = head
        dummy = ListNode(0, head)
        # 1st part
        left_prev = dummy
        cur = head
        idx = 1
        while idx != left:
            left_prev = left_prev.next
            cur = cur.next
            idx += 1
        
        # 2nd part
        prev = None
        times = right - left + 1 # ex: 2->3->4, process 2->None, 3->2, 4->3, => 3 times
        right_prev = cur
        # another method: right_prev = left_p.next
        while times > 0:
            nxt = cur.next 
            cur.next = prev
            prev = cur
            cur = nxt
            times -= 1

        left_prev.next = prev # 1st connect to 2rd
        # 2nd connect to 3rd
        right_prev.next = cur
        
        return dummy.next
    
    # For solution
    def reverseBetween(self, head, left, right):
        # dummy = ListNode(0)
        # dummy.next = head
        dummy = ListNode(0, head)
        # 1st part
        left_prev = dummy
        cur = head
        idx = 1
        for i in range(left-1):
            left_prev = left_prev.next
            cur = cur.next

        # 2nd part
        prev = None
        right_prev = cur
        # right_prev = left_p.next
        for i in range(right - left + 1):
            nxt = cur.next
            cur.next = prev
            prev = cur
            cur = nxt

        left_prev.next = prev # 1st connect to 2rd
        # 2nd connect to 3rd
        right_prev.next = cur
        
        return dummy.next
            

# 61. Rotate List
class Solution61:
    # T: O(N)
    # S: O(1)
    def rotateRight(self, head, k):
        if not head:
            return head
				
        dummy = ListNode(0, head)
        end_node = head
        count = 1
        while end_node.next:
            count += 1
            end_node = end_node.next
        end_node.next = head
        k %= count
        new_end_node = dummy
        new_start_node = head
        for i in range(count-k):
            new_end_node = new_end_node.next
            new_start_node = new_start_node.next
        new_end_node.next = None
        return new_start_node
            

class Solution86:
    # T: O(N)
    # S: O(1)
    def partition(self, head, x):
        if not head:
            return head

        first_list_dummy = ListNode(0, head)
        first_list_p = first_list_dummy
        cur = head
        second_list_dummy = ListNode(0, None)
        second_list_p = second_list_dummy

        while cur:
            if cur.val >= x:
                second_list_p.next = cur
                second_list_p = second_list_p.next
            else:
                first_list_p.next = cur
                first_list_p = first_list_p.next
            cur = cur.next
            
        second_list_p.next = None
        first_list_p.next = second_list_dummy.next
        return first_list_dummy.next


# 146. LRU Cache
# method 1: collections.OrderedDict()
from collections import OrderedDict

class LRUCache:

    def __init__(self, capacity: int):
        # a dict that remembers the order in that keys were first inserted.
        self.d = collections.OrderedDict() # cannot set default type like collections.defaultdict(int)
        self.c = capacity

    # T: O(1)
    def get(self, key: int) -> int:
        if key in self.d.keys():
            self.d.move_to_end(key)
            return self.d[key]
        else:
            return -1

    # T: O(1)
    def put(self, key: int, value: int) -> None:
        if key in self.d.keys():
            self.d.move_to_end(key)
        self.d[key] = value
        if len(self.d) > self.c:
            self.d.popitem(last=False)

# method 2: doubly linked list (record the order) + hashmap (find val of the key in O(1))
class Node:
    def __init__(self, k, v):
        self.key = k
        self.val = v
        self.prev = None
        self.next = None

class LRUCache:

    def __init__(self, capacity: int):
        self.c = capacity
        self.d = collections.defaultdict()
        self.head = Node(0, None)
        self.tail = Node(0, None)
        self.head.next = self.tail
        self.tail.prev = self.head

    # remove node from the linked list
    def _remove(self, node):
        prev_node = node.prev
        next_node = node.next

        # prev_node and next_node
        prev_node.next = next_node
        next_node.prev = prev_node

    # add node in the last of the linked list
    def _insert(self, node):
        prev_node = self.tail.prev

        # prev_node and insert node
        prev_node.next = node
        node.prev = prev_node
        # insert node and tail
        node.next = self.tail
        self.tail.prev = node

    # T: O(1)
    def get(self, key: int) -> int:
        if key in self.d:
            node = self.d[key]
            self._remove(node)
            self._insert(node)
            return node.val
        else:
            return -1

    # T: O(1)
    def put(self, key: int, value: int) -> None:
        if key in self.d:
            self._remove(self.d[key])
        self.d[key] = Node(key, value)
        self._insert(self.d[key])

        if len(self.d) > self.c:
            lru_node = self.head.next
            self._remove(lru_node)
            del self.d[lru_node.key]


# 138. Copy List with Random Pointer
class Node:
    def __init__(self, x: int, next: 'Node' = None, random: 'Node' = None):
        self.val = int(x)
        self.next = next
        self.random = random

class Solution:
    def copyRandomList(self, head):
        dummy = new = Node(0)
        old_to_new = {}
        cur = head
        while cur:
            new.next = Node(cur.val)
            old_to_new[cur] = new.next
            cur = cur.next
            new = new.next
        cur = head
        new = dummy.next
        while cur:
            if cur.random:
                new.random = old_to_new[cur.random]
            cur = cur.next
            new = new.next
        return dummy.next


        

# =============== Binary Tree ===============

# Definition for a binary tree node.
class TreeNode: #  This class defines a simple binary tree node structure with three attributes:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

# 104. Maximum Depth of Binary Tree
class Solution104:
    # Recursion
    # T: O(N)
    # S: O(N), if the tree is unbalanced
    def maxDepth(self, root):
        if not root:
            return 0
        return 1 + max(self.maxDepth(root.left), self.maxDepth(root.right))
    
    # Iteration BFS
    # T: O(N)
    # S: O(D), D: number of nodes in one layer, maximum: store 2D number of nodes in queue
    def maxDepth(self, root):
        if not root:
            return 0
        level = 1
        q = deque([(root, level)])
        while q:
            n = len(q) 
            for _ in range(n):
                node, level = q.popleft()
                if node.left:
                    q.append((node.left, level+1))
                if node.right:
                    q.append((node.right, level+1))
        return level



# 100. Same Tree
class Solution100:
    # Recursion
    # T: O(N)
    # S: O(N), if the tree is unbalanced
    def isSameTree(self, p, q):
        if not p and not q:
            return True
        if not p or not q:
            return False
        if p.val != q.val:
            return False
        return self.isSameTree(p.left, q.left) and self.isSameTree(p.right, q.right)

    # Iteration BFS
    # T: O(N)
    # S: O(D), D: number of nodes in one layer, maximum: store 2D number of nodes in queue
    def isSameTree(self, p, q):
        deq = deque([(p, q)])
        while deq:
            p_node, q_node = deq.popleft()
            if not p_node and not q_node:
                continue
            if not p_node or not q_node:
                return False
            # if execute to here, it means p_node!=None and q_node!=None
            # Thus, below code can use these 2 nodes to refer .left or .right; don't need to pre-check to prevent None.left or None.right error
            if p_node.val != q_node.val:
                return False
            deq.append([p_node.left, q_node.left])
            deq.append([p_node.right, q_node.right])
        return True
    
# 236. Lowest Common Ancestor of a Binary Tree
class Solution236: # This class contains the method for finding the lowest common ancestor of two nodes in a binary tree.
    # Recursive
    def lowestCommonAncestor(self, root, p, q): # The method takes three arguments:
        # recursive base case 1
        if not root:
            return None
        # recursive base case 2
        # start from the root, bc root is always the common ancestor of all nodes in the tree, although it might not be the "lowest" common ancestor
        # if what we want to find: p or q is in the root -> we return the root because the root is the lowest common ancestor.
        if root == p or root == q: # if we find p node or q node is the same as current root node
            return root

        left = right = None # left, right is TreeNode type
        left = self.lowestCommonAncestor(root.left, p, q)
        right = self.lowestCommonAncestor(root.right, p, q)
        
        '''
        If both left and right are not None, it means that p and q are found in different subtrees of the current root, making the current root the lowest common ancestor. 
        In this case, the function returns the current root.
        '''
        if left and right:
            return root
        '''
		either one of the chidren returned a node, meaning either p or q found on left or right branch.
        Ex: assuming 'p' found in left child, right child returned original defeault 'None'. 
		This means 'q' is somewhere below node where 'p' was found. Therefore, we dont need to search all the way, because node where 'p' found is LCA
		'''
        if left or right:
            return left or right
        
# 117. Populating Next Right Pointers in Each Node II
class Solution117:
    def connect(self, root):
        node = root
        while node:
            curr = dummy = Node(0)
            while node:
                if node.left:
                    curr.next = node.left
                    curr = curr.next
                if node.right:
                    curr.next = node.right
                    curr = curr.next
                node = node.next
            node = dummy.next
        return root

# 226. Invert Binary Tree
class Solution226:
    # Recursion: invert the left lowest node first, and then invert postorderly
    # T: O(N)
    # S: O(H), worst case: O(N)
    #   2
    #  / \
    # 1  3
    # Recusive to root 2
    #   Recusive to root 1
    #       left = None; right = None => exchange => return root 1 (inverted version)
    #   Recusive to root 3
    #       left = None; right = None => exchange => return root 3 (inverted version)
    #   left = 1; right = 3 => exchange TreeNode(2).left = TreeNode(3); TreeNode(2).right = TreeNode(1) => return root 2 (inverted version)
    def invertTree(self, root):
        if not root:
            return root
        left = self.invertTree(root.left)
        right = self.invertTree(root.right)
        root.left = right
        root.right = left
        return root
    
    # Iteration: invert upper layer to lower layer
    # T: O(N)
    # S: O(D), worst case: O(N)
    def invertTree(self, root):
        if not root:
            return root
        q = collections.deque([root])
        while q:
            node = q.popleft()
            node.left, node.right = node.right, node.left
            if node.left:
                q.append(node.left)
            if node.right:
                q.append(node.right)
        return root
    
# 101. Symmetric Tree
class Solution101:
    # Recursion
    # T: O(N)
    # S: O(H), worst case: O(N)
    def isSymmetric(self, root):
        return self.check(root.left, root.right)

    def check(self, t1, t2):
        if not t1 and not t2:
            return True
        if not t1 or not t2:
            return False
        if t1.val != t2.val:
            return False
        return self.check(t1.left, t2.right) and self.check(t1.right, t2.left)
    
    # Iteration
    # T: O(N)
    # S: O(D), worst case: O(N)
    def isSymmetric(self, root):
        q = collections.deque([(root.left, root.right)])
        while q:
            t1, t2 = q.popleft()
            if not t1 and not t2:
                continue
            if not t1 or not t2:
                return False
            if t1.val != t2.val:
                return False
            q.append([t1.left, t2.right])
            q.append([t1.right, t2.left])
        return True   

    
# 222. Count Complete Tree Nodes
class Solution222:
    # Recursion - 1
    # T: O(N)
    # S: O(H), worst case: O(N)
    def countNodes(self, root):
        if not root:
            return 0
        if not root.left and not root.right:
            return 1
        return 1 + self.countNodes(root.left) + self.countNodes(root.right)
    
    # Recursion - 2
    def countNodes(self, root):
        return 1 + self.countNodes(root.right) + self.countNodes(root.left) if root else 0
    
    # Iteration
    # T: O(N)
    # S: O(D), worst case: O(N)
    def countNodes(self, root):
        count = 0
        if not root:
            return count
        q = collections.deque([root])
        while q:
            node = q.popleft()
            count += 1
            if node.left:
                q.append(node.left)
            if node.right:
                q.append(node.right)
        return count



# 129. Sum Root to Leaf Numbers
class Solution129:
    def sumNumbers(self, root: TreeNode):
        result = 0

        def dfs(node, cur_total):
            nonlocal result
            if not node:
                return
            
            cur_total = 10*cur_total + node.val
            if not node.left and not node.right: # bottom leaf node
                result += cur_total
                return
            dfs(node.left, cur_total)
            dfs(node.right, cur_total)

        
        dfs(root, 0)
        return result

# 173. Binary Search Tree Iterator
class BSTIterator:
    def __init__(self, root):
        self.stack = list()
        self.pushAll(root)

    def hasNext(self):
        return self.stack

    def next(self):
        tmpNode = self.stack.pop()
        self.pushAll(tmpNode.right)
        return tmpNode.val
        
    def pushAll(self, node):
        while node is not None:
            self.stack.append(node)
            node = node.left

# 106. Construct Binary Tree from Inorder and Postorder Traversal
# T: O(N)
# S: O(N)
class Solution106:
    def buildTree(self, inorder, postorder):
        if not inorder or not postorder:
            return None
        
        root = TreeNode(postorder.pop())
        inorderIndex = inorder.index(root.val)

        root.right = self.buildTree(inorder[inorderIndex+1:], postorder)
        root.left = self.buildTree(inorder[:inorderIndex], postorder)

        return root

# 105. Construct Binary Tree from Preorder and Inorder Traversal
# T: O(N)
# S: O(N)
class Solution105:
    def buildTree(self, preorder, inorder):
        if not preorder or not inorder:
            return None
        root_val = preorder[0]
        mid = inorder.index(root_val)
        root = TreeNode(root_val)
        root.left = self.buildTree(preorder[1:mid+1], inorder[:mid])
        root.right = self.buildTree(preorder[mid+1:], inorder[mid+1:])
        return root
    
# 112. Path Sum
class Solution112:
    # Recursion 
    def hasPathSum(self, root, targetSum):
        if not root:
            return False
        if not root.left and not root.right and targetSum - root.val == 0:
            return True
        else:
            ret1 = self.hasPathSum(root.left, targetSum-root.val)
            ret2 = self.hasPathSum(root.right, targetSum-root.val)
            return ret1 or ret2
        

# 113. Path Sum II
class Solution113:
    def pathSum(self, root, targetSum):
        if not root:
            return []
        
        def dfs(node, targetSum, path):
            if not node.left and not node.right and targetSum == 0:
                result.append(path)
            if node.left:
                dfs(node.left, targetSum-node.left.val, path+[node.left.val])
            if node.right:
                dfs(node.right, targetSum-node.right.val, path+[node.right.val])

        result = []
        dfs(root, targetSum-root.val, [root.val])
        return result


        
# 114. Flatten Binary Tree to Linked List
class Solution114:
    # right -> left -> middle
    # Use self.prev to recode the ordered tree of the right part of current node.
    # Remove the left part of current node  
    '''
     root
      1 
     / \ 
    3   4  
    Node(4).right = None
    Node(4).left = None
    prev = Node(4)
    ----------------------------------------------------
    Node(3).right = Node(4) (prev)
    Node(3).left = None
    prev = Node(3)->Node(4)
    ----------------------------------------------------
    Node(1).right = prev = Node(3) -> Node(4)
    Node(1).left = None
    prev = Node(1)->Node(3)->Node(4) => answer
    '''
    def __init__(self):
        self.prev = None

    def flatten(self, root):
        """
        Do not return anything, modify root in-place instead.
        """
        if not root:
            return root
        self.flatten(root.right)
        self.flatten(root.left)
        root.right = self.prev # connect to previous right-ordered linked list
        root.left = None
        self.prev = root # remember current right-ordered linked list

# 124. Binary Tree Maximum Path Sum
class Solution124:
    def maxPathSum(self, root):
        if not root:
            return 0
        result = -float('inf')

        # without fork
        def dfs(node):
            nonlocal result
            if not node:
                return 0
            left_max = max(0, dfs(node.left))
            right_max = max(0, dfs(node.right))

            result = max(result, node.val + left_max + right_max) # with fork
            return node.val + max(left_max, right_max)
        
        dfs(root)
        return result



# =============== Binary Tree BFS ===============
# 199. Binary Tree Right Side View
class Solution199:
    def rightSideView(self, root):
        result = []
        if not root: 
            return result
        q = deque()
        q.append(root)
        while q:
            n = len(q)
            for i in range(n):
                node = q.popleft()
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
            result.append(node.val)
            
        return result
    
# 637. Average of Levels in Binary Tree
class Solution637:
    # T: O(N)
    # S: O(M), M: nodes num in one layer
    def averageOfLevels(self, root):
        result = []
        q = deque([root])
        # q = deque()
        # q.append(root)
        while q:
            level_sum = 0
            n = len(q)
            for _ in range(n):
                node = q.popleft()
                level_sum += node.val
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
            result.append(level_sum/n)
        return result



# 102. Binary Tree Level Order Traversal
class Solution102:
    def levelOrder(self, root):
        result = []
        if not root:
            return result
        q = deque([root])
        while q:
            n = len(q)
            level = []
            for _ in range(n):
                node = q.popleft()
                level.append(node.val)
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
            result.append(level)
        return result

# 103. Binary Tree Zigzag Level Order Traversal
class Solution:
    def zigzagLevelOrder(self, root):
        result = []
        if not root:
            return result
        q = deque([root])
        order = -1
        while q:
            n = len(q)
            order *= (-1)
            level = []
            for _ in range(n):
                node = q.popleft()
                level.append(node.val)
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
            result.append(level[::order])
        return result


# =============== Graph BFS ===============

# 433. Minimum Genetic Mutation
class Solution433:
    # original BFS w/ visited set
    # T: O(B), B is the length of bank, because need to search if adj_node in bank, actually is 32*B nodes
    # S: O(1), visited set store 32 nodes
    def minMutation(self, startGene, endGene, bank):
        if endGene not in bank:
            return -1
        if startGene == endGene:
            return 0
        level = 0
        q = deque([(startGene, level)])
        visited = set()
        while q:
            node, level = q.popleft()
            visited.add(node)
            if node == endGene:
                return level
            for i in range(len(startGene)):
                for ch in 'ATCG':
                    adj_node = node[:i] + ch + node[i+1:]
                    if adj_node not in visited and adj_node in bank:
                        q.append((adj_node, level+1))
                        visited.add(adj_node)
        return -1

    # optimized BFS w/o visited set; instead, remove used gene combination in the bank 
    # T: O(B), B is the length of bank, because need to search if adj_node in bank and bank.remove(adj_node)
    # S: O(1), visited set store 32 nodes
    def minMutation(self, startGene, endGene, bank):
        if endGene not in bank:
            return -1
        if startGene == endGene:
            return 0

        level = 0
        q = deque([(startGene, level)])
        # visited = set()
        while q:
            node, level = q.popleft()
            # visited.add(node)
            if node == endGene:
                return level
            for i, ch in enumerate(node):
                for gene in 'ATCG':
                    if ch == gene:
                        continue
                    adj_node = node[:i] + gene + node[i+1:]
                    if adj_node in bank: # O(B), gradually smaller after remove
                        q.append((adj_node, level+1))
                        bank.remove(adj_node) # O(B), bank list gradually become smaller
                        # visited.add(adj_node)
        return -1
    
# 127. Word Ladder    
class Solution127:
    def ladderLength(self, beginWord, endWord, wordList):
        count = 0
        if not beginWord or not endWord or beginWord == endWord or endWord not in wordList or len(beginWord) != len(endWord):
            return count

        graph = collections.defaultdict(list) # key: word with *; value: list (words in it are original word)
        n = len(beginWord)
        for w in wordList: # T: O(N)
            for i in range(n): # T: O(M)
                masked_word = w[:i] + '*' + w[i+1:] # T: O(M)
                graph[masked_word].append(w)
        visited = set()
        count = 1
        q = collections.deque([(beginWord, count)])
        while q:
            node, count = q.popleft()
            visited.add(node)
            if node == endWord:
                return count
            for i in range(len(node)): 
                masked_word = node[:i] + '*' + node[i+1:] 
                if masked_word in graph.keys():
                    for adj_word in graph[masked_word]:  
                        if adj_word not in visited:
                            q.append((adj_word, count+1))
                            visited.add(adj_word)
        return 0        

class Solution127:
    def __init__(self):
        self.l = 0
        self.graph = defaultdict(list)

    def visit_word_node(self, q, visited_a, visited_b):
        q_size = len(q)
        for _ in range(q_size):
            cur_word = q.popleft()
            for i in range(self.l): 
                masked_cur_word = cur_word[:i] + '*' + cur_word[i+1:]
                for adj_word in self.graph[masked_cur_word]:
                    if adj_word in visited_b:
                        return visited_a[cur_word] + visited_b[adj_word]
                    if adj_word not in visited_a:
                        visited_a[adj_word] = visited_a[cur_word] + 1
                        q.append(adj_word)
        return None

    def ladderLength(self, beginWord, endWord, wordList):
        if not endWord or not beginWord or not wordList or endWord not in wordList:
            return 0
        self.l = len(beginWord)
        # graph = defaultdict(list)
        for w in wordList: # TC: O(N)
            for i in range(self.l): # TC: O(M)
                self.graph[w[:i] + '*' + w[i+1:]].append(w) # TC: O(M)
        q_begin = deque()
        q_end = deque()
        q_begin.append(beginWord)
        q_end.append(endWord)
        visited_begin = {beginWord: 1} # value: steps
        visited_end = {endWord: 1} # value: steps
        result = None

        while q_begin and q_end:
			# From the shorter queue to progress forward one step 
            if len(q_begin) <= len(q_end):
                result = self.visit_word_node(q_begin, visited_begin, visited_end)
            else:
                result = self.visit_word_node(q_end, visited_end, visited_begin)
            if result:
                return result
                
        return 0

    
# 547. Number of Provinces
class Solution547:
    # with graph dict
    # T: O(N^2)
    # S: O(N)
    def findCircleNum(self, isConnected):
        count = 0
        n = len(isConnected)
        q = deque()
        visited = set()
        graph = defaultdict(list)
        for i in range(n):
            for j in range(i+1, n):
                if isConnected[i][j] == 1:
                    graph[i].append(j)
                    graph[j].append(i)

        def bfs(node):
            q.append(node)
            while q:
                node = q.popleft()
                visited.add(node)
                for adj_node in graph[node]:
                    if adj_node not in visited:
                        q.append(adj_node)
                        visited.add(adj_node)

        for i in range(n):
            if i not in visited:
                bfs(i)
                count += 1
        return count
    
    # no graph dict, refer original input isConnected, ex: isConnected[node][adj_node]
    # T: O(N^2)
    # S: O(N)
    def findCircleNum(self, isConnected):
        count = 0
        n = len(isConnected)
        q = deque()
        visited = set()
        def bfs(node):
            q.append(node)
            while q:
                node = q.popleft()
                visited.add(node)
                for adj_node in range(n):
                    if isConnected[node][adj_node] == 1 and adj_node not in visited:
                        q.append(adj_node)
                        visited.add(adj_node)

        for i in range(n):
            if i not in visited:
                bfs(i)
                count += 1
        return count

# =============== Graph General ===============
# 200. Number of Islands
class Solution200:
    def numIslands(self, grid):
        count = 0
        dirs = [(1,0), (-1,0), (0,1), (0,-1)]
        q = collections.deque()
        visited = set()

        def bfs(i, j):
            q.append((i,j))
            while q:
                x, y = q.popleft()
                visited.add((x, y))
                for dx, dy in dirs:
                    new_x = x + dx
                    new_y = y + dy
                    if 0 <= new_x < m and 0 <= new_y < n and grid[new_x][new_y] == '1' and (new_x, new_y) not in visited:
                        q.append((new_x, new_y))
                        visited.add((new_x, new_y))

        m = len(grid)
        n = len(grid[0])
        for i in range(m):
            for j in range(n):
                if grid[i][j] == '1' and (i, j) not in visited:
                    count += 1
                    bfs(i, j)
        return count

class Solution130:
    def solve(self, board):
        """
        Do not return anything, modify board in-place instead.
        """
        m = len(board)
        n = len(board[0])
        start = []
        dirs = [(1,0), (-1,0), (0,1), (0,-1)]
        for i in range(m):
            for j in [0, n-1]:
                if board[i][j] == 'O':
                    start.append((i, j))
        for i in [0, m-1]:
            for j in range(n):
                if board[i][j] == 'O': # use thie to optimize, although we only store start points at start array, but the start points might be changed 
                    start.append((i, j))
        '''
        start = list(product(range(m), [0, n-1]))
        start += list(product([0, m-1], range(n)))
        '''
        def bfs(i, j):
            q = collections.deque([(i, j)])
            while q:
                x, y = q.popleft()
                board[x][y] = 'B'
                for dx, dy in dirs:
                    new_x = x + dx
                    new_y = y + dy
                    if 0 <= new_x < m and 0 <= new_y < n and board[new_x][new_y] == 'O':
                        q.append((new_x, new_y))
                        board[new_x][new_y] = 'B'

        for x, y in start:
            if board[x][y] == 'O':
                bfs(x, y)
                
        for i in range(m):
            for j in range(n):
                if board[i][j] == 'O':
                    board[i][j] = 'X'
                elif board[i][j] == 'B':
                    board[i][j] = 'O'



# 399. Evaluate Division
class Solution399:
    # BFS without helper function
    # N: number of input equations; M: number of queries.
    # T: O(M*N)
    # S: O(N)
    def calcEquation(self, equations, values, queries):
        graph = defaultdict(list)
        # T: O(N)
        for (a, b), v in zip(equations, values):
            graph[a].append((b, v))
            graph[b].append((a, 1/v))

        result = []
        for idx, (s, e) in enumerate(queries): # O(M)
            if a == b:
                result.append(1.0)
                continue
            if s not in graph or e not in graph:
                result.append(-1.0)
                continue
            q = collections.deque([(s, 1)])
            visited = set()
            while q: # For each query, traverse the graph. Worst case: traverse the entire graph -> O(N)
                cur_node, cur_prod = q.popleft()
                if cur_node == e:
                    result.append(cur_prod)
                    break
                for adj_node, val in graph[cur_node]:
                    if adj_node not in visited:
                        q.append((adj_node, cur_prod*val))
                        visited.add(adj_node)
            if len(result) != idx+1: # for case if s & e both in graph dict, but s cannot reach to e
                result.append(-1)
        return result


# 207. Course Schedule      
class Solution207:
    # Topological Sort Using Kahn's Algorithm
    # T: O(M+N), N: number of courses; M: size of prerequisites.
    # S: O(M+N)
    def canFinish(self, numCourses, prerequisites):
        graph = defaultdict(list) # key: pre-requisite course; value: couses can take after the key prerequisite course
        indegree = defaultdict(int) # key: course; value: num of pre-requisite courses before taking the key course
        # can be stored as array, too. array index: course number, the integer stored in its index: indegree number of courses (which is how many number of couses need to take before the index course)
        
        for a, b in prerequisites: # T: O(M)
            graph[b].append(a)
            indegree[a] += 1

        visited = set()
        q = deque()
        for node in range(numCourses): # T: O(N)
            if indegree[node] == 0:
                q.append(node)
                
        while q:
            node = q.popleft()
            visited.add(node)
            for adj_node in graph[node]: # T: Worst case: O(M) edges
                indegree[adj_node] -= 1
                if indegree[adj_node] == 0:
                    q.append(adj_node)
                    visited.add(adj_node)
        return len(visited) == numCourses

# 210. Course Schedule II
class Solution210:
    def findOrder(self, numCourses, prerequisites):
        graph = collections.defaultdict(list)
        indegree = [0] * numCourses
        for a, b in prerequisites:
            graph[b].append(a)
            indegree[a] += 1

        q = collections.deque()
        # visited = set()
        result = []
        for node in range(numCourses):
            if indegree[node] == 0:
                q.append(node)
        
        while q:
            node = q.popleft()
            result.append(node)
            for adj_node in graph[node]:
                indegree[adj_node] -= 1
                if indegree[adj_node] == 0:
                    q.append(adj_node)
        return result if len(result) == numCourses else []


class Node:
    def __init__(self, val = 0, neighbors = None):
        self.val = val
        self.neighbors = neighbors if neighbors is not None else []

from typing import Optional
class Solution:
    def __init__(self):
        self.visited = {}
    def cloneGraph(self, node: Optional['Node']) -> Optional['Node']:
        if not node:
            return node
        if node in self.visited:
            return self.visited[node]
        clone_node = Node(node.val, [])
        self.visited[node] = clone_node
        if node.neighbors:
            clone_node.neighbors = [self.cloneGraph(n) for n in node.neighbors]
        return clone_node

        

# =============== Binary Search Tree (BST) ===============
class Solution530:
    # use extra list to store inordered node value
    # T: O(N)
    # S: O(N), bc list and recursion stack
    def getMinimumDifference(self, root):
        
        def inorder(node):
            if not node:
                return []
            return inorder(node.left) + [node.val] + inorder(node.right)

        result = float('inf')
        nodes_arr = inorder(root)
        n = len(nodes_arr)
        for i in range(1, n):
            if nodes_arr[i] - nodes_arr[i-1] < result:
                result = nodes_arr[i] - nodes_arr[i-1]
        return result

    # Not use extra list to store inordered node value, use self.prev to store
    # T: O(N)
    # S: O(N), bc recursion stack
    def getMinimumDifference(self, root):
        self.result = float('inf')
        self.prev = None

        def inorder(node):
            if node == None:
                return
            inorder(node.left)
            if self.prev != None: # need to write like this, cannot writ only: if self.prev: bc might have case is the first previous node is 0
                self.result = min(self.result, node.val-self.prev)
            self.prev = node.val
            inorder(node.right)
        
        inorder(root)
        return self.result


# 230. Kth Smallest Element in a BST
class Solution:
    # Recursion -- inorder template, and find the result after inorder function
    # T: O(N)
    # S: O(N)
    def kthSmallest(self, root, k):
        def inorder(node):
            if not node:
                return []
            return inorder(node.left) + [node.val] + inorder(node.right)
        return inorder(root)[k-1]

    # Recursion -- update result within inorder function
    # T: O(N)
    # S: O(logN) or O(N)
    def kthSmallest(self, root, k):
        self.count = 0
        self.result = None
        def inorder(node):
            if not node:
                return
            inorder(node.left)
            self.count += 1
            if self.count == k:
                self.result = node.val
                return
            inorder(node.right)

        inorder(root)
        return self.result
    
    # Iterative DFS with stack
    # T: O(H+k), where H is a tree height. Since before starting to pop out one has to go down to a leaf. This results in
    #            O(log⁡N+k) for the balanced tree 
    #            O(N+k) for completely unbalanced tree 
    # S: O(H), bc stack
    #          O(log⁡N) for the balanced tree 
    #          O(N) for completely unbalanced tree 
    def kthSmallest(self, root, k):
        n = 0
        stack = []
        cur = root
        while cur or stack:
            while cur:
                 stack.append(cur)
                 cur = cur.left
            cur = stack.pop()
            n += 1
            if n == k:
                return cur.val
            cur = cur.right

# 98. Validate Binary Search Tree
class Solution98:
    def isValidBST(self, root):
        def validate(node, low, high):
            if not node:
                return True
            if low >= node.val or node.val >= high:
                return False
            return validate(node.left, low, node.val) and validate(node.right, node.val, high)
            
        return validate(root, -float('inf'), float('inf'))
    



# 1926. Nearest Exit from Entrance in Maze
class Solution1926:
    # T: O(M*N)
    # S: O(M*N)
    def nearestExit(self, maze, entrance):
        m = len(maze)
        n = len(maze[0])
        steps = 0
        start_x, start_y = entrance
        q = deque([(start_x, start_y, steps)])
        dirs = [(1,0), (-1,0), (0,1), (0,-1)]
        visited = set()
        while q:
            i, j, steps = q.popleft()
            visited.add((i,j))
            if [i,j] != entrance and (i == 0 or i == m-1 or j == 0 or j == n-1):
            # or if levels != 0 and ....
                return steps
            for dx, dy in dirs:
                new_x = i + dx
                new_y = j + dy
                if 0 <= new_x < m and 0 <= new_y < n and maze[new_x][new_y] == '.' and (new_x, new_y) not in visited:
                    q.append((new_x, new_y, steps+1))
                    visited.add((new_x, new_y))
        return -1

# =============== Trie ===============
# 208. Implement Trie (Prefix Tree)
class TrieNode:
    def __init__(self):
        self.children = {}
        self.is_end = False

class Trie:

    def __init__(self):
        self.root = TrieNode()
        

    def insert(self, word: str) -> None:
        cur = self.root
        for w in word:
            if w not in cur.children.keys():
                cur.children[w] = TrieNode()
            cur = cur.children[w]
        cur.is_end = True


    def search(self, word: str) -> bool:
        cur = self.root
        for w in word:
            if w not in cur.children.keys():
                return False
            cur = cur.children[w]
        return cur.is_end

    def startsWith(self, prefix: str) -> bool:
        cur = self.root
        for w in prefix:
            if w not in cur.children.keys():
                return False
            cur = cur.children[w]
        return True

# 212. Word Search II
# build trie 
class TrieNode:
    # constructor
    def __init__(self):
        # 2 member variables
        self.children = {}
        self.is_word = False
        
    def add_word(self, word):
        cur = self
        for w in word:
            if w not in cur.children.keys():
                cur.children[w] = TrieNode()
                cur = cur.children[w]
            else:
                cur = cur.children[w]

        cur.is_word = True

class Solution:
    def findWords(self, board, words):
        root = TrieNode()
        # build trie for all the word in given words array
        for word in words:
            root.add_word(word)

        def dfs(i, j, cur_d, found_word):
            if i >= m or i < 0 or j >= n or j < 0 or (i, j) in visited or board[i][j] not in cur_d.children:
                return 

            visited.add((i,j))
            letter = board[i][j]
            cur_d = cur_d.children[letter]
            found_word += letter
            
            # base case
            if cur_d.is_word == True:
                result.add(found_word)
            
            for dx, dy in [(1,0), (-1,0), (0,1), (0,-1)]:
                new_i = i + dx
                new_j = j + dy
                dfs(new_i, new_j, cur_d, found_word)

            # backtrack
            visited.remove((i,j))

        result = set()
        m = len(board)
        n = len(board[0])
        visited = set()
        for i in range(m):
            for j in range(n):
                dfs(i, j, root, "")
        return list(result)


            
# =============== Backtracking ===============
# 17. Letter Combinations of a Phone Number
# dfs func write inside main func
class Solution17:
    def letterCombinations(self, digits):
        result = []
        if not digits:
            return result
        d = {'2':'abc', '3':'def', '4':'ghi', '5':'jkl', '6':'mno', '7':'pqrs', '8':'tuv', '9':'wxyz'}
        n = len(digits)
        
        def backtrack(cur_idx, comb):
            if len(comb) == n:
                result.append(comb)
                return
            else:
                for ch in d[digits[cur_idx]]:

                    backtrack(cur_idx+1, comb+ch)
        backtrack(0, '')
        return result

# dfs func write outside main func
class Solution17:
    def letterCombinations(self, digits):
        result = []
        if not digits:
            return result
        d = {'2':'abc', '3':'def', '4':'ghi', '5':'jkl', '6':'mno', '7':'pqrs', '8':'tuv', '9':'wxyz'}
        
        self.backtrack(digits, d, 0, '', result)
        return result

    def backtrack(self, digits, d, cur_idx, comb, result):
        if len(comb) == len(digits):
            result.append(comb)
            return
        else:
            for ch in d[digits[cur_idx]]:
                self.backtrack(digits, d, cur_idx+1, comb+ch, result)


# 77. Combinations
class Solution77:
    # start from dfs(0, [])
    def combine(self, n, k):
        result = []
        def dfs(cur_idx, path):
            if len(path) == k:
                result.append(path)
                return
            for i in range(cur_idx+1, n+1):
                dfs(i, path+[i])
        dfs(0, [])
        return result
    
    # start from dfs(1, [])
    def combine(self, n, k):
        result = []
        def dfs(cur_idx, path):
            if len(path) == k:
                result.append(path)
                return
            for i in range(cur_idx, n+1):
                dfs(i+1, path+[i])
        dfs(1, [])
        return result

# 46. Permutations
class Solution46:
    def permute(self, nums):
        result = []
        def dfs(path):
            if len(path) == len(nums):
                result.append(path)
                return 
            for n in nums:
                if n not in path:
                    dfs(path + [n])
        dfs([])
        return result

# 39. Combination Sum
class Solution39:
    def combinationSum(self, candidates, target):
        result = []
        n = len(candidates)
        def dfs(idx, path):
            if sum(path) == target:
                result.append(path)
                return
            if sum(path) > target:
                return
            for i in range(idx, n):
                dfs(i, path+[candidates[i]])
            
        dfs(0, [])
        return result

# 22. Generate Parentheses
class Solution22:
    def generateParenthesis(self, n):
        result = []
        def dfs(left, right, path):
            # if left > right:
            #     return
            if left == 0 and right == 0:
                result.append(path)
                return
            if left > 0:
                dfs(left-1, right, path+'(')
            # if right > 0:
            if right > left:
                dfs(left, right-1, path+')')

        dfs(n, n, '')
        return result

# 79. Word Search
class Solution79:
    # backtrack to original board when we find or not find the word
    # N: number of cells in the board; L: the length of the word to be matched
    # T: O(N*3^L), at first we have at most 4 directions to explore, but further the choices are reduced into 3 (since we won't go back to where we come from).
    # S: O(L)
    def exist(self, board, word) -> bool:
        m = len(board)
        n = len(board[0])
        def dfs(i, j, suffix):
            if len(suffix) == 0:
                return True
            if i >= m or i < 0 or j >= n or j < 0 or board[i][j] != suffix[0]:
                return False
            ret_val = False
            board[i][j] = "$"
            for dx, dy in [(1,0), (-1,0), (0,1), (0,-1)]:
                ret_val = dfs(i+dx, j+dy, suffix[1:])
                if ret_val:
                    break
            board[i][j] = suffix[0]
            return ret_val

        for i in range(m):
            for j in range(n):
                ret = dfs(i, j, word)
                if ret == True:
                    return True
        return False
        
    # NOT backtrack to original board when we find the word
    # T: O(N*3^L), at first we have at most 4 directions to explore, but further the choices are reduced into 3 (since we won't go back to where we come from).
    # S: O(L)
    def exist(self, board, word):
        def dfs(i, j, suffix):
            # bottom case: we find match for each letter in the word
            # need to write before checking if the current state is valid or not
            if len(suffix) == 0:
                return True
            # check if the current state is invalid
            if i < 0 or i >= m or j < 0 or j >= n or board[i][j] != suffix[0]:
                return False
            board[i][j] = '$'
            for dx, dy in [(1,0), (-1,0), (0,1), (0,-1)]:
                new_i = i + dx
                new_j = j + dy
                if dfs(new_i, new_j, suffix[1:]) == True:
                    return True # if we find a match, we return before backtrack '$' back to originsl word
            board[i][j] = suffix[0] # backtrack
            return False

        m = len(board)
        n = len(board[0])
        for i in range(m):
            for j in range(n):
                if dfs(i, j, word) == True:
                    return True
        return False

# 51. N-Queens
class Solution51:
    # N: the number of queens (which is the same as the width and height of the board).
    # T: O(N!)
    # will only place queens on squares that aren't under attack. 
    # For the first queen, we have N options. 
    # For the next queen, we won't attempt to place it in the same column as the first queen, and there must be at least one square attacked diagonally by the first queen as well. Thus, the maximum number of squares we can consider for the second queen is N−2. 
    # For the third queen, we won't attempt to place it in 2 columns already occupied by the first 2 queens, and there must be at least two squares attacked diagonally from the first 2 queens. Thus, the maximum number of squares we can consider for the third queen is N−4. 
    # result in an approximate time complexity of N!.
    # S: O(N^2), bc keep the board state costs O(N^2)
    def solveNQueens(self, n):
        result = []

        def create_board(state): # T: O(N^2)
            board = []
            for row in state:
                board.append(''.join(row))
            return board

        def dfs(row, cols, diagonals, anti_diagonals, state):
            # Base case - N queens have been placed
            if row == n:
                result.append(create_board(state)) # valid solution: S(N
                # => T: S(N) * N^2
                return
            for col in range(n):
                # For each square on a given diagonal, (row - col) will be constant.
                cur_diagonal = row - col 
                # For each square on a given anti-diagonal, (row + col) will be constant.
                cur_anti_diagonal = row + col
                # If the queen is not placeable
                if col in cols or cur_diagonal in diagonals or cur_anti_diagonal in anti_diagonals:
                    continue

                 # "Add" the queen to the board
                cols.add(col)
                diagonals.add(cur_diagonal)
                anti_diagonals.add(cur_anti_diagonal)
                state[row][col] = 'Q'

                # Move on to the next row with the updated board state
                dfs(row+1, cols, diagonals, anti_diagonals, state)

                # backtrack
                # "Remove" the queen from the board since we have already
                # explored all valid paths using the above function call
                cols.remove(col)
                diagonals.remove(cur_diagonal)
                anti_diagonals.remove(cur_anti_diagonal)
                state[row][col] = '.'

        result = []
        empty_board = [["."] * n for _ in range(n)]
        dfs(0, set(), set(), set(), empty_board)
        return result

# 52. N-Queens II
class Solution52:
    # method same as LC 51
    # N: the number of queens (which is the same as the width and height of the board).
    # T: O(N!)
    # S: O(N), bc keep the set cost O(N)
    def totalNQueens(self, n):      
        self.result = 0
        def dfs(row, cols, diagonals, anti_diagonals):
            if row == n:
                self.result += 1
            for col in range(n):
                cur_diagonal = row - col
                cur_anti_diagonal = row + col
                if col in cols or cur_diagonal in diagonals or cur_anti_diagonal in anti_diagonals:
                    continue
                # place queen
                cols.add(col)
                diagonals.add(cur_diagonal)
                anti_diagonals.add(cur_anti_diagonal)
                # continue to dfs
                dfs(row+1, cols, diagonals, anti_diagonals)
                # backtrack
                cols.remove(col)
                diagonals.remove(cur_diagonal)
                anti_diagonals.remove(cur_anti_diagonal)

        dfs(0, set(), set(), set())
        return self.result
            

# 108. Convert Sorted Array to Binary Search Tree
class Solution108:
    def sortedArrayToBST(self, nums):
        def dfs(left, right):
            if left > right:
                return None
            mid_idx = (left+right)//2
            # Choose Random Middle Node as a Root
            if (left+right)%2 == 1:
                mid_idx += random.randint(0,1)
            root = TreeNode(nums[mid_idx])
            root.left = dfs(left, mid_idx-1)
            root.right = dfs(mid_idx+1, right)
            return root
        return dfs(0, len(nums)-1)
    
    def sortedArrayToBST(self, nums):
        def dfs(left, right):
            if left > right:
                return None
            mid_idx = (left+right)//2 # Choose Left Middle Node as a Root
            root = TreeNode(nums[mid_idx])
            root.left = dfs(left, mid_idx-1)
            root.right = dfs(mid_idx+1, right)
            return root
        return dfs(0, len(nums)-1)
    
# 148. Sort List
class Solution148:
    # top down merge sort
    # T: O(NlogN)
    # S: O(logN)

    # T: O(logN)
    def sortList(self, head):
        if head == None or head.next == None:
            return head

        slow = head
        fast = head
        while fast.next != None and fast.next.next != None:
            fast = fast.next.next
            slow = slow.next
        second = slow.next
        slow.next = None
        list1 = self.sortList(head)
        list2 = self.sortList(second)
        return self.merge(list1, list2)
        
    # T: O(N)
    def merge(self, list1, list2):
        cur = dummy = ListNode(0)
        p1 = list1
        p2 = list2
        while p1 and p2:
            if p1.val < p2.val:
                cur.next = p1
                p1 = p1.next
            else:
                cur.next = p2
                p2 = p2.next
            cur = cur.next
        if p1:
            cur.next = p1
        if p2:
            cur.next = p2
        return dummy.next

        
# 427. Construct Quad Tree
# Definition for a QuadTree node.
class Node:
    def __init__(self, val, isLeaf, topLeft, topRight, bottomLeft, bottomRight):
        self.val = val
        self.isLeaf = isLeaf
        self.topLeft = topLeft
        self.topRight = topRight
        self.bottomLeft = bottomLeft
        self.bottomRight = bottomRight

class Solution427:
    def construct(self, grid):
        def dfs(n, r, c):
            # the only base case: the current grid has the same value (i.e all 1's or all 0's)
            # n==1 case is included in this case -> dont need to specify
            all_same = True
            for i in range(n):
                for j in range(n):
                    if grid[r][c] != grid[r+i][c+j]:
                        all_same = False
                        break
            if all_same:
                return Node(grid[r][c], True, None, None, None, None)
                # return Node(grid[r][c], True)
                # set val to the value of the grid and set the four children to Null (or dont set it, and use default None) and stop.
            
            # If not meet base case, keep recursively call dfs
            n = n//2
            top_left = dfs(n, r, c)
            top_right = dfs(n, r, c+n)
            bottom_left = dfs(n, r+n, c)
            bottom_right = dfs(n, r+n, c+n)
            return Node(0, False, top_left, top_right, bottom_left, bottom_right)
        
        return dfs(len(grid), 0, 0)

# 23. Merge k Sorted Lists
class Solution23:
    def mergeKLists(self, lists):
        minHeap = []
        for l in lists:
            tmp = l
            while tmp:
                heapq.heappush(minHeap, tmp.val)
                tmp = tmp.next

        dummy = ListNode(-1)
        res = dummy 
        while minHeap:
            res.next = ListNode(heapq.heappop(minHeap))
            res = res.next

        return dummy.next

            

# =============== Kadane's Algorithm =============== 
# finding the maximum sum subarray from a given array

# 53. Maximum Subarray
class Solution53:
    # T: O(N)
    # S: O(N)
    def maxSubArray(self, nums):
        n = len(nums)
        dp = [0] * n
        dp[0] = nums[0]
        for i in range(1, n):
            dp[i] = max(dp[i-1]+nums[i], nums[i])
        return max(dp)

    # T: O(N)
    # S: O(1)
    def maxSubArray(self, nums):
        n = len(nums)
        curSum = -float('inf')
        result = -float('inf')
        for i in range(n):
            curSum = max(curSum + nums[i], nums[i])
            result = max(result, curSum)
        return result

# 918. Maximum Sum Circular Subarray
class Solution918:
    def maxSubarraySumCircular(self, nums):
        if max(nums) < 0:
            return max(nums)
        n = len(nums)
        max_dp = [0]*n
        min_dp = [0]*n
        max_dp[0] = nums[0]
        min_dp[0] = nums[0]

        for i in range(1, n):
            max_dp[i] = max(max_dp[i-1] + nums[i], nums[i])
            min_dp[i] = min(min_dp[i-1] + nums[i], nums[i])

        return max(max(max_dp), sum(nums)-min(min_dp))
        
    
# =============== Binary Search (BS) ===============
# 278. Firs Bad Version
class Solution278:
    # template 1
    def firstBadVersion(self, n):
        l = 1
        r = n
        while l <= r:
            mid = l + (r-l)//2
            if isBadVersion(mid) == False:
                l = mid + 1
            else:
                r = mid -1
        return l
    
    # template 2, better bc not first return True will be the ans
    def firstBadVersion(self, n):
        left = 1
        right = n
        while left < right:
            mid = left+(right-left)//2
            if isBadVersion(mid) == False:
                left = mid+1
            else:
                right = mid
        return left


# 35. Search Insert Position
class Solution35:
    # template 1
    # T: O(logN)
    # S: O(1)
    def searchInsert(self, nums, target):
        left = 0
        right = len(nums) - 1
        while left <= right:
            mid = left + (right-left) // 2
            if nums[mid] == target:
                return mid
            elif nums[mid] < target:
                left = mid + 1
            else:
                right = mid - 1
        return left
    
    # template 2
    # T: O(logN)
    # S: O(1)
    def searchInsert(self, nums, target):
        left = 0
        right = len(nums) - 1
        while left < right:
            mid = left + (right-left) // 2
            if nums[mid] < target:
                left = mid + 1
            else:
                right = mid
        # 1 more candidate
        # post-processing
        return left if target <= nums[left] else left + 1

    # bisect.bisect_left()
    # T: O(logN)
    # S: O(1)
    def searchInsert(self, nums, target):
        idx = bisect.bisect_left(nums, target)
        return idx


# 74. Search a 2D Matrix
class Solution74:
    # template 1
    # T: O(M*N)
    # S: O(1)
    def searchMatrix(self, matrix, target):
        m = len(matrix)
        n = len(matrix[0])
        left = 0
        right = m*n-1
        while left <= right:
            mid = left + (right-left) // 2
            i = mid // n
            j = mid % n
            if matrix[i][j] == target:
                return True
            elif matrix[i][j] < target:
                left = mid + 1
            else:
                right = mid - 1
        return False

    # template 2
    # T: O(M*Nlog(M*N))
    # S: O(1)
    def searchMatrix(self, matrix, target):
        m = len(matrix)
        n = len(matrix[0])
        left = 0
        right = m*n-1
        while left < right:
            mid = left + (right-left)//2
            i = mid // n
            j = mid % n
            if matrix[i][j] < target:
                left = mid+1
            else:
                right = mid
        return matrix[left//n][left%n] == target
    
    # bisect.bisect_left()
    # T: O(M*N)
    # S: O(M*N)
    def searchMatrix(self, matrix, target):
        m = len(matrix)
        n = len(matrix[0])
        one_d_matrix = []
        for row in matrix:
            one_d_matrix.extend(row)
        idx = bisect.bisect_left(one_d_matrix, target)
        return idx < m*n and one_d_matrix[idx] == target

    # search row and search col !!!!
    # T: O(M+N)
    # S: O(1)

 # 69. Sqrt(x)
class Solution69:
    # template 1
    def mySqrt(self, x):
        left = 0
        right = x
        while left <= right:
            mid = left+(right-left)//2
            if mid*mid == x:
                return mid
            elif mid*mid < x:
                left = mid + 1
            else:
                right = mid - 1
        return right
    
    # template 2 - modified
    def mySqrt(self, x):
        # for template 2 we need to process edge case x=0 like below.
        # cannot do BS directly start from left=0 
        if x <= 1:
            return x
        left = 1
        right = x
        while left < right:
            mid = left+(right-left)//2
            if mid*mid <= x: # <=, bc if we meet mid*mid == x, we want to let left = mid+1, so that when the while loop ends, the answer we return: left-1 is the final answer
                left = mid + 1
            else:
                right = mid
        return left-1

class Solution2300:
    # Notice that if a spell and potion pair is successful, then the spell and all stronger potions will be successful too.
    # sort potions, and do BS
    # T: O(NlogN)
    # S: O(M)
    def successfulPairs(self, spells, potions, success):
        n = len(spells)
        m = len(potions)
        potions.sort() # MlogM
        result = []
		# NlogM
        for i in range(n):
            needed_num = math.ceil(success / spells[i])
            idx = bisect.bisect_left(potions, needed_num)
            result.append(m-idx)      
        return result

class Solution162:
    # T: O(N)
    # S: O(1)
    def findPeakElement(self, nums):
        n = len(nums)
        for i in range(n-1):
            if nums[i] > nums[i+1]:
                return i
        return n-1
    
    # template 1
    # T: O(N)
    # S: O(1)
    def findPeakElement(nums):
        n = len(nums)
        l = 0
        r = n-1
        while l <= r:
            mid = l + (r-l)//2
            # right element is greater 
            if mid+1 < n and nums[mid] < nums[mid+1]:
                l = mid+1
            # left element is greater
            elif mid-1 >= 0 and nums[mid-1] > nums[mid]:
                r = mid-1
            else:
                return mid
        
    # template 2
    # T: O(N)
    # S: O(1)
    def findPeakElement(nums):
        n = len(nums)
        left = 0
        right = n-1
        while left < right:
            mid = left+(right-left)//2
            if nums[mid] <= nums[mid+1]:
                left = mid + 1
            else:
                right = mid
        return left


# 153. Find Minimum in Roateted Sorted Array
class Solution153:
    # T: O(logN)
    # S: O(1)
    def findMin(self, nums):
        n = len(nums)
        left = 0
        right = n-1
        while left < right:
            mid = left+(right-left)//2
            if nums[mid] >= nums[-1]:
                left = mid+1
            else:
                right = mid
        return nums[left]




# 33. Search in Rotated Sorted Array
class Solution33:
    # Find Pivot Index + Binary Search with Shift
    def search(self, nums, target):
        n = len(nums)
        left = 0
        right = n-1
        # 1st binary search to find pivot
        while left < right:
            mid = left+(right-left)//2
            if nums[mid] >= nums[-1]:
                left = mid+1
            else:
                right = mid
            ''' # can also write as below:
            if nums[mid] < nums[-1]:
                right = mid
            else:
                left = mid+1
            '''
        pivot = left
        left = 0 
        right = n-1
        # shift every element to the right by n - pivot steps to reach the sorted version of nums
        shift = n-pivot
        # 2nd binary search to find target idx (real_mid) using pivot
        while left <= right:
            mid = left+(right-left)//2
            # we now need to shift the index in the sorted nums to the left by n - pivot steps to find its corresponding index, i, in the original nums (back to original Rotated Sorted Array). 
            # This gives us i - (n - pivot) (taking the modulus of n into account).
            real_mid = (mid-shift)%n 
            if nums[real_mid] == target:
                return real_mid
            elif nums[real_mid] < target:
                left = mid+1
            else:
                right = mid-1
        return -1

    # Find Pivot Index + Binary Search with Shift (in helper function)
    # Ex: nums=[4,5,6,7,0,1,2], target=6
    def search(self, nums, target):
        n = len(nums)
        left = 0
        right = n-1
        while left < right:
            mid = left+(right-left)//2
            if nums[mid] >= nums[-1]:
                left = mid+1
            else:
                right = mid
        
        def shiftBS(pivot_index, target):
            shift = n-pivot_index
            left = 0
            right = n-1
            while left <= right:
                mid = left+(right-left)//2
                real_mid = (mid-shift)%n
                if nums[real_mid] == target:
                    return real_mid
                elif nums[real_mid] < target:
                    left = mid+1
                else:
                    right = mid-1
            return -1

        return shiftBS(left, target)
    
    # one binary search, compare with the leftmost value
    def search(self, nums, target):
        n = len(nums)
        left = 0
        right = n-1
        while left <= right:
            mid = left+(right-left)//2
            if nums[mid] == target:
                return mid

            # compare with the leftmost value to know if current mid's left is sorted OR current mid's right is sorted
            elif nums[left] <= nums[mid]:  # subarray on mid's left is sorted
                if nums[left] <= target <= nums[mid]:
                    right = mid-1
                else: # if target > nums[mid] or target < nums[left]
                    left = mid+1
            else: # subarray on mid's right is sorted.
                if nums[mid] <= target <= nums[right]:        
                    left = mid+1
                else: # if target < nums[mid] or target > nums[right] 
                    right = mid-1 
        return -1

    # one binary search, compare with the rightmost value
    def search(self, nums, target):
        n = len(nums)
        left = 0
        right = n-1
        while left <= right:
            mid = left+(right-left)//2
            if nums[mid] == target:
                return mid
            # compare with the rightmost value
            elif nums[mid] > nums[-1]: # left is sorted
                if nums[left] <= target <= nums[mid]:
                    right = mid-1
                else:
                    left = mid+1
            else: # right is sorted
                if nums[mid] <= target <= nums[right]:
                    left = mid+1
                else:
                    right = mid-1
        return -1
    
class Solution34:
    # bisect
    # T: O(logN)
    # S: O(1)
    def searchRange(self, nums, target):
        left_idx = bisect.bisect_left(nums, target)
        right_idx = bisect.bisect_right(nums, target)
        left = -1
        right = -1
        n = len(nums)
        if left_idx < n and nums[left_idx] == target:
            left = left_idx
        if right_idx > 0 and nums[right_idx-1] == target:
            right = right_idx-1
        return [left, right]

    # bisect optimized version
    # T: O(logN)
    # S: O(1)
    def searchRange(self, nums, target):
        n = len(nums)
        if n == 0:
            return [-1, -1]
        l_idx = bisect.bisect_left(nums, target)
        if l_idx == n or nums[l_idx] != target:
            return [-1, -1]
        r_idx = bisect.bisect_right(nums, target)
        return [l_idx, r_idx-1]
    
    # Binary search and linear search to the left/right to find the first and the last position
    # T: O(N)
    # S: O(1)
    def searchRange(self, nums, target):
        n = len(nums)
        left = 0 
        right = n-1
        while left <= right:
            mid = left+(right-left)//2
            if nums[mid] == target:
                l_idx = mid
                r_idx = mid
                while l_idx > 0 and nums[l_idx-1] == target:
                    l_idx -= 1
                while r_idx+1 < n and nums[r_idx+1] == target:
                    r_idx += 1
                return [l_idx, r_idx]
            elif nums[mid] > target:
                right = mid-1
            else:
                left = mid+1
        return [-1, -1]
    
    # BS template 1
    # T: O(logN)
    # S: O(1)
    def searchRange(self, nums, target):

        def bs(nums, target, left_bias):
            n = len(nums)
            left = 0 
            right = n-1
            idx = -1
            while left <= right:
                mid = left+(right-left)//2
                if nums[mid] == target:
                    idx = mid
                    if left_bias == 1:
                        right = mid-1
                    elif left_bias == 0:
                        left = mid+1
                elif nums[mid] > target:
                    right = mid-1
                else:
                    left = mid+1
            return idx

        l_idx = bs(nums, target, 1)
        r_idx = bs(nums, target, 0) 
        return [l_idx, r_idx]
    

class Solution4:
    def findMedianSortedArrays(self, nums1, nums2):
        len1 = len(nums1)
        len2 = len(nums2)
         # when total length is odd, the median is the middle
        if(len1+len2) % 2 != 0:
            return self.get_kth(nums1, nums2, 0, len1-1, 0,len2-1, (len1+len2)//2)
        else:
        # when total length is even, the median is the average of the middle 2
            mid1 = self.get_kth(nums1, nums2, 0, len1-1, 0, len2-1, (len1+len2)//2)
            mid2 = self.get_kth(nums1, nums2, 0, len1-1, 0, len2-1, (len1+len2)//2 -1)
        return (mid1 + mid2) / 2
            
    def get_kth(self, nums1, nums2, start1, end1, start2, end2, k):
        if start1 > end1:
            return nums2[k-start1]
        if start2 > end2:
            return nums1[k-start2]
        
        mid1 = (start1 + end1) // 2
        mid2 = (start2 + end2) // 2
        mid1Value = nums1[mid1]
        mid2Value = nums2[mid2]

        if (mid1 + mid2) < k:
            if mid1Value > mid2Value:
                return self.get_kth(nums1, nums2, start1, end1, mid2+1, end2, k)
            else:
                return self.get_kth(nums1, nums2, mid1+1, end1, start2, end2, k)
        else:
            if mid1Value > mid2Value:
                return self.get_kth(nums1, nums2, start1, mid1-1, start2, end2, k)
            else:
                return self.get_kth(nums1, nums2, start1, end1, start2, mid2-1, k)
        

# =============== Dynamic Programming (DP) ===============
# 70. Climbing Stairs
class Solution70:
    def climbStairs(self, n):
        result = 1
        if n == 1:
            return result
        a = 1
        b = 1
        
        for i in range(2, n+1):
            result = a + b
            a = b
            b = result
        return result
    
# 198. House Robber
class Solution198:
    # Regular DP
    # T: O(N)
    # S: O(N)
    def rob(self, nums):
        n = len(nums)
        if n <= 2:
            return max(nums)
        dp = [0] * n
        dp[0] = nums[0]
        dp[1] = nums[1]
        for i in range(1, n):
            dp[i] = max(dp[i-2] + nums[i], dp[i-1])
        return dp[-1]
    
    # Space optimized
    # T: O(N)
    # S: O(1)
    def rob(self, nums):
        n = len(nums)
        if n <= 2:
            return max(nums)
        a = nums[0] # F(n-2)
        b = max(nums[0], nums[1]) # F(n-1)
        c = 0 # F(n)
        for i in range(2, n):
            c = max(a + nums[i], b)
            a = b
            b = c
        return c
    
# 139. Word Break
# TC: O(n⋅m⋅k), Given n as the length of s, m as the length of wordDict, and k as the average length of the words in wordDict
# SC: O(n), use an array dp of length n
class Solution139:
    def wordBreak(self, s, wordDict):
        n = len(s)
        dp = [True] + [False] * n
        for i in range(n+1): # O(N)
            if dp[i]:
                for w in wordDict: # O(M)
                    if s[i:i+len(w)] == w: # O(K)
                        dp[i+len(w)] = True
        return dp[-1]

# 62. Unique Paths
class Solution62:
    # DP
    # T: O(M*N)
    # S: O(M*N)
    def uniquePaths(self, m, n):
        dp = [[1] * n for _ in range(m)]
        for i in range(1, m):
            for j in range(1, n):
                dp[i][j] = dp[i-1][j] + dp[i][j-1]
        return dp[-1][-1]
    
    # DP with space optimized
    # T: O(M*N)
    # S: O(N)
    def uniquePaths(self, m, n):
        dp = [1] * n
        for _ in range(1, m):
            for j in range(1, n):
                dp[j] += dp[j-1]
        return dp[-1]
        
    # Combinatorial problem - using built-in function
    # horizontal moves: h = m−1; vertical moves: v = n-1; total choices = m+n-2, can choose any right or down moves from total choices
    # T: standard computation for k! using the definition: O(k^2log⁡k), worse than DP
    # S: O(1)
    def uniquePaths(self, m, n):
        return int(factorial(m + n - 2) / (factorial(n - 1) * factorial(m - 1)))
    
    # Combinatorial problem - math
    # ex: m=8, n=3, ans = 9!/(7!*2!)
    # T: better than DP
    # S: O(1)
    def uniquePaths(self, m, n):
        c = m + n -2
        big_num = max(m-1, n-1)
        small_num = min(m-1, n-1)
        result = 1
        # result *= 9*8
        for i in range(c, big_num, -1):
            result *= i
        # result /= 2*1
        for j in range(small_num, 0, -1):
            result /= j
        return int(result)

# 1143. Longest Common Subsequence
class Solution1143:
    # 2D DP, check from the beginning of the word to the end of the word
    # T: O(M*N), M: len of text1; N: len of text2
    # S: O(M*N)
    def longestCommonSubsequence(self, text1, text2):
        len1 = len(text1)
        len2 = len(text2)
        dp = [[0] * (len2+1) for _ in range(len1+1)] # dp = [[0 for _ in range(len2+1)] for _ in range(len1+1)]
        for i in range(len1):
            for j in range(len2):
                if text1[i] == text2[j]:
                    dp[i+1][j+1] = dp[i][j] + 1
                else: # if this pair doesn't match, we still need to record cur longest match
                    dp[i+1][j+1] = max(dp[i][j+1], dp[i+1][j])
        return dp[-1][-1]
    
    # 2D DP, check from the end of the word to the beginning of the word
    # T: O(M*N), M: len of text1; N: len of text2
    # S: O(M*N)
    def longestCommonSubsequence(self, text1, text2):
        len1 = len(text1)
        len2 = len(text2)
        dp = [[0 for _ in range(len2+1)] for _ in range(len1+1)]
        for i in range(len1-1, -1, -1):
            for j in range(len2-1, -1, -1):
                if text1[i] == text2[j]:
                    dp[i][j] = dp[i+1][j+1] + 1
                else:
                    dp[i][j] = max(dp[i+1][j], dp[i][j+1])
            
        return dp[0][0]

# 300. Longest Increasing Subsequence
class Solution300:
    # T: O(N^2)
    # S: O(N)
    def lengthOfLIS(self, nums):
        n = len(nums)
        dp = [1] * n
        for i in range(n):
            for j in range(i):
                if nums[i] > nums[j]:
                    dp[i] = max(dp[j]+1, dp[i])
        return max(dp)

    # T: O(N^2)
    # S: O(M), M is LIS length
    def lengthOfLIS(self, nums):
        n = len(nums)
        seq = []
        for i in range(n):
            if not seq or nums[i] > seq[-1]:
                seq.append(nums[i])
            else:
                j = 0
                while nums[i] > seq[j]:
                    j += 1
                seq[j] = nums[i]
            
        return len(seq)

# 221. Maximal Square
class Solution221:
    def maximalSquare(self, matrix):
        if not matrix:
            return 0
        m = len(matrix)
        n = len(matrix[0])
        dp = [[0]*(n+1) for _ in range(m+1)]
        max_side = 0
        for i in range(m):
            for j in range(n):
                if matrix[i][j] == '1':
                    dp[i+1][j+1] = min(dp[i][j], dp[i+1][j], dp[i][j+1]) + 1
                    max_side = max(max_side, dp[i+1][j+1])
        return max_side*max_side


# =============== Bit ===============
# 1318. Minimum Flips to Make a OR b Equal to c
class Solution1318:
    # regular operation
    # T: O(1)
    # S: O(1)
    def minFlips(self, a, B, C):
        count = 0
        while a or b or c:
            x = a % 2
            y = b % 2
            z = c % 2
            if x == 0 and y == 0 and z == 1:
                count += 1
            if z == 0:
                count += (x == 1)
                count += (y == 1)
            a //= 2
            b //= 2
            c //= 2
        return count
    
    # bit solution
    # T: O(1)
    # S: O(1)
    def minFlips(self, a, b, c):
        count = 0
        while a or b or c:
            # case: c = 1;  a = 0, b = 0
            if c & 1:
                if (a & 1) == 0 and (b & 1) == 0:
                    count += 1
                # count += 0 if ((a & 1) or (b & 1)) else 1

            # case: c = 0; if both = 1 -> count += 2
            #              if either one is 1 -> count += 1
            #              if neither is 1 -> count += 0
            else:
                count += (a & 1) + (b & 1)
            a >>= 1
            b >>= 1
            c >>= 1
        return count

# =============== Design ===============
class StockSpanner901:

    def __init__(self):
        self.stack = [] # stack store 2 elements: (price, count)
        # count is the number (to its left) smaller than or equal to the current stored price

    def next(self, price: int) -> int:
        count = 1
        # if current iterate number is greater than or equal to the price in stack 
        # -> add the count that's smaller than or equal to the price in stack 
        while self.stack and self.stack[-1][0] <= price:
            count += self.stack.pop()[1]
        self.stack.append([price, count])
        return count


# =============== Heap ===============
# 2542. Maximum Subsequence Score
class Solution2542:
    # T: O(NlogN)
    # S: O(N), because pairs matrix; and heap
    def maxScore(self, nums1, nums2, k):
        pairs = []
        for n1, n2 in zip(nums1, nums2):
            pairs.append((n1, n2))
        pairs.sort(key=lambda x:-x[1])
        top_k_heap = [x[0] for x in pairs[:k]] # sort pairs by the second element (nums2[i]) in decreasing order.
        top_k_sum = sum(top_k_heap)
        heapq.heapify(top_k_heap)
        result = top_k_sum * pairs[k-1][1] # pairs[k-1][1]: min of the selected elements from nums2.
        for n1, n2 in pairs[k:]:
            top_k_sum -= heapq.heappop(top_k_heap) # pop out the smallest element in nums1
            top_k_sum += n1
            heapq.heappush(top_k_heap, n1)
            result = max(result, top_k_sum * n2) # n2: min of the selected elements from nums2 (bc already sorted in descending way).
        return result

# 215. Kth Largest Element in an Array
class Solution215:
    # T: O(N) + O(KlogN)
    # S: O(N) + O(K)
    def findKthLargest(self, nums, k):
        min_heap = [-n for n in nums]
        heapq.heapify(min_heap) # TC: O(N)
        for _ in range(k): # TC: O(K)
            val = heapq.heappop(min_heap) # TC: O(logN)
        return val * (-1)

    # T: O(NlogN)
    # S: O(K)
    def findKthLargest(self, nums, k):
        min_heap = []
        for n in nums:
            heapq.heappush(min_heap, n)
            if len(min_heap) > k:
                heapq.heappop(min_heap)
        return min_heap[0]

    # heappushpop()
    # T: O(NlogN)
    # S: O(K)
    def findKthLargest(self, nums, k):
        min_heap = nums[:k]
        heapq.heapify(min_heap)
        for n in nums[k:]:
            heapq.heappushpop(min_heap, n)
        return min_heap[0]
        
# 373. Find K Pairs with Smallest Sums
class Solution373:
    # T: O(K)
    # S: O(K)
    def kSmallestPairs(self, nums1, nums2, k):
        min_heap = []
        result = []
        idx1 = 0
        idx2 = 0 
        m = len(nums1)
        n = len(nums2)
        heapq.heappush(min_heap, (nums1[idx1]+nums2[idx2], idx1, idx2))
        visited = set()
        visited.add((idx1,idx2))

        while len(result) < k and min_heap:
            total, idx1, idx2 = heapq.heappop(min_heap)
            result.append([nums1[idx1], nums2[idx2]])
            if idx1+1 < m and (idx1+1, idx2) not in visited:
                heapq.heappush(min_heap, (nums1[idx1+1]+nums2[idx2], idx1+1, idx2))
                visited.add((idx1+1, idx2))
            if idx2+1 < n and (idx1, idx2+1) not in visited:
                heapq.heappush(min_heap, (nums1[idx1]+nums2[idx2+1], idx1, idx2+1))
                visited.add((idx1, idx2+1))
        return result

# 502. IPO
class Solution502:
    def findMaximizedCapital(self, k, w, profits, capital):
        if w > max(capital):
            return w + sum(heapq.nlargest(k, profits))
        profit_max_heap = []
        min_heap = []
        # for c, p in zip(capital, profits):
        #     min_heap.append((c, p))
        # heapq.heapify(min_heap)
        # Insert all capitals to a min-heap
        capital_min_heap = [(capital[i], i) for i in range(len(capital))]
        heapq.heapify(capital_min_heap)
            
        # Finding 'k' best projects
        available_capital = w
        for _ in range(k):
            # Projects that can be selected within the available capital. Insert these in a max-heap
            while capital_min_heap and capital_min_heap[0][0] <= available_capital:
                needed_capital, idx = heapq.heappop(capital_min_heap)
                heapq.heappush(profit_max_heap, (-profits[idx], idx))
            # Break if we are not able to find any project that can be completed within the available capital
            if not profit_max_heap:
                break
            # Add the profit from project with the maximum profit
            available_capital += (-1) * heapq.heappop(profit_max_heap)[0]
        return available_capital

class MedianFinder:

    def __init__(self):
        self.small = [] # max heap
        self.large = [] # min heap

    def addNum(self, num: int) -> None:
        if not self.small or num < self.small[0]*(-1): 
            heapq.heappush(self.small, num*(-1))
        else:
            heapq.heappush(self.large, num)
        if len(self.small) >= len(self.large)+2:
            val = heapq.heappop(self.small)*(-1)
            heapq.heappush(self.large, val)
        elif len(self.small)+2 <= len(self.large):
            val = heapq.heappop(self.large)
            heapq.heappush(self.small, val*(-1))

    def findMedian(self) -> float:
        if len(self.small) == len(self.large):
            return (self.small[0]*(-1) + self.large[0])/2
        elif len(self.small) > len(self.large):
            return self.small[0] *(-1)
        else:
            return self.large[0]

# =============== Math ===============
# 9. Palindrome Number
class Solution9:
    # T: O(1)
    # S: O(N), bc of slicing
    def isPalindrome(self, x):
        if x < 0:
            return False
        x = str(x)
        return x == x[::-1]
    
    # 2 pointers
    # T: O(N)
    # S: O(1)
    def isPalindrome(self, x):
        if x < 0:
            return False
        x = str(x)
        n = len(x)
        l = 0
        r = n-1
        while l < r:
            if x[l] != x[r]:
                return False
            else:
                l += 1
                r -= 1
        return True
    
# 66. Plus One
class Solution66:
    # T: O(N)
    # S: O(N)
    def plusOne(self, digits):
        digits = digits[::-1]
        digits[0] += 1
        n = len(digits)
        for i in range(n):
            if digits[i] == 10:
                digits[i] = 0
                if i+1 < n:
                    digits[i+1] += 1
                else:
                    digits += [1]
    
        return digits[::-1]
    
    # for loop from head to end (change i to idx to iterate from end to head)
    def plusOne(self, digits):
        n = len(digits)
        for i in range(n):
            idx = n-1-i
            if digits[idx] == 9:
                digits[idx] = 0
            else:
                digits[idx] += 1
                return digits
    
        return [1] + digits
    
    # for loop from end to head
    def plusOne(self, digits):
        n = len(digits)
        for i in range(n-1, -1, -1):
            if digits[i] == 9:
                digits[i] = 0
            else:
                digits[i] += 1
                return digits
    
        return [1] + digits


