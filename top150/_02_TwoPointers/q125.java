package top150._02_TwoPointers;

public class q125 {
    public boolean isPalindrome(String s) {
        int head = 0;
        int tail = s.length()-1;
        s = s.toLowerCase();

        while(head < tail) {
            char chead = s.charAt(head);
            char ctail = s.charAt(tail);
            // System.out.println(chead + ","+ ctail);
            if(!isAlphanumeric(chead)) head++;
            else if(!isAlphanumeric(ctail)) tail--;
            else if(chead == ctail) {
                head++;
                tail--;
            } else return false;
        }

        return true;
    }

    private boolean isAlphanumeric(char c) {
        if(c - 'a' >= 0 && c - 'a' < 26) return true;
        if(c - '0' >= 0 && c - '0' < 10) return true;
        return false;
    }
}
