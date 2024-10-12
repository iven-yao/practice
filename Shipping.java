public class Shipping {
    public static int minimalNumberOfPackages(int items, int availableLargePackages, int availableSmallPackages) {
        //throw new UnsupportedOperationException("Waiting to be implemented.");
        
        //greedily using large first;
        int count = 0;
        while(availableLargePackages > 0 && items >= 5) {
            items -= 5;
            availableLargePackages --;
            count++;
        }
        
        return items <= availableSmallPackages ? count + items : -1;
    }
    
    public static void main(String[] args) {
        System.out.println(minimalNumberOfPackages(16, 2, 10));
    }
}