package top150._03_SlidingWindow;

public class q3 {
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        int[] presentIndex = new int[128];
        int head = 0;

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(presentIndex[c] > head) {
                head = presentIndex[c];
            }
            presentIndex[c] = i+1;
            max = Math.max(max, i-head+1);
        }

        return max;
    }
}
