package leetcode;

public class q1822 {
    class Solution {
        public int arraySign(int[] nums) {
            int neg = 0;
            for(int num: nums) {
                if(num < 0) neg++;
                if(num == 0) return 0;
            }
    
            return neg%2==0 ? 1: -1;
        }
    }
}
