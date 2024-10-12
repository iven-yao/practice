public class Segment
{
    public static Areas areas(double r, double a) {
        //throw new UnsupportedOperationException("Waiting to be implemented.");
        double pi = Math.PI;
        double circleArea = pi * Math.pow(r, 2);
        double segmentArea = 0.5 * Math.pow(r,2) * (a * pi / 180 - Math.sin(a * pi / 180));
        
        return new Areas(segmentArea, circleArea-segmentArea);
    }
    
    public static class Areas {
        public final double inside, outside;

        public Areas(double inside, double outside) {         
            this.inside = inside;
            this.outside = outside;
        }
    }
    
    public static void main(String[] args) {
        Areas areas = Segment.areas(10, 90);
        System.out.println("Areas: " + areas.inside + ", " + areas.outside);  
    }
}