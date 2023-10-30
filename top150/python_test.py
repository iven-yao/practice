
from python_solution import *

sol383 = Solution383()
sol205 = Solution205()
sol290 = Solution290()
sol242 = Solution242()
sol49 = Solution49()
sol48 = Solution48()
sol219 = Solution219()
sol228 = Solution228()
sol56 = Solution56()
sol57 = Solution57()
sol452 = Solution452()
sol2390 = Solution2390()
sol20 = Solution20()
sol71 = Solution71()
sol649 = Solution649()

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
    assert sol49.groupAnagrams(["eat","tea","tan","ate","nat","bat"]) == [['eat', 'tea', 'ate'], ['tan', 'nat'], ['bat']]
    # # assert sol49.groupAnagrams(["eat","tea","tan","ate","nat","bat"]) == [['eat', 'tea', 'ate'], ['tan', 'nat'], ['bat']]
    assert sol49.groupAnagrams([""]) == [[""]]
    assert sol49.groupAnagrams([]) == []
    assert sol49.groupAnagrams(["a"]) == [["a"]]

    assert sol228.summaryRanges([0,1,2,4,5,7]) == ["0->2","4->5","7"]
    assert sol228.summaryRanges([0,2,3,4,6,8,9]) == ["0","2->4","6","8->9"]
    assert sol56.merge([[1,3],[2,6],[8,10],[15,18]]) == [[1,6],[8,10],[15,18]]
    assert sol56.merge([[1,4],[4,5]]) == [[1,5]]
    assert sol57.insert([[1,3],[6,9]], [2,5]) == [[1,5],[6,9]]
    assert sol57.insert([[1,2],[3,5],[6,7],[8,10],[12,16]], [4,8]) == [[1,2],[3,10],[12,16]]
    assert sol452.findMinArrowShots([[10,16],[2,8],[1,6],[7,12]]) == 2
    assert sol452.findMinArrowShots([[1,2],[3,4],[5,6],[7,8]]) == 4
    assert sol452.findMinArrowShots([[1,2],[2,3],[3,4],[4,5]]) == 2
    assert sol2390.removeStars("leet**cod*e") == "lecoe"
    assert sol2390.removeStars("erase*****") == ""
    assert sol20.isValid("()") == True
    assert sol20.isValid("()[]{}") == True
    assert sol20.isValid("(]") == False
    assert sol71.simplifyPath("/home/") == "/home"
    assert sol71.simplifyPath("/../") == "/"
    assert sol71.simplifyPath("/home//foo/") == "/home/foo"

    assert sol649.predictPartyVictory("RD") == "Radiant"
    assert sol649.predictPartyVictory("RDD") == "Dire"
    assert sol649.predictPartyVictory("RRDDD") == "Radiant"
expected_matrix = [[7,4,1],[8,5,2],[9,6,3]]
input_matrix = [[1,2,3],[4,5,6],[7,8,9]]
def assert_test():
    sol48.rotate(input_matrix)
    assert input_matrix == [[7,4,1],[8,5,2],[9,6,3]]
    # for i in range(len(expected_matrix)):
    #     expected = expected_matrix[i]
    #     # print(expected, '>>>>', input_matrix[i])
    #     assert len(expected) == len(input_matrix[i])
    #     assert all([a == b for a, b in zip(expected, input_matrix[i])])

test()
assert_test()

assert expected_matrix == input_matrix
assert [[7,4,1],[8,5,2],[9,6,3]] == [[7,4,1],[8,5,2],[9,6,3]]

sol21 = Solution21()
list1 = ListNode(1)
list1.next = ListNode(2)
list1.next.next = ListNode(4)
list2 = ListNode(1)
list2.next = ListNode(3)
list2.next.next = ListNode(4)
assert sol21.mergeTwoLists(list1, list2)

sol141 = Solution141()
head = ListNode(3)
cycle = ListNode(2)
cycle.next = ListNode(0)
cycle.next.next = ListNode(-4)
cycle.next.next.next = cycle
head.next = cycle
assert sol141.hasCycle(head) == True
# sol48.rotate([[1,2,3],[4,5,6],[7,8,9]])
