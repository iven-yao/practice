package leetcode;
import java.util.*;

public class q2316 {
    public long countPairs(int n, int[][] edges) {
        Map<Integer, List<Integer>> adj = new HashMap<>();

        for(int[] edge: edges) {
            adj.computeIfAbsent(edge[0], f->new ArrayList<>()).add(edge[1]);
            adj.computeIfAbsent(edge[1], f->new ArrayList<>()).add(edge[0]);
        }

        List<Integer> islandSize = bfs(adj, n);
        // System.out.println(islandSize);

        long ans = 0;
        long remain = n;
        for(int i = 0; i < islandSize.size(); i++) {
            remain -= islandSize.get(i);
            ans += islandSize.get(i) * remain;
        }

        return ans;
    }

    public List<Integer> bfs(Map<Integer, List<Integer>> adj, int n) {
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        List<Integer> islandSize = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                q.offer(i);
                visited[i] = true;

                int nodeCount = 1;
                while(!q.isEmpty()) {
                    int node = q.poll();
                    if(adj.containsKey(node)) {
                        for(int next : adj.get(node)) {
                            if(!visited[next]) {
                                visited[next] = true;
                                nodeCount++;
                                q.offer(next);
                            }
                        }
                    }
                }

                islandSize.add(nodeCount);
            }
        }

        return islandSize;
    }
}
