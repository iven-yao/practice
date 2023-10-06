package top150.ArrayString;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class q380 {
    class RandomizedSet {

        Map<Integer, Integer> map;
        Random rand;
        int[] arr;
        int size;
        

        public RandomizedSet() {
            map = new HashMap<Integer, Integer>();
            rand = new Random();
            arr = new int[10_000];
            size = 0;
        }
        
        public boolean insert(int val) {
            if(map.containsKey(val)) {
                return false;
            } else {
                map.put(val, size);
                arr[size] = val;
                size++;
                return true;
            }
        }
        
        public boolean remove(int val) {
            if(map.containsKey(val)) {
                int valIndex = map.get(val);
                int temp = arr[size-1];
                arr[valIndex] = temp;
                map.put(temp, valIndex);
                map.remove(val);
                size--;
                return true;
            } else {
                return false;
            }
        }
        
        public int getRandom() {
            // System.out.println(Arrays.toString(arr));
            int r = rand.nextInt(size);
            return arr[r];
        }
    }
}
