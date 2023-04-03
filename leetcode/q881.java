package leetcode;

import java.util.Arrays;

public class q881 {
    class Solution {
        public int numRescueBoats(int[] people, int limit) {
            Arrays.sort(people);
    
            int left = 0;
            int right = people.length-1;
    
            int count = 0;
            while(left <= right) {
                if(left == right) {
                    count++;
                    left++;
                } else if(people[left] + people[right] <= limit) {
                    left++;
                    right--;
                    count++;
                } else {
                    right--;
                    count++;
                }
            }    
    
            return count;
        }
    }
}
