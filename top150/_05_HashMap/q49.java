package top150._05_HashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class q49 {
    int mod = 1_000_000_000 + 7;
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Integer, List<String>> map = new HashMap<>();

        for(String str: strs) {
            int[] cnt = new int[26];
            for(char c: str.toCharArray()) {
                cnt[c-'a']++;
            }

            int hash = hash(cnt);

            map.putIfAbsent(hash, new ArrayList<String>());
            map.get(hash).add(str);
        }

        return new ArrayList<>(map.values());
    }

    private int hash(int[] arr) {
        int hash = 0;
        for(int ele: arr) {
            hash += ele;
            hash *= 26;
            hash %= mod;
        }

        return hash;
    }
}
