package interview;

import java.util.ArrayList;
import java.util.List;

import node_definition.Node;

public class Codec_DFS {
    // dfs
    public String serialize(Node root) {
        List<String> list = new ArrayList<>();

        encode(root, list);
        
        return String.join(",", list);
    }

    private void encode(Node root, List<String> list) {
        if(root != null) {
            list.add(String.valueOf(root.val));

            if(root.children != null) {
                for(Node child: root.children) {
                    encode(child, list);
                }
            }

            list.add("X");
        } else {
            list.add("X");
        }
    }

    public Node deserialize(String code) {
        String[] data_arr = code.split(",");
        return decode(data_arr);
    }

    int index = 0;
    private Node decode(String[] data_arr) {
        String curr = data_arr[index++];
        if(curr.equals("X")) return null;
        List<Node> children = new ArrayList<>();
        while(index < data_arr.length) {
            Node child = decode(data_arr);
            if(child != null) {
                children.add(child);
            } else {
                break;
            }
        }

        Node root = new Node(Integer.parseInt(curr), children);
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
        Codec_DFS ser = new Codec_DFS();
        System.out.println(ser.serialize(root));
        Codec_DFS deser = new Codec_DFS();

        Node tree = deser.deserialize(ser.serialize(root));

        tree.printNAryTree();
    }
}
