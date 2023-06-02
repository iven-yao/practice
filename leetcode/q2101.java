package leetcode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class q2101 {
    class Solution {
        public int maximumDetonation(int[][] bombs) {
            int n = bombs.length;
    
            List<List<Integer>> affectedBomb = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                affectedBomb.add(new ArrayList<Integer>());
            }
    
            for(int i = 0; i < n; i++) {
                int r = bombs[i][2];
                for(int j = 0; j < n; j++) {
                    int dx = bombs[i][0]-bombs[j][0];
                    int dy = bombs[i][1]-bombs[j][1];
                    if((long)r*r >= (long)dx*dx+(long)dy*dy ) {
                        affectedBomb.get(i).add(j);
                    }
                }
            }
    
            int max = 0;
            for(int i = 0; i < n; i++) {
                max = Math.max(max, dfs(i, new HashSet<>(), affectedBomb));
                if(max == n) return max;
            }
    
            return max;
        }
    
        public int dfs(int currBomb, Set<Integer> bombed, List<List<Integer>> affectedBomb) {
            bombed.add(currBomb);
            int count = 1;
            for(int bomb: affectedBomb.get(currBomb)) {
                if(!bombed.contains(bomb)) {
                    count += dfs(bomb, bombed, affectedBomb);
                }
            }
    
            return count;
        }
    }
}
