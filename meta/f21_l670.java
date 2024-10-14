package meta;

import java.util.HashMap;
import java.util.Map;

public class f21_l670 {
    class Solution {
        public int maximumSwap(int num) {
            char[] numStr = String.valueOf(num).toCharArray();
            Map<Integer, Integer> rightMostAppearance = new HashMap<>();
            int[] maxPossible = new int[numStr.length];
            for(int i = numStr.length-1; i>=0; i--) {
                int n = numStr[i] - '0';
                rightMostAppearance.putIfAbsent(n, i);
                if(i == numStr.length-1) {
                    maxPossible[i] = n;
                } else {
                    maxPossible[i] = Math.max(n, maxPossible[i+1]);
                }
            }

            for(int i = 0; i < numStr.length; i++) {
                if(numStr[i]-'0' < maxPossible[i]) {
                    //swap i and map.get(maxPossible[i]);
                    int swapIndex = rightMostAppearance.get(maxPossible[i]);
                    char tmp = numStr[i];
                    numStr[i] = numStr[swapIndex];
                    numStr[swapIndex] = tmp;
                    break;
                }
            }

            int res = 0;
            for(char c: numStr) {
                res = res * 10 + (c - '0');
            }

            return res;
        }
    }
}
