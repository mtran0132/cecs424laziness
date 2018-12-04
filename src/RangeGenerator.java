import java.util.Iterator;

public class RangeGenerator implements Iterable < Integer > {
   private int mStart , mEnd , mIncrement ;
   public RangeGenerator ( int start, int end, int incr ) {
      mStart = start ;
      mEnd = end ;
      mIncrement = incr ;
   }
   public Iterator < Integer > iterator () {
      return new RangeIterator ();
   }
   private class RangeIterator implements Iterator < Integer > {
      private int mCurrent ;
// In Java , inner classes have access to the fields of the outer class , and use the values
// from the object that actually instantiated the inner type .
// So " mStart " refers to the mStart value of the RangeGenerator that calls " new RangeIterator ".
      public RangeIterator () {
         mCurrent = mStart ;
      }
      public boolean hasNext () {
         return mCurrent < mEnd ;
      }
      public Integer next () {
         int temp = mCurrent ;
         mCurrent += mIncrement ;
         return temp ;
      }
   }
}
