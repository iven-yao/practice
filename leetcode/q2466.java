package leetcode;

public class q2466 {
    class Solution {
        public int countGoodStrings(int low, int high, int zero, int one) {
            int mod = 1000000007;
            int[] dp = new int[high+1];
            dp[0] = 1;
            int ans = 0;
    
            for(int i = 1; i <= high; i++) {
                if(i >= zero) {dp[i] = (dp[i-zero]+dp[i])%mod;}
                if(i >= one) {dp[i] = (dp[i-one]+dp[i])%mod;}
                if(i >= low) {ans = (ans + dp[i])%mod;}
            }
    
            return ans;
        }
    }
}
