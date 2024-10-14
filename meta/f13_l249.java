package meta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class f13_l249 {
    class Solution {

        private String encode(String string) {
            int n = string.length();
            int[] res = new int[n];

            char first = string.charAt(0);
            for(int i = 1; i < n; i++) {
                res[i] = string.charAt(i) - first;
                if(res[i] < 0) {
                    res[i] += 26;
                }
            }

            return Arrays.toString(res);
        }
        /**
         * @param strings: a string array
         * @return: return a list of string array
         */
        public List<List<String>> groupStrings(String[] strings) {
            // write your code here
            Map<String, List<String>> map = new HashMap<>();

            for(String string: strings) {
                String encoded = encode(string);
                map.putIfAbsent(encoded, new ArrayList<>());
                map.get(encoded).add(string);
            }

            List<List<String>> res = new ArrayList<>();
            for(Map.Entry<String, List<String>> e: map.entrySet()) {
                res.add(e.getValue());
            }

            return res;
        }
    }
    }
