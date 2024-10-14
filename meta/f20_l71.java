package meta;

import java.util.Deque;
import java.util.LinkedList;

public class f20_l71 {
    class Solution {
        public String simplifyPath(String path) {
            Deque<String> dq = new LinkedList<>();

            String[] dirs = path.split("/");
            
            for(String dir: dirs) {
                if(dir.equals("..")) {
                    dq.pollLast();
                }else if(!dir.equals(".") && !dir.equals("")) {
                    dq.offerLast(dir);
                }
            }

            StringBuilder sb = new StringBuilder();
            while(!dq.isEmpty()) {
                sb.append("/").append(dq.pollFirst());
            }

            if(sb.length() == 0) sb.append("/");

            return sb.toString();
        }
    }
}
