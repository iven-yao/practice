package leetcode;
import java.util.*;

public class q2410 {
    class Solution {
        public int matchPlayersAndTrainers(int[] players, int[] trainers) {
            Arrays.sort(players);
            Arrays.sort(trainers);
    
            int index = 0;
            int count = 0;
            for(int player: players) {
                while(index < trainers.length && player > trainers[index]) {index++;};
                if(index++ < trainers.length) count++;
                else break;
            }
    
            return count;
        }
    }
}
