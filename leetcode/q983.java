package leetcode;

public class q983 {
    class Solution {
        public int mincostTickets(int[] days, int[] costs) {
            int maxday = days[days.length-1];
            int[] dp = new int[maxday+1];
            dp[0] = 0;
    
            int index = 0;
            for(int i = 1; i < maxday+1; i++) {
                if(days[index] == i) {
                    int buy1 = dp[i-1] + costs[0];
                    int buy7 = (i-7 >= 0)? dp[i-7]+costs[1] : costs[1];
                    int buy30 = (i-30 >= 0)? dp[i-30]+costs[2]: costs[2];
                    dp[i] = Math.min(buy1, Math.min(buy7, buy30));
                    index++;
                } else {
                    dp[i] = dp[i-1];
                }
    
            }
    
            return dp[maxday];
        }
    }
}
