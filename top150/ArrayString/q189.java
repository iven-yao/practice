package top150.ArrayString;

import utils.util;

public class q189 {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        // for(int i = 0; i < k; i++) {
        //     rotateBy1(nums);
        // }

        // int[] copy = Arrays.copyOf(nums, nums.length);

        // for(int i = 0; i < nums.length; i++) {
        //     nums[i] = copy[(nums.length-k+i)%nums.length];
        // }

        int[] temp = new int[nums.length];

        for(int i = 0; i < k; i++) {
            temp[i] = nums[nums.length-k+i];
        }

        for(int i = k; i < nums.length; i++) {
            temp[i] = nums[i-k];
        }

        for(int i = 0; i < nums.length; i++) {
            nums[i] = temp[i];
        }


    }

    // public void rotateBy1(int[] nums) {
    //     int last = nums[nums.length-1];

    //     for(int i = nums.length-1; i > 0; i--) {
    //         nums[i] = nums[i-1];
    //     }

    //     nums[0] = last;
    // }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7};
        int k = 3;

        new q189().rotate(nums, k);

        util.printArray(nums);
    }
}
