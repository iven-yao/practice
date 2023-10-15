package top150._05_HashMap;

import java.util.HashMap;
import java.util.Map;

public class q1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {
            int index = map.getOrDefault(nums[i], -1);
            if(index != -1) return new int[]{index, i};
            map.put(target-nums[i], i);
        }

        return new int[2];
    }
}
