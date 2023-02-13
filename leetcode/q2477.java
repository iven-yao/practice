package leetcode;
import java.util.*;

public class q2477 {
    long fuel = 0;
    public long minimumFuelCost(int[][] roads, int seats) {
        Map<Integer, List<Integer>> adjacent = new HashMap<>();
        for(int[] road: roads) {
            adjacent.computeIfAbsent(road[0], k->new ArrayList<>()).add(road[1]);
            adjacent.computeIfAbsent(road[1], k->new ArrayList<>()).add(road[0]);
        }

        dfs(0,-1,seats, adjacent);
        return fuel;

    }

    public int dfs(int node, int parent, int seats, Map<Integer, List<Integer>> map) {
        int ppl = 1;
        if(map.get(node) == null) return ppl;
        for(int child: map.get(node)) {
            if(child != parent) {
                ppl += dfs(child, node, seats, map);
            }
        }
        if(node != 0) {
            fuel += (ppl + seats - 1)/seats;
        }

        return ppl;
    }
}
