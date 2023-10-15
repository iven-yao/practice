package top150._04_Matrix;

public class q289 {
    int[][] adj = new int[][]{{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};

    public void gameOfLife(int[][] board) {
        // use 2 as dead
        for(int[] row: board) {
            for(int i = 0; i < row.length; i++) {
                if(row[i] == 0) row[i] = 2;
            }
        }

        // use negative as state change
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                stateChange(board, i, j);
            }
        }

        // recover board
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == -1 || board[i][j] == 2) board[i][j] = 0;
                if(board[i][j] == -2) board[i][j] = 1; 
            }
        }
    }

    private void stateChange(int[][] board, int x, int y) {
        int adjAlive = liveCount(board, x, y);
        // live
        if(board[x][y] == 1) {
            if(adjAlive > 3 || adjAlive < 2) board[x][y] = -1;
        } else {
            if(adjAlive == 3) board[x][y] = -2;
        }
    }

    private int liveCount(int[][] board, int x, int y) {
        int count = 0;
        for(int[] a: adj) {
            int xx = x+a[0];
            int yy = y+a[1];

            if(xx < 0 || xx >= board.length || yy < 0 || yy >= board[0].length) continue;
            if(Math.abs(board[xx][yy]) == 1) count++;
        }

        return count;
    }
}
