package top150._21_Math;

import java.util.HashMap;
import java.util.Map;

public class q149 {
    public int maxPoints(int[][] points) {
        int max = 0;

        for(int[] base: points) {
            Map<Double, Integer> map = new HashMap<>();
            for(int[] point: points) {
                if(base == point) continue;
                double slope = Double.POSITIVE_INFINITY;
                if(base[0] != point[0]) {
                    slope = (point[1]-base[1])/(double)(point[0]-base[0]);
                }

                map.put(slope, map.getOrDefault(slope, 0)+1);
                max = Math.max(max, map.get(slope));
            }
        }

        return max+1;
    }
}
