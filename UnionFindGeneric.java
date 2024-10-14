import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnionFindGeneric<T>{
    
    Map<T, Integer> indexMap;
    Map<Integer, T> nodeMap;
    List<Integer> root;
    List<Integer> rank;

    public UnionFindGeneric(){
        indexMap = new HashMap<>();
        nodeMap = new HashMap<>();
        root = new ArrayList<>();
        rank = new ArrayList<>();
    }

    public int create(T node) {
        Integer id = indexMap.get(node);
        if(id == null) {
            int newId = nodeMap.size();
            indexMap.put(node, newId);
            nodeMap.put(newId, node);
            root.add(newId);
            rank.add(1);
            id = newId;
        } else {
            System.out.println("Create failed: node value already exist");
        }

        return id;
    }

    // find the rootId of the set that contains node
    public int find(T node) {
        Integer id = indexMap.get(node);
        if(id == null) {
            // create new
            id = create(node);
        }

        int rootId = root.get(id);
        if(rootId == id) {
            return id;
        }

        root.set(id, find(nodeMap.get(rootId)));
        return root.get(id);
    }

    // union
    public void union(T node1, T node2) {
        int root1id = find(node1);
        int root2id = find(node2);
        int id1rank = rank.get(root1id);
        int id2rank = rank.get(root2id);

        if(id1rank < id2rank) {
            root.set(root1id, root2id);
        } else if( id1rank > id2rank) {
            root.set(root2id, root1id);
        } else {
            root.set(root2id, root1id);
            rank.set(root1id, id1rank+1);
        }
    }

    public boolean isConnect(T node1, T node2) {
        return find(node1) == find(node2);
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    private void test(T v1, T v2, boolean expected) {
        boolean output = this.isConnect(v1,v2);
        System.out.println("output:   " + output);
        System.out.println("expected: " + expected);
        if(output == expected) {
            System.out.println(ANSI_GREEN+ "PASS" + ANSI_RESET + "\n");
        } else {
            System.out.println(ANSI_RED+ "FAIL" + ANSI_RESET + "\n");
        }
    }

    public static void main(String[] args) {
        UnionFindGeneric<String> uf = new UnionFindGeneric<>();
        uf.union("Jennie", "Jisoo");
        uf.union("Jennie", "Rose");
        uf.union("Jennie", "Lisa");
        uf.union("Karina", "Winter");
        uf.union("NingNing", "Giselle");
        uf.union("Winter", "NingNing");
        uf.union("Minji", "Hanni");
        uf.union("Hanni", "Haerin");
        uf.union("Hanni", "Hyein");
        uf.union("Hanni", "Danielle");

        //Check connectivity:
        uf.test("Jennie", "Jisoo", true);
        uf.test("Jennie", "NingNing", false);
        uf.test("Jisoo", "Lisa", true);
        uf.test("Karina", "NingNing", true);
        uf.test("Minji", "Haerin", true);
        uf.test("Hanni", "Jisoo", false);
        uf.test("Jennie", "Jennie", true);
    }
}
