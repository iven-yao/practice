package top150._01_ArrayString;

public class q45 {
    public int jump(int[] nums) {
        if(nums.length < 2) return 0;

        int[] reachable = helper(nums);
        int jumpsNeeded = 1;
        int prevFurthest = 0;
        int furthest = reachable[0];

        while(furthest < nums.length - 1) {
            int start = prevFurthest;
            int end = furthest;
            prevFurthest = furthest;
            jumpsNeeded++;
            for(int i = start; i <= end; i++) {
                furthest = Math.max(furthest, reachable[i]);
            }
            
        }

        return jumpsNeeded;

    }

    public int[] helper(int[] nums) {
        int[] reachable = new int[nums.length];

        for(int i = 0; i < nums.length; i++) {
            reachable[i] = Math.min(nums.length-1, i+nums[i]);
        } 

        return reachable;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,1};

        int res = new q45().jump(nums);
        System.out.println(res);
    }
}
