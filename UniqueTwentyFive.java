import java.util.ArrayList;
import java.util.List;

public class UniqueTwentyFive {
    
    List<List<String>> res;
    int stack = 0;
    public void helper(String[] inputs, int id, int count, int[] unique, List<String> list) {
        stack++;
        if(count == 5) {
            res.add(new ArrayList<>(list));
            return;
        }

        if(id >= inputs.length) {
            return;
        }

        // don't add to list
        helper(inputs, id+1, count, unique, list);

        // add to list if compatible
        String s = inputs[id];
        boolean compatible = true;
        for(char c: s.toCharArray()) {
            unique[c-'a']++;

            if(unique[c-'a'] > 1) {
                compatible = false;
            }
        }

        if(compatible) {
            // System.out.println("compatible!");
            // System.out.println(count);
            list.add(s);

            helper(inputs, id+1, count+1, unique, list);

            list.remove(list.size()-1);
        }

        for(char c: s.toCharArray()) {
            unique[c-'a']--;
        }
    }

    public List<List<String>> solve(String[] inputs) {
        res = new ArrayList<>();
        stack = 0;
        helper(inputs, 0, 0, new int[26], new ArrayList<>());
        return res;
    }

    private static void print(List<String> list) {
        for(String s: list) {
            System.out.print(s);
            System.out.print(",");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        UniqueTwentyFive o = new UniqueTwentyFive();
        String[] inputs = new String[]{"abcde", "fghij", "klmno", "pqrst", "uvwxy", "zabcd", "apple", "zebra", "ocean", "quick", "world", "jumps", "foxes", "liver"};

        for(List<String> s: o.solve(inputs)) {
            print(s);
        }
        System.out.println(o.stack);


        String[] inputs2 = new String[]{"abcde","fghij","klmno","pqrst","uvwxy"};
        for(List<String> s: o.solve(inputs2)) {
            print(s);
        }
        System.out.println(o.stack);

        String[] inputs3 = new String[]{"abcde","abcde","abcde","abcde","abcde"};
        for(List<String> s: o.solve(inputs3)) {
            print(s);
        }
        System.out.println(o.stack);
    }
}
