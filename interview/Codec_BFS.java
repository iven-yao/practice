package interview;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import node_definition.Node;

public class Codec_BFS {
    // dfs
    public String serialize(Node root) {
        List<String> list = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);

        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                Node curr = q.poll();
                if(curr == null) {
                    list.add("X");
                } else {
                    list.add(String.valueOf(curr.val));
                    q.addAll(curr.children);
                    q.add(null);
                }
            }
        }
        
        return String.join(",", list);
    }

    public Node deserialize(String code) {
        String[] data_arr = code.split(",");

        if(data_arr[0].equals("X")) return null;
        Node root = new Node(Integer.parseInt(data_arr[0]));

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int i = 2;
        while( i < data_arr.length) {
            Node curr = q.poll();

            while(!data_arr[i].equals("X")) {
                Node child = new Node(Integer.parseInt(data_arr[i++]));
                curr.children.add(child);
                q.add(child);
            }

            i++;
        }

        return root;
    }

    public static void main(String[] args) {
        Node root = new Node(0);
        root.children.add(new Node(1));
        root.children.add(new Node(2));
        root.children.add(new Node(3));
        root.children.add(new Node(4));
        root.children.get(0).children.add(new Node(5));
        root.children.get(0).children.add(new Node(6));
        root.children.get(0).children.add(new Node(7));
        root.children.get(1).children.add(new Node(8));
        root.children.get(2).children.add(new Node(9));
        root.children.get(2).children.add(new Node(10));
        root.children.get(2).children.add(new Node(11));
        root.children.get(3).children.add(new Node(12));
        Codec_BFS ser = new Codec_BFS();
        System.out.println(ser.serialize(root));
        Codec_BFS deser = new Codec_BFS();

        Node tree = deser.deserialize(ser.serialize(root));

        tree.printNAryTree();
    }
}
