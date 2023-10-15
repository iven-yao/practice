package top150._03_SlidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class q30 {


    //slow
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> list = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for(String word: words) {
            map.put(word, map.getOrDefault(word, 0)+1);
        }

        // System.out.println(map);

        int wordSize = words[0].length();
        int windowSize = words.length * wordSize;

        for(int i = 0; i <= s.length() - windowSize; i++) {
            int head = i;
            HashMap<String, Integer> seen = new HashMap<>();

            for(int tail=i; tail <= i+windowSize-wordSize; tail+=wordSize) {
                String currWord = s.substring(tail, tail+wordSize);
                if(!map.containsKey(currWord)) break;
                seen.put(currWord, seen.getOrDefault(currWord, 0)+1);
                if(seen.get(currWord) > map.get(currWord)) break;
                if(tail == i+windowSize-wordSize) list.add(head);
            }

            // System.out.println(seen);
        }

        return list;
    }
}
