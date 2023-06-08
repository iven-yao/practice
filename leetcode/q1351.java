package leetcode;

public class q1351 {
    class Solution {
        public int countNegatives(int[][] grid) {
            int count = 0;
            for(int[] row: grid) {
                count += bSearch(row);
            }
    
            return count;
        }
    
        public int bSearch(int[] row) {
            int head = 0;
            int tail = row.length;
    
            while(head < tail) {
                int mid = (head+tail)/2;
                if(row[mid] >= 0) {
                    head = mid+1;
                } else {
                    tail = mid;
                }
            }
    
            return row.length - tail;
        }
    }
}
