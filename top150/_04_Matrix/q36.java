package top150._04_Matrix;

public class q36 {
    int[][] block = new int[][]{{-1,-1},{-1,0},{-1,1},{0,-1},{0,0},{0,1},{1,-1},{1,0},{1,1}};

    public boolean isValidSudoku(char[][] board) {
        // row by row
        for(char[] row: board){
            if(!valid(row)) return false;
        }

        // col by col
        // transpose first then call valid
        for(int i = 0; i < 9; i++) {
            for(int j = i+1; j < 9; j++) {
                char tmp = board[i][j];
                board[i][j] = board[j][i];
                board[j][i] = tmp;
            }
        }

        for(char[] col: board) {
            if(!valid(col)) return false;
        }

        // block by block
        for(int i = 1; i < 9; i+=3) {
            for(int j = 1; j< 9; j+=3) {
                if(!valid(buildArray(board, i,j))) return false;
            }
        }
        

        return true;
    }

    private char[] buildArray(char[][] board, int x, int y) {
        char[] arr = new char[9];
        for(int i = 0; i < 9; i++) {
            arr[i] = board[x+block[i][0]][y+block[i][1]];
        }

        return arr;
    }

    private boolean valid(char[] arr) {
        int[] seen = new int[10];
        for(char c: arr) {
            if(c!='.') {
                if(seen[c-'0']++ > 0) return false;
            }
        }

        return true;
    }
}
