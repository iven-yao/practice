package top150._23_MultidimensionalDP;

public class q221 {
    public int maximalSquare(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int max = 0;

        int[][] dp = new int[n][m];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                dp[i][j] = matrix[i][j] == '1'? 1:0;
                max = Math.max(max, dp[i][j]);
            }
        }

        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                if(dp[i][j] != 0)
                    dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1]), dp[i-1][j-1]) + 1;

                max = Math.max(max, dp[i][j]);
            }
        }

        return max*max;
    }
}
