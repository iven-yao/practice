package interview;

import java.util.HashMap;
import java.util.Map;

public class DNA {

    public static String complementReverse(String input) throws Exception{
        Map<Character, Character> complement = new HashMap<>();
        complement.put('A','T');
        complement.put('T','A');
        complement.put('C','G');
        complement.put('G','C');

        StringBuilder sb = new StringBuilder();
        for(int i = input.length() - 1; i >= 0; i--) {
            
            if(complement.get(input.charAt(i)) == null) {
                throw new Exception("invalid input");
            }
            sb.append(complement.get(input.charAt(i)));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        //test
        String[] testCases = new String[]{
            "ATCGATCG",
            "",
            "AAAAAAAA",
            "CAPYBARA"
        };

        for(String test: testCases) {
            try {
                System.out.println(complementReverse(test));
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }
}