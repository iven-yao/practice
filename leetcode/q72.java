package leetcode;

public class q72 {
    public static final int[][] DIRS = new int[][]{{-1, 0}, {-1,-1}, {0, -1}}; 

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1];

        for(int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        for(int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    int min = Integer.MAX_VALUE;
                    for(int[] dir: DIRS) {
                        int ii = i + dir[0];
                        int jj = j + dir[1];
                        min = Math.min(dp[ii][jj], min);
                    }

                    dp[i][j] = min+1;
                }

                // System.out.print(dp[i][j] + "\t");
            }
            // System.out.println();
        }

        

        return dp[m][n];
    }
}
