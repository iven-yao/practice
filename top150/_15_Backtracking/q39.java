package top150._15_Backtracking;

import java.util.ArrayList;
import java.util.List;

public class q39 {
    class Solution {
        List<List<Integer>> res;
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            res = new ArrayList<>();
            // Arrays.sort(candidates);
            helper(new ArrayList<>(), 0, 0, candidates, target);
            return res;
        }

        private void helper(List<Integer> com, int sum, int idx, int[] cand, int target) {
            if(sum > target) return;

            if(sum == target) {
                res.add(new ArrayList<>(com));
                return;
            }

            for(int i = idx; i < cand.length; i++) {
                sum += cand[i];
                com.add(cand[i]);
                helper(com, sum, i, cand, target);
                com.remove(com.size()-1);
                sum-= cand[i];
            }
        }
    }    
}
