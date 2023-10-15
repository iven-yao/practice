package top150._02_TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class q15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length-2; i++) {
            int head = i+1;
            int tail = nums.length-1;
            while(head < tail) {
                int addup = nums[i] + nums[head] + nums[tail];
                if(addup == 0) {
                    set.add(Arrays.asList(nums[i], nums[head], nums[tail]));
                    while(head < tail && nums[head] == nums[head+1]) head++;
                    while(head < tail && nums[tail] == nums[tail-1]) tail--;
                    head++;
                    tail--;
                }
                if(addup < 0) head++;
                if(addup > 0) tail--;
            }

        }
        
        res.addAll(set);
        return res;
    }
}
