package meta;

import java.util.LinkedList;
import java.util.Queue;

public class f18_l1091 {
    class Solution {

        int[] DIRS = new int[]{1, 0, -1};
        public int shortestPathBinaryMatrix(int[][] grid) {
            //bfs
            Queue<int[]> q = new LinkedList<>();
            if(grid[0][0] == 0) q.offer(new int[]{0,0,1});
            grid[0][0] = 1;

            while(!q.isEmpty()) {
                int[] coord = q.poll();
                int x = coord[0];
                int y = coord[1];
                int step = coord[2];

                if(x == grid.length -1 && y == grid[0].length -1) return step;
                for(int i: DIRS) {
                    for(int j: DIRS) {
                        if(x+i >= 0 && x+i < grid.length && y+j >= 0 && y+j < grid[0].length && grid[x+i][y+j] == 0) {
                            q.offer(new int[]{x+i, y+j, step+1});
                            grid[x+i][y+j] = 1;
                        }
                    }
                }
            }

            return -1;
        }
    }
}
