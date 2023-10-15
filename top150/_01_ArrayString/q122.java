package top150._01_ArrayString;
// 122. Best Time to Buy and Sell Stock II
public class q122 {
    public int maxProfit(int[] prices) {
        int profit = 0;
        int sellAt = prices[prices.length-1];

        for(int i = prices.length-2; i >= 0; i--) {
            if(prices[i] < sellAt) {
                profit += (sellAt - prices[i]);
            } 
            
            sellAt = prices[i];
        }

        return profit;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{7,1,5,3,6,4};

        System.out.println(new q122().maxProfit(prices));
    }
}
