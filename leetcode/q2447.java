package leetcode;

public class q2447 {
    public int subarrayGCD(int[] nums, int k) {
        int count = 0;

        for(int l = 0; l < nums.length; l++) {
            int gcd = nums[l];
            if(gcd == k) count++;
            for(int r = l+1; r < nums.length; r++) {
                gcd = gcd(nums[r], gcd);
                if(gcd == k) count++;
                if(gcd < k) break;
            }
        }

        return count;
    }

    public int gcd(int a, int b) {
        return (b==0)?a:gcd(b, a%b);
    }
}
