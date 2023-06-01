package leetcode;
import java.util.LinkedList;
import java.util.Queue;

public class q1091 {
    class Solution {
        int[][] dirs = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
        public int shortestPathBinaryMatrix(int[][] grid) {
            if(grid[0][0] == 1) return -1;
            int n = grid.length;
    
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{0,0,1});
            grid[0][0] = 1;
    
            while(!q.isEmpty()) {
                int[] curr = q.poll();
                int x = curr[0];
                int y = curr[1];
                int step = curr[2];
                
                if(x == n-1 && y == n-1) {
                    return step;
                }
    
                for(int[] dir: dirs) {
                    int xx = x + dir[0];
                    int yy = y + dir[1];
    
                    if(xx >= 0 && xx < n && yy >=0 && yy < n && grid[xx][yy] == 0) {
                        q.offer(new int[]{xx,yy,step+1});
                        grid[xx][yy] = 1;
                    }
                }
            }
    
            return -1;
        }
    }
}
