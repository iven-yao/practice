package leetcode;

public class q1750 {
    class Solution {
        public int minimumLength(String s) {
            int left = 0;
            int right = s.length()-1;
    
            while(left < right) {
                char l = s.charAt(left);
                char r = s.charAt(right);
    
                if(l == r) {
                    while(left < s.length() && l == s.charAt(left)) left++;
                    if(left >= right) return 0;
                    while(r >= 0 && r == s.charAt(right)) right--;
                } else{
                    break;
                }
            }
    
            return right<left? 0 : right-left+1;
        }
    }
}
