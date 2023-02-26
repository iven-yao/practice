package leetcode;

public class q2574 {
    public int[] leftRigthDifference(int[] nums) {
        int n = nums.length;
        int[] leftSum = new int[n];
        int[] rightSum = new int[n];
        
        int sum = 0;
        for(int i = 0; i < n; i++) {
            leftSum[i] = sum;
            sum += nums[i];
        }
        
        sum = 0;
        for(int i = n-1; i >= 0; i--) {
            rightSum[i] = sum;
            sum += nums[i];
        }
        
        int[] ans = new int[n];
        for(int i = 0; i < n; i++) {
            ans[i] = leftSum[i] >= rightSum[i] ? leftSum[i]-rightSum[i] : rightSum[i]-leftSum[i]; 
        }
        
        return ans;
    }
}
