package top150.ArrayString;
// 55. Jump Game
public class q55 {
    public boolean canJump(int[] nums) {
        int farthest = 0;
        for(int i = 0; i <= farthest; i++) {
            farthest = Math.max(farthest, i+nums[i]);
            if(farthest >= nums.length-1) return true;
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,3,1,1,4};

        boolean res = new q55().canJump(nums);
        System.out.println(res);
    }
}
