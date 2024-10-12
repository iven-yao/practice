package meta;

public class f09_l680 {
    class Solution {

        private boolean palindromeHelper(String s, int head, int tail, int skip) {
            while(head < tail && s.charAt(head)==s.charAt(tail)) {
                head++;
                tail--;
            }

            if(head >= tail) return true;

            if(skip > 0) {
                return palindromeHelper(s, head+1, tail, skip-1) || palindromeHelper(s, head, tail-1, skip-1);
            }

            return false;
        }

        public boolean validPalindrome(String s) {
            int head = 0;
            int tail = s.length() - 1;
            int skip = 1;

            return palindromeHelper(s, head, tail, skip);
        }
    }
}
