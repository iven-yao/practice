package node_definition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode() {}
    public TreeNode(int val) { this.val = val; }
    public TreeNode(int val, TreeNode left, TreeNode right) {
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

    public String toStringHelper() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        List<TreeNode> currLevel = new ArrayList<>();
        currLevel.add(this);
        while(currLevel.size() != 0) {
            List<TreeNode> nextLevel = new ArrayList<>();
            int nullCount = 0;
            for(TreeNode node: currLevel) {
                sb.append((node == null? "null":node.val)+", ");
                if(node == null) {
                    nextLevel.add(null);
                    nextLevel.add(null);
                    nullCount += 2;
                } else {
                    nextLevel.add(node.left);
                    nextLevel.add(node.right);
                    if(node.left == null) nullCount++;
                    if(node.right == null) nullCount++;
                }
            }
            currLevel.removeAll(currLevel);
            if(nullCount != nextLevel.size()) {
                currLevel = nextLevel;
            }
        }

        sb.replace(sb.length()-2, sb.length(), " ]");
        return sb.toString();
    }

    @Override
    public String toString() {
        
        // return "TreeNode{" +
        //         "val=" + val +
        //         ", left=" + left +
        //         ", right=" + right +
        //         '}';
        return toStringHelper();
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(3,9,20,null,null,15,7));
        System.out.println(listToTree(list));

        List<Integer> list2 = new ArrayList<>(Arrays.asList(1,null,2,null, null,3));
        System.out.println(listToTree(list2));

    }
}