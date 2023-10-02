package top150.ArrayString;

import utils.util;

// 27. Remove Element
public class q27 {
    public int removeElement(int[] nums, int val) {
        int head = 0;
        int tail = nums.length-1;

        while(tail >= 0 && nums[tail] == val) {
            tail--;
        }

        while(head <= tail) {
            
            if(nums[head] == val) {
                nums[head] = nums[tail];
                nums[tail] = val;
                while(tail >= 0 && nums[tail] == val) {
                    tail--;
                }
            } 

            head++;
        }

        return head;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,2,2,3,0,4,2};

        new q27().removeElement(nums, 2);
        util.printArray(nums);
    }
}
