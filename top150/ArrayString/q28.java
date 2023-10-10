package top150.ArrayString;

public class q28 {
    public int strStr(String haystack, String needle) {
        // return haystack.indexOf(needle);
        int ptr = 0;
        for(int i = 0; i < haystack.length(); i++) {
            if(haystack.charAt(i) == needle.charAt(ptr)) {
                ptr++;
                if(ptr == needle.length()) {
                    return i-needle.length()+1;
                }
            } else {
                i -= ptr;
                ptr = 0;
            }
        }

        return -1;
    }
}
