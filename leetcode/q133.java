package leetcode;
import java.util.*;

public class q133 {
    
    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    class Solution {
        public Node cloneGraph(Node node) {
            if(node == null) return null;
            Node head = new Node(node.val);
            Map<Integer, Node> visited = new HashMap<>();
            dfs(node, head, visited);        
            return head;
        }

        public void dfs (Node node, Node copy, Map<Integer, Node> visited) {
            visited.put(copy.val, copy);

            for(Node adj : node.neighbors) {
                if(visited.get(adj.val) == null) {
                    dfs(adj, new Node(adj.val), visited);
                } 
                copy.neighbors.add(visited.get(adj.val));
            }
        }
    }
}
