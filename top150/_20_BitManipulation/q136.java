package top150._20_BitManipulation;

public class q136 {
    public int singleNumber(int[] nums) {
        int ans = 0;

        for(int num: nums) {
            ans ^= num;
        }

        return ans;
    }
}
