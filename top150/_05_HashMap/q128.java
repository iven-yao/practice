package top150._05_HashMap;

import java.util.HashSet;
import java.util.Set;

public class q128 {

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for(int ele: nums) set.add(ele);

        int longest = 0;
        for(int ele: nums) {
            if(!set.contains(ele-1)) {
                int len = 0;
                while(set.contains(ele)) {
                    len++;
                    set.remove(ele++);
                }
                longest = Math.max(longest, len);
            }
        }

        return longest;
    }
}
