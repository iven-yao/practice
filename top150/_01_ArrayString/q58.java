package top150._01_ArrayString;

public class q58 {
    public int lengthOfLastWord(String s) {
        int len = 0;
        char[] arr = s.toCharArray();

        int i = arr.length-1;
        while(i >= 0 && arr[i] == ' ') {
            i--;
        }

        while(i >= 0 && arr[i] != ' ') {
            len++;
            i--;
        }
    

        return len;
    }
}
