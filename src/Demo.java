import java.util.ArrayList;

public class Demo {
   public static void main(String[] args) {
       ArrayList<Integer> arrayList = new ArrayList<>();
       for(int i = 1; i <=1000; i++){
           arrayList.add(i);
       }

       for(Integer i : new RangeGenerator(1,1001,1)){
           System.out.println(i);
       }

      for (Double i : new FilterGenerator<Double> (i -> i < 5 ,
                            new SkipGenerator <Double>(2 ,
                                new MapGenerator<Integer, Double>(i -> Math.sqrt(i),
                                        new FilterGenerator<Integer>(i -> i % 2 == 1, arrayList))))){
         System.out.println (i);
      }

   }
}
