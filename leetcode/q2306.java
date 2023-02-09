package leetcode;

import java.util.*;

/*

2306. Naming a Company <Hard>
You are given an array of strings ideas that represents a list of names to be used in the process of naming a company. 
The process of naming a company is as follows:

Choose 2 distinct names from ideas, call them ideaA and ideaB.
Swap the first letters of ideaA and ideaB with each other.
If both of the new names are not found in the original ideas, 
then the name ideaA ideaB (the concatenation of ideaA and ideaB, separated by a space) is a valid company name.
Otherwise, it is not a valid name.
Return the number of distinct valid names for the company. 

*/

public class q2306 {
    
    public static void main(String[] args) {
        q2306 sol = new q2306();
        String[] ideas = {"coffee","donuts","time","toffee"};
        System.out.println(sol.distinctNames(ideas));
    }

    public long distinctNames(String[] ideas) {
        List<Set<String>> alphabetArr = new ArrayList<>();
        for(int i = 0; i < 26; i++) {
            alphabetArr.add(new HashSet<>());
        }
        for(String idea : ideas) {
            alphabetArr.get(idea.charAt(0)-'a').add(idea.substring(1,idea.length()));
        }

        long distinct = 0;

        for(int i = 0; i < 26; i++) {
            Set<String> s1 = alphabetArr.get(i);
            if(s1.size() == 0) continue;
            for(int j = i+1; j < 26; j++) {
                Set<String> s2 = alphabetArr.get(j);
                if(s2.size() == 0) continue;
                long intersection = intersection(s1, s2);
                distinct += 2*(s1.size()-intersection)*(s2.size()-intersection);
            }
            
        }
        return distinct;
    }

    public long intersection(Set<String> s1, Set<String> s2) {
        long count = 0;
        for(String s: s1) {
            if(s2.contains(s)) count++;
        }

        return count;
    }
    

    /*
     * try to optimize, using array instead of map => not working, almost the same
     *                  using list instead of set(since ideas are unique) => worse performance, very slow in getting intersection
     *                  writing my own intersection => works!! Time spent improves from beat 65% to beat 82%
     */
    // public long distinctNames(String[] ideas) {
    //     Map<Character, Set<String>> alphabetMap = new HashMap<>();
    //     for(String idea : ideas) {
    //         Set<String> set = alphabetMap.get(idea.charAt(0));
    //         if(set == null) {
    //             set = new HashSet<String>();
    //         }
    //         set.add(idea.substring(1, idea.length()));
    //         alphabetMap.put(idea.charAt(0), set);
    //     }

    //     long distinct = 0;
    //     List<Character> keys = new ArrayList<>();
    //     for(Character key: alphabetMap.keySet()) {
    //         keys.add(key);
    //     } 

    //     for(int i = 0; i < keys.size(); i++) {
    //         for(int j = i+1; j < keys.size(); j++) {
    //             Character key1 = keys.get(i);
    //             Character key2 = keys.get(j);
    //             Set<String> s1 = alphabetMap.get(key1);
    //             Set<String> s2 = alphabetMap.get(key2);
    //             Set<String> intersection = new HashSet<>(s1);
    //             intersection.retainAll(s2);
    //             distinct += 2*(s1.size()-intersection.size())*(s2.size()-intersection.size());
    //         }
            
    //     }

    //     return distinct;
    // }

    /**
     * TLE, what about using alphabetMap!
     */
    // public long distinctNamesTLE(String[] ideas) {
    //     Map<String, Set<Character>> tailMap = new HashMap<>();
    //     for(String idea : ideas) {
    //         Set<Character> set = tailMap.get(idea.substring(1, idea.length()));
    //         if(set == null) {
    //             set = new HashSet<Character>();
    //         }
    //         set.add(idea.charAt(0));
    //         tailMap.put(idea.substring(1, idea.length()), set);
    //     }

    //     long distinct = 0;
    //     List<String> keys = new ArrayList<>();
    //     for(String key: tailMap.keySet()) {
    //         keys.add(key);
    //     } 

    //     for(int i = 0; i < keys.size(); i++) {
    //         for(int j = i+1; j < keys.size(); j++) {
    //             String key1 = keys.get(i);
    //             String key2 = keys.get(j);
    //             if(key1.equals(key2)) continue;
    //             Set<Character> s1 = tailMap.get(key1);
    //             Set<Character> s2 = tailMap.get(key2);
    //             if(s1.size() == 26 || s2.size() == 26) break;
    //             Set<Character> intersection = new HashSet<>(s1);
    //             intersection.retainAll(s2);
    //             distinct += 2*(s1.size()-intersection.size())*(s2.size()-intersection.size());
    //         }
            
    //     }

    //     return distinct;
    // }
}
