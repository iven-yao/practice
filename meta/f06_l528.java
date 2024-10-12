package meta;

import java.util.Random;

public class f06_l528 {
    class Solution {
        Random rand;
        int[] wPrefixSum;
        public Solution(int[] w) {
            rand = new Random();

            for(int i = 1; i < w.length; i++) {
                w[i] += w[i-1];
            }

            wPrefixSum = w;
        }
        
        public int pickIndex() {
            //binarySearch;
            int n  = wPrefixSum.length;
            int bound = wPrefixSum[n-1];
            int random = rand.nextInt(bound)+1;

            int l = 0;
            int r = n;

            while(l < r) {
                int mid = l + (r-l)/2;
                
                if(wPrefixSum[mid] > random) {
                    r = mid;
                } else if(wPrefixSum[mid] < random) {
                    l = mid+1;
                } else {
                    return mid;
                }
            }

            return r;
        }
    }
}
