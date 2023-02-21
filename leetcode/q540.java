package leetcode;

public class q540 {
    public int singleNonDuplicate(int[] nums) {
        return helper(nums, 0, nums.length-1);
    }

    public int helper(int[] nums, int l, int r) {
        int i = (l+r)/2;

        boolean pre = (i > 0)? nums[i]==nums[i-1]: false;
        boolean post = (i < nums.length-1)? nums[i]==nums[i+1]: false;

        if(!pre && !post) return nums[i];
        else if(pre) {
            if(((i-1)-l)%2 == 0) {
                return helper(nums, i+1, r);
            } else {
                return helper(nums, l, i-2);
            }
        } else {
            if((i-l)%2 == 0) {
                return helper(nums, i+2, r);
            } else {
                return helper(nums, l, i-1);
            }
        }
    }

    public static void main(String[] args) {
        q540 q = new q540();

        System.out.println(q.singleNonDuplicate(new int[]{1,1,2,3,3,4,4,8,8}));
    }
}
