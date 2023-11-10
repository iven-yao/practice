package top150._17_KadanesAlgorithm;

public class q53 {
    public int maxSubArray(int[] nums) {
        int max_sum = Integer.MIN_VALUE;
        int cur_sum = 0;

        for(int num: nums) {
            cur_sum += num;
            if(cur_sum > max_sum) {
                max_sum = cur_sum;
            } 

            if(cur_sum < 0) {
                cur_sum = 0;
            }
        }

        return max_sum;
    }
}
