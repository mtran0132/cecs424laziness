import java.util.Iterator;
import java.util.function.Predicate;

public class TakeWhileGenerator <T> implements Iterable <T> {
    private Iterable <T> mSource ; // the sequence to skip values from
    private Predicate<T> mPred;     // Predicate
    private int skipAmount ; // The number of values to skip
    public TakeWhileGenerator (Predicate<T> pred, Iterable <T> source) {
        mPred = pred;
        mSource = source;
    }
    public Iterator <T > iterator() {
        return new SkipIterator();
    }
    private class SkipIterator implements Iterator <T > {
        private int iterSkipAmount;
        // To allow multiple SkipIterators to walk over the same source , we request a unique
        // source iterator for each SkipIterator.
        private Iterator<T> iterator ;

        public SkipIterator() {
            iterSkipAmount = skipAmount ;
            iterator = mSource.iterator();
        }

        public boolean hasNext () {
            while(iterSkipAmount > 0){
                --iterSkipAmount;
                iterator.next();
            }
            return iterSkipAmount == 0 && iterator.hasNext();
        }

        public T next () {
            return iterator.next();
        }
    }
}

