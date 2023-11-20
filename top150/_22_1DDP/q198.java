package top150._22_1DDP;

public class q198 {
    public int rob(int[] nums) {
        // dp(i) = max(n[i] + dp[i-2], dp[i-1]);
        int n = nums.length;
        int[] dp = new int[n+1];

        dp[0] = 0;
        dp[1] = nums[0];

        for(int i = 2; i <= n; i++) {
            dp[i] = Math.max(nums[i-1] + dp[i-2], dp[i-1]);
        }

        return dp[n];
    }
}
