package node_definition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode listToTree(List<Integer> list) {
        return listToTree(0, list);
    }

    private static TreeNode listToTree(int i, List<Integer> list) {
        if(list.size() <= i || list.get(i) == null) return null;
        TreeNode node = new TreeNode(list.get(i));
        node.left = listToTree(2*i+1, list);
        node.right = listToTree(2*i+2, list);

        return node;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(3,9,20,null,null,15,7));
        System.out.println(listToTree(list));

        List<Integer> list2 = new ArrayList<>(Arrays.asList(1,null,2,null, null,3));
        System.out.println(listToTree(list2));

    }
}