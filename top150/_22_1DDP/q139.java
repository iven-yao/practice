package top150._22_1DDP;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class q139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        // dp[i] = true, if dp[i-n] && wordDict.contains(s.substring(i-n, i));

        Set<String> dict = new HashSet<>();
        int maxlen = 0;
        int len = s.length();
        for(String word: wordDict) {
            dict.add(word);
            maxlen = Math.max(word.length(), maxlen);
        }

        boolean[] dp = new boolean[len+1];
        dp[0] = true;

        for(int i = 1; i <= len; i++) {
            for(int j = 1; j <= i && j <= maxlen; j++) {
                // System.out.println(s.substring(i-j, i));
                dp[i] = dp[i] || (dp[i-j] && dict.contains(s.substring(i-j, i)));
            }
            // System.out.println(Arrays.toString(dp));
        }

        return dp[len];

    }
}
