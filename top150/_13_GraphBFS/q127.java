package top150._13_GraphBFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class q127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> visited = new HashSet<>();
        
        HashSet<String> dict = new HashSet<>();
        for(String word: wordList) dict.add(word);
        if(!dict.contains(endWord)) return 0;

        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        visited.add(beginWord);

        int len = beginWord.length();
        int steps = 1;
        while(!q.isEmpty()) {
            int size = q.size();

            for(int i = 0; i < size; i++) {
                String word = q.poll();
                if(word.equals(endWord)) return steps;
                char[] cArr = word.toCharArray();
                for(int j = 0; j < len; j++) {
                    char og = cArr[j];
                    for(char c = 'a'; c <= 'z'; c++) {
                        cArr[j] = c;
                        String next = new String(cArr);
                        if(!visited.contains(next) && dict.contains(next)) {
                            q.offer(next);
                            visited.add(next);
                        }
                    }
                    cArr[j] = og;
                }
            }
            steps++;
        }
        return 0;
    }
}
