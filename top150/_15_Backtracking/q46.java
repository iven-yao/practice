package top150._15_Backtracking;

import java.util.ArrayList;
import java.util.List;

public class q46 {
    class Solution {
        List<List<Integer>> res;
        public List<List<Integer>> permute(int[] nums) {
            res = new ArrayList<>();
            helper(new ArrayList<>(), nums);
            return res;
        }

        private void helper(List<Integer> per, int[] nums) {
            if(per.size() == nums.length) {
                res.add(new ArrayList<>(per));
                return;
            }

            for(int i = 0; i < nums.length; i++) {
                if(nums[i] != -5566) {
                    int tmp = nums[i];
                    per.add(tmp);
                    nums[i] = -5566;
                    helper(per, nums);
                    nums[i] = tmp;
                    per.remove(per.size()-1);
                }
            }
        }
    }
}
