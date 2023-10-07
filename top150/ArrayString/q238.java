package top150.ArrayString;

public class q238 {
    public int[] productExceptSelf(int[] nums) {
        int zeroCnt = 0;
        int product = 1;
        int productWithOutZero = 1;

        int[] res = new int[nums.length];

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                zeroCnt++;
                if(zeroCnt > 1) return res;
            } else {
                productWithOutZero *= nums[i];
            }

            product *= nums[i];
        }

        for(int i = 0; i < nums.length; i++) {
            if(zeroCnt == 1 && nums[i] == 0) {
                res[i] = productWithOutZero;
            } else {
                res[i] = product/nums[i];
            }
        }

        return res;
    }
}
