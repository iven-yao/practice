package leetcode;

import java.util.*;

public class q1319 {
    public int makeConnected(int n, int[][] connections) {
        if(n > connections.length + 1) return -1;

        Map<Integer, List<Integer>> adj = new HashMap<>();

        for(int[] connection: connections) {
            adj.computeIfAbsent(connection[0], f-> new ArrayList<>()).add(connection[1]);
            adj.computeIfAbsent(connection[1], f-> new ArrayList<>()).add(connection[0]);
        }

        return islandCount(adj, n) -1;
    }

    public int islandCount(Map<Integer, List<Integer>> adj, int n) {
        
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        int count = 0;

        for(int j = 0; j < n; j++) {
            if(!visited[j]) {
                count++;
                q.offer(j);
                while(!q.isEmpty()) {
                    int node = q.poll();

                    if(adj.containsKey(node)) {
                        for(int i: adj.get(node)) {
                            if(!visited[i]) {
                                // System.out.println(i);
                                visited[i] = true;
                                q.offer(i);
                            }
                        }
                    }
                }
            }
        }        

        return count;
    }
}
