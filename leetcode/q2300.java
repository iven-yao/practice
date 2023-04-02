package leetcode;
import java.util.*;

public class q2300 {
    class Solution {
        public int[] successfulPairs(int[] spells, int[] potions, long success) {
            int[] pairs = new int[spells.length];
    
            Map<Integer, Integer> cache = new HashMap<>();
            int[] sortSpells = Arrays.copyOf(spells, spells.length);
    
            Arrays.sort(sortSpells);
            Arrays.sort(potions);
    
            int index = spells.length-1;
            long threshold = success/sortSpells[index];
            threshold += (success%sortSpells[index] == 0? 0:1);
    
            int ptr = 0;
            while(ptr < potions.length) {
                if(potions[ptr] < threshold) { 
                    ptr++;
                } else {
                    cache.putIfAbsent(sortSpells[index--], potions.length-ptr);
                    if(index >= 0) {
                        threshold = success/sortSpells[index];
                        threshold += (success%sortSpells[index] == 0? 0:1);
                    } else {
                        break;
                    }
                }
            }
    
            while(index >= 0) {
                cache.putIfAbsent(sortSpells[index--], potions.length-ptr);
            }
    
            for(int i = 0; i < spells.length; i++) {
                pairs[i] = cache.get(spells[i]);
            }
            
            return pairs;
        }
    }
}
