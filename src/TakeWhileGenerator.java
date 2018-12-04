import java.util.Iterator;
import java.util.function.Predicate;

public class TakeWhileGenerator <T> implements Iterable <T> {
    private Iterable <T> mSource ; // the sequence to skip values from
    private Predicate<T> mPred;     // Predicate
    public TakeWhileGenerator (Predicate<T> pred, Iterable <T> source) {
        mPred = pred;
        mSource = source;
    }

    public Iterator <T > iterator() {
        return new takeWhileIterator();
    }
    private class takeWhileIterator implements Iterator <T > {

        // To allow multiple SkipIterators to walk over the same source , we request a unique
        // source iterator for each SkipIterator.
        private Iterator<T> iterator ;
        private Predicate predicate;
        private boolean keepTaking;
        private T currentValue;

        public takeWhileIterator() {
            predicate = mPred;
            iterator = mSource.iterator();
            keepTaking = true;
        }

        public boolean hasNext () {
            currentValue = iterator.next();
            if(!predicate.test(currentValue)){
                keepTaking = false;
            }
            return keepTaking && iterator.hasNext();
        }

        public T next () {
            return currentValue;
        }
    }
}

