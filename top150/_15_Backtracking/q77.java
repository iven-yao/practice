package top150._15_Backtracking;

import java.util.ArrayList;
import java.util.List;

public class q77 {
    class Solution {
        List<List<Integer>> res;
        public List<List<Integer>> combine(int n, int k) {
            res = new ArrayList<>();
            helper(new ArrayList<>(), 1, n, k);
            return res;
        }

        private void helper(List<Integer> com, int curr, int end, int len) {
            if(com.size() == len) {
                res.add(new ArrayList<>(com));
                return;
            }

            for(int i = curr; i <= end; i++) {
                com.add(i);
                helper(com, i+1, end, len);
                com.remove(com.size()-1);
            }
        }
    }
}
