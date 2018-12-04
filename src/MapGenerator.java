import java.util.Iterator;

public class MapGenerator <TIn, TOut> implements Iterable <TOut> {
   private MapFunction<TIn, TOut> mTransform;
   private Iterable <TIn> mSource ;

   public MapGenerator (MapFunction<TIn, TOut> transformer, Iterable <TIn> source) {
      mTransform = transformer;
      mSource = source;
   }

   public Iterator <TOut> iterator() {
      return new MapIterator();
   }

   private class MapIterator implements Iterator <TOut> {

      // To allow multiple MapIterators to walk over the same source , we request a unique
      // source iterator for each MapIterator.
      private Iterator<TIn> iterator ;

      public MapIterator() {
         iterator = mSource.iterator();
      }

      public boolean hasNext () {
         return iterator.hasNext();
      }

      public TOut next () {
         return mapNext();
      }

      private TOut mapNext(){
         TIn next = iterator.next();
         TOut transformed = mTransform.transform(next);
         return transformed;
      }
   }
}

