package leetcode;
import java.util.*;
import node_definition.TreeNode;

public class q652 {

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Integer> cache = new HashMap<>();
        List<TreeNode> res = new ArrayList<>();
        postorder(root, cache, res);
    
        return res;
    }
    
    public String postorder(TreeNode node, Map<String, Integer> cache, List<TreeNode> res) {
        if(node == null) return "$";
    
        String structure = postorder(node.left, cache, res) +","+ postorder(node.right, cache, res) +","+ node.val;
        cache.put(structure, cache.getOrDefault(structure, 0)+1);
        if(cache.get(structure) == 2) {res.add(node);};
        return structure;
    }
}
