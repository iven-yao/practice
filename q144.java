import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import node_definition.TreeNode;

public class q144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        return preorderHelper(root, new ArrayList<Integer>());
    }
    
    public List<Integer> preorderHelper(TreeNode node, List<Integer> list) {
        if(node != null) {
            list.add(node.val);
            if(node.left!=null) {
                preorderHelper(node.left, list);
            }
            if(node.right!=null) {
                preorderHelper(node.right, list);
            }
        }
        
        return list;
    }

    public static void main(String[] args) {
        System.out.println(new q144().preorderTraversal(TreeNode.listToTree(Arrays.asList(1,null,2,null,null,3))));
    }
}
