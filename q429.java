import java.util.ArrayList;
import java.util.List;

import node_definition.Node;

public class q429 {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        List<Node> curr = new ArrayList<>();
        curr.add(root);
        
        while(!curr.isEmpty()) {
            List<Node> next = new ArrayList<>();
            List<Integer> list = new ArrayList<>();
            for(Node n: curr) {
                list.add(n.val);
                for(Node nn: n.children) {
                    next.add(nn);
                }
            }
            curr = next;
            res.add(list);
        }
        
        return res;
        
    }
}
