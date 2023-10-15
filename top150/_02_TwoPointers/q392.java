package top150.TwoPointers;

public class q392 {
    public boolean isSubsequence(String s, String t) {
        int ptr = 0;

        for(char c: t.toCharArray()) {
            if(ptr == s.length()) return true;
            if(c == s.charAt(ptr)) ptr++;
        }

        return ptr == s.length();
    }
}
