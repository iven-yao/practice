package meta;

public class f27_l921 {
    class Solution {
        public int minAddToMakeValid(String s) {
            int open = 0;
            int close = 0;
    
            for(char c: s.toCharArray()) {
                if(c == '(') open++;
                if(c == ')') {
                    if(open > 0) open--;
                    else close++;
                }
            }
    
            return open + close;
        }
    }
}
