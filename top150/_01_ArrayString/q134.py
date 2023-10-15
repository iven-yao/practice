class Solution:
    # T: O(N), S: O(1)
    def canCompleteCircuit(self, gas, cost):
        n = len(gas)
        cur_surplus = 0
        total_surplus = 0
        start = 0
        for i in range(n):
            cur_surplus += gas[i] - cost[i]
            total_surplus += gas[i] - cost[i]
            if cur_surplus < 0:
                cur_surplus = 0
                start = i+1
        return -1 if total_surplus < 0 else start
