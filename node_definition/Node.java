package node_definition;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
        children = new ArrayList<>();
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
    
    public void printNAryTree(){
        if(this != null) {
            Queue<Node> queue = new LinkedList<>();
            queue.offer(this);
            while(!queue.isEmpty()) {
                int len = queue.size();
                for(int i=0;i<len;i++) { // so that we can reach each level
                    Node node = queue.poll();
                    System.out.print(node.val + " ");
                    for (Node item : node.children) { // for-Each loop to iterate over all childrens
                        queue.offer(item);
                    }
                }
                System.out.println();
            }
        }
    }
}
