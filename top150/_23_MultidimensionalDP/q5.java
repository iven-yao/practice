package top150._23_MultidimensionalDP;

public class q5 {
    public String longestPalindrome(String s) {
        // dp(i, j) = true if dp(i+1, j-1) && s[i] == s[j]
        int n = s.length();
        if(n <= 1) return s;

        int start = 0, end = 0, maxLen = 1;
        boolean[][] dp = new boolean[n][n];

        for(int i = 0; i < n; i++) {
            dp[i][i] = true; // base case
            for(int j = 0; j < i; j++) {
                dp[j][i] = (s.charAt(i) == s.charAt(j)) && (i-j == 1 || dp[j+1][i-1]);
                if(dp[j][i]) {
                    if(i-j+1 > maxLen) {
                        maxLen = i-j+1;
                        start = j;
                        end = i;
                    }
                }
            }
        }

        return s.substring(start, end+1);
    }
}
