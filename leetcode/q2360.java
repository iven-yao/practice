package leetcode;

public class q2360 {

    // 11 ms with int array to remember visiting order!
    class Solution {

        public int longestCycle(int[] edges) {
            int[] visitedOrder = new int[edges.length];
            int order = 1;
            int max = -1;
            for(int i = 0; i < edges.length; i++) {
                if(visitedOrder[i] > 0) {
                    continue;
                }
    
                int startingOrder = order;
                int node = i;
                while(node != -1 && visitedOrder[node] == 0) {
                    visitedOrder[node] = order++;
                    node = edges[node];
                }
    
                if(node != -1 && visitedOrder[node] >= startingOrder) {
                    max = Math.max(max, order-visitedOrder[node]);
                }
            }
    
            return max;
        }
    }

    // slow 1029ms
    public int longestCycle(int[] edges) {
        boolean[] visited = new boolean[edges.length];
        int max = -1;
        for(int i = 0; i < edges.length; i++) {
            if(!visited[i]) {
                // init
                int node = i;
                int cyclestart = -1;
                boolean[] visitedThisIter = new boolean[edges.length];

                // find cycle
                while(!visited[node]) {
                    visited[node] = true;
                    visitedThisIter[node] = true;
                    node = edges[node];
                    if(node == -1) break;
                }
                // System.out.println("cycle");
                // System.out.println(Arrays.toString(visited));
                if(node != -1 && visitedThisIter[node]) {
                    cyclestart = node;
                    node = edges[node];
                    // count length
                    int count = 1;
                    while(node != cyclestart) {
                        // System.out.println(node);
                        count++;
                        node = edges[node];
                    }

                    max = Math.max(max, count);
                }
            }
        }

        return max;
    }
}
