package leetcode;
import java.util.*;

public class q2577 {


    public static final int[][] DIRS = new int[][] {{1,0},{-1,0},{0,1},{0,-1}};

    public int minimumTime(int[][] grid) {
        if(grid[0][1] > 1 && grid[1][0] > 1) return -1;        
        
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        
        Queue<int[]> minHeap = new PriorityQueue<int[]>((a,b) -> a[2]-b[2]);
        minHeap.offer(new int[]{0,0,0});
        
        while(!minHeap.isEmpty()) {
            int[] entry = minHeap.poll();
            int row = entry[0];
            int col = entry[1];
            int time = entry[2];
            if( row == m-1 && col == n-1) {
                return time;
            }

            if(visited[row][col]) continue;

            visited[row][col] = true;

            for(int[] dir: DIRS) {
                int r = row + dir[0];
                int c = col + dir[1];

                if( r < 0 || c < 0 || r == m || c == n || visited[r][c]) continue;

                if(grid[r][c] <= time + 1) {
                    minHeap.offer(new int[]{r,c,time+1});
                } else {
                    int diff = grid[r][c] - time;
                    if(diff % 2 == 0) {
                        minHeap.offer(new int[]{r,c,grid[r][c]+1});
                    } else {
                        minHeap.offer(new int[]{r,c,grid[r][c]});
                    }
                }
            }
        }

        return -1;
    }
    
}
