package top150.ArrayString;

public class q151 {
    public String reverseWords(String s) {
        int index = s.length() - 1;
        StringBuilder res = new StringBuilder();

        while(index >= 0) {
            if(s.charAt(index) == ' ') {
                index--;
            }  else {
                StringBuilder sb = new StringBuilder();
                while(index >= 0 && s.charAt(index) != ' ') {
                    sb.append(s.charAt(index--));
                }
                res.append(sb.reverse().toString()).append(' ');
            }
        }

        return res.deleteCharAt(res.length()-1).toString();
    }
}
