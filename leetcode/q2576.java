package leetcode;
import java.util.*;

public class q2576 {
    public int maxNumOfMarkedIndices(int[] nums) {
        
        Arrays.sort(nums);
        int n = nums.length;
        int l = 0, r;
        if(n % 2 == 0) {
            r = n/2;
        } else {
            r = n/2+1;
        }
        
        int ans = 0;        
        while(l < n/2 && r < n) {
            if(nums[l]*2 <= nums[r]){ 
                ans+=2;
                l++;
            }
            r++;
        }
        
        
        return ans;
    }
}
