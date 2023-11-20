package top150._22_1DDP;

import java.util.Arrays;

public class q322 {
    public int coinChange(int[] coins, int amount) {
        // dp[i] = for n coin, dp[i-n[j]] + 1 otherwise -1
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;

        for(int coin: coins) {
            for(int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i-coin]+1, dp[i]);
            }
        }


        return dp[amount] == amount+1? -1: dp[amount];
    }
}
