package top150._17_KadanesAlgorithm;

public class q918 {
    public int maxSubarraySumCircular(int[] nums) {
        int max_sum = Integer.MIN_VALUE;
        int min_sum = Integer.MAX_VALUE;
        int cur_sum = 0;
        int cur_sum2 = 0;
        int total = 0;

        for(int num: nums) {
            // total
            total += num;

            // max sum
            cur_sum += num;
            if(cur_sum > max_sum) {
                max_sum = cur_sum;
            } 

            if(cur_sum < 0) {
                cur_sum = 0;
            }

            // min sum
            cur_sum2 += num;
            if(cur_sum2 < min_sum) {
                min_sum = cur_sum2;
            }

            if(cur_sum2 > 0) {
                cur_sum2 = 0;
            }
        }

        // if the array consists only negative element
        if(total == min_sum) return max_sum;

        return Math.max(max_sum, total-min_sum);
    }
}
