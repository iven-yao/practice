package leetcode;

public class q413 {
    class Solution {
        public int numberOfArithmeticSlices(int[] nums) {
            int n = nums.length;
            if(n < 3) return 0;
            int[] diff = new int[n-1];
            for(int i=1; i <n; i++) {
                diff[i-1] = nums[i] - nums[i-1];
            }
    
            int currDiff = diff[0];
            int len = 2;
            int total = 0;
    
            for(int i=1; i<n-1; i++) {
                if(diff[i] == currDiff) {
                    len++;
                    total += (len-2);
                } else {
                    currDiff = diff[i];
                    len = 2;
                }
            }
    
            return total;
        }
    }
}
