package leetcode;
import java.util.*;

public class q1376 {
    class Solution {
        public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
            Map<Integer, List<Integer>> graph = new HashMap<>();
            for(int i = 0; i < n; i++) {
                graph.computeIfAbsent(manager[i], k -> new ArrayList<>()).add(i);
            }
            return dfs(headID, graph, informTime);
        }
    
        public int dfs(int currId, Map<Integer, List<Integer>> graph, int[] informTime) {
            if(graph.get(currId) == null) return 0;
            
            int max = 0;
            for(int id: graph.get(currId)) {
                max = Math.max(max, dfs(id, graph, informTime));
            }
            
            return informTime[currId]+max;
        }
    }

    class BetterSolution {
        public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
            int max = 0;
            for(int i = 0; i < n; i++) {
                max = Math.max(max, dfs(i, manager, informTime));
            }
            return max;
        }
    
        public int dfs(int currId, int[] manager, int[] informTime) {
            if(manager[currId] != -1) {
                informTime[currId] += dfs(manager[currId], manager, informTime);
                manager[currId] = -1;
            }
    
            return informTime[currId];
        }
    }
}
