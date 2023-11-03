package top150._13_GraphBFS;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class q909 {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int index = 0;
        int[] flatten = new int[n*n+1];

        for(int row = n-1; row >= 0; row--) {
            // -->
            for(int col = 0; col < n; col++) {
                flatten[++index] = board[row][col];
            }
            if(--row < 0) break;
            // <--
            for(int col = n-1; col >= 0; col--) {
                flatten[++index] = board[row][col];
            }
        }

        // bfs
        int[] steps = new int[n*n+1];
        Arrays.fill(steps, -1);
        steps[1] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);

        while(!q.isEmpty()) {
            int curIdx = q.poll();
            for(int next = curIdx+1; next <= Math.min(curIdx+6, n*n); next++) {
                int nextIdx = flatten[next] == -1? next: flatten[next];
                if(steps[nextIdx] == -1) {
                    steps[nextIdx] = steps[curIdx] + 1;
                    q.offer(nextIdx);
                }
            }
        }

        return steps[n*n];   
    }
}
