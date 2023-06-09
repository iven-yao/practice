package leetcode;

public class q744 {
    class Solution {
        public char nextGreatestLetter(char[] letters, char target) {
            char smallest = letters[0];
            int min = Integer.MAX_VALUE;
            for(char c: letters) {
                if(c-target < min && c-target > 0 ){
                    min = c-target;
                    smallest = c;
                }
            }
    
            return smallest;
        }
    }
}
