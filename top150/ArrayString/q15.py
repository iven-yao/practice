class Solution:
    def threeSum(self, nums):
        nums.sort()  # [-4, -1, -1, 0, 1, 2]
        # print(nums)
        n = len(nums)
        result = []
        for i in range(n):
            if nums[i] > 0:
                break
            if i == 0 or nums[i] != nums[i-1]:
                self.twoSum(nums, i, result)
        return result

    def twoSum(self, nums, i, result):
        n = len(nums)
        j = i+1
        seen = set()
        while j < n:
            # print(i,j)
            # print(nums[i], nums[j])
            remain = -nums[i] - nums[j]
            # print(remain)
            # print(seen)
            if remain in seen:
                result.append([nums[i], remain, nums[j]])
                while j+1 < n and nums[j] == nums[j+1]:
                    j += 1
            seen.add(nums[j])
            j += 1
