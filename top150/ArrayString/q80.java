package top150.ArrayString;

import utils.util;

// 80. Remove Duplicates from Sorted Array II
public class q80 {
    public int removeDuplicates(int[] nums) {
        int len = 1;
        int prev = nums[0];
        int duplicate = 1;

        for(int curr = 1; curr < nums.length; curr++) {
            if(nums[curr] == prev) {
                duplicate++;
            } else {
                prev = nums[curr];
                duplicate = 1;
            }

            if(duplicate <= 2) {
                nums[len] = nums[curr];
                len++;
            }
        }

        return len;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,0,1,1,1,1,2,3,3};

        new q80().removeDuplicates(nums);
        util.printArray(nums);
    }
}
