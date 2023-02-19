package leetcode;
import java.util.*;

public class q2570 {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        
        Map<Integer, Integer> cache = new HashMap<>();
        
        for(int[] num: nums1) {
            cache.put(num[0], num[1]);
        }
        
        for(int[] num: nums2) {
            if(cache.containsKey(num[0])) {
                cache.put(num[0], cache.get(num[0])+num[1]);
            } else {
                cache.put(num[0], num[1]);
            }
        }
        
        SortedSet<Integer> keys = new TreeSet<>(cache.keySet());
        int[][] res = new int[cache.size()][2];
        int i = 0;
        for(Integer key: keys) {
            res[i][0] = key;
            res[i++][1] = cache.get(key); 
        }
        
        return res;
        
    }
}
