package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class q1630 {
    class Solution {
        public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
            int n = l.length;
            List<Boolean> res = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                res.add(helper(Arrays.copyOfRange(nums, l[i], r[i]+1)));
            }
    
            return res;
        }
    
        public boolean helper(int[] arr) {
            if(arr.length <= 2) return true;
            Arrays.sort(arr);
            int diff = arr[1] - arr[0];
            for(int i = 2; i < arr.length; i++) {
                if(arr[i] - arr[i-1] != diff) return false;
            }
    
            return true;
        }
    }
}
