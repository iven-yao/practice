package top150._15_Backtracking;

public class q52 {
    int[][] board;
    int count;
    public int totalNQueens(int n) {
        board = new int[n][n];
        count = 0;
        helper(0, n);
        return count;
    }

    private void printboard() {
        for(int[] row: board) {
            for(int ele: row) {
                System.out.print(ele+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void helper(int row, int n) {
        printboard();
        if(row == n) {
            count++;
            return;
        }

        for(int col = 0; col < n; col++) {
            if(board[row][col] == 0) {
                add(row, col, n);
                helper(row+1, n);
                sub(row, col, n);
            }
        }
    }

    private void add(int row, int col, int n) {
        for(int i = 0; i < n; i++) {
            board[row][i]++;
            board[i][col]++;
        }
        board[row][col]--;

        int x = row+1;
        int y = col+1;
        while(x>=0&&y>=0&&x<n&&y<n) {
            board[x++][y++]++;
        }
        x = row-1;
        y = col-1;
        while(x>=0&&y>=0&&x<n&&y<n) {
            board[x--][y--]++;
        }
        x = row+1;
        y = col-1;
        while(x>=0&&y>=0&&x<n&&y<n) {
            board[x++][y--]++;
        }
        x = row-1;
        y = col+1;
        while(x>=0&&y>=0&&x<n&&y<n) {
            board[x--][y++]++;
        }
    }

    private void sub(int row, int col, int n) {
        for(int i = 0; i < n; i++) {
            board[row][i]--;
            board[i][col]--;
        }
        board[row][col]++;
        
        int x = row+1;
        int y = col+1;
        while(x>=0&&y>=0&&x<n&&y<n) {
            board[x++][y++]--;
        }
        x = row-1;
        y = col-1;
        while(x>=0&&y>=0&&x<n&&y<n) {
            board[x--][y--]--;
        }
        x = row+1;
        y = col-1;
        while(x>=0&&y>=0&&x<n&&y<n) {
            board[x++][y--]--;
        }
        x = row-1;
        y = col+1;
        while(x>=0&&y>=0&&x<n&&y<n) {
            board[x--][y++]--;
        }
    }

    public static void main(String[] args) {
        q52 q = new q52();
        System.out.println(">>>>> "+q.totalNQueens(4));
        System.out.println(">>>>> "+q.totalNQueens(3));
        System.out.println(">>>>> "+q.totalNQueens(5));
    }
}
