package leetcode;

public class q14 {
    class Solution {
        public String longestCommonPrefix(String[] strs) {
            int len = 0;
            char c;
            for(len = 0; len < strs[0].length(); len++) {
                c = strs[0].charAt(len);
                for(int i = 1; i < strs.length; i++) {
                    if(strs[i].length() == len) {
                        return strs[i];
                    }
                    if(strs[i].charAt(len) != c) {
                        return strs[0].substring(0, len);
                    }
                }
            }
    
            return strs[0];
        }
    }
}
