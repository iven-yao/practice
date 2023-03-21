package leetcode;

public class q2348 {
    public long zeroFilledSubarray(int[] nums) {
        long count = 0;
        int l = -1;
        int r = 0;
        while(r < nums.length) {
            if(nums[r] == 0) {
                count += (r-l);
            } else {
                l = r;
            }
            r++;
        }

        return count;
    }
}
