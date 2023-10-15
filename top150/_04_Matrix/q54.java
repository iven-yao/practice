package top150._04_Matrix;

import java.util.ArrayList;
import java.util.List;

public class q54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] steps = {{0,1},{1,0},{0,-1},{-1,0}};//right, down, left, up
        int step = 0;
        int x = 0, y = 0;
        List<Integer> ans = new ArrayList<>();
        while(true) {
            // if visited or out of bound
            if(x==m || x== -1 || y == n || y == -1 || visited[x][y]) {
                // back off
                x -= steps[step][0];
                y -= steps[step][1];
                step = (step+1)%4;

                // move in next direction
                x += steps[step][0];
                y += steps[step][1];

                // still visited or out of bound, then it's over
                if(x==m || x== -1 || y == n || y == -1 || visited[x][y]) break;
            }

            visited[x][y] = true;
            ans.add(matrix[x][y]);

            // move one step
            x += steps[step][0];
            y += steps[step][1];
            
        }
        
        return ans;
    }
}
