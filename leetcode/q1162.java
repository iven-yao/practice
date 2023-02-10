package leetcode;

import java.util.*;

public class q1162 {

    // better approach, with 24ms
    public int maxDistance(int[][] grid) {

        List<int[]> lands = new ArrayList<>();
        // remember land position
        for(int i = 0; i < grid.length; i ++) {
            for(int j = 0; j < grid.length; j++) {
                if(grid[i][j] == 1) {
                    int[] land = {i,j};
                    lands.add(land);
                }
            }
        }

        if(lands.size() == 0 || lands.size() == grid.length*grid.length ) return -1;

        int maxD = 0;
        while(theresWater(grid)) {
            maxD++;
            lands = expandLand(grid, lands);
        }
        

        return maxD;
    }

    private List<int[]> expandLand(int[][] grid, List<int[]> lands) {
        List<int[]> newLands = new ArrayList<>();
        for(int[] land: lands) {
            int x = land[0], y = land[1];
            if(x > 0 && grid[x-1][y] == 0) {
                grid[x-1][y] = 1;
                newLands.add(new int[]{x-1,y});
            }  
            if(x < grid.length-1 && grid[x+1][y] == 0) {
                grid[x+1][y] = 1;
                newLands.add(new int[]{x+1, y});
            }
            if(y > 0 && grid[x][y-1] == 0) {
                grid[x][y-1] = 1;
                newLands.add(new int[]{x,y-1});
            }
            if(y < grid.length-1 && grid[x][y+1] == 0) {
                grid[x][y+1] = 1;
                newLands.add(new int[]{x,y+1});
            }
        }

        return newLands;
    }

    private boolean theresWater(int[][] grid) {
        for(int i = 0; i < grid.length; i ++) {
            for(int j = 0; j < grid.length; j++) {
                if(grid[i][j] == 0) return true;
            }
        }

        return false;
    }


    // too slow, 2331ms
    public int maxDistanceSLOW(int[][] grid) {

        List<int[]> lands = new ArrayList<>();
        // change land to -1, water to Interger.MAX_VALUE
        // remember land position
        for(int i = 0; i < grid.length; i ++) {
            for(int j = 0; j < grid.length; j++) {
                if(grid[i][j] == 1) {
                    grid[i][j] = -1;
                    int[] land = {i,j};
                    lands.add(land);
                }
                if(grid[i][j] == 0) grid[i][j] = Integer.MAX_VALUE;
            }
        }

        if(lands.size() == 0 || lands.size() == grid.length*grid.length ) return -1;
        
        for(int[] land: lands) {
            updateDistance(grid, land[0], land[1]);
        }

        return getMax(grid);
    }

    private void updateDistance(int[][] grid, int x, int y) {
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid.length; j++) {
                int d = Math.abs(x-i) + Math.abs(y-j);
                grid[i][j] = Math.min(d, grid[i][j]);
            }
        }
    }

    private int getMax(int[][] grid) {
        int max = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid.length; j++) {
                max = Math.max(grid[i][j], max);
            }
        }

        return max;
    }
}
