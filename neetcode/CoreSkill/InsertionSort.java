package CoreSkill;

import java.util.ArrayList;
import java.util.List;

public class InsertionSort {
    class Solution {
        class Pair {
            int key;
            String value;
        
            Pair(int key, String value) {
                this.key = key;
                this.value = value;
            }
        }
        public List<List<Pair>> insertionSort(List<Pair> pairs) {
            List<List<Pair>> res= new ArrayList<>();

            for(int i = 0; i < pairs.size(); i++) {
                Pair pair = pairs.get(i);
                for(int j = i-1; j >= 0; j--) {
                    if(pairs.get(j).key > pair.key) {
                        pairs.set(j+1, pairs.get(j));
                        pairs.set(j, pair);
                    } else break;
                }

                res.add(new ArrayList<Pair>(pairs));
            }

            return res;
        }
    }
}
