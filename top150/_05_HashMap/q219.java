package top150._05_HashMap;

import java.util.HashMap;
import java.util.Map;

public class q219 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> former = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {
            if(former.containsKey(nums[i])) {
                if(i-former.get(nums[i]) <= k) return true;
            }

            former.put(nums[i], i);
        }

        return false;
    }
}
