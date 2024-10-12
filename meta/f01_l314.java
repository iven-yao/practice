package meta;

import java.util.*;

import node_definition.TreeNode;

public class f01_l314 {

    public List<List<Integer>> verticalOrder(TreeNode root) {
        if(root == null) return new ArrayList<>();

        Map<Integer, List<Integer>> map = new HashMap<>();
        int lowerKeyBound = 0;
        int upperKeyBound = 0;

        Queue<TreeNode> qNode = new LinkedList<>();
        Queue<Integer> qCol = new LinkedList<>();
        qNode.offer(root);
        qCol.offer(0);

        while(!qNode.isEmpty()) {
            TreeNode node = qNode.poll();
            int col = qCol.poll();

            map.putIfAbsent(col, new ArrayList<>());
            map.get(col).add(node.val);
            lowerKeyBound = Math.min(lowerKeyBound, col);
            upperKeyBound = Math.max(upperKeyBound, col);

            if(node.left != null) {
                qNode.offer(node.left);
                qCol.offer(col-1);
            }

            if(node.right != null) {
                qNode.offer(node.right);
                qCol.offer(col+1);
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        for(int i = lowerKeyBound; i <= upperKeyBound; i++) {
            res.add(map.get(i));
        }

        return res;
    }

    private static String resultBuilder(List<List<Integer>> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int j = 0; j < list.size(); j++) {
            List<Integer> l = list.get(j);
            sb.append("[");
            for(int i = 0 ; i < l.size(); i++) {
                sb.append(l.get(i));
                if(i != l.size() - 1) {
                    sb.append(",");
                }
            }
            sb.append("]");
            if(j != list.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");

        return sb.toString();
    }


    public static void test(TreeNode input, String expected) {
        
        f01_l314 sol = new f01_l314();
        System.out.println("Input:    "+ (input == null ? "[]":input.toString()));
        String res = resultBuilder(sol.verticalOrder(input));
        System.out.println("Output:   " + res);
        System.out.println("Expected: " + expected);
        System.out.println("Pass: " + res.equals(expected));
        System.out.println();
    }

    public static void main(String[] args) {

        TreeNode test1 = TreeNode.listToTree(Arrays.asList(3,9,8,4,0,1,7));
        TreeNode test2 = TreeNode.listToTree(Arrays.asList(3,9,20,null,null,15,7));
        TreeNode test3 = null;

        test(test1, "[[4],[9],[3,0,1],[8],[7]]");
        test(test2, "[[9],[3,15],[20],[7]]");
        test(test3, "[]");
    }
}
