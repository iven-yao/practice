import collections
from collections import defaultdict
import bisect
from collections import deque
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
    # T: O(N)
    # S: O(1)
    def summaryRanges(self, nums):
        n = len(nums)
        result = []
        if n == 0:
            return result
        l = 0
        r = 0
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

class Solution452:
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

# =============== Binary Tree ===============

# Definition for a binary tree node.
class TreeNode: #  This class defines a simple binary tree node structure with three attributes:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution: # This class contains the method for finding the lowest common ancestor of two nodes in a binary tree.
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
        