public class Demo {
   public static void main(String[] args) {
      for (Integer i : new SkipGenerator <Integer>(3 , new RangeGenerator (1, 100000, 1))) {
         System.out.println (i);
      }
   }
}
