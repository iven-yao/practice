package top150._15_Backtracking;

import java.util.ArrayList;
import java.util.List;

public class q22 {
    List<String> res;
    public List<String> generateParenthesis(int n) {
        res = new ArrayList<>();
        helper(new StringBuilder(), 0, 0, n);
        return res;
    }

    private void helper(StringBuilder sb, int left, int right, int n) {
        if(sb.length() == n*2) {
            res.add(sb.toString());
            return;
        }

        if(left < n) {
            sb.append('(');
            helper(sb, left+1, right, n);
            sb.setLength(sb.length() - 1);
        }

        if(right < left) {
            sb.append(')');
            helper(sb, left, right+1, n);
            sb.setLength(sb.length() - 1);
        }
    }
}
