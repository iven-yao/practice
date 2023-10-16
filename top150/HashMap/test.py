
from solution import *

sol383 = Solution383()
sol205 = Solution205()
sol290 = Solution290()
sol242 = Solution242()
sol49 = Solution49()
sol48 = Solution48()
sol219 = Solution219()

def test():
    assert sol383.canConstruct('a', 'b') == False
    assert sol383.canConstruct('aa', 'ab') == False
    assert sol383.canConstruct('aa', 'aab') == True
    assert sol205.isIsomorphic('egg', 'add') == True
    assert sol205.isIsomorphic('foo', 'bar') == False
    assert sol205.isIsomorphic('paper', 'title') == True
    assert sol290.wordPattern('abba', 'dog cat cat dog') == True
    assert sol290.wordPattern('abba', 'dog cat cat fish') == False
    assert sol290.wordPattern('aaaa', 'dog cat cat dog') == False
    assert sol242.isAnagram('anagram', 'nagaram') == True
    assert sol242.isAnagram('rat', 'car') == False
    assert sol219.containsNearbyDuplicate([1,2,3,1], 3) == True
    assert sol219.containsNearbyDuplicate([1,0,1,1], 1) == True
    assert sol219.containsNearbyDuplicate([1,2,3,1,2,3], 2) == False
    # assertEqual(sol49.groupAnagrams(["eat","tea","tan","ate","nat","bat"]), [['eat', 'tea', 'ate'], ['tan', 'nat'], ['bat']])
    # # assert sol49.groupAnagrams(["eat","tea","tan","ate","nat","bat"]) == [['eat', 'tea', 'ate'], ['tan', 'nat'], ['bat']]
    # assert sol49.groupAnagrams([""]) == [[""]]
    # assert sol49.groupAnagrams(["a"]) == [["a"]]

expected_matrix = [[7,4,1],[8,5,2],[9,6,3]]
input_matrix = [[1,2,3],[4,5,6],[7,8,9]]
def assert_test():
    sol48.rotate(input_matrix)
    for i in range(len(expected_matrix)):
        expected = expected_matrix[i]
        # print(expected, '>>>>', input_matrix[i])
        assert len(expected) == len(input_matrix[i])
        assert all([a == b for a, b in zip(expected, input_matrix[i])])
test()
assert_test()