package leetcode;

public class q1035 {
    class Solution {
        public int maxUncrossedLines(int[] nums1, int[] nums2) {
            int n = nums1.length;
            int m = nums2.length;
            int[][] dp = new int[n][m];
    
            for(int j = 0; j < m; j++) {
                if(nums1[0] == nums2[j]) {
                    dp[0][j] = 1;
                } else if(j > 0) {
                    dp[0][j] = dp[0][j-1];
                }
            }
    
            for(int i = 0; i < n; i++) {
                if(nums1[i] == nums2[0]) {
                    dp[i][0] = 1;
                } else if(i > 0) {
                    dp[i][0] = dp[i-1][0];
                }
            }
    
            for(int i = 1; i < n; i++) {
                for(int j = 1; j < m; j++) {
                    if(nums1[i] == nums2[j]) {
                        dp[i][j] = dp[i-1][j-1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                    }
                }
            }
    
            return dp[n-1][m-1];
        }
    }
}
