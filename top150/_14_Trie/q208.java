package top150._14_Trie;

public class q208 {
    class Node {
        private Node[] children;
        private boolean isEndOfWord;
    
        public Node() {
            children = new Node[26];
            isEndOfWord = false;
        }
    
        public void insert(String word, int index) {
            if (index == word.length()) {
                isEndOfWord = true;
                return;
            }
    
            char c = word.charAt(index);
            if (children[c - 'a'] == null) {
                children[c - 'a'] = new Node();
            }
    
            children[c - 'a'].insert(word, index + 1);
        }
    
        public boolean search(String word, int index) {
            if (index == word.length()) {
                return isEndOfWord;
            }
    
            char c = word.charAt(index);
            
            Node child = children[c - 'a'];
            return child != null && child.search(word, index + 1);
        }
    
        public boolean startsWith(String prefix, int index) {
            if( index == prefix.length()) return true;
    
            char c = prefix.charAt(index);
            Node child = children[c-'a'];
    
            return child != null && child.startsWith(prefix, index+1);
        }
    }
    
    
    class Trie {
        
        Node root;
    
        public Trie() {
            root = new Node();
        }
    
        public void insert(String word) {
            root.insert(word, 0);
        }
        
        public boolean search(String word) {
            return root.search(word, 0);
        }
        
        public boolean startsWith(String prefix) {
            return root.startsWith(prefix, 0);
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
