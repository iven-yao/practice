package leetcode;

public class q34 {
    public int[] searchRange(int[] nums, int target) {
        if(nums.length == 0) return new int[]{-1,-1};
        return new int[]{lowerBound(nums, target, 0, nums.length-1), upperBound(nums, target, 0, nums.length-1)};
    }

    public int lowerBound(int[] nums, int target, int left, int right) {
        int bound = -1;
        while(left <= right) {
            int index = (left+right)/2;
            if(nums[index] == target) {
                bound = index;
                right = index-1;
            } else if(nums[index] > target) {
                right = index-1;
            } else left = index+1;
        }

        return bound;
    }

    public int upperBound(int[] nums, int target, int left, int right) {
        int bound = -1;
        while(left <= right) {
            int index = (left+right)/2;
            if(nums[index] == target) {
                bound = index;
                left=index+1;
            } else if(nums[index] > target) {
                right = index-1;
            } else left = index+1;
        }

        return bound;
    }

    public static void main(String[] args) {
        q34 q = new q34();
        
        int[] nums = {5,7,7,8,8,10};
        int target = 8;

        int[] res = q.searchRange(nums, target);

        System.out.println("["+res[0]+","+res[1]+"]");
    }
}
