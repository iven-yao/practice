package meta;

import java.util.HashSet;
import java.util.Set;

public class f23_l523 {
    class Solution {
        public boolean checkSubarraySum(int[] nums, int k) {
            if(nums == null) return false;

            int prefixMod = 0;
            Set<Integer> seen = new HashSet<>();
            int prev = 0;
            for(int num: nums) {
                prefixMod = (num+prefixMod)%k;
                if(seen.contains(prefixMod)) return true;
                seen.add(prev);
                prev = prefixMod;
            }

            return false;
        }
    }
}
