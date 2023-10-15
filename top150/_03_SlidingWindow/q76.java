package top150._03_SlidingWindow;

public class q76 {
    public String minWindow(String s, String t) {
        int[] cache = new int[128];
        int minLen = Integer.MAX_VALUE;
        int start = 0, end = 0, minStart = 0;
        int match = 0;

        for(char c: t.toCharArray()) {
            cache[c]++;
        }

        while(end < s.length()) {
            if(cache[s.charAt(end)]-- > 0) match++;
            end++;

            while(match == t.length()) {
                int currLen = end-start;
                if(currLen < minLen) {
                    minLen = currLen;
                    minStart = start;
                }
                if(cache[s.charAt(start)]++ == 0) match--;
                start++;
            }
        }

        return minLen == Integer.MAX_VALUE?"":s.substring(minStart, minLen+minStart);

    }
}
