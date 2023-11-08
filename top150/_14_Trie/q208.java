package top150._14_Trie;

public class q208 {
    class Node{ 
        Node[] children = new Node[26];
        boolean isEnd = false;
    }
    
    class Trie {
        Node root;
    
        public Trie() {
            root = new Node();
        }
    
        public void insert(String word) {
            Node node = root;
            for(char c: word.toCharArray()){
                int index = c-'a';
                if(node.children[index] == null) node.children[index] = new Node();
                node = node.children[index];
            }
            node.isEnd = true;
        }
        
        public boolean search(String word) {
            Node node = root;
            for(char c: word.toCharArray()){
                int index = c-'a';
                if(node.children[index] != null) node = node.children[index];
                else return false;
            }
            return node.isEnd;
        }
        
        public boolean startsWith(String prefix) {
            Node node = root;
            for(char c: prefix.toCharArray()){
                int index = c-'a';
                if(node.children[index] != null) node = node.children[index];
                else return false;
            }
            return true;
        }
    }
    
}
