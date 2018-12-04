import java.util.Iterator;
import java.util.function.Predicate;

public class FilterGenerator <T> implements Iterable <T> {
   private Iterable <T> mSource ; // the sequence to skip values from
   private Predicate<T> mPred;     // Predicate
   private T mNext;

   public FilterGenerator (Predicate<T> pred, Iterable <T> source) {
      mPred = pred;
      mSource = source;
   }

   public Iterator <T > iterator() {
      return new FilterIterator();
   }
   private class FilterIterator implements Iterator <T> {


      // To allow multiple FilterIterators to walk over the same source , we request a unique
      // source iterator for each FilterIterator.
      private Iterator<T> iterator ;
      private Predicate predicate;
      private T currentValue;

      public FilterIterator() {
         predicate = mPred;
         iterator = mSource.iterator();
      }

      public boolean hasNext() {
         while (iterator.hasNext()) {
            mNext = iterator.next();
            if (predicate.test(mNext)) {
               return true;
            }
         }
         return iterator.hasNext();
      }

      public T next() {
         return mNext;
      }

   }
}

