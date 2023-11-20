package top150._22_1DDP;

import java.util.Arrays;

public class q300 {
    public int lengthOfLIS(int[] nums) {
        // for n = 1~i, if(nums[i] > nums[i-n]) dp[i] = dp[i-n]+1;
        
        int len = nums.length;
        int[] dp = new int[len];
        int max = 1;

        Arrays.fill(dp, 1);

        for(int i = 1; i < len; i++) {
            for(int n = 1; n <= i; n++) {
                if(nums[i] > nums[i-n])
                    dp[i] = Math.max(dp[i], dp[i-n]+1);
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
