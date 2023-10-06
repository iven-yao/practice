package leetcode;

public class q343 {
    public int integerBreak(int n) {
        if(n <= 3) {
            return n-1;
        }

        int[] dp = new int[n+1];

        for(int i = 1; i <= n; i++) {
            if(i < 4) dp[i] = i;
            else {
                int ans = i;
                for(int j = 2; j < i; j++) {
                    ans = Math.max(ans, j * dp[i - j]);
                }

                dp[i] = ans;
            }

        }

        return dp[n];
    }
}
