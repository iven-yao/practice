package leetcode;

import java.util.*;

public class q2492 {
    public int minScore(int n, int[][] roads) {
        Map<Integer, List<List<Integer>>> adj = new HashMap<>();

        for(int[] road: roads) {
            adj.computeIfAbsent(road[0], f->new ArrayList<List<Integer>>()).add(Arrays.asList(road[1], road[2]));
            adj.computeIfAbsent(road[1], f->new ArrayList<List<Integer>>()).add(Arrays.asList(road[0], road[2]));
        }

        return bfs(n, adj);
    }

    public int bfs(int n, Map<Integer, List<List<Integer>>> adj) {
        boolean[] visited = new boolean[n+1];
        Queue<Integer> q = new LinkedList<>();
        int answer = Integer.MAX_VALUE;

        q.offer(1);

        while(!q.isEmpty()) {
            int node = q.poll();

            if(adj.containsKey(node)) {
                for(List<Integer> road: adj.get(node)) {
                    answer = Math.min(answer, road.get(1));
                    if(!visited[road.get(0)]) {
                        visited[road.get(0)] = true;
                        q.offer(road.get(0));
                    }
                }
            }
        }

        return answer;
    }
}
