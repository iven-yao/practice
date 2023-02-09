package leetcode;
import java.util.ArrayList;
import java.util.List;

import node_definition.Node;

public class q589 {
    public List<Integer> preorder(Node root) {
        return preorderHelper(root, new ArrayList<Integer>());
    }
    
    public List<Integer> preorderHelper(Node node, List<Integer> list) {
        if(node != null) {
            list.add(node.val);
            for(Node n: node.children) {
                preorderHelper(n, list);
            }
        }
        
        return list;
    }
}
