package meta;

public class f11_l65 {
    class Solution {
        public boolean isNumber(String s) {
            int expIndex = -1;
            boolean prevIsDigit = false;
            boolean dotAccept = true;
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if(Character.isDigit(c)) {
                    prevIsDigit = true;
                    continue;
                } else {
                    if(c == '+' || c == '-') {
                        if(i != expIndex+1) return false;
                    }
                    else if(c == 'e' || c == 'E') {
                        if(!prevIsDigit) {
                            return false;
                        }
    
                        if(expIndex != -1) {
                            return false;
                        }
    
                        expIndex = i;
                        dotAccept = false;
                    }
                    else if(c == '.') {
                        if(!dotAccept) {
                            return false;
                        }
    
                        dotAccept = false;
                        continue;
                    }
                    else {
                        return false;
                    }
                    prevIsDigit = false;
                }
            }
    
            return prevIsDigit;
        }
    }
}
