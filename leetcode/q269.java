package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class q269 {
    public static void main(String[] args) {
        q269 q = new q269();
        Solution_bfs sol = q.new Solution_bfs();
        
        String[] words = new String[] {
            "wrt",
            "wrf",
            "er",
            "ett",
            "rftt"
        };

        String[] words2 = new String[] {
            "z",
            "x",
            "z"
        };

        System.out.println(sol.alienOrder(words));
        System.out.println(sol.alienOrder(words2));
    }

    public class Solution_bfs {
        public String alienOrder(String[] words) {
            if(words == null || words.length == 0) return "";

            // build the graph
            Map<Character, List<Character>> adjMap = new HashMap<>();
            buildGraph(words, adjMap);

            // toplogical sorting
            // build indegree map
            Map<Character, Integer> indegreeMap = new HashMap<>();
            for(Character node: adjMap.keySet()) {
                indegreeMap.put(node, 0);
            }

            for(Character node: adjMap.keySet()) {
                for(Character neighbor: adjMap.get(node)) {
                    indegreeMap.put(neighbor, indegreeMap.get(neighbor)+1);
                }
            }

            // start from node with indegree == 0
            Queue<Character> queue = new LinkedList<>();
            for(Character node: indegreeMap.keySet()) {
                if(indegreeMap.get(node) == 0) {
                    queue.offer(node);
                    break;
                }
            }

            StringBuilder sb = new StringBuilder();
            
            while(!queue.isEmpty()) {
                char curr = queue.poll();
                sb.append(curr);

                for(char neighbor: adjMap.get(curr)) {
                    indegreeMap.put(neighbor, indegreeMap.get(neighbor) -1);
                    if(indegreeMap.get(neighbor) == 0) {
                        queue.offer(neighbor);
                    }
                }
            }

            if(sb.length() < adjMap.size()) {
                return "";
            }

            return sb.toString();

        }

        public void buildGraph(String[] words, Map<Character, List<Character>> adjMap) {
            for(String word: words) {
                for(Character c: word.toCharArray()) {
                    adjMap.putIfAbsent(c, new ArrayList<>());
                }
            }

            for(int i = 1; i < words.length; i++) {
                String prev = words[i-1];
                String curr = words[i];

                for(int j = 0; j < prev.length() && j < curr.length(); j++) {
                    if(prev.charAt(j) != curr.charAt(j)) {
                        adjMap.get(prev.charAt(j)).add(curr.charAt(j));
                        break;
                    }
                }
            }
        }
    }
    
}
