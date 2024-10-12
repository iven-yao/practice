package meta;

import java.util.HashMap;
import java.util.Map;

public class f05_l1570 {
    class SparseVector {
        // Your SparseVector object will be instantiated and called as such:
        // SparseVector v1(nums1);
        // SparseVector v2(nums2);
        // int ans = v1.dotProduct(v2);
        Map<Integer, Integer> indexToVal;
        SparseVector(int[] nums) {
            // do intialization here
            indexToVal = new HashMap<>();
            for(int i = 0; i < nums.length; i++) {
                if(nums[i] != 0) {
                    indexToVal.put(i, nums[i]);
                }
            }
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector vec) {
            // write your code here
            int sum = 0;
            Map<Integer, Integer> mapA = this.indexToVal;
            Map<Integer, Integer> mapB = vec.indexToVal;

            if(mapA.size() > mapB.size()) {
                Map<Integer, Integer> tmp = mapA;
                mapA = mapB;
                mapB = tmp;
            }

            for(Map.Entry<Integer, Integer> e: mapA.entrySet()) {
                int k = e.getKey();
                int v = e.getValue();
                sum += v * mapB.getOrDefault(k, 0);
            }

            return sum;
        }
    }
}
