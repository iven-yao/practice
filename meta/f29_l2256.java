package meta;

public class f29_l2256 {
    class Solution {
        public int minimumAverageDifference(int[] nums) {
            int n = nums.length;
            long sum = 0;
            // get prefix sum array
            for(int i = 0 ; i < n; i++) {
                sum += nums[i];
            }
    
            // get min avg diff
            long leftSum = 0;
            long rightSum = sum;
            long min = Integer.MAX_VALUE;
            int minId = 0;
            for(int i = 0; i < n; i++) {
                leftSum = leftSum + nums[i];
                rightSum = rightSum - nums[i];
    
                long left = leftSum / (i+1);
                long right = (i == n-1)? 0 : rightSum/(n-i-1);
    
                long avgDiff = Math.abs(left-right);
                if(min > avgDiff) {
                    minId = i;
                    min = avgDiff;
                }
            }
    
            return minId;
        }
    }
}
