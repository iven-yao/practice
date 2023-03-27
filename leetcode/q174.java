package leetcode;

public class q174 {
    class Solution {
        public int calculateMinimumHP(int[][] dungeon) {
            int m = dungeon.length;
            int n = dungeon[0].length;
            int[][] minHP = new int[m][n];
    
            minHP[m-1][n-1] = Math.max(1, 1-dungeon[m-1][n-1]);
            for(int i = m-2; i >= 0; i--) {
                minHP[i][n-1] = Math.max(1, minHP[i+1][n-1] - dungeon[i][n-1]);
            }
            for(int i = n-2; i >= 0; i--) {
                minHP[m-1][i] = Math.max(1, minHP[m-1][i+1] - dungeon[m-1][i]);
            }
    
            for(int i = m-2; i >= 0; i--) {
                for(int j = n-2; j >= 0; j--) {
                    int nextMin = Math.min(minHP[i+1][j], minHP[i][j+1]);
                    minHP[i][j] = Math.max(1, nextMin - dungeon[i][j]);
                }
            }
    
            return minHP[0][0];
        }
    }
}
