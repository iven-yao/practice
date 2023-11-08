package top150._15_Backtracking;

import java.util.ArrayList;
import java.util.List;

public class q17 {
    int[] start = new int[]{0,3,6,9,12,15,19,22,26};
    List<String> res;

    public List<String> letterCombinations(String digits) {
        res = new ArrayList<>();
        if(digits.length() == 0) return res;
        StringBuilder sb = new StringBuilder();
        helper(sb, 0, digits);
        return res;
    }

    private void helper(StringBuilder sb, int ind, String digits) {
        if(ind == digits.length()) {
            res.add(sb.toString());
            return;
        }

        int digit = digits.charAt(ind) - '2';
        for(int i = start[digit]; i < start[digit+1]; i++) {
            helper(sb.append((char)('a' + i)), ind+1, digits);
            sb.deleteCharAt(ind);
        }
    } 
}
