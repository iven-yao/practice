package leetcode;
import java.util.*;

public class q1472 {
    class BrowserHistory {

        List<String> history;
        int curr;
        int end;
    
        public BrowserHistory(String homepage) {
            history = new ArrayList<>();
            history.add(homepage);
            curr = 0;
            end = 0;
        }
        
        public void visit(String url) {
            curr++;
            history.add(curr, url);
            end = curr;
        }
        
        public String back(int steps) {
            if(steps > curr) {
                steps = curr;
            }
    
            curr -= steps;
            return history.get(curr);
        }
        
        public String forward(int steps) {
            curr += steps;
            if(curr > end) curr = end;
    
            return history.get(curr);
        }
    }
}
