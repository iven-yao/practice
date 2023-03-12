package leetcode;

import java.util.Arrays;

public class q204 {
    public int countPrimes(int n) {
        if(n <= 2) return 0;
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        isPrime[0] = 0;
        isPrime[1] = 0;

        int count = 0;
        for(int i = 2; i < n; i++) {
            if(isPrime[i] == 1) {
                count++;
                int crossOut = i+i;
                while(crossOut < n) {
                    isPrime[crossOut] = 0;
                    crossOut += i;
                }
            }
        }

        return count;
    }
}
