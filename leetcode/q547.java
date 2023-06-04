package leetcode;

public class q547 {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int count = 0;
        for(int i = 0; i < n; i++) {
            if(visited[i]) continue;
            dfs(i, isConnected, visited);
            count++;
        }

        return count;
    }

    public void dfs(int currCity, int[][] isConnected, boolean[] visited) {
        if(visited[currCity]) return;
        visited[currCity] = true;
        for(int i = 0; i < isConnected.length; i++) {
            if(isConnected[currCity][i] == 1) {
                dfs(i, isConnected, visited);
            }
        }

        return;
    }
}
