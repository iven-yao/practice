package top150._12_GraphGeneral;

public class q130 {
    public void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        boolean[][] visited = new boolean[n][m];

        // run dfs on boundary, left and right
        for(int x = 0; x < n; x++) {
            dfs(board, visited, x, 0);
            dfs(board, visited, x, m-1);
        }
        // top and bottom
        for(int y = 1; y < m-1; y++) {
            dfs(board, visited, 0, y);
            dfs(board, visited, n-1, y);
        }

        // if an 'O' not visited, they are surrounded region, turn them into 'X' 
        for(int x = 1; x < n-1; x++) {
            for(int y = 1 ; y < m-1; y++) {
                if(board[x][y] == 'O' && !visited[x][y]) board[x][y] = 'X';
            }
        }

    }

    private void dfs(char[][] board, boolean[][] visited, int x, int y) {
        if(x < 0 || y < 0 || x >= board.length || y >= board[0].length) return;
        if(visited[x][y]) return;

        visited[x][y] = true;
        if(board[x][y] == 'O') {
            dfs(board, visited, x+1, y);
            dfs(board, visited, x-1, y);
            dfs(board, visited, x, y+1);
            dfs(board, visited, x, y-1);
        }
    }
}
