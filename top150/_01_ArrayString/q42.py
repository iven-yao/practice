class Solution:
    def trap(self, height):
        l = 0
        r = len(height)-1
        maxL = 0
        maxR = 0
        result = 0
        while l < r:
            if height[l] < height[r]:
                if height[l] > maxL:
                    maxL = height[l]
                else:
                    result += maxL - height[l]
                l += 1
            else:
                if height[r] > maxR:
                    maxR = height[r]
                else:
                    result += maxR - height[r]
                r -= 1
        return result
