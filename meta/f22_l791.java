package meta;

import java.util.PriorityQueue;

public class f22_l791 {
    class Solution {
        public String customSortString(String order, String s) {
            int[] letterCount = new int[26];
            // int[]{order, char-'a'};
            PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b)->a[0]-b[0]);

            for(int i = 0; i < order.length(); i++) {
                minHeap.offer(new int[]{i, order.charAt(i)-'a'});
            }

            for(char c: s.toCharArray()) {
                letterCount[c-'a']++;
            }

            StringBuilder res = new StringBuilder();
            while(!minHeap.isEmpty()) {
                int offset = minHeap.poll()[1];
                char c = (char)('a'+offset);
                int count = letterCount[offset];
                letterCount[offset] = 0;
                
                while(count > 0) {
                    res.append(c);
                    count--;
                }
            }

            for(int offset = 0; offset < 26; offset++) {
                int count = letterCount[offset];
                char c = (char)('a'+offset);
                while(count > 0) {
                    res.append(c);
                    count--;
                }
            }

            return res.toString();
        }
    }
}
