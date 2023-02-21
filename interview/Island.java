package interview;
// TikTok 2022 Creative Tools Summer Intern Technical Interview First Round
class Island {
    public static void main(String[] args){
        int[][] map = new int[][]{
            {0,1,1,1,1,0,0},
            {1,0,0,0,1,0,1},
            {1,0,1,1,0,0,1},
            {1,1,0,1,1,1,1},
            {1,0,0,0,1,0,0}
        };

        System.out.println(islandCount(map));
        mapResume(map);
        System.out.println(largestIsland(map));
    }

    public static void mapResume(int[][] map) {
        for(int i = 0; i< map.length; i++) {
            for(int j = 0; j< map[0].length; j++) {
                if(map[i][j] == 2) map[i][j] = 1;
            }
        }
    }

    public static void floodIsland(int[][] map, int x, int y) {
        if(x<0 || x>=map.length || y<0 || y>=map[0].length) return;
        if(map[x][y] == 1) {
            map[x][y] = 2;
            floodIsland(map, x+1, y);
            floodIsland(map, x-1, y);
            floodIsland(map, x, y+1);
            floodIsland(map, x, y-1);
        }
    }
    
    public static int getArea(int[][] map, int x, int y) {
        int area = 0;
        if(x<0 || x>=map.length || y<0 || y>=map[0].length) return 0;
        if(map[x][y] == 1) {
            map[x][y] = 2;
            area += 1+getArea(map, x+1, y)+getArea(map, x-1, y)+getArea(map, x, y+1)+getArea(map, x, y-1);
        }

        return area;
    }

    public static int largestIsland(int[][] map) {
        int maxArea = 0;
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                if(map[i][j] == 1) {
                    maxArea = Math.max(maxArea, getArea(map, i, j));
                }
            }
        }

        return maxArea;
    }

    public static int islandCount(int[][] map) {
        int count = 0;

        for(int i = 0; i < map.length; i++) {
            for(int j =0; j < map[0].length; j++) {
                if(map[i][j] == 1){
                    floodIsland(map, i, j);
                    count++;
                }
            }
        }
        return count;
    }
}
