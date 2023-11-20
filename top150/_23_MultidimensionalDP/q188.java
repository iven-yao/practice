package top150._23_MultidimensionalDP;

import java.util.Arrays;

public class q188 {
    public static int maxProfit(int k, int[] prices) {
        /**
            dp[i][j] = max(case1, case2)
            case1: dp[i][j-1] (The profit is same as previous day).
            case2: max(prices[j] - prices[i] + dp[i-1][j-1]), for i=[0..j] (The best buying day from i-1 transactions).
         */
        int n = prices.length;
        if (n <= 1)
            return 0;
        
        int[][] dp = new int[k+1][n];
        for (int i = 1; i <= k; i++) {
            int localMax = dp[i-1][0] - prices[0]; 
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j-1],  prices[j] + localMax); // max(not sell, sell on day j)
                localMax = Math.max(localMax, dp[i-1][j] - prices[j]); // update best buy time
            }
        }

        // debug:
        for(int[] row: dp) {
            System.out.println(Arrays.toString(row));
        }

        return dp[k][n-1];
    }

    public static void main(String[] args) {
        int[] testcase = new int[]{2,3,5,1,4,4,2,4,2,3,1,4,1,3,3};
        System.out.println(Arrays.toString(testcase));
        System.out.println();
        System.out.println(maxProfit(3, testcase));
    }
}
