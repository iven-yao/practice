import collections
from collections import defaultdict

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
            for col in range(left, right+1):
                result.append(matrix[top][col])
            top += 1
            for row in range(top, down+1):
                result.append(matrix[row][right])
            right -= 1
            if left > right or top > down:
                break
            for col in range(right, left-1, -1):
                result.append(matrix[down][col])
            down -= 1
            for row in range(down, top-1, -1):
                result.append(matrix[row][left])
            left += 1
        return result
    
# 48. Rotate Image
class Solution:
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
        
        for j in range(n):
            if matrix[0][j] == 0:
                for i in range(m):
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
        print(d.values())
        return d.values()


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
    def isHappy(self, n):
        def get_next(n):
            total=0
            while n!=0:
                digits = n%10
                n = n // 10
                total += digits * digits
            return total

        seen = set()
        while n!=1 and n not in seen:
            seen.add(n)
            n = get_next(n)

        return n==1

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
    
# sol = Solution49()
# print(sol.groupAnagrams(["eat","tea","tan","ate","nat","bat"]))