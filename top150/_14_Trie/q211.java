package top150._14_Trie;

public class q211 {
    class TrieNode {
        private TrieNode[] children;
        private boolean isEndOfWord;
    
        public TrieNode() {
            children = new TrieNode[26];
            isEndOfWord = false;
        }
    
        public void insert(String word, int index) {
            if (index == word.length()) {
                isEndOfWord = true;
                return;
            }
    
            char c = word.charAt(index);
            if (children[c - 'a'] == null) {
                children[c - 'a'] = new TrieNode();
            }
    
            children[c - 'a'].insert(word, index + 1);
        }
    
        public boolean search(String word, int index) {
            if (index == word.length()) {
                return isEndOfWord;
            }
    
            char c = word.charAt(index);
            if (c == '.') {
                for (TrieNode child : children) {
                    if (child != null && child.search(word, index + 1)) {
                        return true;
                    }
                }
                return false;
            } else {
                TrieNode child = children[c - 'a'];
                return child != null && child.search(word, index + 1);
            }
        }
    }
    
    public class WordDictionary {
        private TrieNode root;
    
        public WordDictionary() {
            root = new TrieNode();
        }
    
        public void addWord(String word) {
            root.insert(word, 0);
        }
    
        public boolean search(String word) {
            return root.search(word, 0);
        }
    }
}
