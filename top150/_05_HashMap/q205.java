package top150._05_HashMap;

import java.util.HashMap;
import java.util.Map;

public class q205 {

    public boolean isIsomorphic(String s, String t) {
        int[] s2t = new int[256];
        int[] t2s = new int[256];

        for(int i = 0; i < s.length(); i++) {
            char ss = s.charAt(i);
            char tt = t.charAt(i);

            if(s2t[ss] != t2s[tt]) return false;

            s2t[ss] = i+1;
            t2s[tt] = i+1;
        }

        return true;
    }
    
    // too slow
    public boolean isIsomorphic_slow(String s, String t) {
        Map<Character, Character> mappingS2T = new HashMap<>();
        Map<Character, Character> mappingT2S = new HashMap<>();

        for(int i = 0; i < s.length(); i++) {
            Character s2t = mappingS2T.get(s.charAt(i));
            Character t2s = mappingT2S.get(t.charAt(i));
            if(s2t == null && t2s == null) {
                mappingS2T.put(s.charAt(i), t.charAt(i));
                mappingT2S.put(t.charAt(i), s.charAt(i));
            } else if(s2t == null || t2s == null || s2t != t.charAt(i) || t2s != s.charAt(i)) return false;
        }

        return true;
    }
}
