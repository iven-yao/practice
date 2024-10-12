package interview;

import java.util.Arrays;

public class BankTransactions {
    public static int maxCustomers(int initialAmount, int[] transactions) {
        int currentBalance = initialAmount;
        int max = 0;
        int start = 0;
        int end = 0;

        while (end < transactions.length) {
            if (currentBalance >= 0) {
                currentBalance += transactions[end];
                if(currentBalance >= 0) {
                    max = Math.max(max, end-start+1);
                    System.out.println(Arrays.toString(Arrays.copyOfRange(transactions, start, end+1)));
                }
                end++;
            } else {
                currentBalance -= transactions[start];
                start++;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int initialAmount = 1;
        int[] transactions = {1, -3, 5, -2, 1, -99};
        System.out.println("Max customers served: " + maxCustomers(initialAmount, transactions));
        
        initialAmount = 1;
        int[] transactions2 = {1, -2, -1, 1, 1, 1};
        System.out.println("Max customers served: " + maxCustomers(initialAmount, transactions2));

        initialAmount = 10;
        int[] transactions3 = {-1, -1, -1, -1, 1, 1, 1, 1, 1, 1};
        System.out.println("Max customers served: " + maxCustomers(initialAmount, transactions3));

        initialAmount = 0;
        int[] transactions4 = {-1, -1, -1, -1};
        System.out.println("Max customers served: " + maxCustomers(initialAmount, transactions4));

        initialAmount = 10;
        int[] transactions5 = {};
        System.out.println("Max customers served: " + maxCustomers(initialAmount, transactions5));

    }
}
