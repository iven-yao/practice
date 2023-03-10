package leetcode;
import java.util.*;

public class q398 {
    class Solution {

        Map<Integer, List<Integer>> map;
        Random rand;
    
        public Solution(int[] nums) {
            map = new HashMap<>();
            rand = new Random();
    
            for(int i = 0; i < nums.length; i++) {
                map.computeIfAbsent(nums[i], v -> new ArrayList<Integer>());
                map.get(nums[i]).add(i);
            }
        }
        
        public int pick(int target) {
            List<Integer> list = map.get(target);
            return list.get(rand.nextInt(list.size()));
        }
    }
}
