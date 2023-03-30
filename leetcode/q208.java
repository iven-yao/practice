package leetcode;

import java.util.*;

public class q208 {

    // correct way to do it
    class Trie {

        Node node;
        public Trie() {
            node = new Node();
        }
        
        public void insert(String word) {
            node.insert(word, 0);
        }
        
        public boolean search(String word) {
            return node.search(word, 0);
        }
        
        public boolean startsWith(String prefix) {
            return node.startsWith(prefix, 0);
        }
    
        class Node {
            Node[] node;
            boolean isEnd;
            public Node() {
                node = new Node[26];
            }
    
            public void insert(String word, int index) {
                if(index == word.length()) return;
                if(node[word.charAt(index)-'a'] == null) {
                    node[word.charAt(index)-'a'] = new Node();
                }
                Node n = node[word.charAt(index)-'a'];
                if(index == word.length()-1) n.isEnd = true;
                n.insert(word, index+1);
            }
    
            public boolean search(String word, int index) {
                Node n = node[word.charAt(index)-'a'];
                if(index == word.length()-1) {
                    if(n != null && n.isEnd) return true;
                    else return false;
                }
                if(n == null) return false;
                
                return n.search(word, index+1);
            }
    
            public boolean startsWith(String prefix, int index) {
                Node n = node[prefix.charAt(index)-'a'];
                if(index == prefix.length()-1) {
                    if(n != null) return true;
                    else return false;
                }
                if(n == null) return false;
    
                return n.startsWith(prefix, index+1);
            }
        }
    }
    
    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */

    class TrieBAD {

        Map<Character, Trie> map;
        Set<String> dict;
    
        public TrieBAD() {
            map = new HashMap<>();
            dict = new HashSet<>();
        }
        
        public void insert(String word) {
            dict.add(word);
            if(word.length() == 0) return;
            map.computeIfAbsent(word.charAt(0), f -> new Trie());
            map.get(word.charAt(0)).insert(word.substring(1,word.length()));
        }
        
        public boolean search(String word) {
            return dict.contains(word);
        }
        
        public boolean startsWith(String prefix) {
            if(prefix.length() == 0) return true;
            return map.containsKey(prefix.charAt(0)) && 
            (map.get(prefix.charAt(0)).startsWith(prefix.substring(1, prefix.length())));
        }
    }
    
    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
}
