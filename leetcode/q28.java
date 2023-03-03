package leetcode;

public class q28{
    public int strStr(String haystack, String needle) {
        int ptr = 0;
        int head = 0;
        for(int i = 0; i < haystack.length(); i++) {
            if(haystack.charAt(i) == needle.charAt(ptr)) {
                if(ptr == 0) head = i;
                ptr++;
                if(ptr == needle.length()) {
                    return head;
                }
            } else {
                i -= ptr;
                ptr = 0;
            }
        }

        return -1;
    }
}
