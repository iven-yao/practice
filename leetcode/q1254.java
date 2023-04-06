package leetcode;

public class q1254 {
    class Solution {
        public int closedIsland(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            // boolean[][] visited = new boolean[m][n];
            int count = 0;
            for(int x = 0; x < m; x++) {
                for(int y = 0; y < n; y++) {
                    if(grid[x][y] == 0 && isClosedIsland(x,y,grid)) {
                        count++;
                    }
                }
            }
            
            return count++;
        }
    
        public boolean isClosedIsland(int x, int y, int[][] grid) {
            if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) return false;
            if(grid[x][y] > 0) return true;
    
            if(grid[x][y] == 0) grid[x][y] = 2;
    
            boolean top = isClosedIsland(x-1, y, grid);
            boolean btm = isClosedIsland(x+1, y, grid);
            boolean lef = isClosedIsland(x, y-1, grid);
            boolean rig = isClosedIsland(x, y+1, grid);
    
            return top&&btm&&lef&&rig;
        }
    }
}
