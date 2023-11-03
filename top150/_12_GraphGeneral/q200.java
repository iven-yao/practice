package top150._12_GraphGeneral;

public class q200 {
    public int numIslands(char[][] grid) {
        int count = 0;

        for(int x = 0; x < grid.length; x++) {
            for(int y = 0; y < grid[0].length; y++) {
                if(grid[x][y] == '1') {
                    count++;
                    flood(grid, x, y);
                }
            }
        }
        
        return count;
    }

    private void flood(char[][] grid, int x, int y) {
        if(x < 0 || y < 0) return;
        if(x >= grid.length || y >= grid[0].length) return;

        if(grid[x][y] == '1') {
            grid[x][y] = '0';
            flood(grid, x+1, y);
            flood(grid, x-1, y);
            flood(grid, x, y+1);
            flood(grid, x, y-1);
        }
    }
}
