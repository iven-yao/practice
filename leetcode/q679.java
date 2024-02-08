package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class q679 {
    class Solution {
        public boolean judgePoint24(int[] cards) {
            List<Integer> arr = new ArrayList<>();
            for(int card: cards) arr.add(card);
            return helper(arr);
        }

        private boolean helper(List<Integer> arr) {
            if(arr.size() == 1) {
                if(arr.get(0) == 24) return true;
                return false;
            }
            
            boolean res = false;
            for(int i = 0; i < arr.size(); i++) {
                for(int j = 0; j < i; j++) {
                    List<Integer> next = new ArrayList<>();
                    int card1 = arr.get(i);
                    int card2 = arr.get(j);

                    next.addAll(Arrays.asList(card1+card2, card1-card2, card2-card1, card1*card2));
                    if(card1 > 0 && card2%card1 == 0) next.add(card2/card1);
                    if(card2 > 0 && card1%card2 == 0) next.add(card1/card2);

                    arr.remove(i);
                    arr.remove(j);

                    for(Integer card: next) {
                        arr.add(card);
                        res = res || helper(arr);
                        arr.remove(arr.size()-1);
                    }

                    arr.add(j, card2);
                    arr.add(i, card1);
                }
            }

            return res;
        }
    }
}
