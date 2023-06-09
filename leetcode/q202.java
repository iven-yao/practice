package leetcode;

import java.util.HashSet;
import java.util.Set;

public class q202 {
    class Solution {
        public boolean isHappy(int n) {
            Set<Integer> checked = new HashSet<>();
    
            while(n != 1 && !checked.contains(n)) {
                checked.add(n);
                int sum = 0;
    
                while(n != 0) {
                    int digit = n % 10;
                    sum += digit * digit;
                    n = n/10;
                }
    
                n = sum;
            }
    
            return n == 1;
        }
    }
}
