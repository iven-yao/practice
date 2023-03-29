package leetcode;
import java.util.*;

public class q1402 {

    // calculate in reverse order, so the later(larger) element will be stacked up in each iteration and cause the coeff in ascending order
    // genius way to solve this
    class BetterSolution {
        public int maxSatisfaction(int[] satisfaction) {
            int max = 0;
            int n = satisfaction.length;
            Arrays.sort(satisfaction);
    
            int satTimeSum = 0, satSum = 0;
            for(int i = n-1; i >= 0; i--) {
                satSum += satisfaction[i];
                satTimeSum += satSum;
                max = Math.max(max, satTimeSum);
            }
    
            return max;
        }
    }

    class Solution {
        public int maxSatisfaction(int[] satisfaction) {
            int max = 0;
            int n = satisfaction.length;
    
            Arrays.sort(satisfaction);
            // System.out.println(Arrays.toString(satisfaction));
    
            for(int i = 0; i < n; i++) {
                int sum = 0;
                for(int j = i; j < n; j++) {
                    sum += (j-i+1) * satisfaction[j];
                }
                max = Math.max(max, sum);
            }
    
            return max;
        }
    }
}
