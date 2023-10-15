package top150._01_ArrayString;

import java.util.Arrays;

public class q238 {
    
    // with O(1) space
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, 1);
        int front = 1;
        for(int i = 0; i < n; i++) {
            res[i] *= front;
            front *= nums[i];
        }

        int back = 1;
        for(int i = n-1; i >= 0; i--) {
            res[i] *= back;
            back *= nums[i];
        }

        return res;
    }

    // with out division
    // public int[] productExceptSelf(int[] nums) {
    //     int n = nums.length;
    //     int[] front = new int[n];
    //     int[] back = new int[n];

    //     front[0] = nums[0];
    //     for(int i = 1; i < n; i++) {
    //         front[i] = front[i-1] * nums[i];
    //     }

    //     back[n-1] = nums[n-1];
    //     for(int i = n-2; i>=0; i--) {
    //         back[i] = back[i+1] * nums[i];
    //     }

    //     int[] res = new int[n];
    //     res[0] = back[1];
    //     res[n-1] = front[n-2];
    //     for(int i = 1; i < n-1; i++) {
    //         res[i] = front[i-1]*back[i+1];
    //     }

    //     return res;

    // }

    // shouldn't use division operation
    // public int[] productExceptSelf(int[] nums) {
    //     int zeroCnt = 0;
    //     int product = 1;
    //     int productWithOutZero = 1;

    //     int[] res = new int[nums.length];

    //     for(int i = 0; i < nums.length; i++) {
    //         if(nums[i] == 0) {
    //             zeroCnt++;
    //             if(zeroCnt > 1) return res;
    //         } else {
    //             productWithOutZero *= nums[i];
    //         }

    //         product *= nums[i];
    //     }

    //     for(int i = 0; i < nums.length; i++) {
    //         if(zeroCnt == 1 && nums[i] == 0) {
    //             res[i] = productWithOutZero;
    //         } else {
    //             res[i] = product/nums[i];
    //         }
    //     }

    //     return res;
    // }
}
