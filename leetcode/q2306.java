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

    /*
     * try to optimize, using array instead of map => not working, almost the same
     *                  using list instead of set(since ideas are unique) => worse performance, very slow in getting intersection
     */
    public long distinctNames(String[] ideas) {
        Map<Character, Set<String>> alphabetMap = new HashMap<>();
        for(String idea : ideas) {
            Set<String> set = alphabetMap.get(idea.charAt(0));
            if(set == null) {
                set = new HashSet<String>();
            }
            set.add(idea.substring(1, idea.length()));
            alphabetMap.put(idea.charAt(0), set);
        }

        long distinct = 0;
        List<Character> keys = new ArrayList<>();
        for(Character key: alphabetMap.keySet()) {
            keys.add(key);
        } 

        for(int i = 0; i < keys.size(); i++) {
            for(int j = i+1; j < keys.size(); j++) {
                Character key1 = keys.get(i);
                Character key2 = keys.get(j);
                Set<String> s1 = alphabetMap.get(key1);
                Set<String> s2 = alphabetMap.get(key2);
                Set<String> intersection = new HashSet<>(s1);
                intersection.retainAll(s2);
                distinct += 2*(s1.size()-intersection.size())*(s2.size()-intersection.size());
            }
            
        }

        return distinct;
    }

    public static void main(String[] args) {
        q2306 sol = new q2306();
        String[] ideas = {"coffee","donuts","time","toffee"};
        System.out.println(sol.distinctNames(ideas));
    }

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
