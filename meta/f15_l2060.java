package meta;

public class f15_l2060 {
    class Solution {
        private static final int DIFF_OFFSET = 1000;
    
        public boolean possiblyEquals(String s1, String s2) {
            boolean[][][] visited = new boolean[s1.length() + 1][s2.length() + 1][DIFF_OFFSET * 2];
            return possiblyEquals(s1.toCharArray(), s2.toCharArray(), 0, 0, 0, visited);
        }
    
        private boolean possiblyEquals(char[] s1, char[] s2, int i, int j, int diff, boolean[][][] visited) {
            if (i == s1.length && j == s2.length) return diff == 0;
            if (i > s1.length || j > s2.length) return false;
    
            if (visited[i][j][diff + DIFF_OFFSET]) return false;
            visited[i][j][diff + DIFF_OFFSET] = true;
    
            char c1 = i < s1.length ? s1[i] : '*';
            char c2 = j < s2.length ? s2[j] : '*';
            if (Character.isDigit(c1)) {
                int num = 0;
                while (i < s1.length && Character.isDigit(s1[i])) {
                    int digit = s1[i] - '0';
                    num = num * 10 + digit;
                    if (possiblyEquals(s1, s2, i + 1, j, diff + num, visited)) return true;
                    i++;
                }
            } else if (Character.isDigit(c2)) {
                int num = 0;
                while (j < s2.length && Character.isDigit(s2[j])) {
                    int digit = s2[j] - '0';
                    num = num * 10 + digit;
                    if (possiblyEquals(s1, s2, i, j + 1, diff - num, visited)) return true;
                    j++;
                }
            } else {
                if (diff > 0) return possiblyEquals(s1, s2, i, j + 1, diff - 1, visited);
                if (diff < 0) return possiblyEquals(s1, s2, i + 1, j, diff + 1, visited);
                return c1 == c2 && possiblyEquals(s1, s2, i + 1, j + 1, 0, visited);
            }
    
            return false;
        }
    }
}
