package leetcode;

public class q297 {

// Definition for a binary tree node.
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        encode(root, sb);

        int len = sb.length();
        sb.deleteCharAt(len-1);
        System.out.println(sb.toString());
        
        return sb.toString();
    }

    private void encode(TreeNode node, StringBuilder sb) {
        if(node != null) {
            sb.append(node.val);
            sb.append(",");

            encode(node.left, sb);
            encode(node.right, sb);
        } else {
            sb.append("X");
            sb.append(",");
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] data_arr = data.split(",");
        return decode(data_arr);
    }

    int index = 0;
    private TreeNode decode(String[] data_arr) {
        if(index == data_arr.length) return null;
        String curNode = data_arr[index++];
        if(curNode.equals("X")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(curNode));
        root.left = decode(data_arr);
        root.right = decode(data_arr);

        return root;

    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
}
