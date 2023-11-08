package top150._15_Backtracking;

public class q79 {
    public boolean exist(char[][] board, String word) {
        for(int x = 0; x < board.length; x++) {
            for(int y = 0; y < board[0].length; y++) {
                if(helper(board,x,y,0,word)) return true;
            }
        }
        return false;
    }

    private boolean helper(char[][] board, int x, int y, int idx, String word) {
        if(idx == word.length()) return true;
        if(x < 0 || y < 0 || x >= board.length || y >= board[0].length) return false;
        if(board[x][y] != word.charAt(idx)) return false;
        if(board[x][y] == '#') return false;

        char og = board[x][y];
        board[x][y] = '#';
        boolean check = helper(board, x+1, y, idx+1, word) 
                    ||  helper(board, x-1, y, idx+1, word)
                    ||  helper(board, x, y+1, idx+1, word)
                    ||  helper(board, x, y-1, idx+1, word);
        board[x][y] = og;

        return check;
    }
}
