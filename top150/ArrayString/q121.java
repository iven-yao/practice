package top150.ArrayString;

public class q121 {
    public int maxProfit(int[] prices) {
        int max = 0;
        int min = prices[0];

        for(int price: prices) {
            if(price - min > max) max = price-min;
            if(price < min) min = price;
        }

        return max;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{7,1,5,3,6,4};

        int res = new q121().maxProfit(prices);
        System.out.println(res);
    }
}
