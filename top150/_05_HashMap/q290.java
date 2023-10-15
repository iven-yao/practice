package top150._05_HashMap;

import java.util.HashMap;
import java.util.Map;

public class q290 {
    // 100%
    public boolean wordPattern_0ms(String pattern, String s) {
        String[] ss = s.split(" ");

        if(ss.length != pattern.length()) return false;

        Map<Character, String> c2s = new HashMap<>();

        for(int i = 0; i < ss.length; i++) {
            char c = pattern.charAt(i);
            if(c2s.containsKey(c)) {
                if(!c2s.get(c).equals(ss[i])) return false;
            } else if(c2s.containsValue(ss[i])) {
                return false;
            } else {
                c2s.put(c, ss[i]);
            }
        }

        return true;
    }

    public boolean wordPattern_1ms(String pattern, String s) {
        String[] ss = s.split(" ");

        if(ss.length != pattern.length()) return false;

        Map<Character, String> c2s = new HashMap<>();
        Map<String, Character> s2c = new HashMap<>();

        for(int i = 0; i < ss.length; i++) {
            // System.out.println(String.format("%d, %s",i, ss[i]));
            // System.out.println(c2s);
            // System.out.println(s2c);
            if(c2s.containsKey(pattern.charAt(i)) && s2c.containsKey(ss[i])) {
                if(!c2s.get(pattern.charAt(i)).equals(ss[i]) || s2c.get(ss[i]) != pattern.charAt(i)) return false;
            } else if(c2s.containsKey(pattern.charAt(i)) || s2c.containsKey(ss[i])) {
                return false;
            } else {
                c2s.put(pattern.charAt(i), ss[i]);
                s2c.put(ss[i], pattern.charAt(i));
            }
        }

        return true;
    }
}
