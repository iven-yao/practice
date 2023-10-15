package top150._01_ArrayString;

public class q28 {
    // beat 100%
    public int strStr(String haystack, String needle) {
        // return haystack.indexOf(needle);
        int m = needle.length();
        for(int i = 0; i <= haystack.length()-m; i++) {
            if(haystack.charAt(i) == needle.charAt(0)) {
                if(haystack.substring(i, i+m).equals(needle)) {
                    return i;
                }
            }
        }

        return -1;
    }

    // public int strStr(String haystack, String needle) {
    //     // return haystack.indexOf(needle);
    //     int ptr = 0;
    //     for(int i = 0; i < haystack.length(); i++) {
    //         if(haystack.charAt(i) == needle.charAt(ptr)) {
    //             ptr++;
    //             if(ptr == needle.length()) {
    //                 return i-needle.length()+1;
    //             }
    //         } else {
    //             i -= ptr;
    //             ptr = 0;
    //         }
    //     }

    //     return -1;
    // }
}
