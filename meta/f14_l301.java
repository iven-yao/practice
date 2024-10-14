package meta;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class f14_l301 {
    class Solution {
        Set<String> set;
        private void helper(String s, int index, int open, int openR, int closeR, StringBuilder sb) {
            if(index >= s.length()) {
                if(open == 0 && openR == 0 && closeR == 0) {
                    set.add(sb.toString());
                }
                return;
            }
            
            char c = s.charAt(index);
            if(c == ')') {
                if(closeR > 0) {
                    helper(s, index+1, open, openR, closeR-1, sb);
                }
                if(open > 0) {
                    helper(s, index+1, open-1, openR, closeR, sb.append(c));
                    sb.deleteCharAt(sb.length() - 1);
                }
                
            } 
            else if(c == '(') {
                if(openR > 0) {
                    helper(s, index+1, open, openR-1, closeR, sb);
                }
                helper(s, index+1, open+1, openR, closeR, sb.append(c));
                sb.deleteCharAt(sb.length() - 1);
            }
            else {
                helper(s, index+1, open, openR, closeR, sb.append(c));
                sb.deleteCharAt(sb.length() - 1);
            }
        }

        public List<String> removeInvalidParentheses(String s) {
            int openToBeRemoved = 0;
            int closeToBeRemoved = 0;

            for(char c: s.toCharArray()) {
                if(c == '(') {
                    openToBeRemoved++;
                }
                if(c == ')') {
                    if(openToBeRemoved > 0) {
                        openToBeRemoved--;
                    } else {
                        closeToBeRemoved++;
                    }
                }
            }


            set = new HashSet<>();
            helper(s, 0, 0, openToBeRemoved, closeToBeRemoved, new StringBuilder());

            List<String> res = new ArrayList<>();

            for(String ele: set) {
                res.add(ele);
            }

            return res;
        }
    }
}
