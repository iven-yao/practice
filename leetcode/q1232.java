package leetcode;

public class q1232 {
    class Solution {
        public boolean checkStraightLine(int[][] coordinates) {
            int[] first = coordinates[0];
            int[] second = coordinates[1];
    
            for(int i = 2; i < coordinates.length; i++) {
                int x = coordinates[i][0];
                int y = coordinates[i][1];
                if((x - first[0]) * (y-second[1]) != (x-second[0]) * (y-first[1])) {
                    return false;
                }
            }
    
            return true;
        }
    }
}
