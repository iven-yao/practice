package leetcode;

public class q3 {
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            // int len = 0;
            // boolean[] present = new boolean[128];
            // int head = 0;
            // int max = 0;
            // for(int i = 0; i < s.length(); i++) {
            //     char c = s.charAt(i);
            //     if(present[c]) {
            //         while(s.charAt(head) != c) {
            //             present[s.charAt(head)] = false;
            //             len--;
            //             head++;
            //         }
            //         head++;
            //     } else {
            //         present[c] = true;
            //         len++;
            //     }
    
            //     max = Math.max(max, len);
            // }
    
            // return max;
    
            int max = 0;
            int[] presentIndex = new int[128];
            int head = 0;
    
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if(presentIndex[c] > head) {
                    head = presentIndex[c];
                }
                presentIndex[c] = i+1;
                max = Math.max(max, i-head+1);
            }
    
            return max;
        }
    }
}
