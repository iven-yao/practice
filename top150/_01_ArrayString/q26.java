package top150._01_ArrayString;

import utils.util;

// 26. Remove Duplicates from Sorted Array
public class q26 {
    public int removeDuplicates(int[] nums) {
        if(nums.length <2) return nums.length;
        
        int index=1;
        for(int i=1; i< nums.length; i++) {
            if(nums[i] != nums[i-1]){
                nums[index++] = nums[i];
            }
        }
        
        return index;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,0,1,1,1,2,2,3,3,4};

        new q26().removeDuplicates(nums);
        util.printArray(nums);
    }
}
