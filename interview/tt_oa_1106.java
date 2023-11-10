package interview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class tt_oa_1106 {
    // 0 1 knap sack problem
    public static long getMaxRequests(int[] bandwidth, int[] request, int total_bandwidth) {
        int n = request.length;
        long[][] dp = new long[n+1][total_bandwidth+1];

        for(int i = 0; i < n+1; i++) {
            for(int j = 0; j < total_bandwidth+1; j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if(bandwidth[i-1] <= j) {
                    dp[i][j] = Math.max(request[i-1] + dp[i-1][j - bandwidth[i-1]], dp[i-1][j]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[n][total_bandwidth];
    }

    public static boolean[] getMessageStatus(int[] time, String[] msg, int k) {
        Map<String, Integer> lastMsg = new HashMap<>();
        boolean[] status = new boolean[msg.length];

        for(int i = 0; i < msg.length; i++) {
            int lastT = lastMsg.getOrDefault(msg[i], -1);
            if(lastT == -1) {
                status[i] = true;
            } else if(time[i] - lastT > k) {
                status[i] = true;
            }

            lastMsg.put(msg[i], time[i]);
        }

        return status;
    }

    public static int maxEfficiency(int[] memory) {
        int n = memory.length;
        int mod = 1000000000 + 7;

        for(int i = 0; i < n/2; i++) {
            int x = memory[i];
            int y = memory[n-1-i];

            memory[i] = Math.min(x,y);
            memory[n-1-i] = Math.max(x,y);
        }

        int sum = 0;
        for(int i = 0; i < n; i++) {
            sum += multiply((i+1), memory[i]);
            sum %= mod;
        }

        return sum;
    }

    private static int multiply(int i, int mem) {
        if(i == 1 || mem == 1) return i*mem;
        
        int mod = 1000000000 + 7;
        if(mem%2 == 0) {
            return 2 * multiply(i, mem/2) % mod;
        } else {
            return (2 * multiply(i, mem/2) + i) % mod;
        }
        
    }

    public static void main(String[] args) {
        System.out.println(getMaxRequests(new int[]{100,200,500}, new int[]{5,5,5}, 1000));
        System.out.println(getMaxRequests(new int[]{100,500,80,25,400}, new int[]{100,1000,120,110,100}, 200));

        System.out.println(Arrays.toString(getMessageStatus(new int[]{1,1,1,11}, new String[]{"A","A","B","A"}, 5)));


        System.out.println(maxEfficiency(new int[]{2,4,1,3}));

    }
}
