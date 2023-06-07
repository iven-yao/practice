package leetcode;

public class q2220 {
    class Solution {
        public int minBitFlips(int start, int goal) {
            int flip = 0;
            while(start > 0 || goal > 0) {
                int bitS = start & 1;
                int bitG = goal & 1;
    
                if(bitS != bitG) flip++;
    
                start >>= 1;
                goal >>= 1;
            }
    
            return flip;
        }
    }
}
