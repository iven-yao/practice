package top150._23_MultidimensionalDP;

import java.util.Arrays;
import java.util.List;

public class q120 {
    public int minimumTotal(List<List<Integer>> triangle) {
        // dp(index, layer) = Math.min(dp(index, layer-1), dp(index-1, layer-1)) + value;
        
        int n = triangle.size();
        int[][] dp = new int[n][n+1];
        // fill with max value to avoid boundary case
        for(int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        // base cases
        dp[0][0] = triangle.get(0).get(0);
        for(int i = 1; i < n; i++) {
            dp[i][0] = dp[i-1][0] + triangle.get(i).get(0);
        }

        // fill up dp table
        for(int i = 1; i < n; i++) {
            for(int j = 1; j <= i; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-1]) + triangle.get(i).get(j);
            }
        }

        // find minimum path sum
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            min = Math.min(min, dp[n-1][i]);
        }

        return min;
    }
}
