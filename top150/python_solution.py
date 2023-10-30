import collections
from collections import defaultdict
import bisect
from collections import deque
import math

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

#
class Solution:
    def solve(self, board):
        """
        Do not return anything, modify board in-place instead.
        """
        m = len(board)
        n = len(board[0])
        q = deque()
        for i in range(m):
            for j in [0, n-1]:
                if board[i][j] == 'O':
                    board[i][j] = 'B'
                    q.append([i,j])
        for i in [0, m-1]:
            for j in range(1, n-1):
                if board[i][j] == 'O':
                    board[i][j] = 'B'
                    q.append([i, j])
        dirs = [(1,0), (-1,0), (0,1), (0,-1)]
        while q:
            x, y = q.popleft()
            for dx, dy in dirs:
                new_x = x + dx
                new_y = y + dy
                if 0 <= new_x < m and 0 <= new_y < n and board[new_x][new_y] == 'O':
                    board[new_x][new_y] = 'B'
                    q.append([new_x, new_y])

        for i in range(m):
            for j in range(n):
                if board[i][j] == 'B':
                    board[i][j] = 'O'
                elif board[i][j] == 'O':
                    board[i][j] = 'X'

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
        if root == p or root == q:
            return root

        left = right = None
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
            else:
                cur_total = 10*cur_total + node.val
                if not node.left and not node.right: # bottom leaf node
                    result += cur_total
                    return
                else:
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
    
# 114. Flatten Binary Tree to Linked List
class Solution114:
    # Use self.prev to recode the ordered tree of the right part of current node.
    # Remove the left part of current node  
    '''
       root
       / 
      1 
     / \ 
    3  4  
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
        root.right = self.prev
        root.left = None
        self.prev = root

# 124. Binary Tree Maximum Path Sum
class Solution124:
    def maxPathSum(self, root):
        if not root:
            return 0
        result = -float('inf')
        def dfs(node):
            nonlocal result
            if not node:
                return 0
            left_max = max(0, dfs(node.left))
            right_max = max(0, dfs(node.right))

            result = max(result, node.val + left_max + right_max)
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

# 1926. Nearest Exit from Entrance in Maze
class Solution:
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


# =============== Binary Search ===============
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

